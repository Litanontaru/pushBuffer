package org;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
public class PushBuffer {
    private final Map<Class, Function<Object, EntityInfo>> toInfo;
    private final Map<Class, Consumer> consumers;
    private final FailExtractor failExtractor;

    private final Map<EntityInfo, Set<EntityInfo>> blocks = new ConcurrentHashMap<>();
    private final Map<EntityInfo, EntityContainer> buffer = new ConcurrentHashMap<>();

    public void convertAndSave(Object dto) {
        Consumer consumer = consumers.get(dto.getClass());
        if (consumer != null) {

            consumer.accept(dto);

            List<EntityInfo> fails = failExtractor.extractFails();
            if (fails == null) {
                EntityInfo block = info(dto);
                Set<EntityInfo> list = blocks.get(block);

                if (list != null) {
                    List<Object> toProcess = new ArrayList<>();
                    for (EntityInfo blocked : list) {
                        buffer.computeIfPresent(blocked, (key, container) -> {
                            List<EntityInfo> blockers = container.getIsBlockedBy();
                            blockers.remove(block);
                            if (blockers.isEmpty()) {
                                toProcess.add(container.getDto());
                                return null;
                            } else {
                                return container;
                            }
                        });
                    }
                    toProcess.forEach(this::convertAndSave);
                }
            } else {
                EntityInfo info = info(dto);
                buffer.put(info, new EntityContainer(dto, fails));
                fails.forEach(blocker -> blocks.computeIfAbsent(blocker, key -> ConcurrentHashMap.newKeySet()).add(info));
            }
        }
    }

    private EntityInfo info(Object dto) {
        Function<Object, EntityInfo> toInfo = this.toInfo.get(dto.getClass());
        if (toInfo == null) {
            throw new IllegalArgumentException();
        }
        return toInfo.apply(dto);
    }
}
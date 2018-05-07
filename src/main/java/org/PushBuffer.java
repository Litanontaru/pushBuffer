package org;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@AllArgsConstructor
public class PushBuffer {
    private final Map<Class, Function> mappers;
    private final FailExtractor failExtractor;

    private final Map<EntityInfo, List<EntityInfo>> blocks = new ConcurrentHashMap<EntityInfo, List<EntityInfo>>();
    private final Map<EntityInfo, EntityContainer> buffer = new ConcurrentHashMap<EntityInfo, EntityContainer>();

    public void convertAndSave(Object dto) {
        //todo
    }
}
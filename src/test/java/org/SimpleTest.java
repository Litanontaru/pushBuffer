package org;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class SimpleTest {
    public static void main(String[] args) {
        Session session = new Session();

        Map<Class, Function<Object, EntityInfo>> toInfo = new ConcurrentHashMap<>();
        toInfo.put(A.class, a -> new EntityInfo(A.class, ((A) a).id));
        toInfo.put(BDto.class, b -> new EntityInfo(B.class, ((BDto) b).id));

        InMemoryStore store = new InMemoryStore(session);
        Map<Class, Consumer> consumers = new ConcurrentHashMap<>();
        consumers.put(A.class, new AConsumer(store));
        consumers.put(BDto.class, new BConsumer(store));

        PushBuffer buffer = new PushBuffer(toInfo, consumers, session);

        buffer.convertAndSave(new A(1));
        buffer.convertAndSave(new BDto(1, 2));
        buffer.convertAndSave(new A(2));

    }

    @AllArgsConstructor
    private static class A {
        final long id;
    }

    @AllArgsConstructor
    private static class B {
        final long id;
        final A a;
    }

    @AllArgsConstructor
    private static class BDto {
        final long id;
        final long aId;
    }

    @AllArgsConstructor
    private static class AConsumer implements Consumer<A> {
        private final InMemoryStore store;

        @Override
        public void accept(A a) {
            store.persist(a.id, a);
        }
    }

    @AllArgsConstructor
    private static class BConsumer implements Consumer<BDto> {
        private final InMemoryStore store;

        @Override
        public void accept(BDto b) {
            store.persist(b.id, new B(b.id, store.find(A.class, b.aId)));
        }
    }
}

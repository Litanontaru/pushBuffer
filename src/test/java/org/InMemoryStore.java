package org;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class InMemoryStore {
    private final FailLogger failLogger;

    private final Map<Class, Map<Object, Object>> store = new ConcurrentHashMap<>();

    public void persist(Object key, Object value) {
        if (failLogger.isOk()) {
            store.computeIfAbsent(value.getClass(), k -> new ConcurrentHashMap<>()).put(key, value);
        }
    }

    public <T> T find(Class<T> clazz, Object key) {
        Map<Object, Object> map = store.get(clazz);
        Object result = map == null ? null : map.get(key);
        if (result == null) {
            failLogger.logFail(new EntityInfo(clazz, key));
        }
        return (T) result;
    }
}

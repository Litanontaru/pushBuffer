package org;

import java.util.ArrayList;
import java.util.List;

public class Session implements FailLogger, FailExtractor {
    private final ThreadLocal<List<EntityInfo>> session = new ThreadLocal<List<EntityInfo>>();

    public void logFail(EntityInfo entityInfo) {
        List<EntityInfo> list = session.get();
        if (list == null) {
            list = new ArrayList<>();
            session.set(list);
        }
        list.add(entityInfo);
    }

    public boolean isOk() {
        return session.get() == null;
    }

    public List<EntityInfo> extractFails() {
        List<EntityInfo> result = session.get();
        if (result != null) {
            session.remove();
        }
        return result;
    }
}

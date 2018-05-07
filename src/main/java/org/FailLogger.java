package org;

public interface FailLogger {
    void logFail(EntityInfo entityInfo);

    boolean isOk();
}

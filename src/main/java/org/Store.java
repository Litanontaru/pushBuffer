package org;

import lombok.AllArgsConstructor;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Store {
    private final EntityManager entityManager;
    private final FailLogger failLogger;

    public void persist(Object o) {
        if (failLogger.isOk()) {
            entityManager.persist(o);
        }
    }

    public <T> T merge(T t) {
        if (failLogger.isOk()) {
            return entityManager.merge(t);
        }
        return t;
    }

    public void remove(Object o) {
        if (failLogger.isOk()) {
            entityManager.remove(o);
        }
    }

    public <T> T find(Class<T> aClass, Object o) {
        T result = entityManager.find(aClass, o);
        if (result == null) {
            failLogger.logFail(new EntityInfo(aClass, o));
        }
        return result;
    }

    public <T> T find(Class<T> aClass, Object o, Map<String, Object> map) {
        T result = entityManager.find(aClass, o, map);
        if (result == null) {
            failLogger.logFail(new EntityInfo(aClass, o));
        }
        return result;
    }

    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType) {
        T result = entityManager.find(aClass, o, lockModeType);
        if (result == null) {
            failLogger.logFail(new EntityInfo(aClass, o));
        }
        return result;
    }

    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType, Map<String, Object> map) {
        T result = entityManager.find(aClass, o, lockModeType, map);
        if (result == null) {
            failLogger.logFail(new EntityInfo(aClass, o));
        }
        return result;
    }

    public <T> T getReference(Class<T> aClass, Object o) {
        T result = entityManager.getReference(aClass, o);
        if (result == null) {
            failLogger.logFail(new EntityInfo(aClass, o));
        }
        return result;
    }

    public void flush() {
        if (failLogger.isOk()) {
            entityManager.flush();
        }
    }

    //--- OTHER METHODS ------------------------------------------------------------------------------------------------

    public void setFlushMode(FlushModeType flushModeType) {
        entityManager.setFlushMode(flushModeType);
    }

    public FlushModeType getFlushMode() {
        return entityManager.getFlushMode();
    }

    public void lock(Object o, LockModeType lockModeType) {
        entityManager.lock(o, lockModeType);
    }

    public void lock(Object o, LockModeType lockModeType, Map<String, Object> map) {
        entityManager.lock(o, lockModeType, map);
    }

    public void refresh(Object o) {
        entityManager.refresh(o);
    }

    public void refresh(Object o, Map<String, Object> map) {
        entityManager.refresh(o, map);
    }

    public void refresh(Object o, LockModeType lockModeType) {
        entityManager.refresh(o, lockModeType);
    }

    public void refresh(Object o, LockModeType lockModeType, Map<String, Object> map) {
        entityManager.refresh(o, lockModeType, map);
    }

    public void clear() {
        entityManager.clear();
    }

    public void detach(Object o) {
        entityManager.detach(o);
    }

    public boolean contains(Object o) {
        return entityManager.contains(o);
    }

    public LockModeType getLockMode(Object o) {
        return entityManager.getLockMode(o);
    }

    public void setProperty(String s, Object o) {
        entityManager.setProperty(s, o);
    }

    public Map<String, Object> getProperties() {
        return entityManager.getProperties();
    }

    public Query createQuery(String s) {
        return entityManager.createQuery(s);
    }

    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return entityManager.createQuery(criteriaQuery);
    }

    public Query createQuery(CriteriaUpdate criteriaUpdate) {
        return entityManager.createQuery(criteriaUpdate);
    }

    public Query createQuery(CriteriaDelete criteriaDelete) {
        return entityManager.createQuery(criteriaDelete);
    }

    public <T> TypedQuery<T> createQuery(String s, Class<T> aClass) {
        return entityManager.createQuery(s, aClass);
    }

    public Query createNamedQuery(String s) {
        return entityManager.createNamedQuery(s);
    }

    public <T> TypedQuery<T> createNamedQuery(String s, Class<T> aClass) {
        return entityManager.createNamedQuery(s, aClass);
    }

    public Query createNativeQuery(String s) {
        return entityManager.createNativeQuery(s);
    }

    public Query createNativeQuery(String s, Class aClass) {
        return entityManager.createNativeQuery(s, aClass);
    }

    public Query createNativeQuery(String s, String s1) {
        return entityManager.createNativeQuery(s, s1);
    }

    public StoredProcedureQuery createNamedStoredProcedureQuery(String s) {
        return entityManager.createNamedStoredProcedureQuery(s);
    }

    public StoredProcedureQuery createStoredProcedureQuery(String s) {
        return entityManager.createStoredProcedureQuery(s);
    }

    public StoredProcedureQuery createStoredProcedureQuery(String s, Class... classes) {
        return entityManager.createStoredProcedureQuery(s, classes);
    }

    public StoredProcedureQuery createStoredProcedureQuery(String s, String... strings) {
        return entityManager.createStoredProcedureQuery(s, strings);
    }

    public void joinTransaction() {
        entityManager.joinTransaction();
    }

    public boolean isJoinedToTransaction() {
        return entityManager.isJoinedToTransaction();
    }

    public <T> T unwrap(Class<T> aClass) {
        return entityManager.unwrap(aClass);
    }

    public Object getDelegate() {
        return entityManager.getDelegate();
    }

    public void close() {
        entityManager.close();
    }

    public boolean isOpen() {
        return entityManager.isOpen();
    }

    public EntityTransaction getTransaction() {
        return entityManager.getTransaction();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManager.getEntityManagerFactory();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    public Metamodel getMetamodel() {
        return entityManager.getMetamodel();
    }

    public <T> EntityGraph<T> createEntityGraph(Class<T> aClass) {
        return entityManager.createEntityGraph(aClass);
    }

    public EntityGraph<?> createEntityGraph(String s) {
        return entityManager.createEntityGraph(s);
    }

    public EntityGraph<?> getEntityGraph(String s) {
        return entityManager.getEntityGraph(s);
    }

    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> aClass) {
        return entityManager.getEntityGraphs(aClass);
    }
}

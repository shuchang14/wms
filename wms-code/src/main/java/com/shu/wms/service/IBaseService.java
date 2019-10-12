package com.shu.wms.service;

import com.shu.wms.entity.BaseQuery;
import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.PageEntity;
import com.shu.wms.model.BaseModel;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

public interface IBaseService {

    <MODEL extends BaseModel>MODEL persist(MODEL var1);

    <MODEL extends BaseModel>MODEL save(MODEL model);

    <T> T merge(T var1);

    /**
     * 删除
     * @param model
     */
    void remove(BaseModel model);

    /**
     * 批量删除
     * @param models
     * @return
     */
    <MODEL extends BaseModel> int removeAll(List<MODEL> models);

    /**
     * 根据uuid获取model
     * @param tClass
     * @param primaryKey
     * @param <T>
     * @return
     */
    <T> T find(Class<T> tClass, Object primaryKey);

    <T> T find(Class<T> var1, Object primaryKey, Map<String, Object> var3);

    <T> T find(Class<T> var1, Object primaryKey, LockModeType var3);

    <T> T find(Class<T> var1, Object primaryKey, LockModeType var3, Map<String, Object> var4);

    <T> T getReference(Class<T> var1, Object primaryKey);

    void flush();

    void setFlushMode(FlushModeType flushModeType);

    FlushModeType getFlushMode();

    void lock(Object var1, LockModeType var2);

    void lock(Object var1, LockModeType var2, Map<String, Object> var3);

    void refresh(Object var1);

    void refresh(Object var1, Map<String, Object> var2);

    void refresh(Object var1, LockModeType var2);

    void refresh(Object var1, LockModeType var2, Map<String, Object> var3);

    void clear();

    void detach(Object var1);

    boolean contains(Object var1);

    LockModeType getLockMode(Object var1);

    void setProperty(String var1, Object var2);

    Map<String, Object> getProperties();

    Query createQuery(String var1);

    <T> TypedQuery<T> createQuery(CriteriaQuery<T> var1);

    Query createQuery(CriteriaUpdate var1);

    Query createQuery(CriteriaDelete var1);

    <T> TypedQuery<T> createQuery(String var1, Class<T> var2);

    Query createNamedQuery(String var1);

    <T> TypedQuery<T> createNamedQuery(String var1, Class<T> var2);

    Query createNativeQuery(String var1);

    Query createNativeQuery(String var1, Class var2);

    Query createNativeQuery(String var1, String var2);

    StoredProcedureQuery createNamedStoredProcedureQuery(String var1);

    StoredProcedureQuery createStoredProcedureQuery(String var1);

    StoredProcedureQuery createStoredProcedureQuery(String var1, Class... var2);

    StoredProcedureQuery createStoredProcedureQuery(String var1, String... var2);

    void joinTransaction();

    boolean isJoinedToTransaction();

    <T> T unwrap(Class<T> var1);

    Object getDelegate();

    void close();

    boolean isOpen();

    EntityTransaction getTransaction();

    EntityManagerFactory getEntityManagerFactory();

    CriteriaBuilder getCriteriaBuilder();

    Metamodel getMetamodel();

    <T> EntityGraph<T> createEntityGraph(Class<T> var1);

    EntityGraph<?> createEntityGraph(String var1);

    EntityGraph<?> getEntityGraph(String var1);

    <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> var1);

    <MODEL extends BaseModel> BaseQuery createBaseQuery(Class<MODEL> tClass);

    /**
     * 根据query xml文件查询
     * @param itemClass   查询返回的实体类
     * @param condition   查询条件实体类
     * @param page        分页对象page
     * @return
     */
    <T>DataGridEntity query(Class<T> itemClass, Object condition, PageEntity page);
    /**
     * 根据query xml文件查询
     * @param itemClass   查询返回的实体类
     * @param condition   查询条件实体类
     * @return
     */
    <T>List<T> query(Class<T> itemClass, Object condition);
}

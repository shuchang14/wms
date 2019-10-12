package com.shu.wms.service.impl;

import com.shu.wms.entity.BaseQuery;
import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.PageEntity;
import com.shu.wms.entity.SqlQueryEntity;
import com.shu.wms.model.BaseModel;
import com.shu.wms.service.IBaseService;
import com.shu.wms.util.BeanUtil;
import com.shu.wms.util.RcUtil;
import com.shu.wms.util.ShuAliasToBeanResultTransformer;
import com.shu.wms.util.XmlUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseServiceImpl implements IBaseService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public <MODEL extends BaseModel>MODEL persist(MODEL var1) {
        //entityManager.persist(var1);
        Object uuid = RcUtil.getObjByAnnotation(var1,Id.class);
       // Object isPersist = RcUtil.getValByField(var1,"isPersist");
        if(RcUtil.isNull(uuid))
            return var1;
        MODEL  model = (MODEL) this.find(var1.getClass(),uuid);
        RcUtil.copyProperties(var1,model,true);
        try {
            Field field = model.getClass().getSuperclass().getDeclaredField("isPersist");
            field.setAccessible(true);
            field.set(model, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public <MODEL extends BaseModel> MODEL save(MODEL model) {
        Object uuid = RcUtil.getObjByAnnotation(model,Id.class);
        if(RcUtil.isNull(uuid)){
            //model.setCreator(SessionUtil.getSessionUser().getUserCode());
            model.setCreateTime(new Date());
        }else{
           /* try {
                Field field = model.getClass().getSuperclass().getDeclaredField("isPersist");
                //设置可访问
                field.setAccessible(true);
                Object isPersist = field.get(model);
                if(isPersist==null)
                    throw new RuntimeException("请先执行IBaseService.persist 方法进行持久化操作！");
                if(!(Boolean)isPersist)
                    throw new RuntimeException("请先执行IBaseService.persist 方法进行持久化操作！");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }*/
            model.setModifiTime(new Date());
        }
        return entityManager.merge(model);
    }

    @Override
    public <T> T merge(T var1) {
        return entityManager.merge(var1);
    }

    /**
     * 删除
     * @param model
     */
    @Override
    public void remove(BaseModel model) {
        entityManager.remove(model);
    }

    /**
     * 批量删除
     * @param models
     * @return
     */
    @Override
    public <MODEL extends BaseModel> int removeAll(List<MODEL> models) {
        int i=0;
        for(BaseModel model:models){
            this.remove(model);
            i++;
        }
        return i;
    }

    /**
     * 根据uuid获取model
     * @param tClass
     * @param primaryKey
     * @param <T>
     * @return
     */
    @Override
    public <T> T find(Class<T> tClass, Object primaryKey) {
        return entityManager.find(tClass,primaryKey);
    }

    @Override
    public <T> T find(Class<T> var1, Object primaryKey, Map<String, Object> var3) {
        return entityManager.find(var1,primaryKey,var3);
    }

    @Override
    public <T> T find(Class<T> var1, Object primaryKey, LockModeType var3) {
        return entityManager.find(var1,primaryKey,var3);
    }

    @Override
    public <T> T find(Class<T> var1, Object primaryKey, LockModeType var3, Map<String, Object> var4) {
        return entityManager.find(var1,primaryKey,var3,var4);
    }

    @Override
    public <T> T getReference(Class<T> var1, Object primaryKey) {
        return entityManager.getReference(var1,primaryKey);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        entityManager.setFlushMode(flushModeType);
    }

    @Override
    public FlushModeType getFlushMode() {
        return entityManager.getFlushMode();
    }

    @Override
    public void lock(Object var1, LockModeType var2) {
        entityManager.lock(var1,var2);
    }

    @Override
    public void lock(Object var1, LockModeType var2, Map<String, Object> var3) {
        entityManager.lock(var1,var2,var3);
    }

    @Override
    public void refresh(Object var1) {
        entityManager.refresh(var1);
    }

    @Override
    public void refresh(Object var1, Map<String, Object> var2) {
        entityManager.refresh(var1,var2);
    }

    @Override
    public void refresh(Object var1, LockModeType var2) {
        entityManager.refresh(var1,var2);
    }

    @Override
    public void refresh(Object var1, LockModeType var2, Map<String, Object> var3) {
        entityManager.refresh(var1,var2,var3);
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public void detach(Object var1) {
        entityManager.detach(var1);
    }

    @Override
    public boolean contains(Object var1) {
        return entityManager.contains(var1);
    }

    @Override
    public LockModeType getLockMode(Object var1) {
        return entityManager.getLockMode(var1);
    }

    @Override
    public void setProperty(String var1, Object var2) {
        entityManager.setProperty(var1,var2);
    }

    @Override
    public Map<String, Object> getProperties() {
        return entityManager.getProperties();
    }

    @Override
    public Query createQuery(String var1) {
        return entityManager.createQuery(var1);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> var1) {
        return entityManager.createQuery(var1);
    }

    @Override
    public Query createQuery(CriteriaUpdate var1) {
        return entityManager.createQuery(var1);
    }

    @Override
    public Query createQuery(CriteriaDelete var1) {
        return entityManager.createQuery(var1);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String var1, Class<T> var2) {
        return entityManager.createQuery(var1,var2);
    }

    @Override
    public Query createNamedQuery(String var1) {
        return entityManager.createNamedQuery(var1);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String var1, Class<T> var2) {
        return entityManager.createNamedQuery(var1,var2);
    }

    @Override
    public Query createNativeQuery(String var1) {
        return entityManager.createNativeQuery(var1);
    }

    @Override
    public Query createNativeQuery(String var1, Class var2) {
        return entityManager.createNativeQuery(var1,var2);
    }

    @Override
    public Query createNativeQuery(String var1, String var2) {
        return entityManager.createNativeQuery(var1,var2);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String var1) {
        return entityManager.createNamedStoredProcedureQuery(var1);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String var1) {
        return entityManager.createStoredProcedureQuery(var1);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String var1, Class... var2) {
        return entityManager.createStoredProcedureQuery(var1,var2);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String var1, String... var2) {
        return entityManager.createStoredProcedureQuery(var1,var2);
    }

    @Override
    public void joinTransaction() {
        entityManager.joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return entityManager.isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> var1) {
        return entityManager.unwrap(var1);
    }

    @Override
    public Object getDelegate() {
        return entityManager.getDelegate();
    }

    @Override
    public void close() {
        entityManager.close();
    }

    @Override
    public boolean isOpen() {
        return entityManager.isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return entityManager.getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManager.getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return entityManager.getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> var1) {
        return entityManager.createEntityGraph(var1);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String var1) {
        return entityManager.createEntityGraph(var1);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String var1) {
        return entityManager.getEntityGraph(var1);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> var1) {
        return entityManager.getEntityGraphs(var1);
    }
    @Override
    public  <MODEL extends BaseModel> BaseQuery createBaseQuery(Class<MODEL> tClass){
        return new BaseQuery(entityManager,tClass);
    }

    private Map<String,List<Integer>> GetseparatorIndex(String sql){
        Map<String,List<Integer>> map=new HashMap<>();
        List<Integer> left=new LinkedList<>();
        List<Integer> right=new LinkedList<>();

        Pattern p1 = Pattern.compile("<<");
        Matcher m1 = p1.matcher(sql);
        while (m1.find()) {
            System.out.println(m1.start());
            left.add(m1.start());
        }
        Pattern p2 = Pattern.compile(">>");
        Matcher m2 = p2.matcher(sql);
        while (m2.find()) {
            System.out.println(m2.start());
            right.add(m2.start());
        }
        map.put("left", left);
        map.put("right", right);
        return map;
    }
    private String setSql(String sql,Object condition,List<Integer> left,List<Integer> right){
        StringBuilder sqlStr=new StringBuilder(sql.substring(0, left.get(0)));
        for(int i=0;i<left.size();i++){
            String conditionSql=sql.substring(left.get(i), right.get(i)+2);
            String fieldName=conditionSql.substring(conditionSql.indexOf(":")+1, conditionSql.length()-2).trim();
            if(condition!=null){
                Object value = getFieldValue(condition,fieldName);
                if(!RcUtil.isNull(value)){
                    sqlStr.append(conditionSql.substring(2, conditionSql.length()-2));
                }
            }
            if((i+1)<left.size()){
                sqlStr.append(sql.substring(right.get(i)+2, left.get(i+1)));
            }
        }
        sqlStr.append(sql.substring(right.get(right.size()-1)+2, sql.length()));
        return sqlStr.toString();
    }
    /**
     * 用getMethods获取所有的getter和setter方法，再取值或赋值。
     * 但不要用getDeclaredMethods，因为getMethods反映此 Class 对象所表示的类或
     * 接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的
     * 公共 member 方法；而getDeclaredMethods反映此 Class 对象表示的类或接口声明
     * 的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
     * 这也许是框架注入属性值都采用setter注入的原因吧。
     *
     * 根据属性名称获取属性值
     * @param obj
     * @param fieldName
     * @return
     */
    private String getFieldValue(Object obj,String fieldName){
        String valueStr="";
        Object value=BeanUtil.getFieldValue(obj, fieldName);
        if(value!=null){
            if (value instanceof Date) {
                SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                valueStr=time.format(value);
            }else{
                valueStr=value.toString();
            }
        }
        return valueStr;
    }

    /**
     * 根据query xml文件查询
     *
     * @param itemClass 查询返回的实体类
     * @param condition 查询条件实体类
     * @param page      分页对象page
     * @return
     */
    @Override
    public <T> DataGridEntity query(Class<T> itemClass, Object condition, PageEntity page) {
       /* ClassLoader classLoader = itemClass.getClassLoader();
        URL url = classLoader.getResource("");
        String path=url.getPath();
        String queryName=itemClass.getName().substring(0,itemClass.getName().length()-4);
        queryName=queryName.replace(".", "/");
        SqlQueryEntity sqlEntity = (SqlQueryEntity) XmlUtil.readXmlObj(SqlQueryEntity.class,path+queryName+".xml");
        String sql=sqlEntity.getSql();
        Map<String,List<Integer>> map = GetseparatorIndex(sql);
        DataGridEntity dataGrid=new DataGridEntity();
        TypedQuery query = entityManager.createQuery(setSql(sql,condition,map.get("left"),map.get("right")),itemClass);*/
        DataGridEntity dataGrid=new DataGridEntity();
        org.hibernate.Query query = creatQuery(itemClass, condition);
        if(condition!=null){
            List<Field[]> fieldList = BeanUtil.getFields(condition.getClass());
            for(Field[] fields:fieldList){
                for(int i=0;i<fields.length;i++){
                    String fieldName=fields[i].getName();
                    Object value = getFieldValue(condition, fieldName);
                    if(!RcUtil.isNull(value)){
                        query.setParameter(fieldName, value);
                    }
                }
            }
        }
        List<T> itemList = query.getResultList();
        dataGrid.setCount(itemList.size());
        if(page!=null){
            query.setFirstResult((page.getPageIndex()-1)*page.getPageSize());
            query.setMaxResults(page.getPageSize());
            dataGrid.setData(query.getResultList());
        }else{
            dataGrid.setData(itemList);
        }
        return dataGrid;
    }

    /**
     * 根据query xml文件查询
     *
     * @param itemClass 查询返回的实体类
     * @param condition 查询条件实体类
     * @return
     */
    @Override
    public <T> List<T> query(Class<T> itemClass, Object condition) {
        org.hibernate.Query query = creatQuery(itemClass, condition);
        if(condition!=null){
            List<Field[]> fieldList = BeanUtil.getFields(condition.getClass());
            for(Field[] fields:fieldList){
                for(int i=0;i<fields.length;i++){
                    String fieldName=fields[i].getName();
                    Object value = getFieldValue(condition, fieldName);
                    if(value!=null&&value.toString().trim()!=""){
                        query.setParameter(fieldName, value);
                    }
                }
            }
        }
        return query.list();
    }

    private <T> org.hibernate.Query creatQuery(Class<T> itemClass, Object condition) {
        String path=itemClass.getClassLoader().getResource("").getPath();
        String queryName=itemClass.getName().substring(0,itemClass.getName().length()-4);
        queryName=queryName.replace(".", "/");
        SqlQueryEntity sqlEntity = (SqlQueryEntity) XmlUtil.readXmlObj(SqlQueryEntity.class,path+queryName+".xml");
        String sql=sqlEntity.getSql();
        Map<String,List<Integer>> map = GetseparatorIndex(sql);
        Session session = entityManager.unwrap(Session.class);
        return session.createSQLQuery(setSql(sql,condition,map.get("left"),map.get("right")))
               .setResultTransformer(new ShuAliasToBeanResultTransformer(itemClass));
    }
}

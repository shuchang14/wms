package com.shu.wms.entity;

import com.shu.wms.model.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseQuery {

    /*private CriteriaQuery criteriaQuery;
    private Predicate predicate;
    private CriteriaBuilder criteriaBuilder;
    private Root<BaseModel> root;*/
    private EntityManager entityManager;
    private StringBuilder sql;
    private Map<String,Object> parameters;
    private Class aClass;
    public  <MODEL extends BaseModel> BaseQuery(EntityManager entityManager, Class<MODEL> c){
        this.entityManager=entityManager;
       // this.criteriaBuilder=entityManager.getCriteriaBuilder();
       // this.criteriaQuery=this.criteriaBuilder.createQuery();
        //this.predicate=this.criteriaBuilder.conjunction();;
       // this.root=criteriaQuery.from(c);
        sql=new StringBuilder();
        parameters=new HashMap<>();
        sql.append("from "+c.getName()+"  where");
        this.aClass=c;
    }
  /*  public BaseQuery(CriteriaQuery criteriaQuery,Predicate predicate,CriteriaBuilder criteriaBuilder,Root<BaseModel> root){
        this.criteriaQuery=criteriaQuery;
        this.predicate=predicate;
        this.criteriaBuilder=criteriaBuilder;
        this.root=root;
    }*/

    public <T>BaseQuery addCondition(Condition condition) {
        String endStr=sql.substring(sql.length()-5,sql.length());
        if("where".equals(endStr)){
            sql.append(" "+condition.getSql().substring(5));
        }else{
            sql.append(condition.getSql());
        }
        if(condition.getParameters()!=null)
            parameters.putAll(condition.getParameters());
        return this;
    }
    public <T> List<T> list() {
        String endStr=sql.substring(sql.length()-5,sql.length());
        String sqlStr=null;
        if("where".equals(endStr)){
            sqlStr=sql.substring(0,sql.length()-5);
        }else{
            sqlStr=sql.toString();
        }
        TypedQuery query =  this.entityManager.createQuery(sqlStr,aClass);
        Set<String> keys = parameters.keySet();
        for(String key:keys){
            query.setParameter(key,parameters.get(key));
        }
        return query.getResultList();
    }
    public <T> List<T> list(String...orderBys) {
        String endStr=sql.substring(sql.length()-5,sql.length());
        String sqlStr=null;
        if("where".equals(endStr)){
            sqlStr=sql.substring(0,sql.length()-5);
        }else{
            sqlStr=sql.toString();
        }
        if(orderBys.length>0){
            sqlStr+=" order by ";
            for(String orderBy:orderBys){
                sqlStr=sqlStr+orderBy+",";
            }
            sqlStr.substring(0,sqlStr.length()-1);
        }

        TypedQuery query =  this.entityManager.createQuery(sqlStr,aClass);
        Set<String> keys = parameters.keySet();
        for(String key:keys){
            query.setParameter(key,parameters.get(key));
        }
        return query.getResultList();
    }

    public DataGridEntity list(PageEntity page) {
        DataGridEntity dataGrid=new DataGridEntity();
        String endStr=sql.substring(sql.length()-5,sql.length());
        String sqlStr=null;
        if("where".equals(endStr)){
            sqlStr=sql.substring(0,sql.length()-5);
        }else{
            sqlStr=sql.toString();
        }
        if(page.getOrderBys()!=null && page.getOrderBys().size()>0){
            sqlStr+=" order by ";
            for(String orderBy:page.getOrderBys()){
                sqlStr=sqlStr+orderBy+",";
            }
            sqlStr.substring(0,sqlStr.length()-1);
        }
        TypedQuery query =  this.entityManager.createQuery(sqlStr,aClass);
        Set<String> keys = parameters.keySet();
        for(String key:keys){
            query.setParameter(key,parameters.get(key));
        }
        dataGrid.setCount(query.getResultList().size());
        if(page!=null){
            int first=(page.getPageIndex()-1)*page.getPageSize();
            query.setFirstResult(first);
            query.setMaxResults(page.getPageSize());
          //  System.out.println("********( limit "+first+","+(first*page.getPageSize())+" )");
        }
        dataGrid.setData(query.getResultList());
        return dataGrid;
    }
    /**
     * CriteriaBuilder criteriaBuilder=this.getCriteriaBuilder();
     * 		CriteriaQuery<UserModel> criteriaQuery = criteriaBuilder.createQuery(UserModel.class);
     * 		Root<UserModel> employee = criteriaQuery.from(UserModel.class);
     * 		Predicate condition = criteriaBuilder.equal(employee.get("userCode"), "shu");
     * 		criteriaQuery.where(condition);
     * 		TypedQuery<UserModel> typedQuery =this.createQuery(criteriaQuery);
     * 		List<UserModel> result = typedQuery.getResultList();
     * @param name
     * @param value
     * @return
     */
   /* public BaseQuery eq(String name,String value){
        this.predicate =criteriaBuilder.equal(this.root.get(name), value);// criteriaBuilder.and(this.predicate, criteriaBuilder.equal(this.root.get(name), value));
        return this;
    }
    public <MODEL extends BaseModel> TypedQuery<MODEL> typedQuery(){
        this.criteriaQuery.where(this.predicate);
        return this.entityManager.createQuery(this.criteriaQuery);
    }
    public <MODEL extends BaseModel> List<MODEL> query(){
        this.criteriaQuery.where(this.predicate);
        return this.entityManager.createQuery(this.criteriaQuery).getResultList();
    }*/
   /* public CriteriaQuery getCriteriaQuery() {
        return criteriaQuery;
    }

    public void setCriteriaQuery(CriteriaQuery criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }*/
}

package com.shu.wms.dao.impl;

import com.shu.wms.dao.CommonDao;
import com.shu.wms.entity.Condition;
import com.shu.wms.entity.DataGridEntity;
import com.shu.wms.entity.PageEntity;
import com.shu.wms.entity.SqlQueryEntity;
import com.shu.wms.model.BaseModel;
import com.shu.wms.util.BeanUtil;
import com.shu.wms.util.XmlUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Override
	public Session getSession() {
		return entityManagerFactory.unwrap(SessionFactory.class).openSession();
	}
	private StringBuilder sql;
	private Map<String,Object> parameters;

	@Override
	public <MODEL extends BaseModel>MODEL save(MODEL model) {
		model=entityManagerFactory.createEntityManager().merge(model);
		//Session session =getSession();
		//model=(MODEL)session.merge(model);
		return model;
	}
	@Transactional
	@Override
	public void remove(BaseModel model) {
		Session session=getSession();
		session.delete(model.getClass().getName(),model);
	}
	@Transactional
	@Override
	public int removeAll(List<BaseModel> models) {
		Session session=getSession();
		int i=0;
		for(BaseModel model:models){
			session.delete(model.getClass().getName(),model);
			i++;
		}
		return i;
	}

	/**
	 * 根据uuid查询
	 *
	 * @param tClass
	 * @param uuid
	 * @return
	 */
	@Override
	public <T> T get(Class<T> tClass, String uuid) {
		return getSession().get(tClass,uuid);
	}
	@Override
	public <T>CommonDao query(Class<T> tClass) {
		sql=new StringBuilder();
		parameters=new HashMap<>();
		sql.append("from "+tClass.getName()+"  where 1=1");
		return this;
	}
	@Override
	public <T>CommonDao addCondition(Condition condition) {
		sql.append(condition.getSql());
		parameters.putAll(condition.getParameters());
		return this;
	}

	/**
	 * 执行查询返回集合
	 *
	 * @return
	 */
	@Override
	public <T> List<T> list() {
		Session session=getSession();
		Query query = session.createQuery(sql.toString());
		Set<String> keys = parameters.keySet();
		for(String key:keys){
			query.setParameter(key,parameters.get(key));
		}
		return query.list();
	}

	@Override
	public DataGridEntity list(PageEntity page) {
		Session session=getSession();
		DataGridEntity dataGrid=new DataGridEntity();
		Query query = session.createQuery(sql.toString());
		Set<String> keys = parameters.keySet();
		for(String key:keys){
			query.setParameter(key,parameters.get(key));
		}
		List<?> itemList = query.list();
		dataGrid.setCount(itemList.size());
		if(page!=null){
			query.setFirstResult((page.getPageIndex()-1)*page.getPageSize());
			query.setMaxResults(page.getPageSize());

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
	 * @param page      分页对象page
	 * @return
	 */
	@Override
	public <T> DataGridEntity query(Class<T> itemClass, Object condition, PageEntity page) {
		String path=itemClass.getClassLoader().getResource("/").getPath();
		String queryName=itemClass.getName().substring(0,itemClass.getName().length()-4);
		queryName=queryName.replace(".", "/");
		SqlQueryEntity sqlEntity = (SqlQueryEntity) XmlUtil.readXmlObj(SqlQueryEntity.class,path+queryName+".xml");
		String sql=sqlEntity.getSql();
		Map<String,List<Integer>> map = GetseparatorIndex(sql);
		DataGridEntity dataGrid=new DataGridEntity();
		Session session=getSession();
		Query query = session.createSQLQuery(setSql(sql,condition,map.get("left"),map.get("right")))
				.setResultTransformer(Transformers.aliasToBean(itemClass));
		//Field[] fields=condition.getClass().getDeclaredFields();//获取属性
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

		List<T> itemList = query.list();
		dataGrid.setCount(itemList.size());
		if(page!=null){
			query.setFirstResult((page.getPageIndex()-1)*page.getPageSize());
			query.setMaxResults(page.getPageSize());
			dataGrid.setData(query.list());
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
	public <T>List<T> query(Class<T> itemClass, Object condition) {
		String path=itemClass.getClassLoader().getResource("").getPath();
		String queryName=itemClass.getName().substring(0,itemClass.getName().length()-4);
		queryName=queryName.replace(".", "/");
		SqlQueryEntity sqlEntity = (SqlQueryEntity) XmlUtil.readXmlObj(SqlQueryEntity.class,path+queryName+".xml");
		String sql=sqlEntity.getSql();
		Map<String,List<Integer>> map = GetseparatorIndex(sql);
		Session session=getSession();
		Query query = session.createSQLQuery(setSql(sql,condition,map.get("left"),map.get("right")))
				.setResultTransformer(Transformers.aliasToBean(itemClass));
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
		return query.list();
	}


	private String setSql(String sql,Object condition,List<Integer> left,List<Integer> right){
		StringBuilder sqlStr=new StringBuilder(sql.substring(0, left.get(0)));
		for(int i=0;i<left.size();i++){
			String conditionSql=sql.substring(left.get(i), right.get(i)+2);
			String fieldName=conditionSql.substring(conditionSql.indexOf(":")+1, conditionSql.length()-2).trim();
			Object value = getFieldValue(condition,fieldName);
			if(value!=null&&value.toString().trim()!=""){
				sqlStr.append(conditionSql.substring(2, conditionSql.length()-2));
			}
			if((i+1)<left.size()){
				sqlStr.append(sql.substring(right.get(i)+2, left.get(i+1)));
			}
		}
		sqlStr.append(sql.substring(right.get(right.size()-1)+2, sql.length()));
		return sqlStr.toString();
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

}

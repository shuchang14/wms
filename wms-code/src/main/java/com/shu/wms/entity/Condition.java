package com.shu.wms.entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Condition {

	private String sql;
	private Map<String,Object> params;

	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public  Map<String,Object> getParameters() {
		return params;
	}
	private Condition(String sql){
		this.sql=sql;
	}
	private Condition(String sql, String param, Object value){
		this.sql=sql;
		if (params != null) {
			this.params.put(param,value);
		}else{
			this.params=new HashMap<>();
			this.params.put(param,value);
		}
	}
	private Condition(String sql, Map<String,Object> map){
		this.sql=sql;
		if (params != null) {
			this.params.putAll(map);
		}else{
			this.params=new HashMap<>();
			this.params.putAll(map);
		}
	}
	public static Condition eq(String propertyName, String value){
		return new Condition(" and "+propertyName+" = :"+propertyName,propertyName,value);
	}
	public static Condition gt(String propertyName, String value){
		return new Condition(" and "+propertyName+" > :"+propertyName,propertyName,value);
	}
	public static Condition lt(String propertyName, String value){
		return new Condition(" and "+propertyName+" < :"+propertyName,propertyName,value);
	}
	public static Condition or(String propertyName, String value){
		return new Condition("  or "+propertyName+" = :"+propertyName,propertyName,value);
	}
	public static Condition like(String propertyName, String value){
		return new Condition(" and "+propertyName+" like '%'||:"+propertyName+"'||%' ",propertyName,value);
	}
	public static Condition orLike(String propertyName, String value){
		return new Condition("  or "+propertyName+" like '%'||:"+propertyName+"||'%' ",propertyName,value);
	}
	public static Condition orLikeLeft(String propertyName, String value){
		return new Condition("  or "+propertyName+" like '%'||:"+propertyName,propertyName,value);
	}
	public static Condition orLikeRight(String propertyName, String value){
		return new Condition("  or "+propertyName+" like :"+propertyName+"||'%' ",propertyName,value);
	}
	public static Condition between(String propertyName, String start, String end){
		return new Condition(" and( "+propertyName+" BETWEEN '"+start+"' and '"+end+"')");
	}
	public static Condition andOr(Map<String,Object> params){
		Set<String> keys=params.keySet();
		StringBuilder sqlm=new StringBuilder(" and(");
		boolean first=true;
		for(String key:keys){
			if(first){
				sqlm.append(key+"=:"+key);
				first=false;
			}else{
				sqlm.append("  or "+key+"=:"+key);
			}
		}
		sqlm.append(")");
		return new Condition(sqlm.toString(),params);
	}
	public static Condition and(Condition... conditions){
		StringBuilder sqlm=new StringBuilder(" and(");
		Map<String,Object> params=new HashMap<>();
		boolean first=true;

		for(Condition condition:conditions){
			if(first){
				sqlm.append(" "+condition.getSql().substring(4));
				first=false;
			}else{
				sqlm.append(condition.getSql());
			}

			params.putAll(condition.getParameters());
		}
		sqlm.append(")");
		return new Condition(sqlm.toString(),params);
	}
}

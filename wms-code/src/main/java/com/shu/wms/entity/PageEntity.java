package com.shu.wms.entity;

import java.util.Set;

public class PageEntity {
     
	private String params;
	private int pageIndex;
	private int pageSize;
	private Set<String> orderBys;
	
	public PageEntity(){
		
	}
	public PageEntity(String...orderBys){
		for(String orderBy:orderBys){
			this.orderBys.add(orderBy);
		}
	}
    public PageEntity(int pageIndex,int pageSize){
		this.pageIndex=pageIndex;
		this.pageSize=pageSize;
	}
	public PageEntity(int pageIndex,int pageSize,String...orderBys){
		this.pageIndex=pageIndex;
		this.pageSize=pageSize;
		for(String orderBy:orderBys){
			this.orderBys.add(orderBy);
		}
	}


	public Set<String> getOrderBys() {
		return orderBys;
	}

	public void setOrderBys(Set<String> orderBys) {
		this.orderBys = orderBys;
	}

	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

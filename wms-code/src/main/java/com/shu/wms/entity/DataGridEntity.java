package com.shu.wms.entity;

import java.util.List;

public class DataGridEntity {

	private int code;
	private int count;
	private String msg;
	private List<?> data;

	public DataGridEntity(){
		this.code=0;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}

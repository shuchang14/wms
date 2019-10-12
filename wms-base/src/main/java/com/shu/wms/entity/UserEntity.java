package com.shu.wms.entity;

import com.shu.wms.model.MenuModel;
import com.shu.wms.model.UserModel;

import java.util.List;

public class UserEntity extends UserModel {

	private String url;
	private String roseCode;
	private Integer grade;
	private List<MenuModel> menuList;

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoseCode() {
		return roseCode;
	}

	public void setRoseCode(String roseCode) {
		this.roseCode = roseCode;
	}

	public List<MenuModel> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuModel> menuList) {
		this.menuList = menuList;
	}
	
	
}

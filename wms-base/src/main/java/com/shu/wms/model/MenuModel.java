package com.shu.wms.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MENU_TABLE")
public class MenuModel extends BaseModel {

	@Id
	@GeneratedValue(generator="system_uuid")
	@GenericGenerator(name="system_uuid",strategy="uuid")
	@Column(name = "menu_uuid")
	private String menuUuid;
	@Column(name = "pre_menu_uuid")
	private String preMenuUuid;
	@Column(name = "menu_code")
	private String menuCode;
	@Column(name = "menu_name")
	private String menuName;
	private String service;
	private String url;
	@Column(name = "icon_url")
	private String iconUrl;
	private Integer num;
	private String type;

	/*@OneToMany(mappedBy="menuUuid")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<RoleMenuModel> rmList;*/
	
	@Transient
	private List<MenuModel> subMenuList;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*public Set<RoleMenuModel> getRmList() {
		return rmList;
	}

	public void setRmList(Set<RoleMenuModel> rmList) {
		this.rmList = rmList;
	}*/

	public String getMenuUuid() {
		return menuUuid;
	}
	public void setMenuUuid(String menuUuid) {
		this.menuUuid = menuUuid;
	}
	public String getPreMenuUuid() {
		return preMenuUuid;
	}
	public void setPreMenuUuid(String preMenuUuid) {
		this.preMenuUuid = preMenuUuid;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public List<MenuModel> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<MenuModel> subMenuList) {
		this.subMenuList = subMenuList;
	}
	
	
}

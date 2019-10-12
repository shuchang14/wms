package com.shu.wms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="ROLE_MENU")
public class RoleMenuModel extends BaseModel {

	@Id
	@GeneratedValue(generator="system_uuid")
	@GenericGenerator(name="system_uuid",strategy="uuid")
	private String uuid;
	@Column(name = "role_uuid")
	private String roleUuid;
	@Column(name = "menu_uuid")
	private String menuUuid;
	@Column(name = "pre_menu_uuid")
	private String preMenuUuid;
	private String type;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRoleUuid() {
		return roleUuid;
	}

	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}

	public String getMenuUuid() {
		return menuUuid;
	}

	public void setMenuUuid(String menuUuid) {
		this.menuUuid = menuUuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPreMenuUuid() {
		return preMenuUuid;
	}

	public void setPreMenuUuid(String preMenuUuid) {
		this.preMenuUuid = preMenuUuid;
	}
}

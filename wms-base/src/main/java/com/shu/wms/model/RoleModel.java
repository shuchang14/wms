package com.shu.wms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="ROLE_TABLE")
public class RoleModel extends BaseModel {

	@Id
	@GeneratedValue(generator="system_uuid")
	@GenericGenerator(name="system_uuid",strategy="uuid")
	@Column(name = "role_uuid")
	private String roleUuid;
	@Column(name = "role_code")
	private String roleCode;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "pre_role_uuid")
	private String preRoleUuid;
	private Integer grade;
	/*@OneToMany(mappedBy="roleUuid")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<UserRoleModel> urList;
	@OneToMany(mappedBy="roleUuid")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<RoleMenuModel> rmList;*/
	
	/*public Set<RoleMenuModel> getRmList() {
		return rmList;
	}
	public void setRmList(Set<RoleMenuModel> rmList) {
		this.rmList = rmList;
	}*/
	public String getRoleUuid() {
		return roleUuid;
	}
	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getPreRoleUuid() {
		return preRoleUuid;
	}
	public void setPreRoleUuid(String preRoleUuid) {
		this.preRoleUuid = preRoleUuid;
	}

	/*public Set<UserRoleModel> getUrList() {
		return urList;
	}

	public void setUrList(Set<UserRoleModel> urList) {
		this.urList = urList;
	}*/
}

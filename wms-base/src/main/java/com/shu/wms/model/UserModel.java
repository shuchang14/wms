package com.shu.wms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="USER_TABLE")
public class UserModel extends BaseModel {

	@Id
	@GeneratedValue(generator="system_uuid")   
    @GenericGenerator(name="system_uuid",strategy="uuid")
	@Column(name = "user_uuid")
	private String userUuid;
	@Column(name = "user_code")
	private String userCode;
	@Column(name = "password")
	private String password;
	@Column(name = "userName")
	private String userName;
	@Column(name = "age")
	private Integer age;
	@Column(name = "sex")
	private Integer sex;
	@Column(name = "addr")
	private String addr;
	@Column(name = "tell")
	private String tell;
	@Column(name = "email")
	private String email;
	/*@OneToMany(mappedBy="userUuid")
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<UserRoleModel> urList;

	public Set<UserRoleModel> getUrList() {
		return urList;
	}

	public void setUrList(Set<UserRoleModel> urList) {
		this.urList = urList;
	}*/

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

package com.shu.wms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="OFFICE_TABLE")
public class OfficeModel extends BaseModel {

	@Id
	@GeneratedValue(generator="system_uuid")   
    @GenericGenerator(name="system_uuid",strategy="uuid")
	@Column(name = "office_uuid")
	private String officeUuid;
	@Column(name = "office_name")
	private String officeName;
	@Column(name = "office_desc")
	private String officeDesc;
	@Column(name = "office_card")
	private String officeCard;
	private String email;
	@Column(name = "super_name")
	private String superName;
	@Column(name = "pass_word")
	private String passWord;

	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getOfficeUuid() {
		return officeUuid;
	}
	public void setOfficeUuid(String officeUuid) {
		this.officeUuid = officeUuid;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeDesc() {
		return officeDesc;
	}
	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}
	public String getOfficeCard() {
		return officeCard;
	}
	public void setOfficeCard(String officeCard) {
		this.officeCard = officeCard;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSuperName() {
		return superName;
	}
	public void setSuperName(String superName) {
		this.superName = superName;
	}
	
	
	
}

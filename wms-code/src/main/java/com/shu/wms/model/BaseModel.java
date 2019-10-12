package com.shu.wms.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@MappedSuperclass  
public class BaseModel {
	
	private String status;
	private String creator;
	@Column(name="create_time")
	private Date createTime;
	private String modifier;
	@Column(name="modifi_time")
	private Date modifiTime;
	private String officeCode;
	private String aux1;
	private String aux2;
	private String aux3;
	private String aux4;
	private String aux5;
	/**
	 * 控制字
	 */
	@Column(name = "control_Word")
	private String controlWord;

	/*@Transient
	private boolean isPersist=false;

	public boolean isPersist() {
		return isPersist;
	}

	public void setPersist(boolean persist) {
		isPersist = persist;
	}*/

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifiTime() {
		return modifiTime;
	}
	public void setModifiTime(Date modifiTime) {
		this.modifiTime = modifiTime;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getAux1() {
		return aux1;
	}

	public void setAux1(String aux1) {
		this.aux1 = aux1;
	}

	public String getAux2() {
		return aux2;
	}

	public void setAux2(String aux2) {
		this.aux2 = aux2;
	}

	public String getAux3() {
		return aux3;
	}

	public void setAux3(String aux3) {
		this.aux3 = aux3;
	}

	public String getAux4() {
		return aux4;
	}

	public void setAux4(String aux4) {
		this.aux4 = aux4;
	}

	public String getAux5() {
		return aux5;
	}

	public void setAux5(String aux5) {
		this.aux5 = aux5;
	}
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
	}

}

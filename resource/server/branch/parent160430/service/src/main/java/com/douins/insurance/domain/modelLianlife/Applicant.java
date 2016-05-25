package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Applicant {
	@XStreamAlias("CellPhoneNumber")
	private String cellphonenumber;
	@XStreamAlias("Email")
	private String email;
	@XStreamAlias("ID")
	private String id;
	@XStreamAlias("IDType")
	private String idtype;
	@XStreamAlias("Name")
	private String name;
	@XStreamAlias("Sex")
	private String sex;
	
	public String getCellphonenumber() {
		return cellphonenumber;
	}
	public void setCellphonenumber(String cellphonenumber) {
		this.cellphonenumber = cellphonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}

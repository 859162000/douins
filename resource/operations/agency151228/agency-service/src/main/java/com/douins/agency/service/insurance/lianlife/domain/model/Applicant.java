package com.douins.agency.service.insurance.lianlife.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Description: 投保人信息
 * @author pan
 * @date 2016年03月04日
 */
@XStreamAlias("reapplicant")
public class Applicant {
  private String IDType,
  ID,
  Name,
  Sex,
  Birthday,
  CellPhoneNumber,
  Address,
  Telephone,
  Email;

public String getIDType() {
	return IDType;
}

public void setIDType(String iDType) {
	IDType = iDType;
}

public String getID() {
	return ID;
}

public void setID(String iD) {
	ID = iD;
}

public String getName() {
	return Name;
}

public void setName(String name) {
	Name = name;
}

public String getSex() {
	return Sex;
}

public void setSex(String sex) {
	Sex = sex;
}

public String getBirthday() {
	return Birthday;
}

public void setBirthday(String birthday) {
	Birthday = birthday;
}

public String getCellPhoneNumber() {
	return CellPhoneNumber;
}

public void setCellPhoneNumber(String cellPhoneNumber) {
	CellPhoneNumber = cellPhoneNumber;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public String getTelephone() {
	return Telephone;
}

public void setTelephone(String telephone) {
	Telephone = telephone;
}

public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	Email = email;
}
  
}

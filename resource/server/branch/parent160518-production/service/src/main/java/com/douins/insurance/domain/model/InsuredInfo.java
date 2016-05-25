package com.douins.insurance.domain.model;

import java.math.BigDecimal;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 
* @ClassName: InsuredInfo 
* @Description: 被保人信息
* @author G. F. 
* @date 2015年12月11日 下午3:43:24 
*
 */
@XStreamAlias("insuredInfo")
public class InsuredInfo {
	private String name;
	private String gender;
	private String birthday;
	private String certiType;
	private String certiNo;
	private String expiryDate;
	private String nationality;
	private String jobCateId;
	private String mobilePhone;
	private String email;
	private String address;
	private String postCode;
	private BigDecimal height;
	private BigDecimal weight;
	private String ralationToPH;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCertiType() {
		return certiType;
	}
	public void setCertiType(String certiType) {
		this.certiType = certiType;
	}
	public String getCertiNo() {
		return certiNo;
	}
	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getJobCateId() {
		return jobCateId;
	}
	public void setJobCateId(String jobCateId) {
		this.jobCateId = jobCateId;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getRalationToPH() {
		return ralationToPH;
	}
	public void setRalationToPH(String ralationToPH) {
		this.ralationToPH = ralationToPH;
	}
}

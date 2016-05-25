package com.douins.insurance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("policyHolder")
public class PolicyHolder {
    @XStreamAlias("HolderName")
    @XStreamAsAttribute
	private String name;
    @XStreamAlias("HolderSex")
    @XStreamAsAttribute
	private String gender;
    @XStreamAlias("HolderBirthday")
    @XStreamAsAttribute
	private String birthday;
    @XStreamAlias("HolderCardType")
    @XStreamAsAttribute
	private String certiType;
    @XStreamAlias("HolderCardNo")
    @XStreamAsAttribute
	private String certiCode;
	private String expiryDate;
	private String nationality;
	private String jobCateId;
	@XStreamAlias("HolderMobile")
    @XStreamAsAttribute
	private String mobile;
	private String homeTel;
	@XStreamAlias("HolderEmail")
    @XStreamAsAttribute
	private String email;
	@XStreamAlias("HolderAddress")
    @XStreamAsAttribute
	private String address1;
	@XStreamAlias("HolderZip")
    @XStreamAsAttribute
	private String zipCode;
	private String income;
	private String height;
	private String weight;
	private String passportCode;
	
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
	public String getCertiCode() {
		return certiCode;
	}
	public void setCertiCode(String certiCode) {
		this.certiCode = certiCode;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String postCode) {
		this.zipCode = postCode;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPassportCode() {
		return passportCode;
	}
	public void setPassportCode(String passportCode) {
		this.passportCode = passportCode;
	}
}

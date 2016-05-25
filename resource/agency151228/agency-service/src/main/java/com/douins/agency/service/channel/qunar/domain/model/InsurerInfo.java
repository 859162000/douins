package com.douins.agency.service.channel.qunar.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class InsurerInfo {
	@XStreamAlias("insurername")
	private String insurerName;
	@XStreamAlias("insurergender")
	private String insurerGender;
	@XStreamAlias("insurerbirthday")
	private String insurerBirthday;
	@XStreamAlias("insureridtype")
	private String insurerIdType;
	@XStreamAlias("insureridno")
	private String insurerIdNo;
	@XStreamAlias("insurermobile")
	private String insurerMobile;
	@XStreamAlias("insurantrelationship")
	private String insurantRelationship;
	@XStreamAlias("insureremail")
	private String insurerEmail;
    @XStreamAlias("englishname")
    private String englishName;
    @XStreamAlias("phoneticizelastname")
    private String phoneticizeLastname;
    @XStreamAlias("phoneticizefirstname")
    private String phoneticizeFirstname;
	
    public String getInsurerName() {
        return insurerName;
    }
    public void setInsurerName(String insurerName) {
        this.insurerName = insurerName;
    }
    public String getInsurerGender() {
        return insurerGender;
    }
    public void setInsurerGender(String insurerGender) {
        this.insurerGender = insurerGender;
    }
    public String getInsurerBirthday() {
        return insurerBirthday;
    }
    public void setInsurerBirthday(String insurerBirthday) {
        this.insurerBirthday = insurerBirthday;
    }
   
    public String getInsurerIdType() {
        return insurerIdType;
    }
    public void setInsurerIdType(String insurerIdType) {
        this.insurerIdType = insurerIdType;
    }
    public String getInsurerIdNo() {
        return insurerIdNo;
    }
    public void setInsurerIdNo(String insurerIdNo) {
        this.insurerIdNo = insurerIdNo;
    }
    public String getInsurerMobile() {
        return insurerMobile;
    }
    public void setInsurerMobile(String insurerMobile) {
        this.insurerMobile = insurerMobile;
    }
    public String getInsurantRelationship() {
        return insurantRelationship;
    }
    public void setInsurantRelationship(String insurantRelationship) {
        this.insurantRelationship = insurantRelationship;
    }
    public String getInsurerEmail() {
        return insurerEmail;
    }
    public void setInsurerEmail(String insurerEmail) {
        this.insurerEmail = insurerEmail;
    }
    public String getEnglishName() {
        return englishName;
    }
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
    public String getPhoneticizeLastname() {
        return phoneticizeLastname;
    }
    public void setPhoneticizeLastname(String phoneticizeLastname) {
        this.phoneticizeLastname = phoneticizeLastname;
    }
    public String getPhoneticizeFirstname() {
        return phoneticizeFirstname;
    }
    public void setPhoneticizeFirstname(String phoneticizeFirstname) {
        this.phoneticizeFirstname = phoneticizeFirstname;
    }
}

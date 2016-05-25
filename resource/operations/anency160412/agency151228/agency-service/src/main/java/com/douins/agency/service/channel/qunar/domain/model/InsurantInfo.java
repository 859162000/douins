package com.douins.agency.service.channel.qunar.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class InsurantInfo {
    @XStreamAlias("insurantname")
	private String insurantName;
    @XStreamAlias("insurantgender")
	private String insurantGender;
    @XStreamAlias("insurantbirthday")
	private String insurantBirthday;
    @XStreamAlias("insurantidtype")
	private String insurantIdType;
    @XStreamAlias("insurantidno")
	private String insurantIdNo;
    @XStreamAlias("insurantmobile")
	private String insurantMobile;
    @XStreamAlias("insurerrelationship")
	private String insurerRelationship;
    @XStreamAlias("englishname")
    private String englishName;
    @XStreamAlias("phoneticizelastname")
    private String phoneticizeLastname;
    @XStreamAlias("phoneticizefirstname")
    private String phoneticizeFirstname;
    
    public String getInsurantName() {
        return insurantName;
    }
    public void setInsurantName(String insurantName) {
        this.insurantName = insurantName;
    }
    public String getInsurantGender() {
        return insurantGender;
    }
    public void setInsurantGender(String insurantGender) {
        this.insurantGender = insurantGender;
    }
    public String getInsurantBirthday() {
        return insurantBirthday;
    }
    public void setInsurantBirthday(String insurantBirthday) {
        this.insurantBirthday = insurantBirthday;
    }
  
    public String getInsurantIdType() {
        return insurantIdType;
    }
    public void setInsurantIdType(String insurantIdType) {
        this.insurantIdType = insurantIdType;
    }
    public String getInsurantIdNo() {
        return insurantIdNo;
    }
    public void setInsurantIdNo(String insurantIdNo) {
        this.insurantIdNo = insurantIdNo;
    }
    public String getInsurantMobile() {
        return insurantMobile;
    }
    public void setInsurantMobile(String insurantMobile) {
        this.insurantMobile = insurantMobile;
    }
    public String getInsurerRelationship() {
        return insurerRelationship;
    }
    public void setInsurerRelationship(String insurerRelationship) {
        this.insurerRelationship = insurerRelationship;
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

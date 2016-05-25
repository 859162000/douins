package com.douins.agency.service.insurance.ccic.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Description: 投保人信息
 * @author hou
 * @date 2015年12月28日
 */
@XStreamAlias("reapplicant")
public class Applicant {
	/**
	 * 投保人姓名
	 */
	@XStreamAlias("appliname")
	private String appliName;
	/**
	 * 投保人证件号码
	 */
	@XStreamAlias("identifynumber")
	private String identifyNumber;
	/**
	 * 投保人证件类型
	 */
	@XStreamAlias("identifytype")
	private String identifyType;
	/**
	 * 投保人证件有效期
	 */
	@XStreamAlias("identifyperiod")
	private String identifyPeriod;
	/**
	 * 投保人出生日期	
	 */
	@XStreamAlias("birthday")
	private String birthDay;
	/**
	 * 投投保人年龄
	 */
	@XStreamAlias("age")
	private String age;
	/**
	 * 投保人性别
	 */
	@XStreamAlias("sex")
	private String sex;
	/**
	 * 投保人与被保险人关系
	 */
	@XStreamAlias("insuredidentity")
	private String insuredIdentity;
	/**
	 * 投保人住址
	 */
	@XStreamAlias("appliaddress")
	private String appliAddress;
	/**
	 * 投保人邮编
	 */
	@XStreamAlias("postcode")
	private String postCode;
	/**
	 * 投保人联系电话
	 */
	@XStreamAlias("phonenumber")
	private String phoneNumber;
	/**
	 * 投保人手机
	 */
	@XStreamAlias("mobile")
	private String mobile;
	/**
	 * 投保人邮箱
	 */
	@XStreamAlias("email")
	private String email;
	/**
	 * 银行账户
	 */
	@XStreamAlias("account")
	private String account;
	/**
	 * 银行账号	
	 */
	@XStreamAlias("accountno")
	private String accountNo;
	/**
	 * 开户行
	 */
	@XStreamAlias("bank")
	private String bank;
	
	
	public String getAppliName() {
		return appliName;
	}
	public void setAppliName(String appliName) {
		this.appliName = appliName;
	}
	public String getIdentifyNumber() {
		return identifyNumber;
	}
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}
	public String getIdentifyType() {
		return identifyType;
	}
	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}
	public String getIdentifyPeriod() {
		return identifyPeriod;
	}
	public void setIdentifyPeriod(String identifyPeriod) {
		this.identifyPeriod = identifyPeriod;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getInsuredIdentity() {
		return insuredIdentity;
	}
	public void setInsuredIdentity(String insuredIdentity) {
		this.insuredIdentity = insuredIdentity;
	}
	public String getAppliAddress() {
		return appliAddress;
	}
	public void setAppliAddress(String appliAddress) {
		this.appliAddress = appliAddress;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	
	

}

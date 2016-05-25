package com.douins.agency.service.insurance.ccic.domain.model;
/**
 * @Description: ccic 核保接口中main
 * @author hou
 * @date 2015年12月31日
 */
public class UWmain {
	/*
	 * 险种代码
	 */
	private String riskcode;
	/*
	 * Type业务类型
	 */
	private String businessType;
	/*
	 * 起保日期
	 */
	private String startDate;
	/*
	 * 起保小时
	 */
	private String startHour;
	/*
	 * 终保日期
	 */
	private String endDate;
	/*
	 * 终保小时
	 */
	private String endHour;
	/*
	 * 投保份数
	 */
	private String sumQuantity;
	/*
	 * 总保额
	 */
	private String sumAmount;
	/*
	 * 总保费
	 */
	private String sumPremium;
	
	
	public String getRiskcode() {
		return riskcode;
	}
	public void setRiskcode(String riskcode) {
		this.riskcode = riskcode;
	}
	public String getBusiness() {
		return businessType;
	}
	public void setBusiness(String businessType) {
		this.businessType = businessType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getSumQuantity() {
		return sumQuantity;
	}
	public void setSumQuantity(String sumQuantity) {
		this.sumQuantity = sumQuantity;
	}
	public String getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}
	public String getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(String sumPremium) {
		this.sumPremium = sumPremium;
	}
	
}

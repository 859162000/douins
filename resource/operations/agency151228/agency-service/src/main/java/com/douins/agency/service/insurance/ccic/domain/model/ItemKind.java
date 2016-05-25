package com.douins.agency.service.insurance.ccic.domain.model;

import com.douins.agency.service.douins.domain.vo.ProductVo;

/**
 * @Description: ccic 核保接口中ItemKind
 * @author hou
 * @date 2015年12月31日
 */

public class ItemKind {
	/*
	 * 条款代码
	 */
	private String clauseCode;
	/*
	 * 责任代码
	 */
	private String kindCode;
	/*
	 * 保额
	 */
	private String amount;
	/*
	 * 保费
	 */
	private String premium;
	/*
	 * 每日补贴金额（对于补贴类产品方案必传，非补贴类产品可不传
	 */
	private String dayAmount;
	/*
	 * 补贴天数（对于补贴类产品方案必传，非补贴类产品可不传）
	 */
	private String subDay;
	
	public ItemKind(){}
	
	public ItemKind(ProductVo product){
	    this.clauseCode = product.getClauseCode();
	    this.kindCode = product.getKindCode();
	    this.amount = product.getAmount();
	    this.premium = product.getPremium();
	}
	
	public String getClauseCode() {
		return clauseCode;
	}
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	public String getKindCode() {
		return kindCode;
	}
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getDayAmount() {
		return dayAmount;
	}
	public void setDayAmount(String dayAmount) {
		this.dayAmount = dayAmount;
	}
	public String getSubDay() {
		return subDay;
	}
	public void setSubDay(String subDay) {
		this.subDay = subDay;
	}
	
}

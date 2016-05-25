package com.mango.fortune.product.enums;

/**
 * 还款人类型
 * @author winterchen
 *
 */
public enum TypeOfRepayMent {
	personal("1","借款人还款"),
	enterprise("2","担保人还款");	
	private String code;
	private String desc;
	TypeOfRepayMent(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}

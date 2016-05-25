package com.mango.fortune.product.enums;

/**
 * 出借日类型
 * @author winterchen
 *
 */
public enum LenderType {
	R1("0","到账日期下一个工作日"),
	R2("1","募集期结束下一个工作日");
	
	private LenderType(String value,  String desc) {
		this.value = value;
		this.desc = desc;
	}
	private String value;
	private String desc;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}

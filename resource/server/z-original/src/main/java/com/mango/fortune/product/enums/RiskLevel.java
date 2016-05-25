package com.mango.fortune.product.enums;

/**
 * 
 * 风险等级枚举类
 * @author raowei
 * @version 1.0 Jun 25, 2014
 *
 */
public enum RiskLevel {
	R1(1,"R1","保本")
	;
	
	private RiskLevel(int value, String levelCode, String desc) {
		this.value = value;
		this.levelCode = levelCode;
		this.desc = desc;
	}
	private int value;
	private String levelCode;
	private String desc;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

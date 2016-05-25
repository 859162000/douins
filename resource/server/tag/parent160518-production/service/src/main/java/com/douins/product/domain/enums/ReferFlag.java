package com.douins.product.domain.enums;

/*
 * 产品推荐
 */
public enum ReferFlag {
	OFF("0",""),
	ON("1","推荐"),;
	
	private ReferFlag(String value,  String desc) {
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

package com.douins.product.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum SaleChannel {
	ALL("0","全渠道"),
	IOS("1","IOS"),
	OFFLINE("90","线下");
	
	private SaleChannel(String value,  String desc) {
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
	public SaleChannel  getByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(SaleChannel type : SaleChannel.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}
	
	
}

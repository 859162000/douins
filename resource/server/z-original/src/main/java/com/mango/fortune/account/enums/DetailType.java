package com.mango.fortune.account.enums;

import org.apache.commons.lang.StringUtils;

public enum DetailType {
	RECHARGE("1", "充值"),
	WITHDRAW("2", "提现"),
	BUYPOLICY("3", "购买保单"),
	POLICYLOAN("4", "保单借款打款"),
	POLICYREPAY("5", "保单还款"),
	POLICYSURRENDER("6","退保")
	;

	private String value;
	private String name;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private DetailType(String value, String name) {
		this.value = value;
		this.name = name;

	}
	
	public static  String getValueOf(String name) {
		if (StringUtils.isNotBlank(name)) {
			for(DetailType type : DetailType.values()) {
				if (type.getName().equals(name)) {
					return type.getValue();
				}
			}
		}
		return "";
	}
	
	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(DetailType type : DetailType.values()) {
				if (type.getValue().equals(value)) {
					return type.getName();
				}
			}
		}
		return "";
	}
	
	public static DetailType getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(DetailType type : DetailType.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

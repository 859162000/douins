package com.douins.account.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum DetailIo {
	IN("1", "入账"),
	OUT("-1", "出账");

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

	private DetailIo(String value, String name) {
		this.value = value;
		this.name = name;

	}
	
	public static  String getValueOf(String name) {
		if (StringUtils.isNotBlank(name)) {
			for(DetailIo type : DetailIo.values()) {
				if (type.getName().equals(name)) {
					return type.getValue();
				}
			}
		}
		return "";
	}
	
	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(DetailIo type : DetailIo.values()) {
				if (type.getValue().equals(value)) {
					return type.getName();
				}
			}
		}
		return "";
	}
	
	public static DetailIo getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(DetailIo type : DetailIo.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

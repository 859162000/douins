package com.douins.policy.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum ConfigType {
	UW("01","核保"),
	INSURE("02","承保")
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
	private ConfigType(String value, String name) {
		this.value = value;
		this.name = name;

	}

	public static String getValueOf(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (ConfigType type : ConfigType.values()) {
				if (type.getName().equals(name)) {
					return type.getValue();
				}
			}
		}
		return "";
	}

	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (ConfigType type : ConfigType.values()) {
				if (type.getValue().equals(value)) {
					return type.getName();
				}
			}
		}
		return "";
	}

	public static ConfigType getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (ConfigType type : ConfigType.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

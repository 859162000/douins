package com.douins.policy.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum RelationType {
	MYSELF("1","本人"),
	PARENT("2","父母"),
	CHILDREN("3","子女"),
	PARTNER("4","配偶")
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
	private RelationType(String value, String name) {
		this.value = value;
		this.name = name;

	}

	public static String getValueOf(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (RelationType type : RelationType.values()) {
				if (type.getName().equals(name)) {
					return type.getValue();
				}
			}
		}
		return "";
	}

	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (RelationType type : RelationType.values()) {
				if (type.getValue().equals(value)) {
					return type.getName();
				}
			}
		}
		return "";
	}

	public static RelationType getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (RelationType type : RelationType.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

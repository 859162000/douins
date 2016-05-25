package com.mango.fortune.product.enums;

import org.apache.commons.lang.StringUtils;

public enum PeriodType {
	IRRELEVANT("0","无关"),
	DAY("1", "天"), 
	MONTH("2", "月"), 
	YEAR("3", "年"),
	AGE("4","保至某确定年龄"),
	LIFE("5","保终身");

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

	private PeriodType(String value, String name) {
		this.value = value;
		this.name = name;

	}

	public static String getValueOf(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (PeriodType type : PeriodType.values()) {
				if (type.getName().equals(name)) {
					return type.getValue();
				}
			}
		}
		return "";
	}

	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (PeriodType type : PeriodType.values()) {
				if (type.getValue().equals(value)) {
					return type.getName();
				}
			}
		}
		return "";
	}

	public static PeriodType getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (PeriodType type : PeriodType.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

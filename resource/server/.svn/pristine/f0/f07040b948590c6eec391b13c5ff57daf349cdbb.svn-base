package com.douins.account.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum OpenStatus {
	INIT("0", "待开户"),
	SUCCESS("1", "开户成功"),
	FAILURE("2", "开户失败");

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

	private OpenStatus(String value, String desc) {
		this.value = value;
		this.desc = desc;

	}
	
	public static  String getValueOf(String desc) {
		if (StringUtils.isNotBlank(desc)) {
			for(OpenStatus type : OpenStatus.values()) {
				if (type.getDesc().equals(desc)) {
					return type.getValue();
				}
			}
		}
		return "";
	}
	
	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(OpenStatus type : OpenStatus.values()) {
				if (type.getValue().equals(value)) {
					return type.getDesc();
				}
			}
		}
		return "";
	}
	
	public static OpenStatus getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(OpenStatus type : OpenStatus.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

package com.mango.fortune.pay.enums;

import org.apache.commons.lang.StringUtils;

public enum PayStatus {
	NOTTOPAY("0", "未支付"), 
	SUCCESS("1", "支付成功"), 
	FAILURE("2", "支付失败"), 
	PROCESS("3", "支付处理中");

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

	private PayStatus(String value, String desc) {
		this.value = value;
		this.desc = desc;

	}

	public static String getValueOf(String desc) {
		if (StringUtils.isNotBlank(desc)) {
			for (PayStatus type : PayStatus.values()) {
				if (type.getDesc().equals(desc)) {
					return type.getValue();
				}
			}
		}
		return "";
	}

	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (PayStatus type : PayStatus.values()) {
				if (type.getValue().equals(value)) {
					return type.getDesc();
				}
			}
		}
		return "";
	}

	public static PayStatus getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (PayStatus type : PayStatus.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

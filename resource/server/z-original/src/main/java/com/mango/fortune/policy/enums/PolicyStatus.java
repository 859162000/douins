package com.mango.fortune.policy.enums;

import org.apache.commons.lang.StringUtils;

public enum PolicyStatus {
	UWINIT("0", "待处理/待核保"),
	UWSUCCESS("1", "核保通过/待支付"),
	UWFAILED("2", "核保不通过"),
	PAYFAILED("3","支付失败"),
	PAYSUCCESS("4","支付成功/待承保"),
	INSUREFAILED("5","承保失败"),
	INSURESUCCESS("6","有效"),
	LOANING("7","已贷款"),
	INVALIDATE("8","终止")
	;

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

	private PolicyStatus(String value, String desc) {
		this.value = value;
		this.desc = desc;

	}

	public static String getValueOf(String desc) {
		if (StringUtils.isNotBlank(desc)) {
			for (PolicyStatus type : PolicyStatus.values()) {
				if (type.getDesc().equals(desc)) {
					return type.getValue();
				}
			}
		}
		return "";
	}

	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (PolicyStatus type : PolicyStatus.values()) {
				if (type.getValue().equals(value)) {
					return type.getDesc();
				}
			}
		}
		return "";
	}

	public static PolicyStatus getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (PolicyStatus type : PolicyStatus.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

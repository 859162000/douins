package com.douins.policy.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum PolicyStatus {
	UWINIT("0", "待处理/待核保"),
	UWSUCCESS("1", "核保通过/待支付"),
	UWFAILED("2", "核保不通过"),
	UW2SUCCESS("3", "二核通过"),
	UW2SFAILED("4", "二核失败"),
	PAYFAILED("5","支付失败"),
	PAYSUCCESS("6","支付成功/待承保"),
	INSUREFAILED("7","承保失败"),
	INSURESUCCESS("8","有效"),
	LOANING("9","已贷款"),
	REPAYED("10", "已还款"),
	DRAW_BACK("11","退保处理中"),
	DRAW_BACK_FINISH("12","退保成功"),
	DRAW_BACK_FIAILED("13","退保失败"),
	INVALIDATE("20","终止")
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

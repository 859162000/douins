package com.douins.account.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum UserStatus {
	INIT("0", "未认证"),
	VERIFY_FAILD("1", "认证未通过"),
	VERIFY_SUCCESS("2", "认证通过"),
	FAILURE("3", "开户失败"),
	SUCCESS("4", "开户成功"),
    CANCEL("5", "已注销"),
    UNBIND("6","未绑卡"),
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

	private UserStatus(String value, String desc) {
		this.value = value;
		this.desc = desc;

	}
	
	public static  String getValueOf(String desc) {
		if (StringUtils.isNotBlank(desc)) {
			for(UserStatus type : UserStatus.values()) {
				if (type.getDesc().equals(desc)) {
					return type.getValue();
				}
			}
		}
		return "";
	}
	
	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(UserStatus type : UserStatus.values()) {
				if (type.getValue().equals(value)) {
					return type.getDesc();
				}
			}
		}
		return "";
	}
	
	public static UserStatus getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(UserStatus type : UserStatus.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

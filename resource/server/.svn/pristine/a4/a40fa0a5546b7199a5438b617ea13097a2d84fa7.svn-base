package com.mango.fortune.user.enums;

public enum ValidateType {

	SMS("1", "短信验证");

	private String code;
	private String name;

	private ValidateType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getValueByName(String name) {
		for (ValidateType type : ValidateType.values()) {
			if (type.getName().equals(name))
				return type.getCode();
		}
		return "";
	}

	public static String getNameByValue(String code) {
		for (ValidateType type : ValidateType.values()) {
			if (type.getCode().equals(code))
				return type.getName();
		}
		return "";
	}

}

package com.douins.bank.domain.enums.gfbc;

import com.douins.account.domain.enums.AccountType;

public enum GFcertiType {
	ID("01","0","身份证");
	
	private String code;//银行编码
	private String value;//本系统编码
	private String name;//名称
	
	
	private GFcertiType(String value,String name,String code){
		this.value = value;
		this.name = name;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public static String getValueByName(String name){
		for(AccountType type: AccountType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(Integer value){
		for(AccountType type: AccountType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	public static String getValueByCode(String code){
		for(AccountType type: AccountType.values()){
			if(type.getName().equals(code))
				return type.getValue();
		}
		return "";
	}
}

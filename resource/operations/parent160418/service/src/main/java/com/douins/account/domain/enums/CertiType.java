package com.douins.account.domain.enums;

public enum CertiType {
	ID("110","身份证"),
	Passport("2","护照"),
	Military("3","军官证"),
	Other("4","其它");
	
	
	private String value;
	private String name;
	
	private CertiType(String value,String name){
		this.value = value;
		this.name = name;
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
}

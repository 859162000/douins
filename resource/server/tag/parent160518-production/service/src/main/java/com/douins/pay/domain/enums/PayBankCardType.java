package com.douins.pay.domain.enums;

public enum PayBankCardType {
	BANKCARD("1","银行卡 "),	
	CREDITCARD("2","信用卡");

	private String value;
	private String name;

	
	private PayBankCardType(String value,String name){
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
		for(PayBankCardType type: PayBankCardType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(Integer value){
		for(PayBankCardType type: PayBankCardType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	
	
	
}

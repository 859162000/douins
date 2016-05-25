package com.douins.trans.domain.enums;

public enum BusinessTransType {
	pay_request("1","支付请求"),
	pay_response("2","支付返回"),
	binding_card_validate("3","绑卡验证短信发送"),
	binding_card_validate_code("4","验证验证码"),
	open_request("1","支付请求"),
	open_response("2","支付返回");
	private String value;
	private String name;

	private BusinessTransType(String value,String name){
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
		for(BusinessTransType type: BusinessTransType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(Integer value){
		for(BusinessTransType type: BusinessTransType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
}

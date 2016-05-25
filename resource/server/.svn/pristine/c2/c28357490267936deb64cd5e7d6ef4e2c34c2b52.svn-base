package com.mango.fortune.trans.enums;


public enum BusinessTransStatus {
	INIT("0","申请请求"),
	SUCCESS("1","请求成功"),
	FAILURE("2","失败");;
	
	private String value;
	private String name;
	
	private BusinessTransStatus(String value,String name){
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
		for(BusinessTransStatus type: BusinessTransStatus.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(String value){
		for(BusinessTransStatus type: BusinessTransStatus.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	
}

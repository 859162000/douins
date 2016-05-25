package com.mango.fortune.trans.enums;


public enum TransChannel {
	
	IOS("01","IOS APP"),
	AND("02","android"),
	WX("03","微信"),
	NET("04","官网");
	
	private String value;
	private String name;
	
	private TransChannel(String value,String name){
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
		for(TransChannel type: TransChannel.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(String value){
		for(TransChannel type: TransChannel.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	
}

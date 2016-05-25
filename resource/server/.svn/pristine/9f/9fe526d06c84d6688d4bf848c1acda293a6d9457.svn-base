package com.mango.fortune.apply.enums;

public enum RepayStatus {
	REPAYING("1","还款申请"),
	REPAYFAILED("2","还款失败"),
	REPAYPAYMENT("3","还款打款中"),
	REPAYEND("4","还款完成")
	;
	
	private String value;
	private String name;
	
	private RepayStatus(String value,String name){
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
	
	public static String getNameByValue(String value){
		for(RepayStatus type: RepayStatus.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}

	public static String getValueByName(String name){
		for(RepayStatus type: RepayStatus.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
}

package com.mango.fortune.apply.enums;

public enum SurrenderStatus {
	SURRENDERING("1","还款申请"),
	SURRENDERFAILED("2","还款失败"),
	SURRENDERPAYMENT("3","还款打款中"),
	SURRENDEREND("4","还款完成")
	;
	
	private String value;
	private String name;
	
	private SurrenderStatus(String value,String name){
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
		for(SurrenderStatus type: SurrenderStatus.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}

	public static String getValueByName(String name){
		for(SurrenderStatus type: SurrenderStatus.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
}

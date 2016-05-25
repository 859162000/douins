package com.mango.fortune.apply.enums;

public enum ApplyStatus {
	APPLYING("1","正在申请"),
	APPLYEND("2","完成");
	
	private String value;
	private String name;
	
	private ApplyStatus(String value,String name){
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
		for(ApplyStatus type: ApplyStatus.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}

	public static String getValueByName(String name){
		for(ApplyStatus type: ApplyStatus.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
}

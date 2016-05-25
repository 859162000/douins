package com.douins.apply.domain.enums;

public enum SurrenderType {
	SURRENDERNORMAL("1","退保")
	
	;
	
	private String value;
	private String name;
	
	private SurrenderType(String value,String name){
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
		for(SurrenderType type: SurrenderType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}

	public static String getValueByName(String name){
		for(SurrenderType type: SurrenderType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
}

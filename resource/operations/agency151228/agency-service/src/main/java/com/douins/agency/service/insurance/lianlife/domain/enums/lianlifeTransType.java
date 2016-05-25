package com.douins.agency.service.insurance.lianlife.domain.enums;

public enum lianlifeTransType {
	UW("10002","核保"),
	INSURE("20002","承保"),
	WITHDRAW("30001","退保"),
	NOTICFUND("50001","资金划拨通知");
	
    private String value;
    private String name;
    
    private lianlifeTransType(String value,String name){
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
    	for(lianlifeTransType type:lianlifeTransType.values()){
            if(type.getName().equals(name))
                return type.getValue();
    	}
    	return "";
    }
    
    public static String getNameByValue(Integer value){
    	for(lianlifeTransType type:lianlifeTransType.values()){
    		if(type.getValue().equals(value))
    			return type.getName();
    	}
    	return "";
    }
}

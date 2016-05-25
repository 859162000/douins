package com.douins.agency.service.channel.qunarfinance.domain.emnus;

public enum QunarfnTransType {
	UW("10002","核保"),
	INSURE("20002","承保"),
	WITHDRAW("30001","退保"),
	NOTICFUND("50001","资金划拨通知");
	
    private String value;
    private String name;
    
    private QunarfnTransType(String value,String name){
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
    	for(QunarfnTransType type:QunarfnTransType.values()){
            if(type.getName().equals(name))
                return type.getValue();
    	}
    	return "";
    }
    
    public static String getNameByValue(Integer value){
    	for(QunarfnTransType type:QunarfnTransType.values()){
    		if(type.getValue().equals(value))
    			return type.getName();
    	}
    	return "";
    }
}

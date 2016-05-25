package com.mango.fortune.pay.enums;
/**
 * 描述：支付类型<br>
 * 作者：wintrchen <br>
 * 修改日期：May 15, 20152:43:19 PM <br>
 * E-mail: winterchen@sinorfc.com <br>
 */
public enum PayType {
	ONLINEBANK("1","网银"),
	WITHHOLD("2","代扣"),
	TRANSFERACCOUNT("3","转账"),
	FASTPAY("4","快捷支付");
	private String value;
	private String name;

	
	private PayType(String value,String name){
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
		for(PayType type: PayType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(Integer value){
		for(PayType type: PayType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	
}

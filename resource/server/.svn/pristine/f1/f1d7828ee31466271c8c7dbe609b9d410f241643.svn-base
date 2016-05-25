package com.mango.fortune.pay.enums;
/**
 * 描述：第三方<br>
 * 作者：wintrchen <br>
 * 修改日期：May 15, 20152:43:46 PM <br>
 * E-mail: winterchen@sinorfc.com <br>
 */
public enum ThirdpayType {
	//WECHAT("4","微信"),
	SHANGHAIUNIONPAY("3","上银联","shanghaiPayService"),
	CHINAPAYMENT("6","中金支付","chinaPaymentService");
	private String value;
	private String name;
	private String service;

	
	private ThirdpayType(String value,String name,String service){
		this.value = value;
		this.name = name;
		this.service = service;
	
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

	

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public static String getValueByName(String name){
		for(ThirdpayType type: ThirdpayType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	
	
	public static String getNameByValue(Integer value){
		for(ThirdpayType type: ThirdpayType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	
	public static String getServiceByValue(Integer value){
		for(ThirdpayType type: ThirdpayType.values()){
			if(type.getValue().equals(value))
				return type.getService();
		}
		return "";
	}
}

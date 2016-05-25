package com.douins;

import com.douins.account.domain.enums.AccountType;

public enum InterfaceType {

	TialMotor("0010","试算承保"),
	Tial("0011","试算"),
	Motor("0012","承保"),
	Backout("0013","撤销"),
	Reverse("0014","冲正"),
	CollectMarkingInformation("0015","收集批改信息"),
	Surrender("0016","退保"),
	CorrectInformation("0017","人员基本信息批改"),
	Query("0018","查询（重打）"),
	BusinessReconciliation("0019","业务对账"),
	PolicyEffectiveNotification("0020","保单生效通知接口"),
	OrderInterface("0021","下单接口");
	
	
	private String value;
	private String name;
	
	private InterfaceType(String value,String name){
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
		for(InterfaceType type: InterfaceType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(Integer value){
		for(InterfaceType type: InterfaceType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}


}

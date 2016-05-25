package com.douins.product.domain.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 投资人收益支付方式枚举类 
 * @author raowei
 * @version 1.0 Jun 25, 2014
 *
 */
public enum PaymentMethod {
	
	EVEN_PRINCIPAL_INTEREST("1","等额本息"),
	ONCE_PAID("2","一次性还本付息"),
	SEVERAL_PAYMENT("3","每月派息");
	
	
	private String value;
	private String desc;

	private PaymentMethod(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static  String getValueOf(String desc) {
		if (StringUtils.isNotBlank(desc)) {
			for(PaymentMethod type : PaymentMethod.values()) {
				if (type.getDesc().equals(desc)) {
					return type.getValue();
				}
			}
		}
		return "";
	}
	
	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(PaymentMethod type : PaymentMethod.values()) {
				if (type.getValue().equals(value)) {
					return type.getDesc();
				}
			}
		}
		return "";
	}
}

/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.mango.fortune.product.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年5月26日 上午10:25:21 
 * 分次付息利率算法标志位  
 */
public enum RateCalType {
	AVERAGE("1", "平均"), REDUCE("2", "年金终值");

	private String value;
	private String desc;

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

	private RateCalType(String value, String desc) {
		this.value = value;
		this.desc = desc;

	}
	
	public static  String getValueOf(String desc) {
		if (StringUtils.isNotBlank(desc)) {
			for(RateCalType type : RateCalType.values()) {
				if (type.getDesc().equals(desc)) {
					return type.getValue();
				}
			}
		}
		return "";
	}
	
	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(RateCalType type : RateCalType.values()) {
				if (type.getValue().equals(value)) {
					return type.getDesc();
				}
			}
		}
		return "";
	}
	
	public static RateCalType getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(RateCalType type : RateCalType.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}

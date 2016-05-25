package com.mango.fortune.product.enums;

import java.math.BigDecimal;

public enum RateConvertStrategy {
	DAY365(new BigDecimal(365),"365天"),
	DAY360(new BigDecimal(360),"360天"),
	MONTH(new BigDecimal(12),"月");
	
	private BigDecimal value;
	private String desc;
	
	public final BigDecimal getValue() {
		return value;
	}
	public final void setValue(BigDecimal value) {
		this.value = value;
	}
	public final String getDesc() {
		return desc;
	}
	public final void setDesc(String desc) {
		this.desc = desc;
	}
	
	private  RateConvertStrategy(BigDecimal value,String desc) {
		this.value = value;
		this.desc = desc;
	}
	
	
}

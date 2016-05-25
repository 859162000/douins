package com.douins.product.domain.enums;


/**
 * 
 *
 *
 */
public enum ProductType {
	UNIVERSAL("万能险",1);
	private int value;
	private String desc;
	
	
	private ProductType(String desc, int value) {
		this.value = value;
		this.desc = desc;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static String getDescByValue(int value) {
		for(ProductType type : ProductType.values()) {
			if (type.getValue()==value) {
				return type.getDesc();			
			}
		}
		return "";
	}
	
	
}

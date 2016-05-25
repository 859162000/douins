package com.douins.product.domain.enums;

/**
 * 产品状态
 * @author winterchen
 *
 */
public enum ProductStatus {
	RaisePreparation("0","准备中"),
	RaiseConcentration("1","募集中"),
	RaiseComplete("2","募集完成"),
	RaiseFailure("3","募集失败");
	private String code;
	private String desc;
	ProductStatus(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

package com.douins.agency.service.channel.qunar.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ProductInfo {
    @XStreamAlias("productno")
	private String productNo;
    @XStreamAlias("unit")
	private String unit;
    @XStreamAlias("effdate")
	private String effDate;
    @XStreamAlias("matudate")
	private String matuDate;
	
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getMatuDate() {
		return matuDate;
	}
	public void setMatuDate(String matuDate) {
		this.matuDate = matuDate;
	}
	
}

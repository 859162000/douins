package com.douins.agency.service.douins.domain.model;

import com.douins.agency.service.BaseModel;

public class ProductFact extends BaseModel {
	
	private String productId;    //豆芽内部产品id
	private String insureCode;   //保险公司代码
	private String channlCode;   //销售渠道方代码
	private String insurePdId;   //保险公司的产品id
	private String channlPdId;   //销售渠道方的产品id
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getInsureCode() {
		return insureCode;
	}
	public void setInsureCode(String insureCode) {
		this.insureCode = insureCode;
	}
	public String getChannlCode() {
		return channlCode;
	}
	public void setChannlCode(String channlCode) {
		this.channlCode = channlCode;
	}
	public String getInsurePdId() {
		return insurePdId;
	}
	public void setInsurePdId(String insurePdId) {
		this.insurePdId = insurePdId;
	}
	public String getChannlPdId() {
		return channlPdId;
	}
	public void setChannlPdId(String channlPdId) {
		this.channlPdId = channlPdId;
	}
	
}

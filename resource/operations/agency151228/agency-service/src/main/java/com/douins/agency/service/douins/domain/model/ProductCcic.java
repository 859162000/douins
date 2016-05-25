package com.douins.agency.service.douins.domain.model;

import com.douins.agency.service.BaseModel;

public class ProductCcic extends BaseModel {

	   private String productId;     //豆芽内部产品id
	   private String insureCode;    //保险公司代码
	   private String insurePdId;    //保险公司的产品id
	   private String saleStrategy;  //保险产品销售策略
	   private String clauseCode;    //条款代码
	   private String kindCode;      //责任代码
	   private String amount;        //保额，仅存数值
	   
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
	public String getInsurePdId() {
		return insurePdId;
	}
	public void setInsurePdId(String insurePdId) {
		this.insurePdId = insurePdId;
	}
	public String getSaleStrategy() {
		return saleStrategy;
	}
	public void setSaleStrategy(String saleStrategy) {
		this.saleStrategy = saleStrategy;
	}
	public String getClauseCode() {
		return clauseCode;
	}
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	public String getKindCode() {
		return kindCode;
	}
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	   
}

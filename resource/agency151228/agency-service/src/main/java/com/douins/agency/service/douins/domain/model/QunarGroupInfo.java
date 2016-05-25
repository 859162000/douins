package com.douins.agency.service.douins.domain.model;

import com.douins.agency.service.BaseModel;

public class QunarGroupInfo extends BaseModel{

	private String headId;           //试算请求落地流水号－豆芽内部用
	private String orderNo;          //去哪儿网订单号
	private String businessId;       //去哪儿交易请求的唯一标记
	private String agencyNo;         //合作方分配给去哪的渠道代码
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getAgencyNo() {
		return agencyNo;
	}
	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}

}

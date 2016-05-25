package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("UnderwritingItem")
public class UnderwritingItem {
	@XStreamAlias("ApplyInfo")
	private ApplyInfo applyinfo;
	@XStreamAlias("OrderInfo")
	private OrderInfo orderinfo;
	@XStreamAlias("OtherInfo")
	private OtherInfo otherinfo;
	
	public ApplyInfo getApplyinfo() {
		return applyinfo;
	}
	public void setApplyinfo(ApplyInfo applyinfo) {
		this.applyinfo = applyinfo;
	}
	public OrderInfo getOrderinfo() {
		return orderinfo;
	}
	public void setOrderinfo(OrderInfo orderinfo) {
		this.orderinfo = orderinfo;
	}
	public OtherInfo getOtherinfo() {
		return otherinfo;
	}
	public void setOtherinfo(OtherInfo otherinfo) {
		this.otherinfo = otherinfo;
	}
	
}

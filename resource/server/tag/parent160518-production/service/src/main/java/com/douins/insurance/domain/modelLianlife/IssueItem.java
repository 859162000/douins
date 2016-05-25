package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class IssueItem {
	@XStreamAlias("ApplyInfo")
	private ApplyInfo applyInfo;
	@XStreamAlias("Payment")
	private Payment payment;
	@XStreamAlias("Flight")
	private Flight flight;
	@XStreamAlias("OrderInfo")
	private OrderInfo orderInfo;
	@XStreamAlias("OtherInfo")
	private OtherInfo otherInfo;
	public ApplyInfo getApplyInfo() {
		return applyInfo;
	}
	public void setApplyInfo(ApplyInfo applyInfo) {
		this.applyInfo = applyInfo;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public OtherInfo getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(OtherInfo otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	

}

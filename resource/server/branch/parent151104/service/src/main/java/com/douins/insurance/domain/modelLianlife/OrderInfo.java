package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("orderinfo")
public class OrderInfo {
	@XStreamAlias("OrderId")
	private String orderid;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
}

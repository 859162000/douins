package com.douins.agency.service.channel.qunarfinance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("orderinfo")
public class OrderInfo {
	private String orderid;
	

	public String getorderid() {
		return orderid;
	}

	public void setorderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderInfo other = (OrderInfo) obj;
		if (orderid == null) {
			if (other.orderid != null)
				return false;
		} else if (!orderid.equals(other.orderid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderid=" + orderid + "]";
	}
	

}

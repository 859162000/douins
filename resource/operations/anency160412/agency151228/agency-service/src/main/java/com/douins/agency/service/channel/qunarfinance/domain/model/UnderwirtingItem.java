package com.douins.agency.service.channel.qunarfinance.domain.model;

public class UnderwirtingItem {
 private ApplyInfo applyInfo;
 private OrderInfo orderInfo;
 private OtherInfo otherInfo;
 
 
public ApplyInfo getApplyInfo() {
	return applyInfo;
}
public void setApplyInfo(ApplyInfo applyInfo) {
	this.applyInfo = applyInfo;
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
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((applyInfo == null) ? 0 : applyInfo.hashCode());
	result = prime * result + ((orderInfo == null) ? 0 : orderInfo.hashCode());
	result = prime * result + ((otherInfo == null) ? 0 : otherInfo.hashCode());
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
	UnderwirtingItem other = (UnderwirtingItem) obj;
	if (applyInfo == null) {
		if (other.applyInfo != null)
			return false;
	} else if (!applyInfo.equals(other.applyInfo))
		return false;
	if (orderInfo == null) {
		if (other.orderInfo != null)
			return false;
	} else if (!orderInfo.equals(other.orderInfo))
		return false;
	if (otherInfo == null) {
		if (other.otherInfo != null)
			return false;
	} else if (!otherInfo.equals(other.otherInfo))
		return false;
	return true;
}
@Override
public String toString() {
	return "UnderwirtingItem [applyInfo=" + applyInfo + ", orderInfo="
			+ orderInfo + ", otherInfo=" + otherInfo + "]";
}
 
 
}

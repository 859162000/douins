package com.douins.agency.service.channel.qunarfinance.domain.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("request")
public class Request {
private List<UnderwirtingItem> underwirtingList;
private Payment payment;
private String applyno;
private List<RefundItem> refundlist;


public List<RefundItem> getRefundlist() {
	return refundlist;
}

public void setRefundlist(List<RefundItem> refundlist) {
	this.refundlist = refundlist;
}

public String getApplyno() {
	return applyno;
}

public void setApplyno(String applyno) {
	this.applyno = applyno;
}

public Payment getPayment() {
	return payment;
}

public void setPayment(Payment payment) {
	this.payment = payment;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((underwirtingList == null) ? 0 : underwirtingList.hashCode());
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
	Request other = (Request) obj;
	if (underwirtingList == null) {
		if (other.underwirtingList != null)
			return false;
	} else if (!underwirtingList.equals(other.underwirtingList))
		return false;
	return true;
}

@Override
public String toString() {
	return "Request [underwirtingList=" + underwirtingList + "]";
}

}

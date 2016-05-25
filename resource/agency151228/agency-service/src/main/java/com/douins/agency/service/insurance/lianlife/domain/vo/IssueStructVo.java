package com.douins.agency.service.insurance.lianlife.domain.vo;

import com.douins.agency.service.insurance.lianlife.domain.model.Payment;

public class IssueStructVo {
	private String ApplyNo;
	
	private Payment payment;

	public String getApplyNo() {
		return ApplyNo;
	}

	public void setApplyNo(String applyNo) {
		ApplyNo = applyNo;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}

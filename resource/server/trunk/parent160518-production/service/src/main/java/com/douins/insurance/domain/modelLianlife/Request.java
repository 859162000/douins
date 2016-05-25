package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("request")
public class Request {
	//for under writing
	@XStreamAlias("UnderwirtingList")
	private UnderwritingList underwritingList;
	
	//for insurance
	@XStreamAlias("IssueList")
	private IssueList issueList;
	
	@XStreamAlias("ApplyNo")
	private String applyno;
	@XStreamAlias("Payment")
	private Payment payment;

	
	//for withdraw
	@XStreamAlias("RefundList")
	private RefundList refundList;

	public UnderwritingList getUnderwritingList() {
		return underwritingList;
	}

	public void setUnderwritingList(UnderwritingList underwritingList) {
		this.underwritingList = underwritingList;
	}

	public IssueList getIssueList() {
		return issueList;
	}

	public void setIssueList(IssueList issueList) {
		this.issueList = issueList;
	}

	public RefundList getRefundList() {
		return refundList;
	}

	public void setRefundList(RefundList refundList) {
		this.refundList = refundList;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getApplyno() {
		return applyno;
	}

	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}
	
}

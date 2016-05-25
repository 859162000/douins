package com.mango.fortune.insurance.pflife.vo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("proposal")
public class Proposal {
	private ProposalInfo proposalInfo;
	private PolicyHolder policyHolder;
	private List<Insured> insureds;
	private Beneficiarys beneficiarys;
	private List<Coverage> coverages;
	private PaymentMethod paymentMethod;
	
	public ProposalInfo getProposalInfo() {
		return proposalInfo;
	}
	public void setProposalInfo(ProposalInfo proposalInfo) {
		this.proposalInfo = proposalInfo;
	}
	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}
	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}
	public List<Insured> getInsureds() {
		return insureds;
	}
	public void setInsureds(List<Insured> insureds) {
		this.insureds = insureds;
	}
	public Beneficiarys getBeneficiarys() {
		return beneficiarys;
	}
	public void setBeneficiarys(Beneficiarys beneficiarys) {
		this.beneficiarys = beneficiarys;
	}
	public List<Coverage> getCoverages() {
		return coverages;
	}
	public void setCoverages(List<Coverage> coverages) {
		this.coverages = coverages;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}

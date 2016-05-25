package com.douins.insurance.domain.model;

public class Response {
	private PolicyInfo policyInfo;
	private Policy policy;
	private ProposalResponse proposal;

	public PolicyInfo getPolicyInfo() {
		return policyInfo;
	}
	public void setPolicyInfo(PolicyInfo policyInfo) {
		this.policyInfo = policyInfo;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public ProposalResponse getProposal() {
		return proposal;
	}
	public void setProposal(ProposalResponse proposal) {
		this.proposal = proposal;
	}
}

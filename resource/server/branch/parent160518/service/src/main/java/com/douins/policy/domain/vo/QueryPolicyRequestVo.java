package com.douins.policy.domain.vo;

public class QueryPolicyRequestVo {
	
	private String policyNo;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	

	@Override
	public String toString() {
		return "QueryPolicyRequest [policyNo=" + policyNo + "]";
	}


	

}

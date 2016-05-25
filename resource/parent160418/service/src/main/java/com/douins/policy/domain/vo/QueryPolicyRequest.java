package com.douins.policy.domain.vo;

public class QueryPolicyRequest {
	
	private QueryPolicyRequestVo policyRequestVo;

	public QueryPolicyRequestVo getPolicyRequestVo() {
		return policyRequestVo;
	}

	public void setPolicyRequestVo(QueryPolicyRequestVo policyRequestVo) {
		this.policyRequestVo = policyRequestVo;
	}

	@Override
	public String toString() {
		return "QueryPolicyRequest [policyRequestVo=" + policyRequestVo + "]";
	}
	
	
	

}

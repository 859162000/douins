package com.douins.policy.domain.vo;

import java.util.List;

import com.douins.trans.domain.vo.ResponseTransVo;

public class PolicyResponse extends ResponseTransVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5599713129471857325L;
	private List<PolicyResponseVo> policyList;
	public List<PolicyResponseVo> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<PolicyResponseVo> policyList) {
		this.policyList = policyList;
	}
}

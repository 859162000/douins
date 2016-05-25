package com.mango.api.policyAPI.vo;

import java.util.List;

import com.mango.api.basic.vo.ResponseTransVo;

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

package com.mango.api.policyAPI.vo;

import com.mango.fortune.policy.model.PolicyMaster;

public class PolicyRequestVo extends PolicyMaster{
	private static final long serialVersionUID = 4109614818072013987L;
	private String payPassword;
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

}

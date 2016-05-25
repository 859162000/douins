package com.douins.trans.domain.vo;

import java.io.Serializable;

import org.apache.poi.ss.formula.functions.T;

import com.douins.policy.domain.vo.PolicyResult;
import com.douins.trans.domain.model.ResponseTrans;

public class CanclePolicyResponse extends ResponseTransVo implements Serializable{
	
	private static final long serialVersionUID = -6435165792086530521L;
	private PolicyResult policyResult;
	public PolicyResult getPolicyResult() {
		return policyResult;
	}
	public void setPolicyResult(PolicyResult policyResult) {
		this.policyResult = policyResult;
	}
	
	

	
	

}

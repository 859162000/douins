package com.douins.insurance.service;

import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.trans.domain.vo.CanclePolicyRequestVo;
import com.douins.trans.domain.vo.CanclePolicyResponse;


public interface InsuranceWorkService {
	public PolicyResult doUW(PolicyMaster policyMaster);
	public PolicyResult doInsure(PolicyMaster policyMaster);
	public PolicyResult canclePolicy(CanclePolicyRequestVo canclePolicyRequestVo);
	public PolicyResult canclePolicy2(CanclePolicyRequestVo canclePolicyRequestVo);
}

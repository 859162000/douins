package com.douins.policy.service;

import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequest;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.policy.domain.vo.QueryPolicyRequest;
import com.douins.trans.domain.vo.CanclePolicyRequest;

public interface PolicyManageService {
	
	public PolicyResponse processUnderwrite(PolicyRequest policyRequest);
	public PolicyResponse processInsuredAndPay(PolicyRequest policyRequest);
	public PolicyResult canclePolicy(CanclePolicyRequest canclePolicyRequest);
	/**
	 *
	 * 
	 * @param queryPolicyRequest
	 * @return
	 */
	@Deprecated
	public PolicyResult queryPolicy(QueryPolicyRequest queryPolicyRequest);
	PolicyResponse processInsuredAndPay(PolicyMaster policyMaster, CallBackRequest callBackRequest);
}

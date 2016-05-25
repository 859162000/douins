package com.douins.policy2.service;

import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResult;

public interface PolicyUwService {
	public PolicyResult doThirdUw(PolicyMaster policy);
	public PolicyResult doThirdInsure(PolicyMaster policy);
}
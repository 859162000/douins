package com.douins.policy.service;

import java.util.List;

import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequest;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy.domain.vo.PolicyResponseVo;
import com.mango.orm.DbOperateService;

public interface PolicyMasterService extends DbOperateService<PolicyMaster>{
	public PolicyResponse add2Uw(PolicyRequest policyRequest);
	public PolicyResponse insure2Pay(PolicyRequest policyRequest);
	public List<PolicyMaster> findByCondition(PolicyMaster paramT);
	public List<PolicyResponseVo> findVoByCondition(PolicyRequestVo policyVo);
}
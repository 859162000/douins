package com.mango.fortune.policy.service;

import java.util.List;

import com.mango.api.policyAPI.vo.PolicyRequest;
import com.mango.api.policyAPI.vo.PolicyRequestVo;
import com.mango.api.policyAPI.vo.PolicyResponse;
import com.mango.api.policyAPI.vo.PolicyResponseVo;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.orm.DbOperateService;

public interface PolicyMasterService extends DbOperateService<PolicyMaster>{
	public PolicyResponse add2Uw(PolicyRequest policyRequest);
	public PolicyResponse insure2Pay(PolicyRequest policyRequest);
	public List<PolicyMaster> findByCondition(PolicyMaster paramT);
	public List<PolicyResponseVo> findVoByCondition(PolicyRequestVo policyVo);
}
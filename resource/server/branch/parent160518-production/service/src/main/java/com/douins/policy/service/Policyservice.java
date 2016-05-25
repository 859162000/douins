package com.douins.policy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponseVo;
import com.mango.orm.DbOperateService;

public abstract class Policyservice<T> implements DbOperateService<PolicyMaster>{
	public abstract List<PolicyMaster> findByCondition(PolicyMaster paramT);
	public abstract List<PolicyResponseVo> findVoByCondition(PolicyRequestVo policyVo);
	public abstract List<PolicyResponseVo> findVoByCondition2(PolicyRequestVo paramT);
	public abstract List<PolicyResponseVo> findSuccessVoByCondition(PolicyRequestVo policyVo);
	public abstract PolicyMaster selectByPolicyId(String policyId);
	public abstract PolicyMaster findByChanlFlowNo(String chanlFlowNo);
	public abstract void updateStatus(String policyId,String status);
		
}

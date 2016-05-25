package com.douins.pay.service;

import java.util.List;

import com.douins.apply.domain.vo.ApplyResult;
import com.douins.pay.domain.model.PaymentApply;
import com.douins.policy.domain.model.PolicyMaster;
import com.mango.orm.DbOperateService;

public interface PaymentApplyService  extends DbOperateService<PaymentApply>{
	public List<PaymentApply> findByCondition(PaymentApply paramT);
	public ApplyResult payApply(PolicyMaster policy,String transChannel) throws Exception ;
}
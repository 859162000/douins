package com.mango.fortune.pay.service;

import java.util.List;

import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.pay.model.PaymentApply;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.orm.DbOperateService;

public interface PaymentApplyService  extends DbOperateService<PaymentApply>{
	public List<PaymentApply> findByCondition(PaymentApply paramT);
	public ApplyResult payApply(PolicyMaster policy,String transChannel) throws Exception ;
}
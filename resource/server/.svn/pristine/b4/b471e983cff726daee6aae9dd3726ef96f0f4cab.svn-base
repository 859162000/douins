package com.mango.fortune.account.service;

import java.util.List;

import com.mango.fortune.account.model.UserAccountOpenApply;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.orm.DbOperateService;

public interface UserAccountOpenApplyService extends DbOperateService<UserAccountOpenApply>{
	public List<UserAccountOpenApply> findByCondition(UserAccountOpenApply openApply);
	public ApplyResult openApply(String userAccountId, String userId,String transChannel) throws Exception;
}
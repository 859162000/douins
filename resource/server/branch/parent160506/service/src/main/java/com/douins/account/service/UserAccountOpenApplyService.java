package com.douins.account.service;

import java.util.List;

import com.douins.account.domain.model.UserAccountOpenApply;
import com.douins.apply.domain.vo.ApplyResult;
import com.mango.orm.DbOperateService;

public interface UserAccountOpenApplyService extends DbOperateService<UserAccountOpenApply>{
	public List<UserAccountOpenApply> findByCondition(UserAccountOpenApply openApply);
	public ApplyResult openApply(String userAccountId, String userId,String transChannel) throws Exception;
}
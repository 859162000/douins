package com.douins.account.service;

import java.util.List;

import com.douins.account.domain.model.UserAccountDetail;
import com.douins.apply.domain.vo.ApplyResult;
import com.mango.orm.DbOperateService;

public interface UserAccountDetailService extends DbOperateService<UserAccountDetail>{
	public List<UserAccountDetail> findByCondition(UserAccountDetail paramT);
	public ApplyResult doBusiness(UserAccountDetail detail,String transChannel) throws Exception;
}
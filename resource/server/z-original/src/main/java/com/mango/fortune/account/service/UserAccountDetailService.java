package com.mango.fortune.account.service;

import java.util.List;

import com.mango.fortune.account.model.UserAccountDetail;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.orm.DbOperateService;

public interface UserAccountDetailService extends DbOperateService<UserAccountDetail>{
	public List<UserAccountDetail> findByCondition(UserAccountDetail paramT);
	public ApplyResult doBusiness(UserAccountDetail detail,String transChannel) throws Exception;
}
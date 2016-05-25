package com.douins.account.service;

import java.util.List;

import com.douins.account.domain.model.UserPayAccount;
import com.douins.trans.domain.enums.ResponseCode;
import com.mango.orm.DbOperateService;

public interface UserPayAccountService extends DbOperateService<UserPayAccount> {
	public ResponseCode add4Api(UserPayAccount payAccountVo, String transChannel) throws Exception;
	public ResponseCode change4Api(UserPayAccount payAccountVo) throws Exception;
	public List<UserPayAccount> findByCondition(UserPayAccount payAccountVo);
	public UserPayAccount findOneByUserId(String userId);
	public ResponseCode validate(UserPayAccount userPayAccountVo);
	
	public void createUserPayAccount(UserPayAccount payAccountVo) throws Exception;
}
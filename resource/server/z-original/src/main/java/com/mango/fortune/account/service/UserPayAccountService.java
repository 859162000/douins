package com.mango.fortune.account.service;

import java.util.List;

import com.mango.fortune.account.model.UserPayAccount;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.orm.DbOperateService;

public interface UserPayAccountService extends DbOperateService<UserPayAccount> {
	public ResponseCode add4Api(UserPayAccount payAccountVo, String transChannel) throws Exception;
	public ResponseCode change4Api(UserPayAccount payAccountVo) throws Exception;
	public List<UserPayAccount> findByCondition(UserPayAccount payAccountVo);
	public UserPayAccount findOneByUserId(String userId);
	public ResponseCode validate(UserPayAccount userPayAccountVo);
}
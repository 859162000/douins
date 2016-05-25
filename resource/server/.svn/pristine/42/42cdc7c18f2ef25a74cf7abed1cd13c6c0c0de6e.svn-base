package com.mango.fortune.account.service;

import java.util.List;

import com.mango.api.userAccountAPI.vo.UserAccountRequestVo;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.orm.DbOperateService;

public interface UserAccountService extends DbOperateService<UserAccount>{
	public ResponseCode add(String userId, String transChannel) throws Exception ;
	public List<UserAccount> findByCondition(UserAccount userAccount);
	public ResponseCode pay(UserAccountRequestVo userAccount,String transChannel) throws Exception;
	public UserAccount findOneByUserId(String userId);
}
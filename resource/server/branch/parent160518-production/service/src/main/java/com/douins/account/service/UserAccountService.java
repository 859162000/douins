package com.douins.account.service;

import java.util.List;

import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.vo.UserAccountRequestVo;
import com.douins.trans.domain.enums.ResponseCode;
import com.mango.orm.DbOperateService;

public interface UserAccountService extends DbOperateService<UserAccount>{
	public ResponseCode add(String userId, String transChannel) throws Exception;

	public List<UserAccount> findByCondition(UserAccount userAccount);

	public ResponseCode pay(UserAccountRequestVo userAccount, String transChannel) throws Exception;

	public UserAccount findOneByUserId(String userId);

}
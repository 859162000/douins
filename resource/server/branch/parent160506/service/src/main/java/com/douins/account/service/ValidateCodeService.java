package com.douins.account.service;

import com.douins.account.domain.model.ValidateCode;
import com.douins.trans.domain.enums.ResponseCode;
import com.mango.orm.DbOperateService;

public interface ValidateCodeService extends DbOperateService<ValidateCode>{
	public boolean sendValidateCode(ValidateCode validateCode) throws Exception;
	public boolean vertify(ValidateCode validateCode) throws Exception;
	public ResponseCode sendMessage(String transType,String userMobile,int num);
	public boolean checkSms(String transType, String mobile,String content);
}
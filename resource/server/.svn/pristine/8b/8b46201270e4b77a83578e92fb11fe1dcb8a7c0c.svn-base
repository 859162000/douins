package com.mango.fortune.sms.service;

import java.util.Map;

import com.mango.fortune.sms.model.SmsSend;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.orm.DbOperateService;


public interface SmsSendService extends DbOperateService<SmsSend>{
	public boolean sendSms(SmsSend smsSend,Map<String, String> data)throws Exception;
	
	public boolean checkSms(String transType, String mobile,String content) ;

	public SmsSend findSmsLast(SmsSend smsSend);
	
	public ResponseCode sendMessage(String transType,String userMobile,int num);
	
}

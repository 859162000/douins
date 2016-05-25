package com.douins.sms.service;

import java.util.Map;

import com.douins.sms.domain.model.SmsSend;
import com.douins.trans.domain.enums.ResponseCode;
import com.mango.orm.DbOperateService;


public interface SmsSendService extends DbOperateService<SmsSend>{
	public boolean sendSms(SmsSend smsSend,Map<String, String> data)throws Exception;
	
	public boolean checkSms(String transType, String mobile,String content) ;

	public SmsSend findSmsLast(SmsSend smsSend);
	
	public ResponseCode sendMessage(String transType,String userMobile,int num);
	
}

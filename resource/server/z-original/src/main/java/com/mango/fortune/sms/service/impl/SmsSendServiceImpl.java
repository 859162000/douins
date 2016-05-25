package com.mango.fortune.sms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.core.spring.SpringContextHelper;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.sms.enums.SmsConfig;
import com.mango.fortune.sms.enums.SmsStatusType;
import com.mango.fortune.sms.enums.SmsTemplateType;
import com.mango.fortune.sms.model.SmsSend;
import com.mango.fortune.sms.service.SmsSendApplyService;
import com.mango.fortune.sms.service.SmsSendService;
import com.mango.fortune.sms.yunpian.SmsResultVo;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.util.ConstantsUtil;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;
@Service("smsSendService")
public class SmsSendServiceImpl implements SmsSendService {

	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private BaseDao<SmsSend> smsSendDao;
	@Autowired
	@Qualifier("yunPianSmsService")
	private SmsSendApplyService yunPianSmsService;

	private String mapper = SmsSend.class.getName();
	
	
	@Override
	public boolean sendSms(SmsSend smsSend,Map<String, String> data) throws Exception {		
		boolean flag = false;
		String contentTemplate = SmsTemplateType.getContentByValue(smsSend.getSmsTemplateId());
		String applyType = SmsTemplateType.getApplyByValue(smsSend.getSmsTemplateId());
		String content = this.setValueToContent(contentTemplate, data);
		smsSend.setSmsContent(content);		
		smsSend.setSmsApply(applyType);
		smsSend.setStatus(SmsStatusType.NOT_SEND.getCode());
		SmsResultVo srv = null;
		logger.info("===before sms hashCode:" + smsSend.hashCode() + ";content:" + smsSend.getSmsContent() + ";mobile:" + smsSend.getMobile());
		try {
			SaveEntityUtils.initEntityBeforeInsert(smsSend, SystemConstant.OP_USER);
			smsSendDao.save(mapper + "Mapper.insert", smsSend);
			
			srv = ((SmsSendApplyService)SpringContextHelper.getBean(applyType)).sendSms(smsSend.getSmsContent() ,smsSend.getMobile()) ;
			
			if(srv.getResultCode().equals(SmsStatusType.HAVE_SEND_SUCCESS.getCode())){
				flag = true;
			}
			
			smsSend.setStatus(srv.getResultCode());
			smsSend.setResultMsg(srv.getResultMsg());	
			SaveEntityUtils.setUpdateForEntity(smsSend, SystemConstant.OP_USER);

			smsSendDao.update(mapper + "Mapper.updateForStatus", smsSend);
		} catch (DataBaseAccessException e) {
			logger.error("sendSms exception", e);
			throw e;
		}
		logger.info("===end sms hashCode:" + smsSend.hashCode() + ";resultMsg:" + smsSend.getResultMsg());
		return flag;
	}

	private String setValueToContent(String content, Map<String, String> data) {
		StringBuffer resultContent = new StringBuffer("");
		if(ConstantsUtil.isDebug()){
			data.put("isTest", SmsConfig.ISTEST.getCode());
		}else{
			data.put("isTest", "");
		}
		String[] contents = content.split("#");
		for (int i = 0; i < contents.length; i++) {
			if ((i + 1) % 2 == 0) {
				resultContent.append(data.get(contents[i]) == null ? "" : data
						.get(contents[i]));
			} else {
				resultContent.append(contents[i]);
			}
		}
		return resultContent.toString();
	}

	@Override
	public Page<SmsSend> getPage(SmsSend paramT, Page<SmsSend> paramPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsSend findByKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String paramString, SmsSend paramT) throws DataBaseAccessException {
		return smsSendDao.save(mapper + "Mapper.insert", paramT) > 0;
	}

	@Override
	public boolean update(String paramString, SmsSend paramT) throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String paramString, SmsSend paramT) throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkSms(String transType, String mobile,String content) {
		SmsSend smsSend = new SmsSend();
		smsSend.setTransType(transType);
		smsSend.setMobile(mobile);
		try {
			SmsSend result = findSmsLast(smsSend);
			if(result!=null && result.getCheckCode().equalsIgnoreCase(content)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.error("checkSms",e);
			return false;
		}
	}

	@Override
	public SmsSend findSmsLast(SmsSend smsSend) {
		return smsSendDao.get(mapper + "Mapper.findSmsLast", smsSend);
	}
	
	
	public ResponseCode sendMessage(String transType,String userMobile,int num){
		ResponseCode responseCode=ResponseCode.FAILED;
		String random = RandomStringUtils.randomNumeric(num);
		SmsSend smsSend = new SmsSend();
		smsSend.setTransType(transType);
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("checkcode", random);
		smsSend.setCheckCode(random);
		smsSend.setMobile(transType);
		SmsTemplateType smsTemplate = SmsTemplateType.getSmsTemplateTypebyType(transType);
		if(smsTemplate !=null){
			smsSend.setSmsTemplateId(smsTemplate.getValue());			
			try {
				if(this.sendSms(smsSend,dataMap)){
					responseCode=ResponseCode.SUCCESS;
				}else{
					responseCode=ResponseCode.FAILED;
				}
			} catch (Exception e) {
				e.printStackTrace();
				responseCode=ResponseCode.FAILED;
			}	
		}else{
			responseCode=ResponseCode.DATAREAD_ERROR;
		}
		return responseCode;
	}

}

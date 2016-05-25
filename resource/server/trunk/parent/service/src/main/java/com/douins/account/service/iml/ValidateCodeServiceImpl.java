/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.account.service.iml;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.sms.domain.enums.SmsTemplateType;
import com.douins.sms.domain.model.SmsSend;
import com.douins.sms.service.SmsSendService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.account.domain.model.ValidateCode;
import com.douins.account.dao.ValidateCodeDao;
import com.douins.account.domain.enums.ValidateType;
import com.douins.account.service.ValidateCodeService;
import com.douins.common.util.SaveEntityUtils;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;
import com.mysql.fabric.xmlrpc.base.Data;

import oracle.net.aso.g;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年8月13日 下午2:08:51   
 */
@Service("validateCodeService")
public class ValidateCodeServiceImpl implements ValidateCodeService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());

//	@Autowired
//	private BaseDao<ValidateCode> validateCodeDao;

//	private String mapper = ValidateCode.class.getName();
	
	@Inject
	private ValidateCodeDao codeDao;
	
	@Autowired
	@Qualifier("smsSendService")
	private SmsSendService smsSendService;
	
	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#delete(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean delete(String userName, ValidateCode validateCode)
			throws DataBaseAccessException {
		return false;
	}
	
	
	public boolean deleteByAccount(String userName, ValidateCode validateCode)
			throws DataBaseAccessException {
		//return validateCodeDao.update(mapper + "Mapper.softDeleteByAccount", validateCode) > 0;
	    codeDao.update(validateCode);
	    return true;
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#findByKey(java.lang.String)
	 */
	@Override
	public ValidateCode findByKey(String codeId) {
		// TODO Auto-generated method stub
		return codeDao.findByCodeId(codeId);
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#getPage(java.lang.Object, com.mango.orm.page.Page)
	 */
	@Override
	public Page<ValidateCode> getPage(ValidateCode arg0, Page<ValidateCode> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#save(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean save(String userName, ValidateCode validateCode)
			throws DataBaseAccessException {
		//return validateCodeDao.save(mapper + "Mapper.insert", validateCode) > 0;
	    codeDao.add(validateCode);
	    return true;
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#update(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(String arg0, ValidateCode arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean sendValidateCode(ValidateCode validateCode) throws Exception{
		boolean isSend = false;
		validateCode.setSendTime(DateUtils.today());
		SaveEntityUtils.initEntityBeforeInsert(validateCode, "");
		this.save("", validateCode);
		//短信验证
		if(ValidateType.SMS.getCode().equals(validateCode.getValidateType())){
			SmsSend smsSend = new SmsSend();
			smsSend.setMobile(validateCode.getValidateAccount());
			smsSend.setTransType(validateCode.getCodeType());
			SmsTemplateType smsTemplate = SmsTemplateType.getSmsTemplateTypebyType(validateCode.getCodeType());

			if(smsTemplate != null) {
				smsSend.setSmsTemplateId(smsTemplate.getValue());
				Map<String,String> dataMap = new HashMap<String,String>();
				dataMap.put("company", "豆芽金服");
				dataMap.put("code", validateCode.getValidateCode());
				isSend = smsSendService.sendSms(smsSend, dataMap);
			}else{
				logger.info("SmsTemplate not set,transType():" + validateCode.getCodeType());
			}
		}
		
		return isSend;
	}

	/* (non-Javadoc)
	 * @see com.mango.finance.sms.service.ValidateCodeService#vertify(com.mango.finance.sms.model.ValidateCode)
	 */
	@Override
	public boolean vertify(ValidateCode validateCode) throws Exception {
		ValidateCode lastestValidateCode = this.findLatestValidateCode(validateCode);
		if(lastestValidateCode != null
				&& lastestValidateCode.getValidateCode().equals(validateCode.getValidateCode())){
			SaveEntityUtils.setUpdateForEntity(lastestValidateCode, "");
			// 验证通过，置为无效
			this.deleteByAccount("", lastestValidateCode);
			return true;
		}
		return false;
	}
	
	public ValidateCode findLatestValidateCode(ValidateCode validateCode) {
		//return validateCodeDao.get(mapper + "Mapper.findLatestValidateCode", validateCode);
	    return codeDao.findLatestValidateCode(validateCode);
	}
	
	public ResponseCode sendMessage(String transType,String userMobile,int num){
		ResponseCode responseCode = ResponseCode.FAILED;
		String random = RandomStringUtils.randomNumeric(num);
		
		ValidateCode validateCode = new ValidateCode();
		validateCode.setCodeType(transType);
		validateCode.setValidateAccount(userMobile);
		validateCode.setValidateCode(random);
		validateCode.setValidateType(ValidateType.SMS.getCode());
		try {
			if (this.sendValidateCode(validateCode)) {
				responseCode = ResponseCode.SUCCESS;
			} else {
				responseCode = ResponseCode.SMSNOTSEND;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return responseCode;
	}
	

	public boolean checkSms(String transType, String mobile,String content) {
		ValidateCode validateCode = new ValidateCode();
		validateCode.setCodeType(transType);
		validateCode.setValidateAccount(mobile);
		validateCode.setValidateCode(content);
		validateCode.setValidateType(ValidateType.SMS.getCode());
		try {
			if(this.vertify(validateCode)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.error("checkSms",e);
			return false;
		}
	}

}

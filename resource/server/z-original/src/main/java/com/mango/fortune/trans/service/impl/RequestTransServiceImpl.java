/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.mango.fortune.trans.service.impl;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.api.basic.vo.RequestTransVo;
import com.mango.core.common.util.DateUtils;
import com.mango.core.common.util.MD5Util;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.service.RequestTransService;
import com.mango.fortune.util.Const;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.fortune.util.ReadConfig;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月28日 上午10:15:39   
 */
@Service("requestTransService")
public class RequestTransServiceImpl implements RequestTransService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	@Autowired
	private BaseDao<RequestTrans> requestTransDao;
	
	private String mapper = RequestTrans.class.getName();
	
	private final static String CHECKSIGNVALUE = "isCheckSign";
	
	private final static String SAVETRANSVALUE = "isSaveTrans";
	
	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#delete(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean delete(String arg0, RequestTrans arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#findByKey(java.lang.String)
	 */
	@Override
	public RequestTrans findByKey(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#getPage(java.lang.Object, com.mango.orm.page.Page)
	 */
	@Override
	public Page<RequestTrans> getPage(RequestTrans arg0, Page<RequestTrans> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#save(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean save(String userName, RequestTrans requestTrans) throws DataBaseAccessException {
		return requestTransDao.save(mapper +"Mapper.insert", requestTrans) > 0;
	}

	/* (non-Javadoc)
	 * @see com.mango.orm.DbOperateService#update(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(String arg0, RequestTrans arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkAndSaveTrans(String data,String sign,String channel,String ip){
		logger.info("get trans info===>sign:"+sign+";channel:"+channel+";data:"+data);
		
		boolean isSuccess = true;
		if(StringUtils.isBlank(sign) || StringUtils.isBlank(data) || StringUtils.isBlank(channel)){
			return false;
		}
		String isCheckSign = ReadConfig.get(CHECKSIGNVALUE);
		logger.debug("isCheckSign"+isCheckSign);
		if (!"false".equals(isCheckSign)) {
			String key = ReadConfig.get(channel);
			String encodeSign = MD5Util.MD5Encode(data + key, Const.ENCODE_UTF8);
			//校验sign
			if (!sign.equals(encodeSign)) {
				logger.info("sign is not equels encodeSign:" + encodeSign);
				return false;
			}
		}
		RequestTrans rt;
		try {
			String isSaveTrans = ReadConfig.get(SAVETRANSVALUE);
			if("true".equals(isSaveTrans)){
				rt = this.json2Object(data,ip);
				if(rt != null){
					//保存交易信息
					SaveEntityUtils.initEntityBeforeInsert(rt,"");
					this.save("", rt);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return isSuccess;
	}
	
	public RequestTrans json2Object(String data,String ip) throws ParseException{
		RequestTransVo rtVo = JsonOnlineUtils.readJson2Entity(data, RequestTransVo.class);
		RequestTrans requestTrans = rtVo.getRequestTrans();
		if(requestTrans != null){
			requestTrans.setRequestTime(DateUtils.today());
			if(StringUtils.isNotBlank(requestTrans.getTransTime())){
				requestTrans.setTransTimeDate(DateUtils.stringToDate(requestTrans.getTransTime(), Const.DATEPATTERN_TIME));
			}
			requestTrans.setIpAddress(ip);
		}
		return requestTrans;
	}
	
}

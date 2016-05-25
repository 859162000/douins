/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.trans.service;

import com.douins.trans.domain.model.RequestTrans;
import com.mango.orm.DbOperateService;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月28日 上午10:13:00   
 */
public interface RequestTransService extends DbOperateService<RequestTrans>{
	public boolean checkAndSaveTrans(String data,String sign,String channel,String ip);
}

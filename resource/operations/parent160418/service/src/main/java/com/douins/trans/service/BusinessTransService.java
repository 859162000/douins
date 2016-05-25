package com.douins.trans.service;

import java.util.List;

import com.douins.trans.domain.model.BusinessTrans;
import com.mango.orm.DbOperateService;

public interface BusinessTransService extends DbOperateService<BusinessTrans>{
	public List<BusinessTrans> findByCondition(BusinessTrans trans);
}
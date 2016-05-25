package com.douins.apply.service;

import com.douins.apply.domain.model.ApplyInfo;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.DbOperateService;

public interface ApplyInfoService extends DbOperateService<ApplyInfo>{
	public boolean updateAfter(String userName, ApplyInfo applyInfo) throws DataBaseAccessException;
}
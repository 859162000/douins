package com.mango.fortune.apply.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.apply.model.ApplyInfo;
import com.mango.fortune.apply.service.ApplyInfoService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("applyInfoService")
public class ApplyInfoServiceImpl implements ApplyInfoService {
	
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	@Autowired
	private BaseDao<ApplyInfo> baseDao;
	
	private String mapper = ApplyInfo.class.getName();
	
	@Override
	public boolean delete(String arg0, ApplyInfo arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ApplyInfo findByKey(String key) {
		return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	}

	@Override
	public Page<ApplyInfo> getPage(ApplyInfo arg0, Page<ApplyInfo> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String userName, ApplyInfo applyInfo)
			throws DataBaseAccessException {
		return baseDao.save(mapper + "Mapper.insert", applyInfo) > 0;
	}

	@Override
	public boolean update(String arg0, ApplyInfo arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean updateAfter(String userName, ApplyInfo applyInfo)
			throws DataBaseAccessException {
		return baseDao.update(mapper + "Mapper.updateAfter", applyInfo) > 0;
	}

}

package com.douins.apply.service.iml;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.apply.dao.ApplyInfoDao;
import com.douins.apply.domain.model.ApplyInfo;
import com.douins.apply.service.ApplyInfoService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("applyInfoService")
public class ApplyInfoServiceImpl implements ApplyInfoService {
	
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
//	@Autowired
//	private BaseDao<ApplyInfo> baseDao;
//	
//	private String mapper = ApplyInfo.class.getName();
	
	@Inject
	private ApplyInfoDao infoDao;
	
	@Override
	public boolean delete(String arg0, ApplyInfo arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ApplyInfo findByKey(String key) {
		//return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	    return infoDao.findByInfoId(key);
	}

	@Override
	public Page<ApplyInfo> getPage(ApplyInfo arg0, Page<ApplyInfo> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String userName, ApplyInfo applyInfo)
			throws DataBaseAccessException {
		//return baseDao.save(mapper + "Mapper.insert", applyInfo) > 0;
	    infoDao.add(applyInfo);
	    return true;
	}

	@Override
	public boolean update(String arg0, ApplyInfo arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean updateAfter(String userName, ApplyInfo applyInfo)
			throws DataBaseAccessException {
		//return baseDao.update(mapper + "Mapper.updateAfter", applyInfo) > 0;
	    infoDao.update(applyInfo);
	    return true;
	}

}

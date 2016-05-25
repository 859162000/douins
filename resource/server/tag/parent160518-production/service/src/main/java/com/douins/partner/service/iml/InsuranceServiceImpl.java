package com.douins.partner.service.iml;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.partner.dao.InsuranceDao;
import com.douins.partner.domain.model.Insurance;
import com.douins.partner.service.InsuranceService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class InsuranceServiceImpl implements InsuranceService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<Insurance> baseDao;
//	
//	private String mapperName = Insurance.class.getName();
	
	@Inject
	private InsuranceDao insDao;

	@Override
	public boolean delete(String arg0, Insurance paramT)
			throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getInsuranceId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    
	    insDao.delete(paramT.getInsuranceId());
	    return true;
	}

	@Override
	public Insurance findByKey(String arg0) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", arg0);
	    return insDao.selectByPrimaryKey(arg0);
	}

	@Override
	public Page<Insurance> getPage(Insurance paramT, Page<Insurance> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public boolean save(String arg0, Insurance paramT)
			throws DataBaseAccessException {
//		try {
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    insDao.insert(paramT);
	    return true;
	}

	@Override
	public boolean update(String arg0, Insurance paramT)
			throws DataBaseAccessException {
//		try {
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    insDao.updateByPrimaryKey(paramT);
	    return true;
	}
}

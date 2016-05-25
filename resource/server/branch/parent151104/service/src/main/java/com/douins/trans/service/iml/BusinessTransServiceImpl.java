package com.douins.trans.service.iml;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.trans.dao.BusinessTransDao;
import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.service.BusinessTransService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class BusinessTransServiceImpl implements BusinessTransService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<BusinessTrans> baseDao;
//	private String mapperName = BusinessTrans.class.getName();
	
	@Inject
	private BusinessTransDao transDao;

	@Override
	public List<BusinessTrans> findByCondition(BusinessTrans bank) {
		//return baseDao.getList(mapperName + "Mapper.getList", bank);
	    return transDao.getList(bank);
	}
	
	@Override
	public Page<BusinessTrans> getPage(BusinessTrans paramT, Page<BusinessTrans> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public BusinessTrans findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return transDao.selectByPrimaryKey(paramString);
	}

	@Override
	public boolean save(String paramString, BusinessTrans paramT) throws DataBaseAccessException {
//		try {
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    transDao.insert(paramT);
	    return true;
	}

	@Override
	public boolean update(String paramString, BusinessTrans paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    transDao.updateByPrimaryKey(paramT);
	    return true;
	}

	@Override
	public boolean delete(String paramString, BusinessTrans paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getBusinessId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    transDao.softDeleteByPrimaryKey(paramT.getBusinessId());
	    return true;
	}
}

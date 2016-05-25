package com.douins.product.service.iml;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.product.dao.ProductLoanCycleDao;
import com.douins.product.domain.model.ProductLoanCycle;
import com.douins.product.service.ProductLoanCycleService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("productLoanCycleService")
public class ProductLoanCycleServiceImpl implements ProductLoanCycleService {
	
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<ProductLoanCycle> baseDao;
//
//	private String mapper = ProductLoanCycle.class.getName();
	
	@Inject
	private ProductLoanCycleDao cycleDao;
	
	@Override
	public boolean delete(String arg0, ProductLoanCycle arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductLoanCycle findByKey(String key) {
		//return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	    return cycleDao.selectByPrimaryKey(key);
	}

	@Override
	public Page<ProductLoanCycle> getPage(ProductLoanCycle arg0,
			Page<ProductLoanCycle> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String arg0, ProductLoanCycle arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String arg0, ProductLoanCycle arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<ProductLoanCycle> findByCondition(ProductLoanCycle productLoanCycle)
			throws DataBaseAccessException {
		//return baseDao.getList(mapper + "Mapper.getList", productLoanCycle);
	    return cycleDao.getList(productLoanCycle);
	}
}

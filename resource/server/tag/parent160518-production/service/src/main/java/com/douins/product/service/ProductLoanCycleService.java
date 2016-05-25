package com.douins.product.service;

import java.util.List;

import com.mango.exception.DataBaseAccessException;
import com.douins.product.domain.model.ProductLoanCycle;
import com.mango.orm.DbOperateService;

public interface ProductLoanCycleService extends DbOperateService<ProductLoanCycle>{
	public List<ProductLoanCycle> findByCondition(ProductLoanCycle productLoanCycle)
			throws DataBaseAccessException;
}
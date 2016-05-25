package com.douins.product.service;

import java.util.List;

import com.mango.exception.DataBaseAccessException;
import com.douins.product.domain.model.Product;
import com.douins.product.domain.vo.ProductRequest;
import com.douins.product.domain.vo.ProductVo;
import com.mango.orm.DbOperateService;

public interface ProductService extends DbOperateService<Product>{
	public List<Product> findByCondition(Product product)
			throws DataBaseAccessException;
	
	public List<ProductVo> getProduct4Api(ProductRequest productRequest)
			throws DataBaseAccessException;
	
	
	public boolean updateAfterPaySuccess(String userName, Product product)
			throws DataBaseAccessException ;
	
	public ProductVo getRecommendProduct();
}
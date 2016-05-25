package com.mango.fortune.product.service;

import java.util.List;

import com.mango.api.productAPI.vo.ProductRequest;
import com.mango.api.productAPI.vo.ProductVo;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.product.model.Product;
import com.mango.orm.DbOperateService;

public interface ProductService extends DbOperateService<Product>{
	public List<Product> findByCondition(Product product)
			throws DataBaseAccessException;
	
	public List<ProductVo> getProduct4Api(ProductRequest productRequest)
			throws DataBaseAccessException;
	
	
	public boolean updateAfterPaySuccess(String userName, Product product)
			throws DataBaseAccessException ;
}
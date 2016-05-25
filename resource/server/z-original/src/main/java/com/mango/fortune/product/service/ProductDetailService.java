package com.mango.fortune.product.service;

import com.mango.fortune.product.model.ProductDetail;
import com.mango.orm.DbOperateService;

public interface ProductDetailService extends DbOperateService<ProductDetail>{
	public ProductDetail getByProductId(String key) ;
}
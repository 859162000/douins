package com.douins.product.service;

import com.douins.product.domain.model.ProductDetail;
import com.mango.orm.DbOperateService;

public interface ProductDetailService extends DbOperateService<ProductDetail>{
	public ProductDetail getByProductId(String key) ;
}
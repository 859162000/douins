/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.product.domain.vo;

import com.douins.product.domain.model.Product;
import com.douins.trans.domain.vo.RequestTransVo;


/**
 * @author xuhuiwang
 * @version 1.0, 2015年5月5日 下午1:33:51   
 */
public class ProductRequest extends RequestTransVo{
	
	private static final long serialVersionUID = -4097137190363085383L;
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}

/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.mango.api.productAPI.vo;

import java.util.List;

import com.mango.api.basic.vo.ResponseTransVo;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年5月5日 下午1:48:07
 */
public class ProductResponse extends ResponseTransVo {

	private static final long serialVersionUID = -2128360396047657301L;
	
	private List<ProductVo> productList;

	public List<ProductVo> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductVo> productList) {
		this.productList = productList;
	}

}

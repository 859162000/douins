/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.product.domain.vo;

import java.util.List;

import com.douins.trans.domain.vo.ResponseTransVo;
import com.google.common.collect.Lists;

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

	public void setProductList(ProductVo product){
	    if(productList != null){
	        productList.clear();
	        productList = null;
	    }
	    
	    productList = Lists.newArrayList();
	    productList.add(product);
	}
}

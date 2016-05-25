package com.mango.api.productAPI.vo;

import com.mango.api.basic.vo.ResponseTransVo;
import com.mango.fortune.product.model.ProductDetail;

public class ProductDetailResponse extends ResponseTransVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2661714157739541652L;
	private ProductDetail productDetail;
	
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	
}

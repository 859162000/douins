package com.douins.product.domain.vo;

import com.douins.product.domain.model.ProductDetail;
import com.douins.trans.domain.vo.ResponseTransVo;

public class ProductDetailResponse extends ResponseTransVo{

	private static final long serialVersionUID = 2661714157739541652L;
	private ProductDetail productDetail;
	
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	
}

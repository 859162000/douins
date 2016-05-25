package com.douins.insurance.domain.modelLianlife;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("ProductList")
public class ProductList {
	@XStreamImplicit(itemFieldName ="ProductInfo")
	private List<ProductInfo> productlist;

	public List<ProductInfo> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<ProductInfo> productlist) {
		this.productlist = productlist;
	}
	
	

}

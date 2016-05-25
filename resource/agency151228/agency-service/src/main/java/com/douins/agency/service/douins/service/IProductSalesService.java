package com.douins.agency.service.douins.service;


import com.douins.agency.service.douins.domain.model.ProductValid;

public interface IProductSalesService {
	
	/**
	 * 承保时更新产品的总销售额 通过订单id和销售总额
	 * @param totalSales
	 * @param tradeCode
	 * @return
	 */
	public int saveProductSalesWithISS(String orderId,String totalSales);
	
	/**
	 * 退保时更新产品的总销售额 通过订单id和销售总额
	 * @param totalSales
	 * @param tradeCode
	 * @return
	 */
	public int saveProductSalesWithWD(String policyNo,String totalSales);
	
	/**
	 * 更新产品的总销售额 通过订单id和销售总额
	 * @param orderId
	 * @param totalSales
	 * @return
	 */
	public int saveProductSales(String orderId,String totalSales,String tradeCode);
	
	/**
	 * 更新产品的总销售额
	 * @param productValid
	 * @return
	 */
	public int saveProductSales(ProductValid productValid);
	
	/**
	 * 根据产品编号返回产品总销售额
	 * @param productCode
	 * @return
	 */
	public ProductValid getProductSaleWithProductCode(String productCode);
	
	/**
	 * 取回所有产品的总销售额度
	 * @param productCode
	 * @return
	 */
	public String getProductSaleSum(String productCode);
	
	/**
	 * 根据订单号返回产品总销售额
	 * @param orderId
	 * @return
	 */
	public ProductValid getProductSaleWithOrderId(String orderId);
	
	/**
	 * 根据产品编号返回产品销售总额
	 * @param orderId
	 * @param productCode
	 * @return
	 */
	public String getProductTotalSales(String productCode);
	
	/**
	 * 根据保单号取出产品号
	 * @param policyNo
	 * @return
	 */
	public ProductValid getProductSaleWithPolicyNo(String policyNo);
	
}

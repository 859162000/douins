package com.douins.agency.service.douins.domain.vo;
/**
* @ClassName: ProductWhrCondition 
* @Description: 渠道方传入的产品信息检索条件
* @author G. F. 
* @date 2016年1月1日 下午3:46:19 
*
 */
public class ProductWhrCondition {
    private String channlCode;   //渠道(销售)编码
    private String channlPdId;   //渠道产品代码
    private String saleStrategy; //销售策略
    
	public String getChannlCode() {
		return channlCode;
	}
	public void setChannlCode(String channlCode) {
		this.channlCode = channlCode;
	}
	public String getChannlPdId() {
		return channlPdId;
	}
	public void setChannlPdId(String channlPdId) {
		this.channlPdId = channlPdId;
	}
	public String getSaleStrategy() {
		return saleStrategy;
	}
	public void setSaleStrategy(String saleStrategy) {
		this.saleStrategy = saleStrategy;
	}
	   
}

package com.douins.product.domain.model;

import java.math.BigDecimal;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class ProductDetail extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3285913100164669379L;

	private Long id;

    private String productDetailId;

    private String productId;

    private BigDecimal productAmount;

    private String receiveTimeDesc;

    private String receiveType;

    private String productDesc;

    private String buyDesc;

    private String productBuyDesc;

    private String riskDesc;
    
    private String productInsureName;

    private String isvalid;

    public String getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(String productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public String getReceiveTimeDesc() {
        return receiveTimeDesc;
    }

    public void setReceiveTimeDesc(String receiveTimeDesc) {
        this.receiveTimeDesc = receiveTimeDesc;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getBuyDesc() {
        return buyDesc;
    }

    public void setBuyDesc(String buyDesc) {
        this.buyDesc = buyDesc;
    }

    public String getProductBuyDesc() {
        return productBuyDesc;
    }

    public void setProductBuyDesc(String productBuyDesc) {
        this.productBuyDesc = productBuyDesc;
    }

    public String getRiskDesc() {
        return riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

	@Override
	public void initPrimaryKey() {
		this.setProductDetailId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductInsureName() {
		return productInsureName;
	}

	public void setProductInsureName(String productInsureName) {
		this.productInsureName = productInsureName;
	}

    
}
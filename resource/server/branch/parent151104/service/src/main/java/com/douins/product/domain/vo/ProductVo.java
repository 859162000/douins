/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.product.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年5月5日 上午8:57:16   
 */
public class ProductVo implements Serializable {

	private static final long serialVersionUID = 5853637115921133423L;
	
	private String productId;
	private String productName;
	private String productType;
	private BigDecimal expectRate=new BigDecimal("0.00");
	private Integer productPeriod;
	private String periodType;
	private Long stock;
	private int saleNum;
	private BigDecimal minPrem;
	private String riskLevel;
	private String status;
	private int isRecommend;
	private Date saleBeginTime;
    private Date saleEndTime;
    private int rateLoanable;       // 可贷比例
    private int insuredNum;         // 已购买人数
    private String valueDate;                   // 起息日，描述性文字
    private String RedemptionMode;      // 赎回方式
    private int hesitateTerm;               // 犹豫期天数
    private BigDecimal saledAmount=new BigDecimal("0.00");         //累计已售金额
    private String insurancecategory;
    private String periodpremium;
    private String productCode;
    private String ispackage;
    private String afterdayeffective;
    private String  calculationrule;
    private String  coverperiodtype;
	private Date effectivedate;
	private String  insurancecoverage;
	private String insurancenum;
	private String  insuranceperiod;
	private String  premperiodType;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public BigDecimal getExpectRate() {
		return expectRate;
	}
	public void setExpectRate(BigDecimal expectRate) {
		this.expectRate = expectRate;
	}
	public Integer getProductPeriod() {
		return productPeriod;
	}
	public void setProductPeriod(Integer productPeriod) {
		this.productPeriod = productPeriod;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public BigDecimal getMinPrem() {
		return minPrem;
	}
	public void setMinPrem(BigDecimal minPrem) {
		this.minPrem = minPrem;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}
    public int getIsRecommend() {
        return isRecommend;
    }
    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }
    public Date getSaleBeginTime() {
        return saleBeginTime;
    }
    public void setSaleBeginTime(Date saleBeginTime) {
        this.saleBeginTime = saleBeginTime;
    }
    public Date getSaleEndTime() {
        return saleEndTime;
    }
    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }
    public int getRateLoanable() {
        return rateLoanable;
    }
    public void setRateLoanable(int rateLoanable) {
        this.rateLoanable = rateLoanable;
    }
    public int getInsuredNum() {
        return insuredNum;
    }
    public void setInsuredNum(int insuredNum) {
        this.insuredNum = insuredNum;
    }
    public String getValueDate() {
        return valueDate;
    }
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }
    public String getRedemptionMode() {
        return RedemptionMode;
    }
    public void setRedemptionMode(String redemptionMode) {
        RedemptionMode = redemptionMode;
    }
    public int getHesitateTerm() {
        return hesitateTerm;
    }
    public void setHesitateTerm(int hesitateTerm) {
        this.hesitateTerm = hesitateTerm;
    }
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getSaledAmount() {
		return saledAmount;
	}
	public void setSaledAmount(BigDecimal saledAmount) {
		this.saledAmount = saledAmount;
	}
	public String getInsurancecategory() {
		return insurancecategory;
	}
	public void setInsurancecategory(String insurancecategory) {
		this.insurancecategory = insurancecategory;
	}
	public String getPeriodpremium() {
		return periodpremium;
	}
	public void setPeriodpremium(String periodpremium) {
		this.periodpremium = periodpremium;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getIspackage() {
		return ispackage;
	}
	public void setIspackage(String ispackage) {
		this.ispackage = ispackage;
	}
	
	public String getAfterdayeffective() {
		return afterdayeffective;
	}
	public void setAfterdayeffective(String afterdayeffective) {
		this.afterdayeffective = afterdayeffective;
	}
	public String getCalculationrule() {
		return calculationrule;
	}
	public void setCalculationrule(String calculationrule) {
		this.calculationrule = calculationrule;
	}
	public String getCoverperiodtype() {
		return coverperiodtype;
	}
	public void setCoverperiodtype(String coverperiodtype) {
		this.coverperiodtype = coverperiodtype;
	}
	
	public Date getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(Date effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getInsurancecoverage() {
		return insurancecoverage;
	}
	public void setInsurancecoverage(String insurancecoverage) {
		this.insurancecoverage = insurancecoverage;
	}
	public String getInsurancenum() {
		return insurancenum;
	}
	public void setInsurancenum(String insurancenum) {
		this.insurancenum = insurancenum;
	}
	public String getInsuranceperiod() {
		return insuranceperiod;
	}
	public void setInsuranceperiod(String insuranceperiod) {
		this.insuranceperiod = insuranceperiod;
	}
	public String getPremperiodType() {
		return premperiodType;
	}
	public void setPremperiodType(String premperiodType) {
		this.premperiodType = premperiodType;
	}
	
	
	
}

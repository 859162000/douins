package com.douins.insurance.domain.model;

import java.math.BigDecimal;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 
* @ClassName: PolicyInfo 
* @Description: 保单信息
* @author G. F. 
* @date 2015年12月11日 下午3:42:47 
*
 */
public class PolicyInfo {
    @XStreamAlias("OrderId")
	private String policyNo;           // 保单订单号
	private String proposalNo;     // 投保单号
	private String cvaliDate;          // 保单生效日期
	@XStreamAlias("ApplyNum")
	private Integer insuredNum;    // 投保份数
	@XStreamAlias("TotalPremium")
	private BigDecimal sumPrem;    // 保单总保费
	@XStreamAlias("Premium")
	private BigDecimal sumAmount;              // 保单总保额
	private BigDecimal refundPrem;             // 退保金额
	@XStreamAlias("AutoAplIndi")
	private String autoAplIndi;                    // 是否自动续保
	@XStreamAlias("ChargeYearFlag")
	private String chargePeriod;                   // 保单缴费类型
	@XStreamAlias("ChargeYear")
	private String chargeYear;                     // 保单缴费年限
	@XStreamAlias("PaymentFreq")
	private String paymantFreq;                // 缴费频率
	private String coveragePeriod;                 // 保单保障类型
	@XStreamAlias("CoverageYear")
	private Integer coverageYear;              // 保单保障年限
	
	private String coverageYearFlag;
	
	private String epolicyUrl;                     // 电子保单下载地址
	@XStreamAlias("SkuRiskCode")
	private String skuRiskcode;                    // 保单产品商家编码
	@XStreamAlias("ProductCode")
    private String productCode;                 // 产品代码
	
	private String legalBene;                      // 是否为法定受益人
	private String IsSucess;                       // 交易是否成功
	private String failedReason;               // 失败原因
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getCvaliDate() {
		return cvaliDate;
	}
	public void setCvaliDate(String cvaliDate) {
		this.cvaliDate = cvaliDate;
	}
	public Integer getInsuredNum() {
		return insuredNum;
	}
	public void setInsuredNum(Integer insuredNum) {
		this.insuredNum = insuredNum;
	}
	public BigDecimal getSumPrem() {
		return sumPrem;
	}
	public void setSumPrem(BigDecimal sumPrem) {
		this.sumPrem = sumPrem;
	}
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	public BigDecimal getRefundPrem() {
		return refundPrem;
	}
	public void setRefundPrem(BigDecimal refundPrem) {
		this.refundPrem = refundPrem;
	}
	public String getAutoAplIndi() {
		return autoAplIndi;
	}
	public void setAutoAplIndi(String autoAplIndi) {
		this.autoAplIndi = autoAplIndi;
	}
	public String getChargePeriod() {
		return chargePeriod;
	}
	public void setChargePeriod(String chargePeriod) {
		this.chargePeriod = chargePeriod;
	}
	public String getChargeYear() {
		return chargeYear;
	}
	public void setChargeYear(String chargeYear) {
		this.chargeYear = chargeYear;
	}
	public String getCoveragePeriod() {
		return coveragePeriod;
	}
	public void setCoveragePeriod(String coveragePeriod) {
		this.coveragePeriod = coveragePeriod;
	}
	public Integer getCoverageYear() {
		return coverageYear;
	}
	public void setCoverageYear(Integer coverageYear) {
		this.coverageYear = coverageYear;
	}
	public String getEpolicyUrl() {
		return epolicyUrl;
	}
	public void setEpolicyUrl(String epolicyUrl) {
		this.epolicyUrl = epolicyUrl;
	}
	public String getSkuRiskcode() {
		return skuRiskcode;
	}
	public void setSkuRiskcode(String skuRiskcode) {
		this.skuRiskcode = skuRiskcode;
	}
	public String getLegalBene() {
		return legalBene;
	}
	public void setLegalBene(String legalBene) {
		this.legalBene = legalBene;
	}
	public String getIsSucess() {
		return IsSucess;
	}
	public void setIsSucess(String isSucess) {
		IsSucess = isSucess;
	}
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
    public String getPaymantFreq() {
        return paymantFreq;
    }
    public void setPaymantFreq(String paymantFreq) {
        this.paymantFreq = paymantFreq;
    }
    public String getCoverageYearFlag() {
        return coverageYearFlag;
    }
    public void setCoverageYearFlag(String coverageYearFlag) {
        this.coverageYearFlag = coverageYearFlag;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}

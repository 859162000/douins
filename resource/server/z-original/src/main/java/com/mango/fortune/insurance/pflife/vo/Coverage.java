package com.mango.fortune.insurance.pflife.vo;

import java.math.BigDecimal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("coverage")
public class Coverage {
	private String productCode;
	private String masterProductCode;
	private String chargePeriod;
	private String chargeYear;
	private String coveragePeriod;
	private Integer coverageYear;
	private Integer mult;//投保份数
	private BigDecimal sumAssured;//保额 
	private BigDecimal premium;//保费
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getMasterProductCode() {
		return masterProductCode;
	}
	public void setMasterProductCode(String masterProductCode) {
		this.masterProductCode = masterProductCode;
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
	public Integer getMult() {
		return mult;
	}
	public void setMult(Integer mult) {
		this.mult = mult;
	}
	public BigDecimal getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(BigDecimal sumAssured) {
		this.sumAssured = sumAssured;
	}
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	
}

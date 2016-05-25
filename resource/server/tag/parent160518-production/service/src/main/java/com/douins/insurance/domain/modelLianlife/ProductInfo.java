package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ProductInfo")
public class ProductInfo {
	@XStreamAlias("AfterDayEffective")
	private String afterdayeffective;
	@XStreamAlias("CalculationRule")
	private String calculationrule;
	@XStreamAlias("CoverPeriodType")
	private String coverperiodtype;
	@XStreamAlias("EffectiveDate")
	private String effectivedate;
	@XStreamAlias("InsuranceCategory")
	private String insurancecategory;
	@XStreamAlias("InsuranceCoverage")
	private String insurancecoverage;
	@XStreamAlias("InsuranceNum")
	private String insurancenum;
	@XStreamAlias("InsurancePeriod")
	private String insuranceperiod;
	@XStreamAlias("IsPackage")
	private String ispackage;
	@XStreamAlias("PeriodPremium")
	private String periodpremium;
	@XStreamAlias("PremPeriodType")
	private String premperiodtype;
	@XStreamAlias("ProductCode")
	private String productcode;
	
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
	public String getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getInsurancecategory() {
		return insurancecategory;
	}
	public void setInsurancecategory(String insurancecategory) {
		this.insurancecategory = insurancecategory;
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
	public String getIspackage() {
		return ispackage;
	}
	public void setIspackage(String ispackage) {
		this.ispackage = ispackage;
	}
	public String getPeriodpremium() {
		return periodpremium;
	}
	public void setPeriodpremium(String periodpremium) {
		this.periodpremium = periodpremium;
	}
	public String getPremperiodtype() {
		return premperiodtype;
	}
	public void setPremperiodtype(String premperiodtype) {
		this.premperiodtype = premperiodtype;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	
}

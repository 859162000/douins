package com.douins.agency.service.channel.qunarfinance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("productinfo")
public class productinfo {
	private String  afterdayeffective;			
	private String  calculationrule;			
	private String  coverperiodtype;			
	private String  effectivedate;			
	private String  insurancecategory;			
	private String  insurancecoverage;			
	private String  insurancenum;	
	private String  insuranceperiod;			
	private String  ispackage;			
	private String  periodpremium;		
	private String  premperiodtype;			
	private String  productcode;
	private String  insuranceclass;
	
	public String getafterdayeffective() {
		return afterdayeffective;
	}
	public void setafterdayeffective(String afterdayeffective) {
		this.afterdayeffective = afterdayeffective;
	}
	public String getcalculationrule() {
		return calculationrule;
	}
	public void setcalculationrule(String calculationrule) {
		this.calculationrule = calculationrule;
	}
	public String getcoverperiodtype() {
		return coverperiodtype;
	}
	public void setcoverperiodtype(String coverperiodtype) {
		this.coverperiodtype = coverperiodtype;
	}
	public String geteffectivedate() {
		return effectivedate;
	}
	public void seteffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getinsurancecategory() {
		return insurancecategory;
	}
	public void setinsurancecategory(String insurancecategory) {
		this.insurancecategory = insurancecategory;
	}
	public String getinsurancecoverage() {
		return insurancecoverage;
	}
	public void setinsurancecoverage(String insurancecoverage) {
		this.insurancecoverage = insurancecoverage;
	}
	public String getinsurancenum() {
		return insurancenum;
	}
	public void setinsurancenum(String insurancenum) {
		this.insurancenum = insurancenum;
	}
	public String getinsuranceperiod() {
		return insuranceperiod;
	}
	public void setinsuranceperiod(String insuranceperiod) {
		this.insuranceperiod = insuranceperiod;
	}
	public String getispackage() {
		return ispackage;
	}
	public void setispackage(String ispackage) {
		this.ispackage = ispackage;
	}
	public String getperiodpremium() {
		return periodpremium;
	}
	public void setperiodpremium(String periodpremium) {
		this.periodpremium = periodpremium;
	}
	public String getpremperiodtype() {
		return premperiodtype;
	}
	public void setpremperiodtype(String premperiodtype) {
		this.premperiodtype = premperiodtype;
	}
	public String getproductcode() {
		return productcode;
	}
	public void setproductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getInsuranceclass() {
		return insuranceclass;
	}
	public void setInsuranceclass(String insuranceclass) {
		this.insuranceclass = insuranceclass;
	}
	
}

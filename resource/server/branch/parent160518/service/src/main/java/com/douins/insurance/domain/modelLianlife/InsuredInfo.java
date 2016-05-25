package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("InsuredInfo")
public class InsuredInfo {
	@XStreamAlias("InsurantList")
	private InsurantList InsurantList;
	@XStreamAlias("IsApplicant")
	private String isapplicant;
	
	public InsurantList getInsurantList() {
		return InsurantList;
	}
	public void setInsurantList(InsurantList insurantList) {
		InsurantList = insurantList;
	}
	public String getIsapplicant() {
		return isapplicant;
	}
	public void setIsapplicant(String isapplicant) {
		this.isapplicant = isapplicant;
	}
	
	
	
	
}

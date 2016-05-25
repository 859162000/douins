package com.douins.agency.service.insurance.lianlife.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("policynoitem")
public class PolicyNoItem {
	private String applyno,singlpolicyno;

	public String getapplyno() {
		return applyno;
	}

	public void setapplyno(String applyno) {
		this.applyno = applyno;
	}

	public String getsinglpolicyno() {
		return singlpolicyno;
	}

	public void setsinglpolicyno(String singlpolicyno) {
		this.singlpolicyno = singlpolicyno;
	}
	
	
}

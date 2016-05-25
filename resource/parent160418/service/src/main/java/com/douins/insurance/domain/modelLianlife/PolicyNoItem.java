package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class PolicyNoItem {
	@XStreamAlias("ApplyNo")
	private String applyno;
	@XStreamAlias("SinglPolicyNo")
	private String singlpolicyno;
	
	public String getApplyno() {
		return applyno;
	}
	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}
	public String getSinglpolicyno() {
		return singlpolicyno;
	}
	public void setSinglpolicyno(String singlpolicyno) {
		this.singlpolicyno = singlpolicyno;
	}

}

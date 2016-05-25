package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("otherifno")
public class OtherInfo {
	@XStreamAlias("ConIndProxyNO")
	private String conindproxyno;
	@XStreamAlias("InsureNO")
	private String insureno;
	@XStreamAlias("Plat")
	private String plat;
	
	public String getConindproxyno() {
		return conindproxyno;
	}
	public void setConindproxyno(String conindproxyno) {
		this.conindproxyno = conindproxyno;
	}
	public String getInsureno() {
		return insureno;
	}
	public void setInsureno(String insureno) {
		this.insureno = insureno;
	}
	public String getPlat() {
		return plat;
	}
	public void setPlat(String plat) {
		this.plat = plat;
	}
}

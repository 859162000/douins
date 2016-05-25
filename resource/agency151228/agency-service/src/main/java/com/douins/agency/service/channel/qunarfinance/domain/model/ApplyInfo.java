package com.douins.agency.service.channel.qunarfinance.domain.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("applyinfo")
public class ApplyInfo {
	private String agentnum;
	private Applicant applicant;
	private String applydate;
	private String applytype;
	private String channelnum;
	private String channelreginnum;
	private String channeltype;
	private String effectivedate;
	private InsuredInfo insuredinfo;
	private List<productinfo> productlist;
	private String totalpremium;
	
	public String getagentnum() {
		return agentnum;
	}
	public void setagentnum(String agentnum) {
		this.agentnum = agentnum;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public String getapplydate() {
		return applydate;
	}
	public void setapplydate(String applydate) {
		this.applydate = applydate;
	}
	public String getapplytype() {
		return applytype;
	}
	public void setapplytype(String applytype) {
		this.applytype = applytype;
	}
	public String getchannelnum() {
		return channelnum;
	}
	public void setchannelnum(String channelnum) {
		this.channelnum = channelnum;
	}
	public String getchannelreginnum() {
		return channelreginnum;
	}
	public void setchannelreginnum(String channelreginnum) {
		this.channelreginnum = channelreginnum;
	}
	public String getchanneltype() {
		return channeltype;
	}
	public void setchanneltype(String channeltype) {
		this.channeltype = channeltype;
	}
	public String geteffectivedate() {
		return effectivedate;
	}
	public void seteffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}
	
	public String getAgentnum() {
		return agentnum;
	}
	public String getApplydate() {
		return applydate;
	}
	public String getApplytype() {
		return applytype;
	}
	public String getChannelnum() {
		return channelnum;
	}
	public String getChannelreginnum() {
		return channelreginnum;
	}
	public String getChanneltype() {
		return channeltype;
	}
	public String getEffectivedate() {
		return effectivedate;
	}
	public InsuredInfo getInsuredinfo() {
		return insuredinfo;
	}
	public List<productinfo> getProductlist() {
		return productlist;
	}
	public String getTotalpremium() {
		return totalpremium;
	}
	public void setAgentnum(String agentnum) {
		this.agentnum = agentnum;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	public void setApplytype(String applytype) {
		this.applytype = applytype;
	}
	public void setChannelnum(String channelnum) {
		this.channelnum = channelnum;
	}
	public void setChannelreginnum(String channelreginnum) {
		this.channelreginnum = channelreginnum;
	}
	public void setChanneltype(String channeltype) {
		this.channeltype = channeltype;
	}
	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}
	public void setInsuredinfo(InsuredInfo insuredinfo) {
		this.insuredinfo = insuredinfo;
	}
	public void setProductlist(List<productinfo> productlist) {
		this.productlist = productlist;
	}
	public void setTotalpremium(String totalpremium) {
		this.totalpremium = totalpremium;
	}
	public List<productinfo> getproductlist() {
		return productlist;
	}
	public void setproductlist(List<productinfo> productlist) {
		this.productlist = productlist;
	}
	public String gettotalpremium() {
		return totalpremium;
	}
	public void settotalpremium(String totalpremium) {
		this.totalpremium = totalpremium;
	}
	@Override
	public String toString() {
		return "ApplyInfo [agentnum=" + agentnum + ", applicant=" + applicant
				+ ", applydate=" + applydate + ", applytype=" + applytype
				+ ", channelnum=" + channelnum + ", channelreginnum="
				+ channelreginnum + ", channeltype=" + channeltype
				+ ", effectivedate=" + effectivedate + ", insuredinfo="
				+ insuredinfo + ", productlist=" + productlist
				+ ", totalpremium=" + totalpremium + "]";
	}

}

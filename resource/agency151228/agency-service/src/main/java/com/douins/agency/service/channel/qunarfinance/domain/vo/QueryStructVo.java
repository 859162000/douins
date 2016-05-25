package com.douins.agency.service.channel.qunarfinance.domain.vo;

import com.douins.agency.service.channel.qunarfinance.domain.model.Header;

public class QueryStructVo {
	private Header header;
	private String policyno;
	
	public QueryStructVo(Header header,String policyno){
		this.header = header;
		this.policyno = policyno;
	}
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
}

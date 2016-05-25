package com.douins.agency.service.channel.qunarfinance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Packages {
	 private Header header;
	 private Request request;
	 
	 @XStreamAlias("Header")
	 private HeaderWdQuery headerResp;
	 @XStreamAlias("Response")
	 private Response response;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	
	public HeaderWdQuery getHeaderResp() {
		return headerResp;
	}
	public void setHeaderResp(HeaderWdQuery headerResp) {
		this.headerResp = headerResp;
	}
	@Override
	public String toString() {
		return "Packages [header=" + header + ", request=" + request +", headerResp"+ headerResp+", response=" + response + "]";
	}
	
	 
}

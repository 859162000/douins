package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Package")
public class Package {
	
	@XStreamAlias("Header")
	private Header header;
	@XStreamAlias("Response")
	private Response response;
	@XStreamAlias("Request")
	private Request request;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	
	

}

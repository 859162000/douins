package com.douins.insurance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("Package")
public class Package {
    @XStreamAlias("Header")
	private Header header;
    @XStreamAlias("Request")
	private Request request;
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
}

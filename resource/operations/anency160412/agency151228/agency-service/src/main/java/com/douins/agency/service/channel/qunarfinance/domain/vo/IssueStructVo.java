package com.douins.agency.service.channel.qunarfinance.domain.vo;

import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.channel.qunarfinance.domain.model.Payment;
import com.douins.agency.service.channel.qunarfinance.domain.model.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Description: 承保请求数据
 * @author panrui
 *
 */
@XStreamAlias("issuestructvo")
public class IssueStructVo {

	private Header header;
	private Request request;
	
	public IssueStructVo(Header header,Request request){
		this.header = header;
		this.request = request;
	}
	
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
	
}

package com.douins.agency.service.insurance.lianlife.domain.vo;

import java.util.List;

import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.insurance.lianlife.domain.model.RefundInfoItem;

public class WithdrawResponse {
	private Header header;
	private List<RefundInfoItem> listRefundInfo;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public List<RefundInfoItem> getListRefundInfo() {
		return listRefundInfo;
	}

	public void setListRefundInfo(List<RefundInfoItem> listRefundInfo) {
		this.listRefundInfo = listRefundInfo;
	} 
	
}

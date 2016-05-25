package com.douins.agency.service.insurance.lianlife.domain.vo;

import java.util.List;

import com.douins.agency.service.insurance.lianlife.domain.model.RefundPaymentItem;

public class FundInfoStructVo {
	private List<RefundPaymentItem> listRefundPaymentItem;

	public List<RefundPaymentItem> getListRefundPaymentItem() {
		return listRefundPaymentItem;
	}

	public void setListRefundPaymentItem(List<RefundPaymentItem> listRefundPaymentItem) {
		this.listRefundPaymentItem = listRefundPaymentItem;
	}
	
}

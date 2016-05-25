package com.douins.agency.service.channel.qunarfinance.domain.vo;

import com.douins.agency.service.channel.qunarfinance.domain.model.ApplyInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.channel.qunarfinance.domain.model.OrderInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.OtherInfo;

/**
 * @Description 去哪儿理财险核保数据对象
 * @author panrui
 *
 */
public class UWStructVo {

	private Header header;
	private ApplyInfo applyInfo;
	private OrderInfo orderInfo;
	private OtherInfo otherInfo;
	
	public UWStructVo(Header header,ApplyInfo applyInfo,OrderInfo orderInfo,OtherInfo otherInfo){
		this.header = header;
		this.applyInfo = applyInfo;
		this.orderInfo = orderInfo;
		this.otherInfo = otherInfo;
	}
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ApplyInfo getApplyInfo() {
		return applyInfo;
	}
	public void setApplyInfo(ApplyInfo applyInfo) {
		this.applyInfo = applyInfo;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public OtherInfo getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(OtherInfo otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	
}

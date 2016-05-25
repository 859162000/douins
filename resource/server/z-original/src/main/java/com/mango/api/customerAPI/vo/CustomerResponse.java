package com.mango.api.customerAPI.vo;

import java.util.List;

import com.mango.api.basic.vo.ResponseTransVo;

public class CustomerResponse extends ResponseTransVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4965701197265706174L;
	private List<CustomerResponseVo> CustomerList;
	public List<CustomerResponseVo> getCustomerList() {
		return CustomerList;
	}
	public void setCustomerList(List<CustomerResponseVo> customerList) {
		CustomerList = customerList;
	}

}

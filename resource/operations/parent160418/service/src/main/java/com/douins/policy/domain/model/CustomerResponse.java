package com.douins.policy.domain.model;

import java.util.List;

import com.douins.policy.domain.vo.CustomerResponseVo;
import com.douins.trans.domain.vo.ResponseTransVo;


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

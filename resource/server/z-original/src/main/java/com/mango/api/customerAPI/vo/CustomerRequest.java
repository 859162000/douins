package com.mango.api.customerAPI.vo;

import com.mango.api.basic.vo.RequestTransVo;

public class CustomerRequest extends RequestTransVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4965701197265706174L;
	private CustomerRequestVo customerVo;
	public CustomerRequestVo getCustomerVo() {
		return customerVo;
	}
	public void setCustomerVo(CustomerRequestVo customerVo) {
		this.customerVo = customerVo;
	}
	
}

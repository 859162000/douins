package com.douins.policy.domain.model;

import com.douins.policy.domain.vo.CustomerRequestVo;
import com.douins.trans.domain.vo.RequestTransVo;

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

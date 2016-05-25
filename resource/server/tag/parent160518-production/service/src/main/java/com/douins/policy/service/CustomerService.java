package com.douins.policy.service;

import java.util.List;

import com.douins.policy.domain.model.Customer;
import com.douins.trans.domain.enums.ResponseCode;
import com.mango.orm.DbOperateService;

public interface CustomerService extends DbOperateService<Customer>{
	public ResponseCode addCustomer(Customer customer) throws Exception;
	public ResponseCode delete(Customer customer) throws Exception;
	public ResponseCode modify(Customer customer,String transType)throws Exception;
	public List<Customer> findByCondition(Customer paramT);
	
}
package com.mango.fortune.policy.service;

import java.util.List;

import com.mango.fortune.policy.model.Customer;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.orm.DbOperateService;

public interface CustomerService extends DbOperateService<Customer>{
	public ResponseCode addCustomer(Customer customer) throws Exception;
	public ResponseCode delete(Customer customer) throws Exception;
	public ResponseCode modify(Customer customer,String transType)throws Exception;
	public List<Customer> findByCondition(Customer paramT);
	
}
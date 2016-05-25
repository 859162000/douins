package com.mango.api.customerAPI.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.customerAPI.vo.CustomerRequest;
import com.mango.api.customerAPI.vo.CustomerRequestVo;
import com.mango.api.customerAPI.vo.CustomerResponse;
import com.mango.api.customerAPI.vo.CustomerResponseVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.policy.model.Customer;
import com.mango.fortune.policy.service.CustomerService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/customer")
public class CustomerAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private CustomerService customerService;
	
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String getList(HttpServletRequest request) {
		CustomerResponse responseTrans=new CustomerResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			CustomerRequest trans = JsonOnlineUtils.readJson2Entity(data,CustomerRequest.class);
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			CustomerRequestVo customerVo=trans.getCustomerVo();
			String userId=customerVo.getUserId();
			if(StringUtils.isBlank(userId)){
				responseCode=ResponseCode.DATAREAD_ERROR;
			}else{
				List<Customer> list= customerService.findByCondition(customerVo);
				
				List<CustomerResponseVo> resList=new ArrayList<CustomerResponseVo>();
				if(list!=null&&list.size()>0){
					for(Customer p:list){
						CustomerResponseVo responseVo=new CustomerResponseVo();
						BeanUtils.copyProperties(p, responseVo);
						resList.add(responseVo);
					}
				}
				responseTrans.setCustomerList(resList);
				responseCode=ResponseCode.SUCCESS;
			}
		} catch (Exception e) {
			logger.error("policy getList error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		CustomerResponse responseTrans=new CustomerResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			CustomerRequest trans = JsonOnlineUtils.readJson2Entity(data,CustomerRequest.class);
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			CustomerRequestVo customerVo=trans.getCustomerVo();
			String userId=customerVo.getUserId();
			if (StringUtils.isBlank(userId)
					|| StringUtils.isBlank(customerVo.getName())
					|| StringUtils.isBlank(customerVo.getSex())
					|| StringUtils.isBlank(customerVo.getCardNo())
					|| StringUtils.isBlank(customerVo.getCardType())
					|| customerVo.getBirthday() == null) {
				responseCode = ResponseCode.DATAREAD_ERROR;
			} else{
				responseCode=customerService.addCustomer(customerVo);
				
				if(ResponseCode.SUCCESS.equals(responseCode)){
					List<CustomerResponseVo> resList=new ArrayList<CustomerResponseVo>();
					Customer customer=new Customer();
					customer.setUserId(userId);
					List<Customer> list= customerService.findByCondition(customer);
					if(list!=null&&list.size()>0){
						for(Customer p:list){
							CustomerResponseVo responseVo=new CustomerResponseVo();
							BeanUtils.copyProperties(p, responseVo);
							resList.add(responseVo);
						}
					}
					responseTrans.setCustomerList(resList);
				}
			}
		} catch (Exception e) {
			logger.error("policy getList error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		CustomerResponse responseTrans=new CustomerResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			CustomerRequest trans = JsonOnlineUtils.readJson2Entity(data,CustomerRequest.class);
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			CustomerRequestVo customerVo=trans.getCustomerVo();
			String userId=customerVo.getUserId();
			if (StringUtils.isBlank(userId)
					|| StringUtils.isBlank(customerVo.getCustomerId())) {
				responseCode = ResponseCode.DATAREAD_ERROR;
			} else{
				responseCode=customerService.delete(customerVo);
				
				if(ResponseCode.SUCCESS.equals(responseCode)){
					List<CustomerResponseVo> resList=new ArrayList<CustomerResponseVo>();
					Customer customer=new Customer();
					customer.setUserId(userId);
					List<Customer> list= customerService.findByCondition(customer);
					if(list!=null&&list.size()>0){
						for(Customer p:list){
							CustomerResponseVo responseVo=new CustomerResponseVo();
							BeanUtils.copyProperties(p, responseVo);
							resList.add(responseVo);
						}
					}
					responseTrans.setCustomerList(resList);
				}
			}
		} catch (Exception e) {
			logger.error("policy getList error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request) {
		CustomerResponse responseTrans=new CustomerResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			CustomerRequest trans = JsonOnlineUtils.readJson2Entity(data,CustomerRequest.class);
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			CustomerRequestVo customerVo=trans.getCustomerVo();
			String userId=customerVo.getUserId();
			if (StringUtils.isBlank(userId)
					|| StringUtils.isBlank(customerVo.getCustomerId())) {
				responseCode = ResponseCode.DATAREAD_ERROR;
			} else{
				responseCode=customerService.modify(customerVo,transVo.getTransType());
				
				if(ResponseCode.SUCCESS.equals(responseCode)){
					List<CustomerResponseVo> resList=new ArrayList<CustomerResponseVo>();
					Customer customer=new Customer();
					customer.setUserId(userId);
					List<Customer> list= customerService.findByCondition(customer);
					if(list!=null&&list.size()>0){
						for(Customer p:list){
							CustomerResponseVo responseVo=new CustomerResponseVo();
							BeanUtils.copyProperties(p, responseVo);
							resList.add(responseVo);
						}
					}
					responseTrans.setCustomerList(resList);
				}
			}
		} catch (Exception e) {
			logger.error("policy getList error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}

}

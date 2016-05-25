package com.mango.api.policyAPI.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.policyAPI.vo.PolicyRequest;
import com.mango.api.policyAPI.vo.PolicyRequestVo;
import com.mango.api.policyAPI.vo.PolicyResponse;
import com.mango.api.policyAPI.vo.PolicyResponseVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.policy.service.PolicyMasterService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/policy")
public class PolicyAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private PolicyMasterService policyMasterService;
	
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String getList(HttpServletRequest request) {
		PolicyResponse responseTrans=new PolicyResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			PolicyRequestVo policyVo=trans.getPolicyVo();
			String userId=policyVo.getUserId();
			if(StringUtils.isBlank(userId)){
				responseCode=ResponseCode.DATAREAD_ERROR;
			}else{
				List<PolicyResponseVo> resList=policyMasterService.findVoByCondition(policyVo);
				responseTrans.setPolicyList(resList);
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
	
	/**
	 * 获取账户信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/uw", method = RequestMethod.POST)
	public String uw(HttpServletRequest request){
		String data = request.getParameter("data");
		PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
		PolicyResponse responseTrans= policyMasterService.add2Uw(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	/**
	 * 获取账户信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public String pay(HttpServletRequest request){
		String data = request.getParameter("data");
		PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
		PolicyResponse responseTrans= policyMasterService.insure2Pay(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	
	
}

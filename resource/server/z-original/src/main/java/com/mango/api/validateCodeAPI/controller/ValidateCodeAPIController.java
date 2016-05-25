package com.mango.api.validateCodeAPI.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.basic.vo.ResponseTransVo;
import com.mango.api.validateCodeAPI.vo.ValidateCodeRequest;
import com.mango.api.validateCodeAPI.vo.ValidateCodeRequestVo;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.enums.TransType;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.user.service.UserService;
import com.mango.fortune.user.service.ValidateCodeService;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/validateCode")
public class ValidateCodeAPIController extends BasicController{
	@Autowired
	@Qualifier("validateCodeService")
	private ValidateCodeService validateCodeService;
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String send(HttpServletRequest request) {
		ResponseTransVo responseTrans=new ResponseTransVo();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			ValidateCodeRequest trans = JsonOnlineUtils.readJson2Entity(data,ValidateCodeRequest.class);
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			ValidateCodeRequestVo smsVo=trans.getValidateCodeVo();
			
			if(smsVo!=null&&StringUtils.isNotBlank(smsVo.getValidateAccount())){
				if(TransType.USERREGISTER.getValue().equals(transVo.getTransType())){
					if (userService.exitAccount(smsVo.getValidateAccount())) {
						responseCode = ResponseCode.USERREGIST_1;
					}else{
						responseCode=validateCodeService.sendMessage(smsVo.getCodeType(), smsVo.getValidateAccount(), 6);
					}
				}else{
					if (userService.exitAccount(smsVo.getValidateAccount())) {
						responseCode=validateCodeService.sendMessage(smsVo.getCodeType(), smsVo.getValidateAccount(), 6);
					}else{
						responseCode = ResponseCode.USERREGIST_2;
					}
				}
			}else{
				responseCode=ResponseCode.DATAREAD_ERROR;
			}
		} catch (Exception e) {
			logger.error("validateCode send error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String send(HttpServletRequest request) {
		ResponseTransVo responseTrans=new ResponseTransVo();
		ResponseCode responseCode=ResponseCode.SUCCESS;
		String transId="";
		try {
			String data = request.getParameter("data");
			ValidateCodeRequest trans = JsonOnlineUtils.readJson2Entity(data,ValidateCodeRequest.class);
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			} catch (Exception e) {
			logger.error("validateCode send error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}*/
	
	
}

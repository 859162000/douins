package com.douins.rest.account;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.account.domain.vo.ValidateCodeRequest;
import com.douins.account.domain.vo.ValidateCodeRequestVo;
import com.douins.account.service.UserService;
import com.douins.account.service.ValidateCodeService;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransType;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/validateCode")
public class ValidateCodeAPIController extends BasicController{
	@Autowired
	@Qualifier("validateCodeService")
	private ValidateCodeService validateCodeService;
	@Autowired
	private UserService userService;
	
	/**
	 * 验证码发送
	 * @param request
	 * @return
	 */
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
	
	@ResponseBody
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
	    logger.info("开始");
	    ResponseCode responseCode=validateCodeService.sendMessage("101", "15901967109", 6);
	    //String result = JsonOnlineUtils.bean2json(responseCode);
	    
	    return "OK";
	}
}

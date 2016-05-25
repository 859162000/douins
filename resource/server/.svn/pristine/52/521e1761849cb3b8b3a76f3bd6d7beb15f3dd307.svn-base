package com.mango.api.userAPI.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.basic.vo.ResponseTransVo;
import com.mango.api.userAPI.vo.UserRequestTrans;
import com.mango.api.userAPI.vo.UserRequestVo;
import com.mango.api.userAPI.vo.UserResponse;
import com.mango.api.userAPI.vo.UserResponseVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.user.model.User;
import com.mango.fortune.user.service.PasswordService;
import com.mango.fortune.user.service.UserService;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/user")
public class UserAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordService passwordService;
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		UserResponse reponse = new UserResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId=null;
		try {
			String data = request.getParameter("data");		
			UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
			RequestTrans requestTrans = userRequestTrans.getRequestTrans();
			transId = requestTrans.getTransId();
			User user = userRequestTrans.getUserVo();		
			responseCode = userService.login(user);	
			if(responseCode.equals(ResponseCode.SUCCESS)){
				User sUser = userService.findByKey(user.getUserId());
				UserResponseVo tUser=new UserResponseVo();
				BeanUtils.copyProperties(sUser, tUser);
				reponse.setUser(tUser);
			}
		} catch (Exception e) {
			logger.error("login error",e);
		}
		ResponseTrans responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
		reponse.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(reponse);
		logger.info(result);
		return  result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(HttpServletRequest request) {	
		UserResponse reponse = new UserResponse();	
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId=null;
		try {
			String ip = request.getRemoteAddr();
			String data = request.getParameter("data");		
			UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
			RequestTrans requestTrans = userRequestTrans.getRequestTrans();
			transId = requestTrans.getTransId();
			UserRequestVo user = userRequestTrans.getUserVo();
			responseCode = userService.regist(user, ip,user.getVerificationCode(), requestTrans.getTransType(),requestTrans.getTransChannel());
			if(responseCode.equals(ResponseCode.SUCCESS)){
				User sUser = userService.findByKey(user.getUserId());
				UserResponseVo tUser=new UserResponseVo();
				BeanUtils.copyProperties(sUser, tUser);
				reponse.setUser(tUser);
			}
		} catch (Exception e) {
			logger.error("regist error",e);
		}
		ResponseTrans responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
		reponse.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(reponse);
		logger.info(result);
		return result;
	}
	
	
	/**
	 * 方法名称: passwordInit<br>
	 * 描述：密码变更
	 * 作者: winterchen
	 * 修改日期：May 5, 20154:02:03 PM
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	public String passwordChange(HttpServletRequest request) {	
		ResponseTransVo responseTransVo = new ResponseTransVo();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId=null;
		try {
			String data = request.getParameter("data");	
			UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
			transId = userRequestTrans.getRequestTrans().getTransId();	
			responseCode = passwordService.passwordChange(userRequestTrans);
		} catch (Exception e) {
			logger.error("password error",e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);		
		responseTransVo.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(responseTransVo);
		logger.info(result);
		return result;
	}
	
	/**
	 * 修改用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request) {	
		String data = request.getParameter("data");	
		UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
		RequestTrans requestTrans = userRequestTrans.getRequestTrans();
		String transId = requestTrans.getTransId();
		UserRequestVo userVo = userRequestTrans.getUserVo();
		ResponseCode responseCode = userService.modify(userVo,requestTrans.getTransType());
	
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
		ResponseTransVo responseTransVo = new ResponseTransVo();
		responseTransVo.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(responseTransVo);
		logger.info(result);
		return result;
	}
}

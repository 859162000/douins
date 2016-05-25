package com.mango.api.userAccountAPI.controller;

import java.math.BigDecimal;
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

import com.mango.api.policyAPI.vo.PolicyRequestVo;
import com.mango.api.policyAPI.vo.PolicyResponseVo;
import com.mango.api.userAccountAPI.vo.UserAccountRequest;
import com.mango.api.userAccountAPI.vo.UserAccountRequestVo;
import com.mango.api.userAccountAPI.vo.UserAccountResponse;
import com.mango.api.userAccountAPI.vo.UserAccountResponseVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.policy.enums.PolicyStatus;
import com.mango.fortune.policy.service.PolicyMasterService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/userAccount")
public class UserAccountAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private PolicyMasterService policyMasterService;
	
	
//	@ResponseBody
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public String openApply(HttpServletRequest request) {
//		UserAccountResponse responseTrans=new UserAccountResponse();
//		ResponseCode responseCode=ResponseCode.FAILED;
//		String transId="";
//		try {
//			String data = request.getParameter("data");
//			UserAccountRequest openApplyRequest = JsonOnlineUtils.readJson2Entity(data,UserAccountRequest.class);
//			RequestTrans trans=openApplyRequest.getRequestTrans();
//			transId=trans.getTransId();
//			UserAccountRequestVo userAccount=openApplyRequest.getUserAccountVo();
//			String userId=userAccount.getUserId();
//			if(StringUtils.isBlank(userId)){
//				responseCode=ResponseCode.DATAREAD_ERROR;
//			}else{
//				responseCode= userAccountService.add(userAccount.getUserId(),trans.getTransChannel());
//			}
//			if(responseCode.equals(ResponseCode.SUCCESS)){
//				UserAccount userAccountBack= userAccountService.findOneByUserId(userId);
//				if(userAccountBack!=null){
//					UserAccountResponseVo userAccountResponse=new UserAccountResponseVo();
//					BeanUtils.copyProperties(userAccountBack, userAccountResponse);
//					responseTrans.setUserAccount(userAccountResponse);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("openApply error",e);
//		}
//		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
//		responseTrans.setResponseTrans(trans);
//		String response =JsonOnlineUtils.object2json(responseTrans);
//		logger.info(response);
//		return response;
//	}
	
	/**
	 * 获取账户信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String getUserAccount(HttpServletRequest request) {
		UserAccountResponse responseTrans=new UserAccountResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			UserAccountRequest openApplyRequest = JsonOnlineUtils.readJson2Entity(data,UserAccountRequest.class);
			RequestTrans trans=openApplyRequest.getRequestTrans();
			transId=trans.getTransId();
			UserAccountRequestVo userAccount=openApplyRequest.getUserAccountVo();
			String userId=userAccount.getUserId();
			if(StringUtils.isBlank(userId)){
				responseCode=ResponseCode.DATAREAD_ERROR;
			}else{
				UserAccountResponseVo userAccountResponse=new UserAccountResponseVo();
				UserAccount userAccountBack= userAccountService.findOneByUserId(userId);
				if(userAccountBack!=null){
					BeanUtils.copyProperties(userAccountBack, userAccountResponse);
					userAccountResponse.setTotalIncome(this.calcIncome(userId));	
					responseTrans.setUserAccount(userAccountResponse);
					responseCode=ResponseCode.SUCCESS;
				}else{
					responseCode=ResponseCode.USERNOTOPENERROR;
				}
			}
		} catch (Exception e) {
			logger.error("getUserAccount error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	/**
	 * 充值或提现
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public String pay(HttpServletRequest request) {
		UserAccountResponse responseTrans=new UserAccountResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			UserAccountRequest openApplyRequest = JsonOnlineUtils.readJson2Entity(data,UserAccountRequest.class);
			RequestTrans trans=openApplyRequest.getRequestTrans();
			transId=trans.getTransId();
			UserAccountRequestVo userAccount=openApplyRequest.getUserAccountVo();
			String userId=userAccount.getUserId();
			String userAccountId=userAccount.getUserAccountId();
			BigDecimal applyAmount=userAccount.getApplyAmount();
			String detailType=userAccount.getDetailType();
			if(StringUtils.isBlank(userId)
					||StringUtils.isBlank(userAccountId)
					||applyAmount==null
					||StringUtils.isBlank(detailType)){
				responseCode=ResponseCode.DATAREAD_ERROR;
			}else{
				responseCode= userAccountService.pay(userAccount,trans.getTransChannel());
				if(responseCode.equals(ResponseCode.SUCCESS)){
					UserAccount userAccountBack= userAccountService.findOneByUserId(userId);
					if(userAccountBack!=null){
						UserAccountResponseVo userAccountResponse=new UserAccountResponseVo();
						BeanUtils.copyProperties(userAccountBack, userAccountResponse);
						responseTrans.setUserAccount(userAccountResponse);
					}
				}
			}
		} catch (Exception e) {
			logger.error("pay error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	public BigDecimal calcIncome(String userId) {
		BigDecimal income = new BigDecimal("0");

		PolicyRequestVo qPolicy = new PolicyRequestVo();
		qPolicy.setUserId(userId);
		qPolicy.setStatus(PolicyStatus.INSURESUCCESS.getValue());
		List<PolicyResponseVo> List = policyMasterService
				.findVoByCondition(qPolicy);
		if (List != null && !List.isEmpty()) {
			for (PolicyResponseVo p : List) {
				income = income.add(p.getNowRevenue());
			}
		}
		return income;
	}
}

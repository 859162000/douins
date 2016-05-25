package com.douins.rest.account;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.account.domain.model.UserPayAccount;
import com.douins.account.domain.vo.UserPayAccountRequest;
import com.douins.account.domain.vo.UserPayAccountResponse;
import com.douins.account.domain.vo.UserPayAccountVo;
import com.douins.account.service.UserPayAccountService;
import com.douins.bank.domain.enums.BankType;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.pay.domain.enums.PayBankCardType;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/userPayAccount")
public class UserPayAccountAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserPayAccountService userPayAccountService;
	
	/**
	 * 绑卡并开户
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		ResponseCode responseCode = ResponseCode.FAILED;
		UserPayAccountResponse payAccountResponse = new UserPayAccountResponse();
		String transId = "";
		try {
			String data = request.getParameter("data");
			UserPayAccountRequest userBankRequest = JsonOnlineUtils.readJson2Entity(data,UserPayAccountRequest.class);
			RequestTrans rt = userBankRequest.getRequestTrans();
			transId = rt.getTransId();
			UserPayAccount userPayAccountVo= userBankRequest.getPayAccountVo();
			if(StringUtils.isBlank(userPayAccountVo.getUserId())){
				responseCode = ResponseCode.DATAREAD_ERROR;
			}else{
				responseCode= userPayAccountService.add4Api(userBankRequest.getPayAccountVo(),rt.getTransChannel());
				if(responseCode.equals(ResponseCode.SUCCESS)){
					UserPayAccount payAccount = new UserPayAccount();
					payAccount.setUserId(userPayAccountVo.getUserId());
					List<UserPayAccountVo> accountlist = this.findByCondition4Api(payAccount);
					payAccountResponse.setAccountList(accountlist);
				}
			}
		} catch (Exception e) {
			logger.error("userPaAccount add error",e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		payAccountResponse.setResponseTrans(responseTrans);
		String response =JsonOnlineUtils.object2json(payAccountResponse);
		logger.info(response);
		return response;
	}
	
	/**
	 * 查询绑定的银行卡
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String get(HttpServletRequest request) {
		ResponseCode responseCode = ResponseCode.FAILED;
		UserPayAccountResponse payAccountResponse = new UserPayAccountResponse();
		String transId = "";
		try {
			String data = request.getParameter("data");
			UserPayAccountRequest userBankRequest = JsonOnlineUtils.readJson2Entity(data,UserPayAccountRequest.class);
			RequestTrans rt = userBankRequest.getRequestTrans();
			transId = rt.getTransId();
			UserPayAccount userPayAccountVo= userBankRequest.getPayAccountVo();
			if(StringUtils.isBlank(userPayAccountVo.getUserId())){
				responseCode = ResponseCode.DATAREAD_ERROR;
			}else{
				responseCode = ResponseCode.SUCCESS;
				UserPayAccount payAccount = new UserPayAccount();
				payAccount.setUserId(userPayAccountVo.getUserId());
				List<UserPayAccountVo> accountlist = this.findByCondition4Api(payAccount);
				payAccountResponse.setAccountList(accountlist);
			}
		} catch (Exception e) {
			logger.error("userPayAccount get error",e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		payAccountResponse.setResponseTrans(responseTrans);
		String response =JsonOnlineUtils.object2json(payAccountResponse);
		logger.info(response);
		return response;
	}
	
	/**
	 * 变更银行卡
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public String change(HttpServletRequest request) {
		ResponseCode responseCode = ResponseCode.FAILED;
		UserPayAccountResponse payAccountResponse = new UserPayAccountResponse();
		String transId = "";
		try {
			String data = request.getParameter("data");
			UserPayAccountRequest userBankRequest = JsonOnlineUtils.readJson2Entity(data,UserPayAccountRequest.class);
			RequestTrans rt = userBankRequest.getRequestTrans();
			transId = rt.getTransId();
			UserPayAccount userPayAccountVo= userBankRequest.getPayAccountVo();
			if(StringUtils.isBlank(userPayAccountVo.getUserId())){
				responseCode = ResponseCode.DATAREAD_ERROR;
			}else{
				responseCode= userPayAccountService.change4Api(userPayAccountVo);
				if(responseCode.equals(ResponseCode.SUCCESS)){
					UserPayAccount payAccount = new UserPayAccount();
					payAccount.setUserId(userPayAccountVo.getUserId());
					List<UserPayAccountVo> accountlist = this.findByCondition4Api(payAccount);
					payAccountResponse.setAccountList(accountlist);
				}
			}
		} catch (Exception e) {
			logger.error("userPaAccount change error",e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		payAccountResponse.setResponseTrans(responseTrans);
		String response =JsonOnlineUtils.object2json(payAccountResponse);
		logger.info(response);
		return response;
	}
	
	/**
	 * 验证支付密码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validate(HttpServletRequest request) {
		ResponseCode responseCode = ResponseCode.FAILED;
		ResponseTransVo responseTransVo = new ResponseTransVo();
		String transId = "";
		try {
			String data = request.getParameter("data");
			UserPayAccountRequest userBankRequest = JsonOnlineUtils.readJson2Entity(data,UserPayAccountRequest.class);
			RequestTrans rt = userBankRequest.getRequestTrans();
			transId = rt.getTransId();
			UserPayAccount userPayAccountVo= userBankRequest.getPayAccountVo();
			if(StringUtils.isBlank(userPayAccountVo.getUserId())){
				responseCode = ResponseCode.DATAREAD_ERROR;
			}else{
				responseCode= userPayAccountService.validate(userPayAccountVo);
			}
		} catch (Exception e) {
			logger.error("validate paypassword error",e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		responseTransVo.setResponseTrans(responseTrans);
		String response =JsonOnlineUtils.object2json(responseTransVo);
		logger.info(response);
		return response;
	}
	
	private List<UserPayAccountVo> findByCondition4Api(UserPayAccount bank){
		List<UserPayAccount> accountlist = userPayAccountService.findByCondition(bank);
		List<UserPayAccountVo> resplist=new ArrayList<UserPayAccountVo>();
		if(accountlist!=null&&!accountlist.isEmpty()){
			for(UserPayAccount pAccount:accountlist){
				UserPayAccountVo uv=new UserPayAccountVo();
				BeanUtils.copyProperties(pAccount, uv);
				if(uv.getAccountType().equals(PayBankCardType.BANKCARD.getValue())){
					BankType bt1=  BankType.getBankByCode(uv.getBankCode());
					uv.setEveryLimit(bt1.getEveryLimit());
					uv.setDailyLimit(bt1.getDailyLimit());
				}
				resplist.add(uv);
			}
		}
		return resplist;
	}
	
}

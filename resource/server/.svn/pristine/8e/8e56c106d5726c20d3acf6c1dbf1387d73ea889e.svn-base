package com.douins.rest.account;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.vo.UserAccountRequest;
import com.douins.account.domain.vo.UserAccountRequestVo;
import com.douins.account.domain.vo.UserAccountResponse;
import com.douins.account.domain.vo.UserAccountResponseVo;
import com.douins.account.service.UserAccountService;
import com.douins.bank.service.iml.NYBankService;
import com.douins.common.Api;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponseVo;
import com.douins.policy.service.PolicyMasterService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/userAccount")
//@Transactional
public class UserAccountAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private PolicyMasterService policyMasterService;
	@Autowired
//	private GFBankService GFBankService;
	private NYBankService bankService;
	
	/**
	 * 开户
	 * @param request
	 * @return
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@Transactional
	public String openApply(HttpServletRequest request) {
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
				responseCode= userAccountService.add(userAccount.getUserId(),trans.getTransChannel());
			}
			if(responseCode.equals(ResponseCode.SUCCESS)){
				UserAccount userAccountBack= userAccountService.findOneByUserId(userId);
				if(userAccountBack!=null){
					UserAccountResponseVo userAccountResponse=new UserAccountResponseVo();
					BeanUtils.copyProperties(userAccountBack, userAccountResponse);
					responseTrans.setUserAccount(userAccountResponse);
				}
			}
		} catch (Exception e) {
			logger.error("openApply error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	/**
	 * 查询账户信息
	 * @param request
	 * @return
	 */
	
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String getUserAccount(HttpServletRequest request) {
		UserAccountResponse responseTrans=new UserAccountResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			
			System.out.println("查询账户信息参数:"+data);
			String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);
			UserAccountRequest openApplyRequest = JsonOnlineUtils.readJson2Entity(data,UserAccountRequest.class);
			RequestTrans trans=openApplyRequest.getRequestTrans();
			transId=trans.getTransId();
			UserAccountRequestVo userAccount=openApplyRequest.getUserAccountVo();
			String userId=userAccount.getUserId();
			if(StringUtils.isBlank(userId)){
				responseCode=ResponseCode.DATAREAD_ERROR;
			}else{
				ResponseCode queryAccount = bankService.queryAccount(token);
				UserAccountResponseVo userAccountResponse=new UserAccountResponseVo();
				UserAccount userAccountBack= userAccountService.findOneByUserId(userId);
				System.out.println("userAccountBack------:"+userAccountBack.toString());
				
				if(userAccountBack!=null){
					BeanUtils.copyProperties(userAccountBack, userAccountResponse);
					UserAccountResponseVo calcIncome = this.calcIncome(userId, userAccountResponse);
					
					System.out.println("calcIncome------:"+calcIncome.toString());
					
					//userAccountResponse.setTotalIncome(calcIncome(userId).get(0));	      // 当前累计收益🉐
					//userAccountResponse.setYesDayIncome(calcIncome(userId).get(1));       // 昨日总收益
					responseTrans.setUserAccount(calcIncome);
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
		 System.out.println("serAccountAPI--result------:"+response);
		
		logger.info(response);
		return response;
	}
	
	/**
	 * 充值或提现
	 * @param request
	 * @return
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
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
	
	/**
	 * 计算当前总收益和昨日总收益
	 * @param userId   // 用户 ID
	 * @return
	 */
	public UserAccountResponseVo calcIncome(String userId, UserAccountResponseVo userAccountResponse) {
		BigDecimal income = new BigDecimal("0");
		BigDecimal yesIncome = new BigDecimal("0");
		BigDecimal amount = new BigDecimal(0.0);
		
		
		PolicyRequestVo qPolicy = new PolicyRequestVo();
		qPolicy.setUserId(userId);
		qPolicy.setStatus(PolicyStatus.INSURESUCCESS.getValue());
//		List<PolicyResponseVo> List = policyMasterService.findVoByCondition(qPolicy);
		List<PolicyResponseVo> policyResponseVos = policyMasterService.findVoByCondition2(qPolicy);
		if (policyResponseVos != null && !policyResponseVos.isEmpty()) {
		    int loanCount = 0;
			for (PolicyResponseVo p : policyResponseVos) {
				income = income.add(p.getAccuIncome()==null?new BigDecimal(0):p.getAccuIncome());
				yesIncome = yesIncome.add(p.getYesDayEarn()==null?new BigDecimal(0):p.getYesDayEarn());
			//	System.out.println("-------昨日收益："+p.getYesDayEarn().toString());
				
				amount = amount.add(p.getTotalPrem());
				if(p.getStatus().equals(PolicyStatus.LOANING.getValue())){
				    loanCount++;        // 已贷款的保单
				}
			}
			userAccountResponse.setPolicyNum(policyResponseVos.size());       // 保单份数
			userAccountResponse.setLoanpolicyNum(loanCount);     // 已贷款的保单总数
			
			//System.out.println("累计收益income:-----:"＋income.toString());
			userAccountResponse.setYesDayIncome(yesIncome);//昨日总收益
			userAccountResponse.setTotalIncome(income);//累计收益   totalIncome=9038.14520548, yesDayIncome=9038.00
			userAccountResponse.setPolicyValue(amount);//总保费
		}
		return userAccountResponse;
	}
}

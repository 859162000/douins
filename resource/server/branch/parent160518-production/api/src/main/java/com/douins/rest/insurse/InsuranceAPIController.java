package com.douins.rest.insurse;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.account.domain.model.UserAccount;
import com.douins.account.service.UserAccountService;
import com.douins.bank.service.BankServiceIFC;
import com.douins.common.Api;
import com.douins.common.params.WebUseConstant;
import com.douins.common.util.DateTimeUtils;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequest;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.policy.service.PolicyManageService;
import com.douins.policy.service.Policyservice;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.CanclePolicyRequest;
import com.douins.trans.domain.vo.CanclePolicyResponse;
import com.mango.core.logger.SimpleLogger;

@Controller
@Scope("prototype")
@RequestMapping("/insurance/la")
@Transactional

public class InsuranceAPIController {
	/**
	 * 点立即购买---走核保
	 * @param request
	 */
	private static SimpleLogger logger =SimpleLogger.getLogger(InsuranceAPIController.class);
	
	@Autowired
	private PolicyManageService policyManageService;
	
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private Policyservice<PolicyMaster> policyservice;
	@Resource(name = "nyBankService")
	private BankServiceIFC service;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value ="doUW",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	@Api(token = true)
	public String doUW(HttpServletRequest request) {
		PolicyResponse policyResponse =  new PolicyResponse();

		ResponseCode responseCode=ResponseCode.FAILED;
		ResponseTrans responseParams = new ResponseTrans();

	  try {
			String data = request.getParameter("data");
			PolicyRequest policyRequest = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
			//主动更同步用户账户余额
			service.queryAccount(policyRequest.getAccessToken());
			logger.info("参数data-----:"+data);
			//1.核保
			String chanalFlowNo = System.currentTimeMillis()+""+ramdom4Digate()+""+ramdom4Digate();
			PolicyRequestVo policyVo = policyRequest.getPolicyVo();
			policyVo.setChanlFlowNo(chanalFlowNo);
			policyRequest.setPolicyVo(policyVo);
			BigDecimal totalPrem = policyRequest.getPolicyVo().getTotalPrem();
			String userId = policyVo.getUserId();
			UserAccount userAccount = userAccountService.findOneByUserId(userId);
			// totalPrem > userAccount.getAccountBalance()
			if(totalPrem.compareTo(userAccount.getAccountBalance())>0){
				responseCode =ResponseCode.USERBALANCEERROR;
				responseParams = new ResponseTrans(responseCode.getValue(), responseCode.getName(), chanalFlowNo);
			}else{
				policyResponse = policyManageService.processUnderwrite(policyRequest);
				ResponseTrans responseParams1 = (ResponseTrans)policyResponse.getResponseTrans();
				if(ResponseCode.SUCCESS.getValue().equals(responseParams1.getResponseCode())){
					//UserAccount userAccount = userAccountService.findOneByUserId(policyRequest.getPolicyVo().getUserId());
					String virtualAccountId = userAccount.getVirtualAccountId();
					String mAmt = totalPrem==null||"".equals(totalPrem)?"0.00":totalPrem+".00";
					//String url ="http://dev.douins.com/api/bank/ny/callback/payinfo";
					//String url ="http://api.douins.com/api/bank/ny/callback/payinfo";
					String url =WebUseConstant.PAY_CALLBACK_URL;
					//String mRcvAcctTypeArray = "3";//3－－－代表企业账户
					String mRcvAcctTypeArray = WebUseConstant.MRCV_ACCT_TYPE_ARRAY;//3－－－代表企业账户
					//String mRcvAcctNoArray = "999001270040012";
					String mRcvAcctNoArray = WebUseConstant.RECIVE_BANK_ACCOUNT;
					
					//String mRcvAcctNoArray = "96000127005010006";
					String mRcvAmtArray = mAmt;
					//String mRcvAbsArray = "payment of premium｜支付保费";
					String mRcvAbsArray =DateTimeUtils.formateDateTime(new Date())+"-"+WebUseConstant.MRCV_ABS_ARRAY;
					//String chanlNo = "PTP0000009";
					String chanlNo = WebUseConstant.CHANL_NO;
				    String acctType = WebUseConstant.ACCT_TYPE ; // 支付类型
		            String projCode = WebUseConstant.PROJ_CODE ;// 项目编号 暂时写死
					
					String signMsg = chanlNo+"|"+virtualAccountId+"|"+acctType+"|"+projCode+"|"+ chanalFlowNo+"|"+mAmt+"|"+url;
					
					String sign = service.signForRequest(signMsg);
					
					//String h5Url = "https://one.gdnybank.com/moneymanager/httpacc/H5/ptp_single_pay.html"+"?" +"chanlNo="+chanlNo + "&"
							String h5Url = WebUseConstant.PAY_URL+"?" +"chanlNo="+chanlNo + "&"//网关不带httpacc
		                    + "acctNoId="+virtualAccountId+"&"
		                    + "acctType="+acctType +"&"
		                    + "projCode="+projCode +"&"
		                    + "chanlFlowNo="+chanalFlowNo +"&"
		                    + "amt=" + mAmt +"&"
		                    + "url=" + url+"&"
		                    + "rcvAcctTypeArray=" + mRcvAcctTypeArray +"&"
		                    + "rcvAcctNoArray=" + mRcvAcctNoArray +"&"
		                    + "rcvAmtArray=" + mRcvAmtArray +"&"
		                    + "rcvAbsArray=" + URLEncoder.encode(mRcvAbsArray,"UTF-8") +"&"
		                    + "signMsg=" +sign;
					 //String encode = URLEncoder.encode(h5Url,"utf-8");
				   	 policyResponse.setNyCallBackUrl(h5Url);
					 responseCode=ResponseCode.SUCCESS;
					 responseParams  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), chanalFlowNo);
					
				}else{
					 responseParams  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), chanalFlowNo);
				}

				logger.info("响应信息-----:"+data);
			
			}
			policyResponse.setResponseTrans(responseParams);
			String str = JsonOnlineUtils.object2json(policyResponse);
			logger.info(str);
			return str;
			
	  }catch (Exception e) {
			logger.error("核保失败！", e);
			return JsonOnlineUtils.object2json(policyResponse);
		}
}
	

	public  String ramdom4Digate() {
		Random  random = new Random();
		int nextInt = random.nextInt(1000);
		if(nextInt<10){
			return "000"+nextInt;
		}else if(nextInt<100){
			return "00"+nextInt;
		}else if(nextInt<1000){
			return "0"+nextInt;
		}else{
			return ""+nextInt;
		}
	}
	
	/**
	 * 承保并保存单承保
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping("sure/pay")
	@Transactional
	@Deprecated
	public String pay(HttpServletRequest request){
		PolicyResponse policyResponse = new PolicyResponse();
		try{
			String data = request.getParameter("data");
			PolicyRequest policyRequest = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
			policyResponse =policyManageService.processInsuredAndPay(policyRequest);
			String response =JsonOnlineUtils.object2json(policyResponse);
			logger.info(response);
			return response;
		} catch (Exception e) {
			logger.error("承保失败！", e);
			return JsonOnlineUtils.object2json(policyResponse);
		}
		
		
	}
	
	

	

	/**
	 * 退保
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping("cancel/policy")
	@Transactional
	public String cancelPolicy(HttpServletRequest request){
		String response="";
		try {
			String data = request.getParameter("data");
			CanclePolicyRequest canclePolicyRequest = JsonOnlineUtils.readJson2Entity(data,CanclePolicyRequest.class);
			PolicyResult policyResult =policyManageService.canclePolicy(canclePolicyRequest);
			CanclePolicyResponse policyResponse = new CanclePolicyResponse();
			ResponseTrans responseTrans= new ResponseTrans();
			ResponseCode responseCode=ResponseCode.FAILED;
			if(policyResult.isSuccess()){
			    responseCode=ResponseCode.SUCCESS;
				responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), canclePolicyRequest.getRequestTrans().getTransId());
			}else{
				responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(),  canclePolicyRequest.getRequestTrans().getTransId());
			}
			policyResponse.setResponseTrans(responseTrans);
			policyResponse.setPolicyResult(policyResult);
			response =JsonOnlineUtils.object2json(policyResponse);
			logger.info(response);
			
		} catch (Exception e) {
			logger.error("退保失败",e);
		}
		
		return response;
	}

	
	/**
	 * 保单查询接口
	 * @param request
	 * @return
	 * @throws Exception 
	 */
//	@Api(token = true)
//	@ResponseBody
//	@RequestMapping("query/policy")
//	@Transactional
//	public String query(HttpServletRequest request){
//		String response="";
//		try {
//			String data = request.getParameter("data");
//			QueryPolicyRequest queryPolicyRequest = JsonOnlineUtils.readJson2Entity(data,QueryPolicyRequest.class);
//			PolicyResult policyResult =policyManageService.queryPolicy(queryPolicyRequest);
//			QueryPolicyResponse policyResponse = new QueryPolicyResponse();
//			ResponseTrans responseTrans= new ResponseTrans();
//			ResponseCode responseCode=ResponseCode.FAILED;
//			if(policyResult.isSuccess()){
//			    responseCode=ResponseCode.SUCCESS;
//				responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), canclePolicyRequest.getRequestTrans().getTransId());
//			}else{
//				responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(),  canclePolicyRequest.getRequestTrans().getTransId());
//			}
//			policyResponse.setResponseTrans(responseTrans);
//			policyResponse.setPolicyResult(policyResult);
//			response =JsonOnlineUtils.object2json(policyResponse);
//			logger.info(response);
//			
//		} catch (Exception e) {
//			logger.error("保单查询失败",e);
//		}
//		
//		return response;
//	}

}

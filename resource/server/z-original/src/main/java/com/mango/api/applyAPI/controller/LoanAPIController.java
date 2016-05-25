package com.mango.api.applyAPI.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.applyAPI.vo.LoanApplyVo;
import com.mango.api.applyAPI.vo.LoanInfoResponse;
import com.mango.api.applyAPI.vo.LoanInfoVo;
import com.mango.api.applyAPI.vo.LoanRequest;
import com.mango.api.applyAPI.vo.ApplyResponse;
import com.mango.api.applyAPI.vo.LoanResponse;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.apply.enums.ApplyType;
import com.mango.fortune.apply.model.ApplyInfo;
import com.mango.fortune.apply.model.LoanApply;
import com.mango.fortune.apply.service.BaseApplyInfoService;
import com.mango.fortune.apply.service.LoanApplyService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/loan")
public class LoanAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	@Qualifier("loanApplyService")
	private LoanApplyService loanApplyService;
	
	@Autowired
	@Qualifier("loanApplyService")
	private BaseApplyInfoService  baseApplyInfoService;
	
	@ResponseBody
	@RequestMapping(value = "/loan", method = RequestMethod.POST)
	public String getLoanInfo(HttpServletRequest request) {
		String data = request.getParameter("data");
		String transId = "";
		ResponseCode responseCode = ResponseCode.FAILED;
		LoanInfoResponse loanResp = new LoanInfoResponse();
		try {
			LoanRequest loanRequest = JsonOnlineUtils.readJson2Entity(data,LoanRequest.class);
			if (loanRequest != null) {
				RequestTrans rt = loanRequest.getRequestTrans();
				transId = rt.getTransId();
				LoanInfoVo loanInfoVo = loanApplyService.getLoanInfo(loanRequest.getLoanApply().getPolicyId());
				if(loanInfoVo == null){
					responseCode = ResponseCode.POLICYISNOTEXISTS;;
				}else{
					responseCode = ResponseCode.SUCCESS;
					loanResp.setLoanInfoVo(loanInfoVo);
				}
			}else{
				responseCode = ResponseCode.DATAREAD_ERROR;
			}
		} catch (Exception e) {
			responseCode = ResponseCode.FAILED;
			logger.error("get loan error", e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		loanResp.setResponseTrans(responseTrans);
		String response = JsonOnlineUtils.object2json(loanResp);
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request) {
		String data = request.getParameter("data");
		String transId = "";
		ResponseCode responseCode = ResponseCode.FAILED;
		ApplyResponse loanResp = new ApplyResponse();
		try {
			LoanRequest loanRequest = JsonOnlineUtils.readJson2Entity(data,LoanRequest.class);
			if (loanRequest != null && loanRequest.getLoanApply() != null) {
				RequestTrans rt = loanRequest.getRequestTrans();
				transId = rt.getTransId();
				ApplyInfo applyInfo = loanRequest.getLoanApply();
				applyInfo.setApplyType(ApplyType.LOAN.getValue());
				ApplyResult applyResult = baseApplyInfoService.doApply(applyInfo);
				if(applyResult.isSuccess()){
					responseCode = ResponseCode.SUCCESS;
					loanResp.setApplyInfo(applyResult.getApplyInfo());
				}else{
					responseCode = ResponseCode.getEnumByValue(applyResult.getResultCode());
				}
			}else{
				responseCode = ResponseCode.DATAREAD_ERROR;
			}
		} catch (Exception e) {
			responseCode = ResponseCode.FAILED;
			logger.error("save loan error", e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		loanResp.setResponseTrans(responseTrans);
		String response = JsonOnlineUtils.object2json(loanResp);
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String get(HttpServletRequest request) {
		String data = request.getParameter("data");
		String transId = "";
		ResponseCode responseCode = ResponseCode.FAILED;
		LoanResponse loanResp = new LoanResponse();
		try {
			LoanRequest loanRequest = JsonOnlineUtils.readJson2Entity(data,LoanRequest.class);
			if (loanRequest != null) {
				RequestTrans rt = loanRequest.getRequestTrans();
				transId = rt.getTransId();
				LoanApply loanApply = loanRequest.getLoanApply();
				List<LoanApplyVo> list = loanApplyService.getMyLoanInfo4Api(loanApply);
				
				loanResp.setLoanApplyVoList(list);
				responseCode = ResponseCode.SUCCESS;
			}else{
				responseCode = ResponseCode.DATAREAD_ERROR;
			}
		} catch (Exception e) {
			responseCode = ResponseCode.FAILED;
			logger.error("get loan error", e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		loanResp.setResponseTrans(responseTrans);
		String response = JsonOnlineUtils.object2json(loanResp);
		return response;
	}
}
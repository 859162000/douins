package com.douins.rest.apply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.apply.domain.enums.ApplyType;
import com.douins.apply.domain.model.ApplyInfo;
import com.douins.apply.domain.model.LoanApply;
import com.douins.apply.domain.vo.ApplyResponse;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.apply.domain.vo.LoanApplyVo;
import com.douins.apply.domain.vo.LoanInfoResponse;
import com.douins.apply.domain.vo.LoanInfoVo;
import com.douins.apply.domain.vo.LoanRequest;
import com.douins.apply.domain.vo.LoanResponse;
import com.douins.apply.service.BaseApplyInfoService;
import com.douins.apply.service.LoanApplyService;
import com.douins.common.Api;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/loan")
public class LoanAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	@Qualifier("loanApplyService")
	private LoanApplyService loanApplyService;
	
	@Autowired
	@Qualifier("loanApplyService")
	private BaseApplyInfoService  baseApplyInfoService;
	
	/**
	 * 申请贷款
	 * @param request
	 * @return
	 */
	@Api(token = true)
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
					responseCode = ResponseCode.POLICYISNOTEXISTS;
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
	
	/**
	 *确认贷款
	 * @param request
	 * @return
	 */
	@Api(token = true)
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
	
	/**
	 * 我的保单贷款：查询用户贷款信息
	 * @param request
	 * @return
	 */
	@Api(token = true)
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
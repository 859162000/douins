package com.mango.api.applyAPI.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.applyAPI.vo.ApplyResponse;
import com.mango.api.applyAPI.vo.RepayRequest;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.apply.enums.ApplyType;
import com.mango.fortune.apply.model.ApplyInfo;
import com.mango.fortune.apply.service.BaseApplyInfoService;
import com.mango.fortune.apply.service.RepayApplyService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/repay")
public class RepayAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	@Qualifier("repayApplyService")
	private RepayApplyService repayApplyService;
	
	@Autowired
	@Qualifier("repayApplyService")
	private BaseApplyInfoService  baseApplyInfoService;
	
	
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request) {
		String data = request.getParameter("data");
		String transId = "";
		ResponseCode responseCode = ResponseCode.FAILED;
		ApplyResponse loanResp = new ApplyResponse();
		try {
			RepayRequest repayRequest = JsonOnlineUtils.readJson2Entity(data,RepayRequest.class);
			if (repayRequest != null && repayRequest.getRepayApply() != null) {
				RequestTrans rt = repayRequest.getRequestTrans();
				transId = rt.getTransId();
				ApplyInfo applyInfo = repayRequest.getRepayApply();
				applyInfo.setApplyType(ApplyType.REPAY.getValue());
				
				ApplyResult applyResult = baseApplyInfoService.doApply(applyInfo);
				if(applyResult.isSuccess()){
					responseCode = ResponseCode.SUCCESS;
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
}
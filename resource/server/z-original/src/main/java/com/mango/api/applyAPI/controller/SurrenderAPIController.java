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
import com.mango.api.applyAPI.vo.SurrenderInfoResponse;
import com.mango.api.applyAPI.vo.SurrenderInfoVo;
import com.mango.api.applyAPI.vo.SurrenderRequest;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.apply.enums.ApplyType;
import com.mango.fortune.apply.model.ApplyInfo;
import com.mango.fortune.apply.service.BaseApplyInfoService;
import com.mango.fortune.apply.service.SurrenderApplyService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/surrender")
public class SurrenderAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());

	@Autowired
	@Qualifier("surrenderApplyService")
	private SurrenderApplyService surrenderApplyService;
	
	@Autowired
	@Qualifier("surrenderApplyService")
	private BaseApplyInfoService  baseApplyInfoService;
	
	@ResponseBody
	@RequestMapping(value = "/surrender", method = RequestMethod.POST)
	public String surrender(HttpServletRequest request) {
		String data = request.getParameter("data");
		String transId = "";
		ResponseCode responseCode = ResponseCode.FAILED;
		SurrenderInfoResponse sivResp = new SurrenderInfoResponse();
		try {
			SurrenderRequest surrenderRequest = JsonOnlineUtils.readJson2Entity(data,SurrenderRequest.class);
			if (surrenderRequest != null && surrenderRequest.getSurrenderApply() != null) {
				RequestTrans rt = surrenderRequest.getRequestTrans();
				transId = rt.getTransId();
				SurrenderInfoVo surrenderInfoVo = surrenderApplyService.getSurrenderInfo(surrenderRequest.getSurrenderApply());
				if(surrenderInfoVo == null){
					responseCode = ResponseCode.POLICYISNOTEXISTS;;
				}else{
					responseCode = ResponseCode.SUCCESS;
					sivResp.setSurrenderInfoVo(surrenderInfoVo);
				}
			}else{
				responseCode = ResponseCode.DATAREAD_ERROR;
			}
		} catch (Exception e) {
			responseCode = ResponseCode.FAILED;
			logger.error("get surrender error", e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		sivResp.setResponseTrans(responseTrans);
		String response = JsonOnlineUtils.object2json(sivResp);
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
			SurrenderRequest surrenderRequest = JsonOnlineUtils.readJson2Entity(data,SurrenderRequest.class);
			if (surrenderRequest != null && surrenderRequest.getSurrenderApply() != null) {
				RequestTrans rt = surrenderRequest.getRequestTrans();
				transId = rt.getTransId();
				ApplyInfo applyInfo = surrenderRequest.getSurrenderApply();
				applyInfo.setApplyType(ApplyType.SURRENDER.getValue());
				ApplyResult applyResult = baseApplyInfoService.doApply(surrenderRequest.getSurrenderApply());
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
			logger.error("save surrender error", e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		loanResp.setResponseTrans(responseTrans);
		String response = JsonOnlineUtils.object2json(loanResp);
		return response;
	}
}
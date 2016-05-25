package com.douins.rest.apply;

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
import com.douins.apply.domain.vo.ApplyResponse;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.apply.domain.vo.SurrenderInfoResponse;
import com.douins.apply.domain.vo.SurrenderInfoVo;
import com.douins.apply.domain.vo.SurrenderRequest;
import com.douins.apply.service.BaseApplyInfoService;
import com.douins.apply.service.SurrenderApplyService;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/surrender")
public class SurrenderAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());

	@Autowired
	@Qualifier("surrenderApplyService")
	private SurrenderApplyService surrenderApplyService;
	
	@Autowired
	@Qualifier("surrenderApplyService")
	private BaseApplyInfoService  baseApplyInfoService;
	
	/**
	 * 申请退保
	 * @param request
	 * @return
	 */
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
					responseCode = ResponseCode.POLICYISNOTEXISTS;
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
	
	/**
	 * 确认退保
	 * @param request
	 * @return
	 */
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
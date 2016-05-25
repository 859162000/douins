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
import com.douins.apply.domain.vo.RepayRequest;
import com.douins.apply.service.BaseApplyInfoService;
import com.douins.apply.service.RepayApplyService;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/repay")
public class RepayAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	@Qualifier("repayApplyService")
	private RepayApplyService repayApplyService;
	
	@Autowired
	@Qualifier("repayApplyService")
	private BaseApplyInfoService  baseApplyInfoService;
	
	
	/**
	 * 还款申请
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
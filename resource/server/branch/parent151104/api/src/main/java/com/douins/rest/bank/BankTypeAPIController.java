package com.douins.rest.bank;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.bank.domain.vo.BankTypeRequest;
import com.douins.bank.domain.vo.BankTypeResponse;
import com.douins.bank.domain.vo.BankTypeResponseVo;
import com.douins.bank.service.BankTypeService;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.logger.SimpleLogger;

@Controller
@Scope("prototype")
@RequestMapping("/bankType")
public class BankTypeAPIController {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	@Autowired
	private BankTypeService bankTypeService;
	
	/**
	 * 银行列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String get(HttpServletRequest request) {	
		BankTypeResponse bankTypeResponse=new BankTypeResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			BankTypeRequest bankTypeRequest = JsonOnlineUtils.readJson2Entity(data,BankTypeRequest.class);
			transId=bankTypeRequest.getRequestTrans().getTransId();
			List<BankTypeResponseVo> banklist = bankTypeService.getBankAPI(bankTypeRequest);
			bankTypeResponse.setBanklist(banklist);
			responseCode=ResponseCode.SUCCESS;
		} catch (Exception e) {
			logger.error("bankType list error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
		bankTypeResponse.setResponseTrans(trans);
		String response = JsonOnlineUtils.object2json(bankTypeResponse);
		logger.info(response);
		return response;
	}
	
	
}

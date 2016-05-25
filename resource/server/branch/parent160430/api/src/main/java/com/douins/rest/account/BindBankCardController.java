package com.douins.rest.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.account.domain.vo.BindCardReponseVO;
import com.douins.account.domain.vo.BindCardRequest;
import com.douins.account.service.ProcessBindOrUnBindCardService;
import com.douins.account.service.UserAccountService;
import com.douins.common.Api;
import com.douins.common.mapper.JsonMapper;
import com.douins.common.web.BaseController;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.vo.ResponseTransVo;
/**
 * 绑卡和解绑卡 Controller
 * @author shaotongyao
 *
 */

@Controller
@RequestMapping("/bankCard")
public class BindBankCardController extends BaseController {

	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private ProcessBindOrUnBindCardService  processBindOrUnBindCardService;
	
	
	//获取绑卡信息
	@Api(token = true)
	@RequestMapping(value = "/getBindCardInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getCardInfo( HttpServletRequest request,HttpServletResponse response, @RequestParam String data) {

		BindCardRequest bindCardRequest = fromJson(data, BindCardRequest.class);

		ResponseTransVo<BindCardReponseVO> bindCardResponse = processBindOrUnBindCardService.getBindCardInfo(bindCardRequest);
		
		return JsonMapper.toJsonString(bindCardResponse);
	}
	
	
	// 绑定银行卡
	@Api(token = true)
	@RequestMapping(value = "/bindCard", method = RequestMethod.POST)
	@ResponseBody
	public String bindCard(HttpServletRequest request,HttpServletResponse response, @RequestParam String data) {
		
		BindCardRequest bindCardRequest = fromJson(data, BindCardRequest.class);
				
		ResponseTransVo<BindCardReponseVO> bindCardResponse = processBindOrUnBindCardService.processBindCard(bindCardRequest);
		
		//return renderString(response, JsonMapper.toJsonString(bindCardResponse));
		return JsonMapper.toJsonString(bindCardResponse);

	}
	/**
	 * 绑定银行卡回调信息
	 * @return
	 */
	
	@RequestMapping(value = "/bindCardCallback", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> bindCardCallbackInfo(@RequestParam String errorCode,
			@RequestParam String errorMsg, @RequestParam String flowNo,@RequestParam String chanlFlowNo,
			@RequestParam String signMsg
			){
		int resultCode=0;
		
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		resultMap.put("errorCode", errorCode);
		resultMap.put("errorMsg", errorMsg);
		resultMap.put("flowNo", flowNo);
		resultMap.put("signMsg", signMsg);
		resultMap.put("chanlFlowNo", chanlFlowNo);
		
		//绑定成功，更新 userAccount 中bindCardFlag＝1 绑卡状态
		if("0000".equals(errorCode)){
		   resultCode =  processBindOrUnBindCardService.updateUserAccountBindCardFlagByFlowNo("1",chanlFlowNo);
		}
		resultMap.put("resultCode", resultCode);
		System.out.println("bindCard"+resultMap.toString());
		
		return resultMap;
	}
	
	
	
	
	
	//解除绑定银行卡
	@Api(token = true)
	@RequestMapping(value = "/unbindCard", method = RequestMethod.POST)
	@ResponseBody
	public String unBindCardInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam String data){
		
		BindCardRequest bindCardRequest = fromJson(data, BindCardRequest.class);

		ResponseTransVo<BindCardReponseVO> bindCardResponse = processBindOrUnBindCardService.processUnBindCard(bindCardRequest);
		
		//return renderString(response, JsonMapper.toJsonString(bindCardResponse));
		return JsonMapper.toJsonString(bindCardResponse);

	}
	
	
	/**
	 * 解绑银行卡回调信息
	 * @return
	 */
	
	@RequestMapping(value = "/unbindCardCallback" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> unbindCardCallbackInfo(@RequestParam String errorCode,
			@RequestParam String errorMsg, @RequestParam String flowNo,@RequestParam String chanlFlowNo,
			@RequestParam String signMsg
			){
		int resultCode=0;
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		resultMap.put("errorCode", errorCode);
		resultMap.put("errorMsg", errorMsg);
		resultMap.put("flowNo", flowNo);
		resultMap.put("signMsg", signMsg);
		resultMap.put("chanlFlowNo", chanlFlowNo);
		//绑定成功，更新 userAccount 中bindCardFlag＝1 绑卡状态
		if("0000".equals(errorCode)){
		   resultCode =  processBindOrUnBindCardService.updateUserAccountUnBindCardByUnbindFlowNo(chanlFlowNo);
		}
		resultMap.put("resultCode", resultCode);
		System.out.println("unbindCard-----:"+resultMap.toString());
		
		return resultMap;

		
	}
	
	

}

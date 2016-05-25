package com.mango.api.userAccountDetailAPI.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.userAccountDetailAPI.vo.UserAccountDetailRequest;
import com.mango.api.userAccountDetailAPI.vo.UserAccountDetailRequestVo;
import com.mango.api.userAccountDetailAPI.vo.UserAccountDetailResponse;
import com.mango.api.userAccountDetailAPI.vo.UserAccountDetailResponseVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.account.model.UserAccountDetail;
import com.mango.fortune.account.service.UserAccountDetailService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/api/userAccountDetail")
public class UserAccountDetailAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserAccountDetailService userAccountDetailService;
	
	/**
	 * 交易记录
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String getList(HttpServletRequest request) {
		UserAccountDetailResponse responseTrans=new UserAccountDetailResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			UserAccountDetailRequest openApplyRequest = JsonOnlineUtils.readJson2Entity(data,UserAccountDetailRequest.class);
			RequestTrans trans=openApplyRequest.getRequestTrans();
			transId=trans.getTransId();
			UserAccountDetailRequestVo detail=openApplyRequest.getUserAccountDetailVo();
			String userAccountId=detail.getUserAccountId();
			if(StringUtils.isBlank(userAccountId)){
				responseCode=ResponseCode.DATAREAD_ERROR;
			}else{
				List<UserAccountDetail> detailList= userAccountDetailService.findByCondition(detail);
				
				List<UserAccountDetailResponseVo> respDetailList=new ArrayList<UserAccountDetailResponseVo>();
				if(detailList!=null&&detailList.size()>0){
					for(UserAccountDetail det:detailList){
						UserAccountDetailResponseVo vo=new UserAccountDetailResponseVo();
						BeanUtils.copyProperties(det, vo);
						respDetailList.add(vo);
					}
				}
				responseTrans.setDetailList(respDetailList);
				responseCode=ResponseCode.SUCCESS;
			 }
		} catch (Exception e) {
			logger.error("getUserAccount error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}	
}

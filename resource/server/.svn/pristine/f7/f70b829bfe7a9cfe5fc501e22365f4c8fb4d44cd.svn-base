package com.douins.rest.account;

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

import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.domain.vo.UserAccountDetailRequest;
import com.douins.account.domain.vo.UserAccountDetailRequestVo;
import com.douins.account.domain.vo.UserAccountDetailResponse;
import com.douins.account.domain.vo.UserAccountDetailResponseVo;
import com.douins.account.service.UserAccountDetailService;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/userAccountDetail")
public class UserAccountDetailAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserAccountDetailService userAccountDetailService;
	
	/**
	 * 查询账户交易记录
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

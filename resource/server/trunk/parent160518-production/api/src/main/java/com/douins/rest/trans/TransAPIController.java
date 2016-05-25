package com.douins.rest.trans;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.state.TransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.account.domain.model.UserAuthority;
import com.douins.account.service.UserService;
import com.douins.account.service.iml.UserAuthorityService;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.SystemConstant;
import com.douins.trans.domain.enums.BusinessTransStatus;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransType;
import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.BusinessVo;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.douins.trans.domain.vo.TransRequest;
import com.douins.trans.domain.vo.TransResponse;
import com.douins.trans.service.TrasBusinessService;
import com.google.common.collect.Lists;
import com.mango.core.logger.SimpleLogger;

@Controller
@Scope("prototype")
@RequestMapping("/trans/")
@SuppressWarnings("rawtypes")
@Transactional
public class TransAPIController {
	
	private static SimpleLogger logger =SimpleLogger.getLogger(TransAPIController.class);
	
	@Autowired
	private TrasBusinessService trasBusinessService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAuthorityService authorityService;
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value ="business/trade",method = RequestMethod.POST)
	@ResponseBody
	public String getBusinessTrans(HttpServletRequest request) {
		TransResponse transResponse =  new TransResponse();
		ResponseCode responseCode = ResponseCode.FAILED;
	  try {
			String data = request.getParameter("data");
			TransRequest transRequest = JsonOnlineUtils.readJson2Entity(data,TransRequest.class);
			
			String accessToken = transRequest.getAccessToken();
			UserAuthority findUserByToken = authorityService.findUserByToken(accessToken);
			BusinessTrans businessTrans =new BusinessTrans();
			businessTrans.setTransUserToken(findUserByToken.getUid());
			businessTrans.setIsvalid(SystemConstant.ISVALID_YES);
			businessTrans.setStatus(BusinessTransStatus.SUCCESS.getValue());
			List<BusinessTrans> businessTransLists = trasBusinessService.getList(businessTrans);
			List<BusinessVo> businessVos =Lists.newArrayList();
			for (BusinessTrans businessTrans2 : businessTransLists) {
				businessVos.add(creatBusinessVo(businessTrans2));
			}
			transResponse.setBusinessTrans(businessVos);
			
			if(transRequest!=null){
				responseCode = ResponseCode.SUCCESS;
			}else{
				responseCode = ResponseCode.DATAREAD_ERROR;
			}
			ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transRequest.getRequestTrans().getTransId());
			transResponse.setResponseTrans(responseTrans);
			String object2json = JsonOnlineUtils.object2json(transResponse);
			logger.info(object2json);
			return object2json;
		} catch (Exception e) {
			logger.error("获取交易记录失败！", e); 
		}
	  return "";
		
}
  private BusinessVo creatBusinessVo(BusinessTrans businessTrans2) {
		BusinessVo businessVo = new BusinessVo();
		businessVo.setPayMoney(businessTrans2.getPayMoney());
		businessVo.setTransProductName(businessTrans2.getTransProductName());
		businessVo.setPayType(businessTrans2.getTransType());
		businessVo.setPayTime(businessTrans2.getTransTime());
		return businessVo;
	}
	
	

}
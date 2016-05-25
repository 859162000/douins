package com.douins.rest.policy;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.account.domain.model.User;
import com.douins.account.domain.vo.UserResponseVo;
import com.douins.account.service.UserService;
import com.douins.common.Api;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.policy.domain.vo.FinaceResponse;
import com.douins.policy.domain.vo.PolicyRequest;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy.domain.vo.PolicyResponseVo;
import com.douins.policy.service.PolicyMasterService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/policy")
public class PolicyAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private PolicyMasterService policyMasterService;
	
	@Inject
	private UserService userService;
	
	/**
	 * 查询保单（保险理财）
	 * @param request
	 * @return
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public String getList(HttpServletRequest request) {
		PolicyResponse responseTrans=new PolicyResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId="";
		try {
			String data = request.getParameter("data");
			PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
			
	
			
			RequestTrans transVo=trans.getRequestTrans();
			transId=transVo.getTransId();
			PolicyRequestVo policyVo=trans.getPolicyVo();
			String userId=policyVo.getUserId();
			if(StringUtils.isBlank(userId)){
				responseCode=ResponseCode.DATAREAD_ERROR;
			}else{
//				List<PolicyResponseVo> resList=policyMasterService.findVoByCondition(policyVo);
			    List<PolicyResponseVo> resList=policyMasterService.findSuccessVoByCondition(policyVo);
			    
			   // System.out.println("购买成功保单的list：－－－－"+resList.toString());
				responseTrans.setPolicyList(resList);
				responseCode=ResponseCode.SUCCESS;
			}
		} catch (Exception e) {
			logger.error("policy getList error",e);
		}
		ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
		responseTrans.setResponseTrans(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
	    //System.out.println("保单getListAPI result：－－－－"+response);
		
		logger.info(response);
		return response;
	}
	
	/**
	 * 保单理财详情
	 * @param request
	 * @return
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping("/finace/detail")
	public String getFinaceDetail(HttpServletRequest request){
	    ResponseCode responseCode = ResponseCode.FAILED;
	    FinaceResponse finaceResponse = new FinaceResponse();
	    String transId = null;
	    try {
            String data = request.getParameter("data");
            PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
            transId = trans.getRequestTrans().getTransId();
            List<PolicyResponseVo> resList=policyMasterService.findVoByCondition(trans.getPolicyVo());
            if(resList != null && resList.size() > 0){
                finaceResponse.setPolicyResponse(resList.get(0));
            }else{
                logger.info("there is no detail exist");
            }
            
            responseCode = ResponseCode.SUCCESS;
        } catch (Exception e) {
            logger.error("fince detail error",e);
        }

	    ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
        finaceResponse.setResponseTrans(trans);
        String response =JsonOnlineUtils.object2json(finaceResponse);
        logger.info(response);
        return response;
	}
	
	   /**
	    * 保单详情
	    * @param request
	    * @return
	    */
	  @Api(token = true)
	  @ResponseBody
	  @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
	   public String getDetail(HttpServletRequest request) {
	       PolicyResponse responseTrans=new PolicyResponse();
	       ResponseCode responseCode=ResponseCode.FAILED;
	       String transId="";
	       try {
	           String data = request.getParameter("data");
	           PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
	           RequestTrans transVo=trans.getRequestTrans();
	           transId=transVo.getTransId();
	           PolicyRequestVo policyVo=trans.getPolicyVo();
	           String userId=policyVo.getUserId();
	           if(StringUtils.isBlank(userId)||StringUtils.isBlank(policyVo.getPolicyId())){
	               responseCode=ResponseCode.DATAREAD_ERROR;
	           }else{
	               List<PolicyResponseVo> resList=policyMasterService.findVoByCondition(policyVo);
	               if(resList!=null&&resList.size()>0){
	                   User sUser = userService.findByKey(userId);
	                   UserResponseVo tUser=new UserResponseVo();
	                   BeanUtils.copyProperties(sUser, tUser);
	                   resList.get(0).setUserInfo(tUser);
	               }
	               responseTrans.setPolicyList(resList);
	               
	               responseCode=ResponseCode.SUCCESS;
	           }
	       } catch (Exception e) {
	           logger.error("policy getList error",e);
	       }
	       ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
	       responseTrans.setResponseTrans(trans);
	       String response =JsonOnlineUtils.object2json(responseTrans);
	       logger.info(response);
	       return response;
	   }
	
	/**
	 * 保单核保
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	 @Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/uw", method = RequestMethod.POST)
	@Deprecated
	public String uw(HttpServletRequest request){
		String data = request.getParameter("data");
		PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
		PolicyResponse responseTrans= policyMasterService.add2Uw(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	
	/**
	 * 保单承保并支付
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@Deprecated
	public String pay(HttpServletRequest request){
		String data = request.getParameter("data");
		PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
		PolicyResponse responseTrans= policyMasterService.insure2Pay(trans);
		String response =JsonOnlineUtils.object2json(responseTrans);
		logger.info(response);
		return response;
	}
	 
	 /**
	  * 根据用户身份证号查询保单信息
	  * @param request
	  * @return
	  */
	 @Api(token=true)
	 @ResponseBody
	 @RequestMapping("/query/list")
	 public String queryPolicyByID(HttpServletRequest request){
	     ResponseCode responseCode = ResponseCode.FAILED;
	     PolicyResponse responseTrans=new PolicyResponse();
	     String transId = null;
	     
	     try {
            String data = request.getParameter("data");
            PolicyRequest trans = JsonOnlineUtils.readJson2Entity(data,PolicyRequest.class);
            RequestTrans transVo=trans.getRequestTrans();
            transId=transVo.getTransId();
            
            String certiCode = trans.getPolicyVo().getCertiCode();//JSON.parseObject(data).getString("certiCode");   // 提取身份证号
            User user = userService.findUserByCertiCode(certiCode);
            if(StringUtils.isBlank(user.getUserId())){
                responseCode = ResponseCode.DATAREAD_ERROR;
            }else {
                PolicyRequestVo policyVo = new PolicyRequestVo();
                policyVo.setUserId(userId);
                policyVo.setCertiCode(certiCode);
                List<PolicyResponseVo> resList=policyMasterService.findVoByCondition(policyVo);
                responseTrans.setPolicyList(resList);
                responseCode=ResponseCode.SUCCESS;
            }
        } catch (Exception e) {
            logger.error("policy query error", e);
        }
	     
	     ResponseTrans trans=new ResponseTrans(responseCode.getValue(), responseCode.getName(),transId);
	        responseTrans.setResponseTrans(trans);
	        String response =JsonOnlineUtils.object2json(responseTrans);
	        logger.info(response);
	        return response;
	 }
}

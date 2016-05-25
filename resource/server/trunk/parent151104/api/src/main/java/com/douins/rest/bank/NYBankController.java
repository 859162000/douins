/**
 * 
 */
package com.douins.rest.bank;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAuthority;
import com.douins.account.domain.vo.UserRequestTrans;
import com.douins.account.domain.vo.UserRequestVo;
import com.douins.account.service.UserAccountService;
import com.douins.account.service.iml.UserAuthorityService;
import com.douins.account.service.iml.UserServiceImpl;
import com.douins.bank.domain.model.nybc.Body;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.bank.domain.model.nybc.ProjecteRegistRequestBody;
import com.douins.bank.domain.model.nybc.RegistAccountResponseBody;
import com.douins.bank.domain.vo.RegistAccountResponseVo;
import com.douins.bank.service.BankServiceIFC;
import com.douins.bank.service.iml.NYBankService;
import com.douins.common.Api;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.ResponseUtils;
import com.douins.common.util.SystemConstant;
import com.douins.common.util.XmlUtil;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequest;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy2.service.PolicyManageService;
import com.douins.policy2.service.Policyservice;
import com.douins.trans.domain.enums.BusinessTransStatus;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransChannel;
import com.douins.trans.domain.enums.TransType;
import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.domain.vo.RequestTransCommVo;
import com.douins.trans.domain.vo.RequestTransCommVo.Param;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.douins.trans.service.TrasBusinessService;
import com.google.common.collect.Maps;

/** 
* @ClassName: NYBankController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月7日 上午9:42:33 
*  
*/
@Controller
@Scope("prototype")
@RequestMapping("bank/ny/")
@Transactional
public class NYBankController {
    private static final Logger logger = Logger.getLogger(NYBankController.class);
    
    @Resource(name = "nyBankService")
    private BankServiceIFC service;
    @Inject
    private UserServiceImpl userService;
    @Autowired
    private TrasBusinessService trasBusinessService;
    @Autowired
	private UserAuthorityService userAuthorityService;    
    
    @Autowired
	private PolicyManageService policyManageService;
    
    @Autowired
	private Policyservice<PolicyMaster> policyservice;
    
    @Autowired
	private UserAccountService userAccountService;
    
    @Autowired
    private NYBankService bankservice;
    
    
    
    /**
     * 身份核查
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("verify/id")
    public String verifyId(HttpServletRequest request){
        ResponseTransVo< User> responseTransVo = new ResponseTransVo<User>();
        String transId = null;
        ResponseCode responseCode = ResponseCode.FAILED;
        
        try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN); 
            UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);;
            transId = userRequestTrans.getRequestTrans().getTransId();
            UserRequestVo userRequestVo = userRequestTrans.getUserVo();
            User user = (User)userRequestVo;
            User user2 = userService.getUser(token);
            user.setUserId(user2.getUserId());
            responseCode = service.verifyIdentity(user);
        } catch (Exception e) {
            logger.error("verify identity error.", e);
        }
        
        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId);
        return result;
    }
    
    /**
     * 注册资金账户
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("account/regist")
    //@Transactional
    public String registAccount(HttpServletRequest request){
        ResponseTransVo<RegistAccountResponseBody> responseTransVo = new ResponseTransVo<RegistAccountResponseBody>();
        String transId = null;
       ResponseCode responseCode = ResponseCode.FAILED;
        try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN); 
            UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            transId = userRequestTrans.getRequestTrans().getTransId();
            User user = userRequestTrans.getUserVo();
            userService.getOpenAccountInfo(token, user);
            RegistAccountResponseVo responseVo = service.openAccount(user);    // 注册返回的数据
            responseTransVo.setResponseParams(responseVo.getBody());
            responseCode = responseVo.getResponseCode();
        } catch (Exception e) {
            logger.error("regist account error", e);
        }
        if(responseCode==null) responseCode = ResponseCode.FAILED;
        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId);
        return result;
    }
    
    /**
     *  查询资金账户
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("account/query")
    public String queryAccount(HttpServletRequest request){
        ResponseTransVo<User> responseTransVo = new ResponseTransVo<User>();
        ResponseCode responseCode = ResponseCode.FAILED;
        String transId = null;
        try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN); 
            UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            transId = userRequestTrans.getRequestTrans().getTransId();
            User user = userRequestTrans.getUserVo();
            responseCode = service.queryAccount(token);
        } catch (Exception e) {
            logger.error("query account error.", e);
        }
        
        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId);
        return result;
    }
    
    /**
     * 加签
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("getSign")
    @Transactional
    public String getSign(HttpServletRequest request){
        ResponseTransVo<String> responseTransVo = new ResponseTransVo<String>();
        ResponseCode responseCode = ResponseCode.FAILED;
        String transId = null;
        try {
            String data = request.getParameter("data");
            RequestTransCommVo paramsVo = JsonOnlineUtils.readJson2Entity(data, RequestTransCommVo.class);
            transId = paramsVo.getRequestTrans().getTransId();
            String sign = service.signForRequest(paramsVo.getParam().getParams());
            String urlType = paramsVo.getParam().getUrlType();
            if(TransType.ADDMONEY.getValue().equals(urlType)||TransType.DELETEMONEY.getValue().equals(urlType)){
            	  trasBusinessService.saveTrasBusiness(createBusinessTrans(transId, paramsVo, urlType));
            }
           responseTransVo.setResponseParams(sign);
           responseCode = ResponseCode.SUCCESS;
        } catch (Exception e) {
            logger.error("get sign error.", e);
        }
        
        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId);
        return result;
    }

	private BusinessTrans createBusinessTrans(String transId,RequestTransCommVo paramsVo, String urlType) {
		  BusinessTrans businessTrans = new BusinessTrans();
		  businessTrans.setBusinessId(UUID.randomUUID().toString());
		  businessTrans.setOpUser(SystemConstant.OP_USER);
		  businessTrans.setIsvalid(SystemConstant.ISVALID_NO);
		  UserAuthority findUserByToken = userAuthorityService.findUserByToken(paramsVo.getAccessToken());
		  businessTrans.setTransUserToken(findUserByToken.getUid());
		  
		  String transChannel = paramsVo.getRequestTrans().getTransChannel();
		  if(TransChannel.IOS.getValue().equals(transChannel)){
		  	businessTrans.setTransChannel(transChannel);
		  }
		  if(TransChannel.AND.getValue().equals(transChannel)){
		  	businessTrans.setTransChannel(transChannel);
		  }
		  String params = paramsVo.getParam().getParams();
		  String[] paramSplit = params.split("\\|");
		  String payMoney = paramSplit[3];
		  String transNo = paramSplit[1];
		  businessTrans.setStatus(BusinessTransStatus.INIT.getValue());
		  businessTrans.setTransNo(transNo);
		  businessTrans.setPayMoney(new BigDecimal(payMoney));
		  businessTrans.setTransId(transId);
		  if(TransType.ADDMONEY.getValue().equals(urlType)){//电子账户充值
		        businessTrans.setTransType(urlType);
		  }
		 if(TransType.DELETEMONEY.getValue().equals(urlType)){//电子账户取现
		   businessTrans.setTransType(urlType);
		  }
		 businessTrans.setTransTime(new Date());
		 businessTrans.setUpdateDate(new Date());
		 businessTrans.setCreateDate(new Date());
		return businessTrans;
	}
    
    /**
     * 网关数据落地
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("gateway/save")
    @Transactional
    public String saveGatewayResponse(HttpServletRequest request){
    	 ResponseTransVo<String> responseTransVo = new ResponseTransVo<String>();
         ResponseCode responseCode = ResponseCode.FAILED;
        String transId = null;
        try {
            String data = request.getParameter("data");
            logger.info("请求＝"+data);
            //TODO
            data = data.replace(",operatType", ",transType");
            RequestTransCommVo paramsVo = JsonOnlineUtils.readJson2Entity(data, RequestTransCommVo.class);
            transId = paramsVo.getRequestTrans().getTransId();
            Param param = paramsVo.getParam();
			String params = param.getParams();
			String accessToken = paramsVo.getAccessToken();
			responseCode = service.analyzeGatewayResponse(params, accessToken);
			
			if(ResponseCode.SUCCESS.equals(responseCode)){
				UserAuthority findUserByToken = userAuthorityService.findUserByToken(paramsVo.getAccessToken());
				BusinessTrans businessTrans = new BusinessTrans();
				String urlType = paramsVo.getParam().getUrlType();
				if(TransType.ADDMONEY.getValue().equals(urlType)){//电子账户充值
						businessTrans.setTransType(urlType);
				}
			    if(TransType.DELETEMONEY.getValue().equals(urlType)){//电子账户取现
						businessTrans.setTransType(urlType);
				}
			    String uid="";
				if(findUserByToken != null){
						uid= findUserByToken.getUid();
				}
				businessTrans.setTransUserToken(uid);
				//TODO
				businessTrans.setStatus(BusinessTransStatus.INIT.getValue());
				businessTrans.setIsvalid(SystemConstant.ISVALID_NO);
				List<BusinessTrans> list = trasBusinessService.getList(businessTrans);
				if(!CollectionUtils.isEmpty(list)){
					BusinessTrans businessTrans2 = list.get(list.size()-1);
					//BusinessTrans businessTrans2= getMaxTransTimes(list);
					if(businessTrans2 !=null){
						businessTrans.setStatus(BusinessTransStatus.SUCCESS.getValue());
						businessTrans.setIsvalid(SystemConstant.ISVALID_YES);
						businessTrans.setId(businessTrans2.getId());
						trasBusinessService.updateTrasBusiness(businessTrans);
					}
				}
			}
			
        } catch (Exception e) {
            logger.error("get sign error.", e);
        }
        
        String result = ResponseUtils.getResponse(responseTransVo,responseCode, transId);
       
        return result;
    }
    private BusinessTrans getMaxTransTimes(List<BusinessTrans> list) {
    	Map<Long, Integer> newHashMap = Maps.newHashMap();
    	Integer index =0;
    	for (BusinessTrans businessTrans : list) {
    		newHashMap.put(Long.parseLong(businessTrans.getTransId()),index++);
		}
    	Long max = Collections.max(newHashMap.keySet());
		return list.get(newHashMap.get(max));
	}

	/**
     * 项目信息登记
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("test/project")
    public String ProjectRegist(HttpServletRequest request){
    	ProjecteRegistRequestBody body = new ProjecteRegistRequestBody();
    	body.setCode(request.getParameter("code"));
    	body.setDesc(request.getParameter("desc"));
    	body.setName(request.getParameter("name"));
    	body.setSize(request.getParameter("size"));
    	body.setStatus(request.getParameter("status"));
        
        String reString = service.projecteRegist(body);
        System.out.println(reString);
        return reString;
    }
    
	/**
     * 支付结果通知
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("callback/payinfo")
    public String PayInfo(HttpServletRequest request){
    	String data = request.getParameter("content");
    	logger.info("支付结果＝"+data);
    	CallBackRequest callBackRequest = XmlUtil.xmlDeserialize(data, CallBackRequest.class);
    	String chanlFlowNo = callBackRequest.getBody().getChanlFlowNo();
    	// 查一下 policyMaster  by chanlFlowNo
    	PolicyMaster policyMaster = policyservice.findByChanlFlowNo(chanlFlowNo);
    	UserAccount userAccountBack= userAccountService.findOneByUserId(policyMaster.getUserId());
    	PolicyResponse processInsuredAndPay =new PolicyResponse();
    	if(userAccountBack !=null){
    		BigDecimal accountBalance = userAccountBack.getAccountBalance();//账户余额
    		BigDecimal totalPrem = policyMaster.getTotalPrem();
    		if(accountBalance!=null&&totalPrem!=null&&accountBalance.compareTo(totalPrem)>=0){
    			 processInsuredAndPay = policyManageService.processInsuredAndPay(policyMaster,callBackRequest);
    		}
    		
    	}
    	Body body = callBackRequest.getBody();
    	Body body2 = new Body();
    	if(ResponseCode.SUCCESS.getValue().equals(processInsuredAndPay.getResponseTrans().getResponseCode())){
    		String retCode = body.getRetCode();
    		body2.setRetCode(retCode);
    		body2.setRetMsg(body.getRetMsg());
    	}else{
    		body2.setRetCode(ResponseCode.FAILED.getValue());
    		body2.setRetMsg("出单失败！");
    	}
    	bankservice.insertNYCallbackInfo(callBackRequest);
    	callBackRequest.setBody(body2);
    	return XmlUtil.xmlSerialize(callBackRequest, CallBackRequest.class);
    }

	private PolicyRequest creatPolicyRequest(CallBackRequest callBackRequest) {
		// TODO Auto-generated method stub
		return null;
	}
    
}

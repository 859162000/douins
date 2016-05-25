 /**
 * 
 */
package com.douins.rest.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.douins.bank.domain.model.nybc.SysHead;
import com.douins.bank.domain.vo.RegistAccountResponseVo;
import com.douins.bank.service.BankServiceIFC;
import com.douins.bank.service.iml.NYBankService;
import com.douins.common.Api;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.ResponseUtils;
import com.douins.common.util.SystemConstant;
import com.douins.common.util.XmlUtil;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy.service.PolicyManageService;
import com.douins.policy.service.Policyservice;
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
    	 logger.info("nan yue kai hu ==========================================1");
        ResponseTransVo<RegistAccountResponseBody> responseTransVo = new ResponseTransVo<RegistAccountResponseBody>();
        String transId = null;
       ResponseCode responseCode = ResponseCode.FAILED;
        try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN); 
            UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            transId = userRequestTrans.getRequestTrans().getTransId();
            User user = userRequestTrans.getUserVo();
            logger.info("nan yue kai hu ==========================================2");
            userService.getOpenAccountInfo(token, user);
            RegistAccountResponseVo responseVo = service.openAccount(user);    // 注册返回的数据
            logger.info("nan yue kai hu ==========================================end");
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
    @RequestMapping(value="callback/payinfo", method= RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String PayInfo(HttpServletRequest request){
    	String data = getAllRequstParamsByStringEntity(request);
    	//String data = request.getParameter("content");
    	if(data.contains("<CHANL_FLOW_NO>null</CHANL_FLOW_NO>")){
    		logger.info("支付结果＝<CHANL_FLOW_NO>null</CHANL_FLOW_NO> 忽略");
    		return getResponse(null);
    	}else{
    		logger.info("支付结果＝＋＋＋＋＋＋＋");
        	logger.info("支付结果＝"+data);
    	}
    	//data =StringUtils.isNotEmpty(data)?data:getAllRequstParams(request);
    	 CallBackRequest callBackRequest =  new CallBackRequest();
       if(StringUtils.isNotEmpty(data)&&data.contains("chanlFlowNo=")){
    	   logger.info("chanlFlowNo======================");
    	   callBackRequest =creatCallBackRequest(request);
       }else{
    	   logger.info("callback=====================");
    	   callBackRequest = XmlUtil.xmlDeserialize(data, CallBackRequest.class);
       }
       //保存支付信息
       logger.info("start保存支付信息=====================");
       bankservice.insertNYCallbackInfo(callBackRequest);
       
   	   String chanlFlowNo = callBackRequest.getBody().getChanlFlowNo();
    	// 只有支付成功待承包和核保成功待支付－－才去支付 待定
    	Lock lock =  new ReentrantLock();
    	lock.lock(); 
    	PolicyMaster policyMaster = new PolicyMaster();
    	try{
    		// 查一下 policyMaster  by chanlFlowNo
    		policyMaster = policyservice.findByChanlFlowNo(chanlFlowNo);
    		PolicyResponse processInsuredAndPay =new PolicyResponse();
	     	if(PolicyStatus.PAYSUCCESS.getValue().equals(policyMaster.getStatus())||PolicyStatus.UWSUCCESS.getValue().equals(policyMaster.getStatus())){
	     		UserAccount userAccountBack= userAccountService.findOneByUserId(policyMaster.getUserId());
	     		if(userAccountBack !=null){
	     			BigDecimal accountBalance = userAccountBack.getAccountBalance();//账户余额
	     			BigDecimal totalPrem = policyMaster.getTotalPrem();
	     			logger.info("totalPrem====================="+totalPrem);
	     			logger.info(accountBalance!=null&&totalPrem!=null&&accountBalance.compareTo(totalPrem)>=0);
	     			logger.info("承保－－账号余额："+accountBalance);
	     			logger.info("承保－－保单金额："+totalPrem);
	     			
	     			//if(accountBalance!=null&&totalPrem!=null&&accountBalance.compareTo(totalPrem)>=0){
	     				processInsuredAndPay = policyManageService.processInsuredAndPay(policyMaster,callBackRequest);
	     			// }
	     		}
	    	}
     	}catch(Exception e){
     		logger.error("防止chanlFlowNo重复承保：chanlFlowNo：["+chanlFlowNo+"] policyId:["+policyMaster.getPolicyId()+"]status:["+policyMaster.getStatus()+"]", e);
     	}finally{
     		lock.unlock();
     	}
		return getResponse(callBackRequest);
    }

    public String getResponse(CallBackRequest callBackRequest){
    	CallBackRequest callBackRequest2 = new CallBackRequest();
    	Body body2 = new Body();
    	String retCode;
		
    	if(callBackRequest==null){
    		retCode="2334";
    	}else{
    		Body body = callBackRequest.getBody();
    		retCode = body.getRetCode();
    	}
	    body2.setRetCode(retCode);
	    String retMsg ="成功";
     	body2.setRetMsg(retMsg);
     	callBackRequest2.setBody(body2);
    	
    	SysHead sysHead = new SysHead();
    	String signMsg = retCode+"|"+retMsg;
    	String sign = service.signForRequest(signMsg);
		sysHead.setSignMsg(sign);
		
		callBackRequest2.setSysHead(sysHead);
		String prix ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    	String replaceAll = XmlUtil.xmlSerialize(callBackRequest2, CallBackRequest.class).replaceAll("__", "_");
    	logger.info("支付回复南粤responseXml:"+prix+replaceAll);
		return prix+replaceAll;
    	
    }
private CallBackRequest creatCallBackRequest(HttpServletRequest request) {
		CallBackRequest callBackRequest = new CallBackRequest();
		Map<String, String[]> params = request.getParameterMap();
		Body body = new Body();
		SysHead sysHead = new SysHead();
		if (eixstKey(params, "signMsg")) {
			sysHead.setSignMsg(getValueByKey(params.get("signMsg")));
		}
		if (eixstKey(params, "flowNo")) {
			body.setFlowNo(getValueByKey(params.get("flowNo")));
		}
		if (eixstKey(params, "custNo")) {
			body.setCustNo(getValueByKey(params.get("custNo")));
		}
		if (eixstKey(params, "chanlNo")) {
			body.setChanlNo(getValueByKey(params.get("chanlNo")));
		}
		if (eixstKey(params, "tranDate")) {
			body.setTranDate(getValueByKey(params.get("tranDate")));
		}

		if (eixstKey(params, "flowNo")) {
			body.setStatus(getValueByKey(params.get("status")));
		}
		if (eixstKey(params, "retCode")) {
			body.setRetCode(getValueByKey(params.get("retCode")));
		}
		if (eixstKey(params, "retMsg")) {
			body.setRetMsg(getValueByKey(params.get("retMsg")));
		}
		if (eixstKey(params, "projCode")) {
			body.setProjCode(getValueByKey(params.get("projCode")));
		}
		if (eixstKey(params, "payAcct")) {
			body.setPayAcct(getValueByKey(params.get("payAcct")));
		}
		if (eixstKey(params, "payOpenBran")) {
			body.setPayOpenBran(getValueByKey(params.get("payOpenBran")));
		}
		if (eixstKey(params, "payAcctname")) {
			body.setPayAcctname(getValueByKey(params.get("payAcctname")));
		}
		if (eixstKey(params, "rcvAcctname")) {
			body.setRcvAcctname(getValueByKey(params.get("rcvAcctname")));
		}
		if (eixstKey(params, "rcvBank")) {
			body.setRcvBank(getValueByKey(params.get("rcvBank")));
		}
		if (eixstKey(params, "flowNo")) {
			body.setTranType(getValueByKey(params.get("tranType")));
		}
		if (eixstKey(params, "curr")) {
			body.setCurr(getValueByKey(params.get("curr")));
		}
		if (eixstKey(params, "amt")) {
			body.setAmt(getValueByKey(params.get("amt")));
		}
		if (eixstKey(params, "fee")) {
			body.setFee(getValueByKey(params.get("fee")));
		}
		if (eixstKey(params, "payType")) {
			body.setUsage(getValueByKey(params.get("usage")));
		}
		if (eixstKey(params, "flowNo")) {
			body.setPayType(getValueByKey(params.get("payType")));
		}
		if (eixstKey(params, "freezeNo")) {
			body.setFreezeNo(getValueByKey(params.get("freezeNo")));
		}
		if (eixstKey(params, "projName")) {
			body.setProjName(getValueByKey(params.get("projName")));
		}
		if (eixstKey(params, "tranTitle")) {
			body.setTranTitle(getValueByKey(params.get("tranTitle")));
		}
		if (eixstKey(params, "tranDesc")) {
			body.setTranDesc(getValueByKey(params.get("tranDesc")));
		}
		if (eixstKey(params, "chanlFlowNo")) {
			body.setChanlFlowNo(getValueByKey(params.get("chanlFlowNo")));
		}
		if (eixstKey(params, "tranTime")) {
			body.setTranTime(getValueByKey(params.get("tranTime")));
		}
		callBackRequest.setSysHead(sysHead);
		callBackRequest.setBody(body);
		return callBackRequest;
	}

private boolean eixstKey(Map<String, String[]> params,String key) {
	return params.keySet().contains(key);
}

private String getValueByKey(String[] values) {
	StringBuilder queryStr = new StringBuilder();
	for (String val : values) {
	   queryStr.append(val);
 	}
	return queryStr.toString();
}

protected String getAllRequstParams(HttpServletRequest request){
        Map<String, String[]> params = request.getParameterMap();
        System.out.println(String.valueOf(request.getContentLength()));
        System.out.println(request.getContentType());
        System.out.println(request.getContextPath());
        StringBuilder queryStr = new StringBuilder();
        for(String key : params.keySet()){
            String[] values = params.get(key);
            queryStr.append(key);
            queryStr.append("=");
            for(String val : values){
                queryStr.append(val);
                queryStr.append("&");
            }
        }
        queryStr.deleteCharAt(queryStr.length() - 1);
        return queryStr.toString();
    }
    
	protected String getAllRequstParamsByStringEntity(HttpServletRequest request) {
		String queryStr = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8")); // 实例化输入流，并获取网页代码
			String s; // 依次循环，至到读的值为空
			StringBuilder sb = new StringBuilder();
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			reader.close();
			queryStr = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queryStr;
	}

/**
 * 获取网传报文的内容
 * @param request
 * @return
 */
protected String getRequestConent(HttpServletRequest request){
    String content = null;
    try {
        request.setCharacterEncoding("UTF-8");
        InputStream inputStream = request.getInputStream();
        StringBuilder str = new StringBuilder();
        byte [] bytes=new byte[1024];
        int count = 0;
        while((count = inputStream.read(bytes)) != -1){
            String temp = new String(bytes, 0, count);
            str.append(temp);
        }
        content = str.toString();
        logger.info("报文内容＝ \n"+ content);
    } catch (Exception e) {
        logger.error("input data error.", e);
    }
    
    return content;
}
    
}

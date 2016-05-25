package com.douins.rest.account;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.ClientId;
import com.douins.account.domain.model.User;
import com.douins.account.domain.vo.UserInfo;
import com.douins.account.domain.vo.UserInfoVo;
import com.douins.account.domain.vo.UserRequestTrans;
import com.douins.account.domain.vo.UserRequestVo;
import com.douins.account.domain.vo.UserResponse;
import com.douins.account.domain.vo.UserResponseInfoVo;
import com.douins.account.domain.vo.UserResponseVo;
import com.douins.account.domain.vo.UserStatusInfoResponse;
import com.douins.account.domain.vo.UserStatusInfoVo;
import com.douins.account.service.PasswordService;
import com.douins.account.service.UserService;
import com.douins.account.service.iml.UserAuthorityService;
import com.douins.bank.domain.vo.BankChangeRequest;
import com.douins.bank.domain.vo.BankChangeResponseVo;
import com.douins.bank.service.BankServiceIFC;
import com.douins.common.Api;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.ResponseUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.RequestTransVo;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserAPIController extends BasicController{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordService passwordService;
	@Resource(name = "nyBankService")
    private BankServiceIFC BankService;
	@Inject
	private UserAuthorityService authorityService;
	
	/**
	 * 用户登录
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		UserResponse reponse = new UserResponse();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId=null;
		try {
			String data = request.getParameter("data");		
			UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
			RequestTrans requestTrans = userRequestTrans.getRequestTrans();
			transId = requestTrans.getTransId();
			User user = userRequestTrans.getUserVo();		
			responseCode = userService.login(user);	
			if(responseCode.equals(ResponseCode.SUCCESS)){
				User sUser = userService.findByKey(user.getUserId());
				UserResponseVo tUser=new UserResponseVo();
				BeanUtils.copyProperties(sUser, tUser);
				reponse.setUser(tUser);
				
				// 登录成功，设置本次登录期内的 token
                String token = authorityService.addLoginToken(sUser.getUserId());
                reponse.setAccessToken(token);
			}
		} catch (Exception e) {
			logger.error("login error",e);
		}
		ResponseTrans responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
		reponse.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(reponse);
		logger.info(result);
		return  result;
	}
	
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String get(HttpServletRequest request) {
        UserResponse reponse = new UserResponse();
        ResponseCode responseCode = ResponseCode.FAILED;
        String transId = null;
        try {
            String data = request.getParameter("data");
            UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            RequestTrans requestTrans = userRequestTrans.getRequestTrans();
            transId = requestTrans.getTransId();
            User user = userRequestTrans.getUserVo();
            User sUser = userService.findByKey(user.getUserId());
            UserResponseVo tUser = new UserResponseVo();
            BeanUtils.copyProperties(sUser, tUser);
            reponse.setUser(tUser);
        } catch (Exception e) {
            logger.error("login error", e);
        }
        ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
        reponse.setResponseTrans(responseTrans);
        String result = JsonOnlineUtils.bean2json(reponse);
        logger.info(result);
        return result;
    }
	
	/**
	 * 用户注册
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(HttpServletRequest request) {	
		UserResponse reponse = new UserResponse();	
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId=null;
		try {
			String ip = request.getRemoteAddr();     // 用户IP
			String data = request.getParameter("data");
			// 获取用户交易请求
			UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
			RequestTrans requestTrans = userRequestTrans.getRequestTrans();
			transId = requestTrans.getTransId();
			UserRequestVo user = userRequestTrans.getUserVo();
			// 用户注册，返回注册结果的代码
			responseCode = userService.regist(user, ip,user.getVerificationCode(), requestTrans.getTransType(),requestTrans.getTransChannel());
			if(responseCode.equals(ResponseCode.SUCCESS)){
			    // 注册成功
				User sUser = userService.findByKey(user.getUserId());
				UserResponseVo tUser=new UserResponseVo();
				BeanUtils.copyProperties(sUser, tUser);
				reponse.setUser(tUser);
				
				// 默认登录成功，设置本次登录期内的 token
                String token = authorityService.addLoginToken(sUser.getUserId());
                reponse.setAccessToken(token);
			}
		} catch (Exception e) {
			logger.error("regist error",e);
		}
		ResponseTrans responseTrans  = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
		reponse.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(reponse);
		logger.info(result);
		return result;
	}
	
	
	/**
	 * 方法名称: passwordInit<br>
	 * 描述：密码变更
	 * 作者: winterchen
	 * 修改日期：May 5, 20154:02:03 PM
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	public String passwordChange(HttpServletRequest request) {	
		ResponseTransVo responseTransVo = new ResponseTransVo();
		ResponseCode responseCode=ResponseCode.FAILED;
		String transId=null;
		try {
			String data = request.getParameter("data");	
			UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
			transId = userRequestTrans.getRequestTrans().getTransId();	
			responseCode = passwordService.passwordChange(userRequestTrans);
		} catch (Exception e) {
			logger.error("password error",e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);		
		responseTransVo.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(responseTransVo);
		logger.info(result);
		return result;
	}
	
	/**
	 * 修改用户信息
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request) {	
		String data = request.getParameter("data");
		UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
		RequestTrans requestTrans = userRequestTrans.getRequestTrans();
		String transId = requestTrans.getTransId();
		UserRequestVo userVo = userRequestTrans.getUserVo();
		ResponseCode responseCode = userService.modify(userVo,requestTrans.getTransType());
		
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
		ResponseTransVo responseTransVo = new ResponseTransVo();
		responseTransVo.setResponseTrans(responseTrans);
		String result = JsonOnlineUtils.bean2json(responseTransVo);
		logger.info(result);
		return result;
	}
	
	
	/**
	 * 设置用户个推的 ClientId
	 * @param request
	 * @return
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value="/gtcid/set", method = RequestMethod.POST)
	public String  setClientId(HttpServletRequest request){
	   
        ResponseCode responseCode = ResponseCode.FAILED;
        try {
            String data = request.getParameter("data");
            logger.info("请求参数=================\n" + data);
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);
            RequestTransVo<ClientId> clientIdVo = JsonOnlineUtils.readJson2Entity2(data, ClientId.class);
            boolean flag = userService.setClientId(token, clientIdVo.getRequestParams().getClientId());
            if(flag){
                responseCode = ResponseCode.SUCCESS;
            }
        } catch (Exception e) {
            logger.error("设置推送的 Client ID 错误================", e);
        }

        String result = ResponseUtils.getResponse(new ResponseTransVo<String>(), responseCode, "");
        logger.info("设置 Client id 的响应数据==================\n" + result);
        return result;
	}

	/**
	 * 获取用户信息（购买保险时调用）
	 * @param request
	 * @return
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping("/info/get")
	public String getUserInfo(HttpServletRequest request){
		UserResponseInfoVo responseVo = new UserResponseInfoVo();
	    ResponseCode responseCode = ResponseCode.FAILED;
	    String transId = null;
	    try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);
            UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            transId = userRequestTrans .getRequestTrans().getTransId();
            UserInfoVo info = userService.getUserInfo(token);
            User user = info.getUser();
            UserInfo userInfo = new UserInfo();
            if(user !=null){
            	userInfo.setCity(user.getCity());
            	userInfo.setProvince(user.getProvince());
            	userInfo.setUserEmail(user.getUserEmail());
            	userInfo.setDetailedAdress(user.getDetailedAdress());
            	userInfo.setDistrict(user.getDistrict());
            	userInfo.setZipcode(user.getZipcode());
            	Date userBirthDay = user.getUserBirthDay();
            	int age = getAge(userBirthDay);
            	userInfo.setAge(age);
            	userInfo.setAccountBalance(info.getAccountBalance().toString());
            	
            }
            responseCode = ResponseCode.SUCCESS;
            responseVo.setUserInfo(userInfo);
        } catch (Exception e) {
            logger.error("password error",e);
        }
	    ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);     
        responseVo.setResponseTrans(responseTrans);
        String result = JsonOnlineUtils.bean2json(responseVo);
        logger.info(result);
        return result;
	}
	public static int getAge(Date birthDate) {
		if (birthDate == null) throw new RuntimeException("出生日期不能为null");
		int age = 0;
		Date now = new Date();
		SimpleDateFormat format_y = new
		SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new
		SimpleDateFormat("MM");
		String birth_year =format_y.format(birthDate);
		String this_year =format_y.format(now);
		String birth_month =format_M.format(birthDate);
		String this_month =format_M.format(now);
		// 初步，估算
		age =Integer.parseInt(this_year) - Integer.parseInt(birth_year);
		// 如果未到出生月份，则age - 1
		if(this_month.compareTo(birth_month) < 0){age -=1;}
		if (age <0){age =0;}
		return age;
     }
	/**
	 * 完善个人资料
	 * @param request
	 * @return
	 */
	@Api(token = true)
    @ResponseBody
    @RequestMapping("/priv/info")
	public String setPrivateInfo(HttpServletRequest request){
	    ResponseCode responseCode = ResponseCode.FAILED;
	    String transId = null;
	    try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);   
            UserRequestTrans trans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            transId = trans.getRequestTrans().getTransId();
            User user = trans.getUserVo();
	        responseCode = userService.setPrivateInfos(user, token);
        } catch (Exception e) {
            responseCode = ResponseCode.DATAREAD_ERROR;
            logger.error("set private info. error!", e);
        }
	    
	    ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
	    String response = JsonOnlineUtils.object2json(responseTrans);
	    return response;
	}
	
	/**
	 * 用户相关状态的数据
	 * @param request
	 * @return
	 */
	@Api(token = true)
	@ResponseBody
	@RequestMapping("/status")
	public String gerUserStatus(HttpServletRequest request){
	    ResponseCode responseCode = ResponseCode.FAILED;
	    UserStatusInfoResponse responseVo = new UserStatusInfoResponse();
	    String transId = null;
	    try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);   
            UserRequestTrans trans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            transId = trans.getRequestTrans().getTransId();
            UserStatusInfoVo userStatus = userService.getUserStatusInfo(token);
            
            responseVo.setUserStatus(userStatus);
            
            responseCode = ResponseCode.SUCCESS;
        } catch (Exception e) {
            responseCode = ResponseCode.DATAREAD_ERROR;
            logger.error("get user status error!", e);
        }
	    
	    ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);     
        responseVo.setResponseTrans(responseTrans);
        String result = JsonOnlineUtils.bean2json(responseVo);
       // System.out.println("statusAPI--result------:"+result);
        
        logger.info(result);
        return result;
	}
	
	@ResponseBody
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
	    logger.info("测试接口。。。");
	    ResponseTransVo< User> responseTransVo = new ResponseTransVo<User>();
	    String transId = null;
	    ResponseCode responseCode = ResponseCode.FAILED;
	    
	    try {
            String data = request.getParameter("data");
            //UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
            RequestTransVo<User> userRequestTrans = JsonOnlineUtils.readJson2Entity2(data, User.class);;
            transId = userRequestTrans.getRequestTrans().getTransId();
            String uid = userRequestTrans.getRequestParams().getUserId();
            User user = userService.findByKey(uid);
            responseTransVo.setResponseParams(user);
            
            responseCode = ResponseCode.SUCCESS;
        } catch (Exception e) {
            responseCode = ResponseCode.DATAREAD_ERROR;
            logger.error("test error!", e);
        }
	    
	    ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);     
        responseTransVo.setResponseTrans(responseTrans);
        String result = JsonOnlineUtils.bean2json(responseTransVo);
        logger.info(result);

	    return result;
	}
	
	@Api(token = true)
	@ResponseBody
	@RequestMapping(value = "/getUrl", method = RequestMethod.POST)
	public String getUrl(HttpServletRequest request) {
		BankChangeRequest trans = JsonOnlineUtils.readJson2Entity(request.getParameter("data"), BankChangeRequest.class);
		/* 获取设置密码、修改密码、重置密码、充值、提现几个操作的URL */
		ResponseTransVo<BankChangeResponseVo> responseTransVo = BankService.AccoutManagerWithBank(trans);
		String result = JsonOnlineUtils.bean2json(responseTransVo);
		logger.info(result);
		return result;
	}
}

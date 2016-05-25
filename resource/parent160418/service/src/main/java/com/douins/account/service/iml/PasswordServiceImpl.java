package com.douins.account.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.douins.account.domain.vo.UserRequestTrans;
import com.douins.account.domain.vo.UserRequestVo;
import com.mango.core.logger.SimpleLogger;
import com.douins.account.service.UserPayAccountService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransType;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.account.domain.model.User;
import com.douins.account.service.PasswordService;
import com.douins.account.service.UserService;
import com.douins.account.service.ValidateCodeService;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;
import com.douins.redis.service.RedisCacheService;

@Service
public class PasswordServiceImpl implements PasswordService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private UserPayAccountService userBankService;
	@Autowired
	@Qualifier("validateCodeService")
	private ValidateCodeService validateCodeService;
//	@Autowired
//	private RedisCacheService redisCacheService;
	
	@Override
	public ResponseCode passwordChange(UserRequestTrans userRequestTrans){
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
			RequestTrans requestTrans = userRequestTrans.getRequestTrans();
			UserRequestVo userVo = userRequestTrans.getUserVo();
			
			if (requestTrans.getTransType().equals(TransType.USERPAYPASSWORDCHANGE.getValue())) {// 修改交易密码
				responseCode = payPasswordChange(userVo,true);
			}
			
			if (requestTrans.getTransType().equals(TransType.USERLOGINPASSWORDCHANGE.getValue())) {// 修改登陆密码
			    responseCode = loginPasswordChange(userVo,true);
			}

			if (requestTrans.getTransType().equals(TransType.USERGESTUREPASSWORDCHANGE.getValue())) {// 修改手势密码
				responseCode = gesturePasswordChange(userVo,true);
			}

			if (requestTrans.getTransType().equals(TransType.USERPAYPASSWORDINIT.getValue())) {// 交易密码初始化
				userVo.setSwitchPayPassword("1");
				responseCode = payPasswordChange(userVo,false);
			}

			if (requestTrans.getTransType().equals(TransType.USERGESTUREPASSWORDINIT.getValue())) {// 手势密码初始化
				responseCode = gesturePasswordChange(userVo,false);
			}

			if (requestTrans.getTransType().equals(TransType.USERLOGINPASSWORDRESET.getValue())) {// 找回登陆密码
				responseCode = loginPasswordReset(userVo,requestTrans.getTransType());
			}
			if (requestTrans.getTransType().equals(TransType.USERPAYPASSWORDRESET.getValue())) {// 找回交易密码
				responseCode = payPasswordReSet(userVo,requestTrans.getTransType());
			}
//			if(redisCacheService.exists(User.class.getSimpleName()+redisCacheService.SPLIT+userVo.getUserId())){
//				redisCacheService.remove(User.class.getSimpleName()+redisCacheService.SPLIT+userVo.getUserId());
//			}

		} catch (Exception e) {
			logger.error("修改密码信息", e);
			responseCode = ResponseCode.FAILED;
			
		}
		return responseCode;
	}

	/**
	 * 交易密码变根
	 */
	private ResponseCode loginPasswordChange(UserRequestVo userVo,boolean check){
		String loginPassword = userVo.getLoginPassword();
		String oldLoginPassword = userVo.getOldLoginPassword();
		User guser = userService.findByKey(userVo.getUserId());
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
			if(check){
				if(oldLoginPassword.equals(guser.getLoginPassword())){
					guser.setLoginPassword(loginPassword);
					SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
					userService.update(SystemConstant.OP_USER, guser);
					responseCode =ResponseCode.SUCCESS; 
				}else{
					responseCode =ResponseCode.USERPASSWORD_1; 
				}
			}else{
				guser.setLoginPassword(loginPassword);
				SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
				userService.forceUpdate(SystemConstant.OP_USER, guser);
				responseCode =ResponseCode.SUCCESS; 
			}
		} catch (Exception e) {
			logger.error("登陆密码变更",e);
			responseCode =ResponseCode.FAILED; 
		}
		return responseCode;				
	}
	

	/**
	 * 交易密码变根
	 */
	private ResponseCode payPasswordChange(UserRequestVo userVo,boolean check){
		String payPassword = userVo.getPayPassword();
		String oldPayPassword = userVo.getOldPayPassword();
		User guser = userService.findByKey(userVo.getUserId());
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
			if((check&&oldPayPassword.equals(guser.getPayPassword()))||(!check)){				
				guser.setPayPassword(payPassword);
				SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
				userService.update(SystemConstant.OP_USER, guser);
				responseCode= ResponseCode.SUCCESS;
			}else if(check&&(!oldPayPassword.equals(guser.getPayPassword()))){
				responseCode= ResponseCode.USERPASSWORD_2;
			}
		} catch (Exception e) {
			logger.error("交易密码变更",e);
			responseCode= ResponseCode.FAILED;			
		}
		return responseCode;				
	}
	
	/**
	 * 方法名称: gesturePasswordChange<br>
	 * 描述：手势密码变更
	 * 作者: winterchen
	 * 修改日期：May 5, 20155:41:23 PM
	 * @param userVo
	 * @param check
	 * @param transId
	 * @return
	 */
	private ResponseCode gesturePasswordChange(UserRequestVo userVo,boolean check){
		String gesturePassword = userVo.getGesturePassword();
		User guser = userService.findByKey(userVo.getUserId());
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
				String oldGesturePassword = guser.getGesturePassword();
				String OldGesturePassword = userVo.getOldGesturePassword();
				if(check){
					if(!oldGesturePassword.equals(OldGesturePassword)&&"1".equals(guser.getSwitchGesturePassword())){
						responseCode = ResponseCode.USERPASSWORD_3;
						return responseCode;
					}
					if(gesturePassword.equals(oldGesturePassword)){
						responseCode = ResponseCode.USERPASSWORD_6;
						return responseCode;
					}
				}				
				guser.setSwitchGesturePassword(userVo.getSwitchGesturePassword());
				guser.setGesturePassword(gesturePassword);
				SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
				userService.update(SystemConstant.OP_USER, guser);
				responseCode = ResponseCode.SUCCESS;				
		} catch (Exception e) {
			logger.error("手势密码变更",e);
			responseCode = ResponseCode.FAILED;
		}
		return responseCode;
	}
	
	/**
	 * 方法名称: passwordRecovery<br>
	 * 描述：登陆密码重置
	 * 作者: winterchen
	 * 修改日期：May 12, 201511:13:15 AM
	 * @param userVo
	 * @param transType
	 * @param transId
	 * @return
	 */
	public ResponseCode loginPasswordReset(UserRequestVo userVo,String transType){
		ResponseCode responseCode = ResponseCode.FAILED;
		String verificationCode =userVo.getVerificationCode();
		try {
		    logger.info("验证码=" + verificationCode);
			User guser = userService.findUniqueByCondition(userVo);			
			if(guser == null){
			    logger.info("你大爷");
			}
			String mobile = guser.getUserAccount();
			if(validateCodeService.checkSms(transType, mobile, verificationCode)){//将比较短信验证码
				guser.setLoginPassword(userVo.getLoginPassword());
				SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
				userService.update(SystemConstant.OP_USER, guser);
				responseCode=ResponseCode.SUCCESS;
			}else{
				responseCode=ResponseCode.VERIFICATIONCODE_MOBILE;
			}
		} catch (Exception e) {
			logger.error("登陆密码重置",e);
			responseCode = ResponseCode.FAILED;
		}
		return responseCode;
	}
	
	private ResponseCode payPasswordReSet(UserRequestVo userVo,String transType){
		ResponseCode responseCode = ResponseCode.FAILED;
		String verificationCode =userVo.getVerificationCode();
		try {
			User guser = userService.findUniqueByCondition(userVo);			
			String mobile = guser.getUserAccount();
			if(validateCodeService.checkSms(transType, mobile, verificationCode)){//将比较短信验证码
				guser.setPayPassword(userVo.getPayPassword());				
				SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
				userService.update(SystemConstant.OP_USER, guser);
				responseCode = ResponseCode.SUCCESS;	
			}else{
				responseCode=ResponseCode.VERIFICATIONCODE_MOBILE;
			}
						
		} catch (Exception e) {
			logger.error("交易密码重置",e);
			responseCode = ResponseCode.FAILED;
		}		
		return responseCode;
	}
}

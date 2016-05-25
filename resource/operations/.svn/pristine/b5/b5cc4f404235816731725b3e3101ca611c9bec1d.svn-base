package com.douins.account.service.iml;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.account.dao.UserDao;
import com.douins.account.domain.enums.AccountStaus;
import com.douins.account.domain.enums.AccountType;
import com.douins.account.domain.enums.UserStatus;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAuthority;
import com.douins.account.domain.vo.UserInfoVo;
import com.douins.account.domain.vo.UserStatusInfoVo;
import com.douins.account.service.UserService;
import com.douins.account.service.ValidateCodeService;
import com.douins.bank.service.BankServiceIFC;
import com.douins.common.util.Const;
import com.douins.common.util.ReadConfig;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;
import com.douins.redis.service.RedisCacheService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransType;
import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.page.Page;

/**
 * 描述：<br>
 * 作者：wintrchen <br>
 * 修改日期：May 5, 20154:34:13 PM <br>
 * E-mail: winterchen@sinorfc.com <br>
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	public static String registsmscheck = ReadConfig.get("registsmscheck");
//	@Autowired
//	private BaseDao<User> baseDao;
	@Autowired
	@Qualifier("validateCodeService")
	private ValidateCodeService validateCodeService;
	@Autowired
	private RedisCacheService redisCacheService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserAuthorityService authorityService;
	@Autowired
	private UserAccountServiceImpl accountService;
	
	@Resource(name = "nyBankService")
	private BankServiceIFC bankService;
	@Autowired
	private UserPrivateMsgService privMsgService;
	
	public ResponseCode login(User user) {
		ResponseCode responseCode = ResponseCode.FAILED;		
		try {
			String loginPassword = user.getLoginPassword();
			String userAccount = user.getUserAccount();
			User guser = this.getUserByAccount(userAccount);
			if (guser != null) {				
				if(StringUtils.isNotBlank(loginPassword)&&guser.getLoginPassword().equals(loginPassword)){
					responseCode = ResponseCode.SUCCESS;	
					BeanUtils.copyProperties(guser, user);
				}else{
					responseCode = ResponseCode.USERPASSWORD_4;	
				}				
			} else {
				responseCode = ResponseCode.USERNOTEXIST;
			}			
		} catch (Exception e) {
			logger.error("login error",e);
			responseCode = ResponseCode.DATAREAD_ERROR;
		}
		return responseCode;
	}
	
	public ResponseCode login2(User user) {
		try {
			String loginPassword = user.getLoginPassword();
			String userAccount = user.getUserAccount();
			User guser = this.getUserByAccount(userAccount);
			if(guser == null) return ResponseCode.USERNOTEXIST;
			if(StringUtils.isNotBlank(loginPassword)&&loginPassword.equals(guser.getLoginPassword())){
				   return ResponseCode.SUCCESS;	
			  }else{
				   return ResponseCode.USERPASSWORD_4;	
			}				
		} catch (Exception e) {
			logger.error("login error",e);
			return ResponseCode.DATAREAD_ERROR;
		}
	}
	
	
	/**
	 * 用户注册
	 */
	@Transactional(rollbackFor=Exception.class)
	public ResponseCode regist1(User user, String ip,String verificationCode,String transType,String transChannel) {		
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
			boolean registsms=true;
			if("false".equals(registsmscheck)){
				registsms=true;
			}else{
			    //验证 验证码
				registsms = validateCodeService.checkSms(transType, user.getUserAccount(), verificationCode);
			}
			
			if(registsms){
				if (this.exitAccount(user.getUserAccount())) {
					responseCode = ResponseCode.USERREGIST_1;
				} else {
				    // 目前只支持手机号注册
				    if(user.getAccountType() == null || StringUtils.isBlank(user.getAccountType())){
				        user.setAccountType(AccountType.MOBILE.getValue());
				    }
				    
					if (AccountType.MOBILE.getValue().equals(user.getAccountType())) {
						user.setUserMobile(user.getUserAccount());
					} else if (AccountType.EMAIL.getValue().equals(user.getAccountType())) {
						user.setUserEmail(user.getUserAccount());
					}
					
					user.setRegisterType(transChannel);
					user.setIpAddress(ip);
					user.setRegisterTime(new Date());
					user.setLastLoginTime(new Date());
					user.setNovice("1");
					user.setStatus(UserStatus.INIT.getValue());        // 未认证
					SaveEntityUtils.initEntityBeforeInsert(user, SystemConstant.OP_USER);
					this.save(SystemConstant.OP_USER, user);
					
					responseCode =ResponseCode.SUCCESS;					
				}	
			}else{
				responseCode = ResponseCode.VERIFICATIONCODE_MOBILE;				
			}			

		} catch (Exception e) {
			logger.error("客户注册失败", e);
			//responseCode =ResponseCode.FAILED;
		}
		return responseCode;
	}
	
	/**
	 * 用户注册
	 */
	@Transactional
	public ResponseCode regist(User user, String ip,String verificationCode,String transType,String transChannel) {		
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
			boolean registsms=true;
			if("false".equals(registsmscheck)){
				registsms=true;
			}else{
			    // 验证 验证码
				registsms = validateCodeService.checkSms(transType, user.getUserAccount(), verificationCode);
			}
			
			if(registsms){
				if (this.exitAccount(user.getUserAccount())) {
					responseCode = ResponseCode.USERREGIST_1;
				} else {
				    // 目前只支持手机号注册
				    if(user.getAccountType() == null || StringUtils.isBlank(user.getAccountType())){
				        user.setAccountType(AccountType.MOBILE.getValue());
				    }
				    
					if (AccountType.MOBILE.getValue().equals(user.getAccountType())) {
						user.setUserMobile(user.getUserAccount());
					} else if (AccountType.EMAIL.getValue().equals(user.getAccountType())) {
						user.setUserEmail(user.getUserAccount());
					}
					
					user.setRegisterType(transChannel);
					user.setIpAddress(ip);
					user.setRegisterTime(new Date());
					user.setLastLoginTime(new Date());
					user.setNovice("1");
					user.setStatus(UserStatus.INIT.getValue());        // 未认证
					SaveEntityUtils.initEntityBeforeInsert(user, SystemConstant.OP_USER);
					this.save(SystemConstant.OP_USER, user);
					
					responseCode =ResponseCode.SUCCESS;					
				}	
			}else{
				responseCode = ResponseCode.VERIFICATIONCODE_MOBILE;				
			}			

		} catch (Exception e) {
			logger.error("客户注册失败", e);
			//responseCode =ResponseCode.FAILED;
		}
		return responseCode;
	}

	@Transactional
	public ResponseCode modify(User userVo,String transType) {
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
			if (transType.equals(TransType.USERMOBILECHANGE.getValue())) {// 修改手机号
				String mobilePhone = userVo.getUserMobile();
				if (this.exitAccountByUser(userVo)) {
					responseCode =ResponseCode.USERMOBILE_1;
				} else {
					User guser = this.findByKey(userVo.getUserId());
					guser.setUserMobile(mobilePhone);
					SaveEntityUtils.setUpdateForEntity(guser, "");
					this.forceUpdate(SystemConstant.OP_USER, guser);
					responseCode= ResponseCode.SUCCESS;
				}
			}

			if (transType.equals(TransType.USEREMAILCHANGE.getValue())) {// 修改邮箱
				String email = userVo.getUserEmail();
				if (this.exitAccountByUser(userVo)) {
					responseCode =ResponseCode.USEREMAIL_1;
				} else {
					User guser = this.findByKey(userVo.getUserId());
					guser.setUserEmail(email);
					SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
					this.update(SystemConstant.OP_USER, guser);
					responseCode =ResponseCode.SUCCESS;
				}
			}

			if (transType.equals(TransType.USERNICKNAMECHANGE.getValue())) {// 昵称修改
				String nickName = userVo.getNickName();
				User guser = this.findByKey(userVo.getUserId());
				guser.setNickName(nickName);
				SaveEntityUtils.setUpdateForEntity(guser, SystemConstant.OP_USER);
				this.update(SystemConstant.OP_USER, guser);
				responseCode =ResponseCode.SUCCESS;
			}

			if (transType.equals(TransType.USERFIVEELECHANGE.getValue())) {// 五要素信息修改
				SaveEntityUtils.setUpdateForEntity(userVo, SystemConstant.OP_USER);
				this.update(SystemConstant.OP_USER, userVo);
				responseCode =ResponseCode.SUCCESS;
			}
			
			if(redisCacheService.exists(User.class.getName()+Const.CACHESPLIT+userVo.getUserId())){
				redisCacheService.remove(User.class.getName()+Const.CACHESPLIT+userVo.getUserId());
			}
		} catch (Exception e) {
			logger.error("修改客户信息", e);
			responseCode =ResponseCode.FAILED;			
		}		
		return responseCode;
	}

	public ResponseCode directLogin(User user) {
		ResponseCode responseCode = ResponseCode.FAILED;		
		try {
			String userAccount = user.getOpenId();
			User guser = this.getUserByAccount(userAccount);
			if (guser != null) {				
				responseCode = ResponseCode.SUCCESS;				
			} else {
				responseCode = ResponseCode.USERPASSWORD_5;
			}			
		} catch (Exception e) {
			logger.error("login error",e);
			responseCode = ResponseCode.DATAREAD_ERROR;
		}
		return responseCode;

	}
	
	public User findUserByKey(String key){
	    User user = findByKey(key);
	    if(user == null){
	        user = new User();
	        user.setNickName("未知错误");
	    }
	    return user;
	}
	
	@Override
	public Page<User> getPage(User paramT, Page<User> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public User findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return userDao.find(paramString);
	}
	
	public User getUserByAccount(String account) throws MyBatisSystemException {
//		List<User> users = baseDao.getList(mapperName + "Mapper.getUserByAccount", account);
//		if (users != null && users.size() != 0) {
//			return users.get(0);
//		} else {
//			return null;
//		}
	    
	    return userDao.getUserByAccount(account);
	}

	public List<User> findByCondition(User user) {
		//return baseDao.getList(mapperName + "Mapper.getList", user);
	    return userDao.getList(user);
	}

	public User findByCertiCode(String certCode) {
	    return userDao.findByCertiCode(certCode);
	}
	public List<User> findByMap(Map<String, Object> fieldMap) {
//		if (fieldMap != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getListFromMap", fieldMap);
//			return userDao.get
//		}
		return Collections.emptyList();
	}

	public boolean exitAccount(String account) {
		try {
			User user = getUserByAccount(account);
			if (user == null) {
				return false;
			} else {
				return true;
			}
		} catch (MyBatisSystemException e) {
			return true;
		}
	}

	public boolean exitAccountByUser(User user) {
		String account = "";
		String userName = user.getUserAccount();
		String mobile = user.getUserMobile();
		String email = user.getUserEmail();
		String userId = user.getUserId();
		if (StringUtils.isNotBlank(userName)) {
			account = userName;
		} else if (StringUtils.isNotBlank(mobile)) {
			account = mobile;
		} else {
			account = email;
		}
		User guser = getUserByAccount(account);
		if (guser == null || (guser.getUserId().equals(userId))) {
			return true;
		} else {
			return false;
		}

	}

	@Transactional
	@Override
	public boolean save(String paramString, User paramT) throws DataBaseAccessException {
//		try {
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    userDao.add(paramT);
	    return Boolean.TRUE;
	}

	@Transactional
	@Override
	public boolean update(String paramString, User paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.update(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    userDao.update(paramT);
	    return Boolean.TRUE;
	}
	
	@Transactional
	@Override
	public boolean forceUpdate(String paramString, User paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.update(mapperName + "Mapper.forceUpdateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    userDao.forceUpdateByUid(paramT);
	    return Boolean.TRUE;
	}

	@Override
	public boolean delete(String paramString, User paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getUserId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    userDao.delete(paramT.getUserId());
	    return Boolean.TRUE;
	}

	@Override
	public User findUniqueByCondition(User user) {
		//return baseDao.get(mapperName + "Mapper.findUniqueByCondition", user);
	    return userDao.findUniqueByCondition(user);
	}

	public boolean updateOpenIdById(String paramString, User paramT) throws DataBaseAccessException{
//		try {
//			if (baseDao.save(mapperName + "Mapper.updateOpenIdByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
		userDao.updateOpenIdByUid(paramT);
		return Boolean.TRUE;
	}

	
	// added by G.F.
	/**
	 * 设置用户的个推 ID
	 * @param token
	 * @param cid
	 */
	public boolean setClientId(String token, String cid){
	    boolean flag = true;
	    UserAuthority userAuth = authorityService.findUserByToken(token);
	    if(userAuth == null){
	        flag = false;
	    }
	    
	    if(cid == null || StringUtils.isBlank(cid)){
	        flag = false;
	    }
	    
	    userDao.updateClientId(userAuth.getUid(), cid);
	    
	    return flag;
	}
	
	/**
	 * 根据身份证号查询用户
	 * @param certiCode
	 * @return
	 */
	public User findUserByCertiCode(String certiCode){
	    User user = userDao.findByCertiCode(certiCode);
	    if(user == null){
	        user = new User();
	        logger.error("用户不存在");
	    }
	    
	    return user;
	}
	
	/**
	 * 根据 token 查询用户信息, 包括账户
	 * @param token
	 * @return
	 */
	public UserInfoVo getUserInfo(String token){
	    UserAuthority authority = authorityService.findUserByToken(token);
	    User user = userDao.find(authority.getUid());
	    UserAccount account = accountService.findOneByUserId(authority.getUid());
	    UserInfoVo userInfo = new UserInfoVo(user, account);
	    
	    return userInfo;
	}
	
	/**
	 * 完善用户资料：姓名、身份证、手机
	 * @param user
	 * @param token
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public ResponseCode setPrivateInfos(User user, String token){
	    ResponseCode responseCode = ResponseCode.SUCCESS;
	    UserAuthority authority = authorityService.findUserByToken(token);
        User user2 = userDao.find(authority.getUid());
        if(user2 == null){
            responseCode = ResponseCode.USERNOTEXIST;
        }else if(user.getUserName() == null || StringUtils.isBlank(user.getUserName())){
            responseCode = ResponseCode.USERNAMENULL;
        }else if (user.getCertiCode() == null || StringUtils.isBlank(user.getCertiCode())) {
            responseCode = ResponseCode.USERCARDNULL;
        }else if (user.getUserMobile() == null || StringUtils.isBlank(user.getUserMobile())) {
            responseCode = ResponseCode.USERPHONENULL;
        }
        
        if(responseCode.equals(ResponseCode.SUCCESS)){
            user.setUserId(user2.getUserId());
            userDao.update(user);
        }
        
        return responseCode;
	}
	
	/**
	 * 获取用户状态
	 * @param token
	 * @param transId
	 * @return
	 */
	public UserStatusInfoVo getUserStatusInfo(String token){
	    UserAuthority authority = authorityService.findUserByToken(token);
        User user = userDao.find(authority.getUid());
        UserAccount account = new UserAccount(); 
        if(!user.getStatus().equals(UserStatus.SUCCESS.getValue())){
        	logger.info("用户未开户或开户失败");
             // 用户未开户或开户失败TODO
             account.setStatus(AccountStaus.NEW_USER.getValue());
        }else{
            // 用户已开户，同步状态
            bankService.queryAccount(token);  // 同步银行账户状态nyBankService
            account = accountService.findByKey(user.getUserId());
            logger.info("账户ID ＝ "+ account.getUserAccountId());
        }
        
        // 未读消息
        int unread = privMsgService.getUnreadMsgCount(authority.getUid());
        user.setMsgCount(unread);
        
        UserStatusInfoVo statusInfoVo = new UserStatusInfoVo(user, account);
        
        return statusInfoVo;
	}
	
	/**
	 * 根据 token 查找用户
	 * @param token
	 * @return
	 */
	public User getUser(String token){
	    UserAuthority authority = authorityService.findUserByToken(token);
        User user = userDao.find(authority.getUid());
        return user;
	}
	
	/**
	 * 组合用户开户信息
	 * @param token
	 * @param user
	 */
	public void getOpenAccountInfo(String token, User user){
	    User user2 = getUser(token);
	    user.setStatus(user2.getStatus());
	    user.setUserMobile(user2.getUserMobile());
	    user.setUserId(user2.getUserId());
	    user.setClientIp(user.getClientIp());
	}
	
	/**
	 * 根据用户姓名和身份证号更新用户信息
	 * @param user
	 * @throws ParseException 
	 */
	@Transactional
	public ResponseCode updateUserInfo(User user){
		ResponseCode  responseCode =ResponseCode.FAILED;
	    if(user != null){
	        try {
	        	User user1 = this.findByCertiCode(user.getCertiCode());
	        	if(user1 !=null){
	        		    if(user1.getCertiCode().equals(user.getCertiCode()) && !(user1.getUserId().equals(user.getUserId()))){
	        		    	return responseCode = ResponseCode.USERCARDNUMBEREXIT;
	        		    }
	        	}
	            // 从身份证解析生日
	        	if(user.getUserName() == null) return responseCode = ResponseCode.USERNAMENULL;
	        	if(StringUtils.isEmpty(user.getCertiCode())) return responseCode = ResponseCode.USERCARDNUMBERNULL;
	            if(user.getCertiCode().length() != 18) return responseCode = ResponseCode.USERCARDNUMBER;
	            String date=user.getCertiCode().substring(6, 14);
	            user.setUserBirthDay(DateUtils.parseDatetime(date, "yyyyMMdd"));
	             int a=Integer.parseInt(user.getCertiCode().substring(16, 17));
	             if(a%2==0){//女
	                       user.setUserSex("2");
	               }else{//男
	                       user.setUserSex("1");
	                  }
	              if(this.update(SystemConstant.OP_USER, user)){
	            	  return ResponseCode.USEROPENINFOSUCCESS;
	               }
	                
	            } catch (Exception e) {
	                logger.error("update user info. error...",e);
            }
	    }else{
	    	 logger.info("--------did not get user-------");
	    	 return ResponseCode.USERNOTEXIST;
	    }
		return responseCode;
	}
	@Transactional
	public ResponseCode updateUserInfo1(User user){
		ResponseCode  responseCode =ResponseCode.FAILED;
	    if(user != null){
	        try {
	            // 从身份证解析生日
	            if(user.getCertiCode().length() == 18){
	                String date=user.getCertiCode().substring(6, 14);
	                user.setUserBirthDay(DateUtils.parseDatetime(date, "yyyyMMdd"));
	                int a=Integer.parseInt(user.getCertiCode().substring(16, 17));
	                   if(a%2==0){//女
	                       user.setUserSex("2");
	                   }else{//男
	                       user.setUserSex("1");
	                   }
	                  if(this.update(SystemConstant.OP_USER, user)){
	                    }
	                }
	            } catch (Exception e) {
	                logger.error("update user info. error...",e);
            }
	    }else{
	    	 logger.info("--------did not get user-------");
	    }
		return responseCode;
	    
	}
}

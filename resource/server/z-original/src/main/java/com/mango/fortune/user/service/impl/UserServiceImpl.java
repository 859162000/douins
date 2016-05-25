package com.mango.fortune.user.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.cache.service.CacheService;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.enums.TransType;
import com.mango.fortune.user.enums.AccountType;
import com.mango.fortune.user.model.User;
import com.mango.fortune.user.service.UserService;
import com.mango.fortune.user.service.ValidateCodeService;
import com.mango.fortune.util.Const;
import com.mango.fortune.util.ReadConfig;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;
import com.mango.redis.RedisCacheService;

/**
 * 描述：<br>
 * 作者：wintrchen <br>
 * 修改日期：May 5, 20154:34:13 PM <br>
 * E-mail: winterchen@sinorfc.com <br>
 */
@Service
public class UserServiceImpl implements UserService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	public static String registsmscheck = ReadConfig.get("registsmscheck");
	@Autowired
	private BaseDao<User> baseDao;
	@Autowired
	@Qualifier("validateCodeService")
	private ValidateCodeService validateCodeService;
	@Autowired
	private RedisCacheService redisCacheService;
	@Autowired
	private CacheService cacheService;
	private final static int  expireTime = 1*3600;
	private String mapperName = User.class.getName();
	

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
				responseCode = ResponseCode.USERPASSWORD_4;
			}			
		} catch (Exception e) {
			logger.error("login error",e);
			responseCode = ResponseCode.DATAREAD_ERROR;
		}
		return responseCode;

	}
	
	
	
	
	@Transactional(rollbackFor=Exception.class)
	public ResponseCode regist(User user, String ip,String verificationCode,String transType,String transChannel) {		
		ResponseCode responseCode = ResponseCode.FAILED;
		try {
			boolean registsms=true;
			if("false".equals(registsmscheck)){
				registsms=true;
			}else{
				registsms = validateCodeService.checkSms(transType, user.getUserAccount(), verificationCode);
			}
			if(registsms){
				if (this.exitAccount(user.getUserAccount())) {
					responseCode = ResponseCode.USERREGIST_1;
				} else {
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
					user.setStatus("1");
					SaveEntityUtils.initEntityBeforeInsert(user, SystemConstant.OP_USER);
					this.save(SystemConstant.OP_USER, user);
					
					responseCode =ResponseCode.SUCCESS;					
				}	
			}else{
				responseCode = ResponseCode.VERIFICATIONCODE_MOBILE;				
			}			

		} catch (Exception e) {
			logger.error("客户注册失败", e);
			responseCode =ResponseCode.FAILED;
		}
		return responseCode;
		
		
	}

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
	
	@Override
	public Page<User> getPage(User paramT, Page<User> paramPage) {
		if (paramPage != null) {
			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
		}
		return null;
	}

	@Override
	public User findByKey(String paramString) {
		return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	}
	
	public User getUserByAccount(String account) throws MyBatisSystemException {
		List<User> users = baseDao.getList(mapperName + "Mapper.getUserByAccount", account);
		if (users != null && users.size() != 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	public List<User> findByCondition(User user) {
		return baseDao.getList(mapperName + "Mapper.getList", user);
	}

	public List<User> findByMap(Map<String, Object> fieldMap) {
		if (fieldMap != null) {
			return this.baseDao.getList(mapperName + "Mapper.getListFromMap", fieldMap);
		}
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

	@Override
	public boolean save(String paramString, User paramT) throws DataBaseAccessException {
		try {
			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
		} catch (DataBaseAccessException e) {
			logger.error("create DataBaseAccessException", e);
			throw e;
		}
	}

	@Override
	public boolean update(String paramString, User paramT) throws DataBaseAccessException {
		try {
			if (baseDao.update(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("update DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}
	@Override
	public boolean forceUpdate(String paramString, User paramT) throws DataBaseAccessException {
		try {
			if (baseDao.update(mapperName + "Mapper.forceUpdateByPrimaryKey", paramT) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("update DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean delete(String paramString, User paramT) throws DataBaseAccessException {
		try {
			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getUserId()) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("delete DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}

	@Override
	public User findUniqueByCondition(User user) {
		return baseDao.get(mapperName + "Mapper.findUniqueByCondition", user);
	}

	public boolean updateOpenIdById(String paramString, User paramT) throws DataBaseAccessException{
		try {
			if (baseDao.save(mapperName + "Mapper.updateOpenIdByPrimaryKey", paramT) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("update DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
		
	}

}

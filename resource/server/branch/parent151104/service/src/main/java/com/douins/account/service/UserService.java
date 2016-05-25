package com.douins.account.service;

import com.douins.account.domain.model.User;
import com.douins.account.domain.vo.UserInfoVo;
import com.douins.account.domain.vo.UserStatusInfoVo;
import com.douins.trans.domain.enums.ResponseCode;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.DbOperateService;

public interface UserService extends DbOperateService<User>{
	public ResponseCode login(User user);
	public ResponseCode regist(User user, String ip,String verificationCode,String transType,String transChannel);
	public ResponseCode modify(User userVo,String transType) ;
	public boolean forceUpdate(String paramString, User paramT) throws DataBaseAccessException;
	public User findUniqueByCondition(User user) ;
	public boolean exitAccount(String account);
	
	public User findUserByKey(String key);
	
	public boolean setClientId(String token, String cid);
	public User findUserByCertiCode(String sertiCode);
	public UserInfoVo getUserInfo(String token);
	public ResponseCode setPrivateInfos(User user, String token);
	public UserStatusInfoVo getUserStatusInfo(String token);

//	public List<User> findByCondition(User user) ;
//	public User getUserByAccount(String account)throws MyBatisSystemException;
//	
//
//
//	public boolean exitAccountByUser(User user);
//	public boolean exitAccount(String account);
//	
//	public List<User> findByMap(Map<String, Object> fieldMap) ;
//	public boolean updateOpenIdById(String paramString, User paramT) throws DataBaseAccessException;
//	public ResponseCode directLogin(User user);
	
	
}

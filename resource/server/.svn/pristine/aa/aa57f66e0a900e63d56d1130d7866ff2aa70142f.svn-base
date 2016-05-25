package com.mango.fortune.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.api.userPayAccountAPI.vo.UserPayAccountVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.account.enums.OpenStatus;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.model.UserPayAccount;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.account.service.UserPayAccountService;
import com.mango.fortune.bank.enums.BankType;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.user.model.User;
import com.mango.fortune.user.service.UserService;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class UserPayAccountServiceImpl implements UserPayAccountService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private BaseDao<UserPayAccount> baseDao;
	@Autowired
	private BaseDao<UserPayAccountVo> baseVoDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountService userAccountService;
	
	String mapperName = UserPayAccount.class.getName();
	
	public ResponseCode add4Api(UserPayAccount payAccountVo,String transChannel) throws Exception {
		ResponseCode responseCode=ResponseCode.FAILED;
		UserPayAccount qUserPayAccount = new UserPayAccount();
		qUserPayAccount.setAccountNo(payAccountVo.getAccountNo());
		qUserPayAccount.setAccountType(payAccountVo.getAccountType());
		List<UserPayAccount> qPayList= findByCondition(qUserPayAccount);
		if(qPayList!=null&&qPayList.size()>0){
			responseCode = ResponseCode.USERBANK_1;	
		}else{
			//绑定银行卡
			boolean isOpen=true;
			UserPayAccount myPayAccount=findOneByUserId(payAccountVo.getUserId());
			if(myPayAccount==null){
				BankType bt = BankType.getBankByCode(payAccountVo.getBankCode());
				payAccountVo.setAccountName(bt.getName());
				payAccountVo.setStatus(OpenStatus.INIT.getValue());
				SaveEntityUtils.initEntityBeforeInsert(payAccountVo, SystemConstant.OP_USER);
				this.save(SystemConstant.OP_USER, payAccountVo);
			}else{
				if(myPayAccount.getStatus().equals(OpenStatus.SUCCESS.getValue())){
					isOpen=false;
					responseCode=ResponseCode.USEROPENERROR;
				}else{
					BankType bt = BankType.getBankByCode(payAccountVo.getBankCode());
					payAccountVo.setAccountName(bt.getName());
					payAccountVo.setStatus(OpenStatus.INIT.getValue());
					payAccountVo.setAccountId(myPayAccount.getAccountId());
					SaveEntityUtils.setUpdateForEntity(payAccountVo, SystemConstant.OP_USER);
					this.update(SystemConstant.OP_USER, payAccountVo);
				}
			}
			if(isOpen){
				//开户
				responseCode=userAccountService.add(payAccountVo.getUserId(),transChannel);
				//更新绑定银行卡表
				UserPayAccount payAccount=new UserPayAccount();
				payAccount.setAccountId(payAccountVo.getAccountId());
				if (responseCode.equals(ResponseCode.SUCCESS)) {
					UserAccount userAccount=userAccountService.findOneByUserId(payAccountVo.getUserId());
					payAccount.setUserAccountId(userAccount.getUserAccountId());
					payAccount.setStatus(OpenStatus.SUCCESS.getValue());
					responseCode=ResponseCode.SUCCESS;
				}else{
					payAccount.setStatus(OpenStatus.FAILURE.getValue());
				}
				SaveEntityUtils.setUpdateForEntity(payAccount, SystemConstant.OP_USER);
				this.update(SystemConstant.OP_USER, payAccount);
				//更新User
				User user =  new User();
				user.setUserName(payAccountVo.getCardholderName());
				user.setCertiCode(payAccountVo.getCardholderCerticode());
				user.setCertiType(payAccountVo.getCardholderCertitype());
				user.setUserId(payAccountVo.getUserId());
				user.setPayPassword(payAccountVo.getPayPassword());
				user.setSwitchPayPassword("1");
				SaveEntityUtils.setUpdateForEntity(user, SystemConstant.OP_USER);
				this.userService.update(SystemConstant.OP_USER, user);
			}
		}
		return responseCode;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseCode change4Api(UserPayAccount payAccountVo)throws Exception {
		ResponseCode responseCode = ResponseCode.FAILED;
		User vUser = userService.findByKey(payAccountVo.getUserId());
		if (!vUser.getPayPassword().equals(payAccountVo.getPayPassword())) {
			responseCode = ResponseCode.ORDERPAYPASSWORD;
		} else {
			UserPayAccount userPayAccount_para = new UserPayAccount();
			userPayAccount_para.setAccountNo(payAccountVo.getAccountNo());
			userPayAccount_para.setAccountType(payAccountVo.getAccountType());
			List<UserPayAccount> userbBanks = findByCondition(userPayAccount_para);
			if (userbBanks != null && userbBanks.size() > 0) {
				responseCode = ResponseCode.USERBANK_1;
			} else {
				// 删除已绑定的银行卡
				UserPayAccount queryUserPayAccount = new UserPayAccount();
				queryUserPayAccount.setUserId(payAccountVo.getUserId());
				List<UserPayAccount> payAccounts = findByCondition(queryUserPayAccount);
				this.delete(SystemConstant.OP_USER, payAccounts.get(0));
				// 保存新的银行卡
				SaveEntityUtils.initEntityBeforeInsert(payAccountVo,SystemConstant.OP_USER);
				payAccountVo.setAccountName(BankType.getNameByValue(payAccountVo.getBankCode()));
				payAccountVo.setUserAccountId(payAccounts.get(0).getUserAccountId());
				payAccountVo.setStatus(OpenStatus.SUCCESS.getValue());
				this.save(SystemConstant.OP_USER, payAccountVo);

				User user = new User();
				user.setUserName(payAccountVo.getCardholderName());
				user.setCertiCode(payAccountVo.getCardholderCerticode());
				user.setCertiType(payAccountVo.getCardholderCertitype());
				user.setUserId(payAccountVo.getUserId());
				SaveEntityUtils.setUpdateForEntity(user, SystemConstant.OP_USER);
				userService.update(SystemConstant.OP_USER, user);
				responseCode = ResponseCode.SUCCESS;
			}
		}
		return responseCode;
	}
	
	@Override
	public ResponseCode validate(UserPayAccount payAccountVo) {
		ResponseCode responseCode = ResponseCode.FAILED;
		User vUser = userService.findByKey(payAccountVo.getUserId());
		if (!vUser.getPayPassword().equals(payAccountVo.getPayPassword())) {
			responseCode = ResponseCode.ORDERPAYPASSWORD;
		} else {
			responseCode = ResponseCode.SUCCESS;
		}
		return responseCode;
	}
	
	@Override
	public UserPayAccount findOneByUserId(String userId) {
		UserPayAccount paramT=new UserPayAccount();
		paramT.setUserId(userId);
		List<UserPayAccount> list=this.findByCondition(paramT);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<UserPayAccount> findByCondition(UserPayAccount bank) {
		return baseDao.getList(mapperName + "Mapper.getList", bank);
	}
	
	@Override
	public Page<UserPayAccount> getPage(UserPayAccount paramT, Page<UserPayAccount> paramPage) {
		if (paramPage != null) {
			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
		}
		return null;
	}

	@Override
	public UserPayAccount findByKey(String paramString) {
		return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	}

	@Override
	public boolean save(String paramString, UserPayAccount paramT) throws DataBaseAccessException {
		try {
			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
		} catch (DataBaseAccessException e) {
			logger.error("create DataBaseAccessException", e);
			throw e;
		}
	}

	@Override
	public boolean update(String paramString, UserPayAccount paramT) throws DataBaseAccessException {
		try {
			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("update DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean delete(String paramString, UserPayAccount paramT) throws DataBaseAccessException {
		try {
			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getAccountId()) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("delete DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}
}

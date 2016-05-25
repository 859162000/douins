package com.mango.fortune.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.account.enums.OpenStatus;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.model.UserAccountOpenApply;
import com.mango.fortune.account.service.UserAccountOpenApplyService;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.pay.service.impl.UnionPayServiceImpl;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class UserAccountOpenApplyServiceImpl implements UserAccountOpenApplyService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private BaseDao<UserAccountOpenApply> baseDao;
	@Autowired
	private UnionPayServiceImpl unionPayService;
	@Autowired
	private UserAccountService userAccountService;
	
	String mapperName = UserAccountOpenApply.class.getName();
	
	@Override
	public ApplyResult openApply(String userAccountId, String userId,String transChannel) throws Exception {
		UserAccountOpenApply openApply=new UserAccountOpenApply();
		openApply.setUserId(userId);
		openApply.setAccountId(userAccountId);
		openApply.setStatus(OpenStatus.INIT.getValue());
		SaveEntityUtils.initEntityBeforeInsert(openApply, SystemConstant.OP_USER);
		this.save(SystemConstant.OP_USER, openApply);
		
		ApplyResult openResp=unionPayService.openApply(openApply, transChannel);
		
		if(openResp.isSuccess()){
			openApply.setStatus(OpenStatus.SUCCESS.getValue());
		}else{
			openApply.setStatus(OpenStatus.FAILURE.getValue());
		}
		
		//更新虚拟账户
		UserAccount userAccount = userAccountService.findByKey(userAccountId);
		userAccount.setStatus(openApply.getStatus());
		if(openResp.isSuccess()){
			userAccount.setVirtualAccountNo(openResp.getVirtualAccountNo());
			openApply.setVirtualAccountNo(openResp.getVirtualAccountNo());
		}
		SaveEntityUtils.setUpdateForEntity(userAccount, SystemConstant.OP_USER);
		userAccountService.update(SystemConstant.OP_USER, userAccount);
		
		//更新开户申请表
		SaveEntityUtils.setUpdateForEntity(openApply, SystemConstant.OP_USER);
		this.update(SystemConstant.OP_USER, openApply);
		
		return openResp;
	}
	
	@Override
	public List<UserAccountOpenApply> findByCondition(UserAccountOpenApply paramT) {
		return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	}

	@Override
	public Page<UserAccountOpenApply> getPage(UserAccountOpenApply paramT, Page<UserAccountOpenApply> paramPage) {
		if (paramPage != null) {
			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
		}
		return null;
	}

	@Override
	public UserAccountOpenApply findByKey(String paramString) {
		return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	}

	@Override
	public boolean save(String paramString, UserAccountOpenApply paramT) throws DataBaseAccessException {
		try {
			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
		} catch (DataBaseAccessException e) {
			logger.error("create DataBaseAccessException", e);
			throw e;
		}
	}

	@Override
	public boolean update(String paramString, UserAccountOpenApply paramT) throws DataBaseAccessException {
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
	public boolean delete(String paramString, UserAccountOpenApply paramT) throws DataBaseAccessException {
		try {
			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getOpenApplyId()) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("delete DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}

}
package com.douins.account.service.iml;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.account.dao.UserAccountOpenApplyDao;
import com.douins.account.domain.enums.UserStatus;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAccountOpenApply;
import com.douins.account.service.UserAccountOpenApplyService;
import com.douins.account.service.UserAccountService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.pay.service.impl.UnionPayServiceImpl;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class UserAccountOpenApplyServiceImpl implements UserAccountOpenApplyService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<UserAccountOpenApply> baseDao;
	@Autowired
	private UnionPayServiceImpl unionPayService;
	@Autowired
	private UserAccountService userAccountService;
	
	@Inject
	private UserAccountOpenApplyDao applyDao;
	
	//String mapperName = UserAccountOpenApply.class.getName();
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ApplyResult openApply(String userAccountId, String userId,String transChannel) throws Exception {
		UserAccountOpenApply openApply=new UserAccountOpenApply();
		openApply.setUserId(userId);
		openApply.setAccountId(userAccountId);
		openApply.setStatus(UserStatus.INIT.getValue());
		SaveEntityUtils.initEntityBeforeInsert(openApply, SystemConstant.OP_USER);
		this.save(SystemConstant.OP_USER, openApply);
		
		ApplyResult openResp=unionPayService.openApply(openApply, transChannel);
		
		if(openResp.isSuccess()){
			openApply.setStatus(UserStatus.SUCCESS.getValue());
		}else{
			openApply.setStatus(UserStatus.FAILURE.getValue());
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
		//return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	    return applyDao.getList(paramT);
	}

	@Override
	public Page<UserAccountOpenApply> getPage(UserAccountOpenApply paramT, Page<UserAccountOpenApply> paramPage) {
//		if (paramPage != null) {
//			//return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		    return applyDao.getList(paramT);
//		}
		return null;
	}

	@Override
	public UserAccountOpenApply findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return applyDao.findByApplyId(paramString);
	}

	@Override
	public boolean save(String paramString, UserAccountOpenApply paramT) throws DataBaseAccessException {
//		try {
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    
	    applyDao.add(paramT);
	    return true;
	}

	@Override
	public boolean update(String paramString, UserAccountOpenApply paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    
	    applyDao.update(paramT);
	    return Boolean.TRUE;
	}

	@Override
	public boolean delete(String paramString, UserAccountOpenApply paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getOpenApplyId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    
	    applyDao.delete(paramT.getOpenApplyId());
	    return Boolean.FALSE;
	}

}
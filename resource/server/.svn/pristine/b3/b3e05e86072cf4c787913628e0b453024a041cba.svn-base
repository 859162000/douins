package com.douins.account.service.iml;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.account.dao.UserAccountDetailDao;
import com.douins.account.domain.enums.DetailType; 
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.service.UserAccountDetailService;
import com.douins.account.service.UserAccountService;
import com.douins.account.service.UserPayAccountService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.bank.domain.enums.BankType;
import com.douins.pay.domain.enums.PayStatus;
import com.douins.pay.service.impl.UnionPayServiceImpl;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class UserAccountDetailServiceImpl implements UserAccountDetailService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<UserAccountDetail> baseDao;
	@Autowired
	private UnionPayServiceImpl unionPayService;
	@Autowired
	private UserPayAccountService userPayAccountService;
	@Autowired
	private UserAccountService userAccountService;
	
	@Inject UserAccountDetailDao detailDao;
	
//	String mapperName = UserAccountDetail.class.getName();
	
	public ApplyResult doBusiness(UserAccountDetail detail,String transChannel) throws Exception {
		logger.info("bankCOde=======>"+detail.getFromBankCode());
		detail.setFromBankName(BankType.getNameByValue(detail.getFromBankCode()));
		detail.setToBankName(BankType.getNameByValue(detail.getToBankCode()));
		detail.setApplyTime(new Date());
		detail.setStatus(PayStatus.NOTTOPAY.getValue());
		SaveEntityUtils.initEntityBeforeInsert(detail, SystemConstant.OP_USER);
		this.save(SystemConstant.OP_USER, detail);
		
		ApplyResult result = unionPayService.doPay(detail, transChannel);
		
		//更新表数据
		this.doResultAfterPay(result.isSuccess(), detail);
		return result;
	}
	
	private void doResultAfterPay(boolean isSuccess,UserAccountDetail detail) throws DataBaseAccessException {
		if(isSuccess){
			UserAccount userAccount=userAccountService.findByKey(detail.getUserAccountId());
			//更新虚拟账户
			if (detail.getDetailType().equals(DetailType.RECHARGE.getValue())) {
				userAccount.setAccountAmount(userAccount.getAccountAmount().add(detail.getApplyAmount()));
				userAccount.setAccountBalance(userAccount.getAccountBalance().add(detail.getApplyAmount()));
			} else if (detail.getDetailType().equals(DetailType.WITHDRAW.getValue())) {
				userAccount.setAccountAmount(userAccount.getAccountAmount().subtract(detail.getApplyAmount()));
				userAccount.setAccountBalance(userAccount.getAccountBalance().subtract(detail.getApplyAmount()));
			}else if(detail.getDetailType().equals(DetailType.BUYPOLICY.getValue())){
				userAccount.setPolicyValue(userAccount.getPolicyValue().add(detail.getApplyAmount()));
				userAccount.setAccountBalance(userAccount.getAccountBalance().subtract(detail.getApplyAmount()));
			}else if(detail.getDetailType().equals(DetailType.POLICYLOAN.getValue())){
				userAccount.setAccountAmount(userAccount.getAccountAmount().add(detail.getApplyAmount()));
				userAccount.setAccountBalance(userAccount.getAccountBalance().add(detail.getApplyAmount()));
				
				userAccount.setLoanAmount(userAccount.getLoanAmount().add(detail.getPrincipalAmount()));
			}else if(detail.getDetailType().equals(DetailType.POLICYREPAY.getValue())){
				userAccount.setAccountAmount(userAccount.getAccountAmount().subtract(detail.getApplyAmount()));
				userAccount.setAccountBalance(userAccount.getAccountBalance().subtract(detail.getApplyAmount()));
				
				userAccount.setLoanAmount(userAccount.getLoanAmount().subtract(detail.getPrincipalAmount()));
				userAccount.setRepayAmount(userAccount.getRepayAmount().add(detail.getApplyAmount()));
			}else if(detail.getDetailType().equals(DetailType.POLICYSURRENDER.getValue())){
				userAccount.setAccountAmount(userAccount.getAccountAmount().subtract(detail.getPrincipalAmount()).add(detail.getApplyAmount()));
				userAccount.setAccountBalance(userAccount.getAccountBalance().add(detail.getApplyAmount()));
				
				userAccount.setPolicyValue(userAccount.getPolicyValue().subtract(detail.getPrincipalAmount()));
				userAccount.setSurrAmount(userAccount.getSurrAmount().add(detail.getApplyAmount()));
			}else{
				throw new DataBaseAccessException("detailType参数错误！");
			}
			SaveEntityUtils.setUpdateForEntity(userAccount,SystemConstant.OP_USER);
			userAccountService.update(SystemConstant.OP_USER, userAccount);
			
			detail.setStatus(PayStatus.SUCCESS.getValue());
			detail.setEndTime(new Date());
		}else{
			detail.setStatus(PayStatus.FAILURE.getValue());
			detail.setEndTime(new Date());
		}
		//更新虚拟账户记录表
		SaveEntityUtils.setUpdateForEntity(detail, SystemConstant.OP_USER);
		this.update(SystemConstant.OP_USER, detail);
	}
	
	@Override
	public List<UserAccountDetail> findByCondition(UserAccountDetail paramT) {
		//return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	    return detailDao.getList(paramT);
	}

	@Override
	public Page<UserAccountDetail> getPage(UserAccountDetail paramT, Page<UserAccountDetail> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public UserAccountDetail findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return detailDao.findByDetailId(paramString);
	}

	@Override
	public boolean save(String paramString, UserAccountDetail paramT) throws DataBaseAccessException {
//		try {
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    detailDao.add(paramT);
	    return true;
	}

	@Override
	public boolean update(String paramString, UserAccountDetail paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    detailDao.update(paramT);
	    return true;
	}

	@Override
	public boolean delete(String paramString, UserAccountDetail paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getUserAccountDetailId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    detailDao.delete(paramT.getUserAccountDetailId());
	    return true;
	}
}
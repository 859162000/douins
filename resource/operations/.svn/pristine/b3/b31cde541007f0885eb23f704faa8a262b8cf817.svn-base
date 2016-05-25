package com.douins.pay.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.account.domain.enums.DetailIo;
import com.douins.account.domain.enums.DetailType;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.service.UserAccountDetailService;
import com.douins.account.service.UserAccountService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.pay.dao.PaymentApplyDao;
import com.douins.pay.domain.enums.PayStatus;
import com.douins.pay.domain.enums.PayType;
import com.douins.pay.domain.enums.ThirdpayType;
import com.douins.pay.domain.model.PaymentApply;
import com.douins.pay.service.PaymentApplyService;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.service.PolicyMasterService;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class PaymentApplyServiceImpl implements PaymentApplyService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private PolicyMasterService policyMasterService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccountDetailService userAccountDetailService;
//	@Autowired
//	private BaseDao<PaymentApply> baseDao;
//	
//	String mapperName =PaymentApply.class.getName();
	
	@Inject
	private PaymentApplyDao applyDao;
	
	@Override
	public ApplyResult payApply(PolicyMaster policy,String transChannel) throws Exception {
		PaymentApply apply=new PaymentApply();
		UserAccountDetail detail = new UserAccountDetail();
		this.saveDetailAndApply(policy,apply,detail);
		
		ApplyResult result=userAccountDetailService.doBusiness(detail, transChannel);
		
		this.dealResResult(result,apply);
		return result;
	}

	private void saveDetailAndApply(PolicyMaster policy,PaymentApply paymentApply,
			UserAccountDetail detail) throws DataBaseAccessException {
		UserAccount fromAccount = userAccountService.findOneByUserId(policy.getUserId());
		paymentApply.setUserAccountId(fromAccount.getUserAccountId());
		paymentApply.setPolicyId(policy.getPolicyId());
		paymentApply.setPayOrderNo(policy.getPayOrderNo());
		paymentApply.setPayType(PayType.ONLINEBANK.getValue());
		paymentApply.setThirdpayType(ThirdpayType.CHINAPAYMENT.getValue());
		paymentApply.setFromAccountName("");
		paymentApply.setFromAccountNo(fromAccount.getVirtualAccountNo());
		paymentApply.setFromBankCode("");
		paymentApply.setToAccountName("test");
		paymentApply.setToAccountNo("test");
		paymentApply.setToBankCode("test");
		paymentApply.setPayApplyTime(policy.getApplyTime());
		paymentApply.setPayMoney(policy.getTotalPrem());
		paymentApply.setStatus(PayStatus.NOTTOPAY.getValue());
		SaveEntityUtils.initEntityBeforeInsert(paymentApply, SystemConstant.OP_USER);
		this.save(SystemConstant.OP_USER, paymentApply);
		
		detail.setUserAccountId(fromAccount.getUserAccountId());
		detail.setBusinessId(policy.getPolicyId());
		detail.setBusinessNo(policy.getOrderNo());
		detail.setApplyAmount(policy.getTotalPrem());
		detail.setDetailType(DetailType.BUYPOLICY.getValue());
		detail.setDetailIo(DetailIo.OUT.getValue());
		detail.setFromAccountName("");
		detail.setFromAccountNo(fromAccount.getVirtualAccountNo());
		detail.setFromBankCode("");
		detail.setToAccountName("test");
		detail.setToAccountNo("test");
		detail.setToBankCode("test");
		detail.setPrincipalAmount(policy.getTotalPrem());
	}
	
	private void dealResResult(ApplyResult result,PaymentApply apply) throws DataBaseAccessException {
		if(result.isSuccess()){
			apply.setStatus(PayStatus.SUCCESS.getValue());
		}else{
			apply.setStatus(PayStatus.FAILURE.getValue());
		}
		apply.setPayTime(result.getFinishTime());
		SaveEntityUtils.setUpdateForEntity(apply, SystemConstant.OP_USER);
		this.update(SystemConstant.OP_USER, apply);
	}

	@Override
	public List<PaymentApply> findByCondition(PaymentApply paramT) {
		//return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	    return applyDao.getList(paramT);
	}
	
	@Override
	public Page<PaymentApply> getPage(PaymentApply paramT, Page<PaymentApply> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public PaymentApply findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return applyDao.selectByPrimaryKey(paramString);
	}

	@Override
	public boolean save(String paramString, PaymentApply paramT) throws DataBaseAccessException {
//		try {
//			SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    
	    SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
	    applyDao.insert(paramT);
	    return true;
	}

	@Override
	public boolean update(String paramString, PaymentApply paramT) throws DataBaseAccessException {
//		try {
//			SaveEntityUtils.setUpdateForEntity(paramT, SystemConstant.OP_USER);
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    
	    SaveEntityUtils.setUpdateForEntity(paramT, SystemConstant.OP_USER);
	    applyDao.updateByPrimaryKey(paramT);
	    return true;
	}

	@Override
	public boolean delete(String paramString, PaymentApply paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getPaymentApplyId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    applyDao.softDeleteByPrimaryKey(paramT.getPaymentApplyId());
	    return true;
	}
}

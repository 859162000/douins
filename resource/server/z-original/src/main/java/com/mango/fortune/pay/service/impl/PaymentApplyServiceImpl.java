package com.mango.fortune.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.account.enums.DetailIo;
import com.mango.fortune.account.enums.DetailType;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.model.UserAccountDetail;
import com.mango.fortune.account.service.UserAccountDetailService;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.pay.enums.PayStatus;
import com.mango.fortune.pay.enums.PayType;
import com.mango.fortune.pay.enums.ThirdpayType;
import com.mango.fortune.pay.model.PaymentApply;
import com.mango.fortune.pay.service.PaymentApplyService;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.fortune.policy.service.PolicyMasterService;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SystemConstant;
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
	@Autowired
	private BaseDao<PaymentApply> baseDao;
	
	String mapperName =PaymentApply.class.getName();
	
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
		return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	}
	
	@Override
	public Page<PaymentApply> getPage(PaymentApply paramT, Page<PaymentApply> paramPage) {
		if (paramPage != null) {
			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
		}
		return null;
	}

	@Override
	public PaymentApply findByKey(String paramString) {
		return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	}

	@Override
	public boolean save(String paramString, PaymentApply paramT) throws DataBaseAccessException {
		try {
			SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
		} catch (DataBaseAccessException e) {
			logger.error("create DataBaseAccessException", e);
			throw e;
		}
	}

	@Override
	public boolean update(String paramString, PaymentApply paramT) throws DataBaseAccessException {
		try {
			SaveEntityUtils.setUpdateForEntity(paramT, SystemConstant.OP_USER);
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
	public boolean delete(String paramString, PaymentApply paramT) throws DataBaseAccessException {
		try {
			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getPaymentApplyId()) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("delete DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}
}

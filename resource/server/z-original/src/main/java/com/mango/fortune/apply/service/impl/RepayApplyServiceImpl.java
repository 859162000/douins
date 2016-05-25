package com.mango.fortune.apply.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.common.util.DateUtils;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.account.enums.DetailIo;
import com.mango.fortune.account.enums.DetailType;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.model.UserAccountDetail;
import com.mango.fortune.account.service.UserAccountDetailService;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.apply.enums.LoanStatus;
import com.mango.fortune.apply.enums.RepayStatus;
import com.mango.fortune.apply.enums.RepayType;
import com.mango.fortune.apply.model.ApplyInfo;
import com.mango.fortune.apply.model.LoanApply;
import com.mango.fortune.apply.model.RepayApply;
import com.mango.fortune.apply.service.BaseApplyInfoService;
import com.mango.fortune.apply.service.LoanApplyService;
import com.mango.fortune.apply.service.RepayApplyService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.policy.enums.PolicyStatus;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.fortune.policy.service.PolicyMasterService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SeqNumUtils;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("repayApplyService")
public class RepayApplyServiceImpl extends BaseApplyInfoService implements
		RepayApplyService {
	@Autowired
	private PolicyMasterService policyMasterService;
	
	@Autowired
	private BaseDao<RepayApply> baseDao;
	
	private String mapper = RepayApply.class.getName();
	
	@Autowired
	private LoanApplyService loanApplyService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAccountDetailService userAccountDetailService;

	@Override
	public ApplyResult checkApply(ApplyInfo applyInfo) throws Exception {
		ApplyResult applyResult = new ApplyResult();
		boolean isSuccess = true;
		String resultCode = ResponseCode.SUCCESS.getValue();
		PolicyMaster policyMaster = policyMasterService.findByKey(applyInfo.getPolicyId());
		if(policyMaster == null){
			resultCode = ResponseCode.POLICYISNOTEXISTS.getValue();
			isSuccess = false;
		}else{
			if(!PolicyStatus.LOANING.getValue().equals(policyMaster.getStatus())){
				resultCode = ResponseCode.POLICYNOTREPAY.getValue();
				isSuccess = false;
			}
			LoanApply loanApply = loanApplyService.findByKey(applyInfo.getBusinessId());
			if(loanApply == null || !LoanStatus.LOANINGEND.getValue().equals(loanApply.getStatus())){
				resultCode = ResponseCode.POLICYNOTREPAY.getValue();
				isSuccess = false;
			}
			
		}
		applyResult.setSuccess(isSuccess);
		applyResult.setResultCode(resultCode);
		return applyResult;
	}

	@Override
	public ApplyResult doApplyImpl(ApplyInfo applyInfo) throws Exception {
		ApplyResult applyResult = new ApplyResult();
		boolean isSuccess = false;
		//TODO 更改policymaster状态
		PolicyMaster policyMaster = policyMasterService.findByKey(applyInfo.getPolicyId());
		
		LoanApply loanApply = loanApplyService.findByKey(applyInfo.getBusinessId());
		
		RepayApply repayApply = new RepayApply();
		repayApply.setApplyAmount(loanApply.getTotalRepayAmount());
		repayApply.setPolicyId(applyInfo.getPolicyId());
		repayApply.setPolicyCode(policyMaster.getPolicyCode());
		repayApply.setApplyCustomerId(policyMaster.getUserId());
		repayApply.setApplyInfoId(applyInfo.getApplyInfoId());
		repayApply.setLoanApplyId(loanApply.getLoanApplyId());
		repayApply.setRepayNo(SeqNumUtils.geneTxSN(SeqNumUtils.REPAY_PREFIX));
		repayApply.setRepayValue(loanApply.getApplyAmount());
		repayApply.setRepayType(RepayType.REPAYNORMAL.getValue());
		repayApply.setLoanCompany(loanApply.getLoanCompany());
		repayApply.setInsuranceCompany(repayApply.getInsuranceCompany());
		repayApply.setRepayRate(loanApply.getLoanInterest());
		repayApply.setStatus(RepayStatus.REPAYING.getValue());
		repayApply.setHandfee(BigDecimal.ZERO);
		repayApply.setActualRepayAmount(loanApply.getTotalRepayAmount());
		repayApply.setLoanCompany(loanApply.getLoanCompany());
		repayApply.setInsuranceCompany(loanApply.getInsuranceCompany());
		
		SaveEntityUtils.initEntityBeforeInsert(repayApply, "");
		this.save("", repayApply);
		
		
		//发送还款请求到第三方
		ApplyResult payResult = this.doReqToThirdPay(repayApply);
		
		if(payResult.isSuccess()){
			//付款成功后
			loanApply.setStatus(LoanStatus.LOANFINISHED.getValue());
			SaveEntityUtils.setUpdateForEntity(loanApply, "");
			loanApplyService.updateAfter("", loanApply);
			
			SaveEntityUtils.setUpdateForEntity(repayApply, "");
			repayApply.setStatus(RepayStatus.REPAYEND.getValue());
			repayApply.setRepayTime(DateUtils.today());
			this.updateAfter("", repayApply);
			
			policyMaster.setStatus(PolicyStatus.INSURESUCCESS.getValue());
			policyMasterService.update("", policyMaster);
			
			isSuccess = true;
		}
		
		applyResult.setSuccess(isSuccess);
		applyResult.setResultCode(ResponseCode.SUCCESS.getValue());
		return applyResult;
	}
	
	public ApplyResult doReqToThirdPay(RepayApply repayApply) throws Exception{
		UserAccount userAccount = userAccountService.findOneByUserId(repayApply.getApplyCustomerId());
		
		UserAccountDetail detail=new UserAccountDetail();
		detail.setUserAccountId(userAccount.getUserAccountId());
		detail.setBusinessId(repayApply.getRepayApplyId());
		detail.setBusinessNo(repayApply.getRepayNo());
		detail.setApplyAmount(repayApply.getActualRepayAmount());
		detail.setDetailType(DetailType.POLICYREPAY.getValue());
		detail.setDetailIo(DetailIo.OUT.getValue());
		detail.setPrincipalAmount(repayApply.getRepayValue());
		detail.setFromAccountNo(userAccount.getVirtualAccountNo());
		detail.setPrincipalAmount(repayApply.getRepayValue());
		/*detail.setFromAccountName(userAccount.getVirtualAccountNo());
		detail.setFromBankCode(fromBankCode);
		detail.setFromBankName(BankType.getNameByValue(fromBankCode));*/
		detail.setToAccountName("银行");
		/*detail.setToAccountNo(toAccountNo);
		detail.setToBankCode(toBankCode);*/
		//detail.setToBankName(BankType.getNameByValue(toBankCode));
		
		ApplyResult applyResult = userAccountDetailService.doBusiness(detail, "");
		
		return applyResult;
	}
	
	@Override
	public boolean delete(String arg0, RepayApply arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RepayApply findByKey(String key) {
		return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	}

	@Override
	public Page<RepayApply> getPage(RepayApply arg0, Page<RepayApply> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String userName, RepayApply repayApply)
			throws DataBaseAccessException {
		return baseDao.save(mapper + "Mapper.insert", repayApply) > 0;
	}

	@Override
	public boolean update(String userName, RepayApply repayApply)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean updateAfter(String userName, RepayApply repayApply)
			throws DataBaseAccessException {
		return  baseDao.update(mapper + "Mapper.updateAfter", repayApply) > 0;
	}

}

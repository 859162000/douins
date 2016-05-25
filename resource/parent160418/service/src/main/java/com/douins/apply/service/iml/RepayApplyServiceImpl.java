package com.douins.apply.service.iml;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.common.util.DateUtils;
import com.mango.exception.DataBaseAccessException;
import com.douins.account.domain.enums.DetailIo;
import com.douins.account.domain.enums.DetailType;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.service.UserAccountDetailService;
import com.douins.account.service.UserAccountService;
import com.douins.apply.dao.ApplyInfoDao;
import com.douins.apply.dao.RepayApplyDao;
import com.douins.apply.domain.enums.LoanStatus;
import com.douins.apply.domain.enums.RepayStatus;
import com.douins.apply.domain.enums.RepayType;
import com.douins.apply.domain.model.ApplyInfo;
import com.douins.apply.domain.model.LoanApply;
import com.douins.apply.domain.model.RepayApply;
import com.douins.apply.service.BaseApplyInfoService;
import com.douins.apply.service.LoanApplyService;
import com.douins.apply.service.RepayApplyService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.service.PolicyMasterService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SeqNumUtils;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("repayApplyService")
public class RepayApplyServiceImpl extends BaseApplyInfoService implements
		RepayApplyService {
	@Autowired
	private PolicyMasterService policyMasterService;
	
//	@Autowired
//	private BaseDao<RepayApply> baseDao;
//	
//	private String mapper = RepayApply.class.getName();
	
	@Inject
	private RepayApplyDao applyDao;
	
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
		//return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	    return applyDao.findByApplyId(key);
	}

	@Override
	public Page<RepayApply> getPage(RepayApply arg0, Page<RepayApply> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String userName, RepayApply repayApply)
			throws DataBaseAccessException {
		//return baseDao.save(mapper + "Mapper.insert", repayApply) > 0;
	    applyDao.add(repayApply);
	    return true;
	}

	@Override
	public boolean update(String userName, RepayApply repayApply)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean updateAfter(String userName, RepayApply repayApply)
			throws DataBaseAccessException {
		//return  baseDao.update(mapper + "Mapper.updateAfter", repayApply) > 0;
	    applyDao.updateAfter(repayApply);
	    return true;
	}

}

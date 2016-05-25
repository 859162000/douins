package com.douins.apply.service.iml;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.common.util.Arith;
import com.mango.core.common.util.DateUtils;
import com.mango.exception.DataBaseAccessException;
import com.douins.account.domain.enums.DetailIo;
import com.douins.account.domain.enums.DetailType;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.service.UserAccountDetailService;
import com.douins.account.service.UserAccountService;
import com.douins.apply.dao.LoanApplyDao;
import com.douins.apply.domain.enums.LoanStatus;
import com.douins.apply.domain.enums.LoanType;
import com.douins.apply.domain.model.ApplyInfo;
import com.douins.apply.domain.model.LoanApply;
import com.douins.apply.service.BaseApplyInfoService;
import com.douins.apply.service.LoanApplyService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.apply.domain.vo.LoanApplyVo;
import com.douins.apply.domain.vo.LoanInfoVo;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.service.PolicyMasterService;
import com.douins.product.domain.model.ProductLoanCycle;
import com.douins.product.service.ProductLoanCycleService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.common.util.CalculateUtils;
import com.douins.common.util.DateExpectedUtils;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SeqNumUtils;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("loanApplyService")
public class LoanApplyServiceImpl extends BaseApplyInfoService implements
		LoanApplyService {
	@Autowired
	private PolicyMasterService policyMasterService;
	@Autowired
	private ProductLoanCycleService productLoanCycleService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAccountDetailService userAccountDetailService;
	
//	@Autowired
//	private BaseDao<LoanApply> baseDao;
//	
//	@Autowired
//	private BaseDao<LoanApplyVo> loanApplyVoDao;
//	
//	private String mapper = LoanApply.class.getName();
	
	@Inject
	private LoanApplyDao applyDao;
	
	@Override
	public boolean delete(String arg0, LoanApply arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LoanApply findByKey(String key) {
		//return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	    return applyDao.selectByApplyId(key);
	}

	@Override
	public Page<LoanApply> getPage(LoanApply arg0, Page<LoanApply> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String userName, LoanApply loanApply)
			throws DataBaseAccessException {
		//return baseDao.save(mapper + "Mapper.insert", loanApply) > 0;
	    applyDao.insert(loanApply);
	    return true;
	}

	@Override
	public boolean update(String userName, LoanApply loanApply)
			throws DataBaseAccessException {
		return false;
	}
	
	@Override
	public boolean updateAfter(String userName, LoanApply loanApply)
			throws DataBaseAccessException {
		//return baseDao.update(mapper + "Mapper.updateAfter", loanApply) > 0;
	    applyDao.updateAfter(loanApply);
	    return true;
	}

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
			if(!PolicyStatus.INSURESUCCESS.getValue().equals(policyMaster.getStatus())){
				resultCode = ResponseCode.POLICYNOTLOAN.getValue();
				isSuccess = false;
			}else{
				BigDecimal maxLoanAmount = this.getMaxLoanAmount(policyMaster.getTotalPrem());
				if(maxLoanAmount.compareTo(applyInfo.getApplyAmount()) < 0){
					resultCode = ResponseCode.POLICYLOANAMOUNT.getValue();
					isSuccess = false;
				}
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
		
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyInfoId(applyInfo.getApplyInfoId());
		loanApply.setApplyCustomerId(policyMaster.getUserId());
		loanApply.setPolicyId(applyInfo.getPolicyId());
		loanApply.setPolicyCode(policyMaster.getPolicyCode());
		loanApply.setApplyAmount(applyInfo.getApplyAmount());
		loanApply.setApplyTime(applyInfo.getApplyTime());
		loanApply.setStatus(LoanStatus.LOANING.getValue());
		loanApply.setLoanRate(new BigDecimal(2));
		loanApply.setLoanType(LoanType.LOANNORMAL.getValue());
		loanApply.setLoanNo(SeqNumUtils.geneTxSN(SeqNumUtils.LOAN_PREFIX));
		loanApply.setHandfee(BigDecimal.ZERO);
		loanApply.setInsuranceCompany(policyMaster.getInsuranceId());
		loanApply.setLoanCompany("1");
		loanApply.setPolicyValue(policyMaster.getTotalPrem());
		
		Date loanBeginDate = DateUtils.addDay(DateUtils.todayFormat(),1);
		
		ProductLoanCycle productLoanCycle = productLoanCycleService.findByKey(applyInfo.getLoanCycleId());
		
		Date loanEndDate = DateExpectedUtils.expectedDate(loanBeginDate, productLoanCycle.getUnit(), productLoanCycle.getCycle());
		loanApply.setLoanBeginDate(loanBeginDate);
		loanApply.setLoanEndDate(loanEndDate);
		
		//利息  精确到两位小数
		BigDecimal loanInterest = Arith.getReturnRoundUp(CalculateUtils.calculateTotalRevenueByDay(loanApply.getApplyAmount(),loanApply.getLoanRate(),loanBeginDate,loanEndDate));
		//本金+利息
		BigDecimal totalRepayAmount = loanApply.getApplyAmount().add(loanInterest);
		loanApply.setLoanInterest(loanInterest);
		loanApply.setTotalRepayAmount(totalRepayAmount);
		loanApply.setUnit(productLoanCycle.getUnit());
		loanApply.setCycle(productLoanCycle.getCycle());

		SaveEntityUtils.initEntityBeforeInsert(loanApply, "");
		this.save("", loanApply);
		
		
		//发送支付请求到第三方
		ApplyResult payResult = this.doReqToThirdPay(loanApply);
		
		if(payResult.isSuccess()){
			//付款成功后
			SaveEntityUtils.setUpdateForEntity(loanApply, "");
			loanApply.setPaymentTime(DateUtils.today());
			loanApply.setStatus(LoanStatus.LOANINGEND.getValue());
			loanApply.setActualLoanAmount(loanApply.getApplyAmount());
			this.updateAfter("", loanApply);
			
			policyMaster.setStatus(PolicyStatus.LOANING.getValue());
			policyMasterService.update("", policyMaster);
			
			isSuccess = true;
		}
		
		applyResult.setSuccess(isSuccess);
		return applyResult;
	}
	
	public ApplyResult doReqToThirdPay(LoanApply loanApply) throws Exception{
		UserAccount userAccount = userAccountService.findOneByUserId(loanApply.getApplyCustomerId());
		
		UserAccountDetail detail=new UserAccountDetail();
		detail.setUserAccountId(userAccount.getUserAccountId());
		detail.setBusinessId(loanApply.getLoanApplyId());
		detail.setBusinessNo(loanApply.getLoanNo());
		detail.setApplyAmount(loanApply.getApplyAmount());
		detail.setDetailType(DetailType.POLICYLOAN.getValue());
		detail.setDetailIo(DetailIo.IN.getValue());
		detail.setFromAccountName("银行");
		detail.setPrincipalAmount(loanApply.getApplyAmount());
		/*detail.setFromAccountNo(fromAccountNo);
		detail.setFromBankCode(fromBankCode);
		detail.setFromBankName(BankType.getNameByValue(fromBankCode));*/
		detail.setToAccountNo(userAccount.getVirtualAccountNo());
		/*detail.setToAccountName(userAccount.getVirtualAccountNo());
		detail.setToBankCode(toBankCode);*/
		//detail.setToBankName(BankType.getNameByValue(toBankCode));
		
		ApplyResult applyResult = userAccountDetailService.doBusiness(detail, "");
		
		return applyResult;
	}
	
	/**
	 * 获取指定保单可以贷款额度的信息
	 */
	@Override
	public LoanInfoVo getLoanInfo(String policyId) throws Exception {
		PolicyMaster policyMaster = policyMasterService.findByKey(policyId);
		if(policyMaster == null) return null;
		
		/*policyMaster.setTotalPrem(new BigDecimal(10000));
		policyMaster.setPolicyCode("1234567891011");
		policyMaster.setProductId("79aacb6b382143a8af9dcca0d88cbbe1");
		policyMaster.setPolicyId("b165ba16f0df4bc9a5b2c0a80ca2edbe");*/
		
		LoanInfoVo loanInfoVo = new LoanInfoVo();
		loanInfoVo.setPolicyId(policyMaster.getPolicyId());
		loanInfoVo.setPolicyCode(policyMaster.getPolicyCode());
		loanInfoVo.setMaxLoanAmount(this.getMaxLoanAmount(policyMaster.getTotalPrem()).toString());
		
		ProductLoanCycle productLoanCycle = new ProductLoanCycle();
		productLoanCycle.setProductId(policyMaster.getProductId());
		List<ProductLoanCycle> cycleList = productLoanCycleService.findByCondition(productLoanCycle);
		
		loanInfoVo.setCycleList(cycleList);
		
		return loanInfoVo;
	}

	@Override
	public List<LoanApply> findByCondition(LoanApply loanApply) {
		//return baseDao.getList(mapper + "Mapper.getList", loanApply);
	    return applyDao.getList(loanApply);
	}

	@Override
	public List<LoanApplyVo> getMyLoanInfo4Api(LoanApply loanApply) {
		//return loanApplyVoDao.getList(mapper + "Mapper.getVoList", loanApply);
	    return applyDao.getVoList(loanApply);
	}
	
	public BigDecimal getMaxLoanAmount(BigDecimal totalPrem){
		return totalPrem.multiply(new BigDecimal(4)).divide(new BigDecimal(5));
	}

}

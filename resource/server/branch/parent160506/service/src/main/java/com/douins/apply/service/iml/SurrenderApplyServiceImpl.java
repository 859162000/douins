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
import com.douins.apply.dao.SurrenderApplyDao;
import com.douins.apply.domain.enums.SurrenderStatus;
import com.douins.apply.domain.enums.SurrenderType;
import com.douins.apply.domain.model.ApplyInfo;
import com.douins.apply.domain.model.SurrenderApply;
import com.douins.apply.service.BaseApplyInfoService;
import com.douins.apply.service.SurrenderApplyService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.apply.domain.vo.SurrenderInfoVo;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.service.PolicyMasterService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SeqNumUtils;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("surrenderApplyService")
public class SurrenderApplyServiceImpl extends BaseApplyInfoService implements
		SurrenderApplyService {
	@Autowired
	private PolicyMasterService policyMasterService;
	
//	@Autowired
//	private BaseDao<SurrenderApply> baseDao;
//	
//	private String mapper = SurrenderApply.class.getName();
	
	@Inject
	private SurrenderApplyDao applyDao;
	
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
			if(!PolicyStatus.INSURESUCCESS.getValue().equals(policyMaster.getStatus())){
				resultCode = ResponseCode.POLICYNOTSURRENDER.getValue();
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
		
		SurrenderApply surrenderApply = new SurrenderApply();
		surrenderApply.setApplyCustomerId(applyInfo.getApplyCustomerId());
		surrenderApply.setApplyAmount(policyMaster.getRepayAmount());
		//surrenderApply.setApplyAmount(policyMaster.getTotalPrem());
		surrenderApply.setApplyInfoId(applyInfo.getApplyInfoId());
		surrenderApply.setPolicyCode(policyMaster.getPolicyCode());
		surrenderApply.setPolicyId(applyInfo.getPolicyId());
		surrenderApply.setSurrendType(SurrenderType.SURRENDERNORMAL.getValue());
		surrenderApply.setHandfee(BigDecimal.ZERO);
		surrenderApply.setPrincipal(policyMaster.getTotalPrem());
		surrenderApply.setTotalRevenue(policyMaster.getTotalRevenue());
		surrenderApply.setStatus(SurrenderStatus.SURRENDERING.getValue());
		surrenderApply.setSurrendNo(SeqNumUtils.geneTxSN(SeqNumUtils.SURRENDER_PREFIX));
		surrenderApply.setRepayAmount(policyMaster.getRepayAmount());
		
		SaveEntityUtils.initEntityBeforeInsert(surrenderApply, "");
		this.save("", surrenderApply);
		
		//发送还款请求到第三方
		ApplyResult payResult = this.doReqToThirdPay(surrenderApply,policyMaster.getInsuranceId());

		if (payResult.isSuccess()) {
			// 付款成功后
			surrenderApply.setStatus(SurrenderStatus.SURRENDEREND.getValue());
			surrenderApply.setRepayTime(DateUtils.today());
			SaveEntityUtils.setUpdateForEntity(surrenderApply, "");
			this.updateAfter("", surrenderApply);

			policyMaster.setStatus(PolicyStatus.INVALIDATE.getValue());
			policyMaster.setEndTime(DateUtils.today());
			policyMasterService.update("", policyMaster);

			isSuccess = true;
		}
		
		applyResult.setSuccess(isSuccess);
		return applyResult;
	}
	
	public ApplyResult doReqToThirdPay(SurrenderApply surrenderApply,String insuranceId) throws Exception{
		UserAccount userAccount = userAccountService.findOneByUserId(surrenderApply.getApplyCustomerId());
		
		UserAccountDetail detail=new UserAccountDetail();
		detail.setUserAccountId(userAccount.getUserAccountId());
		detail.setBusinessId(surrenderApply.getSurrendApplyId());
		detail.setBusinessNo(surrenderApply.getSurrendNo());
		detail.setApplyAmount(surrenderApply.getRepayAmount());
		detail.setDetailType(DetailType.POLICYSURRENDER.getValue());
		detail.setDetailIo(DetailIo.IN.getValue());
		detail.setFromAccountName(insuranceId);
		detail.setPrincipalAmount(surrenderApply.getPrincipal());
		/*detail.setFromAccountNo(fromAccountNo);
		detail.setFromBankCode(fromBankCode);
		detail.setFromBankName(BankType.getNameByValue(fromBankCode));*/
		
		detail.setToAccountNo(userAccount.getVirtualAccountNo());
		/*detail.setToAccountName(userAccount.getVirtualAccountNo());
		 detail.setToAccountNo(toAccountNo);
		detail.setToBankCode(toBankCode);*/
		//detail.setToBankName(BankType.getNameByValue(toBankCode));
		
		ApplyResult applyResult = userAccountDetailService.doBusiness(detail, "");
		
		return applyResult;
	}
	
	@Override
	public boolean delete(String arg0, SurrenderApply arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SurrenderApply findByKey(String key) {
		//return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	    return applyDao.findByApplyId(key);
	}

	@Override
	public Page<SurrenderApply> getPage(SurrenderApply arg0, Page<SurrenderApply> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String userName, SurrenderApply surrenderApply)
			throws DataBaseAccessException {
		//return baseDao.save(mapper + "Mapper.insert", surrenderApply) > 0;
	    applyDao.add(surrenderApply);
	    return true;
	}

	@Override
	public boolean update(String arg0, SurrenderApply arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean updateAfter(String userName, SurrenderApply surrenderApply)
			throws DataBaseAccessException {
		//return  baseDao.update(mapper + "Mapper.updateAfter", surrenderApply) > 0;
	    applyDao.updateAfter(surrenderApply);
	    return true;
	}
	
	/**
	 * 退保信息
	 * @param surrenderApply
	 * @return
	 */
	public SurrenderInfoVo getSurrenderInfo(SurrenderApply surrenderApply){
		SurrenderInfoVo siv = new SurrenderInfoVo();
		PolicyMaster policy = policyMasterService.findByKey(surrenderApply.getPolicyId());
		siv.setPolicyId(surrenderApply.getPolicyId());
		siv.setPayTime(policy.getPayTime());
		BigDecimal fee = getHandFee(policy.getRepayAmount());
		siv.setHandFee(fee);
		siv.setPolicyCode(policy.getPolicyCode());
		siv.setTotalPrem(policy.getTotalPrem());
		BigDecimal repay = policy.getRepayAmount().subtract(fee);
		siv.setRepayAmount(repay);
		siv.setRevenue(policy.getAccuIncome());
		return siv;
	}
	
	/**
	 * 计算手续费
	 * @return
	 */
	private BigDecimal getHandFee(BigDecimal total){
	    return total.multiply(new BigDecimal(0.1D));
	}
	
}

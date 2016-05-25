package com.mango.fortune.apply.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.api.applyAPI.vo.SurrenderInfoVo;
import com.mango.core.common.util.DateUtils;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.account.enums.DetailIo;
import com.mango.fortune.account.enums.DetailType;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.model.UserAccountDetail;
import com.mango.fortune.account.service.UserAccountDetailService;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.apply.enums.SurrenderStatus;
import com.mango.fortune.apply.enums.SurrenderType;
import com.mango.fortune.apply.model.ApplyInfo;
import com.mango.fortune.apply.model.SurrenderApply;
import com.mango.fortune.apply.service.BaseApplyInfoService;
import com.mango.fortune.apply.service.SurrenderApplyService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.policy.enums.PolicyStatus;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.fortune.policy.service.PolicyMasterService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SeqNumUtils;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("surrenderApplyService")
public class SurrenderApplyServiceImpl extends BaseApplyInfoService implements
		SurrenderApplyService {
	@Autowired
	private PolicyMasterService policyMasterService;
	
	@Autowired
	private BaseDao<SurrenderApply> baseDao;
	
	private String mapper = SurrenderApply.class.getName();
	
	
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
		return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	}

	@Override
	public Page<SurrenderApply> getPage(SurrenderApply arg0, Page<SurrenderApply> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String userName, SurrenderApply surrenderApply)
			throws DataBaseAccessException {
		return baseDao.save(mapper + "Mapper.insert", surrenderApply) > 0;
	}

	@Override
	public boolean update(String arg0, SurrenderApply arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean updateAfter(String userName, SurrenderApply surrenderApply)
			throws DataBaseAccessException {
		return  baseDao.update(mapper + "Mapper.updateAfter", surrenderApply) > 0;
	}
	
	
	public SurrenderInfoVo getSurrenderInfo(SurrenderApply surrenderApply){
		SurrenderInfoVo siv = new SurrenderInfoVo();
		PolicyMaster policy = policyMasterService.findByKey(surrenderApply.getPolicyId());
		siv.setPolicyId(surrenderApply.getPolicyId());
		siv.setPayTime(policy.getPayTime());
		siv.setHandFee(BigDecimal.ZERO);
		siv.setPolicyCode(policy.getPolicyCode());
		siv.setTotalPrem(policy.getTotalPrem());
		siv.setRepayAmount(policy.getRepayAmount());
		return siv;
	}
	
}

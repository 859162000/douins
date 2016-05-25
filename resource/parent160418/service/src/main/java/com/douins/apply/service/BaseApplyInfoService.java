package com.douins.apply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.douins.apply.domain.enums.ApplyStatus;
import com.douins.apply.domain.model.ApplyInfo;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.common.util.SaveEntityUtils;
import com.mango.core.common.util.DateUtils;

public abstract class BaseApplyInfoService {
	@Autowired
	@Qualifier("applyInfoService")
	private ApplyInfoService  applyInfoService;
	
	@Transactional(rollbackFor=Exception.class)
	public ApplyResult doApply(ApplyInfo applyInfo) throws Exception{
		ApplyResult applyResult = new ApplyResult();
		//校验是否符合申请条件
		applyResult = this.checkApply(applyInfo);
		if(applyResult.isSuccess()){
			ApplyInfo info = this.saveApplyInfo(applyInfo);
			applyResult = this.doApplyImpl(applyInfo);
			if(applyResult.isSuccess()){
				this.updateApplyInfo(info);
			}
		}
		
		return applyResult;
	}
	
	public abstract ApplyResult checkApply(ApplyInfo applyInfo) throws Exception;
	
	public abstract ApplyResult doApplyImpl(ApplyInfo applyInfo) throws Exception;
	
	public ApplyInfo saveApplyInfo(ApplyInfo applyInfo) throws Exception{
		ApplyInfo info = new ApplyInfo();
		info.setApplyTime(DateUtils.today());
		info.setApplyType(applyInfo.getApplyType());
		info.setApplyCustomerId(applyInfo.getApplyCustomerId());
		info.setPolicyId(applyInfo.getPolicyId());
		info.setStatus(ApplyStatus.APPLYING.getValue());
		SaveEntityUtils.initEntityBeforeInsert(info, "");
		applyInfoService.save("", info);
		applyInfo.setApplyInfoId(info.getApplyInfoId());
		return info;
	}
	
	public void updateApplyInfo(ApplyInfo info) throws Exception{
		info.setStatus(ApplyStatus.APPLYEND.getValue());
		info.setValidateTime(DateUtils.today());
		SaveEntityUtils.setUpdateForEntity(info, "");
		applyInfoService.updateAfter("", info);
	}
}

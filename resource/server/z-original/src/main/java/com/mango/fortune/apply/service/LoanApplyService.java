package com.mango.fortune.apply.service;

import java.util.List;

import com.mango.api.applyAPI.vo.LoanApplyVo;
import com.mango.api.applyAPI.vo.LoanInfoVo;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.apply.model.LoanApply;
import com.mango.orm.DbOperateService;

public interface LoanApplyService extends DbOperateService<LoanApply>{
	
	public LoanInfoVo getLoanInfo(String policyId) throws Exception;
	
	public boolean updateAfter(String arg0, LoanApply arg1)
			throws DataBaseAccessException;
	
	public List<LoanApply> findByCondition(LoanApply loanApply);
	
	public List<LoanApplyVo> getMyLoanInfo4Api(LoanApply loanApply);
}
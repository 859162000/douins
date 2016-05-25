package com.douins.apply.service;

import java.util.List;

import com.douins.apply.domain.model.LoanApply;
import com.douins.apply.domain.vo.LoanApplyVo;
import com.douins.apply.domain.vo.LoanInfoVo;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.DbOperateService;

public interface LoanApplyService extends DbOperateService<LoanApply>{
	
	public LoanInfoVo getLoanInfo(String policyId) throws Exception;
	
	public boolean updateAfter(String arg0, LoanApply arg1)
			throws DataBaseAccessException;
	
	public List<LoanApply> findByCondition(LoanApply loanApply);
	
	public List<LoanApplyVo> getMyLoanInfo4Api(LoanApply loanApply);
}
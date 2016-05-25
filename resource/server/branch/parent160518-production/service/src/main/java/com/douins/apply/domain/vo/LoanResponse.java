package com.douins.apply.domain.vo;

import java.util.List;

import com.douins.trans.domain.vo.ResponseTransVo;


public class LoanResponse extends ResponseTransVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5509501977469587295L;
	
	private List<LoanApplyVo> loanApplyVoList;
	
	public List<LoanApplyVo> getLoanApplyVoList() {
		return loanApplyVoList;
	}
	
	public void setLoanApplyVoList(List<LoanApplyVo> loanApplyVoList) {
		this.loanApplyVoList = loanApplyVoList;
	}
	

}

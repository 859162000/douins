package com.mango.api.applyAPI.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanApplyVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3438645079222706428L;
	private BigDecimal applyAmount;
	private Date loanEndDate;
	private BigDecimal loanRate;
	private String status;
	private String policyId;
	private String policyCode;
	private String loanApplyId;
	private BigDecimal totalRepayAmount;
	private BigDecimal accountBalance;
	
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public BigDecimal getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(BigDecimal loanRate) {
		this.loanRate = loanRate;
	}

	public BigDecimal getTotalRepayAmount() {
		return totalRepayAmount;
	}

	public void setTotalRepayAmount(BigDecimal totalRepayAmount) {
		this.totalRepayAmount = totalRepayAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

}

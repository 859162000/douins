package com.douins.account.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserAccountResponseVo implements Serializable {
	private static final long serialVersionUID = -2747902748423723354L;
	private String userAccountId;
    private BigDecimal accountAmount;
    private BigDecimal point;
    private BigDecimal accountBalance;
    private BigDecimal policyValue;
    private BigDecimal loanAmount;
    private BigDecimal surrAmount;
    private BigDecimal repayAmount;
    private BigDecimal totalIncome;
	public String getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}
	public BigDecimal getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}
	public BigDecimal getPoint() {
		return point;
	}
	public void setPoint(BigDecimal point) {
		this.point = point;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public BigDecimal getPolicyValue() {
		return policyValue;
	}
	public void setPolicyValue(BigDecimal policyValue) {
		this.policyValue = policyValue;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getSurrAmount() {
		return surrAmount;
	}
	public void setSurrAmount(BigDecimal surrAmount) {
		this.surrAmount = surrAmount;
	}
	public BigDecimal getRepayAmount() {
		return repayAmount;
	}
	public void setRepayAmount(BigDecimal repayAmount) {
		this.repayAmount = repayAmount;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
}

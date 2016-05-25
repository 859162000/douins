package com.douins.account.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserAccountResponseVo implements Serializable {
	private static final long serialVersionUID = -2747902748423723354L;
	private String userAccountId;
    private BigDecimal accountAmount;       // 总资产
    private BigDecimal point;
    private BigDecimal accountBalance;      // 账户余额
    private BigDecimal frozenAmount;       // 冻结资金
    private BigDecimal policyValue;             // 保单总价值（保险理财的值）
    private BigDecimal loanAmount;              // 贷款总额
    private BigDecimal surrAmount;              // 退保总额
    private BigDecimal repayAmount;             // 支付总额
    private BigDecimal totalIncome;             // 累计总收益
    private BigDecimal yesDayIncome;        // 昨日总收益
    
    private int policyNum;          // 已成功购买的保单份数
    private int loanpolicyNum;      // 已贷款的保单份数
    
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
    public BigDecimal getYesDayIncome() {
        return yesDayIncome;
    }
    public void setYesDayIncome(BigDecimal yesDayIncome) {
        this.yesDayIncome = yesDayIncome;
    }
    public int getPolicyNum() {
        return policyNum;
    }
    public void setPolicyNum(int policyNum) {
        this.policyNum = policyNum;
    }
    public int getLoanpolicyNum() {
        return loanpolicyNum;
    }
    public void setLoanpolicyNum(int loanpolicyNum) {
        this.loanpolicyNum = loanpolicyNum;
    }
    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }
    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
	@Override
	public String toString() {
		return "UserAccountResponseVo [userAccountId=" + userAccountId
				+ ", accountAmount=" + accountAmount + ", point=" + point
				+ ", accountBalance=" + accountBalance + ", frozenAmount="
				+ frozenAmount + ", policyValue=" + policyValue
				+ ", loanAmount=" + loanAmount + ", surrAmount=" + surrAmount
				+ ", repayAmount=" + repayAmount + ", totalIncome="
				+ totalIncome + ", yesDayIncome=" + yesDayIncome
				+ ", policyNum=" + policyNum + ", loanpolicyNum="
				+ loanpolicyNum + "]";
	}
   
}

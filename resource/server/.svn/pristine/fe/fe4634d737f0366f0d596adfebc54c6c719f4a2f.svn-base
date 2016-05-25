package com.douins.account.domain.model;

import java.math.BigDecimal;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class UserAccount extends AbstractModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6400895474293742837L;

	private Long id;
	
    private String userAccountId;

    private String userId;

    private BigDecimal accountAmount;   //账户总额

    private BigDecimal point;

    private BigDecimal accountBalance;//账户余额
    private BigDecimal frozenAmount;    // 冻结资金额度
    
    private BigDecimal policyValue;//实际保单本金价值

    private BigDecimal loanAmount;//实际贷款金额

    private String virtualAccountNo;        // 虚拟账号
    private String virtualAccountId;          // 银行账号识别ID
    private BigDecimal surrAmount;//累计实际退保金额

    private BigDecimal repayAmount;//累计实际回款金额

    private String status;
    private String passwordStatus;          // 账户密码（支付密码）状态
    private String isvalid;

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getVirtualAccountNo() {
        return virtualAccountNo;
    }

    public void setVirtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

	@Override
	public void initPrimaryKey() {
		this.setUserAccountId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(String passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public String getVirtualAccountId() {
        return virtualAccountId;
    }

    public void setVirtualAccountId(String virtualAccountId) {
        this.virtualAccountId = virtualAccountId;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", userAccountId=" + userAccountId
				+ ", userId=" + userId + ", accountAmount=" + accountAmount
				+ ", point=" + point + ", accountBalance=" + accountBalance
				+ ", frozenAmount=" + frozenAmount + ", policyValue="
				+ policyValue + ", loanAmount=" + loanAmount
				+ ", virtualAccountNo=" + virtualAccountNo
				+ ", virtualAccountId=" + virtualAccountId + ", surrAmount="
				+ surrAmount + ", repayAmount=" + repayAmount + ", status="
				+ status + ", passwordStatus=" + passwordStatus + ", isvalid="
				+ isvalid + "]";
	}
    
}
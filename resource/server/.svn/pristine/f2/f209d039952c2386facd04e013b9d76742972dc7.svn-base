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

    private BigDecimal accountAmount;//账户总价值

    private BigDecimal point;

    private BigDecimal accountBalance;//账户余额

    private BigDecimal policyValue;//实际保单本金价值

    private BigDecimal loanAmount;//实际贷款金额

    private String virtualAccountNo;

    private BigDecimal surrAmount;//累计实际退保金额

    private BigDecimal repayAmount;//累计实际回款金额

    private String status;

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
}
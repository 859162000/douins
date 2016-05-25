package com.douins.pay.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class PaymentApply extends AbstractModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3770579837211336358L;

	private Long id;
	
    private String paymentApplyId;

    private String policyId;

    private String userAccountId;

    private String payOrderNo;

    private String payType;

    private String thirdpayType;

    private String fromAccountName;

    private String fromAccountNo;

    private String fromBankCode;

    private String fromBankName;

    private String toAccountName;

    private String toAccountNo;

    private String toBankCode;

    private String toBankName;

    private Date payApplyTime;

    private Date payTime;

    private BigDecimal payMoney;

    private String status;

    private String isvalid;

    public String getPaymentApplyId() {
        return paymentApplyId;
    }

    public void setPaymentApplyId(String paymentApplyId) {
        this.paymentApplyId = paymentApplyId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getThirdpayType() {
        return thirdpayType;
    }

    public void setThirdpayType(String thirdpayType) {
        this.thirdpayType = thirdpayType;
    }

    public String getFromAccountName() {
        return fromAccountName;
    }

    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName;
    }

    public String getFromAccountNo() {
        return fromAccountNo;
    }

    public void setFromAccountNo(String fromAccountNo) {
        this.fromAccountNo = fromAccountNo;
    }

    public String getFromBankCode() {
        return fromBankCode;
    }

    public void setFromBankCode(String fromBankCode) {
        this.fromBankCode = fromBankCode;
    }

    public String getFromBankName() {
        return fromBankName;
    }

    public void setFromBankName(String fromBankName) {
        this.fromBankName = fromBankName;
    }

    public String getToAccountName() {
        return toAccountName;
    }

    public void setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName;
    }

    public String getToAccountNo() {
        return toAccountNo;
    }

    public void setToAccountNo(String toAccountNo) {
        this.toAccountNo = toAccountNo;
    }

    public String getToBankCode() {
        return toBankCode;
    }

    public void setToBankCode(String toBankCode) {
        this.toBankCode = toBankCode;
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName;
    }

    public Date getPayApplyTime() {
        return payApplyTime;
    }

    public void setPayApplyTime(Date payApplyTime) {
        this.payApplyTime = payApplyTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
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
		this.setPaymentApplyId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
package com.mango.fortune.apply.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mango.orm.KeyGenerator;

public class RepayApply extends ApplyInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4616877077779387012L;

	private Long id;
	
    private String repayApplyId;

    private String repayNo;

    private String loanApplyId;

    private String repayType;

    private BigDecimal applyAmount;

    private BigDecimal repayValue;//本金

    private BigDecimal repayRate;

    private BigDecimal handfee;

    private BigDecimal actualRepayAmount;

    private Date repayTime;//实际还款时间

    private String insuranceCompany;

    private String loanCompany;

    private String status;

    private String isvalid;

    public String getRepayApplyId() {
        return repayApplyId;
    }

    public void setRepayApplyId(String repayApplyId) {
        this.repayApplyId = repayApplyId;
    }

    public String getRepayNo() {
        return repayNo;
    }

    public void setRepayNo(String repayNo) {
        this.repayNo = repayNo;
    }

    public String getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(String loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public BigDecimal getRepayValue() {
        return repayValue;
    }

    public void setRepayValue(BigDecimal repayValue) {
        this.repayValue = repayValue;
    }

    public BigDecimal getRepayRate() {
        return repayRate;
    }

    public void setRepayRate(BigDecimal repayRate) {
        this.repayRate = repayRate;
    }

    public BigDecimal getHandfee() {
        return handfee;
    }

    public void setHandfee(BigDecimal handfee) {
        this.handfee = handfee;
    }

    public BigDecimal getActualRepayAmount() {
        return actualRepayAmount;
    }

    public void setActualRepayAmount(BigDecimal actualRepayAmount) {
        this.actualRepayAmount = actualRepayAmount;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getLoanCompany() {
        return loanCompany;
    }

    public void setLoanCompany(String loanCompany) {
        this.loanCompany = loanCompany;
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
		this.setRepayApplyId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
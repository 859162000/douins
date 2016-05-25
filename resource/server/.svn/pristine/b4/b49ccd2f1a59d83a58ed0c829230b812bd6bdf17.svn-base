package com.mango.fortune.apply.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mango.orm.KeyGenerator;

public class LoanApply extends ApplyInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3266771572072388906L;

	private Long id;

    private String loanApplyId;

    private String loanNo;

    private String policyCode;

    private String loanType;

    private BigDecimal policyValue;

    private BigDecimal loanRate;

    private BigDecimal handfee;

    private BigDecimal actualLoanAmount;

    private Date loanBeginDate;//计息开始时间
    
    private Date loanEndDate;//计息结束时间
    
    private BigDecimal loanInterest;
    
    private BigDecimal totalRepayAmount;
    
    private Date paymentTime;

    private String insuranceCompany;

    private String loanCompany;

    private String status;
    
    private BigDecimal cycle;//贷款周期
    
    private String unit;//周期类型

    private BigDecimal maxLoanAmount;//最大贷款金额

    public String getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(String loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    

    public BigDecimal getPolicyValue() {
        return policyValue;
    }

    public void setPolicyValue(BigDecimal policyValue) {
        this.policyValue = policyValue;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getHandfee() {
        return handfee;
    }

    public void setHandfee(BigDecimal handfee) {
        this.handfee = handfee;
    }

    public BigDecimal getActualLoanAmount() {
        return actualLoanAmount;
    }

    public void setActualLoanAmount(BigDecimal actualLoanAmount) {
        this.actualLoanAmount = actualLoanAmount;
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
    
	@Override
	public void initPrimaryKey() {
		this.setLoanApplyId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLoanBeginDate() {
		return loanBeginDate;
	}

	public void setLoanBeginDate(Date loanBeginDate) {
		this.loanBeginDate = loanBeginDate;
	}

	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public BigDecimal getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(BigDecimal maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}


	public BigDecimal getLoanInterest() {
		return loanInterest;
	}

	public void setLoanInterest(BigDecimal loanInterest) {
		this.loanInterest = loanInterest;
	}

	public BigDecimal getTotalRepayAmount() {
		return totalRepayAmount;
	}

	public void setTotalRepayAmount(BigDecimal totalRepayAmount) {
		this.totalRepayAmount = totalRepayAmount;
	}

	public BigDecimal getCycle() {
		return cycle;
	}

	public void setCycle(BigDecimal cycle) {
		this.cycle = cycle;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
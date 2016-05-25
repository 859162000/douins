package com.mango.fortune.apply.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class ApplyInfo extends AbstractModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7628355738395412457L;
	
	private Long id;
	
	private String applyInfoId;

    private String policyId;
    
    private String policyCode;

    private String applyType;

    private Date applyTime;

    private String applyCustomerId;

    private Date validateTime;
    
    private BigDecimal applyAmount;
    
    private String businessId;

    private String status;

    private String isvalid;
    
    private String loanCycleId;
    
    private String payPassword;

    public String getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(String applyInfoId) {
        this.applyInfoId = applyInfoId;
    }


    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyCustomerId() {
        return applyCustomerId;
    }

    public void setApplyCustomerId(String applyCustomerId) {
        this.applyCustomerId = applyCustomerId;
    }

    public Date getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(Date validateTime) {
        this.validateTime = validateTime;
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
		this.setApplyInfoId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getLoanCycleId() {
		return loanCycleId;
	}

	public void setLoanCycleId(String loanCycleId) {
		this.loanCycleId = loanCycleId;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	
}
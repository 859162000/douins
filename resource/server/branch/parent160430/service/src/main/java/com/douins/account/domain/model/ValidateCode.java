package com.douins.account.domain.model;

import java.util.Date;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class ValidateCode extends AbstractModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3706041497363304837L;

	private Long id;

    private String validateCodeId;

    private String validateType;

	private String validateAccount;

    private String validateCode;

    private Date sendTime;

    private String isvalid;
    
    private String codeType;
    
    public String getValidateCodeId() {
        return validateCodeId;
    }

    public void setValidateCodeId(String validateCodeId) {
        this.validateCodeId = validateCodeId;
    }

    public String getValidateAccount() {
        return validateAccount;
    }

    public void setValidateAccount(String validateAccount) {
        this.validateAccount = validateAccount;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

	/* (non-Javadoc)
	 * @see com.mango.core.abstractclass.AbstractModel#initPrimaryKey()
	 */
	@Override
	public void initPrimaryKey() {
		this.setValidateCodeId(KeyGenerator.randomSeqNum());
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
    public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	@Override
	public String toString() {
		return "ValidateCode [id=" + id + ", validateCodeId=" + validateCodeId
				+ ", validateType=" + validateType + ", validateAccount="
				+ validateAccount + ", validateCode=" + validateCode
				+ ", sendTime=" + sendTime + ", isvalid=" + isvalid
				+ ", codeType=" + codeType + "]";
	}
	
	

}
package com.mango.fortune.partner.model;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class Bank extends AbstractModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1015577733457568404L;

	private Long id;
	
    private String bankId;

    private String bankName;

    private String bankCode;

    private String bankAbbrName;

    private String isvalid;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAbbrName() {
        return bankAbbrName;
    }

    public void setBankAbbrName(String bankAbbrName) {
        this.bankAbbrName = bankAbbrName;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

	@Override
	public void initPrimaryKey() {
		this.setBankId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
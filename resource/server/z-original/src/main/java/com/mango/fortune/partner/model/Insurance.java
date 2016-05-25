package com.mango.fortune.partner.model;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class Insurance extends AbstractModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6088398662767876635L;

	private Long id;
	
	private String insuranceId;

    private String insuranceName;

    private String insuranceCode;

    private String insuranceAbbrName;
    
    private String signKey;
    
    private String transChannel;

    private String isvalid;

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getInsuranceAbbrName() {
        return insuranceAbbrName;
    }

    public void setInsuranceAbbrName(String insuranceAbbrName) {
        this.insuranceAbbrName = insuranceAbbrName;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

	@Override
	public void initPrimaryKey() {
		this.setInsuranceId(KeyGenerator.randomSeqNum());
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getTransChannel() {
		return transChannel;
	}

	public void setTransChannel(String transChannel) {
		this.transChannel = transChannel;
	}
}
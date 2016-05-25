package com.douins.agency.service.douins.domain.model;

import java.util.Date;

import com.douins.agency.service.BaseModel;

public class PolicyReservationResponse extends BaseModel {

    private String responseCode;

    private String uuid;

    private String ackUuid;

    private String tradeCode;

    private String responseTime;

    private String cost;

    private String errorMsg;

    private String paymentId;

    private String bankSerial;

    private String payTime;

    private String payMoney;

    private String accountDate;

    private String payBankCode;

    private String payBankAcount;

    private String payBankUserId;

    private String payBankUsername;

    private String reciveBankCode;

    private String reciveBankAcount;

    private String reciveBankUserId;

    private String reciveBankUsername;

    private String channelType;

    private String channelNum;

    private String channelReginNum;

    private String agentNum;

    private String applyType;

    private String applyDate;

    private String policyEffectiveDate;

    private String totalPremium;

    private String productCode;

    private String insuranceNum;

    private String insurancePeriod;

    private String insuranceClass;

    private String afterDayEffective;

    private String periodPremium;

    private String insuranceCategory;

    private String productEffectiveDate;

    private String frequency;

    private String calculationRule;

    private String paymentInstallments;

    private String premperiodType;

    private String isPackage;

    private String certificateTypeId;

    private String certificateId;

    private String userName;

    private String sex;

    private String birthday;

    private String applicantCellphoneNumber;

    private String address;

    private String applicantTelephone;

    private String email;

    private String isApplicant;

    private String orderId;

    private String policyNo;
    
    private String insurantApplicantRelation;

    private Date createTime;

    private String properties;
    
    private String refundTime;
    
    private String refundAmount;
    private String isSuccess;
    private String applyNo;
    private String idNO;
    
    public String getIsSuccess() {
		return isSuccess;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public String getIdNO() {
		return idNO;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public void setIdNO(String idNO) {
		this.idNO = idNO;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode == null ? null : responseCode.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getAckUuid() {
        return ackUuid;
    }

    public void setAckUuid(String ackUuid) {
        this.ackUuid = ackUuid == null ? null : ackUuid.trim();
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime == null ? null : responseTime.trim();
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public String getBankSerial() {
        return bankSerial;
    }

    public void setBankSerial(String bankSerial) {
        this.bankSerial = bankSerial == null ? null : bankSerial.trim();
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getAccountDate(String string) {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate == null ? null : accountDate.trim();
    }

    public String getPayBankCode() {
        return payBankCode;
    }

    public void setPayBankCode(String payBankCode) {
        this.payBankCode = payBankCode == null ? null : payBankCode.trim();
    }

    public String getPayBankAcount() {
        return payBankAcount;
    }

    public void setPayBankAcount(String payBankAcount) {
        this.payBankAcount = payBankAcount == null ? null : payBankAcount.trim();
    }

    public String getPayBankUserId() {
        return payBankUserId;
    }

    public void setPayBankUserId(String payBankUserId) {
        this.payBankUserId = payBankUserId == null ? null : payBankUserId.trim();
    }

    public String getPayBankUsername() {
        return payBankUsername;
    }

    public void setPayBankUsername(String payBankUsername) {
        this.payBankUsername = payBankUsername == null ? null : payBankUsername.trim();
    }

    public String getReciveBankCode() {
        return reciveBankCode;
    }

    public void setReciveBankCode(String reciveBankCode) {
        this.reciveBankCode = reciveBankCode == null ? null : reciveBankCode.trim();
    }

    public String getReciveBankAcount() {
        return reciveBankAcount;
    }

    public void setReciveBankAcount(String reciveBankAcount) {
        this.reciveBankAcount = reciveBankAcount == null ? null : reciveBankAcount.trim();
    }

    public String getReciveBankUserId() {
        return reciveBankUserId;
    }

    public void setReciveBankUserId(String reciveBankUserId) {
        this.reciveBankUserId = reciveBankUserId == null ? null : reciveBankUserId.trim();
    }

    public String getReciveBankUsername() {
        return reciveBankUsername;
    }

    public void setReciveBankUsername(String reciveBankUsername) {
        this.reciveBankUsername = reciveBankUsername == null ? null : reciveBankUsername.trim();
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum == null ? null : channelNum.trim();
    }

    public String getChannelReginNum() {
        return channelReginNum;
    }

    public void setChannelReginNum(String channelReginNum) {
        this.channelReginNum = channelReginNum == null ? null : channelReginNum.trim();
    }

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum == null ? null : agentNum.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate == null ? null : applyDate.trim();
    }

    public String getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }

    public void setPolicyEffectiveDate(String policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate == null ? null : policyEffectiveDate.trim();
    }

    public String getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(String totalPremium) {
        this.totalPremium = totalPremium;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum == null ? null : insuranceNum.trim();
    }

    public String getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod(String insurancePeriod) {
        this.insurancePeriod = insurancePeriod == null ? null : insurancePeriod.trim();
    }

    public String getInsuranceClass() {
        return insuranceClass;
    }

    public void setInsuranceClass(String insuranceClass) {
        this.insuranceClass = insuranceClass == null ? null : insuranceClass.trim();
    }

    public String getAfterDayEffective() {
        return afterDayEffective;
    }

    public void setAfterDayEffective(String afterDayEffective) {
        this.afterDayEffective = afterDayEffective == null ? null : afterDayEffective.trim();
    }

    public String getPeriodPremium() {
        return periodPremium;
    }

    public void setPeriodPremium(String periodPremium) {
        this.periodPremium = periodPremium == null ? null : periodPremium.trim();
    }

    public String getInsuranceCategory() {
        return insuranceCategory;
    }

    public void setInsuranceCategory(String insuranceCategory) {
        this.insuranceCategory = insuranceCategory == null ? null : insuranceCategory.trim();
    }

    public String getProductEffectiveDate() {
        return productEffectiveDate;
    }

    public void setProductEffectiveDate(String productEffectiveDate) {
        this.productEffectiveDate = productEffectiveDate == null ? null : productEffectiveDate.trim();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    public String getCalculationRule() {
        return calculationRule;
    }

    public void setCalculationRule(String calculationRule) {
        this.calculationRule = calculationRule == null ? null : calculationRule.trim();
    }

    public String getPaymentInstallments() {
        return paymentInstallments;
    }

    public void setPaymentInstallments(String paymentInstallments) {
        this.paymentInstallments = paymentInstallments == null ? null : paymentInstallments.trim();
    }

    public String getPremperiodType() {
        return premperiodType;
    }

    public void setPremperiodType(String premperiodType) {
        this.premperiodType = premperiodType == null ? null : premperiodType.trim();
    }

    public String getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(String isPackage) {
        this.isPackage = isPackage == null ? null : isPackage.trim();
    }

    public String getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(String certificateTypeId) {
        this.certificateTypeId = certificateTypeId == null ? null : certificateTypeId.trim();
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId == null ? null : certificateId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getApplicantCellphoneNumber() {
        return applicantCellphoneNumber;
    }

    public void setApplicantCellphoneNumber(String applicantCellphoneNumber) {
        this.applicantCellphoneNumber = applicantCellphoneNumber == null ? null : applicantCellphoneNumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getApplicantTelephone() {
        return applicantTelephone;
    }

    public void setApplicantTelephone(String applicantTelephone) {
        this.applicantTelephone = applicantTelephone == null ? null : applicantTelephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIsApplicant() {
        return isApplicant;
    }

    public void setIsApplicant(String isApplicant) {
        this.isApplicant = isApplicant == null ? null : isApplicant.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getInsurantApplicantRelation() {
        return insurantApplicantRelation;
    }

    public void setInsurantApplicantRelation(String insurantApplicantRelation) {
        this.insurantApplicantRelation = insurantApplicantRelation == null ? null : insurantApplicantRelation.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
    
}
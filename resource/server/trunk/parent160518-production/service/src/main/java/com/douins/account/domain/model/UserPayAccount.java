package com.douins.account.domain.model;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class UserPayAccount extends AbstractModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1242785626488705268L;

	private Long id;
	
    private String accountId;//账户ID

    private String userId;

    private String userAccountId;//虚拟账户ID

    private String accountType;//账户类型，1：手机   2:邮箱

    private String accountName;//银行名称

    private String accountNo;//银行卡号

    private String bankProvince;//省

    private String bankCity;//市

    private String bankBranch;//支行

    private String bankCode;//银行简称

    private String cardholderName;//卡持有人姓名

    private String cardholderCerticode;//卡持有人证件号

    private String cardholderCertitype;//卡持有人证件类型

    private String bankmobile;//银行预留电话

    private String status;//状态，1:可用   0:不可用

    private String opuser;

    private String isvalid;//是否有效，同status
    
    private String payPassword;//交易密码

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardholderCerticode() {
        return cardholderCerticode;
    }

    public void setCardholderCerticode(String cardholderCerticode) {
        this.cardholderCerticode = cardholderCerticode;
    }

    public String getCardholderCertitype() {
        return cardholderCertitype;
    }

    public void setCardholderCertitype(String cardholderCertitype) {
        this.cardholderCertitype = cardholderCertitype;
    }

    public String getBankmobile() {
        return bankmobile;
    }

    public void setBankmobile(String bankmobile) {
        this.bankmobile = bankmobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpuser() {
        return opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

	@Override
	public void initPrimaryKey() {
		this.setAccountId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	@Override
	public String toString() {
		return "UserPayAccount [id=" + id + ", accountId=" + accountId
				+ ", userId=" + userId + ", userAccountId=" + userAccountId
				+ ", accountType=" + accountType + ", accountName="
				+ accountName + ", accountNo=" + accountNo + ", bankProvince="
				+ bankProvince + ", bankCity=" + bankCity + ", bankBranch="
				+ bankBranch + ", bankCode=" + bankCode + ", cardholderName="
				+ cardholderName + ", cardholderCerticode="
				+ cardholderCerticode + ", cardholderCertitype="
				+ cardholderCertitype + ", bankmobile=" + bankmobile
				+ ", status=" + status + ", opuser=" + opuser + ", isvalid="
				+ isvalid + ", payPassword=" + payPassword + "]";
	}
	
	
}
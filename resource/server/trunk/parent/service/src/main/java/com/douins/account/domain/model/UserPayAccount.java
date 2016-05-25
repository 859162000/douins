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

    private String accountType;

    private String accountName;

    private String accountNo;

    private String bankProvince;

    private String bankCity;

    private String bankBranch;

    private String bankCode;

    private String cardholderName;

    private String cardholderCerticode;

    private String cardholderCertitype;

    private String bankmobile;

    private String status;

    private String opuser;

    private String isvalid;
    
    private String payPassword;

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
}
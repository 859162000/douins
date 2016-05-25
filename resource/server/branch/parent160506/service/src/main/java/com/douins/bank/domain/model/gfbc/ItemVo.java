package com.douins.bank.domain.model.gfbc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 已签约银行查询接口返回--银行详情
 * @author hou
 *
 */
@XStreamAlias("item")
public class ItemVo {
	@XStreamAlias("payaccount")
	private String payAccount;
	@XStreamAlias("obankcode")
	private String oBankCode;
	@XStreamAlias("obankname")
	private String oBankName;
	@XStreamAlias("obankmobileno")
	private String oBankMobileNo;
	@XStreamAlias("accountname")
	private String accountName;
	@XStreamAlias("recaccount")
	private String recAccount;
	@XStreamAlias("protocolcode")
	private String protocolCode;
	
	public String getPayAccount() {
		return payAccount;
	}
	public String getoBankCode() {
		return oBankCode;
	}
	public String getoBankName() {
		return oBankName;
	}
	public String getoBankMobileNo() {
		return oBankMobileNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getRecAccount() {
		return recAccount;
	}
	public String getProtocolCode() {
		return protocolCode;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public void setoBankCode(String oBankCode) {
		this.oBankCode = oBankCode;
	}
	public void setoBankName(String oBankName) {
		this.oBankName = oBankName;
	}
	public void setoBankMobileNo(String oBankMobileNo) {
		this.oBankMobileNo = oBankMobileNo;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setRecAccount(String recAccount) {
		this.recAccount = recAccount;
	}
	public void setProtocolCode(String protocolCode) {
		this.protocolCode = protocolCode;
	}
	
}

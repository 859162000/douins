package com.douins.trans.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class BusinessTrans extends AbstractModel{
	private static final long serialVersionUID = 6257641086495052050L;
	private Long id;
	
	private String isvalid;
	
	private String transUserToken;//userToken
	
	private String transInsureId;//保单号
	
	private String userName ;//投保人

	private String transId;

    private String businessId;

    private String transNo;

    private String transType;

    private String transChannel;

    private String status;
    
    private Date transTime;

    private BigDecimal payMoney;

    private String resultMsg;
     
    private String transProductName;
    
    private String transChannelNo;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getTransUserToken() {
		return transUserToken;
	}

	public void setTransUserToken(String transUserToken) {
		this.transUserToken = transUserToken;
	}

	public String getTransInsureId() {
		return transInsureId;
	}

	public void setTransInsureId(String transInsureId) {
		this.transInsureId = transInsureId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransChannel() {
		return transChannel;
	}

	public void setTransChannel(String transChannel) {
		this.transChannel = transChannel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	

	public String getTransProductName() {
		return transProductName;
	}

	public void setTransProductName(String transProductName) {
		this.transProductName = transProductName;
	}
	

	public String getTransChannelNo() {
		return transChannelNo;
	}

	public void setTransChannelNo(String transChannelNo) {
		this.transChannelNo = transChannelNo;
	}

	@Override
	public void initPrimaryKey() {
		// TODO Auto-generated method stub
		
	}


   
	
	
	
	
}
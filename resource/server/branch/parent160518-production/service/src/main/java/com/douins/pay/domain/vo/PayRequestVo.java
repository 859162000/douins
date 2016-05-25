package com.douins.pay.domain.vo;

public class PayRequestVo {
	private String   chanlNo	;//Y	渠道编号
	private String   acctNoId;//Y	电子账户号标识
	private String   acctType;//Y	支付账户类型（0：电子账户标识；1：电子账户，2：冻结凭证；*如果为电子帐号标识，ACCT_NO_ID为必输，如果为电子账号，ACCT_NO为必输，如果为冻结凭证，则冻结凭证为必输）
	private String   acctNo	;//	N	支付电子账户号
	private String   freezeNo;//	N	冻结凭证
	private String   projCode;//Y4	Y	项目编号
	private String   chanlFlowNo;//Y5	Y	渠道流水号
	private String   amt;//Y6	Y	交易金额
	private String   url;//Y7	Y	回调url(Encode后的url)
	private String   rcvAcctTypeArray;//Y	收款账户类型（0：电子账户标识；1：电子账户3：企业账户*如果为电子帐号标识，ACCT_NO_ID为必输，如果为电子账户/企业账户，ACCT_NO为必输）收款账号类型列表：acctType|
	private String   rcvAcctNoIdArray;//Y	收款账号标识列表：rcvAcctNoId1|
	private String   rcvAcctNoArray	;//	Y	收款账号列表：rcvAcctNo1|
	private String   rcvAmtArray;//	Y	金额列表：
	private String   rcvAbsArray;//	Y	收款摘要列表：
	private String   signMsg;//	Y	数据签名（详见4）
	
	public String getChanlNo() {
		return chanlNo;
	}
	public void setChanlNo(String chanlNo) {
		this.chanlNo = chanlNo;
	}
	public String getAcctNoId() {
		return acctNoId;
	}
	public void setAcctNoId(String acctNoId) {
		this.acctNoId = acctNoId;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getFreezeNo() {
		return freezeNo;
	}
	public void setFreezeNo(String freezeNo) {
		this.freezeNo = freezeNo;
	}
	public String getProjCode() {
		return projCode;
	}
	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	public String getChanlFlowNo() {
		return chanlFlowNo;
	}
	public void setChanlFlowNo(String chanlFlowNo) {
		this.chanlFlowNo = chanlFlowNo;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRcvAcctTypeArray() {
		return rcvAcctTypeArray;
	}
	public void setRcvAcctTypeArray(String rcvAcctTypeArray) {
		this.rcvAcctTypeArray = rcvAcctTypeArray;
	}
	public String getRcvAcctNoIdArray() {
		return rcvAcctNoIdArray;
	}
	public void setRcvAcctNoIdArray(String rcvAcctNoIdArray) {
		this.rcvAcctNoIdArray = rcvAcctNoIdArray;
	}
	public String getRcvAcctNoArray() {
		return rcvAcctNoArray;
	}
	public void setRcvAcctNoArray(String rcvAcctNoArray) {
		this.rcvAcctNoArray = rcvAcctNoArray;
	}
	public String getRcvAmtArray() {
		return rcvAmtArray;
	}
	public void setRcvAmtArray(String rcvAmtArray) {
		this.rcvAmtArray = rcvAmtArray;
	}
	public String getRcvAbsArray() {
		return rcvAbsArray;
	}
	public void setRcvAbsArray(String rcvAbsArray) {
		this.rcvAbsArray = rcvAbsArray;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acctNo == null) ? 0 : acctNo.hashCode());
		result = prime * result
				+ ((acctNoId == null) ? 0 : acctNoId.hashCode());
		result = prime * result
				+ ((acctType == null) ? 0 : acctType.hashCode());
		result = prime * result + ((amt == null) ? 0 : amt.hashCode());
		result = prime * result
				+ ((chanlFlowNo == null) ? 0 : chanlFlowNo.hashCode());
		result = prime * result + ((chanlNo == null) ? 0 : chanlNo.hashCode());
		result = prime * result
				+ ((freezeNo == null) ? 0 : freezeNo.hashCode());
		result = prime * result
				+ ((projCode == null) ? 0 : projCode.hashCode());
		result = prime * result
				+ ((rcvAbsArray == null) ? 0 : rcvAbsArray.hashCode());
		result = prime * result
				+ ((rcvAcctNoArray == null) ? 0 : rcvAcctNoArray.hashCode());
		result = prime
				* result
				+ ((rcvAcctNoIdArray == null) ? 0 : rcvAcctNoIdArray.hashCode());
		result = prime
				* result
				+ ((rcvAcctTypeArray == null) ? 0 : rcvAcctTypeArray.hashCode());
		result = prime * result
				+ ((rcvAmtArray == null) ? 0 : rcvAmtArray.hashCode());
		result = prime * result + ((signMsg == null) ? 0 : signMsg.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayRequestVo other = (PayRequestVo) obj;
		if (acctNo == null) {
			if (other.acctNo != null)
				return false;
		} else if (!acctNo.equals(other.acctNo))
			return false;
		if (acctNoId == null) {
			if (other.acctNoId != null)
				return false;
		} else if (!acctNoId.equals(other.acctNoId))
			return false;
		if (acctType == null) {
			if (other.acctType != null)
				return false;
		} else if (!acctType.equals(other.acctType))
			return false;
		if (amt == null) {
			if (other.amt != null)
				return false;
		} else if (!amt.equals(other.amt))
			return false;
		if (chanlFlowNo == null) {
			if (other.chanlFlowNo != null)
				return false;
		} else if (!chanlFlowNo.equals(other.chanlFlowNo))
			return false;
		if (chanlNo == null) {
			if (other.chanlNo != null)
				return false;
		} else if (!chanlNo.equals(other.chanlNo))
			return false;
		if (freezeNo == null) {
			if (other.freezeNo != null)
				return false;
		} else if (!freezeNo.equals(other.freezeNo))
			return false;
		if (projCode == null) {
			if (other.projCode != null)
				return false;
		} else if (!projCode.equals(other.projCode))
			return false;
		if (rcvAbsArray == null) {
			if (other.rcvAbsArray != null)
				return false;
		} else if (!rcvAbsArray.equals(other.rcvAbsArray))
			return false;
		if (rcvAcctNoArray == null) {
			if (other.rcvAcctNoArray != null)
				return false;
		} else if (!rcvAcctNoArray.equals(other.rcvAcctNoArray))
			return false;
		if (rcvAcctNoIdArray == null) {
			if (other.rcvAcctNoIdArray != null)
				return false;
		} else if (!rcvAcctNoIdArray.equals(other.rcvAcctNoIdArray))
			return false;
		if (rcvAcctTypeArray == null) {
			if (other.rcvAcctTypeArray != null)
				return false;
		} else if (!rcvAcctTypeArray.equals(other.rcvAcctTypeArray))
			return false;
		if (rcvAmtArray == null) {
			if (other.rcvAmtArray != null)
				return false;
		} else if (!rcvAmtArray.equals(other.rcvAmtArray))
			return false;
		if (signMsg == null) {
			if (other.signMsg != null)
				return false;
		} else if (!signMsg.equals(other.signMsg))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PayRequest [chanlNo=" + chanlNo + ", acctNoId=" + acctNoId
				+ ", acctType=" + acctType + ", acctNo=" + acctNo
				+ ", freezeNo=" + freezeNo + ", projCode=" + projCode
				+ ", chanlFlowNo=" + chanlFlowNo + ", amt=" + amt + ", url="
				+ url + ", rcvAcctTypeArray=" + rcvAcctTypeArray
				+ ", rcvAcctNoIdArray=" + rcvAcctNoIdArray
				+ ", rcvAcctNoArray=" + rcvAcctNoArray + ", rcvAmtArray="
				+ rcvAmtArray + ", rcvAbsArray=" + rcvAbsArray + ", signMsg="
				+ signMsg + "]";
	}

	
	


}

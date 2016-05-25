package com.douins.pay.domain.vo;


public class PayResponseVo {
	
	private String errorCode;	 //	响应代码（成功时固定为“0000”）
	private String errorMsg	; //	响应信息
	private String chanlFlowNo;	  //渠道流水号
	private String  signMsg;		//	数据签名（详见4）
	private String  policyId;		//	订单号
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public String getChanlFlowNo() {
		return chanlFlowNo;
	}


	public void setChanlFlowNo(String chanlFlowNo) {
		this.chanlFlowNo = chanlFlowNo;
	}


	public String getSignMsg() {
		return signMsg;
	}


	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}


	public String getPolicyId() {
		return policyId;
	}


	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chanlFlowNo == null) ? 0 : chanlFlowNo.hashCode());
		result = prime * result
				+ ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result
				+ ((errorMsg == null) ? 0 : errorMsg.hashCode());
		result = prime * result
				+ ((policyId == null) ? 0 : policyId.hashCode());
		result = prime * result + ((signMsg == null) ? 0 : signMsg.hashCode());
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
		PayResponseVo other = (PayResponseVo) obj;
		if (chanlFlowNo == null) {
			if (other.chanlFlowNo != null)
				return false;
		} else if (!chanlFlowNo.equals(other.chanlFlowNo))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (errorMsg == null) {
			if (other.errorMsg != null)
				return false;
		} else if (!errorMsg.equals(other.errorMsg))
			return false;
		if (policyId == null) {
			if (other.policyId != null)
				return false;
		} else if (!policyId.equals(other.policyId))
			return false;
		if (signMsg == null) {
			if (other.signMsg != null)
				return false;
		} else if (!signMsg.equals(other.signMsg))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PayResponseVo [errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + ", chanlFlowNo=" + chanlFlowNo + ", signMsg="
				+ signMsg + ", policyId=" + policyId + "]";
	}


}

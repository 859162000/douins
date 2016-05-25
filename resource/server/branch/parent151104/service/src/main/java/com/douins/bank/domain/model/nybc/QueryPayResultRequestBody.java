package com.douins.bank.domain.model.nybc;

import com.douins.account.domain.model.User;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QueryPayResultRequestBody {
	
	@XStreamAlias("TRAN_TYPE")
	private String trantype;
	@XStreamAlias("CHANL_FLOW_NO")
	private String chanlflowno;
	
	public String getTrantype() {
		return trantype;
	}
	public String getChanlflowno() {
		return chanlflowno;
	}
	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}
	public void setChanlflowno(String chanlflowno) {
		this.chanlflowno = chanlflowno;
	}
	
	
}

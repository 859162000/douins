package com.douins.bank.domain.vo;

import java.util.List;

import com.douins.trans.domain.vo.ResponseTransVo;


public class BankTypeResponse extends ResponseTransVo {
	private static final long serialVersionUID = 1L;
	private List<BankTypeResponseVo> banklist;

	public List<BankTypeResponseVo> getBanklist() {
		return banklist;
	}

	public void setBanklist(List<BankTypeResponseVo> banklist) {
		this.banklist = banklist;
	}

}

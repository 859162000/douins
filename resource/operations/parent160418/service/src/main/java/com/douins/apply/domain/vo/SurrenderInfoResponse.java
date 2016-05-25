package com.douins.apply.domain.vo;

import com.douins.trans.domain.vo.ResponseTransVo;

public class SurrenderInfoResponse extends ResponseTransVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6281197762256169804L;

	private SurrenderInfoVo surrenderInfoVo;

	public SurrenderInfoVo getSurrenderInfoVo() {
		return surrenderInfoVo;
	}

	public void setSurrenderInfoVo(SurrenderInfoVo surrenderInfoVo) {
		this.surrenderInfoVo = surrenderInfoVo;
	}

}

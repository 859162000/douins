package com.mango.api.applyAPI.vo;

import com.mango.api.basic.vo.ResponseTransVo;

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

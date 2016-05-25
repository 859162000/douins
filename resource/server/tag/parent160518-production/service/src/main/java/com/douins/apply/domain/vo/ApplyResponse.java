package com.douins.apply.domain.vo;

import com.douins.apply.domain.model.ApplyInfo;
import com.douins.trans.domain.vo.ResponseTransVo;

public class ApplyResponse extends ResponseTransVo {

	private static final long serialVersionUID = -3883010065542144131L;
	private ApplyInfo applyInfo;
	public ApplyInfo getApplyInfo() {
		return applyInfo;
	}
	public void setApplyInfo(ApplyInfo applyInfo) {
		this.applyInfo = applyInfo;
	}
}

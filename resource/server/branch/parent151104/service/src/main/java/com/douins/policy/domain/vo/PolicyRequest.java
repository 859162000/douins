package com.douins.policy.domain.vo;

import com.douins.trans.domain.vo.RequestTransVo;

public class PolicyRequest extends RequestTransVo {
	private static final long serialVersionUID = 7259857081616959227L;
	private PolicyRequestVo policyVo;//保单信息

	public PolicyRequestVo getPolicyVo() {
		return policyVo;
	}
	public void setPolicyVo(PolicyRequestVo policyVo) {
		this.policyVo = policyVo;
	}
}

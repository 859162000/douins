package com.douins.account.domain.vo;

import com.douins.trans.domain.vo.RequestTransVo;

public class ValidateCodeRequest extends RequestTransVo{
	private static final long serialVersionUID = 560503505051631761L;
	private ValidateCodeRequestVo validateCodeVo;
	public ValidateCodeRequestVo getValidateCodeVo() {
		return validateCodeVo;
	}
	public void setValidateCodeVo(ValidateCodeRequestVo validateCodeVo) {
		this.validateCodeVo = validateCodeVo;
	}
}

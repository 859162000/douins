/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.apply.domain.vo;

import com.douins.apply.domain.model.LoanApply;
import com.douins.trans.domain.vo.RequestTransVo;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年5月5日 下午1:33:51
 */
public class LoanRequest extends RequestTransVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2143255308959812871L;
	private LoanApply loanApply;

	public LoanApply getLoanApply() {
		return loanApply;
	}

	public void setLoanApply(LoanApply loanApply) {
		this.loanApply = loanApply;
	}

}

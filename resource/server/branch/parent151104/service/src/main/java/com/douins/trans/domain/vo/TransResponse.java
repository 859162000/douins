package com.douins.trans.domain.vo;

import java.io.Serializable;
import java.util.List;

public class TransResponse<T> extends ResponseTransVo implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private List<BusinessVo> businessTrans;

	public List<BusinessVo> getBusinessTrans() {
		return businessTrans;
	}

	public void setBusinessTrans(List<BusinessVo> businessTrans) {
		this.businessTrans = businessTrans;
	}
	
	
	

	
	
	

	
	
	

}

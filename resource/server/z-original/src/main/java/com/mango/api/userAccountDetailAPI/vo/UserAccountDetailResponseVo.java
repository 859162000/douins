package com.mango.api.userAccountDetailAPI.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserAccountDetailResponseVo implements Serializable {

	private static final long serialVersionUID = 8447850703686075451L;
    private String detailType;
    private String detailIo;
    private Date applyTime;
    private BigDecimal applyAmount;
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	public String getDetailIo() {
		return detailIo;
	}
	public void setDetailIo(String detailIo) {
		this.detailIo = detailIo;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	
}

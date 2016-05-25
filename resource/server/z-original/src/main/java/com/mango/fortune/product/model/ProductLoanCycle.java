package com.mango.fortune.product.model;

import java.math.BigDecimal;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

public class ProductLoanCycle extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7013497095956681044L;

	private Long id;

	private String loanCycleId;

	private String productId;

	private String unit;

	private BigDecimal cycle;

	private BigDecimal rate;

	private String isvalid;

	public String getLoanCycleId() {
		return loanCycleId;
	}

	public void setLoanCycleId(String loanCycleId) {
		this.loanCycleId = loanCycleId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getCycle() {
		return cycle;
	}

	public void setCycle(BigDecimal cycle) {
		this.cycle = cycle;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	@Override
	public void initPrimaryKey() {
		this.setLoanCycleId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
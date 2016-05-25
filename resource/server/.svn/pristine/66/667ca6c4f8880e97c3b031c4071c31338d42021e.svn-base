package com.douins.policy.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.douins.product.domain.enums.PeriodType;
import com.douins.product.domain.enums.ProductType;
import com.douins.account.domain.vo.UserResponseVo;
import com.douins.policy.domain.enums.PolicyStatus;

public class PolicyResponseVo implements Serializable {
	private static final long serialVersionUID = 5485487189275048838L;
	//保单信息
	private String policyId;
	private String status;
	private String statusName;
	private Date applyTime;
	private Date payTime;
	private Date validateDate;
	private Date expireDate;
	private BigDecimal totalPrem;      // 总保费
	private BigDecimal totalRevenue;//预期总收益
	private String sendCode;
	private String policyCode;             // 保单号
	private BigDecimal yesDayEarn;//昨日收益
	private BigDecimal accuIncome;       // 当前累计总收益 ,和PolicyMaster entity  保持一致 
	
	
	private BigDecimal yesDayIncome;
	private BigDecimal nowRevenue;//当前收益 （sty ）
	private String applicantId;
	private String insuredId;
	private UserResponseVo userInfo;
	private String orderNo;
	private String applyNo;   //投保单
	private String policyUrl; //保单url
	private String chanlFlowNo;
	

	//产品信息
	private String productId;
	private String productName;
	private String productType;
	private String productTypeName;
	private Integer productPeriod;
	private String productPeriodName;
	private String periodType;
	private BigDecimal expectRate;
	private int hesitateTerm;//犹豫期
	 
	private int rateLoanable;          // 可贷比例
	private BigDecimal loanableAmount;         // 可贷金额
	private int periodDays;            // 理财周期天数
	
	
	
	public int getHesitateTerm() {
		return hesitateTerm;
	}
	public void setHesitateTerm(int hesitateTerm) {
		this.hesitateTerm = hesitateTerm;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getValidateDate() {
		return validateDate;
	}
	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public BigDecimal getTotalPrem() {
		return totalPrem;
	}
	public void setTotalPrem(BigDecimal totalPrem) {
		this.totalPrem = totalPrem;
	}
	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public String getSendCode() {
		return sendCode;
	}
	public void setSendCode(String sendCode) {
		this.sendCode = sendCode;
	}
	public String getPolicyCode() {
		return policyCode;
	}
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Integer getProductPeriod() {
		return productPeriod;
	}
	public void setProductPeriod(Integer productPeriod) {
		this.productPeriod = productPeriod;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public BigDecimal getExpectRate() {
		return expectRate;
	}
	public void setExpectRate(BigDecimal expectRate) {
		this.expectRate = expectRate;
	}
	public BigDecimal getYesDayEarn() {
		return yesDayEarn;
	}
	public void setYesDayEarn(BigDecimal yesDayEarn) {
		this.yesDayEarn = yesDayEarn;
	}
	public BigDecimal getNowRevenue() {
		return nowRevenue;
	}
	public void setNowRevenue(BigDecimal nowRevenue) {
		this.nowRevenue = nowRevenue;
	}
	public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getInsuredId() {
        return insuredId;
    }
    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId;
    }
    public UserResponseVo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserResponseVo userInfo) {
        this.userInfo = userInfo;
    }
    
    
    public String getProductTypeName() {
		if(StringUtils.isNotBlank(this.getProductType())){
			return ProductType.getDescByValue(Integer.parseInt(this.getProductType()));
		}
		return "";
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductPeriodName() {
		if (StringUtils.isNotBlank(this.getPeriodType())) {
			if (PeriodType.MONTH.getValue().equals(this.getPeriodType())) {
				return this.getProductPeriod() + "个"
						+ PeriodType.getNameByValue(this.getPeriodType());
			} else {
				return this.getProductPeriod()
						+ PeriodType.getNameByValue(this.getPeriodType());
			}
		}
		return "";
	}
	public void setProductPeriodName(String productPeriodName) {
		this.productPeriodName = productPeriodName;
	}
	public String getStatusName() {
		if(StringUtils.isNotBlank(this.getStatus())){
			return PolicyStatus.getNameByValue(this.getStatus());
		}
		return "";
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
    public int getRateLoanable() {
        return rateLoanable;
    }
    public void setRateLoanable(int rateLoanable) {
        this.rateLoanable = rateLoanable;
    }
    public BigDecimal getLoanableAmount() {
        return loanableAmount;
    }
    public void setLoanableAmount(BigDecimal loanableAmount) {
        this.loanableAmount = loanableAmount;
    }
    public int getPeriodDays() {
        return periodDays;
    }
    public void setPeriodDays(int periodDays) {
        this.periodDays = periodDays;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
	public String getPolicyUrl() {
		return policyUrl;
	}
	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}
	
	public BigDecimal getAccuIncome() {
		return accuIncome;
	}
	public void setAccuIncome(BigDecimal accuIncome) {
		this.accuIncome = accuIncome;
	}
	
	public String getChanlFlowNo() {
		return chanlFlowNo;
	}
	public void setChanlFlowNo(String chanlFlowNo) {
		this.chanlFlowNo = chanlFlowNo;
	}
	public BigDecimal getYesDayIncome() {
		return yesDayIncome;
	}
	public void setYesDayIncome(BigDecimal yesDayIncome) {
		this.yesDayIncome = yesDayIncome;
	}
    
}

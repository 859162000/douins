/**
 * 
 */
package com.douins.apply.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mango.orm.KeyGenerator;

/** 
* @ClassName: SurrenderApply 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 上午11:27:33 
*  
*/
public class SurrenderApply extends ApplyInfo {
    private static final long serialVersionUID = -5948520188655567434L;

    private Long id;
    
    private String surrendApplyId;

    private String applyInfoId;

    private String surrendNo;

    private String surrendType;
    
    private BigDecimal repayAmount;

    private BigDecimal principal;//本金

    private BigDecimal totalRevenue;

    private BigDecimal handfee;

    private Date repayTime;

    private String status;

    private String isvalid;

    public String getSurrendApplyId() {
        return surrendApplyId;
    }

    public void setSurrendApplyId(String surrendApplyId) {
        this.surrendApplyId = surrendApplyId;
    }

    public String getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(String applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    public String getSurrendNo() {
        return surrendNo;
    }

    public void setSurrendNo(String surrendNo) {
        this.surrendNo = surrendNo;
    }

    public String getSurrendType() {
        return surrendType;
    }

    public void setSurrendType(String surrendType) {
        this.surrendType = surrendType;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getHandfee() {
        return handfee;
    }

    public void setHandfee(BigDecimal handfee) {
        this.handfee = handfee;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    @Override
    public void initPrimaryKey() {
        this.setSurrendApplyId(KeyGenerator.randomSeqNum());
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: GroupInfo 
* @Description: qunar 请求数据的 groupInfo 通用
* @author G. F. 
* @date 2015年12月30日 下午4:50:07 
*  
*/
@XStreamAlias("groupinfo")
public class GroupInfo {
    @XStreamAlias("orderno")
    private String orderNo;
    @XStreamAlias("businessid")
    private String businessId;
    @XStreamAlias("agencyno")
    private String agencyNo;
    @XStreamAlias("applydate")
    private String applyDate; 
    
    // 退保确认
    @XStreamAlias("revokeamount")
    private String revokeAmount;
    @XStreamAlias("bankseqno")
    private String bankSeqNo;
    @XStreamAlias("bankdealdate")
    private String bankDealDate;
    @XStreamAlias("paytype")
    private String payType;
    @XStreamAlias("bankno")
    private String bankNo;
    
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getAgencyNo() {
        return agencyNo;
    }
    public void setAgencyNo(String agencyNo) {
        this.agencyNo = agencyNo;
    }
    public String getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
    public String getRevokeAmount() {
        return revokeAmount;
    }
    public void setRevokeAmount(String revokeAmount) {
        this.revokeAmount = revokeAmount;
    }
    public String getBankSeqNo() {
        return bankSeqNo;
    }
    public void setBankSeqNo(String bankSeqNo) {
        this.bankSeqNo = bankSeqNo;
    }
    public String getBankDealDate() {
        return bankDealDate;
    }
    public void setBankDealDate(String bankDealDate) {
        this.bankDealDate = bankDealDate;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getBankNo() {
        return bankNo;
    }
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
  
}

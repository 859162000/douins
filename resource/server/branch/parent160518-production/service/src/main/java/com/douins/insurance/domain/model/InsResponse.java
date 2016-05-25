/**
 * 
 */
package com.douins.insurance.domain.model;

/** 
* @ClassName: InsResponse 
* @Description: 复星返回的数据模型
* @author G. F. 
* @date 2015年12月16日 下午6:21:26 
*  
*/
public class InsResponse {
    private String orderId;
    private String proposalNo;
    private String totalPremium;
    private String underwriteFlag;
    private String failReason;
    private String requestType;
    private String responseCode;
    private String errMsg;
    private String sendTime;
    private String accDate;
    private String success;
    private String policyNo;
    private String policyUrl;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getProposalNo() {
        return proposalNo;
    }
    public void setProposalNo(String proposalNo) {
        this.proposalNo = proposalNo;
    }
    public String getTotalPremium() {
        return totalPremium;
    }
    public void setTotalPremium(String totalPremium) {
        this.totalPremium = totalPremium;
    }
    public String getUnderwriteFlag() {
        return underwriteFlag;
    }
    public void setUnderwriteFlag(String underwriteFlag) {
        this.underwriteFlag = underwriteFlag;
    }
    public String getFailReason() {
        return failReason;
    }
    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    public String getSendTime() {
        return sendTime;
    }
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
    public String getAccDate() {
        return accDate;
    }
    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public String getPolicyNo() {
        return policyNo;
    }
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }
    public String getPolicyUrl() {
        return policyUrl;
    }
    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }
}

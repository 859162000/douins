/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.model;

/** 
* @ClassName: ResponseRecord 
* @Description: 响应中的 record
* @author G. F. 
* @date 2016年1月8日 下午5:35:10 
*  
*/
public class ResponseRecord {
    private String applySeq;
    private String applyPolicyNo;
    private String payPremium;
    private String responseCode;
    private String responseMessage;
    private String policyNo;
    private String revokeAmount;
    private String revokeDate;
    private String errMsg;
    
    public String getApplySeq() {
        return applySeq;
    }
    public void setApplySeq(String applySeq) {
        this.applySeq = applySeq;
    }
    public String getApplyPolicyNo() {
        return applyPolicyNo;
    }
    public void setApplyPolicyNo(String applyPolicyNo) {
        this.applyPolicyNo = applyPolicyNo;
    }
    public String getPayPremium() {
        return payPremium;
    }
    public void setPayPremium(String payPremium) {
        this.payPremium = payPremium;
    }
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public String getPolicyNo() {
        return policyNo;
    }
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }
    public String getRevokeAmount() {
        return revokeAmount;
    }
    public void setRevokeAmount(String revokeAmount) {
        this.revokeAmount = revokeAmount;
    }
    public String getRevokeDate() {
        return revokeDate;
    }
    public void setRevokeDate(String revokeDate) {
        this.revokeDate = revokeDate;
    }
    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

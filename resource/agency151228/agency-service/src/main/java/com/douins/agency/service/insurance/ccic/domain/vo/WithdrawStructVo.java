/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.vo;

import com.douins.agency.service.insurance.ccic.domain.model.ChannelInfo;

/** 
* @ClassName: withdrawStructVo 
* @Description: 退保请求的数据
* @author G. F. 
* @date 2016年1月7日 上午11:40:04 
*  
*/
public class WithdrawStructVo {
    private ChannelInfo channel;
    private String policyNo;
    private String endorseType;
    private String endorseDate;
    private String validDate;
    private String validHour;
    
    public ChannelInfo getChannel() {
        return channel;
    }
    public void setChannel(ChannelInfo channel) {
        this.channel = channel;
    }
    public String getPolicyNo() {
        return policyNo;
    }
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }
    public String getEndorseType() {
        return endorseType;
    }
    public void setEndorseType(String endorseType) {
        this.endorseType = endorseType;
    }
    public String getEndorseDate() {
        return endorseDate;
    }
    public void setEndorseDate(String endorseDate) {
        this.endorseDate = endorseDate;
    }
    public String getValidDate() {
        return validDate;
    }
    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
    public String getValidHour() {
        return validHour;
    }
    public void setValidHour(String validHour) {
        this.validHour = validHour;
    }
}

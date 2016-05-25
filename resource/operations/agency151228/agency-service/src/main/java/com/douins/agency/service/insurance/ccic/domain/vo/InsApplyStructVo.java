/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.vo;

import com.douins.agency.service.insurance.ccic.domain.model.ChannelInfo;

/** 
* @ClassName: InsApplyStructVo 
* @Description: 承保申请数据
* @author G. F. 
* @date 2016年1月1日 上午10:08:15 
*  
*/
public class InsApplyStructVo {
    private ChannelInfo channel;
    /**
     * 单证流水号
     */
    private String printNo;
    /**
     * 投保单号
     */
    private String businessNo;
    /**
     * 原渠道交易流水号
     */
    private String originTradeSerialNo;
    /**
     * 渠道关联单号
     */
    private String originChannelRelationNo;
    
    private String orderNo;
    private String applySeq;
    
    public ChannelInfo getChannel() {
        return channel;
    }
    public void setChannel(ChannelInfo channel) {
        this.channel = channel;
    }
    public String getPrintNo() {
        return printNo;
    }
    public void setPrintNo(String printNo) {
        this.printNo = printNo;
    }
    public String getBusinessNo() {
        return businessNo;
    }
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }
    public String getOriginTradeSerialNo() {
        return originTradeSerialNo;
    }
    public void setOriginTradeSerialNo(String originTradeSerialNo) {
        this.originTradeSerialNo = originTradeSerialNo;
    }
    public String getOriginChannelRelationNo() {
        return originChannelRelationNo;
    }
    public void setOriginChannelRelationNo(String originChannelRelationNo) {
        this.originChannelRelationNo = originChannelRelationNo;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getApplySeq() {
        return applySeq;
    }
    public void setApplySeq(String applySeq) {
        this.applySeq = applySeq;
    }
}

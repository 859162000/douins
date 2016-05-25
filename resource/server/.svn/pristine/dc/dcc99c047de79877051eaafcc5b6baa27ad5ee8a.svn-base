/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: SystermHead 
* @Description:  xml 中的系统头部分
* @author G. F. 
* @date 2015年12月5日 下午11:38:29 
*  
*/
public class SystermHead {
    @XStreamAlias("CONSUMER_SEQ_NO")
    private String seqNo;                   // 交易流水号
    @XStreamAlias("SERVICE_CODE")
    private String serviceCode;         // 业务代码
    @XStreamAlias("SERVICE_SCENE")
    private String scene;                   // 业务场景
    @XStreamAlias("TRAN_DATE")
    private String date;                    // 交易日期
    @XStreamAlias("TRAN_TIMESTAMP")
    private String timestamp;           // 交易时间戳
    @XStreamAlias("IP_ADD")
    private String ip;                          // 我方的IP 地址
    @XStreamAlias("SIGN_MSG")
    private String sign;                    // 签名
    
    {
        sign = StringUtils.EMPTY;
    }
    
    public String getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    public String getServiceCode() {
        return serviceCode;
    }
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    public String getScene() {
        return scene;
    }
    public void setScene(String scene) {
        this.scene = scene;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
}

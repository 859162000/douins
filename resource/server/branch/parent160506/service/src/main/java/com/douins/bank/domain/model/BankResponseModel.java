/**
 * 
 */
package com.douins.bank.domain.model;

import com.douins.common.domain.BaseModel;

/** 
* @ClassName: GateWayResponse 
* @Description: 银行请求／响应数据的模型
* @author G. F. 
* @date 2015年12月9日 下午6:05:25 
*  
*/
public class BankResponseModel extends BaseModel {
    private long id;
    private String userId;                         // 用户 id
    private String transId;                 // dy 自己的交易流水号
    private String transType;            // 交易类型
    private String status;                   // 交易状态，由银行返回
    private String errorMsg;             // 交易失败的错误信息
    private String bankCode;            // 银行代码
    private String bankTransId;         //  银行记录的交易流水号 flowId
    private String channelNo;           // 渠道号，银行分配给平台的
    private String sign;                      // 银行返回的签名
//    private String operatType;	         // 交易类型
    
    
//    public String getOperatType() {
//		return operatType;
//	}
//	public void setOperatType(String operatType) {
//		this.operatType = operatType;
//	}
	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTransId() {
        return transId;
    }
    public void setTransId(String transId) {
        this.transId = transId;
    }
    public String getTransType() {
        return transType;
    }
    public void setTransType(String transType) {
        this.transType = transType;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getBankCode() {
        return bankCode;
    }
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    public String getBankTransId() {
        return bankTransId;
    }
    public void setBankTransId(String bankTransId) {
        this.bankTransId = bankTransId;
    }
    public String getChannelNo() {
        return channelNo;
    }
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
}

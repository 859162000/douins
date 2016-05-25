/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: ResponseStatus 
* @Description: CCIC 响应状态
* @author G. F. 
* @date 2016年1月5日 下午9:32:19 
*  
*/
@XStreamAlias("responsestatus")
public class ResponseStatus {
    @XStreamAlias("responsecode")
    private String responseCode;
    @XStreamAlias("responsemessage")
    private String responseMessage;
    
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
}

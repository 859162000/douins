/**
 * 
 */
package com.douins.bank.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: BankResponse 
* @Description: 银行响应数据的基类
* @author G. F. 
* @date 2015年12月5日 下午10:28:06 
*  
*/
public abstract class BankResponse {
    @XStreamAlias("ERROR_CODE")
    private String code;        // 响应代码
    @XStreamAlias("ERROR_MSG")
    private String message;     // 响应信息
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

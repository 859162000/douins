/**
 * 
 */
package com.douins.bank.domain.vo;

import com.douins.bank.domain.model.nybc.RegistAccountResponseBody;
import com.douins.trans.domain.enums.ResponseCode;

/** 
* @ClassName: RegistAccountResponseVo 
* @Description: 资金账户开户的响应Vo
* @author G. F. 
* @date 2015年12月8日 下午3:06:51 
*  
*/
public class RegistAccountResponseVo {
    private ResponseCode responseCode;
    private RegistAccountResponseBody body;
    
    public ResponseCode getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
    public RegistAccountResponseBody getBody() {
        return body;
    }
    public void setBody(RegistAccountResponseBody body) {
        this.body = body;
    }
}

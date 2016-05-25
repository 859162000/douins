/**
 * 
 */
package com.douins.account.domain.model;

/** 
* @ClassName: ClientId 
* @Description: 用于推送的用户客户端 id
* @author G. F. 
* @date 2016年2月1日 下午2:36:46 
*  
*/
public class ClientId {
    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

	@Override
	public String toString() {
		return "ClientId [clientId=" + clientId + "]";
	}
    
}

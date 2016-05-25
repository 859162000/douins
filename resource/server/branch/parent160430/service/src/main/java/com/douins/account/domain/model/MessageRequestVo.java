/**
 * 
 */
package com.douins.account.domain.model;

import com.douins.account.domain.vo.MessageRequest;
import com.douins.trans.domain.vo.RequestTransVo;

/** 
* @ClassName: MessageRequestVo 
* @Description: 消息
* @author G. F. 
* @date 2015年11月23日 下午2:41:55 
*  
*/
public class MessageRequestVo extends RequestTransVo {
    private static final long serialVersionUID = -2890404710659007433L;
    private MessageRequest messageRequest;
    
    public MessageRequest getMessageRequest() {
        return messageRequest;
    }
    public void setMessageRequest(MessageRequest messageRequest) {
        this.messageRequest = messageRequest;
    }
	@Override
	public String toString() {
		return "MessageRequestVo [messageRequest=" + messageRequest + "]";
	}
    
}

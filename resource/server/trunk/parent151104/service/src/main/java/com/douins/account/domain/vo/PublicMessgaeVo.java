/**
 * 
 */
package com.douins.account.domain.vo;

import java.util.Date;
import java.util.List;

import com.douins.account.domain.model.PublicMessage;
import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: PublicMessgaeVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月23日 上午9:16:33 
*  
*/
public class PublicMessgaeVo extends ResponseTransVo {
    private static final long serialVersionUID = -7825104041131499493L;
    
    private List<PublicMessage> messages;

    public List<PublicMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<PublicMessage> messages) {
        this.messages = messages;
    }

	@Override
	public String toString() {
		return "PublicMessgaeVo [messages=" + messages + "]";
	}

    
    
}

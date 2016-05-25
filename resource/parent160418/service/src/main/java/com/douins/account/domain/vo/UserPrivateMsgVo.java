/**
 * 
 */
package com.douins.account.domain.vo;

import java.util.Date;
import java.util.List;

import com.douins.account.domain.model.UserPrivateMessage;
import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: UserPrivateMsgVo 
* @Description: 用户私有消息的封装类
* @author G. F. 
* @date 2015年11月20日 下午5:09:41
*  
*/
public class UserPrivateMsgVo extends ResponseTransVo{
    private static final long serialVersionUID = 1L;
    
    private List<UserPrivateMessage> messages;

    public List<UserPrivateMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<UserPrivateMessage> messages) {
        this.messages = messages;
    }
    
}

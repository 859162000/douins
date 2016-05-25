/**
 * 
 */
package com.douins.account.service.iml;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.douins.account.dao.UserPrivateMessageDao;
import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAuthority;
import com.douins.account.domain.model.UserPrivateMessage;
import com.douins.account.domain.vo.MessageRequest;
import com.douins.trans.domain.enums.ResponseCode;

/** 
* @ClassName: UserPrivateMsgService 
* @Description: 用户私有消息
* @author G. F. 
* @date 2015年11月20日 下午5:37:17 
*  
*/
@Service
public class UserPrivateMsgService {
    private static final Logger logger = Logger.getLogger(UserPrivateMessage.class);
    @Inject
    private UserPrivateMessageDao messageDao;
    
    @ Inject
    private UserAuthorityService authorityService;
    
    /**
     * 添加用户消息
     * @param msg
     */
    public ResponseCode add(UserPrivateMessage msg){
        if(msg.getTitle() == null || msg.getContent() == null){
            return ResponseCode.FAILED;
        }
        
        messageDao.add(msg);
        
        return ResponseCode.SUCCESS;
    }
    
    /**
     * 获取用户未删除的所有消息
     * @param uid
     * @param start
     * @param size
     * @return
     */
    public List<UserPrivateMessage> getUserPrivMsg(String uid, int start, int size){
        List<UserPrivateMessage> messages = messageDao.findByUid(uid, start, size);
        if(messages == null){
            messages = Collections.emptyList();
        }
        
        return messages;
    }
    
    /**
     * 根据 token 查询用户消息
     * @param token
     * @param request
     * @return
     */
    public List<UserPrivateMessage> getUserPrivMsg(String token, MessageRequest request){
        UserAuthority auth = authorityService.findUserByToken(token);
        if(auth == null){
            return Collections.emptyList();
        }
        
        request.setUid(auth.getUid());
        List<UserPrivateMessage> messages = messageDao.listById(request);
        if(messages == null){
            messages = Collections.emptyList();
        }
        
        // 排序
        Collections.sort(messages, new Comparator<UserPrivateMessage>(){
            @Override
            public int compare(UserPrivateMessage o1, UserPrivateMessage o2) {
                return o2.getUpdateTime().compareTo(o1.getUpdateTime());
            }
        });
        
        return messages;
    }
    
    /**
     * 删除指定的私有消息
     * @param msgId
     */
    public void deleteMsg(long msgId){
        messageDao.deleted(msgId);
    }
    
    /**
     * 将指定私有消息置为已读
     * @param msgId
     */
    public void setIsRead(long msgId){
        messageDao.updateReadStatus(msgId);
    }
    
    /**
     * 获取用户未读的私有消息数
     * @param uid
     * @return
     */
    public int getUnreadMsgCount(String uid){
        return messageDao.getUnreadCount(uid);
    }
}

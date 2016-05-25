/**
 * 
 */
package com.douins.account.service.iml;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.douins.account.dao.PublicMessageDao;
import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.vo.MessageRequest;
import com.douins.trans.domain.enums.ResponseCode;

/** 
* @ClassName: PublicMessageService 
* @Description: 公告
* @author G. F. 
* @date 2015年11月23日 上午9:40:26 
*  
*/
@Service
public class PublicMessageService {
    
    @Inject
    private PublicMessageDao messageDao;
    
   /**
    * 添加一条公告信息
    * @param msg
    * @return
    */
    public ResponseCode add(PublicMessage msg){
        if(msg.getTitle() == null || msg.getContent() == null){
            return ResponseCode.FAILED;
        }
        
        messageDao.add(msg);
        
        return ResponseCode.SUCCESS;
    }
    
    /**
     * 获取所有公告
     * @param start
     * @param size
     * @return
     */
    public List<PublicMessage> getMessages(int start, int size){
        List<PublicMessage> messages = messageDao.listMessages(start, size);
        if(messages == null){
            messages = Collections.emptyList();
        }
        
        return messages;
    }
    
    /**
     * 获取
     * @param request
     * @return
     */
    public List<PublicMessage> getMessages(MessageRequest request){
        List<PublicMessage> messages = messageDao.listMessagesById(request);
        if(messages == null){
            messages = Collections.emptyList();
        }
        
        // 排序
        Collections.sort(messages, new Comparator<PublicMessage>(){
            @Override
            public int compare(PublicMessage o1, PublicMessage o2) {
                return o2.getUpdateTime().compareTo(o1.getUpdateTime());
            }
        });
        
        return messages;
    }
}

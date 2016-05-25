/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.vo.MessageRequest;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: PublicMessageDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月23日 上午9:21:11 
*  
*/
@MyBatisMapper
public interface PublicMessageDao{

    /**
     * 插入一条消息
     * @param msg
     */
    public void add(PublicMessage msg);
    
    /**
     * 提取所有公告信息
     * @param start 
     * @param size
     * @return
     */
    public List<PublicMessage> listMessages(@Param("start")int start,@Param("size") int size);
    
    /**
     * 获取指定 ID 的公告
     * @param msgId
     * @return
     */
    public PublicMessage findById(@Param("msgId")long msgId);
    /**
     * 提取公告信息
     * @param request
     * @return
     */
    public List<PublicMessage> listMessagesById(MessageRequest request);
}

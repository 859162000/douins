/**
 * 
 */
package com.douins.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserPrivateMessage;
import com.douins.account.domain.vo.MessageRequest;
import com.douins.common.dao.AbstractDao;
import com.douins.common.persistence.annotation.MyBatisMapper;
import com.google.common.collect.Maps;

/** 
* @ClassName: UserPrivateMessageDao 
* @Description: 用户私有消息的数据
* @author G. F. 
* @date 2015年11月20日 下午4:42:41 
*  
*/
@MyBatisMapper
public interface UserPrivateMessageDao{

    /**
     * 添加一条记录
     * @param msg
     */
    public void add(UserPrivateMessage msg);
    
    /**
     * 提取用户的所有信息
     * @param uid
     * @param start
     * @param size
     * @return
     */
    public List<UserPrivateMessage> findByUid(@Param("uid") String uid,@Param("start") int start,@Param("size") int size);
    
    /**
     * 提取用户的所有信息
     * @param request
     * @return
     */
    public  List<UserPrivateMessage> listById(MessageRequest request);
    /**
     * 软删除
     * @param msgId
     */
    public void deleted(long msgId);
    
    /**
     * 更新为已读
     * @param msgId
     */
    public void updateReadStatus(@Param("msgId")long msgId);
    
    /**
     *获取用户未读消息的条数
     * @param uid
     * @return
     */
    public int getUnreadCount(@Param("uid")String uid);
}

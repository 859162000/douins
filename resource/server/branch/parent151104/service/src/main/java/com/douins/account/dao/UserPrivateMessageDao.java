/**
 * 
 */
package com.douins.account.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserPrivateMessage;
import com.douins.account.domain.vo.MessageRequest;
import com.douins.common.dao.AbstractDao;
import com.google.common.collect.Maps;

/** 
* @ClassName: UserPrivateMessageDao 
* @Description: 用户私有消息的数据
* @author G. F. 
* @date 2015年11月20日 下午4:42:41 
*  
*/
@Repository
public class UserPrivateMessageDao extends AbstractDao {

    /**
     * 添加一条记录
     * @param msg
     */
    public void add(UserPrivateMessage msg){
        writeSqlSession.insert(sql(), msg);
    }
    
    /**
     * 提取用户的所有信息
     * @param uid
     * @param start
     * @param size
     * @return
     */
    public List<UserPrivateMessage> findByUid(String uid, int start, int size){
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", uid);
        params.put("start", start);
        params.put("size", size);
        
        return list(writeSqlSession, sql(), params);
    }
    
    /**
     * 提取用户的所有信息
     * @param request
     * @return
     */
    public  List<UserPrivateMessage> listById(MessageRequest request){
        return list(writeSqlSession, sql(), request);
    }
    
    /**
     * 软删除
     * @param msgId
     */
    public void deleted(long msgId){
        writeSqlSession.update(sql(), msgId);
    }
    
    /**
     * 更新为已读
     * @param msgId
     */
    public void updateReadStatus(long msgId){
        writeSqlSession.update(sql(), msgId);
    }
    
    /**
     *获取用户未读消息的条数
     * @param uid
     * @return
     */
    public int getUnreadCount(String uid){
        return writeSqlSession.selectOne(sql(), uid);
    }
}

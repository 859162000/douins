/**
 * 
 */
package com.douins.account.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.vo.MessageRequest;
import com.douins.common.dao.AbstractDao;
import com.google.common.collect.Maps;

/** 
* @ClassName: PublicMessageDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月23日 上午9:21:11 
*  
*/
@Repository
public class PublicMessageDao extends AbstractDao {

    /**
     * 插入一条消息
     * @param msg
     */
    public void add(PublicMessage msg){
        writeSqlSession.insert(sql(), msg);
    }
    
    /**
     * 提取所有公告信息
     * @param start
     * @param size
     * @return
     */
    public List<PublicMessage> listMessages(int start, int size){
        Map<String, Integer> params = Maps.newHashMap();
        params.put("start", start);
        params.put("size", size);
        
        return list(writeSqlSession,  sql(), params);
    }
    
    /**
     * 获取指定 ID 的公告
     * @param msgId
     * @return
     */
    public PublicMessage findById(long msgId){
        return writeSqlSession.selectOne(sql(), msgId);
    }
    
    /**
     * 提取公告信息
     * @param request
     * @return
     */
    public List<PublicMessage> listMessagesById(MessageRequest request){
        return list(writeSqlSession, sql(), request);
    }
}

/**
 * 
 */
package com.douins.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.User;
import com.douins.common.dao.AbstractDao;
import com.google.common.collect.Maps;

/** 
* @ClassName: UserDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午10:57:17 
*  
*/
@Repository
public class UserDao extends AbstractDao {

    public User find(String userId){
        return writeSqlSession.selectOne(sql(), userId);
    }
    
    public User getUserByAccount(String userAccount){
        return writeSqlSession.selectOne(sql(), userAccount);
    }
    
    public List<User> getList(User user){
        return list(writeSqlSession, sql(), user);
    }
    
    public int getList_Count(User user){
        return size(writeSqlSession, sql(), user);
    }
    
    public void add(User user){
        writeSqlSession.insert(sql(), user);
    }
    
    public void update(User user){
        writeSqlSession.update(sql(), user);
    }
    
    public void delete(String userId){
        writeSqlSession.update(sql(), userId);
    }
    
    public void forceUpdateByUid(User user){
        writeSqlSession.update(sql(), user);
    }
    
    public User findUniqueByCondition(User user){
        return writeSqlSession.selectOne(sql(), user);
    }
    
    public void updateOpenIdByUid(User user){
        writeSqlSession.selectOne(sql(), user);
    }
    
    public void updateClientId(String uid, String clientId){
        Map<String, String> params = Maps.newHashMap();
        params.put("uid", uid);
        params.put("clientId", clientId);
        writeSqlSession.update(sql(), params);
    }
    
    /**
     * 根据身份证号查询用户
     * @param certiCode
     * @return
     */
    public User findByCertiCode(String certiCode){
        return writeSqlSession.selectOne(sql(), certiCode);
    }

}

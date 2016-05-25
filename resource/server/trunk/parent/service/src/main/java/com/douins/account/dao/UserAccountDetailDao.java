/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserAccountDetail;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: UserAccountDetailDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:26:33 
*  
*/
@Repository
public class UserAccountDetailDao extends AbstractDao{

    public List<UserAccountDetail> getList(UserAccountDetail detail){
        return list(writeSqlSession, sql(), detail);
    }
    
    public int getList_Count(UserAccountDetail detail){
        return size(writeSqlSession, sql(), detail);
    }
    
    public void delete(String userAccountDetailId){
        writeSqlSession.update(sql(), userAccountDetailId);
    }
    
    public void add(UserAccountDetail detail){
        writeSqlSession.insert(sql(), detail);
    }
    
    public void update(UserAccountDetail detail){
        writeSqlSession.update(sql(), detail);
    }
    
    public UserAccountDetail findByDetailId(String userAccountDetailId){
        return writeSqlSession.selectOne(sql(), userAccountDetailId);
    }
}

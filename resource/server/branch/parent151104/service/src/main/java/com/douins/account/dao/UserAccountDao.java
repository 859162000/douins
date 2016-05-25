/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserAccount;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: UserAccountDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:26:00 
*  
*/
@Repository
public class UserAccountDao extends AbstractDao {

    public List<UserAccount> getList(UserAccount user){
        return list(writeSqlSession, sql(), user);
    }
    
    public int getList_Count(UserAccount user){
        return size(writeSqlSession, sql(), user);
    }
    
    public void add(UserAccount user){
        writeSqlSession.insert(sql(), user);
    }
    
    public void delete(String userAccountId){
        writeSqlSession.update(sql(), userAccountId);
    }
    
    public void update(UserAccount user){
        writeSqlSession.update(sql(), user);
    }
    
    public UserAccount findByUserId(String userAccountId){
        return writeSqlSession.selectOne(sql(), userAccountId);
    }
}

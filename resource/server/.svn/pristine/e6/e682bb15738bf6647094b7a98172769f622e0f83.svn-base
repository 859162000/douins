/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserPayAccount;
import com.douins.account.domain.vo.UserPayAccountVo;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: UserPayAccount 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:28:26 
*  
*/
@Repository
public class UserPayAccountDao extends AbstractDao {
    
    public List<UserPayAccount> getList(UserPayAccount account){
        return list(writeSqlSession,sql(), account);
    }
    
    public int getList_Count(UserPayAccount account){
        return size(writeSqlSession, sql(), account);
    }
    
    public void add(UserPayAccount account){
        writeSqlSession.insert(sql(), account);
    }
    
    public void delete(String accountId){
        writeSqlSession.update(sql(), accountId);
    }
    
    public void update(UserPayAccount account){
        writeSqlSession.update(sql(), account);
    }
    
    public UserPayAccount findByAccountId(String accountId){
        return writeSqlSession.selectOne(sql(), accountId);
    }
    
    public List<UserPayAccount> getList4Api(UserPayAccountVo vo){
        return list(writeSqlSession, sql(), vo);
    }
    
    public int getList4Api_Count(UserPayAccountVo vo){
        return size(writeSqlSession, sql(), vo);
    }
}

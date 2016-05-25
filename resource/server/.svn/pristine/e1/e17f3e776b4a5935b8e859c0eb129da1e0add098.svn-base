/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserAccountOpenApply;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: UserAccountOpenApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:27:56 
*  
*/
@Repository
public class UserAccountOpenApplyDao extends AbstractDao {
    
    public List<UserAccountOpenApply> getList(UserAccountOpenApply apply){
        return list(writeSqlSession, sql(), apply);
    }
    
    public int getList_Count(UserAccountOpenApply apply){
        return size(writeSqlSession, sql(), apply);
    }
    
    public void add(UserAccountOpenApply apply){
        writeSqlSession.insert(sql(), apply);
    }
    
    public void delete(String openApplyId){
        writeSqlSession.update(sql(), openApplyId);
    }
    
    public void update(UserAccountOpenApply apply){
        writeSqlSession.update(sql(), apply);
    }
    
    public UserAccountOpenApply findByApplyId(String openApplyId){
        return writeSqlSession.selectOne(sql(), openApplyId);
    }
}

/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserPayAccount;
import com.douins.account.domain.vo.UserPayAccountVo;
import com.douins.common.dao.AbstractDao;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: UserPayAccount 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:28:26 
*  
*/
@MyBatisMapper
public interface UserPayAccountDao{
    
    public List<UserPayAccount> getList(UserPayAccount account);
    
    public int getList_Count(UserPayAccount account);
    
    public void add(UserPayAccount account);
    
    public void delete(@Param("accountId") String accountId);
    
    public void update(UserPayAccount account);
    
    public UserPayAccount findByAccountId(@Param("accountId") String accountId);
    
    public List<UserPayAccount> getList4Api(UserPayAccountVo vo);
    
    public int getList4Api_Count(UserPayAccountVo vo);
}

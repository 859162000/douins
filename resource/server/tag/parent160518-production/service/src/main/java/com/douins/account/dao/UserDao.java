/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.User;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: UserDao 
* @Description:用户 DAO接口
* @author G. F. 
* @date 2015年10月21日 上午10:57:17 
*  
*/
@MyBatisMapper
public interface UserDao{

    public User find(@Param("userId")String userId);  
    
    public User getUserByAccount(@Param("userAccount")String userAccount);
    
    public List<User> getList(User user);
    
    public int getList_Count(User user);
    
    public void add(User user);
    
    public void update(User user);
    
    public void delete(String userId);
    
    public void forceUpdateByUid(User user);
    
    public User findUniqueByCondition(User user);
    
    public void updateOpenIdByUid(User user);
    public void updateClientId(@Param("uid")String uid, @Param("clientId")String clientId);
    
    /**
     * 根据身份证号查询用户
     * @param certiCode
     * @return
     */
    public User findByCertiCode(@Param("certiCode")String certiCode);

}

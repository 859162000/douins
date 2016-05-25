/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.account.domain.model.UserAccountDetail;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: UserAccountDetailDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:26:33 
*  
*/
@MyBatisMapper
public interface UserAccountDetailDao{

    public List<UserAccountDetail> getList(UserAccountDetail detail);
    
    public int getList_Count(UserAccountDetail detail);
    
    public void delete(@Param("userAccountDetailId") String userAccountDetailId);
    
    public void add(UserAccountDetail detail);
    
    public void update(UserAccountDetail detail);
    
    public UserAccountDetail findByDetailId(@Param("userAccountDetailId") String userAccountDetailId);
}

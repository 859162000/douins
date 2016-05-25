/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.account.domain.model.UserAccountOpenApply;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: UserAccountOpenApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:27:56 
*  
*/
@MyBatisMapper
public interface UserAccountOpenApplyDao{
    
    public List<UserAccountOpenApply> getList(UserAccountOpenApply apply);
    
    public int getList_Count(UserAccountOpenApply apply);
    
    public void add(UserAccountOpenApply apply);
    
    public void delete(@Param("openApplyId")String openApplyId);
    
    public void update(UserAccountOpenApply apply);
    
    public UserAccountOpenApply findByApplyId(@Param("openApplyId") String openApplyId);
}

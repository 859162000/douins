/**
 * 
 */
package com.douins.account.dao;

import org.apache.ibatis.annotations.Param;

import com.douins.account.domain.model.UserAuthority;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: UserAuthorityDao 
* @Description: 用户权限的数据接口
* @author G. F. 
* @date 2015年11月18日 下午4:46:39 
*  
*/
@MyBatisMapper
public interface UserAuthorityDao{
    /**
     * 添加一条记录
     * @param authority
     */
    public void add(UserAuthority authority);
    
    /**
     * 查找用户权限配置
     * @param uid
     * @return
     */
    public UserAuthority find(@Param("uid")String uid);
    
    /**
     * 更新用户 token
     * @param authority
     */
    public void updateToken(UserAuthority authority);
    
    /**
     * 根据 token 查询记录
     * @param token
     * @return
     */
    public UserAuthority findUserByToken(@Param("token")String token);
}

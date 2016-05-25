/**
 * 
 */
package com.douins.account.dao;

import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.UserAuthority;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: UserAuthorityDao 
* @Description: 用户权限的数据接口
* @author G. F. 
* @date 2015年11月18日 下午4:46:39 
*  
*/
@Repository
public class UserAuthorityDao extends AbstractDao {
    /**
     * 添加一条记录
     * @param authority
     */
    public void add(UserAuthority authority){
        writeSqlSession.insert(sql(), authority);
    }
    
    /**
     * 查找用户权限配置
     * @param uid
     * @return
     */
    public UserAuthority find(String uid){
        return writeSqlSession.selectOne(sql(), uid);
    }
    
    /**
     * 更新用户 token
     * @param authority
     */
    public void updateToken(UserAuthority authority){
        writeSqlSession.update(sql(), authority);
    }
    
    /**
     * 根据 token 查询记录
     * @param token
     * @return
     */
    public UserAuthority findUserByToken(String token){
        return writeSqlSession.selectOne(sql(), token);
    }
}

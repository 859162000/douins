/**
 * 
 */
package com.douins.account.service.iml;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.douins.account.dao.UserAuthorityDao;
import com.douins.account.domain.enums.TokenVerifyEnum;
import com.douins.account.domain.model.UserAuthority;
import com.douins.common.util.AuthorityUtil;

/** 
* @ClassName: UserAuthorityService 
* @Description: 用户权限 service
* @author G. F. 
* @date 2015年11月18日 下午5:06:37 
*  
*/
@Service
public class UserAuthorityService {
    private static final Logger logger = Logger.getLogger(UserAuthorityService.class);
    
    @Inject
    private UserAuthorityDao authorityDao;
    
    /**
     * 添加记录
     * @param authority
     */
    public void add(UserAuthority authority){
        authorityDao.add(authority);
    }
    
    /**
     * 查找指定用户的权限配置
     * @param uid
     * @return
     */
    public UserAuthority findByUid(String uid){
        return authorityDao.find(uid);
    }
    
    /**
     * 更新用户 token
     * @param authority
     */
    public void updateToken(UserAuthority authority){
        authorityDao.updateToken(authority);
    }
    
    /**
     * 根据 token 查询权限记录
     * @param token
     * @return
     */
    public UserAuthority findUserByToken(String token){
        UserAuthority authority = authorityDao.findUserByToken(token);
        return authority;
    }
    
    /**
     * 验证用户 token
     * @param token
     * @return
     */
    public TokenVerifyEnum varifyUserByToken(String token){
        TokenVerifyEnum result = TokenVerifyEnum.Normal;
        UserAuthority authority = authorityDao.findUserByToken(token);
        if(authority != null){
            // 用户权限存在
            Date now = new Date();
            if(authority.getExpiredTime().before(now)){
                // token 已过期，需要重新登录
                result = TokenVerifyEnum.Expired;
            }
        }else {
            // toekn 不存在
            result = TokenVerifyEnum.NotExist;
        }
        
        logger.info(result.getContent());
        return result;
    }
    
    /**
     * 为用户设置本次登录期内的 token
     * @param uid
     */
    public String addLoginToken(String uid){
        UserAuthority authority = new UserAuthority();
        authority.setUid(uid);
        
        String token = AuthorityUtil.generateToken();
        authority.setToken(token);
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 3);     // token 有效期为 3 小时
        authority.setExpiredTime(calendar.getTime());
        
        authorityDao.add(authority);        // 入库
        
        return token;
    }
}

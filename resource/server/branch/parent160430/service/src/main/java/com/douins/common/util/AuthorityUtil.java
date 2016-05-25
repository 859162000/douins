/**
 * 
 */
package com.douins.common.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/** 
* @ClassName: AuthorityUtil 
* @Description: 权限相关的工具类
* @author G. F. 
* @date 2015年11月18日 下午3:22:23 
*  
*/
public class AuthorityUtil {
    
    /**
     * 生成 token
     * @return
     */
    public static String generateToken(){
        String token = UUID.randomUUID().toString();
        // 去掉短接线
        token = token.replaceAll("-", "");
        return token;
    }
    
    
    /**
     * 根据时间戳生成一组随机数字串
     * @param time
     * @return
     */
    private String generateRandomNumStr(Date time){
        Random random = new Random(time.getTime());
        long val = random.nextLong();
        return  String.valueOf(val);
    }
    
    /**
     * 根据当前时间生成一组随机数字串
     * @return
     */
    private String generateRandomNumStr(){
        Random random = new Random(new Date().getTime());
        long val = random.nextLong();
        return  String.valueOf(val);
    }
}

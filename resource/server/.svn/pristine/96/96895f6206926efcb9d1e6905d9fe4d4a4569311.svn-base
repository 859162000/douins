/**
 * 
 */
package com.douins.common.util;

import java.io.InputStream;

import org.apache.log4j.Logger;

/** 
* @ClassName: KeyFileUtils 
* @Description: 私钥／公钥加载工具类
* @author G. F. 
* @date 2016年1月20日 上午9:12:27 
*  
*/
public class KeyFileUtils {
    private static final Logger logger = Logger.getLogger(KeyFileUtils.class);
    
    public static String loadKey(String filePath){
        String key = null;
        try {
            InputStream inputStream = KeyFileUtils.class.getClassLoader().getResourceAsStream(filePath);
            StringBuilder strb = new StringBuilder();
            byte[] bytes = new byte[1024];
            int byteCount = 0;
            while ((byteCount = inputStream.read(bytes)) != -1){
                  strb.append(new String(bytes, 0, byteCount));
            }
            key = strb.toString();
        } catch (Exception e) {
            logger.error("KeyFileUtils: load key file error.", e);
        }
        
        return key;
    }
}

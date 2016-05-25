/**
 * 
 */
package com.douins.common.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/** 
* @ClassName: Configs 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月4日 下午5:11:11 
*  
*/
public class Configs {
    private static  Logger logger = Logger.getLogger(Configs.class);
    private static Properties config;
    
    static{
        config = init("constants.properties");
    }
    
    public Configs(){}
    public Configs(String filePath){
        config = init(filePath);
    }

    
    private static Properties init(String filePath){
        Properties properties = null;
        try {
            InputStream inputStream = Configs.class.getClassLoader().getResourceAsStream(filePath);
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("load constants error.",e);
        }
        
        return properties;
    }
    
    public static String get(String key){
        String value =  config.getProperty(key);
        if(value!=null)
            return value.trim();
        return value;
    }
}

/**
 * 
 */
package com.douins.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mango.video.VideoConvert;

/** 
* @ClassName: ConstantsConf 
* @Description: 常量配置, 从属性文件 constants.properties 加载
* @author G. F. 
* @date 2015年12月3日 上午11:02:38 
*  
*/
@Component("constantsConf")
public class ConstantsConf {
    
    public static String enviroment;
    public static String devServerIP;
    public static String qaServerIP;
    public static String onlineServerIP;
    
    @Value("${env}")
    private void setIsOnline(String enviroment){
        ConstantsConf.enviroment = enviroment;
    }
    
    @Value("${ip.dev}")
    private void setDevServerIP(String devIP){
        ConstantsConf.devServerIP = devIP;
    }
    
    @Value("${ip.qa}")
    private void setQaServerIP(String qaIP){
        ConstantsConf.qaServerIP = qaIP;
    }
    
    @Value("${ip.online}")
    private void setOnlineServerIP(String onlineIP){
        ConstantsConf.onlineServerIP = onlineIP;
    }
    
    
    
    
    
    // 测试用的字段
    public static String testReader;
    
    @Value("${test.reader}")
    public void setTestReader(String testReader) {
        ConstantsConf.testReader = testReader;
    }

}

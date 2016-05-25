/**
 * 
 */
package com.douins.common.msgpush.getui;

import org.apache.cxf.endpoint.PreexistingConduitSelector;
import org.apache.tools.ant.taskdefs.Get;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 
* @ClassName: getuiConfigs 
* @Description: 个推配置相关的参数
* @author G. F. 
* @date 2015年11月7日 下午4:03:15 
*  
*/
@Component("getuiConfigs")
public class GetuiConfigs {
    
    // 离线通知的保留时间 24 小时（毫秒级）
    public static final int offlineExpireTime = 86400000;
    // logo
    public static final String logo = "logo.png";
    public static final String logoUrl = "";
    
    public static String appId ="GvKQzfNemu6VUogaPXOMM2";
    public static String appKey ="oQzpg3Wplr9LDRudpBy8L3";
    public static String appSecret ="J3wHV1Oyqz9NgeaWC1Qgm7";
    public static String masterSecret ="gjHPhzGipJAQlIlsbKqOZ2";
    //收到消息是否立即启动应用：1为立即启动，2则广播等待客户端自启动
    public static int transmissionType=2;
    
//    public static String appId;
//    public static String appKey;
//    public static String appSecret;
//    public static String masterSecret;
//    public static int transmissionType;
//    
//    @Value("${getui.AppId}")
//    public static void setAppId(String appId) {
//        GetuiConfigs.appId = appId;
//    }
//    
//    @Value("${getui.AppKey}")
//    public static void setAppKey(String appKey) {
//        GetuiConfigs.appKey = appKey;
//    }
//    
//    @Value("${getui.AppSecret}")
//    public static void setAppSecret(String appSecret) {
//        GetuiConfigs.appSecret = appSecret;
//    }
//    
//    @Value("${getui.MasterSecret}")
//    public static void setMasterSecret(String masterSecret) {
//        GetuiConfigs.masterSecret = masterSecret;
//    }
//
//    @Value("${getui.TransmissionType}")
//    public static void setTransmissionType(int transmissionType) {
//        GetuiConfigs.transmissionType = transmissionType;
//    }
}

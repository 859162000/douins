/**
 * 
 */
package com.douins.common.msgpush.getui;

import org.apache.cxf.endpoint.PreexistingConduitSelector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 
* @ClassName: getuiConfigs 
* @Description: 个推配置相关的参数
* @author G. F. 
* @date 2015年11月7日 下午4:03:15 
*  
*/
@Component("getuiConfig")
public class GeTuiConfigs {

    public static String appId;
    public static String appKey;
    public static String appSecret;
    public static String masterSecret;
    public static int transmissionType;
    
    @Value("${getui.AppId}")
    public static void setAppId(String appId) {
        GeTuiConfigs.appId = appId;
    }
    
    @Value("${getui.AppKey}")
    public static void setAppKey(String appKey) {
        GeTuiConfigs.appKey = appKey;
    }
    
    @Value("${getui.AppSecret}")
    public static void setAppSecret(String appSecret) {
        GeTuiConfigs.appSecret = appSecret;
    }
    
    @Value("${getui.MasterSecret}")
    public static void setMasterSecret(String masterSecret) {
        GeTuiConfigs.masterSecret = masterSecret;
    }

    @Value("${getui.TransmissionType}")
    public static void setTransmissionType(int transmissionType) {
        GeTuiConfigs.transmissionType = transmissionType;
    }
}

/**
 * 
 */
package com.douins.common.msgpush.getui;

import org.springframework.stereotype.Component;

import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;

/** 
* @ClassName: GeXinPush 
* @Description: 个推－－消息推送
* @author G. F. 
* @date 2015年11月7日 下午7:05:33 
*  
*/
@Component
public class GeTuiPush {

    /**
     * 在通知栏显示一条含图标、标题等的通知，用户点击后激活您的应用
     * @param title     消息标题
     * @param content       消息内容
     * @param logo      消息的图标名，包括后缀名
     * @param logoUrl      图标的 url
     * @return
     */
    private NotificationTemplate InitNotificationTemplate(String title, String content, String logo, String logoUrl){
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(GeTuiConfigs.appId);
        template.setAppkey(GeTuiConfigs.appKey);
        template.setTransmissionType(GeTuiConfigs.transmissionType);
        
        template.setTitle(title);
        template.setText(content);
        template.setLogo(logo);
        template.setLogoUrl(logoUrl);
        
        return template;
    }
    
    public static void main(String[] args){
        IGtPush gtPush = new IGtPush(null, GeTuiConfigs.appKey, GeTuiConfigs.masterSecret);
    }
}

/**
 * 
 */
package com.douins.common.msgpush.getui;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.model.UserPrivateMessage;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.core.logger.SimpleLogger;

/** 
* @ClassName: GeXinPush 
* @Description: 个推－－消息推送
* @author G. F. 
* @date 2015年11月7日 下午7:05:33 
*  
*/
@Component
public class GetuiPush {
    
    private SimpleLogger logger = SimpleLogger.getLogger(GetuiPush.class);
    
    /**
     * 集体推送，私有消息
     * @param cidList
     * @param message
     * @return
     */
    public IPushResult push2ListPrivMsg(List<String> cidList, UserPrivateMessage message){
        IPushResult pushResult = null;
        try {
            Message  msg = new Message();
            BeanUtils.copyProperties(msg, message);
            pushResult = push2List(cidList, msg, "message");
        } catch (IllegalAccessException e) {
            logger.error("逻辑错误");
        } catch (InvocationTargetException e) {
           logger.error("数据拷贝错误");
        }
        
        return pushResult;
    }
    
    /**
     * 集体推送，公告消息
     * @param cidList
     * @param message
     * @return
     */
    public IPushResult push2ListPubMsg(List<String> cidList, PublicMessage message){
        IPushResult pushResult = null;
        try {
            Message  msg = new Message();
            BeanUtils.copyProperties(msg, message);
            pushResult = push2List(cidList, msg, "message");
        } catch (IllegalAccessException e) {
            logger.error("逻辑错误");
        } catch (InvocationTargetException e) {
           logger.error("数据拷贝错误");
        }
        
        return pushResult;
    }
    

    /**
     * 批量推送
     * @param cidList
     * @param title
     * @param content
     * @param msgType 消息类型：notice ／ message
     * @param msgId     消息ID号
     * @param msgTarget      消息体类型：“text” ／“url”
     * @return
     */
    private IPushResult push2List(List<String> cidList, Message msg, String msgType){
        ITemplate template = InitTransmissionTemplate(msg, msgType);
        // 消息
        ListMessage message = new ListMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(GetuiConfigs.offlineExpireTime);
        message.setPushNetWorkType(0);      // 不区分 wifi 和其他网络，统一推送
        
        IGtPush push = InitPush();
        String taskId = push.getContentId(message);
        
        // 配置推送目标
        List<Target> targets = Lists.newArrayList();
        for(int i = 0; i < cidList.size(); i++){
                Target target = new Target();
                target.setAppId(GetuiConfigs.appId);
                target.setClientId(cidList.get(i));
                
                targets.add(target);
        }

        logger.debug("开始推送 ");
        // 推送
        IPushResult pushResult = push.pushMessageToList(taskId, targets);
        if (pushResult != null) {
            logger.info(pushResult.getResponse().toString());
        }else {
            logger.error("服务器异常，发送失败。");
        }

        return pushResult;
    }
    
    /**
     * 单体推送
     * @param cid   client id
     * @param title 标题
     * @param content   通知的内容
     * @return
     */
    public IPushResult push2Single(String cid, Message msg, String msgType){
        // 消息模板
        TransmissionTemplate template = InitTransmissionTemplate(msg, msgType);
        //NotificationTemplate template = InitNotificationTemplate(title, content, "logo", "url");
        
        SingleMessage message = new SingleMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(GetuiConfigs.offlineExpireTime);
        message.setPushNetWorkType(0);      // 不区分 wifi 和其他网络，统一推送
        
        // 目标
        Target target = new Target();
        target.setAppId(GetuiConfigs.appId);
        target.setClientId(cid);
        
        // 推送
        IGtPush push = InitPush();
        IPushResult pushResult = null;
        try {
            pushResult = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            logger.error(e.toString());
            logger.info("重发一次");
           pushResult =  push.pushMessageToSingle(message, target, e.getRequestId());
        }
        
        if(pushResult != null){
            logger.info(pushResult.getResponse().toString());
        }else{
            logger.error("服务器异常，推送失败");
        }
        
        return pushResult;
    }//! 单体推送
    
    private IGtPush InitPush(){
        IGtPush push = new IGtPush(GetuiConfigs.appKey, GetuiConfigs.masterSecret);
        return push;
    }
    
    /**
     * 在通知栏显示一条含图标、标题等的通知，用户点击后激活您的应用
     * @param title     消息标题
     * @param content       消息内容
     * @return
     */
    private NotificationTemplate InitNotificationTemplate(Message msg, String msgType){
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(GetuiConfigs.appId);
        template.setAppkey(GetuiConfigs.appKey);
        template.setTransmissionType(GetuiConfigs.transmissionType);
        
        template.setTitle(msg.getTitle());
        template.setText(msg.getContent());
        template.setLogo(GetuiConfigs.logo);
        template.setLogoUrl(GetuiConfigs.logoUrl);
        
        APNPayload payload = getPayload(msg, msgType);
        template.setAPNInfo(payload);
        
        return template;
    }
    
    /**
     * 透传消息模板
     * @param content
     * @return
     */
    private TransmissionTemplate InitTransmissionTemplate(Message msg, String msgType){
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(GetuiConfigs.appId);
        template.setAppkey(GetuiConfigs.appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(GetuiConfigs.transmissionType);
        // 2b
        String msg2 = getJsonDatas(msg, msgType);
        template.setTransmissionContent(msg2);
        
        APNPayload payload = getPayload(msg, msgType);
        template.setAPNInfo(payload);
        
        return template;
        
    }
    
   /**
    * IOS 透传消息的设置
    * @param content
    * @param msgType    消息类型
    * @param msgId      消息 ID
    * @param target     消息体内容类型
    * @return
    */
    private APNPayload getPayload(Message msg, String msgType){
     // IOS 使用的透传内容
        APNPayload payload = new APNPayload();
        payload.setBadge(1);        // 应用 icon 上显示的数字
        payload.setSound("default");
        payload.setContentAvailable(1);     // 推送直接带有透传消息
        payload.setCategory("@客户端自己定义");    // 在客户端通知栏触发特定的action和button显示
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(msg.getContent()));
        
        // 附加消息
//        Map<String, Object> userParams = Maps.newHashMap();
//        userParams.put("type", msgType);
//        userParams.put("id", msgId);
//        userParams.put("target", target);
//        String msg = JSON.toJSONString(userParams);
//        payload.addCustomMsg("commonInfo", msg);
        
        payload.addCustomMsg("type", msgType);
        payload.addCustomMsg("id", msg.getMsgId());
        payload.addCustomMsg("target", msg.getMsgTarget());
        payload.addCustomMsg("title", msg.getTitle());
        payload.addCustomMsg("time", msg.getCreateTime());
        payload.addCustomMsg("url", msg.getUrl());
        
        return payload;
    }
    
    
    public String getJsonDatas(Message msg, String msgType){
        // 附加消息
        Map<String, Object> userParams = Maps.newHashMap();
        userParams.put("type", msgType);
        userParams.put("id", msg.getMsgId());
        userParams.put("target", msg.getMsgTarget());
        userParams.put("title", msg.getTitle());
        userParams.put("url", msg.getUrl());
        userParams.put("time", msg.getCreateTime());

        userParams.put("alert", msg.getContent());
        userParams.put("content-available", 1);
        userParams.put("category", "@客户端自己定义");
        String msg2 = JSON.toJSONString(userParams);

        return msg2;
    }
}

/**
 * 
 */
package com.douins.common.msgpush.getui;

import java.util.Date;

/** 
* @ClassName: Meaasge 
* @Description: 推送的消息体
* @author G. F. 
* @date 2015年11月24日 下午4:53:25 
*  
*/
public class Message {
    private long msgId;
    private String title;
    private String content;
    private String url;
    private String msgTarget;
    private Date createTime;
    
    
    public long getMsgId() {
        return msgId;
    }
    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMsgTarget() {
        return msgTarget;
    }
    public void setMsgTarget(String msgTarget) {
        this.msgTarget = msgTarget;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   
}

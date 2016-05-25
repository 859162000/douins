/**
 * 
 */
package com.douins.account.domain.model;

import com.douins.common.domain.BaseModel;

/** 
* @ClassName: UserPrivateMessage 
* @Description: 用户私有消息
* @author G. F. 
* @date 2015年11月20日 下午4:36:07 
*  
*/
public class UserPrivateMessage extends BaseModel {
    private long msgId;
    private String uid;             // 用户ID
    private String title;           // 公告标题
    private String content;     // 公告内容
    private String url;             // 消息映射的 url
    private int isRead;             // 是否已读的标识，0-- 未读；1 -- 已读
    private int deleted;            // 是否已删除的标识，0-- 未删除；1-- 已删除
    private String target;         // url 或 text

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
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
    public long getMsgId() {
        return msgId;
    }
    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getIsRead() {
        return isRead;
    }
    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
    public int getDeleted() {
        return deleted;
    }
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
	@Override
	public String toString() {
		return "UserPrivateMessage [msgId=" + msgId + ", uid=" + uid
				+ ", title=" + title + ", content=" + content + ", url=" + url
				+ ", isRead=" + isRead + ", deleted=" + deleted + ", target="
				+ target + "]";
	}
    
    
}

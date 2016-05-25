/**
 * 
 */
package com.douins.account.domain.model;

import com.douins.common.domain.BaseModel;

/** 
* @ClassName: PublicMessage 
* @Description: 公有消息
* @author G. F. 
* @date 2015年11月20日 下午5:47:59 
*  
*/
public class PublicMessage extends BaseModel {
    
    private long msgId;
    private String title;           // 消息标题
    private String content;     // 消息内容
    private String url;             // 消息映射的 url
    private String target;         // url 或 text
    
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
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
	@Override
	public String toString() {
		return "PublicMessage [msgId=" + msgId + ", title=" + title
				+ ", content=" + content + ", url=" + url + ", target="
				+ target + "]";
	}
    
}

/**
 * 
 */
package com.douins.statics.domain.model;

import com.douins.common.domain.BaseModel;

/** 
* @ClassName: Images 
* @Description: 图像文件
* @author G. F. 
* @date 2015年11月10日 下午3:45:14 
*  
*/
public class Image extends BaseModel {

    private int id;
    private String name;
    private String url;
    private String targetUrl;
    private int expired;
    
   
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getExpired() {
        return expired;
    }
    public void setExpired(int expired) {
        this.expired = expired;
    }
    
    
    @Override
   	public String toString() {
   		return "Image [id=" + id + ", name=" + name + ", url=" + url + ", targetUrl=" + targetUrl + ", expired="
   				+ expired + "]";
   	}
}

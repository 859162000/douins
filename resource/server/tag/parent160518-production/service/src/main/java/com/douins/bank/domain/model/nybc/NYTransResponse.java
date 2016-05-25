/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: NYTransResponse 
* @Description: nybc 返回数据的通用部分
* @author G. F. 
* @date 2015年12月8日 上午11:19:03 
*  
*/
public abstract class NYTransResponse {
    @XStreamAlias("SYS_HEAD")
    private SystermHead systermHead;
    @XStreamAlias("APP_HEAD")
    private AppHead appHead;
    
    public SystermHead getSystermHead() {
        return systermHead;
    }
    public void setSystermHead(SystermHead systermHead) {
        this.systermHead = systermHead;
    }
    public AppHead getAppHead() {
        return appHead;
    }
    public void setAppHead(AppHead appHead) {
        this.appHead = appHead;
    }
    
    public abstract <T> void setBody(T body);
}

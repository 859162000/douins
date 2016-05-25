/**
 * 
 */
package com.douins.agency.service;

import java.util.Date;

/** 
* @ClassName: BaseModel 
* @Description: 数据模型的基类，主要是创建时间和更新时间
* @author G. F. 
* @date 2015年12月28日 上午9:11:08 
*  
*/
public class BaseModel {
    /**
     * 数据在表中的 ID 号
     */
    private long id;
    /**
     * 该条记录的创建时间
     */
    private Date createTime;
    /**
     * 该条记录的更新时间
     */
    private Date updateTime;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

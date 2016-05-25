/**
 * 
 */
package com.douins.common.domain;

import java.util.Date;

/** 
* @ClassName: BaseModel 
* @Description: 实体的基类
* @author G. F. 
* @date 2015年11月10日 下午4:03:41 
*  
*/
public  class BaseModel {
    protected Date createTime;
    protected Date updateTime;
    
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

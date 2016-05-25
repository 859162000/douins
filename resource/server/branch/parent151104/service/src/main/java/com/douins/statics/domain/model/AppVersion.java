/**
 * 
 */
package com.douins.statics.domain.model;

import com.douins.common.domain.BaseModel;

/** 
* @ClassName: AppVersion 
* @Description: APP 版本号
* @author G. F. 
* @date 2016年2月1日 上午10:25:15 
*  
*/
public class AppVersion extends BaseModel {
    private String version;         // 版本号: xx.xx.xx
    private long value;              // 版本号的 value
    
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public long getValue() {
        return value;
    }
    public void setValue(long value) {
        this.value = value;
    }
}

/**
 * 
 */
package com.douins.statics.dao;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.statics.domain.model.AppVersion;

/** 
* @ClassName: AppVersionDao 
* @Description: App 版本号
* @author G. F. 
* @date 2016年2月1日 上午10:34:33 
*  
*/
@MyBatisMapper
public interface AppVersionDao {
    
    /**
     * 添加一条版本信息
     * @param version
     */
    public void add(AppVersion version);
    
    /**
     * 获取最新版本信息
     * @return
     */
    public AppVersion getLastVersion();
}

/**
 * 
 */
package com.douins.statics.dao;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.statics.domain.model.AppVersion;

/** 
* @ClassName: AppVersionDao 
* @Description: App 版本号
* @author G. F. 
* @date 2016年2月1日 上午10:34:33 
*  
*/
@Repository
public class AppVersionDao extends AbstractDao {
    
    /**
     * 添加一条版本信息
     * @param version
     */
    public void add(AppVersion version){
        writeSqlSession.insert(sql(), version);
    }
    
    /**
     * 获取最新版本信息
     * @return
     */
    public AppVersion getLastVersion(){
        return writeSqlSession.selectOne(sql(), null);
    }
}

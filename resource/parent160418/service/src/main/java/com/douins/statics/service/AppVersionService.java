/**
 * 
 */
package com.douins.statics.service;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.douins.statics.dao.AppVersionDao;
import com.douins.statics.domain.model.AppVersion;

/** 
* @ClassName: AppVersionService 
* @Description: App 版本
* @author G. F. 
* @date 2016年2月1日 上午11:08:02 
*  
*/
@Service
public class AppVersionService {
    
    @Resource
    private AppVersionDao versionDao;
    
    /**
     * 判断是否有新版本存在
     *      0: 没有新版本
     *      1: 有新版本可以更新
     * @param clientVersion
     * @return
     */
    public String newVersionExist(String clientVersion){
        String version = null;
        if(clientVersion.contains(".")){
            version = clientVersion.replace(".", "");
        }else {
            version = clientVersion;
        }
        
        long val = Long.valueOf(version).longValue();
        String result = "0";
        // 当前最新版本
        AppVersion lastVersion = versionDao.getLastVersion();
        if(lastVersion.getValue() > val){
            result = "1";
        }
        
        return result;
    }
}

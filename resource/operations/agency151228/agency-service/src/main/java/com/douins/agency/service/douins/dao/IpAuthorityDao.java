/**
 * 
 */
package com.douins.agency.service.douins.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.IpAuthority;

/** 
* @ClassName: IpAuthorityDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月13日 上午9:00:46 
*  
*/
@Repository
public class IpAuthorityDao extends AbstractDao {
    /**
     * 保存一条 IP 记录
     * @param ip
     */
    public void insert(IpAuthority ip){
        writeSqlSession.insert(sql(), ip);
    }
    
    /**
     * 提取所有的 IP 地址
     * @return
     */
    public List<String> getIpList(){
        return list(writeSqlSession, sql(), null);
    }
}

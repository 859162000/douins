/**
 * 
 */
package com.douins.partner.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;

/** 
* @ClassName: InsuranceInterfaceConfigDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:47:51 
*  
*/
@Repository
public class InsuranceInterfaceConfigDao extends AbstractDao {

    public void insert(InsuranceInterfaceConfig config){
        writeSqlSession.insert(sql(), config);
    }
    
    public void updateByPrimaryKey(String configId){
        writeSqlSession.update(sql(), configId);
    }
    
    public InsuranceInterfaceConfig selectByPrimaryKey(String configId){
        return writeSqlSession.selectOne(sql(), configId);
    }
    
    public List<InsuranceInterfaceConfig> selectAll(){
        return list(writeSqlSession, sql(), null);
    }
}

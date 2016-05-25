/**
 * 
 */
package com.douins.partner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;

/** 
* @ClassName: InsuranceInterfaceConfigDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:47:51 
*  
*/
@MyBatisMapper
public interface InsuranceInterfaceConfigDao{

    public void insert(InsuranceInterfaceConfig config);
    
    public void updateByPrimaryKey(@Param("configId")String configId);
    
    public InsuranceInterfaceConfig selectByPrimaryKey(@Param("configId")String configId);
    
    public List<InsuranceInterfaceConfig> selectAll();
}

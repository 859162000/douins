/**
 * 
 */
package com.douins.partner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.partner.domain.model.Insurance;

/** 
* @ClassName: InsuranceDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:34:20 
*  
*/
@MyBatisMapper
public interface InsuranceDao{

    public List<Insurance> getList(Insurance insurance);
    public int getList_Count(Insurance insurance);
    
    public void delete(@Param("insuranceId")String insuranceId);
    
    public void insert(Insurance insurance);
    
    public void updateByPrimaryKey(Insurance insurance);
    
    public Insurance selectByPrimaryKey(@Param("insuranceId")String insuranceId);
}

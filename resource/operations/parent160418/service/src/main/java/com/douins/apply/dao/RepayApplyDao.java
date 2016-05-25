/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.apply.domain.model.RepayApply;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: RepayApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 上午11:30:30 
*  
*/
@MyBatisMapper
public interface RepayApplyDao{
    
    public List<RepayApply> getList(RepayApply apply);
    
    public int getList_Count(RepayApply apply);
    
    public void delete(@Param("repayApplyId")String repayApplyId);
    
    public void add(RepayApply apply);
    
    public void update(RepayApply apply);
    
    public RepayApply findByApplyId(@Param("repayApplyId")String repayApplyId);
    
    public void updateAfter(RepayApply repayApply);
}

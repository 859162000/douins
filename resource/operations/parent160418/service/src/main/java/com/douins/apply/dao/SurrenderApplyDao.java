/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.stereotype.Repository;

import com.douins.apply.domain.model.SurrenderApply;
import com.douins.common.dao.AbstractDao;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: SurrenderApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 上午11:31:01 
*  
*/
@MyBatisMapper
public interface SurrenderApplyDao{

    public List<SurrenderApply> getList(SurrenderApply apply);
    
    public int getList_Count(SurrenderApply apply);
    
    public void add(SurrenderApply apply);
    
    public void delete(@Param("applyId")String applyId);
    
    public void update(@Param("applyId")String applyId);
    
    public SurrenderApply findByApplyId(@Param("surrendApplyId")String surrendApplyId);
    
    public void updateAfter(SurrenderApply surrendApply);
}

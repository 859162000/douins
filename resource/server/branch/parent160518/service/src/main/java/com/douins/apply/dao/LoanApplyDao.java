/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.douins.apply.domain.model.LoanApply;
import com.douins.apply.domain.vo.LoanApplyVo;
import com.douins.common.dao.AbstractDao;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: LoanApplyInfoDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 上午11:21:38 
*  
*/
@MyBatisMapper
public interface LoanApplyDao{
    
    public List<LoanApply> getList(LoanApply apply);
    
    public int getList_Count(LoanApply apply);
    public void insert(LoanApply apply);
    
    public void delete(@Param("applyId")  String applyId);
    public void update(LoanApply apply);
    
    public LoanApply selectByApplyId(@Param("loanApplyId")String loanApplyId);
    
    public void updateAfter(LoanApply apply);
    
    public List<LoanApplyVo> getVoList(LoanApply apply);
}

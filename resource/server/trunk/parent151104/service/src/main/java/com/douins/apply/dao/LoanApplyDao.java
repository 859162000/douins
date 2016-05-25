/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.apply.domain.model.LoanApply;
import com.douins.apply.domain.vo.LoanApplyVo;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: LoanApplyInfoDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 上午11:21:38 
*  
*/
@Repository
public class LoanApplyDao extends AbstractDao {
    
    public List<LoanApply> getList(LoanApply apply){
        return list(writeSqlSession, sql(), apply);
    }
    
    public int getList_Count(LoanApply apply){
        return size(writeSqlSession, sql(), apply);
    }
    
    public void insert(LoanApply apply){
        writeSqlSession.insert(sql(), apply);
    }
    
    public void delete(String applyId){
        writeSqlSession.update(sql(), applyId);
    }
    
    public void update(LoanApply apply){
        writeSqlSession.update(sql(), apply);
    }
    
    public LoanApply selectByApplyId(String loanApplyId){
        return writeSqlSession.selectOne(sql(), loanApplyId);
    }
    
    public void updateAfter(LoanApply apply){
        writeSqlSession.update(sql(), apply);
    }
    
    public List<LoanApplyVo> getVoList(LoanApply apply){
        return list(writeSqlSession, sql(), apply);
    }
}

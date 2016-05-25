/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;

import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.stereotype.Repository;

import com.douins.apply.domain.model.RepayApply;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: RepayApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 上午11:30:30 
*  
*/
@Repository
public class RepayApplyDao extends AbstractDao {
    
    public List<RepayApply> getList(RepayApply apply){
        return list(writeSqlSession, sql(), apply);
    }
    
    public int getList_Count(RepayApply apply){
        return size(writeSqlSession, sql(), apply);
    }
    
    public void delete(String repayApplyId){
        writeSqlSession.update(sql(), repayApplyId);
    }
    
    public void add(RepayApply apply){
        writeSqlSession.insert(sql(), apply);
    }
    
    public void update(RepayApply apply){
        writeSqlSession.update(sql(), apply);
    }
    
    public RepayApply findByApplyId(String repayApplyId){
        return writeSqlSession.selectOne(sql(), repayApplyId);
    }
    
    public void updateAfter(RepayApply repayApply){
        writeSqlSession.update(sql(),  repayApply);
    }
}

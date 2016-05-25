/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;

import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.stereotype.Repository;

import com.douins.apply.domain.model.SurrenderApply;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: SurrenderApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 上午11:31:01 
*  
*/
@Repository
public class SurrenderApplyDao extends AbstractDao {

    public List<SurrenderApply> getList(SurrenderApply apply){
        return list(writeSqlSession, sql(), apply);
    }
    
    public int getList_Count(SurrenderApply apply){
        return size(writeSqlSession, sql(), apply);
    }
    
    public void add(SurrenderApply apply){
        writeSqlSession.insert(sql(), apply);
    }
    
    public void delete(String applyId){
        writeSqlSession.update(sql(), applyId);
    }
    
    public void update(String applyId){
        writeSqlSession.update(sql(), applyId);
    }
    
    public SurrenderApply findByApplyId(String surrendApplyId){
        return writeSqlSession.selectOne(sql(), surrendApplyId);
    }
    
    public void updateAfter(SurrenderApply surrendApply){
        writeSqlSession.update(sql(), surrendApply);
    }
}

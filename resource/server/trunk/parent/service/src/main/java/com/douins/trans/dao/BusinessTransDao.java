/**
 * 
 */
package com.douins.trans.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.trans.domain.model.BusinessTrans;

/** 
* @ClassName: BusinessTransDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午3:28:20 
*  
*/
@Repository
public class BusinessTransDao extends AbstractDao {

    public List<BusinessTrans> getList(BusinessTrans trans){
        return list(writeSqlSession, sql(), trans);
    }
    
    public int getList_Count(BusinessTrans tans){
        return size(writeSqlSession, sql(), tans);
    }
    
    public void softDeleteByPrimaryKey(String transId){
        writeSqlSession.update(sql(), transId);
    }
    
    public void insert(BusinessTrans trans){
        writeSqlSession.insert(sql(), trans);
    }
    
    public void updateByPrimaryKey(BusinessTrans trans){
        writeSqlSession.update(sql(), trans);
    }
    
    public BusinessTrans selectByPrimaryKey(String tansId){
        return writeSqlSession.selectOne(sql(), tansId);
    }
}

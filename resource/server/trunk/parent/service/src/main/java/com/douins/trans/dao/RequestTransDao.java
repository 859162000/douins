/**
 * 
 */
package com.douins.trans.dao;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.trans.domain.model.RequestTrans;

/** 
* @ClassName: RequestTransDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午4:04:55 
*  
*/
@Repository
public class RequestTransDao extends AbstractDao {

    public RequestTrans selectByPrimaryKey(String requestTransId){
        return writeSqlSession.selectOne(sql(), requestTransId);
    }
    
    public void insert(RequestTrans trans){
        writeSqlSession.insert(sql(), trans);
    }
    
    public void updateResponseInfo(RequestTrans trans){
        writeSqlSession.update(sql(), trans);
    }
}

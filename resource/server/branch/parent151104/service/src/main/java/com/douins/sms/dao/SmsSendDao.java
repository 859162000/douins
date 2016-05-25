/**
 * 
 */
package com.douins.sms.dao;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.sms.domain.model.SmsSend;

/** 
* @ClassName: SmsSendDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午3:18:46 
*  
*/
@Repository
public class SmsSendDao extends AbstractDao {

    public SmsSend selectByPrimaryKey(String smsId){
        return writeSqlSession.selectOne(sql(), smsId);
    }
    
    public void insert(SmsSend sms){
        writeSqlSession.insert(sql(), sms);
    }
    
    public void updateForStatus(SmsSend sms){
        writeSqlSession.update(sql(), sms);
    }
    
    public void softDeleteByPrimaryKey(String smsId){
        writeSqlSession.update(sql(), smsId);
    }
    
    public SmsSend findSmsLast(SmsSend sms){
        return writeSqlSession.selectOne(sql(), sms);
    }
}

/**
 * 
 */
package com.douins.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.pay.domain.model.PaymentApply;

/** 
* @ClassName: PaymentApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:54:36 
*  
*/
@Repository
public class PaymentApplyDao extends AbstractDao {

    public List<PaymentApply> getList(PaymentApply apply){
        return list(writeSqlSession, sql(), apply);
    }
    
    public int getList_Count(PaymentApply apply){
        return size(writeSqlSession, sql(), apply);
    }
    
    public void softDeleteByPrimaryKey(String paymentApplyId){
        writeSqlSession.update(sql(), paymentApplyId);
    }
    
    public void insert(PaymentApply apply){
        writeSqlSession.insert(sql(), apply);
    }
    
    public void updateByPrimaryKey(PaymentApply apply){
        writeSqlSession.insert(sql(), apply);
    }
    
    public PaymentApply selectByPrimaryKey(String paymentApplyId){
        return writeSqlSession.selectOne(sql(), paymentApplyId);
    }
}

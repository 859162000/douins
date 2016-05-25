/**
 * 
 */
package com.douins.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.pay.domain.model.PaymentApply;

/** 
* @ClassName: PaymentApplyDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:54:36 
*  
*/
@MyBatisMapper
public interface PaymentApplyDao {

    public List<PaymentApply> getList(PaymentApply apply);
    
    public int getList_Count(PaymentApply apply);
    
    public void softDeleteByPrimaryKey(@Param("paymentApplyId")String paymentApplyId);
    
    public void insert(PaymentApply apply);
    
    public void updateByPrimaryKey(PaymentApply apply);
    
    public PaymentApply selectByPrimaryKey(@Param("paymentApplyId")String paymentApplyId);
}

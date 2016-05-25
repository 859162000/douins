/**
 * 
 */
package com.douins.policy.dao;

import java.util.List;

import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.policy.domain.model.Customer;

/** 
* @ClassName: CustomerDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午12:05:06 
*  
*/
@Repository
public class CustomerDao extends AbstractDao {

    public List<Customer> getList(Customer customer){
        return list(writeSqlSession, sql(), customer);
    }
    
    public int getList_Count(Customer customer){
        return size(writeSqlSession, sql(), customer);
    }
    
    public void softDeleteByPrimaryKey(String customerId){
        writeSqlSession.update(sql(), customerId);
    }
    
    public void insert(Customer customer){
        writeSqlSession.insert(sql(), customer);
    }
    
    public void updateByPrimaryKey(Customer customer){
        writeSqlSession.update(sql(), customer);
    }
    
    public Customer selectByPrimaryKey(String customerId){
        return writeSqlSession.selectOne(sql(), customerId);
    }
}

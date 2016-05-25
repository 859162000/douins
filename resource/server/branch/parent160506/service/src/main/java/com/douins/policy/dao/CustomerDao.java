/**
 * 
 */
package com.douins.policy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.policy.domain.model.Customer;

/**
 * @ClassName: CustomerDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年10月21日 下午12:05:06
 * 
 */
@MyBatisMapper
public interface CustomerDao {

	public List<Customer> getList(Customer customer);

	public int getList_Count(Customer customer);

	public void softDeleteByPrimaryKey(@Param("customerId") String customerId);

	public void insert(Customer customer);

	public void updateByPrimaryKey(Customer customer);

	public Customer selectByPrimaryKey(@Param("customerId") String customerId);
}

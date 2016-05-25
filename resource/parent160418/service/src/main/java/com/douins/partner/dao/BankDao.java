/**
 * 
 */
package com.douins.partner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.partner.domain.model.Bank;

/**
 * @ClassName: BankDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年10月21日 上午11:26:15
 * 
 */
@MyBatisMapper
public interface BankDao {

	public List<Bank> getList(Bank bank);

	public int getList_Count(Bank bank);

	public void add(Bank bank);

	public void delete(@Param("bankId") String bankId);

	public void update(Bank bank);

	public Bank findByBankId(@Param("bankId") String bankId);
}

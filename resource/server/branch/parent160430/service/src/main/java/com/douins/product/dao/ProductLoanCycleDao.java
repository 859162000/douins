/**
 * 
 */
package com.douins.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.product.domain.model.ProductLoanCycle;

/**
 * @ClassName: ProductLoanCycleDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年10月21日 下午2:35:52
 * 
 */
@MyBatisMapper
public interface ProductLoanCycleDao {

	public void deleteByPrimaryKey(@Param("loanCycleId")String loanCycleId);

	public void insert(ProductLoanCycle loanCycle);

	public void updateByPrimaryKey(ProductLoanCycle loanCycle);

	public ProductLoanCycle selectByPrimaryKey(@Param("loanCycleId")String loanCycleId);

	public List<ProductLoanCycle> getList(ProductLoanCycle loanCycle);
}

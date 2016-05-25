/**
 * 
 */
package com.douins.trans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.trans.domain.model.BusinessTrans;

/**
 * @ClassName: BusinessTransDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年10月21日 下午3:28:20
 * 
 */
@MyBatisMapper
public interface BusinessTransDao{

	public List<BusinessTrans> getList(BusinessTrans trans);

	public int getList_Count(BusinessTrans tans);

	public void softDeleteByPrimaryKey(@Param("transId")String transId);

	public void insert(BusinessTrans trans);

	public void updateByPrimaryKey(BusinessTrans trans);

	public BusinessTrans selectByPrimaryKey(@Param("transId")String tansId);

	public BusinessTrans findByTransNo(@Param("transNo")String transNo);
}

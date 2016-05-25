/**
 * 
 */
package com.douins.policy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.policy.domain.model.PolicyData;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponseVo;

/**
 * @ClassName: PolicyMasterDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年10月21日 下午12:12:14
 * 
 */
@MyBatisMapper
public interface PolicyMasterDao {

	public List<PolicyMaster> getList(PolicyMaster policy);

	public int getList_Count(PolicyMaster policy);

	public void softDeleteByPrimaryKey(@Param("policyId")String policyId);

	public void insert(PolicyMaster policy);

	public void updateByPrimaryKey(PolicyMaster policy);

	public PolicyMaster selectByPrimaryKey(@Param("policyId")String policyId);

	public PolicyResponseVo getByPrimaryKey(@Param("policyId")String policyId);

	public List<PolicyResponseVo> getVoList(PolicyRequestVo vo);

	public List<PolicyResponseVo> getVoListByUid(PolicyRequestVo vo);

	public List<PolicyResponseVo> getSuccessPolicyVoList(PolicyRequestVo vo);

	public PolicyMaster findByChanlFlowNo(@Param("chanlFlowNo")String chanlFlowNo);
	
	public int updateByAgencyData(PolicyData policyDatas);

	public void updateStatus(@Param("policyId")String policyId,@Param("status")String status);

}

/**
 * 
 */
package com.douins.policy.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
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
@Repository
public class PolicyMasterDao extends AbstractDao {

    public List<PolicyMaster> getList(PolicyMaster policy){
        return list(writeSqlSession, sql(), policy);
    }
    
    public int getList_Count(PolicyMaster policy){
        return size(writeSqlSession, sql(), policy);
    }
    
    public void softDeleteByPrimaryKey(String policyId){
        writeSqlSession.update(sql(), policyId);
    }
    
    public void insert(PolicyMaster policy){
        writeSqlSession.insert(sql(), policy);
    }
    
    public void updateByPrimaryKey(PolicyMaster policy){
        writeSqlSession.update(sql(), policy);
    }
    
    public PolicyMaster selectByPrimaryKey(String policyId){
        return writeSqlSession.selectOne(sql(), policyId);
    }
    //TODO
    public PolicyResponseVo getByPrimaryKey(String policyId){
        return writeSqlSession.selectOne(sql(), policyId);
    }
    
    public List<PolicyResponseVo> getVoList(PolicyRequestVo vo){
        return list(writeSqlSession,sql(), vo);
    }
    
    public List<PolicyResponseVo> getVoListByUid(PolicyRequestVo vo){ 
        return list(writeSqlSession,sql(), vo);
    }
    
    public List<PolicyResponseVo> getSuccessPolicyVoList(PolicyRequestVo vo){
        return list(writeSqlSession,sql(), vo);
    }

	public PolicyMaster findByChanlFlowNo(String chanlFlowNo) {
		  return writeSqlSession.selectOne(sql(), chanlFlowNo);
		
	}

	
}

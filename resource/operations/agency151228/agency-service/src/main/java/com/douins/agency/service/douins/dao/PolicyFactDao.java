package com.douins.agency.service.douins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.PolicyFact;
import com.google.common.collect.Maps;

@Repository
public class PolicyFactDao extends AbstractDao{
    
	public void insert(List<PolicyFact> policyFacts){
		writeSqlSession.insert(sql(), policyFacts);
	}
	
	public List<PolicyFact> getPolicyInfoByPolicyNo(String policyNo){
		return list(writeSqlSession,sql(),policyNo);
	}

	/**
	 * 根据保单号查询指定渠道和保险公司的保单
	 * @param channelNo
	 * @param insureNo
	 * @param policyNos
	 * @return
	 */
	public List<PolicyFact> findPolicysByPolicyNo(String channelNo, String insureNo, List<String> policyNos){
	    Map<String, Object> params = Maps.newHashMap();
	    params.put("channelNo", channelNo);
	    params.put("insureNo", insureNo);
	    params.put("list", policyNos);
	    
	    return list(writeSqlSession, sql(), params);
	}

	/**
	 * 批量更新保单状态
	 * @param policys
	 */
	public void updateStatus(List<PolicyFact> policys, String status){
	    Map<String, Object> params = Maps.newHashMap();
	    params.put("status", status);
	    params.put("list", policys);
	    writeSqlSession.update(sql(), params);
	}
	
	/**
	 * 批量跟新指定保单号的保单状态
	 * @param policyNos
	 * @param status
	 */
	public void updateStatus2(List<String> policyNos, String status){
	    Map<String, Object> params = Maps.newHashMap();
        params.put("status", status);
        params.put("list", policyNos);
        writeSqlSession.update(sql(), params);
	}
}

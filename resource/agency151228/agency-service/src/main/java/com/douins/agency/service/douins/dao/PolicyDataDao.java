package com.douins.agency.service.douins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.channel.qunarfinance.domain.model.Response;
import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.PolicyData;
import com.douins.agency.service.douins.domain.model.PolicyReservationResponse;

@Repository
public class PolicyDataDao extends AbstractDao {

	public List<PolicyData> queryPolicys(Map<String, Object> params) {
		return list(writeSqlSession, sql(), params);
	}

	// public QunarFNQueryWDVo queryWDInfo(String policyNo){
	// //return list(writeSqlSession,sql(),policyNo);
	// return writeSqlSession.selectOne(policyNo);
	// }
	
	public List<String> queryResp(Map<String, Object> params){
		return  list(writeSqlSession, sql(), params);
	}
	public List<PolicyData> queryWDInfo(String policyNo) {
		return list(writeSqlSession, sql(), policyNo);
	}
	
	public List<PolicyData> validation() {
		List<PolicyData> list = writeSqlSession.selectList(sql());
		return list;
	}
	
	public List<PolicyData> getPolicyNoWithSuccess() {
		List<PolicyData> list = writeSqlSession.selectList(sql());
		return list;
	}
}

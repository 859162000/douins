package com.douins.agency.service.douins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.PolicyData;
@Repository
public class PolicyDataDao extends AbstractDao{


	public List<PolicyData> queryPolicys(Map<String, Object> params) {
		   return list(writeSqlSession, sql(), params);
	}

}

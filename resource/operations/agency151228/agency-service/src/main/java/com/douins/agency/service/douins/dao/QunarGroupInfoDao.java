package com.douins.agency.service.douins.dao;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.QunarGroupInfo;

@Repository
public class QunarGroupInfoDao extends AbstractDao{

	public String getHeadidByGpInfo(QunarGroupInfo qnGpInfo){
		 return writeSqlSession.selectOne(sql(), qnGpInfo);
	}
}

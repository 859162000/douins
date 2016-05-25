package com.douins.agency.service.douins.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureRequestHeader;
import com.douins.agency.service.douins.domain.model.QunarOrderStatu;

@Repository
public class InsureRequestHeaderDao extends AbstractDao {

	public void insert(InsureRequestHeader insureRequestHeader){
		writeSqlSession.insert(sql(), insureRequestHeader);
	}
	
	public void updateByOrder(InsureRequestHeader insureRequestHeader){
		writeSqlSession.update(sql(),insureRequestHeader);
	}
	
	public List<QunarOrderStatu> getQnOrderStatu(String orderNo){
		return list(writeSqlSession,sql(),orderNo);
	}
}

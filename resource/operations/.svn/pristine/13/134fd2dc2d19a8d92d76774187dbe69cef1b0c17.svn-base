package com.douins.agency.service.douins.dao;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureConfirmItem;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InsureConfirmItmDao extends AbstractDao {
	public int insert(List<InsureConfirmItem> items){
		int count = writeSqlSession.insert(sql(), items);
		return count;
	}
	
	public List<InsureConfirmItem> getIsCfmItmByOrder(String orderNo){
		return list(writeSqlSession,sql(),orderNo);
	}
}

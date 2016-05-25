package com.douins.agency.service.douins.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmItem;

@Repository
public class InsureCancelConfirmItemDao extends AbstractDao{

	public void insert(List<InsureCancelConfirmItem> isCnlAplyItms){
		   writeSqlSession.insert(sql(), isCnlAplyItms);
	}
	
}

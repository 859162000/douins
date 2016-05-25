package com.douins.agency.service.douins.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureCancelApplyItem;

@Repository
public class InsureCancelApplyItemDao extends AbstractDao{
	public void insert(List<InsureCancelApplyItem> isCnlAplyItms){
		   writeSqlSession.insert(sql(), isCnlAplyItms);
	}
	
}

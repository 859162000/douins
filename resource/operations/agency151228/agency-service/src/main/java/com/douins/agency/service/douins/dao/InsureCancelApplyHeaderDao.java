package com.douins.agency.service.douins.dao;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureCancelApplyHeader;

@Repository
public class InsureCancelApplyHeaderDao extends AbstractDao{
   public long  insert(InsureCancelApplyHeader isCnlAplyHd){
	   writeSqlSession.insert(sql(), isCnlAplyHd);
	   return isCnlAplyHd.getId();
   }
}

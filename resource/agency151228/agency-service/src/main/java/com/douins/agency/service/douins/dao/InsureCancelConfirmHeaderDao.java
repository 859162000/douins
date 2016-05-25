package com.douins.agency.service.douins.dao;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmHeader;

@Repository
public class InsureCancelConfirmHeaderDao extends AbstractDao{

   public long insert(InsureCancelConfirmHeader isCnlCfmHd){
	   writeSqlSession.insert(sql(), isCnlCfmHd);

	   return isCnlCfmHd.getId();
   }
}

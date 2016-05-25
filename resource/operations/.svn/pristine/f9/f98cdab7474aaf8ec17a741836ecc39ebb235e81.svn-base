package com.douins.agency.service.douins.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureConfirmItem;
import com.douins.agency.service.douins.domain.model.InsureRequestFeedback;

@Repository
public class InsureRequestFeedbackDao extends AbstractDao {
	
	public void insert(InsureRequestFeedback insureReqFb){
		writeSqlSession.insert(sql(), insureReqFb);
	}

	public List<InsureRequestFeedback> getIsReqFeedbackByTradeNo(String tradeNo){
		return list(writeSqlSession,sql(),tradeNo);
	}
	
	public List<InsureRequestFeedback> getIsReqFeedbackByOrderNo(InsureConfirmItem isCfmItm){
		return list(writeSqlSession,sql(),isCfmItm);
	}
}

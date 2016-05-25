package com.douins.agency.service.douins.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureConfirmFeedback;
import com.douins.agency.service.douins.domain.model.InsureConfirmItem;

@Repository
public class InsureConfirmFeedbackDao extends AbstractDao{

	public void insert(List<InsureConfirmFeedback> insureCfmFbs){
		writeSqlSession.insert(sql(), insureCfmFbs);
	}
	
	public List<InsureConfirmFeedback> getIsCfmFbByProposalNo(String proposalNo){
		return list(writeSqlSession,sql(),proposalNo);
	}
	
	public List<InsureConfirmFeedback> getIsCfmByTradeNo(InsureConfirmItem isCfmItm){
		return list(writeSqlSession,sql(),isCfmItm);
	}
}

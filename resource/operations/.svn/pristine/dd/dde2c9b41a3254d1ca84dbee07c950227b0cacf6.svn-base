package com.douins.agency.service.douins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureRequestFeedback;
import com.douins.agency.service.douins.domain.model.InsureRequestItem;
import com.douins.agency.service.douins.domain.model.QunarReqReturn;
import com.douins.agency.service.douins.domain.model.QunarReqWhrCondition;
import com.google.common.collect.Maps;

@Repository
public class InsureRequestItmDao extends AbstractDao {
	public void insert(InsureRequestItem insureRequestItem){
		writeSqlSession.insert(sql(), insureRequestItem);
	}
	
	public void updateCfmflagByTradeNo(InsureRequestItem insureRequestItem ){
		writeSqlSession.update(sql(), insureRequestItem);
	}
	
	public List<InsureRequestItem> getIsReqItmByTradeno(String tradeNo){
		return list(writeSqlSession,sql(),tradeNo);
	}
	
	public void updateCfmflagByApplyseq(QunarReqWhrCondition qnReqWhrCndit){
		writeSqlSession.update(sql(), qnReqWhrCndit);
	}
	
	public List<QunarReqReturn> getQunarReqReturn(QunarReqWhrCondition qnReqWhrCndit){
		 return list(writeSqlSession,sql(),qnReqWhrCndit);
	}
	
	public void  updateStatu(InsureRequestItem insureRequestItem) {
		writeSqlSession.update(sql(), insureRequestItem);
	}
	
	public List<InsureRequestFeedback> getCfmedReqItmByOrderNo(String orderNo){
		return list(writeSqlSession,sql(),orderNo);
	}
	
	/**
	 * 根据订单号查询核保记录数据
	 * @param orderNo
	 * @return
	 */
	public List<InsureRequestFeedback> getReqItmByOrderNo(String orderNo){
	    return list(writeSqlSession, sql(), orderNo);
	}
	
	/**
	 * 根据订单号和
	 * @param cfmFlag
	 * @param orderNo
	 * @param applySeqs
	 */
	public void updateCfmFlagByOrderNoAndSeq(String cfmFlag, String orderNo, List<String> applySeqs){
	    Map<String, Object> params = Maps.newHashMap();
	    params.put("cfmFlag", cfmFlag);
	    params.put("orderNo", orderNo);
	    params.put("list", applySeqs);
	    
	    writeSqlSession.update(sql(), params);
	}
}

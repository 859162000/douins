package com.douins.account.service;


import com.douins.account.domain.vo.BindCardReponseVO;
import com.douins.account.domain.vo.BindCardRequest;
import com.douins.trans.domain.vo.ResponseTransVo;

/**
 * 解 绑卡业务接口
 * 
 * @author shaotongyao
 *
 */

public interface ProcessBindOrUnBindCardService {
	
	public ResponseTransVo<BindCardReponseVO> processBindCard(BindCardRequest bindCardRequest);
	
	public ResponseTransVo<BindCardReponseVO> processUnBindCard(BindCardRequest bindCardRequest);

	public int updateUserAccountBindCardFlagByFlowNo(String string, String flowNo);

	public int updateUserAccountUnBindCardByUnbindFlowNo(String unbindflowNo);

	public ResponseTransVo<BindCardReponseVO> getBindCardInfo(BindCardRequest bindCardRequest);

}

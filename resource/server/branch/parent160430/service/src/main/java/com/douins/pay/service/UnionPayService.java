package com.douins.pay.service;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.exception.NestedBusinessException;
import com.douins.account.domain.enums.DetailIo;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.domain.model.UserAccountOpenApply;
import com.douins.account.service.UserAccountDetailService;
import com.douins.account.service.UserAccountOpenApplyService;
import com.douins.account.service.UserAccountService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.trans.domain.enums.BusinessTransStatus;
import com.douins.trans.domain.enums.BusinessTransType;
import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.service.BusinessTransService;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;

public abstract class UnionPayService {
	protected SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	protected UserAccountOpenApplyService userAccountOpenApplyService;
	@Autowired
	protected UserAccountService userAccountService;
	@Autowired
	protected UserAccountDetailService userAccountDetailService;
	@Autowired
	protected BusinessTransService businessTransService;

	protected final Lock lock = new ReentrantLock();
	
	protected abstract ApplyResult doThirdPay(UserAccountDetail detail);
	
	protected abstract ApplyResult doThirdRePay(UserAccountDetail detail);
	
	protected abstract ApplyResult doThirdOpen(UserAccountOpenApply openApply);
	
	/**
	 * 支付或提款
	 * @param transChannel
	 * @param detailType
	 * @param userId
	 * @param applyAmount
	 * @return
	 * @throws Exception
	 */
	public ApplyResult doPay(UserAccountDetail detail,String transChannel){
		ApplyResult result=new ApplyResult();
		try {
			logger.info("======doPay begin========");
			BusinessTrans trans=new BusinessTrans();
			trans.setBusinessId(detail.getBusinessId());
			trans.setTransNo(detail.getBusinessNo());
			trans.setTransType(detail.getDetailType());
			trans.setTransChannel(transChannel);
			trans.setTransTime(new Date());
			trans.setPayMoney(detail.getApplyAmount());
			trans.setStatus(BusinessTransStatus.INIT.getValue());
			BusinessTrans paramT  = this.savePaymentTrans(trans);
			
			if (detail.getDetailIo().equals(DetailIo.IN.getValue())) {
				result = doThirdPay(detail);
			} else if (detail.getDetailIo().equals(DetailIo.OUT.getValue())) {
				result = doThirdRePay(detail);
			}
			
			if(result.isSuccess()){
				paramT.setStatus(BusinessTransStatus.SUCCESS.getValue());
			}else{
				paramT.setStatus(BusinessTransStatus.FAILURE.getValue());
			}
			paramT.setResultMsg(result.getResultCode() + "||" + result.getMsg());
			this.updatePaymentTrans(paramT);
			logger.info("======doPay end========");
		} catch (Exception e) {
			logger.error("doPay error",e);
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 申请虚拟账户
	 * @param paramT
	 * @return
	 * @throws DataBaseAccessException
	 */
	public ApplyResult openApply(UserAccountOpenApply openApply,String transChannel){
		ApplyResult result=new ApplyResult();
		try {
			logger.info("======openApply begin========");
			BusinessTrans paramT = this.saveTrans(openApply, transChannel);
			result = this.doThirdOpen(openApply);
			this.dealOpenResp(paramT, result);
			logger.info("======openApply end========");
		} catch (Exception e) {
			logger.error("doPay error",e);
			result.setSuccess(false);
		}
		return result;
	}
	
	private BusinessTrans saveTrans(UserAccountOpenApply openApply,String transChannel) throws Exception {
		BusinessTrans trans=new BusinessTrans();
		trans.setBusinessId(openApply.getOpenApplyId());
		trans.setTransType(BusinessTransType.open_request.getValue());
		trans.setTransChannel(transChannel);
		trans.setTransTime(new Date());
		trans.setStatus(BusinessTransStatus.INIT.getValue());
		BusinessTrans paramT  = this.savePaymentTrans(trans);
		return paramT;
	}
	
	private void dealOpenResp(BusinessTrans trans,ApplyResult openResp) throws Exception{
		//更新BusinessTrans
		if(openResp.isSuccess()){
			trans.setStatus(BusinessTransStatus.SUCCESS.getValue());
		}else{
			trans.setStatus(BusinessTransStatus.FAILURE.getValue());
		}
		trans.setResultMsg(openResp.getResultCode() + "||" + openResp.getMsg());
		this.updatePaymentTrans(trans);

	}
	
	protected BusinessTrans savePaymentTrans(BusinessTrans paramT) throws DataBaseAccessException {
		try {
			SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
			businessTransService.save(SystemConstant.OP_USER, paramT);
			return paramT;
		} catch (DataBaseAccessException e) {
			throw new NestedBusinessException("系统错误");
		}
	}
	
	protected BusinessTrans updatePaymentTrans(BusinessTrans paramT) throws DataBaseAccessException {
		try {
			SaveEntityUtils.setUpdateForEntity(paramT, SystemConstant.OP_USER);
			businessTransService.update(SystemConstant.OP_USER, paramT);
			return paramT;
		} catch (DataBaseAccessException e) {
			throw new NestedBusinessException("系统错误");
		}
	}
}

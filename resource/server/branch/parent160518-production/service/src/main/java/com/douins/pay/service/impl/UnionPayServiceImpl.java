package com.douins.pay.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.domain.model.UserAccountOpenApply;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.pay.service.UnionPayService;

@Service
public class UnionPayServiceImpl extends UnionPayService {
	protected SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	protected ApplyResult doThirdPay(UserAccountDetail detail){
		ApplyResult result=new ApplyResult();
		try {
			result.setResultCode("000000");
			result.setMsg("成功");
			result.setSuccess(true);
			result.setFinishTime(new Date());
		} catch (Exception e) {
			logger.info("unionpay error",e);
			result.setResultCode("999999");
			result.setMsg("失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	protected ApplyResult doThirdOpen(UserAccountOpenApply openApply) {
		ApplyResult result=new ApplyResult();
		try {
			result.setVirtualAccountNo(System.currentTimeMillis()+"");
			result.setResultCode("000000");
			result.setMsg("成功");
			result.setFinishTime(new Date());
			result.setSuccess(true);
		} catch (Exception e) {
			logger.info("unionpay error",e);
			result.setResultCode("999999");
			result.setMsg("失败");
			result.setSuccess(false);
		}
		return result;
		
	}

	@Override
	protected ApplyResult doThirdRePay(UserAccountDetail detail){
		ApplyResult result=new ApplyResult();
		try {
			result.setResultCode("000000");
			result.setMsg("成功");
			result.setSuccess(true);
			result.setFinishTime(new Date());
		} catch (Exception e) {
			logger.info("unionpay error",e);
			result.setResultCode("999999");
			result.setMsg("失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	
}

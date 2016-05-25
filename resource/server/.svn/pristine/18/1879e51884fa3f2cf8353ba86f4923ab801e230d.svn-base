package com.mango.fortune.sms.service.impl;

import org.springframework.stereotype.Service;

import com.mango.core.common.util.JsonUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.sms.enums.SmsConfig;
import com.mango.fortune.sms.enums.SmsStatusType;
import com.mango.fortune.sms.service.SmsSendApplyService;
import com.mango.fortune.sms.yunpian.SmsResultVo;
import com.mango.fortune.sms.yunpian.YunPianResponse;
import com.mango.fortune.sms.yunpian.YunPianSmsApi;
import com.mango.fortune.util.ConstantsUtil;
import com.mango.tunnel.sms.service.SmsWSService;
import com.mango.tunnel.utils.ServiceLocator;

@Service("yunPianSmsService")
public class YunPianSmsApplyServiceImpl implements SmsSendApplyService {
	
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	private SmsWSService smsWSService = (SmsWSService) ServiceLocator.getWebservice(SmsWSService.class);
	
	@Override
	public SmsResultVo sendSms(String context, String mobile) {

		YunPianResponse ypRes = new YunPianResponse();
		String resultCode = SmsStatusType.HAVE_SEND_FAIL.getCode();

		String resultMsg = "";
		try {
			logger.info("^^^yunpian send sms begin===");
			
			if (ConstantsUtil.isTunnelOrLocal()) {
				resultMsg = smsWSService.yunpianSend(SmsConfig.YUNPIANAPIKEY.getCode(), context, mobile);
			} else {
				resultMsg = YunPianSmsApi.sendSms(SmsConfig.YUNPIANAPIKEY.getCode(), context, mobile);
			}
			logger.info("YunPianSmsResponse:" + resultMsg + ";send sms end^^^");
			ypRes = JsonUtils.readJson(resultMsg, YunPianResponse.class);

			if (YunPianSmsApi.SUCCESSCODE.equals(ypRes.getCode())) {
				resultCode = SmsStatusType.HAVE_SEND_SUCCESS.getCode();
			}
		} catch (Exception e) {
			resultMsg = "sendSms exception";
			logger.error("***sendSms exception:", e);
		}
		ypRes.setResultCode(resultCode);
		ypRes.setResultMsg(resultMsg);

		return ypRes;

	}
}

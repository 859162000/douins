package com.douins.sms.service.iml;

import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.douins.sms.utils.chanzor.ChanzorResponse;
import com.douins.sms.utils.chanzor.ChanzorSendData;
import com.douins.sms.domain.enums.SmsConfig;
import com.douins.sms.domain.enums.SmsStatusType;
import com.douins.sms.service.SmsSendApplyService;
import com.douins.sms.utils.yunpian.SmsResultVo;
import com.douins.common.util.ConstantsUtil;
import com.douins.common.util.HttpClientUtils;
import com.douins.common.util.XmlUtil;
import com.mango.tunnel.sms.service.SmsWSService;
import com.mango.tunnel.utils.ServiceLocator;

@Service("chanzorSmsService")
public class ChanzorSmsApplyServiceImpl implements SmsSendApplyService {
	
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	private SmsWSService smsWSService = (SmsWSService) ServiceLocator.getWebservice(SmsWSService.class);
	
	private static String successCode = "Success";
	
	@Override
	public SmsResultVo sendSms(String context, String mobile) {
		ChanzorResponse chanzorRes = new ChanzorResponse();
		String resultCode = SmsStatusType.HAVE_SEND_FAIL.getCode();
		StringBuffer resultMsg =  new StringBuffer("");
		String chanzorMsg = "";
		try {
			logger.info("^^^chanzor send sms begin===");
			ChanzorSendData data = new ChanzorSendData(SmsConfig.CHANZORACCOUNT.getCode(),SmsConfig.CHANZORPASSWORD.getCode(),mobile,context);
			
			if (ConstantsUtil.isTunnelOrLocal()) {
				chanzorMsg = smsWSService.chanzorSend(SmsConfig.CHANZORURL.getCode(),HttpClientUtils.object2NameValuePair(data));
			}else{
				chanzorMsg = HttpClientUtils.httpClientForm(SmsConfig.CHANZORURL.getCode(), HttpClientUtils.object2NameValuePair(data));
			}
			logger.info("chanzor:" + chanzorMsg + ";send sms end^^^");
			
			chanzorRes = (ChanzorResponse) XmlUtil.string2Object(chanzorMsg, ChanzorResponse.class);

			if (successCode.equals(chanzorRes.getReturnstatus())) {
				resultCode = SmsStatusType.HAVE_SEND_SUCCESS.getCode();
			}
			resultMsg.append(chanzorRes.getReturnstatus() + "|");
			resultMsg.append(chanzorRes.getMessage() + "|");
			resultMsg.append(chanzorRes.getTaskID() + "|");
			resultMsg.append(chanzorRes.getRemainpoint() + "|");
			resultMsg.append(chanzorRes.getSuccessCounts());
		} catch (Exception e) {
			resultMsg.append("chanzor sendSms exception");
			logger.error("***sendSms exception:", e);
		}
		chanzorRes.setResultCode(resultCode);
		chanzorRes.setResultMsg(resultMsg.toString());

		return chanzorRes;

	}
}

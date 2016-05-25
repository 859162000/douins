package com.mango.fortune.policy.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.core.spring.SpringContextHelper;
import com.mango.fortune.insurance.service.InsuranceWorkService;
import com.mango.fortune.partner.model.InsuranceInterfaceConfig;
import com.mango.fortune.partner.service.InsuranceInterfaceConfigService;
import com.mango.fortune.policy.enums.ConfigType;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.fortune.policy.service.PolicyUwService;
import com.mango.fortune.policy.vo.PolicyResult;
import com.mango.fortune.util.InterfaceConfigUtil;

@Service
public class PolicyUwServiceImpl implements PolicyUwService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	@Qualifier("insuranceInterfaceConfigService")
	private InsuranceInterfaceConfigService insuranceInterfaceConfigService;

	@Override
	public PolicyResult doThirdUw(PolicyMaster policy){
		logger.info("===doThirdUw begin==");
		PolicyResult result=new PolicyResult();
		InsuranceInterfaceConfig cfg = new InsuranceInterfaceConfig();
		cfg.setInsuranceId(policy.getInsuranceId());
		cfg.setConfigType(ConfigType.UW.getValue());
		cfg = InterfaceConfigUtil.getInsuranceConfig(cfg);
		logger.info("doThirdUw insuranceId:" + policy.getInsuranceId());
		if(cfg != null){
			InsuranceWorkService service = (InsuranceWorkService) SpringContextHelper.getBean(cfg.getConfigService());
			result = service.doUW(policy);
		}else{
			result.setResultCode("000000");
			result.setMsg("成功");
			result.setSuccess(true);
			result.setFinishTime(new Date());
			result.setSendCode(System.currentTimeMillis()+"");
		}
		logger.info("===doThirdUw end==");
		
		return result;
	}

	@Override
	public PolicyResult doThirdInsure(PolicyMaster policy){
		logger.info("===doThirdInsure begin==");
		PolicyResult result = new PolicyResult();
		InsuranceInterfaceConfig cfg = new InsuranceInterfaceConfig();
		cfg.setInsuranceId(policy.getInsuranceId());
		cfg.setConfigType(ConfigType.INSURE.getValue());
		cfg = InterfaceConfigUtil.getInsuranceConfig(cfg);
		logger.info("doThirdInsure insuranceId:" + policy.getInsuranceId());
		if(cfg != null){
			InsuranceWorkService service = (InsuranceWorkService) SpringContextHelper.getBean(cfg.getConfigService());
			result = service.doInsure(policy);
		}else{
			result.setResultCode("000000");
			result.setMsg("成功");
			result.setSuccess(true);
			result.setFinishTime(new Date());
			result.setPolicyCode(System.currentTimeMillis()+"");
		}
		logger.info("===doThirdInsure end==");
		return result;
	}
}
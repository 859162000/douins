package com.douins.policy2.service.iml;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.common.spring.SpringContextHolder;
import com.douins.common.util.InterfaceConfigUtil;
import com.douins.insurance.domain.enums.InsTransType;
import com.douins.insurance.service.InsuranceWorkService;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;
import com.douins.partner.service.InsuranceInterfaceConfigService;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.policy2.service.PolicyUwService;
import com.mango.core.logger.SimpleLogger;

@Service("policyUwServiceImpl2")
@Transactional
public class PolicyUwServiceImpl2 implements PolicyUwService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	@Qualifier("insuranceInterfaceConfigService")
	private InsuranceInterfaceConfigService insuranceInterfaceConfigService;

	/**
	 * 第三方（保险公司）核保
	 */
	@Override
	@Transactional
	public PolicyResult doThirdUw(PolicyMaster policy){
		PolicyResult result=new PolicyResult();
		InsuranceInterfaceConfig cfg = new InsuranceInterfaceConfig();
		cfg.setInsuranceId(policy.getInsuranceId());
		cfg.setConfigType(InsTransType.UNDERWRITE.getValue());
		cfg = InterfaceConfigUtil.getInsuranceConfig(cfg);
		logger.info("保险公司 ＝ " + cfg.getInsuranceId());
		InsuranceWorkService service = (InsuranceWorkService) SpringContextHolder.getBean(cfg.getConfigService());
		//TODO liAnService
        result = service.doUW(policy);
		
		return result;
	}

	@Override
	@Transactional
	public PolicyResult doThirdInsure(PolicyMaster policy){
		logger.info("===doThirdInsure begin==");
		PolicyResult result = new PolicyResult();
		InsuranceInterfaceConfig cfg = new InsuranceInterfaceConfig();
		cfg.setInsuranceId(policy.getInsuranceId());
		cfg.setConfigType(InsTransType.APPROVE.getValue());
		cfg = InterfaceConfigUtil.getInsuranceConfig(cfg);
		if(cfg != null){
			InsuranceWorkService service = (InsuranceWorkService) SpringContextHolder.getBean(cfg.getConfigService());
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
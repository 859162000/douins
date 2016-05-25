package com.douins.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.douins.common.spring.SpringContextHolder;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;
import com.douins.partner.service.InsuranceInterfaceConfigService;


public class InterfaceConfigUtil {
	private static final Logger logger = LoggerFactory.getLogger(InterfaceConfigUtil.class);
	
	private static long INSURANCEINTERFACE_INTERVAL_TIME = 6;//保险公司配置更新map间隔 1小时间隔
	
	private static long INSUANCE_NOWTIME = 0;
	
	private static Map<String,InsuranceInterfaceConfig> insuranceInterface = new HashMap<String,InsuranceInterfaceConfig>();
	
	@Autowired
	private static InsuranceInterfaceConfigService insuranceInterfaceConfigService;
	
	
	//根据保险公司ID和configType获取配置
	public static InsuranceInterfaceConfig getInsuranceConfig(InsuranceInterfaceConfig insuranceInterfaceConfig){
		InsuranceInterfaceConfig insuranceConfig = new InsuranceInterfaceConfig();
		long nowTime = System.currentTimeMillis();
		if(insuranceInterface == null || insuranceInterface.isEmpty() 
				|| (nowTime - INSUANCE_NOWTIME > INSURANCEINTERFACE_INTERVAL_TIME)){
			if(insuranceInterfaceConfigService==null){
				insuranceInterfaceConfigService=(InsuranceInterfaceConfigService) SpringContextHolder.getBean("insuranceInterfaceConfigService"); 
			}
			logger.info("get InsuranceConfig from db");
			List<InsuranceInterfaceConfig> iicList = insuranceInterfaceConfigService.getAll();
			for(InsuranceInterfaceConfig iic : iicList){
				insuranceInterface.put(iic.getInsuranceId() + ":" + iic.getConfigType(), iic);
			}
			INSUANCE_NOWTIME = nowTime;
		}
		
		insuranceConfig = insuranceInterface.get(insuranceInterfaceConfig.getInsuranceId() + ":" + insuranceInterfaceConfig.getConfigType());
				
		return insuranceConfig;
	} 
	
	
}

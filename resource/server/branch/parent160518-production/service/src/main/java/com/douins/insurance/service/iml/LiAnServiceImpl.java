package com.douins.insurance.service.iml;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.douins.common.util.Configs;
import com.douins.common.util.InterfaceConfigUtil;
import com.douins.common.util.TrustStoreUtils;
import com.douins.insurance.domain.enums.InsTransType;
import com.douins.insurance.service.ConvertService;
import com.douins.insurance.service.InsuranceWorkService;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;
import com.douins.partner.service.InsuranceInterfaceConfigService;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.policy.service.Policyservice;
import com.douins.trans.domain.vo.CanclePolicyRequestVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.tunnel.utils.ServiceLocator;
import com.mango.tunnel.xml.service.XmlWSService;

@Service("liAnService")
public class LiAnServiceImpl implements InsuranceWorkService {
	
	private  SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
    @Autowired
    private ConvertService convertService;
    @Autowired
    private Policyservice policyservice;
    
    @Autowired
    @Qualifier("insuranceInterfaceConfigService")
    private InsuranceInterfaceConfigService insuranceInterfaceConfigService;

	private static final String UTF = "utf-8";
	private static final String orgchkcode = "12345";
	
    private XmlWSService xmlWSService = (XmlWSService) ServiceLocator.getWebservice(XmlWSService.class);
    
    @Resource
    private TrustStoreUtils httpsConnection;
    
    
    private static File keystoreFile;
    private static File truststoreFile;
    
    /**
     * 加载证书库
     */
    @PostConstruct
    private void init(){
        try {
            logger.info("password:"+Configs.get("jsk_password_agency"));
            logger.info("keypath:"+Configs.get("jks_keystore_path_agency"));
            logger.info("trustpath:"+Configs.get("jks_truststore_path_agency"));
            keystoreFile = ResourceUtils.getFile(Configs.get("jks_keystore_path_agency"));
            if(keystoreFile.getPath() != null){     logger.info(keystoreFile.getPath());}else{logger.error("keystore get fail");}
            truststoreFile = ResourceUtils.getFile(Configs.get("jks_truststore_path_agency"));
            if(truststoreFile.getPath() != null ){     logger.info(truststoreFile.getPath());}else{logger.error("truststore get fail");}
            httpsConnection.initHttpsURLConnection(Configs.get("jsk_password_agency"), keystoreFile.getPath(), truststoreFile.getPath());
            
        } catch (Exception e) {
           logger.error("Https 连接初始化失败test================", e);
        }
    }
    
    
    /**
     * 核保
     */
	@Override
	@Transactional
	public PolicyResult doUW(PolicyMaster policyMaster) {
		InsuranceInterfaceConfig cfg=new InsuranceInterfaceConfig();
		cfg.setInsuranceId(policyMaster.getInsuranceId());
		cfg.setConfigType(InsTransType.UNDERWRITE.getValue());
		
        cfg=InterfaceConfigUtil.getInsuranceConfig(cfg);        // 此处将从数据库加载配置
        String transType ="UW";
 		String transFlag = "0";
		String xml = convertService.toConvertRequest(policyMaster, transFlag, transType);
		
		logger.info("send: \n"+xml);
		String retXml = "";
		  try {
		          logger.info("app start send xml to agency:");
		         // retXml = HttpClientUtils2.sendXml_Post(cfg.getInterfaceUrl(), xml, UTF);
		          retXml = httpsConnection.httpsPostLianlife(cfg.getInterfaceUrl(), xml, UTF, null);
	        }catch (Exception e) {
	        	logger.error("===pflife result:"+retXml);
	      }
		logger.info("===pflife result:"+retXml);
		PolicyResult policyResult = convertService.toConvertResponse(retXml);
		return policyResult;
	}



	@Override
	@Transactional
	public PolicyResult doInsure(PolicyMaster policyMaster) {
		String transType = "ISS";
		String transFlag = "0";
		InsuranceInterfaceConfig cfg=new InsuranceInterfaceConfig();
		cfg.setInsuranceId(policyMaster.getInsuranceId());
		cfg.setConfigType(InsTransType.APPROVE.getValue());
        cfg=InterfaceConfigUtil.getInsuranceConfig(cfg);        // 此处将从数据库加载配置
         
		String xml = convertService.toConvertRequest(policyMaster, transFlag, transType);
		logger.info("agency 请求："+xml);
		String responseXml="";
		try {
			//responseXml = HttpClientUtils2.sendXml_Post(cfg.getInterfaceUrl(), xml, UTF);
			responseXml = httpsConnection.httpsPostLianlife(cfg.getInterfaceUrl(), xml, UTF,null);
		  //responseXml = xmlWSService.sendXml(cfg.getInterfaceUrl()+"?sign="+sign, xml, UTF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("agency response："+responseXml);
		return convertService.toConvertResponse(responseXml);
	}

	@Override
	@Transactional
	public PolicyResult canclePolicy(CanclePolicyRequestVo canclePolicyRequestVo) {
		 String transType = "WD";
		 String transFlag = "0";
		 InsuranceInterfaceConfig cfg=new InsuranceInterfaceConfig();
		 PolicyMaster policyMaster=(PolicyMaster) policyservice.findByKey(canclePolicyRequestVo.getPolicyMasterId());
		 cfg.setInsuranceId(policyMaster.getInsuranceId());
		 cfg.setConfigType(InsTransType.SURRENDER_ACC.getValue());
         cfg=InterfaceConfigUtil.getInsuranceConfig(cfg);        // 此处将从数据库加载配置
         
		String xml = convertService.toConvertRequest(policyMaster, transFlag, transType);
		String responseXml = "";
		
		try {
			//responseXml = HttpClientUtils2.sendXml_Post(cfg.getInterfaceUrl(), xml, UTF);
			responseXml = httpsConnection.httpsPostLianlife(cfg.getInterfaceUrl(), xml, UTF,null);
			//responseXml= xmlWSService.sendXml(cfg.getInterfaceUrl()+"?sign="+sign, xml, UTF);
		} catch (Exception e) {
			logger.error("退保失败！", e);
		}
		return convertService.toConvertResponse(responseXml);
	}


	@Override
	@Transactional
	public PolicyResult canclePolicy2(CanclePolicyRequestVo canclePolicyRequestVo) {
		PolicyResult policyResult = new PolicyResult();
		policyResult.setSuccess(true);
		return  policyResult;
	}

	
	
}
	

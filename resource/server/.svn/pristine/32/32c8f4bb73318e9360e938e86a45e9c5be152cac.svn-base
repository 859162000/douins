package com.mango.fortune.insurance.pflife.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mango.core.common.util.DateUtils;
import com.mango.core.common.util.MD5Util;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.account.service.UserPayAccountService;
import com.mango.fortune.insurance.pflife.vo.Beneficiarys;
import com.mango.fortune.insurance.pflife.vo.Coverage;
import com.mango.fortune.insurance.pflife.vo.Header;
import com.mango.fortune.insurance.pflife.vo.Insured;
import com.mango.fortune.insurance.pflife.vo.Package;
import com.mango.fortune.insurance.pflife.vo.Packages;
import com.mango.fortune.insurance.pflife.vo.PaymentMethod;
import com.mango.fortune.insurance.pflife.vo.Policy;
import com.mango.fortune.insurance.pflife.vo.PolicyHolder;
import com.mango.fortune.insurance.pflife.vo.Proposal;
import com.mango.fortune.insurance.pflife.vo.ProposalInfo;
import com.mango.fortune.insurance.pflife.vo.ProposalResponse;
import com.mango.fortune.insurance.pflife.vo.Response;
import com.mango.fortune.insurance.service.InsuranceWorkService;
import com.mango.fortune.partner.model.Insurance;
import com.mango.fortune.partner.model.InsuranceInterfaceConfig;
import com.mango.fortune.partner.service.InsuranceInterfaceConfigService;
import com.mango.fortune.partner.service.InsuranceService;
import com.mango.fortune.pay.enums.PayType;
import com.mango.fortune.policy.enums.ConfigType;
import com.mango.fortune.policy.enums.RelationType;
import com.mango.fortune.policy.model.Customer;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.fortune.policy.service.CustomerService;
import com.mango.fortune.policy.service.PolicyMasterService;
import com.mango.fortune.policy.vo.PolicyResult;
import com.mango.fortune.product.enums.CoverageType;
import com.mango.fortune.product.model.Product;
import com.mango.fortune.product.service.ProductService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.user.enums.CertiType;
import com.mango.fortune.user.model.User;
import com.mango.fortune.user.service.UserService;
import com.mango.fortune.util.Const;
import com.mango.fortune.util.ConstantsUtil;
import com.mango.fortune.util.HttpClientUtils;
import com.mango.fortune.util.InterfaceConfigUtil;
import com.mango.tunnel.utils.ServiceLocator;
import com.mango.tunnel.xml.service.XmlWSService;
import com.thoughtworks.xstream.XStream;

@Service("pfLifeService")
public class PfLifeServiceImpl implements InsuranceWorkService {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private InsuranceService insuranceService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserPayAccountService userPayAccountService;

	@Autowired
	@Qualifier("insuranceInterfaceConfigService")
	private InsuranceInterfaceConfigService insuranceInterfaceConfigService;

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	
	private XmlWSService xmlWSService = (XmlWSService) ServiceLocator.getWebservice(XmlWSService.class);
	
	@Override
	public PolicyResult doUW(PolicyMaster policyMaster) {
		PolicyResult policyResult = new PolicyResult();
		try {
			InsuranceInterfaceConfig cfg=new InsuranceInterfaceConfig();
			cfg.setInsuranceId(policyMaster.getInsuranceId());
			cfg.setConfigType(ConfigType.UW.getValue());
			cfg=InterfaceConfigUtil.getInsuranceConfig(cfg);
			Insurance insurance=insuranceService.findByKey(policyMaster.getInsuranceId());
			
			Proposal packages = this.convPackage(policyMaster,insurance,cfg);
			Package retPack=this.send(packages,insurance,cfg);
			policyResult=dealResult(retPack,cfg.getConfigType());
		} catch (Exception e) {
			logger.error("pfLifeService uw error",e);
			policyResult.setSuccess(false);
			policyResult.setResultCode(ResponseCode.FAILED.getValue());
			policyResult.setMsg(ResponseCode.FAILED.getName());
		}
		return policyResult;
	}

	@Override
	public PolicyResult doInsure(PolicyMaster policyMaster) {
		PolicyResult policyResult = new PolicyResult();
		try {
			InsuranceInterfaceConfig cfg=new InsuranceInterfaceConfig();
			cfg.setInsuranceId(policyMaster.getInsuranceId());
			cfg.setConfigType(ConfigType.INSURE.getValue());
			cfg=InterfaceConfigUtil.getInsuranceConfig(cfg);
			Insurance insurance=insuranceService.findByKey(policyMaster.getInsuranceId());
			
			Proposal proposal = this.convPackage(policyMaster,insurance,cfg);
			Package retPack=this.send(proposal,insurance,cfg);
			policyResult=dealResult(retPack,cfg.getConfigType());
		} catch (Exception e) {
			logger.error("pfLifeService insure error",e);
			policyResult.setSuccess(false);
			policyResult.setResultCode(ResponseCode.FAILED.getValue());
			policyResult.setMsg(ResponseCode.FAILED.getName());
		}
		return policyResult;
	}

	private Proposal convPackage(PolicyMaster policyMaster,Insurance insurance,InsuranceInterfaceConfig cfg) {
		Proposal proposal = null;
		if(ConfigType.UW.getValue().equals(cfg.getConfigType())){
			proposal=this.convUwProposal(policyMaster,insurance,cfg);
		}else if(ConfigType.INSURE.getValue().equals(cfg.getConfigType())){
			proposal=this.convInsureProposal(policyMaster,insurance,cfg);
		}
		return proposal;
	}
	
	private Proposal convUwProposal(PolicyMaster policyMaster,Insurance insurance,InsuranceInterfaceConfig cfg) {
		Proposal proposal = new Proposal();
		Product product = productService.findByKey(policyMaster.getProductId());
		User user = userService.findByKey(policyMaster.getUserId());
		proposal.setProposalInfo(this.convUwProposalInfo(policyMaster,insurance,cfg));
		proposal.setPolicyHolder(this.convHolder(user));
		proposal.setInsureds(this.convInsureds(policyMaster, user));
		proposal.setBeneficiarys(this.convBeneficiarys(policyMaster));
		proposal.setCoverages(this.convRisks(policyMaster,product));
		return proposal;
	}
	
	private Proposal convInsureProposal(PolicyMaster policyMaster,Insurance insurance,InsuranceInterfaceConfig cfg) {
		Proposal proposal = new Proposal();
		proposal.setProposalInfo(this.convInsureProposalInfo(policyMaster,insurance,cfg));
		proposal.setPaymentMethod(this.convPayInfo(policyMaster));
		return proposal;
	}

	private ProposalInfo convUwProposalInfo(PolicyMaster policyMaster,Insurance insurance,InsuranceInterfaceConfig cfg) {
		ProposalInfo prInfo = new ProposalInfo();
		prInfo.setTransType(cfg.getTransType());
		prInfo.setOrderId(policyMaster.getOrderNo());
		prInfo.setApplyDate(DateUtils.dateToString(policyMaster.getApplyTime(), DEFAULT_DATE_FORMAT));
		prInfo.setValidateDate(DateUtils.dateToString(policyMaster.getValidateDate(), DEFAULT_DATE_FORMAT));
		prInfo.setSubmitChannel(insurance.getTransChannel());
		prInfo.setChildChannel(insurance.getTransChannel());
		Date d=DateUtils.today();
		prInfo.setTransDate(DateUtils.dateToString(d, DEFAULT_DATE_FORMAT));
		prInfo.setTransTime(DateUtils.dateToString(d, DEFAULT_TIME_FORMAT));
		prInfo.setAutoAplIndi("N");
		prInfo.setTmpTotalPrem(policyMaster.getTotalPrem());
		return prInfo;
	}
	
	private ProposalInfo convInsureProposalInfo(PolicyMaster policyMaster,Insurance insurance,InsuranceInterfaceConfig cfg) {
		ProposalInfo prInfo = new ProposalInfo();
		prInfo.setTransType(cfg.getTransType());
		prInfo.setOrderId(policyMaster.getOrderNo());
		prInfo.setProposalNo(policyMaster.getSendCode());
		Date d=DateUtils.today();
		prInfo.setTransDate(DateUtils.dateToString(d, DEFAULT_DATE_FORMAT));
		prInfo.setTransTime(DateUtils.dateToString(d, DEFAULT_TIME_FORMAT));
		prInfo.setSubmitChannel(insurance.getTransChannel());
		prInfo.setChildChannel(insurance.getTransChannel());
		prInfo.setAutoAplIndi("N");
		prInfo.setTmpTotalPrem(policyMaster.getTotalPrem());
		return prInfo;
	}

	private PolicyHolder convHolder(User user) {
		PolicyHolder hoderInfo = new PolicyHolder();
		hoderInfo.setName(user.getUserName());
		hoderInfo.setGender(user.getUserSex().equals("1") ? "M" : "F");
		hoderInfo.setBirthday(DateUtils.formatDate(user.getUserBirthDay()));
		hoderInfo.setCertiType(this.transFormCertiType(user.getCertiType()));
		hoderInfo.setCertiCode(user.getCertiCode());
//		hoderInfo.setExpiryDate(DateUtils.formatDate(user.getCertiValidDate()));
		hoderInfo.setMobile(user.getUserMobile());
		hoderInfo.setEmail(user.getUserEmail());
		hoderInfo.setAddress1(user.getDetailedAdress());
		hoderInfo.setPostCode(user.getZipcode());
		return hoderInfo;
	}

	private List<Insured> convInsureds(PolicyMaster policyMaster, User user) {
		List<Insured> insuredInfos = new ArrayList<Insured>();
		Insured insuredInfo = new Insured();
		if (!"1".equals(policyMaster.getRelation())) {
			Customer customer = customerService.findByKey(policyMaster.getInsuredId());
			insuredInfo.setName(customer.getName());
			insuredInfo.setGender(customer.getSex().equals("1") ? "M" : "F");
			insuredInfo.setBirthday(DateUtils.formatDate(customer.getBirthday()));
			insuredInfo.setCertiType(this.transFormCertiType(customer.getCardType()));
			insuredInfo.setCertiCode(customer.getCardNo());
//			insuredInfo.setExpiryDate(DateUtils.formatDate(customer.getCardValidDate()));
			insuredInfo.setMobile(customer.getMobilephone());
			insuredInfo.setEmail(customer.getEmail());
			insuredInfo.setAddress1(customer.getAddress());
			insuredInfo.setPostCode(customer.getPostcode());
		} else {
			insuredInfo.setName(user.getUserName());
			insuredInfo.setGender(user.getUserSex().equals("1") ? "M" : "F");
			insuredInfo.setBirthday(DateUtils.formatDate(user.getUserBirthDay()));
			insuredInfo.setCertiType(this.transFormCertiType(user.getCertiType()));
			insuredInfo.setCertiCode(user.getCertiCode());
//			insuredInfo.setExpiryDate(DateUtils.formatDate(user.getCertiValidDate()));
			insuredInfo.setMobile(user.getUserMobile());
			insuredInfo.setEmail(user.getUserEmail());
			insuredInfo.setAddress1(user.getDetailedAdress());
			insuredInfo.setPostCode(user.getZipcode());
		}
		insuredInfo.setRalationToPH(this.transformRelation(policyMaster.getRelation()));
		insuredInfo.setIsDriver("0");
		insuredInfos.add(insuredInfo);
		return insuredInfos;
	}
	
	private Beneficiarys convBeneficiarys(PolicyMaster policyMaster) {
		Beneficiarys beneficiarys=new Beneficiarys();
		beneficiarys.setIsLegal("1");
		return beneficiarys;
	}

	private List<Coverage> convRisks(PolicyMaster policyMaster,Product product) {
		List<Coverage> riskInfos = new ArrayList<Coverage>();
		Coverage riskInfo = new Coverage();
		riskInfo.setProductCode(product.getPartnerProductCode());
		riskInfo.setMasterProductCode(product.getProductCode());
		riskInfo.setChargePeriod("1");
		riskInfo.setChargeYear("0");
		riskInfo.setCoveragePeriod(this.transformPeriodType(product.getCoverageType()));
		riskInfo.setCoverageYear(product.getProductCoverage());
		riskInfo.setMult(policyMaster.getApplyNum());
//		riskInfo.setSumAssured(policyMaster.getTotalPrem());
		riskInfo.setPremium(policyMaster.getTotalPrem());
		riskInfos.add(riskInfo);
		return riskInfos;
	}
	
	private PaymentMethod convPayInfo(PolicyMaster policy){
		PaymentMethod pay=new PaymentMethod();
		pay.setPayMode(this.transformPayMode(PayType.TRANSFERACCOUNT.getValue()));
		Date d=DateUtils.today();
		pay.setPayDate(DateUtils.dateToString(d, DEFAULT_DATE_FORMAT));
		pay.setPayTime(DateUtils.dateToString(d, DEFAULT_TIME_FORMAT));
		pay.setPayMoney(policy.getTotalPrem());
		pay.setPayCheckNo(policy.getPayOrderNo());
//		UserPayAccount payAccount=userPayAccountService.findOneByUserId(policy.getUserId());
//		pay.setBankCode(transformBankCode(payAccount.getBankCode()));
//		pay.setBankAccount(payAccount.getAccountNo());
//		pay.setAccoutName(payAccount.getAccountName());
		return pay;
	}

	private String transformPeriodType(String periodType) {
		if (CoverageType.IRRELEVANT.getValue().equals(periodType)) {
			return "0";
		} else if (CoverageType.LIFE.getValue().equals(periodType)) {
			return "1";
		} else if (CoverageType.YEAR.getValue().equals(periodType)) {
			return "2";
		} else if (CoverageType.AGE.getValue().equals(periodType)) {
			return "3";
		} else if (CoverageType.MONTH.getValue().equals(periodType)) {
			return "4";
		} else if (CoverageType.DAY.getValue().equals(periodType)) {
			return "5";
		} 
		return null;
	}

	private String transFormCertiType(String cardType) {
		if (CertiType.ID.getValue().equals(cardType)) {
			return "1";
		} else if (CertiType.Passport.getValue().equals(cardType)) {
			return "3";
		} else if (CertiType.Military.getValue().equals(cardType)) {
			return "8";
		} else if (CertiType.Other.getValue().equals(cardType)) {
			return "9";
		}
		return null;
	}

	private String transformRelation(String relation) {
		if (RelationType.MYSELF.getValue().equals(relation)) {
			return "1";
		} else if (RelationType.PARENT.getValue().equals(relation)) {
			return "10";
		} else if (RelationType.CHILDREN.getValue().equals(relation)) {
			return "2";
		} else if (RelationType.PARTNER.getValue().equals(relation)) {
			return "3";
		}
		return null;
	}
	
	private String transformPayMode(String payMode){
		if (PayType.TRANSFERACCOUNT.getValue().equals(payMode)) {
			return "3";
		}
		return null;
	}
	
	private Package send(Proposal proposal,Insurance insurance,InsuranceInterfaceConfig cfg) throws Exception {
		XStream xs = new XStream();
		xs.processAnnotations(Proposal.class);
		String xml = xs.toXML(proposal);
		xml="<?xml version=\"1.0\" encoding=\"GBK\"?>\n"+xml;
		logger.info("===pflife request xml====\n"+xml);
		String sign=this.sign(xml,insurance.getSignKey());
		logger.info("sign===="+sign);
		String retXml = "";
		if (ConstantsUtil.isTunnelOrLocal()) {
			retXml = xmlWSService.sendXml(cfg.getInterfaceUrl()+"?sign="+sign, xml, "GBK");
		}else{
			retXml = HttpClientUtils.sendXml(cfg.getInterfaceUrl()+"?sign="+sign, xml, "GBK");
		}
		logger.info("===pflife result:"+retXml);
		xs.processAnnotations(Packages.class);
		Packages packages = (Packages) xs.fromXML(retXml);
		return packages.getPackageList().get(0);
	}

	private String sign(String xml,String key) {
		return MD5Util.MD5Encode(key+xml, Const.ENCODE_UTF8);
	}
	
	private PolicyResult dealResult(Package retPack,String configType) {
		PolicyResult policyResult = new PolicyResult();
		Header head = retPack.getHeader();
		if ("0".equals(head.getResponseCode())) {
			Response response = retPack.getResponse();
			if(ConfigType.UW.getValue().equals(configType)){
				ProposalResponse proposal=response.getProposal();
				if(proposal!=null&&"1".equals(proposal.getUnderwriteFlag())){
					policyResult.setSuccess(true);
					policyResult.setResultCode(ResponseCode.SUCCESS.getValue());
					policyResult.setFinishTime(DateUtils.today());
					policyResult.setSendCode(proposal.getProposalNo());
					logger.info("=====uw success====");
				}else{
					policyResult.setSuccess(false);
					policyResult.setResultCode(ResponseCode.FAILED.getValue());
					policyResult.setMsg(proposal.getFailReason());
					logger.info("=====uw failed====");
				}
			}else if(ConfigType.INSURE.getValue().equals(configType)){
				Policy policy=response.getPolicy();
				if(policy!=null&&"1".equals(policy.getIsSuccess())){
					policyResult.setSuccess(true);
					policyResult.setResultCode(ResponseCode.SUCCESS.getValue());
					policyResult.setFinishTime(DateUtils.today());
					policyResult.setPolicyCode(policy.getPolicyNo());
					logger.info("=====insure success====");
				}else{
					policyResult.setSuccess(false);
					policyResult.setResultCode(ResponseCode.FAILED.getValue());
					policyResult.setMsg(policy.getFailReason());
					logger.info("=====insure failed====");
				}
			}
		} else {
			policyResult.setSuccess(false);
			policyResult.setResultCode(head.getResponseCode());
			policyResult.setMsg(head.getErrorMessage());
		}
		return policyResult;
	}
	
	public static void main(String[] args) throws ParseException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath*:spring/applicationContext.xml",
				"classpath*:spring/applicationContext-mvc.xml");
		InsuranceWorkService service=(InsuranceWorkService) ac.getBean("pfLifeService"); 
		PolicyMasterService policysService = (PolicyMasterService) ac.getBean("policyMasterService");
		PolicyMaster policy = policysService.findByKey("f67d2ac94a1d4764b843de6cfde32301");
		service.doUW(policy);
	}
	


}

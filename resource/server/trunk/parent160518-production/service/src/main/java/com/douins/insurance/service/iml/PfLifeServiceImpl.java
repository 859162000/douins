package com.douins.insurance.service.iml;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.douins.account.domain.enums.CertiType;
import com.douins.account.domain.model.User;
import com.douins.account.service.UserService;
import com.douins.common.template.ProposalTemplate;
import com.douins.common.template.UWTemplate;
import com.douins.common.util.ConstantsUtil;
import com.douins.common.util.HttpClientUtils;
import com.douins.common.util.InterfaceConfigUtil;
import com.douins.insurance.domain.enums.InsTransType;
import com.douins.insurance.domain.model.Beneficiarys;
import com.douins.insurance.domain.model.Coverage;
import com.douins.insurance.domain.model.Header;
import com.douins.insurance.domain.model.InsResponse;
import com.douins.insurance.domain.model.Insured;
import com.douins.insurance.domain.model.Package;
import com.douins.insurance.domain.model.Packages;
import com.douins.insurance.domain.model.PaymentMethod;
import com.douins.insurance.domain.model.Policy;
import com.douins.insurance.domain.model.PolicyBenifit;
import com.douins.insurance.domain.model.PolicyHolder;
import com.douins.insurance.domain.model.PolicyInfo;
import com.douins.insurance.domain.model.Proposal;
import com.douins.insurance.domain.model.ProposalInfo;
import com.douins.insurance.domain.model.ProposalResponse;
import com.douins.insurance.domain.model.Response;
import com.douins.insurance.service.InsuranceWorkService;
import com.douins.partner.domain.model.Insurance;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;
import com.douins.partner.service.InsuranceInterfaceConfigService;
import com.douins.partner.service.InsuranceService;
import com.douins.pay.domain.enums.PayType;
import com.douins.policy.domain.enums.RelationType;
import com.douins.policy.domain.model.Customer;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.policy.service.CustomerService;
import com.douins.policy.service.PolicyMasterService;
import com.douins.product.domain.enums.CoverageType;
import com.douins.product.domain.model.Product;
import com.douins.product.service.ProductService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.vo.CanclePolicyRequestVo;
import com.douins.trans.domain.vo.CanclePolicyResponse;
import com.google.common.collect.Maps;
import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
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
//    @Autowired
//    private UserPayAccountService userPayAccountService;

    @Autowired
    @Qualifier("insuranceInterfaceConfigService")
    private InsuranceInterfaceConfigService insuranceInterfaceConfigService;

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    private XmlWSService xmlWSService = (XmlWSService) ServiceLocator.getWebservice(XmlWSService.class);
    
    /**
     * 核保
     */
    @Override
    public PolicyResult doUW(PolicyMaster policyMaster) {
        PolicyResult policyResult = new PolicyResult();
        try {
            InsuranceInterfaceConfig cfg=new InsuranceInterfaceConfig();
            cfg.setInsuranceId(policyMaster.getInsuranceId());
            cfg.setConfigType(InsTransType.UNDERWRITE.getValue());
            cfg=InterfaceConfigUtil.getInsuranceConfig(cfg);        // 此处将从数据库加载配置
            Insurance insurance=insuranceService.findByKey(policyMaster.getInsuranceId());

//            Proposal packages = this.convPackage(policyMaster,insurance,cfg);
//            // 测试代码，上线时请注释掉
//            logger.info("InsConfUrl:"+ cfg.getInterfaceUrl());
//            String proposalStr = JsonOnlineUtils.object2json(packages);
//            logger.info("投保标书：" + proposalStr);
//            // 测试结束
//            
//            Package retPack=this.send(packages,insurance,cfg);
//            // 测试
//            String reString = JsonOnlineUtils.object2json(retPack);
//            logger.info("发送到保险公司："+ reString);
//            // 测试结束
//            
//            policyResult=dealResult(retPack,cfg.getConfigType());
            
            String xml = getUWXml(policyMaster);
            String rexml = this.send2(xml, insurance, cfg);
            if(rexml == null || StringUtils.isBlank(rexml)){
                policyResult.setSuccess(false);
                policyResult.setResultCode(ResponseCode.RESPOSE_NULL.getValue());
                policyResult.setMsg(ResponseCode.RESPOSE_NULL.getName());
            }else {
                InsResponse uwResponse = ResponseUtil.getUWResponse(rexml);
                converResponse(uwResponse, policyResult);
            }
        } catch (Exception e) {
            logger.error("pfLifeService uw error",e);
            policyResult.setSuccess(false);
            policyResult.setResultCode(ResponseCode.FAILED.getValue());
            policyResult.setMsg(ResponseCode.FAILED.getName());
        }
        return policyResult;
    }

    /**
     * 承保
     */
    @Override
    public PolicyResult doInsure(PolicyMaster policyMaster) {
        PolicyResult policyResult = new PolicyResult();
        try {
            InsuranceInterfaceConfig cfg=new InsuranceInterfaceConfig();
            cfg.setInsuranceId(policyMaster.getInsuranceId());
            cfg.setConfigType(InsTransType.APPROVE.getValue());
            cfg=InterfaceConfigUtil.getInsuranceConfig(cfg);
            Insurance insurance=insuranceService.findByKey(policyMaster.getInsuranceId());
            
//            Proposal proposal = this.convPackage(policyMaster,insurance,cfg);
//            Package retPack=this.send(proposal,insurance,cfg);
//            policyResult=dealResult(retPack,cfg.getConfigType());
            
            String xml = getProposalXml(policyMaster);
            String reXml = this.send2(xml, insurance, cfg);
            if(reXml == null || StringUtils.isBlank(reXml)){
                policyResult.setSuccess(false);
                policyResult.setResultCode(ResponseCode.RESPOSE_NULL.getValue());
                policyResult.setMsg(ResponseCode.RESPOSE_NULL.getName());
            }else {
                InsResponse insResponse = ResponseUtil.getInsuredResponse(reXml);
                converResponse(insResponse, policyResult);
            }
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
        if(InsTransType.UNDERWRITE.equals(cfg.getConfigType())){
            proposal=this.convUwProposal(policyMaster,insurance,cfg);
        }else if(InsTransType.APPROVE.getValue().equals(cfg.getConfigType())){
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

    /**
     * 投保人
     * @param user
     * @return
     */
    private PolicyHolder convHolder(User user) {
        PolicyHolder hoderInfo = new PolicyHolder();
        hoderInfo.setName(user.getUserName());
        hoderInfo.setGender(user.getUserSex()!=null?(user.getUserSex().equals("1") ? "M" : "F"):null);
        hoderInfo.setBirthday(user.getUserBirthDay()!=null?DateUtils.formatDate(user.getUserBirthDay()):null);
        hoderInfo.setCertiType(this.transFormCertiType(user.getCertiType()));
        hoderInfo.setCertiCode(user.getCertiCode());
        hoderInfo.setExpiryDate((user.getCertiValidDate()!=null)?DateUtils.formatDate(user.getCertiValidDate()):null);
        hoderInfo.setMobile(user.getUserMobile());
        hoderInfo.setEmail(user.getUserEmail());
        StringBuffer address=new StringBuffer();
        if(StringUtils.isNotBlank(user.getProvince())){
            address.append(user.getProvince());
        }
        if(StringUtils.isNotBlank(user.getCity())){
            address.append(user.getCity());
        }
        if(StringUtils.isNotBlank(user.getDistrict())){
            address.append(user.getDistrict());
        }
        if(StringUtils.isNotBlank(user.getDetailedAdress())){
            address.append(user.getDetailedAdress());
        }
        hoderInfo.setAddress1(address.toString());
        hoderInfo.setZipCode(user.getZipcode());
        return hoderInfo;
    }

    /**
     * 被保人
     * @param policyMaster
     * @param user
     * @return
     */
    private List<Insured> convInsureds(PolicyMaster policyMaster, User user) {
        List<Insured> insuredInfos = new ArrayList<Insured>();
        Insured insuredInfo = new Insured();
        if (!"1".equals(policyMaster.getRelation())) {
            Customer customer = customerService.findByKey(policyMaster.getInsuredId());
            insuredInfo.setName(customer.getName());
            insuredInfo.setGender(customer.getSex()!=null?(customer.getSex().equals("1") ? "M" : "F"):null);
            insuredInfo.setBirthday(customer.getBirthday()!=null?DateUtils.formatDate(customer.getBirthday()):null);
            insuredInfo.setCertiType(this.transFormCertiType(customer.getCardType()));
            insuredInfo.setCertiCode(customer.getCardNo());
            insuredInfo.setExpiryDate((customer.getCardValidDate()!=null)?DateUtils.formatDate(customer.getCardValidDate()):null);
            insuredInfo.setMobile(customer.getMobilephone());
            insuredInfo.setEmail(customer.getEmail());
            insuredInfo.setAddress1(customer.getAddress());
            insuredInfo.setPostCode(customer.getPostcode());
        } else {
            insuredInfo.setName(user.getUserName());
//            insuredInfo.setGender(user.getUserSex().equals("1") ? "M" : "F");
//            insuredInfo.setBirthday(DateUtils.formatDate(user.getUserBirthDay()));
            insuredInfo.setGender(user.getUserSex()!=null?(user.getUserSex().equals("1") ? "M" : "F"):null);
            insuredInfo.setBirthday(user.getUserBirthDay()!=null?DateUtils.formatDate(user.getUserBirthDay()):null);
            insuredInfo.setCertiType(this.transFormCertiType(user.getCertiType()));
            insuredInfo.setCertiCode(user.getCertiCode());
            insuredInfo.setExpiryDate((user.getCertiValidDate()!=null)?DateUtils.formatDate(user.getCertiValidDate()):null);
            insuredInfo.setMobile(user.getUserMobile());
            insuredInfo.setEmail(user.getUserEmail());
            StringBuffer address=new StringBuffer();
            if(StringUtils.isNotBlank(user.getProvince())){
                address.append(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                address.append(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getDistrict())){
                address.append(user.getDistrict());
            }
            if(StringUtils.isNotBlank(user.getDetailedAdress())){
                address.append(user.getDetailedAdress());
            }
            insuredInfo.setAddress1(address.toString());
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
//      riskInfo.setSumAssured(policyMaster.getTotalPrem());
        riskInfo.setPremium(policyMaster.getTotalPrem());
        riskInfos.add(riskInfo);
        return riskInfos;
    }
    
    private PaymentMethod convPayInfo(PolicyMaster policy){
        PaymentMethod pay=new PaymentMethod();
        //pay.setPayMode(this.transformPayMode(PayType.TRANSFERACCOUNT.getValue()));
        Date d=DateUtils.today();
        pay.setPayDate(DateUtils.dateToString(d, DEFAULT_DATE_FORMAT));
        pay.setPayTime(DateUtils.dateToString(d, DEFAULT_TIME_FORMAT));
        pay.setPayMoney(policy.getTotalPrem());
//        pay.setPayCheckNo(policy.getPayOrderNo());
        pay.setOrderId(policy.getOrderNo());
        pay.setProposalNo(policy.getProposalNo());
        pay.setTotalPrem(policy.getTotalPrem());
        
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day = format.format(date);
        pay.setSendTime(day);
//      UserPayAccount payAccount=userPayAccountService.findOneByUserId(policy.getUserId());
//      pay.setBankCode(transformBankCode(payAccount.getBankCode()));
//      pay.setBankAccount(payAccount.getAccountNo());
//      pay.setAccoutName(payAccount.getAccountName());
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
            logger.info("发送 XML：HttpClientUtils.sendXml");
            retXml = HttpClientUtils.sendXml(cfg.getInterfaceUrl()+"?sign="+sign, xml, "GBK");
            //retXml = HttpClientUtils2.sendXml_Post(cfg.getInterfaceUrl()+"?sign="+sign, xml, "GBK");
        }
        logger.info("===pflife result:"+retXml);
        xs.processAnnotations(Packages.class);
        Packages packages = (Packages) xs.fromXML(retXml);
        return packages.getPackageList().get(0);
    }
    
    private String send2(String xml,Insurance insurance,InsuranceInterfaceConfig cfg) throws Exception {
//        XStream xs = new XStream();
//        xs.processAnnotations(Proposal.class);
//        String xml = xs.toXML(proposal);
//        xml="<?xml version=\"1.0\" encoding=\"GBK\"?>\n"+xml;
//        logger.info("===pflife request xml====\n"+xml);
        String sign=this.sign(xml,insurance.getSignKey());
        logger.info("sign===="+sign);
        String retXml = "";
        if (ConstantsUtil.isTunnelOrLocal()) {
            retXml = xmlWSService.sendXml(cfg.getInterfaceUrl()+"?sign="+sign, xml, "GBK");
        }else{
            logger.info("发送 XML：HttpClientUtils.sendXml = " + xml);
            retXml = HttpClientUtils.sendXml(cfg.getInterfaceUrl()+"?sign="+sign, xml, "GBK");
            //retXml = HttpClientUtils2.sendXml_Post(cfg.getInterfaceUrl()+"?sign="+sign, xml, "GBK");
        }
        logger.info("===pflife result:"+retXml);
        
        return retXml;
    }

    private String sign(String xml,String key) {
        //return MD5Util.MD5Encode(key+xml, Const.ENCODE_UTF8);
        Pattern p = Pattern.compile("\t|\r|\n|\r\n|\n\r");
        String temp = key + xml;
        Matcher m2 = p.matcher(temp);
        temp = m2.replaceAll("");
        
        String md5 = null;
        byte[] datas;
        try {
            datas = temp.getBytes("GBK");
            md5 = getMD5(datas);
        } catch (UnsupportedEncodingException e) {
            logger.error("pflife md5 error");
        }

        return md5;
    }
    
    private PolicyResult dealResult(Package retPack,String configType) {
        PolicyResult policyResult = new PolicyResult();
        Header head = retPack.getHeader();
        if ("0".equals(head.getResponseCode())) {
            Response response = retPack.getResponse();
            if(InsTransType.UNDERWRITE.getValue().equals(configType)){
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
            }else if(InsTransType.APPROVE.getValue().equals(configType)){
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
    
    /**
     * 获取核保需要的 xml
     * @param policyMaster
     * @return
     */
    private String getUWXml(PolicyMaster policyMaster){
        String result = null;
        Product product = productService.findByKey(policyMaster.getProductId());
        User user = userService.findByKey(policyMaster.getUserId());
        // header
        Header header = convHeader(InsTransType.UNDERWRITE.getValue(), "0");
        Policy policy = convPolicy(policyMaster);
        PolicyInfo info = convPolicyInfo(policyMaster, product);
        PolicyHolder holder = convHolder2(user);
        Insured insured = convInsured(user, policyMaster.getRelation());
        PolicyBenifit benifit = convBenifit(user);
        
        Map<String, Object> root = Maps.newHashMap();
        root.put("header", header);
        root.put("policy", policy);
        root.put("policyInfo", info);
        root.put("holder", holder);
        root.put("insured", insured);
        root.put("benifit", benifit);
        
        try {
            result = FreeMarkerTemplateUtils.processTemplateIntoString(UWTemplate.getTemplate(), root);
        } catch (Exception e) {
            logger.info("get uw xml error.", e);
        }
        
        return result;
    }
    
    /**
     * header
     * @param transType
     * @param isPackage
     * @return
     */
    private Header convHeader(String transType, String isPackage){
        Header header = new Header();
        header.setTransType(transType);
        header.setIsPackage(isPackage);
        header.setAgentCode("12009020");
        header.setAgentChannel("2");
        header.setPostCode("0660101011901");
        header.setSubmitChannel("33");
        
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day = format.format(date);
        header.setSendTime(day);
        
        return header;
    }
    
    /**
     * policy
     * @param policyMaster
     * @return
     */
    private Policy convPolicy(PolicyMaster policyMaster){
        Policy info = new Policy();
        info.setOrderId(policyMaster.getOrderNo());
        info.setTotalPremium(policyMaster.getTotalPrem().toString());
        return info;
    }
    
    private PolicyInfo convPolicyInfo(PolicyMaster policyMaster, Product product){
        PolicyInfo info = new PolicyInfo();
        info.setSkuRiskcode(product.getPartnerProductCode());
        info.setProductCode(product.getProductCode());
        info.setSumPrem(policyMaster.getTotalPrem());
        info.setChargeYear("");
        info.setChargePeriod("");
        info.setCoverageYear(0);
        info.setCoverageYearFlag("");
        info.setPaymantFreq("");
        
        info.setInsuredNum(policyMaster.getApplyNum());
        info.setAutoAplIndi("N");
        
        return info;
    }
    
    
    private PolicyHolder convHolder2(User user){
        PolicyHolder holder = new PolicyHolder();
        holder.setName(user.getUserName());
        holder.setCertiType(user.getCertiType());
        holder.setCertiCode(user.getCertiCode());
        
        holder.setBirthday(user.getUserBirthDay()!=null?DateUtils.formatDate(user.getUserBirthDay()):null);
        holder.setGender(user.getUserSex().equals("1") ? "M" : "F");
        holder.setEmail(user.getUserEmail());
        holder.setMobile(user.getUserMobile());

        StringBuffer address=new StringBuffer();
        if(StringUtils.isNotBlank(user.getProvince())){
            address.append(user.getProvince());
        }
        if(StringUtils.isNotBlank(user.getCity())){
            address.append(user.getCity());
        }
        if(StringUtils.isNotBlank(user.getDistrict())){
            address.append(user.getDistrict());
        }
        if(StringUtils.isNotBlank(user.getDetailedAdress())){
            address.append(user.getDetailedAdress());
        }
        holder.setAddress1(address.toString());
        holder.setZipCode(user.getZipcode());
        
        return holder;
    }
    
    private Insured convInsured(User user, String ralation){
        Insured holder = new Insured();
        holder.setRalationToPH("1");
        holder.setName(user.getUserName());
        holder.setCertiType(user.getCertiType());
        holder.setCertiCode(user.getCertiCode());

        holder.setBirthday(user.getUserBirthDay()!=null?DateUtils.formatDate(user.getUserBirthDay()):null);
        holder.setGender(user.getUserSex().equals("1") ? "M" : "F");
        holder.setEmail(user.getUserEmail());
        holder.setMobile(user.getUserMobile());

        StringBuffer address=new StringBuffer();
        if(StringUtils.isNotBlank(user.getProvince())){
            address.append(user.getProvince());
        }
        if(StringUtils.isNotBlank(user.getCity())){
            address.append(user.getCity());
        }
        if(StringUtils.isNotBlank(user.getDistrict())){
            address.append(user.getDistrict());
        }
        if(StringUtils.isNotBlank(user.getDetailedAdress())){
            address.append(user.getDetailedAdress());
        }
        holder.setAddress1(address.toString());
        holder.setPostCode(user.getZipcode());
        
        return holder;
    }
    
    
    private PolicyBenifit convBenifit(User user){
        PolicyBenifit benifit = new PolicyBenifit();
        benifit.setLegalBenifit("1");           // 默认法定继承人
        benifit.setName(user.getUserName());
        benifit.setGender(user.getUserSex().equals("1") ? "M" : "F");
        benifit.setCertiType(user.getCertiType());
        benifit.setCertiCode(user.getCertiCode());
        
        benifit.setBirthday(user.getUserBirthDay()!=null?DateUtils.formatDate(user.getUserBirthDay()):null);
        
        benifit.setDesignation("90");
        
        return benifit;
    }
    
    /**
     * 获取承保需要的 xml
     * @param policyMaster
     * @return
     */
    private String getProposalXml(PolicyMaster policyMaster) {
        String result = null;
        PaymentMethod policy = convPayInfo(policyMaster);
        Map<String, Object> root = Maps.newHashMap();
        root.put("policy", policy);
        
        try {
            result = FreeMarkerTemplateUtils.processTemplateIntoString(ProposalTemplate.getTemplate(),  root);
        } catch (Exception e) {
            logger.info("get proposal xml error.", e);
        }
        
        return result;
    }
    
    public String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    
    /**
     * 返回数据的转换
     * @param response
     * @param result
     */
    private void converResponse(InsResponse response, PolicyResult result){
        result.setFailReason(response.getFailReason());
        result.setMsg(response.getErrMsg());
        result.setOrderId(response.getOrderId());
        result.setPolicyCode(response.getPolicyNo());
        result.setRequestType(response.getRequestType());
        result.setResultCode(response.getResponseCode());
        result.setSendCode(response.getProposalNo());
        result.setUwFlag(response.getUnderwriteFlag());
        result.setPolicyUrl(response.getPolicyUrl());
        try {
            if(response.getSuccess() != null){
                boolean success = response.getSuccess().equals("1") ? true : false;
                result.setSuccess(success);
            }else if (response.getUnderwriteFlag() != null) {
                boolean success2 = response.getUnderwriteFlag().equals("1") ? true : false;
                result.setSuccess(success2);
            }
            result.setFinishTime(DateUtils.getDateByFormat(response.getSendTime(), "yyyy-MM-dd"));
        } catch (ParseException e) {
            logger.error("date convert error.", e);
        }
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

	@Override
	public PolicyResult canclePolicy(CanclePolicyRequestVo canclePolicyRequestVo) {
		return null;
	}

	@Override
	public PolicyResult canclePolicy2(CanclePolicyRequestVo canclePolicyRequestVo) {
		PolicyResult policyResult = new PolicyResult();
		policyResult.setSuccess(true);
		return policyResult;
	}

}
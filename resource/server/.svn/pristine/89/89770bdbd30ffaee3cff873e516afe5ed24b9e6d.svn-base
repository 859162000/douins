package com.douins.insurance.service.iml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.douins.account.dao.UserAccountDao;
import com.douins.account.dao.UserDao;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.bank.dao.BankResponeDao;
import com.douins.bank.domain.model.nybc.BankDict;
import com.douins.bank.domain.model.nybc.Body;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.bank.domain.model.nybc.DopatArray;
import com.douins.bank.domain.model.nybc.NyBankInfoMapping;
import com.douins.bank.domain.model.nybc.NyPayInfo;
import com.douins.bank.service.iml.NYBankService;
import com.douins.common.rsa.MD5Utils;
import com.douins.common.util.DateTimeUtils;
import com.douins.common.util.XmlUtil;
import com.douins.insurance.domain.modelLianlife.Applicant;
import com.douins.insurance.domain.modelLianlife.ApplyInfo;
import com.douins.insurance.domain.modelLianlife.ApplyInfoItem;
import com.douins.insurance.domain.modelLianlife.ApplyInfoList;
import com.douins.insurance.domain.modelLianlife.ApplyNoItem;
import com.douins.insurance.domain.modelLianlife.ApplyNoList;
import com.douins.insurance.domain.modelLianlife.Flight;
import com.douins.insurance.domain.modelLianlife.Header;
import com.douins.insurance.domain.modelLianlife.Insurant;
import com.douins.insurance.domain.modelLianlife.InsurantList;
import com.douins.insurance.domain.modelLianlife.InsuredInfo;
import com.douins.insurance.domain.modelLianlife.IssueItem;
import com.douins.insurance.domain.modelLianlife.OrderInfo;
import com.douins.insurance.domain.modelLianlife.OtherInfo;
import com.douins.insurance.domain.modelLianlife.Package;
import com.douins.insurance.domain.modelLianlife.PackageList;
import com.douins.insurance.domain.modelLianlife.Payment;
import com.douins.insurance.domain.modelLianlife.PolicyInfoItem;
import com.douins.insurance.domain.modelLianlife.PolicyInfoList;
import com.douins.insurance.domain.modelLianlife.PolicyNoItem;
import com.douins.insurance.domain.modelLianlife.PolicyNoList;
import com.douins.insurance.domain.modelLianlife.ProductInfo;
import com.douins.insurance.domain.modelLianlife.ProductList;
import com.douins.insurance.domain.modelLianlife.RefundInfoItem;
import com.douins.insurance.domain.modelLianlife.RefundInfoList;
import com.douins.insurance.domain.modelLianlife.RefundItem;
import com.douins.insurance.domain.modelLianlife.RefundList;
import com.douins.insurance.domain.modelLianlife.Request;
import com.douins.insurance.domain.modelLianlife.Response;
import com.douins.insurance.domain.modelLianlife.UnderwritingItem;
import com.douins.insurance.domain.modelLianlife.UnderwritingList;
import com.douins.insurance.service.ConvertService;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.product.dao.ProductDao;
import com.douins.product.domain.vo.ProductVo;
import com.google.common.collect.Lists;

@Service
public class ConvertServiceIpml implements ConvertService{
	private static final String ChannelType = "01";
	//private static final String AgentNum = "8632016050"; //代理人编码 for lianlife
	//private static final String AgentNum = "8631000003"; //代理人编码 for lianlife
	private static final String AgentNum = "8631000007"; //代理人编码 for lianlife
	private static final String ChannelNum = "1701";     //渠道编码   for lianlife
	private static final String ChannelReginNum = "dyjf"; //渠道方指定销售区域编码
	//private static final String ApplyType = "1";			//投保单类型
	private static final String ApplyType = "2";			//投保单类型
	private static final String PolicyAddress_Test = "http://app.lianlife.com/PROXY/BS_DEV/"; //电子保单地址 测试
	private static final String PolicyAddress_Pro = "http://app.lianlife.com/BS/"; //电子保单地址 生产
	private static final String OrgChkCode_T = "12345";       //测试环境加言签名密钥
	private static final String OrgChkCode_P = "dyjf#2016";   //生产环境加言签名密钥
	private static final Logger logger =Logger.getLogger(ConvertServiceIpml.class);

	@Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserAccountDao userAccountDao;
    
    @Autowired
	private BankResponeDao bankResponeDao;
    @Autowired
    private NYBankService nyService;
    
    @Override
	public String toConvertRequest(PolicyMaster policyMaster, String transFlag,String transType) {
    	Header header = getHeader(transFlag,transType);
		Request request = getRequest(policyMaster,transType);
		List<Package> packageLists = Lists.newArrayList();
		Package package1 = new Package();
		package1.setHeader(header);
		package1.setRequest(request);
		packageLists.add(package1);
		PackageList packageList = new PackageList();
		packageList.setPackage1(packageLists);
		String requestXml = XmlUtil.xmlSerialize(packageList, PackageList.class);
		String type ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
		return type+requestXml;
	}

	@Override
	public PolicyResult toConvertResponse(String responseXml) {
		 PackageList packageList = XmlUtil.xmlDeserialize(responseXml, PackageList.class);
		 return convertCancleResult(packageList);
	}

	private PolicyResult convertCancleResult(PackageList packageList) {
		PolicyResult policyResult = new PolicyResult();
		if (packageList != null) {
			List<Package> packageLists = packageList.getPackage1();
			if (!CollectionUtils.isEmpty(packageLists)) {
				Package package1 = packageLists.get(0);
				if (package1 != null) {
					Response response = package1.getResponse();
					Header header = package1.getHeader();
					if (header != null) {
						// 核保
						String responseCode = header.getResponseCode();
						policyResult.setResultCode(responseCode);
						policyResult.setRequestType(header.getCode());
						policyResult.setFinishTime(new Date());
						policyResult.setSuccess(false);
						if (response != null) {
							ApplyInfoList applyInfoList = response.getApplyInfoList();
							if (applyInfoList != null) {
								List<ApplyInfoItem> applyinfolists = applyInfoList.getApplyinfolist();
								if (!CollectionUtils.isEmpty(applyinfolists)) {
									ApplyInfoItem applyInfoItem = applyinfolists.get(0);
									if (applyInfoItem != null) {
										policyResult.setOrderId(applyInfoItem.getOrderid());
										String failReason = applyInfoItem.getFailReason();
										policyResult.setSuccess(StringUtils.isEmpty(failReason)?true:false);
										//policyResult.setSuccess("0".equals(responseCode) ? true: false);
										policyResult.setFailReason(applyInfoItem.getFailReason());
										ApplyNoList applyNoList = applyInfoItem.getApplyNoList();
										if(applyNoList !=null){
											List<ApplyNoItem> applynolist2 = applyNoList.getApplynolist();
											if(!CollectionUtils.isEmpty(applynolist2)){
												ApplyNoItem applyNoItem = applynolist2.get(0);
												if(applyNoItem != null){
													policyResult.setApplyno(applyNoItem.getSinglapplyno());
												}
											}
										}
									}
								}
							}
							// 承保
							PolicyInfoList policyInfoList = response.getPolicyInfoList();
							if (policyInfoList != null) {
								List<PolicyInfoItem> policyinfolists = policyInfoList.getPolicyinfolist();
								if (!CollectionUtils.isEmpty(policyinfolists)) {
									PolicyInfoItem policyInfoItem = policyinfolists.get(0);
									if (policyInfoItem != null) {
										policyResult.setOrderId(policyInfoItem.getOrderid());
										policyResult.setResultCode(responseCode);
										//policyResult.setSuccess("0".equals(responseCode) ? true: false);
										String failReason = policyInfoItem.getFailReason();
										policyResult.setSuccess(StringUtils.isEmpty(failReason)?true:false);
										policyResult.setFailReason(policyInfoItem.getFailReason());
										PolicyNoList policyNoList = policyInfoItem.getPolicyNoList();
										if(policyNoList != null){
											List<PolicyNoItem> policynolist2 = policyNoList.getPolicynolist();
											if(!CollectionUtils.isEmpty(policynolist2)){
												PolicyNoItem policyNoItem = policynolist2.get(0);
												if(policyNoItem != null){
													policyResult.setPolicyCode(policyNoItem.getSinglpolicyno());
													policyResult.setPolicyUrl(getPolicyUrl(policyNoItem.getSinglpolicyno()));
												}
											}
										}
									}
								}
							}

							// 退保
							RefundInfoList refundInfoList = response.getRefundInfoList();
							if (refundInfoList != null) {
								List<RefundInfoItem> refundInfoItems = refundInfoList.getRefunditem();
								if (!CollectionUtils.isEmpty(refundInfoItems)) {
									RefundInfoItem refundInfoItem = refundInfoItems.get(0);
									if (refundInfoItem != null) {
										policyResult.setOrderId(refundInfoItem.getPolicyno());
										policyResult.setSendTime(DateTimeUtils.getDateFromString2(refundInfoItem.getRefundtime()));
										policyResult.setResultCode(responseCode);
										policyResult.setSuccess("0".equals(responseCode) ?true : false);
										policyResult.setFailReason(refundInfoItem.getFailReason());
									}
								}
							}
						}
					}
				}
			}
		}
		return policyResult;
	}

	private String getPolicyUrl(String singlpolicyno) {
		String policyURL = PolicyAddress_Test+"Policy?channelNum="+"dyjf"+"&policyNo="+singlpolicyno+"&sign="+getMD5(singlpolicyno);
		return policyURL;
	}

	private String getMD5(String singlpolicyno) {
		// TODO Auto-generated method stub
		byte source[]= (OrgChkCode_T+singlpolicyno).getBytes();
		return  MD5Utils.lianlifeMD5(source);
	}

	private Request getRequest(PolicyMaster policyMaster, String transType) {
		Request request = new Request();
		
		switch(transType){
		case "UW":
			List<UnderwritingItem> underwritinglists = getUnderWritinglist(policyMaster);
			UnderwritingList underwritingList = new UnderwritingList();
			underwritingList.setUnderwritinglist(underwritinglists);
			request.setUnderwritingList(underwritingList);
			break;
		case "ISS":
			/*List<IssueItem> issueLists =buildIssueList(policyMaster);
			IssueList issueList = new IssueList();
			issueList.setIssueList(issueLists);
			request.setIssueList(issueList);*/
			request.setPayment(getPayment3(policyMaster));
			
			if(policyMaster.getApplyno() != null){
				request.setApplyno(policyMaster.getApplyno());
			}else{
				request.setApplyno(policyMaster.getSendCode());
			}
			break;
		case "WD":
			List<RefundItem> refundLists =buildRefundList(policyMaster);
			RefundList refundList=new RefundList();
			refundList.setRefundItem(refundLists);
			request.setRefundList(refundList);
			break;
		}
		return request;
	}
	
	private List<RefundItem> buildRefundList(PolicyMaster policyMaster) {
		 List<RefundItem> refundItems=Lists.newArrayList();
		 RefundItem refundItem = new RefundItem();
		 User user = userDao.find(policyMaster.getUserId());
		 refundItem.setApplicantName(user.getUserName());
		 refundItem.setPolicyno(policyMaster.getPolicyCode());
		 refundItem.setRefundtime(DateTimeUtils.formateDateTime(new Date()));
		 refundItems.add(refundItem);
		return refundItems;
	}

	private List<IssueItem> buildIssueList(PolicyMaster policyMaster) {
		List<IssueItem> issueLists= Lists.newArrayList();
		IssueItem issueItem = new IssueItem();
		issueItem.setApplyInfo(getApplyinfo(policyMaster));
		issueItem.setOrderInfo(getOrderinfo(policyMaster));
		issueItem.setOtherInfo(getOtherinfo(policyMaster));
		issueItem.setPayment(getPayment3(policyMaster));
		//fltNO
		issueItem.setFlight(new Flight(UUID.randomUUID().toString()));
		issueLists.add(issueItem);
		return null;
	}

	private Header getHeader(String transFlag, String transType) {
		Header header = new Header();
		if( "0".equals(transFlag)){
			switch(transType){
			case "UW":
				header.setCode("10002");
				header.setAsyn("0");
				header.setpartneridentifier("dyjf");
				header.setTime(getTime());
				header.setuuid(getUuid());
				break;
			case "ISS":
				header.setCode("20002");
				header.setAsyn("0");
				header.setPartneridentifier("dyjf");
				header.setTime(getTime());
				header.setuuid(getUuid());
				break;
			case "WD":
				header.setCode("30002");
				header.setAsyn("0");
				header.setPartneridentifier("dyjf");
				header.setTime(getTime());
				header.setuuid(getUuid());
				break;
			default:
				break;
			}
		}else{
			
		}
		return header;
	}
	
	private String getUuid() {
		return UUID.randomUUID().toString();
	}

	private String getTime() {
		  return DateTimeUtils.formateDateTime(new Date());
		}
	
	private List<UnderwritingItem> getUnderWritinglist(PolicyMaster policyMaster) {
		List<UnderwritingItem> underwritinglist = new ArrayList<UnderwritingItem>();
		underwritinglist.add(getUnderWritingItem(policyMaster));
		return underwritinglist;
	}

	private UnderwritingItem getUnderWritingItem(PolicyMaster policyMaster) {
		UnderwritingItem underritingItem = new UnderwritingItem();
		underritingItem.setApplyinfo(getApplyinfo(policyMaster));
		underritingItem.setOrderinfo(getOrderinfo(policyMaster));
		underritingItem.setOtherinfo(getOtherinfo(policyMaster));
		return underritingItem;
	}

	private OtherInfo getOtherinfo(PolicyMaster policyMaster) {
		OtherInfo otherinfo = new OtherInfo();
		otherinfo.setConindproxyno("dyjf001");
		otherinfo.setInsureno("1");
		 //网银支付的话 利安不check
		otherinfo.setPlat("YL");
		return otherinfo;
	}

	private OrderInfo getOrderinfo(PolicyMaster policyMaster) {
		OrderInfo orderinfo = new OrderInfo();
		orderinfo.setOrderid(policyMaster.getOrderNo());
		return orderinfo;
	}

	private ApplyInfo getApplyinfo(PolicyMaster policyMaster) {
		ApplyInfo applyinfo = new ApplyInfo();
		applyinfo.setAgentNum(AgentNum);
		applyinfo.setApplytype(ApplyType);
		applyinfo.setChannelnum(ChannelNum);
		applyinfo.setChannelreginnum(ChannelReginNum);
		applyinfo.setChanneltype(ChannelType);
		applyinfo.setApplydate(getTime());
		applyinfo.setEffectivedate(getTime());  
		applyinfo.setTotalpremium(policyMaster.getTotalPrem().toString());//agencyapi改为以分为单位
		applyinfo.setApplicant(getApplicant(policyMaster));
		List<ProductInfo> productlists = getProductlist(policyMaster);
		ProductList productlist =  new ProductList();
		productlist.setProductlist(productlists);
		applyinfo.setProductList(productlist);
		applyinfo.setInsuredinfo(getInsuredinfo(policyMaster));
		return applyinfo;
	}
	
	private InsuredInfo getInsuredinfo(PolicyMaster policyMaster) {
		InsuredInfo insuredInfo = new InsuredInfo();
		List<Insurant> insurantlists = new ArrayList<Insurant>();
		Insurant insurant = new Insurant();
		User user = userDao.find(policyMaster.getUserId());
		insuredInfo.setIsapplicant("1");
		
		insurant.setCellphonenumber(user.getUserMobile());
		insurant.setEmail(user.getUserEmail());
		insurant.setId(user.getCertiCode());
		insurant.setInsurantapplicantrelation("00"); //被保人与投保人关系 00-本人
		insurant.setIdtype("0");
		insurant.setName(user.getUserName());
		insurant.setSex("2".equals(user.getUserSex())?"1":"0");//利安 0-男 1-女  //dyji-1-nan 2-nv
		
		insurantlists.add(insurant);
		InsurantList insurantlist =new InsurantList();
		insurantlist.setInsurantlist(insurantlists);
		insuredInfo.setInsurantList(insurantlist);
		return insuredInfo;
	}

	private List<ProductInfo> getProductlist(PolicyMaster policyMaster) {
		List<ProductInfo> prodInfoList = new ArrayList<ProductInfo>();
		ProductInfo productInfo = new ProductInfo();
		ProductVo product = productDao.getProductVoById(policyMaster.getProductId());
		//必要信息从product中取出，等利安产品拿来没用的字段在数据库里才加
		//TODO
		//	    productInfo.setInsurancecategory("Y"); //主附险标志
		//	    productInfo.setPeriodpremium("0.122222");         //期交保费
		//		productInfo.setProductcode("TXDY0001"); 			 //保险公司定义的险种代码
		//		productInfo.setIspackage("1");						 //是否套餐
		//		productInfo.setAfterdayeffective("1");
		//		productInfo.setCalculationrule("3");
		//		productInfo.setCoverperiodtype("Y");
		//		productInfo.setEffectivedate(getTime());
		
		//		productInfo.setInsurancecoverage("10000");
		//		productInfo.setInsurancenum("20");
		//		productInfo.setInsuranceperiod("1");
		//		productInfo.setPremperiodtype("Y");
		
		productInfo.setInsurancecategory(product.getInsurancecategory());
		
		BigDecimal totalPrem = policyMaster.getTotalPrem();
		productInfo.setPeriodpremium(totalPrem+"");
		productInfo.setProductcode(product.getProductCode());
		productInfo.setIspackage(product.getIspackage());
		productInfo.setAfterdayeffective(product.getAfterdayeffective());
		productInfo.setCalculationrule(product.getCalculationrule());
		productInfo.setCoverperiodtype(product.getCoverperiodtype());
		//生效时间t+1
		DateTime plus = new DateTime().plus(1);
		productInfo.setEffectivedate(plus.toString("yyyy-MM-dd HH:mm:ss"));
		productInfo.setInsurancecoverage(totalPrem+"");//保险额度＝投保金额，以分为单位
		productInfo.setInsurancenum(policyMaster.getApplyNum()+"");
		productInfo.setInsuranceperiod(product.getInsuranceperiod());
		productInfo.setPremperiodtype(product.getPremperiodType());
		prodInfoList.add(productInfo);
		return prodInfoList;
	}

	private Applicant getApplicant(PolicyMaster policyMaster) {
		Applicant app = new Applicant();
		User user = userDao.find(policyMaster.getUserId());	
		app.setCellphonenumber(user.getUserMobile());
		app.setEmail(user.getUserEmail());
		app.setId(user.getCertiCode());
		//TODO
		app.setIdtype("0");
		app.setName(user.getUserName());
		app.setSex("2".equals(user.getUserSex())?"1":"0");
		return app;
	}

	private Payment getPayment(PolicyMaster policyMaster) {
		//TODO
		Payment payment =new Payment();
		payment.setAccountdate(DateTimeUtils.formateDateTime(policyMaster.getApplyTime()));
		payment.setBankserial("12345");
		payment.setPayBankAcount("6225884119750902");
		payment.setPaybankcode("ZFB");
		payment.setPaymentid("123456");
		payment.setPaytime(DateTimeUtils.formateDateTime(policyMaster.getApplyTime()));
		payment.setPaymoney("1000");
		payment.setPaytype("9");
		payment.setRecivebankacount("212312312312312");
		payment.setRecivebankcode("DYJF");
		payment.setRecivebankusename("利安人寿股份有限公司");
		payment.setRecivebankuserid("LianLife-ZFB-Recive");
		return payment;
	}	
	private Payment getPayment2(PolicyMaster policyMaster) {
		CallBackRequest callBackRequest =  bankResponeDao.findPayInfo(policyMaster.getChanlFlowNo());
		UserAccount userAccount = userAccountDao.findByUserId(policyMaster.getUserId());
		//TODO
		Payment payment =new Payment();
		payment.setAccountdate(DateTimeUtils.formateDateTime(policyMaster.getApplyTime()));
		Body body = callBackRequest.getBody();
		payment.setBankserial(body.getChanlNo());//银行流水号
		payment.setPayBankAcount(userAccount.getCreditCard());//付款帐号客户卡号
		payment.setPaybankcode(body.getPayAcctname());//lian识别的付款银行名称
		//
		payment.setPaymentid(body.getChanlFlowNo());//支付订单号
		payment.setPaytime(body.getTranDate());
		payment.setPaymoney(body.getAmt());
		//payment.setPaymoney(policyMaster.getTotalPrem()＋"");
		payment.setPaytype(body.getPayType());
		payment.setRecivebankcode(body.getRcvAcctname());
		payment.setRecivebankacount("448171175406");
		payment.setRecivebankusename("利安人寿股份有限公司");
		payment.setRecivebankuserid("LianLife-YL-Recive");
		return payment;
//		<ReciveBankAcount>448171175406</ReciveBankAcount>
//		<ReciveBankCode>DYJF</ReciveBankCode>
//		<ReciveBankUserId>LianLife-YL-Recive</ReciveBankUserId>
//		<ReciveBankUserName>利安人寿股份有限公司</ReciveBankUserName>
	}	

	private Payment getPayment3(PolicyMaster policyMaster) {
		String payInfo = nyService.queryPayResult(policyMaster.getChanlFlowNo());
    	NyPayInfo body = XmlUtil.xmlDeserialize(payInfo, NyPayInfo.class);
		//TODO
		Payment payment =new Payment();
		payment.setAccountdate(DateTimeUtils.formateDateTime(policyMaster.getApplyTime()));
		DopatArray dopatArray = body.getBody().getDopatArray();
		logger.info("sdo to sting :"+dopatArray.getSdos().get(0));
		payment.setBankserial(dopatArray.getSdos().get(0).getFlowNo());//银行流水号
	//	String payAcct = dopatArray.getSdos().get(0).getPayAcct();
		
		String payAcct =userAccountDao.getCardByUserId(policyMaster.getUserId());
		payment.setPayBankAcount(payAcct);//付款帐号客户卡号
		
		payment.setPaybankcode(getBankCode(payAcct));//lian识别的付款银行名称
		//TODO
		//payment.setPaybankcode(payAcct);//lian识别的付款银行名称
		payment.setPaymentid(policyMaster.getPayOrderNo());//支付订单号
		logger.info("南粤pay time+++++++++++++++++++++++++["+dopatArray.getSdos().get(0).getTranDate()+"]dyjinfu underwritindTime["+policyMaster.getUnderwritindTime()+"]");
		payment.setPaytime(DateTimeUtils.formateDateTime(policyMaster.getUnderwritindTime()));
		payment.setPaymoney(dopatArray.getSdos().get(0).getAmt());
		//payment.setPaytype(dopatArray.getSdos().get(0).getPayType());
		payment.setPaytype("9");
		payment.setRecivebankcode("DYJF");
		payment.setRecivebankacount("448171175406");
		payment.setRecivebankusename("利安人寿股份有限公司");
		payment.setRecivebankuserid("LianLife-YL-Recive");
		return payment;
//		<ReciveBankAcount>448171175406</ReciveBankAcount>
//		<ReciveBankCode>DYJF</ReciveBankCode>
//		<ReciveBankUserId>LianLife-YL-Recive</ReciveBankUserId>
//		<ReciveBankUserName>利安人寿股份有限公司</ReciveBankUserName>
	}

	private String getBankCode(String payAcct) {
	   List<String>	list =nyService.getBankName(payAcct);
		return list.get(0);
	}

	
}

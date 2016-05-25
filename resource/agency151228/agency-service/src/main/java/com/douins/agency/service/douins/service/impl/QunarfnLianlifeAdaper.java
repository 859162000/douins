package com.douins.agency.service.douins.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.douins.agency.service.channel.qunarfinance.domain.emnus.QunarfnTransType;
import com.douins.agency.service.channel.qunarfinance.domain.model.Applicant;
import com.douins.agency.service.channel.qunarfinance.domain.model.ApplyInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.channel.qunarfinance.domain.model.HeaderWdQuery;
import com.douins.agency.service.channel.qunarfinance.domain.model.OrderInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.OtherInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.PackageList;
import com.douins.agency.service.channel.qunarfinance.domain.model.Packages;
import com.douins.agency.service.channel.qunarfinance.domain.model.RefundInfoApplyItem;
import com.douins.agency.service.channel.qunarfinance.domain.model.Request;
import com.douins.agency.service.channel.qunarfinance.domain.model.Response;
import com.douins.agency.service.channel.qunarfinance.domain.model.productinfo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.IssueStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.QueryStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.QueryWDStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.ResponseVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.UWStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.WithdrawStructVo;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.common.util.Configs;
import com.douins.agency.service.common.util.DateTimeUtils;
import com.douins.agency.service.common.util.HttpClientUtils;
import com.douins.agency.service.common.util.JsonOnlineUtils;
import com.douins.agency.service.common.util.MD5Utils;
import com.douins.agency.service.common.util.XmlUtils;
import com.douins.agency.service.douins.dao.PolicyDataDao;
import com.douins.agency.service.douins.dao.QunarLiAnBankCodeMappingDao;
import com.douins.agency.service.douins.domain.enums.DouinsException;
import com.douins.agency.service.douins.domain.model.PolicyData;
import com.douins.agency.service.douins.domain.model.PolicyReservationRequest;
import com.douins.agency.service.douins.domain.model.PolicyReservationResponse;
import com.douins.agency.service.douins.service.AdapterServiceIFC;
import com.douins.agency.service.douins.service.IProductSalesService;
import com.douins.agency.service.douins.service.LianConvertServiceIFC;
import com.douins.agency.service.douins.service.database.LianDataService;
import com.douins.agency.service.insurance.lianlife.domain.vo.InsUWResponse;
import com.douins.agency.service.insurance.lianlife.domain.vo.IssueResponse;
import com.douins.agency.service.insurance.lianlife.domain.vo.WithdrawResponse;
import com.douins.agency.service.insurance.lianlife.service.lianlifeDataAnaylsis;
import com.google.common.collect.Lists;

/**
 * @ClassName: QunarfnLianlifeAdaper 
 * @author panrui
 * @Description: 去哪儿理财险与利安适配器
 * @Date 2016年03月06日 上午11:13:22 
 *
 */
@Service(Constants.QUNARFN_LIANLIFE_ADAPTER)
public class QunarfnLianlifeAdaper implements AdapterServiceIFC {
    private static final Logger logger = Logger.getLogger(QunarfnLianlifeAdaper.class);

    
    private static String url;
    private static String orgchkcode;
    private static String partnerIdentify;	
    private static File keystoreFile;
    private static File truststoreFile;
    private static String qunaChannelCode="qunafn";
    private static String dyChannelCode = "dyjf";
    
    @Resource(name = "lianDataService")
    private LianDataService lianDataService; 
    @Resource(name = "lianConvertServiceIFC")
    private LianConvertServiceIFC lianConvertServiceIFC;
    
	@Resource
	PolicyDataDao policyDataDao;
	@Resource
	private IProductSalesService productSalesService;
	
	@Resource
	private QunarLiAnBankCodeMappingDao qunarLiAnBankCodeMappingDao;
	
    static{
        url = Configs.get("url_lianlife");
        orgchkcode = Configs.get("org_checkcode");
        partnerIdentify = Configs.get("part_identify");
        logger.info("当前的LianLife URL＝"+ url);
    }
    
    public  String doUWLianlifeAgency(UWStructVo uwData,String uwXml){
		String resXml = null;
		uwXml = this.transitionXmlCode(uwXml);
		if(!uwData.getHeader().getCode().equals(QunarfnTransType.UW.getValue())){
			return uwErrorResponse(uwData, DouinsException.TRADE_TYPE_ERROR.getName());
		}
		PolicyReservationRequest dtPolicyResReq = lianConvertServiceIFC.convertPolicyReservationRequest(uwData,uwData.getHeader().getCode());
		lianDataService.savePolicyReservation(dtPolicyResReq);

		resXml = doFirstUWAgency(uwData,uwXml);     // 核保
		
		InsUWResponse uwResponse = lianlifeDataAnaylsis.getUWResponse(resXml);
		lianDataService.savePolicyReservationResponse(lianConvertServiceIFC.convertPolicyReservationResponse(uwResponse,uwData.getHeader().getCode()));
    	return resXml;
    }

	/**
	 * @author panrui
	 * @param uwData
	 * @return
	 */
	private String doFirstUWAgency(UWStructVo uwData,String uwXml) {
		// TODO Auto-generated method stub
	    String resXml = null;
    	long start1=System.currentTimeMillis();
    	
        long start2 = System.currentTimeMillis();
        logger.info("核保请求给－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+uwXml +"\n=========================");
        String xml2=null;
        String fURL = getFullURL(url,uwXml);
		logger.info("=============++++++++++++++++"+fURL+"=============++++++++++++++++");
        try {
			xml2 = HttpClientUtils.Post(fURL, uwXml, "utf-8");
			long end =System.currentTimeMillis();
			logger.info("核保请求－－利安响应：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml2 +"\n=======================");
			long end2 =System.currentTimeMillis();
			logger.info(" 核保请求发送利安前时间＝============"+(start2-start1));
			logger.info("利安响应时间＝＝＝＝==＝"+(end-start2));
			logger.info("核保请求返回去哪＝＝===＝＝"+(end2-end));
        if(xml2 == null){
        	resXml = uwErrorResponse(uwData, DouinsException.SEND_DATA_ERROR.getName());
        }
       }catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
     }
   
        resXml = xml2;
		return resXml;
	}


	public String doInsureAgency(IssueStructVo isuData,String isuXml) {
		 String resXml = null;
		 //去哪银行编码转换成利安银行编码,qunafn-dyjf
		 isuXml = this.transitionXmlCode(isuXml);
			
		// isuXml = this.transitionBankCode(isuXml);  //如果是qnfn的渠道承保请求，转换
		 
			if(!isuData.getHeader().getCode().equals(QunarfnTransType.INSURE.getValue())){
				return insureErrorResponse(isuData, DouinsException.TRADE_TYPE_ERROR.getName());
			}
		 
			PolicyReservationRequest dtPolicyResReq = lianConvertServiceIFC.convertPolicyReservationRequest(isuData,isuData.getHeader().getCode());
			lianDataService.savePolicyReservation(dtPolicyResReq);
			resXml = excInsure(isuData,isuXml);
			IssueResponse issResponse = lianlifeDataAnaylsis.getIssueResponse(resXml);
			PolicyReservationResponse record = lianConvertServiceIFC.convertPolicyReservationResponse(issResponse,issResponse.getHeader().getCode());
			lianDataService.savePolicyReservationResponse(record);
			logger.info("利安承保成功保存数据，对象中标识承保成功的字段是否有值：record.getIsSuccess()------------------"+record.getIsSuccess());
			//modify by jjy 2016-05-05 如果承保成功则更新产品的销售总额
			if("1".equals(record.getIsSuccess())){
				logger.info("承保成功后要更新产品销售总额表了！承保金额："+record.getTotalPremium()+"分");
				productSalesService.saveProductSalesWithISS(record.getOrderId(), record.getTotalPremium());
			}
			return resXml;
	}

	private String excInsure(IssueStructVo isuData, String isuXml) {
			// TODO Auto-generated method stub
			String resXml=null;
	    	long start1=System.currentTimeMillis();	    	
	        long start2 = System.currentTimeMillis();
	        logger.info("承保请求给－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+isuXml +"\n=========================");
	        String xml2=null;
	        String fURL = getFullURL(url,isuXml);
			try {
				xml2 = HttpClientUtils.Post(fURL, isuXml, "utf-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        long end =System.currentTimeMillis();
	        logger.info("承保请求－－利安响应：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml2 +"\n=======================");
	        long end2 =System.currentTimeMillis();
	        logger.info(" 承保请求发送利安前时间＝============"+(start2-start1));
	        logger.info("利安响应时间＝＝＝＝==＝"+(end-start2));
	        logger.info("承保请求返回去哪FN＝＝===＝＝"+(end2-end));
	        if(xml2 == null){
	//          resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.BACK_NULL));
	          resXml = insureErrorResponse(isuData, DouinsException.SEND_DATA_ERROR.getName());
	      }else{
	            resXml = xml2;
	      }
        return resXml;
	}

	public String doWithdarwAgency(WithdrawStructVo wdData,String wdXml){
		String resXml = null;
		wdXml = this.transitionXmlCode(wdXml);
		if(wdData.getHeader().getCode().equals(QunarfnTransType.WITHDRAW.getValue())){
			return withdrawErrorResponse(wdData,DouinsException.TRADE_TYPE_ERROR.getName());
		}
		PolicyReservationRequest dtPolicyResReq = lianConvertServiceIFC.convertPolicyReservationRequest(wdData,wdData.getHeader().getCode());
		lianDataService.savePolicyReservation(dtPolicyResReq);
		resXml = excWithdraw(wdData,wdXml);
		WithdrawResponse wdResponse = lianlifeDataAnaylsis.getWithdrawResponse(resXml);
		PolicyReservationResponse record = lianConvertServiceIFC.convertPolicyReservationResponse(wdResponse, wdData.getHeader().getCode());
    	lianDataService.savePolicyReservationResponse(record);
    	//modify by jjy 2016-05-05 如果退保成功则更新产品的销售总额
		if("1".equals(record.getIsSuccess())){
			logger.info("退保成功后要更新产品销售总额表了！退保金额："+record.getRefundAmount()+"分");
			productSalesService.saveProductSalesWithWD(record.getPolicyNo(),record.getRefundAmount());
		}
		return resXml;
	}
	
	private String excWithdraw(WithdrawStructVo wdData, String wdXml) {
		// TODO Auto-generated method stub
		String resXml=null;
		long start1=System.currentTimeMillis();
    	
        long start2 = System.currentTimeMillis();
        logger.info("退保请求给－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+wdXml +"\n=========================");
        String xml2=null;
        String fURL = getFullURL(url,wdXml);
		try {
			xml2 = HttpClientUtils.Post(fURL, wdXml, "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long end =System.currentTimeMillis();
        logger.info("退保请求－－利安响应：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml2 +"\n=======================");
        long end2 =System.currentTimeMillis();
        logger.info(" 退保请求发送利安前时间＝============"+(start2-start1));
        logger.info("利安响应时间＝＝＝＝==＝"+(end-start2));
        logger.info("退保请求返回去哪FN＝＝===＝＝"+(end2-end));
        if(xml2 == null){
        	resXml = withdrawErrorResponse(wdData,DouinsException.SEND_DATA_ERROR.getName());
        }else{
            resXml = xml2;
        }
		return resXml;
	}

	public String doQueryAgency(QueryStructVo qyData,String qyXml){
    	long start1=System.currentTimeMillis();
    	
        long start2 = System.currentTimeMillis();
        logger.info("查询请求给－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+qyXml +"\n=========================");
    	String xml=gennrateXml(qyData);
//        String fURL = getFullURL(url,qyXml);
//		try {
//			xml = HttpClientUtils.Post(fURL, qyXml, "utf-8");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    long end =System.currentTimeMillis();
//        logger.info("查询请求－－利安响应：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml +"\n=======================");
//        long end2 =System.currentTimeMillis();
//        logger.info(" 查询请求发送利安前时间＝============"+(start2-start1));
//        logger.info("利安响应时间＝＝＝＝==＝"+(end-start2));
//        logger.info("查询请求返回去哪FN＝＝===＝＝"+(end2-end));
//        if(xml == null){
//        	xml = queryErrorResponse(qyData, DouinsException.SEND_DATA_ERROR.getName());
//        }
		return xml;
	}
	//9325000000037720
	public String gennrateXml(QueryStructVo qyData) {
		List<String> policyNos=Lists.newArrayList();
		policyNos.add(qyData.getPolicyno());
		List<PolicyData> policyDatas = lianDataService.queryPolicy(policyNos);
		
		PackageList packageList = new PackageList();
		
		Packages packages = new Packages();
		Header header =new Header();
		header.setCode("40001");
		header.setAsyn("0");
		header.setpartneridentifier("dyjf");
		header.setTime(getTime());
		header.setuuid(getUuid());
		header.setAckuuid(getUuid());
		packages.setHeader(header);
		
		Response response=new Response();
		PolicyData policyData=new PolicyData();
		if(!CollectionUtils.isEmpty(policyDatas)){
			 policyData = policyDatas.get(0);
			 if(policyData !=null){
					response.setApplyNo(policyData.getApplyNo());
					response.setComments(policyData.getComments());
					response.setIdNo(policyData.getIdNo());
					response.setInterestEffectiveDate(policyData.getInterestEffectiveDate());
					response.setPolicyEffectiveDate(policyData.getPolicyEffectiveDate());
					response.setPrinciple(policyData.getPrinciple());
					response.setTotalIncome(policyData.getTotalIncome());
					response.setTotalPremium(policyData.getTotalPremium());
				}else{
					response.setError("保单号不存在");
				}
		}
		
		packages.setResponse(response);
		
		packageList.setPackages(packages);
		return XmlUtils.xmlSerialize(packageList, PackageList.class);
	}
    
	private String getUuid() {
		return UUID.randomUUID().toString();
	}

	private String getTime() {
		  return DateTimeUtils.formateDateTime(new Date());
		}
	private String queryErrorResponse(QueryStructVo qyData, String name) {
		// TODO Auto-generated method stub
		ResponseVo qy = new ResponseVo();
		qy.setResponsecode("1");
		qy.setCode(qyData.getHeader().getCode());
		qy.setUuid(qyData.getHeader().getuuid());
		qy.setAckuuid(qyData.getHeader().getAckuuid());
		qy.setTime(qyData.getHeader().getTime());
		String reXml = qy.toString();
		logger.info("查询请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n========================");
		return reXml;
	}

	private String withdrawErrorResponse(WithdrawStructVo wdData, String name) {
		// TODO Auto-generated method stub
		ResponseVo wd = new ResponseVo();
		wd.setResponsecode("1");
		wd.setCode(wdData.getHeader().getCode());
		wd.setUuid(wdData.getHeader().getuuid());
		wd.setAckuuid(wdData.getHeader().getAckuuid());
		wd.setTime(wdData.getHeader().getTime());
		String reXml = wd.toString();
		logger.info("退保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n========================");
        return reXml;
	}

	private String uwErrorResponse(UWStructVo uwData, String name) {
		// TODO Auto-generated method stub
		ResponseVo uw = new ResponseVo();
		uw.setResponsecode("1");
		uw.setCode(uwData.getHeader().getCode());
		uw.setUuid(uwData.getHeader().getuuid());
		uw.setTime(uwData.getHeader().getTime());
		uw.setAckuuid(uwData.getHeader().getAckuuid());
		String reXml = uw.toString();
		logger.info("核保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n========================");
        return reXml;
	}
	
	
	private String insureErrorResponse(IssueStructVo isuData, String name) {
		// TODO Auto-generated method stub
		ResponseVo isu = new ResponseVo();
		isu.setResponsecode("1");
		isu.setCode(isuData.getHeader().getCode());
		isu.setUuid(isuData.getHeader().getuuid());
		isu.setAckuuid(isuData.getHeader().getAckuuid());
		isu.setTime(isuData.getHeader().getTime());
		String reXml = isu.toString();
		logger.info("承保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n========================");
        return reXml;
	}
	
	
	@Override
	public <T> String doUW(T data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> String doInsure(T data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String doWithdraw(T data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String applyWithdraw(T data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doBalance(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getFullURL(String url2,String xml) {
		// TODO Auto-generated method stub
		//String xmlMd5 = MD5Utils.string2MD5(orgchkcode+xml);

		//byte source[]= (orgchkcode+replaceBlank(xml)).getBytes();
		//String orgchkcode_tmp = "12345";
		//String partnerIdentify_tmp = "dyjf";

		byte source[]= (orgchkcode+xml).getBytes();
		//byte source[]= (orgchkcode+xml).getBytes();


		//if("".equals(url2) || null == url2){
		//	url2 = "http://app.lianlife.com/PROXY/CGW_DEV/";
		//}
		String xmlMd5 = MD5Utils.lianlifeMD5(source);
		//String fUrl = url2 + "Transaction?PARTNER_IDENTIFIER="+partnerIdentify_tmp+"&CHARSET=UTF-8&TRACE_STATE=Y&sign="+ xmlMd5;
		String fUrl =  url2+"Transaction?PARTNER_IDENTIFIER="+partnerIdentify+"&CHARSET=UTF-8&TRACE_STATE=Y&sign="+ xmlMd5;
		return fUrl;
	}
	
	 
	private PolicyReservationRequest convertPolicyReservationRequest(UWStructVo uwData) {
		PolicyReservationRequest dtPolicyResReq = new PolicyReservationRequest();
		Header header = uwData.getHeader();
		if(header !=null){
			dtPolicyResReq.setTradeCode(header.getCode());
			dtPolicyResReq.setAsyn(header.getAsyn());
			dtPolicyResReq.setPartnerIdentifier(header.getpartneridentifier());
			dtPolicyResReq.setRequestTime(header.getTime());
			dtPolicyResReq.setUuid(header.getuuid());
		}
		ApplyInfo applyInfo = uwData.getApplyInfo();
		dtPolicyResReq.setAgentNum(applyInfo.getagentnum());
		dtPolicyResReq.setApplyDate(applyInfo.getapplydate());
		dtPolicyResReq.setApplyType(applyInfo.getapplytype());
		dtPolicyResReq.setChannelNum(applyInfo.getchannelnum());
		dtPolicyResReq.setChannelReginNum(applyInfo.getchannelreginnum());
		dtPolicyResReq.setChannelType(applyInfo.getchanneltype());
		dtPolicyResReq.setPolicyEffectiveDate(applyInfo.geteffectivedate());
		dtPolicyResReq.setTotalPremium(applyInfo.gettotalpremium());
		//TODO
		Applicant applicant = applyInfo.getApplicant();
		if(applicant!=null){
			dtPolicyResReq.setApplicantCellphoneNumber(applicant.getcellphonenumber());				
			dtPolicyResReq.setEmail(applicant.getEmail());			
			dtPolicyResReq.setCertificateTypeId(applicant.getidtype());	
			dtPolicyResReq.setCertificateId(applicant.getid());
			dtPolicyResReq.setUserName(applicant.getName());			
			dtPolicyResReq.setSex(applicant.getSex());
		}
		List<productinfo> getproductlist = applyInfo.getproductlist();
		if(!CollectionUtils.isEmpty(getproductlist)){
			productinfo productinfo = getproductlist.get(0);
			if(productinfo!=null){
				dtPolicyResReq.setProductCode(productinfo.getproductcode());
				dtPolicyResReq.setProductEffectiveDate(productinfo.geteffectivedate());
				dtPolicyResReq.setAfterDayEffective(productinfo.getafterdayeffective());
				dtPolicyResReq.setInsuranceCategory(productinfo.getinsurancecategory());
				dtPolicyResReq.setInsuranceClass(productinfo.getInsuranceclass());
				dtPolicyResReq.setInsurancePeriod(productinfo.getinsuranceperiod());
				dtPolicyResReq.setIsPackage(productinfo.getispackage());
				dtPolicyResReq.setPeriodPremium(productinfo.getperiodpremium());
				dtPolicyResReq.setPremperiodType(productinfo.getcoverperiodtype());
				dtPolicyResReq.setCoverPeriodType(productinfo.getcoverperiodtype());
				dtPolicyResReq.setCalculationRule(productinfo.getcalculationrule());
				dtPolicyResReq.setInsuranceNum(productinfo.getinsurancenum());
			}
		}
		OrderInfo orderInfo = uwData.getOrderInfo();
		if(orderInfo!=null){
			dtPolicyResReq.setOrderId(orderInfo.getorderid());
		}
		OtherInfo otherInfo = uwData.getOtherInfo();
		dtPolicyResReq.setProperties(JsonOnlineUtils.bean2json(otherInfo));
		return dtPolicyResReq;
	}
	
	public void setLianDataService(LianDataService lianDataService) {
		this.lianDataService = lianDataService;
	}

	public String doQueryWDAgency(QueryWDStructVo queryWDReq, String queryWDXml) {
		// TODO Auto-generated method stub
		if(queryWDReq.getHeader().getCode().equals(QunarfnTransType.QUERYWD.getValue())){
			//return QueryWDErrorResponse(queryWDReq,DouinsException.TRADE_TYPE_ERROR.getName());
		}
		String resXml =  excQueryWD(queryWDReq,queryWDXml);
		logger.info("退保查询请求返回给去哪FN数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+resXml +"\n========================");
		return resXml;
	}

	private String excQueryWD(QueryWDStructVo queryWDReq, String queryWDXml) {
		// TODO Auto-generated method stub
		String type ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
		Packages package1 = new Packages();
		
		HeaderWdQuery header = getQueryWDRespHeader(queryWDReq.getHeader());
		Response response = getQueryWDResp(queryWDReq.getRequest());
		
		package1.setHeaderResp(header);;
		package1.setResponse(response);
		PackageList packageList = new PackageList();
		packageList.setPackages(package1);
		String xml = XmlUtils.xmlSerialize(packageList, PackageList.class);
		return type+xml;
	}

	private Response getQueryWDResp(Request request) {
		// TODO Auto-generated method stub
		Response resp = new Response();
		resp.setRefundInfoApplyItem(getrefundInfoItem(request));
		return resp;
	}

	private RefundInfoApplyItem getrefundInfoItem(Request request) {
		// TODO Auto-generated method stub
		RefundInfoApplyItem refundInfoApplyItem = new RefundInfoApplyItem();

		List<PolicyData> PolicyDatalist = new ArrayList<PolicyData>();
		PolicyData policyDat = new PolicyData();
		//PolicyDatalist = policyDataDao.queryWDInfo(request.getRefundinfoapplyitem().getPolicyno());
		List<String> policyNos = new ArrayList<String>();
		policyNos.add(request.getRefundinfoapplyitem().getPolicyno());
		PolicyDatalist = lianDataService.queryPolicy(policyNos);
		
		if(PolicyDatalist.size()>0 && PolicyDatalist.get(0).getPolicyNo() != null){
			refundInfoApplyItem.setRefundamount(PolicyDatalist.get(0).getWdPremium());
			refundInfoApplyItem.setFee(PolicyDatalist.get(0).getWdPoundage());
			refundInfoApplyItem.setIssuccess("1");
		}else{
			policyDat = findResponesByPolicy(request.getRefundinfoapplyitem().getPolicyno());
			if(policyDat != null){
				//refundInfoApplyItem.setRefundamount(policyDat.getWdPremium());
				//refundInfoApplyItem.setFee(policyDat.getWdPoundage());
				//refundInfoApplyItem.setIssuccess("1");
				refundInfoApplyItem.setFailreason("保单不可当日退保");
				refundInfoApplyItem.setIssuccess("0");
			}else{
				refundInfoApplyItem.setFailreason("policyNo do not exist");
				refundInfoApplyItem.setIssuccess("0");
			}
		}
		return refundInfoApplyItem;
	}

	private PolicyData findResponesByPolicy(String policyNo) {
		// TODO Auto-generated method stub
		PolicyReservationResponse resp = new PolicyReservationResponse();
		PolicyData policyData = new PolicyData();
		resp = lianDataService.getResponesByPolicy(policyNo);
		if(resp != null){
			policyData.setWdPoundage("0");
			policyData.setWdPremium(resp.getTotalPremium());
			policyData.setCreateTime(resp.getCreateTime().toString());
		return policyData;
		}else{
			return null;
		}
	}

	private HeaderWdQuery getQueryWDRespHeader(Header header) {
		// TODO Auto-generated method stub
		HeaderWdQuery headerResp = new HeaderWdQuery();
		headerResp.setResponseCode("0");
		headerResp.setAckuuid(header.getuuid());
		headerResp.setAsyn("0");
		headerResp.setCode(header.getCode());
		headerResp.setCost(header.getCost());
		headerResp.setUuid(getUuid());
		headerResp.setTime(getTime());
		return headerResp;
	}

	/*
	 * 渠道号变更qunafn——dyjf
	 */
	private String transitionXmlCode(String data){
		Document doc = Jsoup.parse(data, "", Parser.xmlParser());
		String qunaCode = doc.select("partneridentifier").text().trim();
		
		String tradeCode = doc.select("code").text().trim();
		
		if(qunaChannelCode.equals(qunaCode)){
			data = data.replace(qunaCode, dyChannelCode);
			if("20002".equals(tradeCode)){
				data = transitionBankCode(data);
			}
		}
		return data;
	}
	
	/*
	 * 去哪银行编码与利安银行编码转换
	 */
	private String transitionBankCode(String data){
		Document doc = Jsoup.parse(data, "", Parser.xmlParser());
		String qunaCode = doc.select("paybankcode").text().trim();
		String lianCode="";
		if(qunaCode != null && qunaCode != ""){
//			switch (qunaCode) {
//			case "中国农业银行":
//				lianCode="0401";
//				break;
//			case "澳大利亚和新西兰银行":
//				lianCode="24001";
//				break;
//			case "美国银行有限公司":
//				lianCode="20401";
//				break;
//			case "交通银行":
//				lianCode="0601";
//				break;
//			case "青海银行":
//				lianCode="34701";
//				break;
//			case "北京银行":
//				lianCode="2701";
//				break;
//			case "北京农村商业银行":
//				lianCode="18901";
//				break;
//			case "蒙特利尔银行（中国）有限公司":
//				lianCode="23901";
//				break;
//			case "法国巴黎银行(中国)":
//				lianCode="24501";
//				break;
//			case "加拿大丰业银行有限公司":
//				lianCode="23801";
//				break;
//			case "中国银行":
//				lianCode="0301";
//				break;
//			case " 内蒙古银行":
//				lianCode="34201";
//				break;
//			case "东方汇理银行（中国）有限公司":
//				lianCode="22701";
//				break;
//			case "渤海银行":
//				lianCode="13601";
//				break;
//			case "中国建设银行":
//				lianCode="0501";
//				break;
//			case "光大银行":
//				lianCode="1901";
//				break;
//			case "南充市商业银行":
//				lianCode="29501";
//				break;
//			case "长安银行":
//				lianCode="32301";
//				break;
//			case "创兴银行有限公司":
//				lianCode="19701";
//				break;
//			case "兴业银行":
//				lianCode="1301";
//				break;
//			case "中信银行":
//				lianCode="1201";
//				break;
//			case "招商银行":
//				lianCode="0801";
//				break;
//			case "中国民生银行":
//				lianCode="0901";
//				break;
//			case "华商银行":
//				lianCode="24601";
//				break;
//			case "重庆银行":
//				lianCode="35601";
//				break;
//			case "重庆三峡银行":
//				lianCode="35501";
//				break;
//			case "集友银行有限公司":
//				lianCode="19601";
//				break;
//			case "浙商银行":
//				lianCode="13401";
//				break;
//			case "沧州银行":
//				lianCode="32201";
//				break;
//			case "大新银行（中国）有限公司":
//				lianCode="20201";
//				break;
//			case "大新银行":
//				lianCode="20201";
//				break;
//			case "大连银行":
//				lianCode="3901";
//				break;
//			case "德意志银行（中国）有限公司":
//				lianCode="23001";
//				break;
//			case "星展银行（中国）有限公司":
//				lianCode="19801";
//				break;
//			case "达州市商业银行":
//				lianCode="28201";
//				break;
//			case "德州银行":
//				lianCode="32501";
//				break;
//			case "恒丰银行":
//				lianCode="13201";
//				break;
//			case "福建农村信用社":
//				lianCode="3501";
//				break;
//			case "华一银行":
//				lianCode="24701";
//				break;
//			case "深圳福田银座村镇银行":
//				lianCode="15401";
//				break;
//			case "富滇银行":
//				lianCode="32801";
//				break;
//			case "广东发展银行":
//				lianCode="2001";
//				break;
//			case "广东南粤银行":
//				lianCode="13001";
//				break;
//			case "广东华兴银行":
//				lianCode="12901";
//				break;
//			case "贵阳银行":
//				lianCode="3301";
//				break;
//			case "韩亚银行":
//				lianCode="21501";
//				break;
//			case "鹤壁银行":
//				lianCode="33101";
//				break;
//			case "河北银行":
//				lianCode="33001";
//				break;
//			case "东亚银行":
//				lianCode="19201";
//				break;
//			case "韩亚银行(中国)有限公司":
//				lianCode="21501";
//				break;
//			case "徽商银行":
//				lianCode="13801";
//				break;
//			case "衡水市商业银行":
//				lianCode="28801";
//				break;
//			case "工商银行":
//				lianCode="0201";
//				break;
//			case "中国工商银行":
//				lianCode="0201";
//				break;
//			case "荷兰安智银行股份有限公司":
//				lianCode="22301";
//				break;
//			case "意大利联合圣保罗银行":
//				lianCode="23501";
//				break;
//			case "晋城银行":
//				lianCode="33801";
//				break;
//			case "济宁银行股份有限公司":
//				lianCode="33501";
//				break;
//			case "摩根大通银行(中国)有限公司":
//				lianCode="20501";
//				break;
//			case "江苏银行":
//				lianCode="33601";
//				break;
//			case "江西农村信用社":
//				lianCode="3201";
//				break;
//			case "晋中市商业银行":
//				lianCode="29001";
//				break;
//			case "比利时联合银行股份有限公司":
//				lianCode="22001";
//				break;
//			case "韩国产业银行上海分行":
//				lianCode="21201";
//				break;
//			case "昆仑银行":
//				lianCode="33901";
//				break;
//			case "漯河商行":
//				lianCode="32001";
//				break;
//			case "龙江银行":
//				lianCode="34101";
//				break;
//			case "凉山州商业银行":
//				lianCode="29301";
//				break;
//			case "柳州银行":
//				lianCode="34001";
//				break;
//			case "瑞穗实业银行（中国）有限公司":
//				lianCode="20801";
//				break;
//			case "摩根士丹利国际银行":
//				lianCode="24101";
//				break;
//			case "宁波银行":
//				lianCode="2601";
//				break;	
//			case "南昌银行":
//				lianCode="2301";
//				break;
//			case "南京银行":
//				lianCode="1501";
//				break;
//			case "德国北德意志州银行":
//				lianCode="23401";
//				break;
//			case "法国外贸银行股份有限公司":
//				lianCode="22801";
//				break;
//			case "华侨银行(中国)有限公司":
//				lianCode="21601";
//				break;
//			case "鄂尔多斯银行":
//				lianCode="32601";
//				break;	
//			case "平顶山银行":
//				lianCode="34501";
//				break;
//			case "深圳平安银行":
//				lianCode="34401";
//				break;
//			case "中国邮政":
//				lianCode="19001";
//				break;
//			case "齐鲁银行":
//				lianCode="34601";
//				break;
//			case "曲靖市商业银行":
//				lianCode="29801";
//				break;
//			case "泉州银行":
//				lianCode="2201";
//				break;
//			case "苏格兰皇家银行（中国）有限公司":
//				lianCode="22501";
//				break;
//			case "荷兰合作银行有限公司":
//				lianCode="24301";
//				break;
//			case "渣打银行":
//				lianCode="22401";
//				break;
//			case "法国兴业银行(中国)有限公司":
//				lianCode="22601";
//				break;
//			case "上海银行":
//				lianCode="4401";
//				break;
//			case "新韩银行":
//				lianCode="21301";
//				break;
//			case "新韩银行(中国)有限公司":
//				lianCode="21301";
//				break;
//			case "盛京银行":
//				lianCode="34901";
//				break;
//			case "三井住友银行(中国)有限公司":
//				lianCode="20701";
//				break;
//			case "遂宁市商业银行":
//				lianCode="30101";
//				break;
//			case "上海浦东发展银行":
//				lianCode="4301";
//				break;
//			case "石嘴山银行":
//				lianCode="35001";
//				break;
//			case "三菱东京日联银行(中国）有限公司":
//				lianCode="20601";
//				break;
//			case "唐山市商业银行":
//				lianCode="30301";
//				break;
//			case "大华银行":
//				lianCode="21701";
//				break;
//			case "大华银行（中国）有限公司":
//				lianCode="21701";
//				break;
//			case "乌鲁木齐市商业银行":
//				lianCode="6801";
//				break;
//			case "友利银行(中国)有限公司":
//				lianCode="21101";
//				break;
//			case "永亨银行":
//				lianCode="20001";
//				break;
//			case "永亨银行（中国）有限公司":
//				lianCode="20001";
//				break;
//			case "友利银行":
//				lianCode="21101";
//				break;
//			case "乌海银行":
//				lianCode="35101";
//				break;
//			case "厦门国际银行":
//				lianCode="24401";
//				break;
//			case "库尔勒市商业银行":
//				lianCode="29201";
//				break;
//			case "邢台银行":
//				lianCode="35401";
//				break;
//			case "雅安市商业银行":
//				lianCode="30801";
//				break;
//			case "宜宾市商业银行":
//				lianCode="31001";
//				break;
//			case "宁夏银行":
//				lianCode="34301";
//				break;
//			case "银川市商业银行":
//				lianCode="7701";
//				break;
//			case "宜昌市商业银行":
//				lianCode="7301";
//				break;
//			case "日本山口银行股份有限公司":
//				lianCode="20901";
//				break;
//			case "阳泉市商业银行":
//				lianCode="30901";
//				break;
//			case "玉溪市商业银行":
//				lianCode="31101";
//				break;
//			case "自贡市商业银行":
//				lianCode="31801";
//				break;
//			case "浙江稠州商业银行":
//				lianCode="31401";
//				break;
//			case "浙江民泰商业银行":
//				lianCode="31501";
//				break;
//			case "遵义市商业银行":
//				lianCode="31901";
//				break;
//			case "郑州银行股份有限公司":
//				lianCode="2801";
//				break;			
//			}
			lianCode= qunarLiAnBankCodeMappingDao.selectLiAnCodeByBankName(qunaCode);
			if(lianCode != null){
			   data = data.replace(qunaCode, lianCode);
			}
		}		
		return data;
	}
}

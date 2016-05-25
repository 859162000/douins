package com.douins.agency.service.douins.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.douins.agency.service.channel.qunarfinance.domain.emnus.QunarfnTransType;
import com.douins.agency.service.channel.qunarfinance.domain.model.Applicant;
import com.douins.agency.service.channel.qunarfinance.domain.model.ApplyInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.channel.qunarfinance.domain.model.OrderInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.OtherInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.PackageList;
import com.douins.agency.service.channel.qunarfinance.domain.model.Packages;
import com.douins.agency.service.channel.qunarfinance.domain.model.Response;
import com.douins.agency.service.channel.qunarfinance.domain.model.productinfo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.IssueStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.QueryStructVo;
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
import com.douins.agency.service.douins.domain.enums.DouinsException;
import com.douins.agency.service.douins.domain.model.PolicyData;
import com.douins.agency.service.douins.domain.model.PolicyReservationRequest;
import com.douins.agency.service.douins.service.AdapterServiceIFC;
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
    
    @Resource(name = "lianDataService")
    private LianDataService lianDataService; 
    @Resource(name = "lianConvertServiceIFC")
    private LianConvertServiceIFC lianConvertServiceIFC; 
  
    static{
        url = Configs.get("url_lianlife");
        orgchkcode = Configs.get("org_checkcode");
        partnerIdentify = Configs.get("part_identify");
        logger.info("当前的LianLife URL＝"+ url);
    }
    
    public  String doUWLianlifeAgency(UWStructVo uwData,String uwXml){
		String resXml = null;
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
		 
			if(!isuData.getHeader().getCode().equals(QunarfnTransType.INSURE.getValue())){
				return insureErrorResponse(isuData, DouinsException.TRADE_TYPE_ERROR.getName());
			}
		 
			PolicyReservationRequest dtPolicyResReq = lianConvertServiceIFC.convertPolicyReservationRequest(isuData,isuData.getHeader().getCode());
			lianDataService.savePolicyReservation(dtPolicyResReq);
			resXml = excInsure(isuData,isuXml);
			IssueResponse issResponse = lianlifeDataAnaylsis.getIssueResponse(resXml);
			lianDataService.savePolicyReservationResponse(lianConvertServiceIFC.convertPolicyReservationResponse(issResponse,issResponse.getHeader().getCode()));
			
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
		
		if(wdData.getHeader().getCode().equals(QunarfnTransType.WITHDRAW.getValue())){
			return withdrawErrorResponse(wdData,DouinsException.TRADE_TYPE_ERROR.getName());
		}
		PolicyReservationRequest dtPolicyResReq = lianConvertServiceIFC.convertPolicyReservationRequest(wdData,wdData.getHeader().getCode());
		lianDataService.savePolicyReservation(dtPolicyResReq);
		resXml = excWithdraw(wdData,wdXml);
		WithdrawResponse wdResponse = lianlifeDataAnaylsis.getWithdrawResponse(resXml);
    	lianDataService.savePolicyReservationResponse(lianConvertServiceIFC.convertPolicyReservationResponse(wdResponse, wdData.getHeader().getCode()));
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
		String orgchkcode_tmp = "12345";
		String partnerIdentify_tmp = "dyjf";

		byte source[]= (orgchkcode_tmp+xml).getBytes();
		//byte source[]= (orgchkcode+xml).getBytes();


		//if("".equals(url2) || null == url2){
		//	url2 = "http://app.lianlife.com/PROXY/CGW_DEV/";
		//}
		String xmlMd5 = MD5Utils.lianlifeMD5(source);
		//String fUrl = url2 + "Transaction?PARTNER_IDENTIFIER="+partnerIdentify_tmp+"&CHARSET=UTF-8&TRACE_STATE=Y&sign="+ xmlMd5;

//		String fUrl =  "http://app.lianlife.com/PROXY/CGW_DEV/"+"Transaction?PARTNER_IDENTIFIER="+"dyjf"+"&CHARSET=UTF-8&TRACE_STATE=Y&sign="+ xmlMd5;
		String fUrl =  "http://cgw.lianlife.com/UAT/CGW/"+"Transaction?PARTNER_IDENTIFIER="+"dyjf"+"&CHARSET=UTF-8&TRACE_STATE=Y&sign="+ xmlMd5;

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
}

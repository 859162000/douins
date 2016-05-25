package com.douins.agency.service.douins.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.douins.agency.service.channel.qunarfinance.domain.model.Applicant;
import com.douins.agency.service.channel.qunarfinance.domain.model.ApplyInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.channel.qunarfinance.domain.model.Insurant;
import com.douins.agency.service.channel.qunarfinance.domain.model.InsuredInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.OrderInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.OtherInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.Payment;
import com.douins.agency.service.channel.qunarfinance.domain.model.RefundItem;
import com.douins.agency.service.channel.qunarfinance.domain.model.productinfo;
import com.douins.agency.service.common.util.Configs;
import com.douins.agency.service.common.util.HttpClientThreadUtil;
import com.douins.agency.service.common.util.JsonOnlineUtils;
import com.douins.agency.service.douins.dao.PolicyDataDao;
import com.douins.agency.service.douins.domain.model.PolicyData;
import com.douins.agency.service.douins.domain.model.PolicyReservationRequest;
import com.douins.agency.service.douins.domain.model.PolicyReservationResponse;
import com.douins.agency.service.douins.service.LianConvertServiceIFC;
import com.douins.agency.service.insurance.lianlife.domain.model.ApplyInfoItem;
import com.douins.agency.service.insurance.lianlife.domain.model.PolicyInfoItem;
import com.douins.agency.service.insurance.lianlife.domain.model.RefundInfoItem;
import com.douins.agency.service.channel.qunarfinance.domain.vo.IssueStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.UWStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.WithdrawStructVo;
import com.douins.agency.service.insurance.lianlife.domain.vo.InsUWResponse;
import com.douins.agency.service.insurance.lianlife.domain.vo.IssueResponse;
import com.douins.agency.service.insurance.lianlife.domain.vo.WithdrawResponse;



@Service("lianConvertServiceIFC")
public class LianConvertServiceImpl implements LianConvertServiceIFC {
	
	private static final Logger logger = Logger.getLogger(QunarCcicAdapter.class);
	@Autowired
	private PolicyDataDao policyDataDao;
	
	@Override
	public <T>PolicyReservationResponse convertPolicyReservationResponse(T data,String code) {
		PolicyReservationResponse policyResResponse = new PolicyReservationResponse();
			
			switch (code){
				case "10002":
					InsUWResponse uwResponse = (InsUWResponse)data;
					Header header = uwResponse.getHeader();
					policyResResponse.setTradeCode(header.getCode());
					policyResResponse.setResponseTime(header.getTime());
					policyResResponse.setUuid(header.getuuid());
					policyResResponse.setAckUuid(header.getAckuuid());;
					policyResResponse.setResponseCode(header.getResponseCode());
					
					List<ApplyInfoItem> listApplyInfo = uwResponse.getListApplyInfoItem();
					if(listApplyInfo.size() >0){
						for(int i=0;i < listApplyInfo.size();i++){
						 if(listApplyInfo.get(i) != null){
							policyResResponse.setAccountDate(listApplyInfo.get(i).getaccountdate());
							policyResResponse.setOrderId(listApplyInfo.get(i).getorderid());
							policyResResponse.setTotalPremium(listApplyInfo.get(i).gettotalpremium());
							policyResResponse.setErrorMsg(listApplyInfo.get(i).getFailreason());
							policyResResponse.setUserName(listApplyInfo.get(i).getapplicantname());
							if(listApplyInfo.get(i).getapplynolist().size() > 0 && 
									listApplyInfo.get(i).getapplynolist().get(i) != null){
								policyResResponse.setApplyNo(listApplyInfo.get(i).getapplynolist().get(i).getsinglapplyno());
							}
							policyResResponse.setIsSuccess(listApplyInfo.get(i).getissuccess());
						  }
						}
					}
					break;
				case "20002":
					IssueResponse issueResponse = (IssueResponse)data;
					Header headerIss = issueResponse.getHeader();
					policyResResponse.setTradeCode(headerIss.getCode());
					policyResResponse.setResponseTime(headerIss.getTime());
					policyResResponse.setUuid(headerIss.getuuid());
					policyResResponse.setAckUuid(headerIss.getAckuuid());;
					policyResResponse.setResponseCode(headerIss.getResponseCode());
					List<PolicyInfoItem> listPolicyInfoItem = issueResponse.getListPolicyInfoItem();
					
					if(listPolicyInfoItem.size()>0){
						for(int i=0;i<listPolicyInfoItem.size();i++){
							  if(listPolicyInfoItem.get(i) != null && "1".equals(listPolicyInfoItem.get(i).getIssuccess())){
								policyResResponse.setAccountDate(listPolicyInfoItem.get(i).getAccountdate());
								policyResResponse.setCertificateId(listPolicyInfoItem.get(i).getIdno());
								policyResResponse.setOrderId(listPolicyInfoItem.get(i).getOrderid());
								policyResResponse.setPolicyNo(listPolicyInfoItem.get(i).getPolicyno());
								policyResResponse.setTotalPremium(listPolicyInfoItem.get(i).getTotalpremium());
								policyResResponse.setIsSuccess(listPolicyInfoItem.get(i).getIssuccess());
								policyResResponse.setApplyNo(listPolicyInfoItem.get(i).getPolicynolist().get(0).getapplyno());
								policyResResponse.setIdNO(listPolicyInfoItem.get(i).getIdno());
							  }else if(listPolicyInfoItem.get(i) != null && "0".equals(listPolicyInfoItem.get(i).getIssuccess())){
								 policyResResponse.setApplyNo(listPolicyInfoItem.get(i).getApplyno());
								 policyResResponse.setIsSuccess(listPolicyInfoItem.get(i).getIssuccess());
								 policyResResponse.setErrorMsg(listPolicyInfoItem.get(i).getFailreason());
							  }
						  }
					}
					break;
				case "30002":
					WithdrawResponse wdResponse = (WithdrawResponse)data;
					Header headerWd = wdResponse.getHeader();
					policyResResponse.setTradeCode(headerWd.getCode());
					policyResResponse.setResponseTime(headerWd.getTime());
					policyResResponse.setUuid(headerWd.getuuid());
					policyResResponse.setAckUuid(headerWd.getAckuuid());;
					policyResResponse.setResponseCode(headerWd.getResponseCode());
					List<RefundInfoItem> listRefundInfo = wdResponse.getListRefundInfo();
					if(listRefundInfo.size()>0){
						for(int i=0;i<listRefundInfo.size();i++){
								if(listRefundInfo.get(i) != null){
								policyResResponse.setUserName(listRefundInfo.get(i).getApplicantname());
								policyResResponse.setErrorMsg(listRefundInfo.get(i).getFailreason());
								policyResResponse.setPolicyNo(listRefundInfo.get(i).getPolicyno());	
								policyResResponse.setCertificateId(listRefundInfo.get(i).getIdno());
								policyResResponse.setRefundTime(listRefundInfo.get(i).getRefundtime());
								policyResResponse.setRefundAmount(listRefundInfo.get(i).getRefundamount());
								policyResResponse.setIsSuccess(listRefundInfo.get(i).getIssuccess());
							}
						}
					}
					break;
				default:
					break;
			}
		
		return policyResResponse;
	}
	
	@Override
	public <T> PolicyReservationRequest convertPolicyReservationRequest(T data,String code) {
		// TODO Auto-generated method stub
		PolicyReservationRequest dtPolicyResReq = new PolicyReservationRequest();

			switch(code){
			case "10002":
				UWStructVo uwData = (UWStructVo)data;
				dtPolicyResReq = UWRequest(uwData);
				break;
			case "20002":
				IssueStructVo issueData = (IssueStructVo)data;
				dtPolicyResReq = ISSRequest(issueData);
				break;
			case "30002":
				WithdrawStructVo wdData = (WithdrawStructVo)data;
				dtPolicyResReq = WDRequest(wdData);
				break;
			}
		return dtPolicyResReq;
	}
	
	
	private PolicyReservationRequest WDRequest(WithdrawStructVo wdData) {
		// TODO Auto-generated method stub
		PolicyReservationRequest dtPolicyResReq = new PolicyReservationRequest();
		Header header = wdData.getHeader();
		if(header !=null){
			dtPolicyResReq.setTradeCode(header.getCode());
			dtPolicyResReq.setAsyn(header.getAsyn());
			dtPolicyResReq.setPartnerIdentifier(header.getpartneridentifier());
			dtPolicyResReq.setRequestTime(header.getTime());
			dtPolicyResReq.setUuid(header.getuuid());
			dtPolicyResReq.setPartnerSerial(header.getPartnerserial());
		}
		List<RefundItem> list = wdData.getRequest().getRefundlist();
		dtPolicyResReq.setPolicyNo(list.get(0).getPolicyno());
		dtPolicyResReq.setUserName(list.get(0).getApplicantname());
		dtPolicyResReq.setRefundTime(list.get(0).getRefundtime());
		return dtPolicyResReq;
	}

	private PolicyReservationRequest ISSRequest(IssueStructVo issueData) {
		// TODO Auto-generated method stub
		PolicyReservationRequest dtPolicyResReq = new PolicyReservationRequest();
		Header header = issueData.getHeader();
		if(header !=null){
			dtPolicyResReq.setTradeCode(header.getCode());
			dtPolicyResReq.setAsyn(header.getAsyn());
			dtPolicyResReq.setPartnerIdentifier(header.getpartneridentifier());
			dtPolicyResReq.setRequestTime(header.getTime());
			dtPolicyResReq.setUuid(header.getuuid());
		}
		
		dtPolicyResReq.setApplyNo(issueData.getRequest().getApplyno());
		
		Payment pay = issueData.getRequest().getPayment();
		if(pay != null){
			dtPolicyResReq.setAccountDate(pay.getAccountdate());
			dtPolicyResReq.setBankSerial(pay.getBankserial());
			dtPolicyResReq.setPayBankCode(pay.getPaybankcode());
			dtPolicyResReq.setPaymentId(pay.getPaymentid());
			dtPolicyResReq.setPayMoney(pay.getPaymoney());
			dtPolicyResReq.setPayTime(pay.getPaytime());
			dtPolicyResReq.setPayType(pay.getPaytype());
			dtPolicyResReq.setPayBankAcount(pay.getPaybankacount());
			dtPolicyResReq.setReciveBankAcount(pay.getRecivebankacount());
			dtPolicyResReq.setReciveBankCode(pay.getRecivebankcode());
			dtPolicyResReq.setReciveBankUserId(pay.getRecivebankuserid());
			dtPolicyResReq.setReciveBankUsername(pay.getRecivebankusername());
		}
		return dtPolicyResReq;
	}

	public PolicyReservationRequest UWRequest(UWStructVo uwData) {
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
				dtPolicyResReq.setInsuranceCoverage(productinfo.getinsurancecoverage());
			}
		}
		InsuredInfo insuredInfo = applyInfo.getInsuredinfo();
		dtPolicyResReq.setIsApplicant(insuredInfo.getIsapplicant());
		if(!CollectionUtils.isEmpty(insuredInfo.getInsurantlist())){
			Insurant insurant = insuredInfo.getInsurantlist().get(0);
			if(insurant!=null){
				dtPolicyResReq.setInsurantCellPhoneNumber(insurant.getcellphonenumber());
				dtPolicyResReq.setInsurantEmail(insurant.getEmail());
				dtPolicyResReq.setInsurantID(insurant.getid());
				dtPolicyResReq.setInsurantIDType(insurant.getidtype());
				dtPolicyResReq.setInsurantApplicantRelation(insurant.getinsurantapplicantrelation());
				dtPolicyResReq.setInsurantName(insurant.getName());
				dtPolicyResReq.setInsurantSex(insurant.getSex());
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
	
	@Override
	public void ValidationJob() {
		// 检索询数据库，查询利安保险收益是否有变化
		List<PolicyData> policyDatas = policyDataDao.validation();
		if (policyDatas.isEmpty()) {
			return;
		} else {
			logger.info("利安保险收益数据有变了，还等什么，赶紧更新吧！");
			// 拼接json数据
			String data = JsonOnlineUtils.list2json(policyDatas);
			String url = Configs.get("appcontroller_url");
			List<Map<String,Object>> posts = new ArrayList<Map<String,Object>>();
			Map<String,Object> postData = new HashMap<String,Object>();
			postData.put("url", url+"?version="+System.currentTimeMillis());
			postData.put("encode", "utf-8");
			Map<String,Object> requestData = new HashMap<String,Object>();
			requestData.put("data", data);
			postData.put("params", requestData);
			posts.add(postData);
			String[] response = HttpClientThreadUtil.threadPost(posts);
			for (String string : response) {
				logger.info(new Date(System.currentTimeMillis())+"更新收益数据是否成功?======="+string);
			}
		}
	}
	
	@Override
	public void PolicyJob(){
		// 检索询数据库，查询利安保险收益是否有变化
				List<PolicyData> policyDatas = policyDataDao.getPolicyNoWithSuccess();
				if (policyDatas.isEmpty()) {
					return;
				} else {
					logger.info("发到去哪儿！");
					String url = Configs.get("qunaer_url");
					List<Map<String, Object>> posts = new ArrayList<Map<String,Object>>();
					//拼装待请求的数组
					for (PolicyData policyData : policyDatas) {
						Map<String,Object> postData = new HashMap<String,Object>();
						postData.put("url", url);
						postData.put("encode", "utf-8");
						Map<String,Object> requestData = new HashMap<String,Object>();
						requestData.put("policyNo", policyData.getPolicyNo());
						postData.put("params", requestData);
						posts.add(postData);
					}
					String[] response = HttpClientThreadUtil.threadPost(posts);
					for (String string : response) {
						logger.info(new Date(System.currentTimeMillis())+"去哪儿返回的数据：===="+string);
					}
					
				}
	}
}

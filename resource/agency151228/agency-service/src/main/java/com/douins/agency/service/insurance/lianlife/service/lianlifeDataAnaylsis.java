package com.douins.agency.service.insurance.lianlife.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.common.util.XmlUtils;
import com.douins.agency.service.insurance.lianlife.domain.model.ApplyInfoItem;
import com.douins.agency.service.insurance.lianlife.domain.model.ApplyNoItem;
import com.douins.agency.service.insurance.lianlife.domain.model.PolicyInfoItem;
import com.douins.agency.service.insurance.lianlife.domain.model.PolicyNoItem;
import com.douins.agency.service.insurance.lianlife.domain.model.RefundInfoItem;
import com.douins.agency.service.insurance.lianlife.domain.vo.InsUWResponse;
import com.douins.agency.service.insurance.lianlife.domain.vo.IssueResponse;
import com.douins.agency.service.insurance.lianlife.domain.vo.WithdrawResponse;

/** 
* @ClassName: lianlifeDataAnalysis 
* @Description: 解析 lianlife 返回的数据
* @author pan
* @date 2016年3月5日 下午9:16:40 
*  
*/
public class lianlifeDataAnaylsis {
	private static final String pattern = "[\\s]";
	
    /**
     * lianlife 的核保返回的数据结构
     * @param xml
     * @return
     */
    public static InsUWResponse getUWResponse(String xml){
    	Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        InsUWResponse response = new InsUWResponse();
        List<ApplyInfoItem> listApplyInfoItem = new ArrayList<ApplyInfoItem>();
        
        Header header = getHeader(doc);
        listApplyInfoItem = getListApplyInfoItem(doc);
        response.setListApplyInfoItem(listApplyInfoItem);
        response.setHeader(header);
        
        return response;
    }
    
    private static Header getHeader(Document doc) {
		// TODO Auto-generated method stub
        Elements header = doc.select("header");
        String headerXml = header.toString().replaceAll(pattern, "");
        Header header2 = XmlUtils.xml2Object(headerXml, Header.class);
        return header2;
	}

	private static List<ApplyInfoItem> getListApplyInfoItem(Document doc) {
		// TODO Auto-generated method stub
    	List<ApplyInfoItem> listApplyInfoItem = new ArrayList<ApplyInfoItem>();
    	
    	ApplyInfoItem applyInfoItem = getApplyInfoItem(doc);
    	
    	listApplyInfoItem.add(applyInfoItem);//目前统一为单个order承保结果返回
		return listApplyInfoItem;
	}

	private static ApplyInfoItem getApplyInfoItem(Document doc) {
		// TODO Auto-generated method stub
		List<ApplyNoItem> listApplyNoItem = new ArrayList<ApplyNoItem>();
		listApplyNoItem.add(getApplyNoItem(doc));
		
		Elements elements = doc.select("applyinfoitem");
	    String xml = elements.toString().replaceAll(pattern, "");
	    ApplyInfoItem applyInfoItem = XmlUtils.xml2Object(xml, ApplyInfoItem.class);
	    if(applyInfoItem != null){
			applyInfoItem.setapplynolist(listApplyNoItem);
			return applyInfoItem;
	    }else{
	    	return null;
	    }

	}

	private static ApplyNoItem getApplyNoItem(Document doc){
		Elements elements = doc.select("applynoitem");
    	String xml = elements.toString().replaceAll(pattern, "");
    	ApplyNoItem applyNoItem =  XmlUtils.xml2Object(xml, ApplyNoItem.class);
		return applyNoItem;
	}
	/**
     * lianlife 承保返回的数据
     * @param xml
     * @return
     */
    public static IssueResponse getIssueResponse(String xml){
    	 Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    	 
    	 IssueResponse issueResponse = new IssueResponse();
    	 
    	 List<PolicyInfoItem> listPolicyInfoItem = new ArrayList<PolicyInfoItem>();
    	 Header header = getHeader(doc);
    	 listPolicyInfoItem = getListPolicyInfoItem(doc);
    	 issueResponse.setHeader(header);
    	 issueResponse.setListPolicyInfoItem(listPolicyInfoItem);
    	 
    	 return issueResponse;
    }

    public static WithdrawResponse getWithdrawResponse(String xml){
    	 Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    	 WithdrawResponse withdrawResponse = new WithdrawResponse();
    	 List<RefundInfoItem> listRefundInfo = new ArrayList<RefundInfoItem>();
    	 Header header = getHeader(doc);
    	 listRefundInfo = getListRefundInfo(doc);
    	 withdrawResponse.setHeader(header);
    	 withdrawResponse.setListRefundInfo(listRefundInfo);
    	 return withdrawResponse;
    }
    
	private static List<RefundInfoItem> getListRefundInfo(Document doc) {
		// TODO Auto-generated method stub
		List<RefundInfoItem> listRefundInfoItem = new ArrayList<RefundInfoItem>();
		RefundInfoItem refundInfoItem = getRefundInfoItem(doc);
		listRefundInfoItem.add(refundInfoItem);
		return listRefundInfoItem;
	}

	private static RefundInfoItem getRefundInfoItem(Document doc) {
		// TODO Auto-generated method stub
		 Elements elements = doc.select("refundinfoitem") ;
		 String xml = elements.toString().replaceAll(pattern, "");
		 RefundInfoItem refundInfoItem = XmlUtils.xml2Object(xml, RefundInfoItem.class);
		return refundInfoItem;
	}

	private static List<PolicyInfoItem> getListPolicyInfoItem(Document doc) {
		// TODO Auto-generated method stub
		 List<PolicyInfoItem> listPolicyInfoItem = new ArrayList<PolicyInfoItem>();
		 PolicyInfoItem policyInfoItem = new PolicyInfoItem();
		 
		 policyInfoItem = getPolicyInfoItem(doc);
		 
		 listPolicyInfoItem.add(policyInfoItem);//目前统一为单个order承保结果返回
		return listPolicyInfoItem;
	}

	private static PolicyInfoItem getPolicyInfoItem(Document doc) {
		// TODO Auto-generated method stub
	    Elements elements = doc.select("policyinfoitem") ;
	    String xml = elements.toString().replaceAll(pattern, "");
	    PolicyInfoItem policyInfoItem = XmlUtils.xml2Object(xml, PolicyInfoItem.class);
		
	    return policyInfoItem;
	}
	
	private static List<PolicyNoItem> getPolicyNoItem(Document doc){
		List<PolicyNoItem> listPolicyNoItem = new ArrayList<PolicyNoItem>();
		Elements elements = doc.select("policynoitem");
		String xml = elements.toString().replaceAll(pattern, "");
		PolicyNoItem policyNoItem = XmlUtils.xml2Object(xml,PolicyNoItem.class);
		
		listPolicyNoItem.set(0, policyNoItem); //目前统一为单个order承保结果返回
		
		return listPolicyNoItem;
	}
}

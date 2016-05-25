package com.douins.agency.service.channel.qunarfinance.service;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.douins.agency.service.channel.qunarfinance.domain.model.ApplyInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.Header;
import com.douins.agency.service.channel.qunarfinance.domain.model.OrderInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.OtherInfo;
import com.douins.agency.service.channel.qunarfinance.domain.model.Payment;
import com.douins.agency.service.channel.qunarfinance.domain.model.RefundItem;
import com.douins.agency.service.channel.qunarfinance.domain.model.Request;
import com.douins.agency.service.channel.qunarfinance.domain.vo.IssueStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.QueryStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.UWStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.WithdrawStructVo;
import com.douins.agency.service.common.util.XmlUtils;


/**
 * @ClassName: ConvertDataService 
 * @Description: 数据转换类
 * @author panrui
 *
 */
public class ConvertDataService {
	
    private static final String pattern = "[\\s]";
    
    /**
     * 从 xml 结构中提取核保请求数据
     * @param xml
     * @return
     * @throws Exception 
     */
    public static UWStructVo getUWDatasFromXml(String xml) throws Exception{
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Header header = getHeader(doc);
        ApplyInfo applyInfo = getApplyInfo(doc);
        OrderInfo orderInfo = getOrderInfo(doc);
        OtherInfo otherInfo = getOtherInfo(doc);
        
        UWStructVo uwStruct = new UWStructVo(header,applyInfo,orderInfo,otherInfo);
        return uwStruct;
    }

    /**
     * 从 xml 结构中提取承保请求数据
     * @param doc
     * @return
     */
    public static IssueStructVo getIssueDatasFromXml(String xml)throws Exception{
    	Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    	Header header = getHeader(doc);
    	Request request = getRequest(doc);
    	IssueStructVo issueStruct = new IssueStructVo(header,request);
    	return issueStruct;
    }
    
    private static Request getRequest(Document doc) {
		// TODO Auto-generated method stub
		Elements elements = doc.select("request");
		String requestXml = elements.toString().replaceAll(pattern, "");
		Request request = XmlUtils.xml2Object(requestXml, Request.class);

    	Payment payment = getPayment(doc);
    	List<RefundItem> refundlist = new ArrayList<RefundItem>();
    	refundlist.add(getRefundItem(doc));
    	
    	if(payment != null){
    		request.setPayment(payment);
    	}
    	
    	if(refundlist.size()>0 && refundlist.get(0) != null){
    		request.setRefundlist(refundlist);
    	}
    	
		return request;
	}

	private static RefundItem getRefundItem(Document doc) {
		// TODO Auto-generated method stub
        Elements refunditem = doc.select("refunditem");
        String refunditemXml = refunditem.toString().replaceAll(pattern, "");
        RefundItem refunditem2 = XmlUtils.xml2Object(refunditemXml, RefundItem.class);
        return refunditem2;
	}

	/**
     * 从 xml 结构中提取退保请求数据
     * @param doc
     * @return
     */
   public static WithdrawStructVo getWithdrawDatasFromXml(String xml)throws Exception{
	   Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
	   Header header = getHeader(doc);
	   Request request = getRequest(doc);
	   
	   WithdrawStructVo withdrawStruct = new WithdrawStructVo(header,request);
	   return withdrawStruct;
   }
   
   /**
    * 从 xml 结构中提取查询请求数据
    * @param doc
    * @return
    */
   public static QueryStructVo getQueryDatasFromXml(String xml){
	   Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
	   Header header = getHeader(doc);
	   String policyNo = getpolicyNo(doc);
	   
	   QueryStructVo queryStruct = new QueryStructVo(header,policyNo);
	   return queryStruct;
   }
   
	private static String getpolicyNo(Document doc) {
	// TODO Auto-generated method stub
		Elements elements = doc.select("policyno");
		String policyNoXml = elements.toString().replaceAll(pattern, "");
//		String policyNo = XmlUtils.xml2Object(policyNoXml, String.class);
		String policyNo = elements.text();
	return policyNo;
	}
	private static String getapplicantName(Document doc) {
		Elements elements = doc.select("applicantname");
		String applicantName = elements.text();
	return applicantName;
	}
	private static String getrefundTime(Document doc) {
		Elements elements = doc.select("refundtime");
		String refundTime = elements.text();
	return refundTime;
	}

	private static Payment getPayment(Document doc) {
		// TODO Auto-generated method stub
		Elements elements = doc.select("payment");
		String paymentXml = elements.toString().replaceAll(pattern, "");
		Payment payment = XmlUtils.xml2Object(paymentXml,Payment.class);
		return payment;
	}

	private static String getApplyNo(Document doc) {
		// TODO Auto-generated method stub
		Elements elements = doc.select("applyno");
		String applyNoXml = elements.toString().replaceAll(pattern, "");
		String applyNo = XmlUtils.xml2Object(applyNoXml, String.class);
		return applyNo;
	}

	private static OtherInfo getOtherInfo(Document doc) {
		// TODO Auto-generated method stub
		Elements elements = doc.select("otherinfo");
		String otherInfoXml = elements.toString().replaceAll(pattern, "");
		OtherInfo otherinfo = XmlUtils.xml2Object(otherInfoXml, OtherInfo.class);
		return otherinfo;
	}

	private static OrderInfo getOrderInfo(Document doc) {
		// TODO Auto-generated method stub
		Elements elements = doc.select("orderinfo");
		String orderinfoXml = elements.toString().replaceAll(pattern, "");
		OrderInfo orderinfo = XmlUtils.xml2Object(orderinfoXml, OrderInfo.class);
		return orderinfo;
	}

	private static ApplyInfo getApplyInfo(Document doc) {
		// TODO Auto-generated method stub
		Elements elements = doc.select("applyinfo");
		String applyinfoXml = elements.toString().replaceAll(pattern, "");
		ApplyInfo applyInfo = XmlUtils.xml2Object(applyinfoXml, ApplyInfo.class);
		return applyInfo;
	}

	private static Header getHeader(Document doc) {
		// TODO Auto-generated method stub
	        Elements header = doc.select("header");
	        String headerXml = header.toString().replaceAll(pattern, "");
	        Header header2 = XmlUtils.xml2Object(headerXml, Header.class);
	        return header2;
	}

}

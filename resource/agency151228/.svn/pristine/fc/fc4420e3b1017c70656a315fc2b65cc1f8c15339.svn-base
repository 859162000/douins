/**
 * 
 */
package com.douins.agency.service.insurance.ccic.service;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.douins.agency.service.common.util.XmlUtils;
import com.douins.agency.service.insurance.ccic.domain.model.Applicant;
import com.douins.agency.service.insurance.ccic.domain.model.Endorse;
import com.douins.agency.service.insurance.ccic.domain.model.Insured;
import com.douins.agency.service.insurance.ccic.domain.model.Policy;
import com.douins.agency.service.insurance.ccic.domain.model.ResponseExpand;
import com.douins.agency.service.insurance.ccic.domain.model.ResponseStatus;
import com.douins.agency.service.insurance.ccic.domain.vo.InsApplyResponse;
import com.douins.agency.service.insurance.ccic.domain.vo.InsUWResponse;
import com.douins.agency.service.insurance.ccic.domain.vo.WithdrawResponse;

/** 
* @ClassName: CCICDataAnalysis 
* @Description: 解析 CCIC 返回的数据
* @author G. F. 
* @date 2016年1月5日 下午9:16:40 
*  
*/
public class CCICDataAnalysis {
//    private static Logger logger = Logger.getLogger(CCICDataAnalysis.class);
    private static final String pattern = "[\\s]";
    
    /**
     * CCIC 的核保返回的数据结构
     * @param xml
     * @return
     */
    public static InsUWResponse getUWResponse(String xml){
        InsUWResponse response = XmlUtils.xml2Object(xml, InsUWResponse.class);
        return response;
    }
    
    /**
     * CCIC 承保返回的数据
     * @param xml
     * @return
     */
    public static InsApplyResponse getInsApplyResponse(String xml){
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        InsApplyResponse insApplyResponse = new InsApplyResponse();
        ResponseStatus status = getResponseStatus(doc);
        Policy policy = getPolicyInfo(doc);
        ResponseExpand expand = getExpandInfo(doc);
        Applicant applicant = getApplicantInfo(doc);
        Insured insured =getInsuredInfo(doc);
        
        insApplyResponse.setResponseStatus(status);
        insApplyResponse.setPolicy(policy);
        insApplyResponse.setExpand(expand);
        insApplyResponse.setApplicant(applicant);
        insApplyResponse.setInsured(insured);
        return insApplyResponse;
//        InsApplyResponse response = XmlUtils.xml2Object(xml, InsApplyResponse.class);
//        return response;
    }
    
    /**
     * CCIC 返回的退保／撤销数据
     * @param xml
     * @return
     */
    public static WithdrawResponse getWithdrawResponse(String xml){
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        WithdrawResponse withdrawResponse = new WithdrawResponse();
        ResponseStatus status = getResponseStatus(doc);
        Policy policy = getPolicyInfo(doc);
        Endorse endorse = getEndorse(doc);
        
        withdrawResponse.setEndorse(endorse);
        withdrawResponse.setPolicy(policy);
        withdrawResponse.setResponseStatus(status);
        
        return withdrawResponse;
    }
    
    /**
     * 响应状态
     * @param doc
     * @return
     */
    private static ResponseStatus getResponseStatus(Document doc){
        Elements elements = doc.select("responsestatus");
        String xml = elements.toString().replaceAll(pattern, "");
        ResponseStatus response = XmlUtils.xml2Object(xml, ResponseStatus.class);
        return response;
    }
    
    /**
     * 保单信息
     * @param doc
     * @return
     */
    private static  Policy getPolicyInfo(Document doc){
        Elements elements = doc.select("main");
        String xml = elements.toString().replaceAll(pattern, "");
        Policy policy = XmlUtils.xml2Object(xml, Policy.class);
        return policy;
    }
    
    /**
     * 投保人信息
     */
    private static Applicant getApplicantInfo(Document doc){
    	Elements elements = doc.select("reapplicant");
    	String xml = elements.toString().replaceAll(pattern, "");
    	Applicant applicant = XmlUtils.xml2Object(xml, Applicant.class);
    	return applicant;
    }
    /**
     * 被保人信息
     */
    private static Insured getInsuredInfo(Document doc){
    	Elements elements = doc.select("reinsured");
    	String xml = elements.toString().replaceAll(pattern, "");
    	Insured insured = XmlUtils.xml2Object(xml, Insured.class);
    	return insured;
    }
    
    /**
     * 扩展字段
     * @param doc
     * @return
     */
    private static ResponseExpand getExpandInfo(Document doc){
        Elements elements = doc.select("expand");
        String xml = elements.toString().replaceAll(pattern, "");
        ResponseExpand expand = XmlUtils.xml2Object(xml, ResponseExpand.class);
        return expand;
    }
    
    /**
     * 批改信息
     * @param doc
     * @return
     */
    private static Endorse getEndorse(Document doc){
        Elements elements = doc.select("endorse");
        String xml = elements.toString().replaceAll(pattern, "");
        Endorse endorse = XmlUtils.xml2Object(xml, Endorse.class);
        return endorse;
    }
}

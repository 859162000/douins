/**
 * 
 */
package com.douins.insurance.service.iml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.douins.insurance.domain.model.InsResponse;

/** 
* @ClassName: ResponseUtil 
* @Description: 复星返回信息解析工具类
* @author G. F. 
* @date 2015年12月16日 下午6:03:58 
*  
*/
public class ResponseUtil {
    private static final Logger logger = Logger.getLogger(ResponseUtil.class);
    
    private static final String requestType = "RequestType";
    private static final String responseCode = "ResponseCode";
    private static final String errMsg = "ErrorMessage";
    private static final String sendTime = "SendTime";
    private static final String proposalNo = "ProposalNo";
    private static final String totalPrem = "TotalPremium";
    private static final String orderId = "OrderId";
    private static final String policyNo = "PolicyNo";
    private static final String accDate = "AccountDate";
    private static final String success = "IsSuccess";
    private static final String failReason = "FailReason";
    private static final String uwFlag = "UnderwriteFlag";
    private static final String exTag = "Exception";
    private static final String policyUrl = "PolicyUrl";
    
    /**
     * 核保返回数据
     * @param response
     * @return
     */
    public static InsResponse getUWResponse(String response){
        InsResponse insResponse = new InsResponse();
        try {
            org.jsoup.nodes.Document doc = Jsoup.parse(response, "", Parser.xmlParser());
            if(doc.hasAttr(exTag)){
                insResponse.setResponseCode("1");       // 0-- 成功；1-- 失败
                insResponse.setErrMsg(doc.select(exTag).text());
                return insResponse;
            }
            
            Elements header = doc.select("Header");       // header 部分
            insResponse.setRequestType(header.select(requestType).text());
            insResponse.setResponseCode(header.select(responseCode).text());
            insResponse.setErrMsg(header.select(errMsg).text());
            insResponse.setSendTime(header.select(sendTime).text());
            
            Elements proposal = doc.select("Proposal");     // proposal 部分
            insResponse.setOrderId(proposal.select(orderId).text());
            insResponse.setProposalNo(proposal.select(proposalNo).text());
            insResponse.setTotalPremium(proposal.select(totalPrem).text());
            insResponse.setUnderwriteFlag(proposal.select(uwFlag).text());
            insResponse.setFailReason(proposal.select(failReason).text());
        } catch (Exception e) {   
            logger.error("convert uw response error.", e);
        }   
        
        return insResponse;
    }
    
    /**
     * 承保返回的数据
     * @param response
     * @return
     */
    public static InsResponse getInsuredResponse(String response){
        InsResponse insResponse = new InsResponse();
        try {   
            org.jsoup.nodes.Document doc = Jsoup.parse(response, "", Parser.xmlParser());
            if(doc.hasAttr(exTag)){
                insResponse.setResponseCode("1");       // 0-- 成功；1-- 失败
                insResponse.setErrMsg(doc.select(exTag).text());
                return insResponse;
            }

            Elements header = doc.select("Header");       // header 部分
            insResponse.setRequestType(header.select(requestType).text());
            insResponse.setResponseCode(header.select(responseCode).text());
            insResponse.setErrMsg(header.select(errMsg).text());
            insResponse.setSendTime(header.select(sendTime).text());
            
            Elements policy = doc.select("Policy");     // proposal 部分
            insResponse.setProposalNo(policy.select(proposalNo).text());
            insResponse.setTotalPremium(policy.select(totalPrem).text());
            insResponse.setOrderId(policy.select(orderId).text());
            insResponse.setPolicyNo(policy.select(policyNo).text());
            insResponse.setAccDate(policy.select(accDate).text());
            insResponse.setSuccess(policy.select(success).text());
            insResponse.setFailReason(policy.select(failReason).text());
            insResponse.setPolicyUrl(policy.select(policyUrl).text());
        } catch (Exception e) {   
            logger.error("convert insured response error.", e);
        }   
        
        return insResponse;
    }
}

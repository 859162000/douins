/**
 * 
 */
package com.douins.agency.service.channel.qunar.service;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.douins.agency.service.channel.qunar.domain.model.GroupInfo;
import com.douins.agency.service.channel.qunar.domain.model.Header;
import com.douins.agency.service.channel.qunar.domain.model.PolicyConfirm;
import com.douins.agency.service.channel.qunar.domain.model.PolicyInfo;
import com.douins.agency.service.channel.qunar.domain.vo.InsureApplyVo;
import com.douins.agency.service.channel.qunar.domain.vo.InsuredInfoVo;
import com.douins.agency.service.channel.qunar.domain.vo.UWStructVo;
import com.douins.agency.service.channel.qunar.domain.vo.WithdrawReqVo;
import com.douins.agency.service.common.util.XmlUtils;
import com.google.common.collect.Lists;

/** 
* @ClassName: ConvertDataService 
* @Description: 数据转换类
* @author G. F. 
* @date 2015年12月30日 下午6:14:11 
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
        GroupInfo groupInfo = getGroupInfo(doc, true);
        List<InsuredInfoVo> insureds = getInsuredInfo(doc);
        
        UWStructVo uwStruct = new UWStructVo(header, groupInfo, insureds);
        return uwStruct;
    }
    
    /**
     * 从 xml 结构中提取承保请求数据
     * @param xml
     * @return
     * @throws Exception 
     */
    public static InsureApplyVo getInsureApplyDataFromXml(String xml) throws Exception{
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Header header = getHeader(doc);
        List<PolicyConfirm> confirms = getPolicyConfirm(doc);
        
        InsureApplyVo applyVo = new InsureApplyVo(header, confirms);
        return applyVo;
    }
    
    /**
     * 从 xml 结构中提取退保请求数据
     * @param xml
     * @return
     * @throws Exception 
     */
    public static WithdrawReqVo getWithdrawReqDataFromXml(String xml) throws Exception{
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Header header = getHeader(doc);
        GroupInfo groupInfo = getGroupInfo(doc, false);
        List<PolicyInfo> policyInfos = getPolicyInfos(doc);
        
        WithdrawReqVo reqVo = new WithdrawReqVo(header, groupInfo, policyInfos);
        return reqVo;
    }

    
    
    /**
     * xml 中的 header 部分
     * @param doc
     * @return
     */
    private static Header getHeader(Document doc){
        Elements header = doc.select("header");
        String headerXml = header.toString().replaceAll(pattern, "");
        Header header2 = XmlUtils.xml2Object(headerXml, Header.class);
        return header2;
    }
    
    /**
     * xml 中的 groupInfo
     * @param doc
     * @return
     * @throws Exception 
     */
    private static GroupInfo getGroupInfo(Document doc, boolean check) throws Exception{
        Elements elements = doc.select("groupInfo");
        String data = elements.toString().replaceAll(pattern, "");
        GroupInfo groupInfo = XmlUtils.xml2Object(data, GroupInfo.class);
        if(check)
            if(groupInfo.getOrderNo() == null || groupInfo.getOrderNo().isEmpty()){
                throw new Exception("orderNo 不能为空============");
            }
        return groupInfo;
    }
    
    /**
     * 投保人的相关信息
     * @param doc
     * @return
     * @throws Exception 
     */
    private static List<InsuredInfoVo> getInsuredInfo(Document doc) throws Exception{
        Elements elements = doc.select("parameter");
        List<InsuredInfoVo> insureds = Lists.newArrayList();
        
        for(int i = 0; i < elements.size(); i ++){
            String elem = elements.get(i).toString().replaceAll(pattern, "");
            InsuredInfoVo insured = XmlUtils.xml2Object(elem, InsuredInfoVo.class);
            if(insured.getApplySeq() == null || insured.getApplySeq().isEmpty()){
                throw new Exception("applySeq 不能为空============");
            }
            insureds.add(insured);
        }
        return insureds; 
    }
    
    /**
     * 承保请求中的 PolicyConfirm
     * @param doc
     * @return
     * @throws Exception 
     */
    private static List<PolicyConfirm> getPolicyConfirm(Document doc) throws Exception{
        Elements elements = doc.select("parameter");
        List<PolicyConfirm> params = Lists.newArrayList();
        
        for(int i = 0; i < elements.size();i++){
            String elem = elements.get(i).toString().replaceAll(pattern, "");
            PolicyConfirm policy = XmlUtils.xml2Object(elem, PolicyConfirm.class);
            if(policy.getBusinessId() == null || policy.getBusinessId().isEmpty()){
                throw new Exception("businessId 不能为空==============");
            }
            params.add(policy);
        }
        
        return params;
    }
    
    /**
     * 退保请求中的 PolicyNo
     * @param doc
     * @return
     * @throws Exception 
     */
    private static List<PolicyInfo> getPolicyInfos(Document doc) throws Exception{
        Elements elements = doc.select("parameter");
        List<PolicyInfo> infos = Lists.newArrayList();
        
        for(int i = 0; i < elements.size(); i++){
            PolicyInfo info = new PolicyInfo();
            info.setPolicyNo(elements.get(i).select("policyno").text());
            infos.add(info);
            if(info.getPolicyNo() == null || info.getPolicyNo().isEmpty()){
                throw new Exception("policyNo 不能为空==========");
            }
        }
        
        return infos;
    }
}

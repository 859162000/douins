/**
 * 
 */
package com.douins.agency.service.douins.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.douins.agency.service.channel.qunar.domain.enums.QunarIdType;
import com.douins.agency.service.channel.qunar.domain.enums.QunarTransType;
import com.douins.agency.service.channel.qunar.domain.model.PolicyConfirm;
import com.douins.agency.service.channel.qunar.domain.model.PolicyInfo;
import com.douins.agency.service.channel.qunar.domain.model.ResponseRecord;
import com.douins.agency.service.channel.qunar.domain.vo.InsureApplyVo;
import com.douins.agency.service.channel.qunar.domain.vo.InsuredInfoVo;
import com.douins.agency.service.channel.qunar.domain.vo.ResponseVo;
import com.douins.agency.service.channel.qunar.domain.vo.UWStructVo;
import com.douins.agency.service.channel.qunar.domain.vo.WithdrawReqVo;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.common.util.Configs;
import com.douins.agency.service.common.util.DateTimeUtils;
import com.douins.agency.service.common.util.SeqNoUtils;
import com.douins.agency.service.common.util.TemplateLoader;
import com.douins.agency.service.common.util.TrustStoreUtils;
import com.douins.agency.service.douins.domain.enums.ChannelEnum;
import com.douins.agency.service.douins.domain.enums.DouinsException;
import com.douins.agency.service.douins.domain.enums.InsuranceEnum;
import com.douins.agency.service.douins.domain.enums.PolicyStatus;
import com.douins.agency.service.douins.domain.model.InsureCancelApplyHeader;
import com.douins.agency.service.douins.domain.model.InsureCancelApplyItem;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmFeedback;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmHeader;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmItem;
import com.douins.agency.service.douins.domain.model.InsureConfirmFeedback;
import com.douins.agency.service.douins.domain.model.InsureConfirmHeader;
import com.douins.agency.service.douins.domain.model.InsureConfirmItem;
import com.douins.agency.service.douins.domain.model.InsureRequestFeedback;
import com.douins.agency.service.douins.domain.model.InsureRequestHeader;
import com.douins.agency.service.douins.domain.model.InsureRequestItem;
import com.douins.agency.service.douins.domain.model.PolicyFact;
import com.douins.agency.service.douins.domain.vo.InsureApplyDataVo;
import com.douins.agency.service.douins.domain.vo.ProductVo;
import com.douins.agency.service.douins.service.AdapterServiceIFC;
import com.douins.agency.service.douins.service.ChannelServiceIFC;
import com.douins.agency.service.douins.service.InsureServiceIFC;
import com.douins.agency.service.douins.service.database.CCICDataService;
import com.douins.agency.service.douins.service.database.DouinsDataService;
import com.douins.agency.service.douins.service.database.QunarDataService;
import com.douins.agency.service.insurance.ccic.domain.enums.CCICIdType;
import com.douins.agency.service.insurance.ccic.domain.enums.CCICTransType;
import com.douins.agency.service.insurance.ccic.domain.model.Applicant;
import com.douins.agency.service.insurance.ccic.domain.model.ChannelInfo;
import com.douins.agency.service.insurance.ccic.domain.model.Insured;
import com.douins.agency.service.insurance.ccic.domain.model.ItemKind;
import com.douins.agency.service.insurance.ccic.domain.model.UWmain;
import com.douins.agency.service.insurance.ccic.domain.vo.InsApplyResponse;
import com.douins.agency.service.insurance.ccic.domain.vo.InsApplyStructVo;
import com.douins.agency.service.insurance.ccic.domain.vo.InsUWResponse;
import com.douins.agency.service.insurance.ccic.domain.vo.InsUWStructVo;
import com.douins.agency.service.insurance.ccic.domain.vo.WithdrawResponse;
import com.douins.agency.service.insurance.ccic.service.CCICDataAnalysis;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/** 
* @ClassName: QunarCcicAdapter 
* @Description: qunar-ccic 的适配器
* @author G. F. 
* @date 2015年12月31日 上午11:13:22 
*  
*/
@Service(Constants.QUNAR_CCIC_ADAPTER)
public class QunarCcicAdapter implements AdapterServiceIFC {
    private static final Logger logger = Logger.getLogger(QunarCcicAdapter.class);
    
    @Resource(name = Constants.CCIC_SERVICE)
    private InsureServiceIFC insureService;
    @Resource(name = Constants.QUNAR_SERVICE)
    private ChannelServiceIFC chanlService;
    @Resource(name = Constants.CCIC_DATA_SERVICE)
    private CCICDataService ccicDataService;
    @Resource
    private QunarDataService qunarDataService;
    @Resource
    private TemplateLoader templateLoader;
    
    @Resource
    private DouinsDataService douinsDataService;
    @Resource
    private TrustStoreUtils httpsConnection;
    
    private List<String> orderNos = Lists.newArrayList();
    
    private static String url;
    private static File keystoreFile;
    private static File truststoreFile;
    
    static{
        url = Configs.get("url_ccic");
        logger.info("当前的CCIC URL＝"+ url);
    }
    
    /**
     * 加载证书库
     */
    @PostConstruct
    private void init(){
        try {
            keystoreFile = ResourceUtils.getFile(Configs.get("jks_keystore_path_ccic"));
            truststoreFile = ResourceUtils.getFile(Configs.get("jks_truststore_path_ccic"));
            httpsConnection.initHttpsURLConnection(Configs.get("jsk_password_ccic"), keystoreFile.getPath(), truststoreFile.getPath());
        } catch (Exception e) {
           logger.error("Https 连接初始化失败================", e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.AdapterServiceIFC#doUW(java.lang.Object)
     */
    public <T> String doUW(T data) {
        String resXml = null;
        UWStructVo uwData = (UWStructVo)data;
        //  判断交易类型是否正确
        if(!uwData.getHeader().getProfileRequest().equals(QunarTransType.UNDERWRITING.getValue())
                || !uwData.getHeader().getFunction().equals(QunarTransType.UNDERWRITING.getFunction())){
//            return StaticTemplate.getExceptionTemplate(templateLoader, DouinsException.getErrorMap(DouinsException.TRADE_TYPE_ERROR));
            return uwErrorResponse(uwData, DouinsException.TRADE_TYPE_ERROR.getName());
        }
        
        // 查询记录是否存在
        List<InsureRequestFeedback> items = qunarDataService.getUWRecordes(uwData.getGroupInfo().getOrderNo());
        if(items.size() == 0){
            resXml = doFirstUW(uwData);     // 一核
        }else{
            logger.info("二核");
            // 对比前后两次数据是否一致
            verifyUWData(items, uwData);
            resXml = getUWResponse2(uwData, items);
        }
        
        return resXml;
    }
    
    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.AdapterServiceIFC#doInsure(java.lang.Object)
     */
    public <T> String doInsure(T data) {
    	long start =System.currentTimeMillis();
        String resXml = null;
        // TODO Auto-generated method stub   	
        InsureApplyVo insApplyData = (InsureApplyVo)data;
        //  判断交易类型是否正确
        if(!insApplyData.getHeader().getProfileRequest().equals(QunarTransType.CONFIRMMOTORDEALS.getValue())
                || !insApplyData.getHeader().getFunction().equals(QunarTransType.CONFIRMMOTORDEALS.getFunction())){
//            return StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.TRADE_TYPE_ERROR));
            return insureErrorResponse(insApplyData, DouinsException.TRADE_TYPE_ERROR.getName());
        }
        
        int count = this.saveConfirmMoData(insApplyData);
        
        // 提取核保结果信息
        for(int i = 0; i < insApplyData.getConfirm().size(); i++){
            // 当前批次可以承保的数据记录
            List<InsureApplyDataVo> applyVos = qunarDataService.getApplyByOrder(insApplyData.getConfirm().get(i).getOrderNo());
            List<InsApplyStructVo> applys = null;
            try {
                // 转成 CCIC 承保请求的数据
                applys  = convertApplyData(applyVos);
            } catch (Exception e) {
                logger.error("承保数据转化错误===================", e);
//                return StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.INSURE_DATA_ERROR));
                return insureErrorResponse(insApplyData, DouinsException.INSURE_DATA_ERROR.getName());
            }
           
            // 请求承保
            if(applys != null && applys.size() > 0){
                List<InsApplyResponse> responses = Lists.newArrayList();
                try {
                    for(int j = 0; j < applys.size(); j++){
                        Map<String, Object> root = Maps.newHashMap();
                        root.put("insure", applys.get(j));
                        String xml = templateLoader.processTemplate("/ccic/insureApply.ftl", root);
                        long start2=System.currentTimeMillis();
                        logger.info("承保请求给－－大地：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml +"\n===========================");
//                        String xml2 = HttpClientUtils.httpsPostCCIC(url, xml, "GBK", CCICTransType.INSURE.getValue());
                        String xml2 = httpsConnection.httpsPostCCIC(url, xml, "GBK", CCICTransType.INSURE.getValue());
                        long end =System.currentTimeMillis();
                        logger.info("承保请求－－大地响应：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml2 +"\n========================");
                        if(xml2 == null){
                            resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.BACK_NULL));
                        }
                        
                        InsApplyResponse response = CCICDataAnalysis.getInsApplyResponse(xml2);
                        response.setOrderNo(insApplyData.getConfirm().get(i).getOrderNo());
                        response.setApplySeq(applys.get(j).getApplySeq());
                        response.setTradeNo(applys.get(j).getChannel().getChannelTradeSerialNo());
                        
                        responses.add(response);
                        logger.info("承保请求大地响应时间＝＝=＝＝"+(end-start2));
                    }
                    // 返回给 qunar 的xml
                    resXml = getInsureApplyResponse(insApplyData, responses);
                    // 保存到数据库
                    this.saveInsureResponse(responses);
                    long end2 = System.currentTimeMillis();
                    logger.info("承保总耗时＝＝=＝＝"+(end2-start));
                } catch (Exception e) {
                    logger.error("send insure data failed.", e);
//                    resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.SEND_DATA_ERROR));
                    resXml = insureErrorResponse(insApplyData, DouinsException.SEND_DATA_ERROR.getName());
                }
            }else{
//                resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.INSURE_DATA_ERROR));
                resXml = insureErrorResponse(insApplyData, DouinsException.INSURE_DATA_ERROR.getName());
            }
        }
        return resXml;
    }
    
    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.AdapterServiceIFC#doWithdraw(java.lang.Object)
     */
    public <T> String doWithdraw(T data) {
    	long start =System.currentTimeMillis();
        WithdrawReqVo reqData = (WithdrawReqVo)data;
        //  判断交易类型是否正确
        if(!reqData.getHeader().getProfileRequest().equals(QunarTransType.NETSELLREVOKECONFIRM.getValue())
                || !reqData.getHeader().getFunction().equals(QunarTransType.NETSELLREVOKECONFIRM.getFunction())){
//            return StaticTemplate.getExceptionTemplate(templateLoader, DouinsException.getErrorMap(DouinsException.TRADE_TYPE_ERROR));
            return withdrawErrorResponse(reqData, DouinsException.TRADE_TYPE_ERROR.getName());
        }
        // 保存请求数据
        this.saveWithdrawConfirmRequest(reqData);
        
        List<Map<String, String>> wds = convertWithdrawData(reqData);
        String resXml = null;
        if(wds != null && wds.size() > 0){
            try {
                List<WithdrawResponse> responses = Lists.newArrayList();
                for (Map<String, String> wd : wds) {
                    Map<String, Object> root = Maps.newHashMap();
                    root.put("withdraw", wd);
                    String xml = templateLoader.processTemplate("/ccic/withdraw.ftl", root);
                    long start2 =System.currentTimeMillis();
                    logger.info("退保确认给－－大地：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml +"\n======================");
//                    resXml = HttpClientUtils.httpsPostCCIC(url, xml, "GBK", CCICTransType.WITHDRAW.getValue());
                    resXml = httpsConnection.httpsPostCCIC(url, xml, "GBK", CCICTransType.WITHDRAW.getValue());
                    logger.info("退保确认－－大地响应：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+resXml +"\n===============");
                    long end = System.currentTimeMillis();
                    if(resXml == null){
//                        resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.BACK_NULL));
                        resXml = withdrawErrorResponse(reqData, DouinsException.BACK_NULL.getName());
                    }
                    // 数据转换
                    WithdrawResponse response = CCICDataAnalysis.getWithdrawResponse(resXml);
                    if(!response.getResponseStatus().getResponseCode().equals("00")){
                        response.getPolicy().setPolicyNo(wd.get("policyNo"));
                        response.getEndorse().setChgPremium(reqData.getGroupInfo().getRevokeAmount());
                    }
                    response.getPolicy().setTradeNo(wd.get("channelTradeSerialNo"));
                    responses.add(response);
                    logger.info("退保确认大地响应时间＝＝＝＝＝"+(end-start2));
                }
                // 封装成 qunar 的xml
                resXml = getWithdrawResponse(responses, reqData.getGroupInfo().getRevokeAmount());
                // 保存数据
                this.saveWithdrawResponse(responses);
                long ends =System.currentTimeMillis();
                logger.info("退保确认总时间＝＝＝＝＝"+(ends-start));
            } catch (Exception e) {
                logger.error("send withdraw data failed.", e);
//                resXml = StaticTemplate.getExceptionTemplate(templateLoader, DouinsException.getErrorMap(DouinsException.SEND_DATA_ERROR));
                resXml = withdrawErrorResponse(reqData, DouinsException.SEND_DATA_ERROR.getName());
            }
        }
        return resXml;
    }
    
    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.AdapterServiceIFC#applyWithdraw(java.lang.Object)
     */
    @Override
    public <T> String applyWithdraw(T data) {
        // TODO Auto-generated method stub
        WithdrawReqVo reqData = (WithdrawReqVo)data;
        //  判断交易类型是否正确
        if(!reqData.getHeader().getProfileRequest().equals(QunarTransType.NETSELLREVOKEAPPLY.getValue())
                || !reqData.getHeader().getFunction().equals(QunarTransType.NETSELLREVOKEAPPLY.getFunction())){
//            return StaticTemplate.getExceptionTemplate(templateLoader, DouinsException.getErrorMap(DouinsException.TRADE_TYPE_ERROR));
            return applyWithdrawErrorResponse(reqData, DouinsException.TRADE_TYPE_ERROR.getName());
        }
        // 保存请求数据
        this.saveWithdrawRequest(reqData);
        ResponseVo responseVo = verifyPolicyStatus(reqData);
        Map<String, Object> root = Maps.newHashMap();
        root.put("apply", responseVo);
        // 契撤申请返回的 xml
        String reXml = templateLoader.processTemplate("/qunar/withdrawApplyResponse.ftl", root);
        logger.info("退保请求给－－去哪：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n================================");
        return reXml;
    }
    
    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.AdapterServiceIFC#doBalance(java.lang.String)
     */
    @Override
    public String doBalance(String data) {
        // TODO Auto-generated method stub
        
        return null;
    }
    
    /**
     * 一核操作
     * @param uwData
     * @return
     */
    private String doFirstUW(UWStructVo uwData){
    	long start1=System.currentTimeMillis();
        String resXml = null;
        // 保存数据
        this.saveReqUwData(uwData);
        // 转成 CCIC 核保需要的数据
        List<InsUWStructVo> uw = null;
        try {
            uw = convertUWData(uwData);
        } catch (Exception e) {
            uw = null;
            logger.error("核保请求数据解析失败================", e);
        }
        
        if(uw != null && uw.size() > 0){
            List<InsUWResponse> responses = Lists.newArrayList();
            int okCount = 0;
            try {
                for (int i = 0; i < uw.size(); i++) {
                    // 去 CCIC 核保
                    Map<String, Object> root = Maps.newHashMap();
                    root.put("uw", uw.get(i));
                    String xml = templateLoader.processTemplate("/ccic/uw.ftl", root);
                    long start2 = System.currentTimeMillis();
                    logger.info("核保请求给－－大地：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml +"\n=========================");
//                    String xml2 = HttpClientUtils.httpsPostCCIC(url, xml, "GBK", CCICTransType.UW.getValue());
                    String xml2 = httpsConnection.httpsPostCCIC(url, xml, "GBK", CCICTransType.UW.getValue());
                    long end =System.currentTimeMillis();
                    logger.info("核保请求－－大地响应：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+xml2 +"\n=======================");
                    if(xml2 == null){
//                        resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.BACK_NULL));
                        resXml = uwErrorResponse(uwData, DouinsException.SEND_DATA_ERROR.getName());
                    }
                    
                    // 保存大地核保返回的数据
                    InsUWResponse response = CCICDataAnalysis.getUWResponse(xml2);
                    responses.add(response);
                    if("00".equals(response.getResponseStatus().getResponseCode())){
                        okCount++;      // 统计核保成功的记录数
                    }
                    // 源 applySeq
                    response.setApplySeq(uw.get(i).getChannel().getApplySeq());

                    logger.info("CCIC核保返回:=\n"+ xml2);
                    // 保存到数据库
                    this.saveFBUwData(response, uw.get(i).getChannel().getChannelTradeSerialNo(),
                            uw.get(i).getChannel().getApplySeq(), uw.get(i).getChannel().getChannelProductCode());
                    long end2 =System.currentTimeMillis();
                    logger.info(" 核保请求发送大地前时间＝============"+(start2-start1));
                    logger.info("大地响应时间＝＝＝＝==＝"+(end-start2));
                    logger.info("核保请求返回去哪＝＝===＝＝"+(end2-end));
                }
                String status = (okCount == uw.size()) ? "1" : "0";
                resXml = getUWResponse(uwData, responses, status);      // qunar 的 xml 格式
            } catch (Exception e) {
                logger.error("send uw data failed.", e);
//                resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.SEND_DATA_ERROR));
                resXml = uwErrorResponse(uwData, DouinsException.SEND_DATA_ERROR.getName());
            }
        }else{
            resXml = uwErrorResponse(uwData, DouinsException.UW_DATA_ERROR.getName());
        }
        
        return resXml;
    }
    
    /**
     * 二核校验
     * @param items
     * @param uwData
     */
    private void verifyUWData(List<InsureRequestFeedback> items, UWStructVo uwData){
        // 当前传来的 applySeq
        List<String> curSeqs = Lists.newArrayList();
        for(InsuredInfoVo vo: uwData.getInsuredInfoVos()){
            curSeqs.add(vo.getApplySeq());
        }
        
        if(items.size() != curSeqs.size()){
            // 可能缺失的 applySeq
            List<String> lostSeqs = Lists.newArrayList();
            List<Integer> indexes = Lists.newArrayList();
            for(int i = 0; i < items.size();i++){
                if(!curSeqs.contains(items.get(i).getApplySeq())){
                    lostSeqs.add(items.get(i).getApplySeq());     // 缺失的 applySeq
                   indexes.add(i);
                }
            }
            // 删除缺失的数据
            for(int ind : indexes){
                items.remove(ind);
            }
            
            // 更新 cfmFlg
            if(lostSeqs.size() > 0)
                qunarDataService.updateCfmFlagByOrderNoAndSeq("0", uwData.getGroupInfo().getOrderNo(), lostSeqs);
        }
        // 两次都有的 applySeq
        List<String> readySeqs = Lists.newArrayList();
        for (InsureRequestFeedback it : items) {
            readySeqs.add(it.getApplySeq());
        }
        if (readySeqs.size() > 0) {
            qunarDataService.updateCfmFlagByOrderNoAndSeq("1", uwData.getGroupInfo().getOrderNo(), readySeqs);
        }
    }
    
    // ******** define your private function below ********
    /**
     * 渠道间的核保数据转换 Qunar ---> CCIC
     * @param uwData
     * @return
     */
    private List<InsUWStructVo> convertUWData(UWStructVo uwData){
        List<InsUWStructVo> insUWDatas = Lists.newArrayList();
        
        for(int i = 0; i < uwData.getInsuredInfoVos().size(); i ++){
            InsUWStructVo insData = new InsUWStructVo();
            // 保险的起止时间
            String start = uwData.getInsuredInfoVos().get(i).getEffDate();
            String end = uwData.getInsuredInfoVos().get(i).getMatuDate();
            int days = getDays(start, end);
//            String saleStrategy = CCICSaleStrategyType.getTypeCodeByDays(days);
            // 条款信息
            float sumAmount = 0.0f;
            float sumPremium = 0.0f;
//            List<ProductVo> items = ccicDataService.getProductInfos(ChannelEnum.QUNAR.getCode(), uwData.getInsuredInfoVos().get(i).getProductNo(), saleStrategy);                   // 拉取条款列表
            List<ProductVo> items = ccicDataService.getProductInfos(ChannelEnum.QUNAR.getCode(), uwData.getInsuredInfoVos().get(i).getProductNo(), days);
            List<ItemKind> itemKindList = Lists.newArrayList();
            for(ProductVo vo : items){
                ItemKind kind = new ItemKind(vo);
                itemKindList.add(kind);
                sumAmount += Float.valueOf(vo.getAmount());
                sumPremium += Float.valueOf(vo.getPremium());
            }
            insData.setItemKindList(itemKindList);
            // 渠道信息
            ChannelInfo channel = new ChannelInfo();
            channel.setChannelTradeCode(CCICTransType.UW.getValue());
            channel.setChannelTradeDate(DateTimeUtils.formateDateTime(new Date()));
            channel.setChannelTradeSerialNo(uwData.getInsuredInfoVos().get(i).getTransNo());
            channel.setChannelProductCode(items.get(0).getInsurePdId());
            channel.setApplySeq(uwData.getInsuredInfoVos().get(i).getApplySeq());
            insData.setChannel(channel); 
            // main
            UWmain uwmain = new UWmain();
            uwmain.setStartDate(DateTimeUtils.formateDate(start.substring(0, 8)));
//            uwmain.setStartHour(start.substring(8));      // 默认 0 点
            uwmain.setEndDate(DateTimeUtils.formateDate(end.substring(0, 8)));       
//            uwmain.setEndHour(end.substring(8));       // 默认 24 点
            uwmain.setSumQuantity(uwData.getInsuredInfoVos().get(i).getUnit());
            uwmain.setSumAmount(String.valueOf((int)sumAmount));
            uwmain.setSumPremium(String.valueOf((int)sumPremium));
            insData.setUwMain(uwmain);
            // 投保人
            Applicant applicant = new Applicant();
            applicant.setAppliName(uwData.getInsuredInfoVos().get(i).getInsurerInfo().getInsurerName());
            applicant.setIdentifyNumber(uwData.getInsuredInfoVos().get(i).getInsurerInfo().getInsurerIdNo());
            String birthday = uwData.getInsuredInfoVos().get(i).getInsurerInfo().getInsurerBirthday();
            applicant.setBirthDay(DateTimeUtils.formateDate(birthday));
            applicant.setInsuredIdentity(uwData.getInsuredInfoVos().get(i).getInsurerInfo().getInsurantRelationship());
            applicant.setEmail(uwData.getInsuredInfoVos().get(i).getInsurerInfo().getInsurerEmail());
            String id = getIdTypeCode(uwData.getInsuredInfoVos().get(i).getInsurerInfo().getInsurerIdType());
            applicant.setIdentifyType(id);
            insData.setApplicant(applicant);
            // 被保人
            Insured insured = new Insured();
            insured.setInsuredName(uwData.getInsuredInfoVos().get(i).getInsurantInfo().getInsurantName());
            String id2 = getIdTypeCode(uwData.getInsuredInfoVos().get(i).getInsurantInfo().getInsurantIdType());
            insured.setIdentifyType(id2);
            insured.setIdentifyNumber(uwData.getInsuredInfoVos().get(i).getInsurantInfo().getInsurantIdNo());
            String birthday2 = uwData.getInsuredInfoVos().get(i).getInsurantInfo().getInsurantBirthday();
            insured.setBirthDay(DateTimeUtils.formateDate(birthday2));
            List<Insured> insureds = Lists.newArrayList();
            insureds.add(insured);
            insData.setInsureds(insureds);
            
            insUWDatas.add(insData);
        }
        
        return insUWDatas;
    }
    
    /**
     * 渠道间的承保数据转换  Qunar ---> CCIC
     * @param uwResults
     * @param insApplyData 
     * @return
     */
    private List<InsApplyStructVo> convertApplyData(List<InsureApplyDataVo> uwResults){
        List<InsApplyStructVo> insApplyDatas = Lists.newArrayList();
        
        for(int i=0;i<uwResults.size();i++){
            InsApplyStructVo insApply = new InsApplyStructVo();
            // 渠道信息
            ChannelInfo channel = new ChannelInfo();
            channel.setChannelTradeCode(CCICTransType.INSURE.getValue());
            channel.setChannelTradeDate(DateTimeUtils.formateDateTime(new Date()));
            String tradeNo = SeqNoUtils.geneTransNo(Constants.QUNAR_CCIC, Constants.APPLY_TRANS);
            channel.setChannelTradeSerialNo(tradeNo);
            channel.setChannelProductCode(uwResults.get(i).getProductCode());
            insApply.setChannel(channel);
            // 投保单信息
//            insApply.setPrintNo(uw.getPrintNo());
            insApply.setBusinessNo(uwResults.get(i).getProposalNo());
//            insApply.setOriginTradeSerialNo(uw.getTradeNo());
            insApply.setApplySeq(uwResults.get(i).getApplySeq());
            
            insApplyDatas.add(insApply);
        }
        
        return insApplyDatas;
    }
    
    /**
     * 计算投保的天数
     * @param start
     * @param end
     * @return
     */
    private int getDays(String start, String end){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        int days = 0;
        try {
            Date sDate = format.parse(start);
            Date eDate = format.parse(end);
            days = (int)((eDate.getTime() - sDate.getTime()) / 86400000) + 1;
        } catch (ParseException e) {
            logger.error("Date convert error.", e);
        }
       
        return days;
    }
    
    /**
     * CCIC 退保需要的数据
     * @param reqVo
     * @return
     */
    private List<Map<String, String>> convertWithdrawData(WithdrawReqVo reqVo){
        List<Map<String, String>> datas = Lists.newArrayList();
        for(int i = 0; i < reqVo.getParamparamList().size(); i++){
            Map<String, String> param = Maps.newHashMap();
            param.put("channelTradeDate", DateTimeUtils.formateDateTime(new Date()));     
            param.put("channelTradeSerialNo", reqVo.getParamparamList().get(i).getTradeNo());       
            param.put("policyNo", reqVo.getParamparamList().get(i).getPolicyNo());
            datas.add(param);
        }
        
        return datas;
    }
    
    /**
     * 给 qunar 的核保返回数据
     * @param uwData
     * @param response
     * @param status
     * @return
     */
    private String getUWResponse(UWStructVo uwData, List<InsUWResponse> response, String status){
        ResponseVo uw = new ResponseVo();
        uw.setAgencyNo(uwData.getGroupInfo().getAgencyNo());
        uw.setBusinessId(uwData.getGroupInfo().getBusinessId());
        uw.setOrderNo(uwData.getGroupInfo().getOrderNo());
        uw.setStatus(status);
        
        List<ResponseRecord> records = Lists.newArrayList();
        for(InsUWResponse res : response){
            ResponseRecord record = new ResponseRecord();
            record.setApplyPolicyNo(res.getPolicy().getProposalNo());
            record.setApplySeq(res.getApplySeq());
            record.setPayPremium(res.getPolicy().getPremium());
            record.setErrMsg(res.getResponseStatus().getResponseMessage());
//            record.setResponseMessage(res.getResponseStatus().getResponseMessage());
            if("00".equals(res.getResponseStatus().getResponseCode())){
                record.setResponseCode("1");
            }else{
                record.setResponseCode("0");
            }
            records.add(record);
        }
        
        uw.setRecords(records);
        Map<String, Object> root = Maps.newHashMap();
        root.put("uw", uw);
        String reXml = templateLoader.processTemplate("/qunar/uwResponse.ftl", root);
        logger.info("核保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n========================");
        return reXml;
    }
    
    /**
     * 二核的返回结果
     * @param uwData
     * @param items
     * @return
     */
    private String getUWResponse2(UWStructVo uwData, List<InsureRequestFeedback> items){
        int okCount = 0;
        ResponseVo uw = new ResponseVo();
        BeanUtils.copyProperties(uwData.getGroupInfo(), uw);
        String reXml = null;
        
        try{
            List<ResponseRecord> records = Lists.newArrayList();
            for (InsureRequestFeedback it : items) {
                ResponseRecord record = new ResponseRecord();
                record.setApplyPolicyNo(it.getProposalNo());
                record.setApplySeq(it.getApplySeq());
                record.setPayPremium(it.getPremium());
                // record.setResponseMessage(it.getResponseMessage());

                if ("00".equals(it.getResponseCode())) {
                    okCount++;
                    record.setResponseCode("1");
                } else {
                    record.setResponseCode("0");
                }
                records.add(record);
            }
            uw.setRecords(records);
            String status = (okCount == items.size()) ? "1" : "0";
            uw.setStatus(status);
            
            Map<String, Object> root = Maps.newHashMap();
            root.put("uw", uw);
            reXml = templateLoader.processTemplate("/qunar/uwResponse.ftl", root);
        }catch(Exception e){
            logger.error("二核失败=============", e);
            reXml = uwErrorResponse(uwData, DouinsException.UW_DATA_ERROR.getName());
        }

        logger.info("二次核保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n============================");
        return reXml;
    }
    
    /**
     * 承保的响应
     * @param applyVo
     * @param responses
     * @return
     */
    private String getInsureApplyResponse(InsureApplyVo applyVo, List<InsApplyResponse> responses){
        ResponseVo insure = new ResponseVo();
        BeanUtils.copyProperties(applyVo.getConfirm().get(0), insure);
        List<ResponseRecord> records = Lists.newArrayList();
        int okCount = 0;
        
        for(InsApplyResponse res : responses){
            ResponseRecord record = new ResponseRecord();
            record.setApplyPolicyNo(res.getPolicy().getProposalNo());
            record.setPolicyNo(res.getPolicy().getPolicyNo());

            if(res.getResponseStatus().getResponseCode().equals("00")){
                
                okCount++;
                record.setResponseCode("1");
            }else{
                record.setResponseCode("0");
            }
            
            records.add(record);
        }
        insure.setRecords(records);
        String status = (okCount == responses.size()) ? "1" : "0";
        insure.setStatus(status);
        
        Map<String, Object> root = Maps.newHashMap();
        root.put("insure", insure);
        String reXml = templateLoader.processTemplate("/qunar/insuredResponse.ftl", root);    
        logger.info("承保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n============================");
        return reXml;
    }
    
    /**
     * 返回给 Qunar 的退保／契撤 xml 报文
     * @param responses
     * @return
     */
    private String getWithdrawResponse(List<WithdrawResponse> responses, String revok){
        int okCount = 0;
        ResponseVo responseVo = new ResponseVo();
        List<ResponseRecord> records = Lists.newArrayList();
        for(WithdrawResponse res : responses){
            ResponseRecord record = new ResponseRecord();
            record.setPolicyNo(res.getPolicy().getPolicyNo());
            if(res.getEndorse().getChgPremium().contains("-")){
                String charge = res.getEndorse().getChgPremium().substring(1);
                record.setRevokeAmount(charge);
            }else{
                record.setRevokeAmount(res.getEndorse().getChgPremium());
            }
            
            record.setRevokeDate(DateTimeUtils.formateDateTime2(new Date()));
            if(res.getResponseStatus().getResponseCode().equals("00")){
                record.setResponseCode("01");
                okCount++;
            }else if (res.getResponseStatus().getResponseMessage().startsWith("该保单无效！")) {
                // 重复退保，且上次退保已经成功
                List<String> policyNos = Lists.newArrayList();
                policyNos.add(res.getPolicy().getPolicyNo());
                List<PolicyFact> policyInfo = douinsDataService.findPolicyByPolicyNo(ChannelEnum.QUNAR.getCode(), InsuranceEnum.CCIC.getCode(), policyNos);
                if(policyInfo != null && policyInfo.size() > 0){
                    Date now = new Date();
                    Date start = DateTimeUtils.getDateFromString2(policyInfo.get(0).getStartDate());
                    if(now.before(start)){
                        record.setResponseCode("01");
                        record.setRevokeAmount(policyInfo.get(0).getPremium());     // 保费
                        okCount++;
                    }
                }
            } else {
                record.setResponseCode("02");
                record.setErrMsg(res.getResponseStatus().getResponseMessage());
                record.setRevokeAmount(revok);
            }
            
            records.add(record);
        }
        
        if(okCount == responses.size()){
            responseVo.setStatus("1");
        }else {
            responseVo.setStatus("0");
        }
        responseVo.setRecords(records);
        
        Map<String, Object> root = Maps.newHashMap();
        root.put("apply", responseVo);
        String reXml = templateLoader.processTemplate("/qunar/withdrawResponse.ftl", root);
        logger.info("退保确认返回给去哪数据:==================\n"+reXml +"\n===========================");
        return reXml;
    }
    
    // ***************** 数据落地 *******************************
    /*
     * 去哪－核保请求数据存储
     */
    private void saveReqUwData(UWStructVo uwData){
    	// 消息头信息
    	InsureRequestHeader header = new InsureRequestHeader();
    	header.setTranCode(uwData.getHeader().getTran_Code());
    	header.setChnlNo(ChannelEnum.QUNAR.getCode());
    	BeanUtils.copyProperties(uwData.getHeader(), header);
    	BeanUtils.copyProperties(uwData.getGroupInfo(), header);
    	
    	//报文主体信息
    	List<InsureRequestItem> items = Lists.newArrayList();
    	for(InsuredInfoVo vo : uwData.getInsuredInfoVos()){
    		String transNo = SeqNoUtils.geneTransNo(Constants.QUNAR_CCIC, Constants.UW_TRANS);
    		vo.setTransNo(transNo);
    		InsureRequestItem item = new InsureRequestItem();
    		item.setOrderNo(uwData.getGroupInfo().getOrderNo());
    		item.setTradeNo(transNo);
    		BeanUtils.copyProperties(vo, item);
    		BeanUtils.copyProperties(vo.getInsurantInfo(), item);
    		BeanUtils.copyProperties(vo.getInsurerInfo(), item);
    		BeanUtils.copyProperties(vo.getLinkManInfo(), item);
    		items.add(item);
    	}
    	
    	qunarDataService.saveInsureReq(header, items);
    }
    /*
     * 大地－核保请求响应数据存储
     */
    public  void saveFBUwData(InsUWResponse res, String tradeNo, String applySeq, String productNo){
    	InsureRequestFeedback feedback = new InsureRequestFeedback();
    	BeanUtils.copyProperties(res.getResponseStatus(), feedback);
    	BeanUtils.copyProperties(res.getPolicy(), feedback);
    	feedback.setTradeNo(tradeNo);
    	feedback.setProductCode(productNo);
    	feedback.setApplySeq(applySeq);
    	
    	InsureRequestItem item = new InsureRequestItem();
    	item.setStatu((res.getResponseStatus().getResponseCode().equals("00"))?"1":"0");
    	item.setTradeNo(tradeNo);
    	qunarDataService.updateStatu(item);
    	ccicDataService.saveInsureReqFeedback(feedback);
    }
    /*
     * 去哪－承保请求数据存储
     */
    private int  saveConfirmMoData(InsureApplyVo data) {
		//消息头信息
    	InsureConfirmHeader head = new InsureConfirmHeader();
    	head.setTranCode(data.getHeader().getTran_Code());
    	head.setChnlNo(ChannelEnum.QUNAR.getCode());
    	BeanUtils.copyProperties(data.getHeader(), head);
    	
    	//报文主体信息
    	List<InsureConfirmItem>  items =Lists.newArrayList();
    	for(PolicyConfirm pc : data.getConfirm()){
    		InsureConfirmItem item = new InsureConfirmItem();
    		String transNo = SeqNoUtils.geneTransNo(Constants.QUNAR_CCIC, Constants.APPLY_TRANS);
    		pc.setTradeNo(transNo);
    		item.setTradeNo(transNo);
    		BeanUtils.copyProperties(pc, item);
    		items.add(item);
    	}
    	
    	return qunarDataService.saveInsureCfm(head, items);
	}
    
    /**
     * 保存承保返回的结果数据
     * @param tradeNo 
     * @param response
     */
    private void saveInsureResponse(List<InsApplyResponse> responses){
        List<InsureConfirmFeedback> fbs = Lists.newArrayList();
        List<PolicyFact> policys = Lists.newArrayList();        // 保单
        for(InsApplyResponse res : responses){
            InsureConfirmFeedback fb = new InsureConfirmFeedback();
            BeanUtils.copyProperties(res.getResponseStatus(), fb);
            BeanUtils.copyProperties(res.getPolicy(), fb);
            fb.setTradeNo(res.getTradeNo());
            fb.setOrderNo(res.getOrderNo());
            fb.setApplySeq(res.getApplySeq());
            // 投保人信息
            fb.setAppliName(res.getApplicant().getAppliName());
            fb.setIdentifyNumber(res.getApplicant().getIdentifyNumber());
            fb.setIdentifyType(res.getApplicant().getIdentifyType());
            fb.setIdentifyPeriod(res.getApplicant().getIdentifyPeriod());
            fb.setBirthDay(res.getApplicant().getBirthDay());
            fb.setAge(res.getApplicant().getAge());
            fb.setSex(res.getApplicant().getSex());
            fb.setInsuredIdentity(res.getApplicant().getInsuredIdentity());
            fb.setAppliAddress(res.getApplicant().getAppliAddress());
            fb.setMobile(res.getApplicant().getMobile());
            fb.setEmail(res.getApplicant().getEmail());
            fb.setAccount(res.getApplicant().getAccount());
            fb.setAccountNo(res.getApplicant().getAccountNo());
            fb.setBank(res.getApplicant().getBank());
            //被保人信息
            fb.setInsuredName(res.getInsured().getInsuredName());
            fb.setInsuredIdentifyType(res.getInsured().getIdentifyType());
            fb.setInsuredIdentifyNumber(res.getInsured().getIdentifyNumber());
            fb.setInsuredIdentifyPeriod(res.getInsured().getIdentifyPeriod());
            fb.setInsuredBirthDay(res.getInsured().getBirthDay());
            fb.setInsuredAge(res.getInsured().getAge());
            fb.setInsuredEmail(res.getInsured().getEmail());
            fb.setInsuredSex(res.getInsured().getSex());
            fb.setAppliIdentity(res.getInsured().getAppliIdentity());
            fb.setInsuredAddress(res.getInsured().getInsuredAddress());
            fb.setPostCode(res.getInsured().getPostCode());
            fb.setInsuredPhoneNumber(res.getInsured().getPhoneNumber());
            fb.setOccupationCode(res.getInsured().getOccupationCode());
            fb.setInsuredMobile(res.getInsured().getMobile());
            fb.setInsuredEmail(res.getInsured().getEmail());
            
            if(res.getExpand() !=  null && "ePolicyURL".equals(res.getExpand().getKey())){
                fb.setePolicyURL(res.getExpand().getValue());
            }
//            BeanUtils.copyProperties(res.getExpand(), fb);
            fbs.add(fb);
            
            // 保单
            PolicyFact policy = new PolicyFact();
            policy.setAmount(fb.getAmount());
            policy.setChannlCode(ChannelEnum.QUNAR.getCode());
            policy.setEndDate(fb.getEndDate());
            policy.setInsureCode(InsuranceEnum.CCIC.getCode());
            policy.setPolicyNo(fb.getPolicyNo());
            policy.setPremium(fb.getPremium());
//            policy.setProductId(fb.getpeo);
            policy.setStartDate(fb.getStartDate());
            if(fb.getResponseCode().equals("00")){
                policy.setStatus(PolicyStatus.UN_EFFECTIVE.getValue());
            }
            policys.add(policy);
        }
        ccicDataService.saveInsureCfmFeedback(fbs);
        douinsDataService.savePolicyFact(policys);
    }
    
    /**
     * 保存退保申请的数据
     * @param reqVo
     */
    private void saveWithdrawRequest(WithdrawReqVo reqVo){
        InsureCancelApplyHeader header = new InsureCancelApplyHeader();
        header.setAgencyNo(reqVo.getGroupInfo().getAgencyNo());
        header.setApplyDate(reqVo.getGroupInfo().getApplyDate());
        header.setBusinessId(reqVo.getGroupInfo().getBusinessId());
        header.setChnlNo(ChannelEnum.QUNAR.getCode());
        header.setDocumentId(reqVo.getHeader().getDocumentId());
        header.setFunc(reqVo.getHeader().getFunction());
        header.setProfileRequest(reqVo.getHeader().getProfileRequest());
        header.setSrc(reqVo.getHeader().getFrom());
        header.setTarget(reqVo.getHeader().getTo());
        header.setTranCode(reqVo.getHeader().getTran_Code());
        
        long id = qunarDataService.saveWithdrawApplyHeader(header);
 
        List<InsureCancelApplyItem> items = Lists.newArrayList();
        for (int i = 0; i < reqVo.getParamparamList().size(); i++) {
            InsureCancelApplyItem item = new InsureCancelApplyItem();
            item.setHeadId(String.valueOf(id));
            item.setPolicyNo(reqVo.getParamparamList().get(i).getPolicyNo());
            
            items.add(item);
        }
        
        qunarDataService.saveWithdrawApplyItems(items);
    }
    
    /**
     * 保存退保确认的数据
     * @param reqVo
     */
    private void saveWithdrawConfirmRequest(WithdrawReqVo reqVo){
        InsureCancelConfirmHeader header = new InsureCancelConfirmHeader();
        header.setAgencyNo(reqVo.getGroupInfo().getAgencyNo());
        header.setBusinessId(reqVo.getGroupInfo().getBusinessId());
        header.setChnlNo(ChannelEnum.QUNAR.getCode());
        header.setDocumentId(reqVo.getHeader().getDocumentId());
        header.setFunc(reqVo.getHeader().getFunction());
        header.setProfileRequest(reqVo.getHeader().getProfileRequest());
        header.setSrc(reqVo.getHeader().getFrom());
        header.setTarget(reqVo.getHeader().getTo());
        header.setTranCode(reqVo.getHeader().getTran_Code());
        header.setBankDealDate(reqVo.getGroupInfo().getBankDealDate());
        header.setBankNo(reqVo.getGroupInfo().getBankNo());
        header.setPayType(reqVo.getGroupInfo().getPayType());
        
        long id = qunarDataService.saveWithdrawConfirmHeader(header);
 
        List<InsureCancelConfirmItem> items = Lists.newArrayList();
        for (int i = 0; i < reqVo.getParamparamList().size(); i++) {
            InsureCancelConfirmItem item = new InsureCancelConfirmItem();
            String transNo = SeqNoUtils.geneTransNo(Constants.QUNAR_CCIC, Constants.UW_TRANS);
            reqVo.getParamparamList().get(i).setTradeNo(transNo);
    		item.setTradeNo(transNo);
            item.setHeadId(String.valueOf(id));
            item.setPolicyNo(reqVo.getParamparamList().get(i).getPolicyNo());
            
            items.add(item);
        }
        
        qunarDataService.saveWithdrawConfirmItems(items);
    }
    
    /**
     * 保存CCIC的退保响应数据
     * @param responses
     */
    private void saveWithdrawResponse(List<WithdrawResponse> responses){
        List<InsureCancelConfirmFeedback> items = Lists.newArrayList();
        List<String> okPolicys = Lists.newArrayList();
        for(WithdrawResponse res : responses){
            InsureCancelConfirmFeedback item = new InsureCancelConfirmFeedback();
            BeanUtils.copyProperties(res.getResponseStatus(), item);
            BeanUtils.copyProperties(res.getEndorse(), item);
            BeanUtils.copyProperties(res.getPolicy(), item);
            
            items.add(item);
            // 退保成功的保单
            if(res.getResponseStatus().getResponseCode().equals("00")){
                okPolicys.add(res.getPolicy().getPolicyNo());
            }
        }
        
        ccicDataService.saveInsureCancelCfmFeedback(items);
        // 更新保单状态
        douinsDataService.updatePolicyFactByPolicyNo(okPolicys, PolicyStatus.WITHDRAW.getValue());
    }
    
    /**
     * 检查保单状态
     * @param reqVo
     * @return
     */
    private ResponseVo verifyPolicyStatus(WithdrawReqVo reqVo){
        // 提取要退保的保单信息
        List<String> policyNos = Lists.newArrayList();
        for(int i = 0; i < reqVo.getParamparamList().size(); i++){
            policyNos.add(reqVo.getParamparamList().get(i).getPolicyNo());
        }
        // 查询保单信息
        List<PolicyFact> policys = douinsDataService.findPolicyByPolicyNo(ChannelEnum.QUNAR.getCode(), InsuranceEnum.CCIC.getCode(), policyNos);
        int okCount = 0;
        List<ResponseRecord> records = Lists.newArrayList();
        for(PolicyFact p: policys){
            ResponseRecord record = new ResponseRecord();
            record.setPolicyNo(p.getPolicyNo());
            record.setRevokeAmount(p.getPremium());
            record.setRevokeDate(DateTimeUtils.formateDateTime2(new Date()));
            
            if(p.getStatus().equals(PolicyStatus.EXPIRED.getValue())){
                record.setResponseCode("03");
            }if(p.getStatus().equals(PolicyStatus.WITHDRAW.getValue())){
                record.setResponseCode("04");
            }else{
             // 查看保单是否生效或过期
                Date now = new Date();
                Date startDate = DateTimeUtils.getDateFromString(p.getStartDate());
                Date endDate = DateTimeUtils.getDateFromString(p.getEndDate());
                if(now.before(startDate)){
                    record.setResponseCode("01");
                    okCount++;
                    p.setStatus(PolicyStatus.APPLY_WITHDRAW.getValue());
                }else if(now.after(endDate)){
                    record.setResponseCode("03");
                    p.setStatus(PolicyStatus.EXPIRED.getValue());
                }else{
                    record.setResponseCode("02");
                    p.setStatus(PolicyStatus.NORMAL.getValue());
                }
            }
            
            records.add(record);
        }//! for
        
        ResponseVo responseVo = new ResponseVo();
        if(okCount == policyNos.size()){
            responseVo.setStatus("1");
        }else{
            responseVo.setStatus("0");
        }
        responseVo.setRecords(records);
        
        // 更新保单状态
        douinsDataService.updatePolicyFact(policys, PolicyStatus.APPLY_WITHDRAW.getValue());
        
        return responseVo;
    }
    
    /**
     * 证件类型代码的转换
     * @param qunarCode
     * @return
     */
    private String getIdTypeCode(String qunarCode){
        String douinsCode = QunarIdType.getCodeByValue(qunarCode);
        String ccicIdCode = CCICIdType.getValueByCode(douinsCode);
        return ccicIdCode;
    }
    
    /**
     * 检查重复的 orderNO
     * @param orderNo
     * @return
     */
    private String checkOrderNo(String orderNo){
        String resXml = null;
     // 记录当前订单号
        if(orderNos.contains(orderNo)){
            resXml = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.UW_DATA_DUPLICATE));
        }else{
            // 只保留 20 条记录
            while(orderNos.size() > 20){
                orderNos.remove(0);
            }
            
            orderNos.add(orderNo);
            resXml = "ok";
        }
        
        return resXml;
    }
    
    // ************************ 数据解析失败 / 异常，返回的报文内容 ***************************
    /**
     * 核保出现异常时返回的数据
     * @param data
     * @param errMsg
     * @return
     */
    private String uwErrorResponse(UWStructVo data, String errMsg){
        ResponseVo uw = new ResponseVo();
        uw.setAgencyNo(data.getGroupInfo().getAgencyNo());
        uw.setBusinessId(data.getGroupInfo().getBusinessId());
        uw.setOrderNo(data.getGroupInfo().getOrderNo());
        uw.setStatus("0");
        
        List<ResponseRecord> records = Lists.newArrayList();
        if(data.getInsuredInfoVos().size() > 0){
            for(InsuredInfoVo info : data.getInsuredInfoVos()){
                ResponseRecord record = new ResponseRecord();
                record.setApplySeq(info.getApplySeq());
                record.setErrMsg(errMsg);
                record.setResponseCode("0");
                
                records.add(record);
            }
        }
        
        uw.setRecords(records);
        Map<String, Object> root = Maps.newHashMap();
        root.put("uw", uw);
        String reXml = templateLoader.processTemplate("/qunar/uwResponse.ftl", root);
        logger.info("核保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n========================");
        return reXml;
    }
    
    /**
     * 承保异常时返回的数据
     * @param data      请求报文解析出的数据
     * @param errMsg    错误信息
     * @return
     */
    private String insureErrorResponse(InsureApplyVo data, String errMsg){
        ResponseVo insure = new ResponseVo();
        List<ResponseRecord> records = Lists.newArrayList();
        for(int i = 0; i < data.getConfirm().size(); i++){
            // 当前批次可以承保的数据记录
            List<InsureApplyDataVo> applyVos = qunarDataService.getApplyByOrder(data.getConfirm().get(i).getOrderNo());
            if(applyVos != null && applyVos.size() > 0){
                for(InsureApplyDataVo apply : applyVos){
                    ResponseRecord record = new ResponseRecord();
                    record.setApplyPolicyNo(apply.getProposalNo());
                    record.setResponseCode("0");
                    record.setErrMsg(errMsg);
                    
                    records.add(record);
                }
            }else {
                insure.setErrMsg("没有核保通过的数据，不可以承保");
            }
            
            insure.setRecords(records);
            insure.setAgencyNo(data.getConfirm().get(i).getAgencyNo());
            insure.setBusinessId(data.getConfirm().get(i).getBusinessId());
            insure.setOrderNo(data.getConfirm().get(i).getBusinessId());
            insure.setStatus("0");
        }
        
        Map<String, Object> root = Maps.newHashMap();
        root.put("insure", insure);
        String reXml = templateLoader.processTemplate("/qunar/insuredResponse.ftl", root);    
        logger.info("承保请求返回给去哪数据：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n============================");
        return reXml;
    }
    
    /**
     * 退保申请异常时返回的数据
     * @param data
     * @param errMsg
     * @return
     */
    private String applyWithdrawErrorResponse(WithdrawReqVo data, String errMsg){
        ResponseVo reqData = new ResponseVo();
        List<ResponseRecord> records = Lists.newArrayList();
        reqData.setRecords(records);
        reqData.setErrMsg(errMsg);
        
        Map<String, Object> root = Maps.newHashMap();
        root.put("apply", reqData);
        // 契撤申请返回的 xml
        String reXml = templateLoader.processTemplate("/qunar/withdrawApplyResponse.ftl", root);
        logger.info("退保请求给－－去哪：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reXml +"\n================================");
        return reXml;
    }
    
    /**
     * 退保确认异常时返回的数据
     * @param data
     * @param errMsg
     * @return
     */
    private String withdrawErrorResponse(WithdrawReqVo data, String errMsg){
        ResponseVo responseVo = new ResponseVo();
        List<ResponseRecord> records = Lists.newArrayList();
        if(data.getParamparamList() != null && data.getParamparamList().size() > 0){
            for(PolicyInfo info : data.getParamparamList()){
                ResponseRecord record = new ResponseRecord();
                record.setResponseCode("02");
                record.setRevokeAmount(data.getGroupInfo().getRevokeAmount());
                record.setPolicyNo(info.getPolicyNo());
                record.setRevokeDate(DateTimeUtils.formateDateTime2(new Date()));
                record.setErrMsg(errMsg);
                
                records.add(record);
            }
        }
        
        responseVo.setStatus("0");
        responseVo.setRecords(records);
        
        Map<String, Object> root = Maps.newHashMap();
        root.put("apply", responseVo);
        String reXml = templateLoader.processTemplate("/qunar/withdrawResponse.ftl", root);
        logger.info("退保确认返回给去哪数据:==================\n"+reXml +"\n===========================");
        return reXml;
    }
}

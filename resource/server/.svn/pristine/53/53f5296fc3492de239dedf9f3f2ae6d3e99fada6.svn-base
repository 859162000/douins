/**
 * 
 */
package com.douins.bank.service.iml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.wsdl.http.UrlEncoded;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.bson.util.StringRangeSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.douins.account.dao.UserAccountDetailDao;
import com.douins.account.dao.UserDao;
import com.douins.account.dao.UserPayAccountDao;
import com.douins.account.domain.enums.AccountStaus;
import com.douins.account.domain.enums.AccountType;
import com.douins.account.domain.enums.UserStatus;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.domain.model.UserPayAccount;
import com.douins.account.domain.vo.UserInfoVo;
import com.douins.account.service.iml.UserAccountServiceImpl;
import com.douins.account.service.iml.UserPayAccountServiceImpl;
import com.douins.account.service.iml.UserServiceImpl;
import com.douins.bank.dao.BankResponeDao;
import com.douins.bank.domain.enums.AccStatusCode;
import com.douins.bank.domain.enums.BankTransType;
import com.douins.bank.domain.enums.BankType;
import com.douins.bank.domain.enums.gfbc.GFcertiType;
import com.douins.bank.domain.enums.gfbc.GFtradeCode;
import com.douins.bank.domain.model.BankResponseModel;
import com.douins.bank.domain.model.gfbc.AbstractResposeGF;
import com.douins.bank.domain.model.gfbc.BQResponse;
import com.douins.bank.domain.model.gfbc.RRQResponse;
import com.douins.bank.domain.model.gfbc.SBLQResponse;
import com.douins.bank.domain.model.gfbc.SECResponse;
import com.douins.bank.domain.model.gfbc.TPResponse;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.bank.domain.model.nybc.ProjecteRegistRequestBody;
import com.douins.bank.domain.vo.RegistAccountResponseVo;
import com.douins.bank.service.BankServiceIFC;
import com.douins.common.template.TemplateLoader;
import com.douins.common.util.DateTimeUtils;
import com.douins.common.util.HttpClientUtils2;
import com.douins.common.util.ReadConfig;
import com.douins.common.util.SeqNumUtils;
import com.douins.common.util.XmlUtil;
import com.douins.trans.domain.enums.ResponseCode;
import com.google.common.collect.Maps;
import com.mango.exception.DataBaseAccessException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/** 
* @ClassName: GFBankService 
* @Description: 广发银行业务处理
* @author G. F. 
* @date 2016年1月20日 上午9:48:00 
*  
*/
@Service("gfbcService")
public class GFBankService implements BankServiceIFC {
    private Logger logger = Logger.getLogger(GFBankService.class);
    
    @Resource
    private UserServiceImpl userService;
    @Resource
    private UserAccountServiceImpl accountService;
    @Resource
    private UserPayAccountServiceImpl payAccountService;
    @Resource
    private TemplateLoader templateLoader;
    @Inject
    private BankResponeDao bankResponeDao;
    @Resource
    private UserAccountDetailDao userAccountDetailDao;
    
    private static final String  instId = "955081180000001";        // 商户号
    private static final String bAccount = "101001504010008346";    // 商户账号
    private static String pubKeyFilePath;
    private static String privKeyFilePath;
    private static String privKeyPassword;
    private static String pubKeyPassword;
    private static String url;
    private static String registUrl;
    private static final String pattern = "[\\s]";
    private static final String tagDigestValue = "ds:DigestValue";
    private static final String tagSignatureValue = "ds:SignatureValue";
    private static String wapUrl;
    private static final String channelId ="118";
    
    static{
        pubKeyFilePath = ReadConfig.get("key_pub_path_gfbc");
        privKeyFilePath = ReadConfig.get("key_priv_path_gfbc");
        privKeyPassword = ReadConfig.get("key_priv_password_gfbc");
        pubKeyPassword = ReadConfig.get("key_pub_password_gfbc");
        url = ReadConfig.get("url_gfbc");
        registUrl = ReadConfig.get("url_regist_gfbc");
        wapUrl = ReadConfig.get("wap_regist_gfbc");
    }
    
    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#loadPrivateKey()
     */
    @Override
    public String loadPrivateKey() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#loadPublicKey()
     */
    @Override
    public String loadPublicKey() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#loadBankPublicKey()
     */
    @Override
    public String loadBankPublicKey() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#verifyIdentity(com.douins.account.domain.model.User)
     */
    @Override
    public ResponseCode verifyIdentity(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    /* 开户
     * @see com.douins.bank.service.BankServiceIFC#openAccount(com.douins.account.domain.model.User)
     */
    @Override
    public RegistAccountResponseVo openAccount(User user) {
        // TODO Auto-generated method stub
        String reqTime = DateTimeUtils.formateDateTime2(new Date());
        //拼接签名参数
        String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.REGIST_ACC.getValue());
        //String params = catSignParams(user, tradeNo, "RSREG", null, reqTime);
        String params = catSignParamsWap(user,tradeNo,"11561181001001318479");
//        String sign = signForRequest(params);
//        // 拼接url参数
//        String urlParams = catUrlParams(user, tradeNo, "RSREG", reqTime, null, sign);
        try {
            String reString = HttpClientUtils2.sendData_Get(registUrl, params, "UTF-8");
            String temp = new String(reString.getBytes("ISO-8859-1"),"UTF-8");
            logger.info(temp);
        } catch (IOException e) {
            logger.error("GF 开户错误", e);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#queryAccount(com.douins.account.domain.model.User)
     */
    @Override
    public ResponseCode queryAccount(String token) {  	
       ResponseCode responseCode = ResponseCode.BANK_FAILED;
        User user = userService.getUser(token);
        UserAccount account = accountService.findOneByUserId(user.getUserId());
        if(account == null){
            return ResponseCode.FAILED;         // 用户未开户
        }
        
        // 交易类型和流水号
        String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.QUERY_ACC.getValue());
        String tradeType = "BQReq";
        // 保存交易请求
        saveTransRecord(user, tradeNo, instId, BankTransType.QUERY_ACC.getValue());
        // 组装请求数据
        Map<String, String> root = Maps.newHashMap();
        root.put("tradeType", tradeType);
        root.put("tradeNo", tradeNo);
        root.put("vAccountNo", account.getVirtualAccountNo());
        String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);
        logger.info("GF 查询电子账户信息的请求报文===============\n" + xml);
        // 发送并接收返回
        String resXml = sendData(xml, tradeType);
        logger.info("GF 查询电子账户信息的响应报文===============\n" + resXml);
        if(resXml != null){
            BQResponse response = responseAnalyzer(resXml, "BQRes", BQResponse.class);
            // 更新交易记录表
            BankResponseModel model = convertResponseStatus(response);
            updateTransRecord(model);
            try {
                // 更新账户信息
                if(response.getErrorCode().equals("0000")){
                    account.setAccountAmount(new BigDecimal(response.getvAccTotalAmt()));
                    account.setAccountBalance(new BigDecimal(response.getvAccAmt()));
                    account.setFrozenAmount(new BigDecimal(response.getvAccFreezeAmt()));
                    accountService.update(null, account);
                    responseCode = ResponseCode.SUCCESS;
                }
            } catch (Exception e) {
                logger.error("保存账户信息失败============================", e);
            }
        }else{
            logger.error("GF 查看电子账户信息的响应为空=====================");
        }
        return responseCode;
    }
    
    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#saveTransRecord(com.douins.account.domain.model.User, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void saveTransRecord(User user, String transId, String chanlNo, String transType) {
        // TODO Auto-generated method stub
        BankResponseModel model = new BankResponseModel();
        model.setUserId(user.getUserId());
        model.setTransId(transId);
        model.setChannelNo(chanlNo);
        model.setTransType(transType);
//        model.setOperatType(transType);
        model.setStatus(AccStatusCode.NO_BACK.getValue());
        
        model.setBankCode(BankType.GFBC.getValue());
        bankResponeDao.add(model);
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#updateTransRecord(com.douins.bank.domain.model.BankResponseModel)
     */
    @Override
    public void updateTransRecord(BankResponseModel response) {
    	  bankResponeDao.update(response);
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#signForRequest(java.lang.String)
     */
    @Override
    public String signForRequest(String params) {
        // TODO Auto-generated method stub
        String sign = null;
        try {
            PrivateKey privateKey = XmlUtil.getPrivateKey(privKeyFilePath, privKeyPassword);
            byte[] datas = params.getBytes("UTF-8");
            byte[] signDatas = XmlUtil.sign(datas, privateKey);         // 签名
            sign = byteToHex(signDatas);
        }catch (Exception e) {
            logger.error("URL 参数签名错误.", e);
        }
      
        return sign;
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#analyzeGatewayResponse(java.lang.String, java.lang.String)
     */
    @Override
    public ResponseCode analyzeGatewayResponse(String params, String token) {
        // TODO Auto-generated method stub
        return null;
    }

    /* 
     * 开户查询
     * @see com.douins.bank.service.BankServiceIFC#queryRegistResult(java.lang.String)
     */
    @Override
    public ResponseCode queryRegistResult(String token) {
        // TODO Auto-generated method stub
        User user = userService.getUser(token);
        if(user == null){
            return ResponseCode.BANK_FAILED;
        }
        String tradeType = "RRQReq";
        // 请求数据的 xml
        String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.REGIST_ACC_REQ.getValue());
      //保存交易记录
        saveTransRecord(user, tradeNo, instId, BankTransType.REGIST_ACC_REQ.getValue());
        // 组装请求数据
        Map<String, Object> root = Maps.newHashMap();
        root.put("tradeNo", tradeNo);
        root.put("certNo", user.getCertiCode());
        root.put("tradeType", tradeType);
        String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);
        logger.info("查询开户结果的请求报文==================\n" + xml);
        //发送数据请求
        String  resXml = sendData(xml, tradeType);
        logger.info("查询开户结果的返回报文==================\n" + resXml);
        
        ResponseCode responseCode = ResponseCode.FAILED;
        if(resXml != null){
        	try {
	            // 获得开户查询返回数据
	//        	//RRQResponse response =getRRQResponse(resXml);
	        	RRQResponse response = responseAnalyzer(resXml, "RRQRes", RRQResponse.class);
	        	//存储数据 (UserAccount,PayUserAccount )并更新交易记录
				saveRRQResponse(response, user.getUserId());
		        //更新用户表
	        	user.setStatus(UserStatus.SUCCESS.getValue());
	        	userService.update(null, user);
			} catch (Exception e) {
				logger.error("开户查询出错：\n",e);
			}

        	responseCode = ResponseCode.SUCCESS;
        }else{
            logger.error("GF开户查询：银行返回数据为空");
        }
        
        return responseCode;
    }
    
    
    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#recharge(java.lang.String)
     */
    @Override
    public ResponseCode recharge(String token, BigDecimal amount) {
        // TODO Auto-generated method stub
        String tradeType = "TPReq";
        String status = null;
        // 查询用户信息
        User user = userService.getUser(token);
        UserAccount account = accountService.findOneByUserId(user.getUserId());
        if(account == null){
            return ResponseCode.USERNOTOPENERROR;           // 未开通电子账户
        }
        
        UserPayAccount payAccount = payAccountService.findOneByUserId(user.getUserId());
        if(payAccount == null){
            return ResponseCode.USERNOTBINDCARD;            // 未绑定实体卡
        }
        
        ResponseCode responseCode = ResponseCode.BANK_FAILED;
        // 交易流水号
        String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.RECHARGE.getValue());
        // 保存交易申请
        saveTransRecord(user, tradeNo, instId, BankTransType.RECHARGE.getValue());
        
        Map<String, Object> root = Maps.newHashMap();
        root.put("tradeType", tradeType);
        root.put("tradeNo", tradeNo);
        root.put("tradeDate", DateTimeUtils.formateDate2(new Date()));
        root.put("tradeTime", DateTimeUtils.formateTime2(new Date()));
        root.put("vAccountNo", account.getVirtualAccountNo());
        root.put("payAccount", payAccount.getAccountNo());
        root.put("payAmount", amount.doubleValue());
        String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);     // 请求报文
        logger.info("GF 充值请求报文===============\n" + xml);
        //保存账户金额变动请求
        saveAmountChange(token, amount, BankTransType.RECHARGE.getValue(), tradeNo, null, null, null, null);
        // 发送请求
        String  resXml = sendData(xml, tradeType);
        logger.info("GF 充值响应报文===============\n" + resXml);
        if(resXml != null){
            // 解析并保存数据
            TPResponse response = responseAnalyzer(resXml, "TPRes", TPResponse.class);
            BankResponseModel model = convertResponseStatus(response);
            updateTransRecord(model);
            try {
                if(!account.getStatus().equals(AccountStaus.ACTIVATED.getValue()) && response.getDealRes().equals("0")){
                    account.setStatus(AccountStaus.ACTIVATED.getValue());
                    accountService.update(null, account);
                }
                //更新账户金额变动表
               updateAmountChange(tradeNo, response.getDealRes());
                responseCode = ResponseCode.BANK_SUCCESS;
            } catch (Exception e) {
                logger.error("更新用户的电子账户状态失败", e);
            }
        }else{
            logger.error("GF 充值：银行返回数据为空=============");
        }
      
        return responseCode;
    }

    /* (non-Javadoc)
     * @see com.douins.bank.service.BankServiceIFC#payment(java.lang.String)
     */
    @Override
    public ResponseCode payment(String token, String bAccount, BigDecimal amoutn, BankTransType transType,String orderNo) {
        // TODO Auto-generated method stub
        String tradeType = "SECReq";
        String tradeNo = null;
        String orderType = null;
        String currentTransType = null;
        if(transType.getValue().equals(BankTransType.PURCHASE.getValue())){ // 消费
            currentTransType = BankTransType.PURCHASE.getValue();
            tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), currentTransType);
            orderNo = tradeNo;
            orderType = "0";
        }else if (transType.getValue().equals(BankTransType.REFUND.getValue())) {   // 撤单
            currentTransType = BankTransType.REFUND.getValue();
            tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), currentTransType);
            orderType = "1";            
        }
        
        User user = userService.getUser(token);
        UserAccount account = accountService.findOneByUserId(user.getUserId());
        // 保存交易请求记录
        saveTransRecord(user, tradeNo, instId, currentTransType);
        // 组装请求数据
        Map<String, Object> root = Maps.newHashMap();
        root.put("tradeType", tradeType);
        root.put("tradeNo", tradeNo);
        root.put("orderNo", orderNo);
        root.put("tradeDate", DateTimeUtils.formateDate2(new Date()));
        root.put("tradeTime", DateTimeUtils.formateTime2(new Date()));
        root.put("orderType", orderType);
        root.put("vAccountNo", account.getVirtualAccountNo());
        root.put("bAccountNo", bAccount);         // 商户账号
        root.put("transAmount", amoutn);        // 交易金额
        root.put("busiDate", DateTimeUtils.formateDate2(new Date()));
        String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);
        logger.info("GF 消费／撤单请求报文=======================\n" + xml);
        //保存账户金额变动记录
        saveAmountChange(token, amoutn, transType.getValue(), tradeNo, "测试", "测试", "测试", bAccount);
        // 发送并接收数据
        String resXml = sendData(xml, tradeType);
        logger.info("GF 消费／撤单返回报文=======================\n" + resXml);
        if(resXml != null){
        	SECResponse response = responseAnalyzer(resXml, "SECRes", SECResponse.class);
        	//更新交易记录
    		BankResponseModel model = convertResponseStatus(response);
    		updateTransRecord(model);
    		//更新账户金额变动表
            updateAmountChange(tradeNo, response.getDealRes());
        }else{
            logger.error("GF 消费／撤单交易返回为空==================");
        }
        return null;
    }

    /* (non-Javadoc)
     * 提现
     * @see com.douins.bank.service.BankServiceIFC#withdraw(java.lang.String, java.math.BigDecimal)
     */
    @Override
    public ResponseCode withdraw(String token, BigDecimal amount) {
        // TODO Auto-generated method stub
    	
    	User user = userService.getUser(token);
    	if(user == null){
    			return ResponseCode.BANK_FAILED;
    	}
   	     String tradeType = "WDReq";
   	     UserPayAccount payAccount = payAccountService.findOneByUserId(user.getUserId()); 
         UserAccount account = accountService.findOneByUserId(user.getUserId());
   	     String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.WITHDRAW.getValue());

    	 Map<String, Object> root = Maps.newHashMap();
    	 root.put("tradeType", tradeType);
    	 root.put("tradeNo", tradeNo);
         root.put("tradeDate", DateTimeUtils.formateDate2(new Date()));
         root.put("tradeTime", DateTimeUtils.formateTime2(new Date()));
    	 root.put("recAccount", payAccount.getAccountNo());
         root.put("payAccount", account.getVirtualAccountNo());
         root.put("payAmount", amount.doubleValue());
         String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);     // 请求报文
         logger.info("GF提现请求报文＝＝＝＝＝＝＝＝\n"+xml);
         saveAmountChange(token, amount, BankTransType.WITHDRAW.getValue(), tradeNo, null, null, null, null);
         String  resXml = sendData(xml, tradeType);
         logger.info("GF 提现响应报文===============\n" + resXml);
         if(resXml != null){
             // 解析并保存数据
        	 TPResponse response = responseAnalyzer(resXml,"WDRes",TPResponse.class);
        	 BankResponseModel model = convertResponseStatus(response);
             updateTransRecord(model);
             updateAmountChange(tradeNo, response.getDealRes());
             try {
                 if(!account.getStatus().equals(AccountStaus.ACTIVATED.getValue()) && response.getDealRes().equals("0")){
                     account.setStatus(AccountStaus.ACTIVATED.getValue());
                     accountService.update(null, account);
                 }
             } catch (Exception e) {
                 logger.error("更新用户的电子账户状态失败", e);
             }
         }else {
             logger.error("GF 提现：银行返回数据为空=============");
         }
        return null;
    }
    
    /*
     * 批量查询交易记录（暂不用）
     * @see com.douins.bank.service.BankServiceIFC#queryConsumeRecords(java.lang.String)
     */
    @Override
    public ResponseCode queryConsumeRecords(String token) {
    	 User user = userService.getUser(token);
         String tradeType = "BEPQReq";
         // 请求数据的 xml
         String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.REGIST_ACC_REQ.getValue());
         Map<String, Object> root = Maps.newHashMap();
         root.put("tradeNo", tradeNo);
         root.put("tradeType",tradeType);
         root.put("date", DateTimeUtils.formateDate2(new Date()));
         root.put("time", DateTimeUtils.formateTime2(new Date()));
         //自动消费查询
         root.put("tradeCode",GFtradeCode.AutoConsumption);
         String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);
//         logger.info("自动消费记录发送＝ \n"+xml);
         String resXml = sendData(xml, tradeType);
//         logger.info("自动消费记录返回＝\n"+resXml);
         //批量退款查询
         root.put("tradeCode",GFtradeCode.BatchRefund);
         String xml2 = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);
         logger.info("GF 批量退款记录发送＝ \n"+xml2);
         String resXml2 = sendData(xml2, tradeType);
         logger.info("GF 批量退款记录返回＝\n"+resXml2);
        return null;
    }
    /**
     * 单笔交易结果查询
     * @param token
     * @param userInfo
     * @return
     */
    @Override
    public ResponseCode QuerySingleResults(String token,BankTransType transType,String channelSeq){
    	User user = userService.getUser(token);
    	if(user == null){
    			return ResponseCode.BANK_FAILED;
    	}
   	     String tradeType = "TRQReq";
   	     String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.QUERY_SINGLE_RESULTS.getValue());
   	     String tradeCode =null;
    	 Map<String, Object> root = Maps.newHashMap();
    	 root.put("tradeType", tradeType);
    	 root.put("tradeNo", tradeNo);
    	 root.put("channelSeq",channelSeq);
    	 if(transType.getValue().equals(BankTransType.RECHARGE.getValue())){ // 充值
    		tradeCode = "TPReq";
    	 }else if(transType.getValue().equals(BankTransType.WITHDRAW.getValue())){//取现
    		tradeCode = "WDReq";
    	 }else if(transType.getValue().equals(BankTransType.PURCHASE.getValue() ) || transType.getValue().equals(BankTransType.REFUND.getValue())){
    		 tradeCode = "SECReq";
    	 }
    	 root.put("tradeCode",tradeCode);
         String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);     // 请求报文
         logger.info("GF 单笔交易结果查询请求报文===============\n" + xml);
         String  resXml = sendData(xml, tradeType);
         logger.info("GF 单笔交易结果查询响应报文===============\n" + resXml);
        return null;
    }
    /**
     * 已签约银行查询
     * @param token
     * @param userInfo
     * @return
     */
    @Override
    public ResponseCode queryBankNo(String token){
    	User user = userService.getUser(token);
    	if(user == null){
    			return ResponseCode.BANK_FAILED;
    	}
   	   String tradeType = "SBLQReq";
   	   String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.QUERY_BANK_NO.getValue());
   	   Map<String, Object> root = Maps.newHashMap();
   	   root.put("tradeType", tradeType);
   	   root.put("certNo", user.getCertiCode());
   	   root.put("tradeNo",tradeNo);
   	   String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);     // 请求报文
       logger.info("GF 已签约银行查询请求报文===============\n" + xml);
       //保存交易记录
       saveTransRecord(user, tradeNo, instId, BankTransType.QUERY_BANK_NO.getValue());
       String  resXml = sendData(xml, tradeType);
       logger.info("GF 已签约银行查询响应报文===============\n" + resXml);
       if(resXml !=null){
    	   //解析并保存数据
    	   SBLQResponse response = responseAnalyzer(resXml, "SBLQRes",SBLQResponse.class);
    	   BankResponseModel model = convertResponseStatus(response);
    	   updateTransRecord(model);    	   
       }
    	return null;
    }

    /**
     * 银行卡解除绑定
     * @param token
     * @param userInfo
     * @return
     */
    @Override
    public ResponseCode UnbindAccount(String token,String channelSeq){
    	User user = userService.getUser(token);
    	if(user == null){
    		return ResponseCode.BANK_FAILED;
    	}
    	String tradeType = "SBCReq";
  	    UserPayAccount payAccount = payAccountService.findOneByUserId(user.getUserId()); 
        UserAccount account = accountService.findOneByUserId(user.getUserId());
    	String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.UNBIND_CARD.getValue());
    	
    	Map<String,Object> root = Maps.newHashMap();
        root.put("tradeType", tradeType);
        root.put("tradeNo", tradeNo);
        root.put("tradeDate", DateTimeUtils.formateDate2(new Date()));
        root.put("tradeTime", DateTimeUtils.formateTime2(new Date()));
        root.put("vAccountNo", account.getVirtualAccountNo());
        root.put("opAccount", payAccount.getAccountNo());
        root.put("channelSeq",channelSeq);
        String xml = templateLoader.processTemplate("/bank/gfbc/request.ftl", root);     // 请求报文
        logger.info("GF解除绑定银行卡请求报文＝＝＝＝＝＝＝＝\n"+xml);
        
        String  resXml = sendData(xml, tradeType);
        //保存交易记录
        saveTransRecord(user, tradeNo, instId, BankTransType.UNBIND_CARD.getValue());
        logger.info("GF 解除绑定银行卡响应报文===============\n" + resXml);
        if(resXml != null){
          // 解析并保存数据
       	    TPResponse response = responseAnalyzer(resXml,"SBCRes",TPResponse.class);
       	    BankResponseModel model = convertResponseStatus(response);
            updateTransRecord(model);
           /* updateAmountChange(tradeNo, response.getDealRes());*/
            unbindBankCard(user,payAccount,response.getDealRes());

        }else {
            logger.error("GF 提现：银行返回数据为空=============");
        }
    	return null;
    }

	// ********************** GF 专用接口 **********************************
    public String signUrl(String token, User userInfo){
        User user = userService.getUser(token);
        user.setUserName(userInfo.getUserName());
        user.setCertiCode(userInfo.getCertiCode());
        
        try {
            // 更新用户信息
            userService.update(null, user);
            // 创建用户账户－－改为在开户查询账户成功后创建用户账户
//            accountService.createUserAccount(user.getUserId(), null, null);
        } catch (DataBaseAccessException e) {
              logger.error("更新用户信息失败", e);
        }
        
        String tradeType = "RSREG";
        String reqTime = DateTimeUtils.formateDateTime2(new Date());
        //拼接签名参数
        String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.REGIST_ACC.getValue());
//        String params = catSignParams(user, tradeNo, tradeType, null, reqTime);
//        String sign = signForRequest(params);
//        // 拼接url参数
//        String urlParams = catUrlParams(user, tradeNo, tradeType, reqTime, null, sign);
//        String url = registUrl + "?" + urlParams;
        
          // wap url
        String url = wapUrl + "?" +catSignParamsWap(user, tradeNo, null);
        
        return url;
    }
    
    // ********************** 私有方法定义如下 *****************************
    /**
     * 向银行发送数据
     * @param xml
     * @param tradeType
     * @return
     */
    private String sendData(String xml, String tradeType){
        String  resXml = null;
        try {
            String signXml = XmlUtil.xmlSignature(xml, "Message", tradeType, privKeyFilePath,  privKeyPassword);
            resXml = HttpClientUtils2.sendXml_Post_text(url, signXml, "UTF-8");
        } catch (Exception e) {
            logger.error("网络传输错误===============\n", e);
        }
        
        return resXml;
    }
    
    /**
     * 为 wap 链接拼接签名串
     * @param user
     * @param tradeNo
     * @param vAccountNo
     * @return
     */
    private String catSignParamsWap(User user, String tradeNo, String vAccountNo){
        StringBuilder result = new StringBuilder();
        StringBuilder params = new StringBuilder();
        try {
	        params.append(channelId);
	        params.append(user.getUserName());
	        params.append(user.getCertiCode());
	        params.append(tradeNo);
	        result.append(String.format("channelId=%s", channelId));
	        result.append(String.format("&custName=%s", URLEncoder.encode(user.getUserName() ,"UTF-8")));
	        result.append(String.format("&certNo=%s", user.getCertiCode()));
	        result.append(String.format("&transferFlowNo=%s", tradeNo));
	        if(vAccountNo != null){
	            params.append(vAccountNo);
	            result.append(String.format("&accountId=%s", vAccountNo));
	        }
	        params.append(ReadConfig.get("wap_regist_key"));
	        String temp = params.toString();
        
        // MD5 摘要      
            String sign = DigestUtils.md5Hex(temp.getBytes("UTF-8"));
            result.append(String.format("&privateInfo=%s", sign));
            result.append(String.format("&merchantUrl=%s", ReadConfig.get("wap_callback_url")));
        } catch (UnsupportedEncodingException e) {
           logger.error("wap url 生成 MD5 摘要错误", e);
        }
        return result.toString();
    }
    
    /**
     * 拼接开户／绑卡需要的参数串 （URL）
     * @param user
     * @param reqType
     * @param vAccountNo
     * @param time
     * @return
     */
   private String catSignParams(User user, String tradeNo, String reqType, String vAccountNo, String time){
       String[] params = null;
       if(reqType.equals("MC")){
           params = new String[9];
           params[8] = vAccountNo;
       }else{
           params = new String[8];
       }
       params[0] = tradeNo;
       params[1] = user.getUserName();
       params[2] = "01";        // 只支持身份证开户
       params[3] = user.getCertiCode();
       if(user.getUserSex() != null){
           params[4] = user.getUserSex().equals("1") ? "M" : "F";
       }else {
           String temp = user.getCertiCode().length() == 18 ? user.getCertiCode().substring(16, 17) : user.getCertiCode().substring(14, 14);
           int val = Integer.valueOf(temp);
           if((val % 2) == 1){
               params[4] =  "M";
               user.setUserSex("1");
           }else {
               params[4] = "F";
               user.setUserSex("2");
           }
       }
       params[5] = instId;
       params[6] = reqType;
       params[7] = time;
       
       String temp = "|";
       String result = StringUtils.join(params, temp);
       if(result.endsWith(temp)){
           int length = result.length();
           result = result.substring(0, length - 1);
       }
       return result;
   }
   
   /**
    * 拼接 url 字符串
    * @param user
    * @param tradeNo
    * @param reqType
    * @param reqTime
    * @param vAccountNo
    * @param sign
    * @return
    */
   private String catUrlParams(User user, String tradeNo, String reqType, String reqTime, String vAccountNo, String sign){
       StringBuilder params = new StringBuilder();
       params.append(String.format("channelSeq=%s", tradeNo));
       params.append(String.format("&cusName=%s", user.getUserName()));
       params.append("&certType=01");
       params.append(String.format("&certNo=%s", user.getCertiCode()));
       String sex = (user.getUserSex() != null) ? (user.getUserSex().equals("1") ? "M" : "F") : null;
       params.append(String.format("&sex=%s", sex));
       params.append(String.format("&instId=%s", instId));
       params.append(String.format("&reqType=%s", reqType));
       params.append(String.format("&reqTime=%s", reqTime));
       if(reqType.equals("MC")){
           params.append(String.format("&vAccountNo=%s", vAccountNo));
       }
       params.append(String.format("&signedMsg=%s", sign));
       
       return params.toString();
   }
 
    /**
     * 十进制字节数组转换为十六进制字符串
     * @param arr
     * @return
     */
    private String byteToHex(byte[] arr) {
        int i;
        String byteStr;
        StringBuffer strBuf = new StringBuffer();
        for (i = 0; i < arr.length; i++) {
            byteStr = Integer.toHexString(arr[i] & 0x00ff);
            if (byteStr.length() != 2) {
                strBuf.append('0').append(byteStr);
            }
            else {
                strBuf.append(byteStr);
            }
        }
        return new String(strBuf);
    }
   /*
    * 获得开户返回查询结果
    */
    public RRQResponse getRRQResponse(String xml){
    	Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    	Elements elements = doc.select("rrqres");
    	RRQResponse response =new RRQResponse();
    	String o =null;
    	// 解析 id
    	if(elements.hasAttr("id")){
    		String id = elements.attr("id").trim();
    		o=id;
//    		response.setId(id);
    		logger.info("id = "+ id);
    		// 删除 id
    		elements.removeAttr("id");
    	}	
    	String xml2 = elements.toString().replaceAll(pattern, "");
    	response = XmlUtil.xmlDeserialize(xml2, RRQResponse.class);
    	response.setId(o);
    	Elements elements2 = doc.select("message");
    	if(elements2.hasAttr("id")){
    		String transId =elements2.attr("id").trim();
    		response.setTransId(transId);
    		logger.info("transId="+transId);
    	}
    	return response;
    }
    

    /*
     * 开户查询结果存入表中
     */
    public void saveRRQResponse(RRQResponse response,String userId) throws Exception{    	
		if("0".equals(response.getDealRes())){
			//userAccount存数据
			UserAccount userAccount=accountService.createUserAccount(userId, response.getAccountNo(),null);
			 //userPayAccount存数据
			UserPayAccount payAccount =new UserPayAccount();
	    	payAccount.setUserId(userId);
	    	payAccount.setUserAccountId(userAccount.getUserAccountId());
	    	payAccount.setAccountType(AccountType.MOBILE.getValue());
	    	payAccount.setAccountNo(response.getCardNo());
	    	payAccount.setAccountName(response.getoBankName());
	    	payAccount.setBankCode(BankType.getValueByName(response.getoBankName()));
	    	payAccount.setCardholderName(response.getCusName());
	    	payAccount.setCardholderCertitype(GFcertiType.getValueByCode(response.getCertType()));
	    	payAccount.setCardholderCerticode(response.getCardNo());
	    	payAccount.setBankmobile(response.getPhoneNo());
	    	payAccount.setStatus("1");
	    	payAccount.setIsvalid("1");
	    	payAccountService.createUserPayAccount(payAccount);
		}
		//更新交易记录
		BankResponseModel model = convertResponseStatus(response);
		updateTransRecord(model);
    }
    
    /**
     * 转化响应状态
     * @param response
     * @return
     */
    private BankResponseModel convertResponseStatus(AbstractResposeGF response){
    	BankResponseModel model =new BankResponseModel();
		model.setTransId(response.getTransId());
		model.setBankTransId(response.getId());
		model.setErrorMsg(response.getErrorMessage()+";"+response.getErrorDetail());
		model.setStatus(response.getErrorCode());
		model.setSign(null);
		
		return model;
    }
    
 // ******************* 通用报文解析方法 *****************************
    /**
     * 通用报文解析方法
     * @param xml
     * @param contextTag
     * @param clz
     * @return
     */
    private <T> T responseAnalyzer(String xml, String contextTag, Class<T> clz){
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
     // 解析 Message 的 id
        Elements msg = doc.select("message");
        String msgId = null;
    	if(msg.hasAttr("id")){
    		String transId = msg.attr("id").trim();
    		msgId = transId;
    		logger.info("transId="+transId);
    	}
    	// 解析报文的上下文
    	String context = contextTag.toLowerCase();
        Elements elements = doc.select(context);       // 处理小写
        T  response = null;
        String contextId = null;
        if(!elements.isEmpty()){
        	 // 解析 id
            if(elements.hasAttr("id")){
                String id = elements.attr("id").trim();
//                response.setId(id);
                contextId = id;
                logger.info("id = "+ id);
                // 删除 id
                elements.removeAttr("id");
            }   
            String xml2 = elements.toString().replaceAll(pattern, "");
            response = (T)XmlUtil.xmlDeserialize(xml2, clz);
            if(contextId != null && msgId != null){
                try {
                    Method method = clz.getMethod("setResponseId", String.class);
                    Method methodMsg = clz.getMethod("setTransId", String.class);
                    method.invoke(response, contextId);
                    methodMsg.invoke(response, msgId);
                } catch (Exception e) {
                   logger.error("反射处理响应上下文的ID出错================", e);
                }
            }
        }
        // 报错通用模版解析
        Elements errMsg = doc.select("error");
        if(errMsg != null && !errMsg.isEmpty()){
//        	String errorCode =doc.select("errorcode").get(0).text();    	 
        	String xml2 = errMsg.toString().replaceAll("error>", context+">");
        	xml2 = xml2.replaceAll(pattern, "");      	
            response = (T)XmlUtil.xmlDeserialize(xml2, clz);
        }
        
        return response;
    }
    /**
     * 保存账户金额变动记录
     * 以下4个参数为：别的账户，即除了虚拟账户和绑定账户之外的其他账户
     * @param bankName
     * @param bankCode
     * @param accountName
     * @param accountNo
     */
	@Override
	public void saveAmountChange(String token ,BigDecimal amount,String transType,String tradeNo,String bankName,String bankCode,String accountName,String accountNo) {
		UserInfoVo vo = userService.getUserInfo(token);
		logger.info("userId="+vo.getUser().getUserId());
		UserPayAccount account = payAccountService.findOneByUserId(vo.getUser().getUserId());
		UserAccountDetail detail=new UserAccountDetail();
		detail.setUserAccountId(account.getUserAccountId());
		detail.setBusinessNo(tradeNo);
		detail.setDetailType(transType);
		detail.setApplyAmount(amount);
		detail.setStatus("0");
		detail.setDetailIo(BankTransType.getCodeByValue(transType));
		detail.setApplyTime(new Date());
		detail.setCreateDate(new Date());
		detail.setUpdateDate(new Date());
		//不同的交易类型银行信息不同
		detail.setFromAccountName(account.getCardholderName());
		detail.setFromBankCode(BankType.GFBC.getValue());
		detail.setFromBankName(BankType.GFBC.getName());
		detail.setToAccountName(account.getCardholderName());
		detail.setToBankCode(BankType.GFBC.getValue());
		detail.setToBankName(BankType.GFBC.getName());
		if(BankTransType.RECHARGE.getValue().equals(transType)){
			detail.setFromBankCode(account.getBankCode());
			detail.setFromBankName(account.getAccountName());
			detail.setFromAccountNo(account.getAccountNo());
			detail.setToAccountNo(vo.getAccount().getVirtualAccountNo());
		}else if(BankTransType.WITHDRAW.getValue().equals(transType)){
			detail.setFromAccountNo(vo.getAccount().getVirtualAccountNo());
			detail.setToAccountNo(account.getAccountNo());
			detail.setToBankCode(account.getBankCode());
			detail.setToBankName(account.getAccountName());
		}else if(BankTransType.REPAY.getValue().equals(transType)|| BankTransType.PURCHASE.getValue().equals(transType)){
			detail.setFromAccountNo(vo.getAccount().getVirtualAccountNo());
			detail.setToAccountName(accountName);
			detail.setToAccountNo(accountNo);
			detail.setToBankCode(bankCode);
			detail.setToBankName(bankName);
		}else if(BankTransType.REFUND.getValue().equals(transType)){
			detail.setToAccountNo(vo.getAccount().getVirtualAccountNo());
			detail.setFromAccountName(accountName);
			detail.setFromAccountNo(accountNo);
			detail.setFromBankCode(bankCode);
			detail.setFromBankName(bankName);
		}
		
		userAccountDetailDao.add(detail);
	}
	
	/**
	 * 更新账户金额变动记录
	 */
	@Override
	public void updateAmountChange(String tradeNo,String status) {
		UserAccountDetail detail=new UserAccountDetail();
		detail.setEndTime(new Date());
		detail.setUpdateDate(new Date());
		if("0".equals(status)){
        	detail.setStatus("1");//成功
        }else if("1".equals(status)){
        	detail.setStatus("2");
        }
//		detail.setStatus(status);
		detail.setBusinessNo(tradeNo);
		userAccountDetailDao.update(detail);
	}
	
	/**
	 * 银行卡解除绑定
	 * @param user
	 * @param dealRes
	 */
    private void unbindBankCard(User user,UserPayAccount  payAccount, String dealRes) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		UserPayAccountDao userPayAccountDao = new UserPayAccountDao();
    	if("0".equals(dealRes)){  //解绑成功
    		user.setStatus("6");
			userDao.update(user);
			payAccount.setStatus("0");
			userPayAccountDao.update(payAccount);
		}
	}

	@Override
	public String projecteRegist(ProjecteRegistRequestBody requestBody) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void callBack(User user, String transId, String transType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertNYCallbackInfo(CallBackRequest callbackInfo) {
		// TODO Auto-generated method stub
		return 0;
	}
}

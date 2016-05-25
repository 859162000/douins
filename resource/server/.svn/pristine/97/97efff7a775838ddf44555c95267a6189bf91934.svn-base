/**
 * 
 */
package com.douins.bank.service.iml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.account.domain.enums.CertiType;
import com.douins.account.domain.enums.UserStatus;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAuthority;
import com.douins.account.domain.vo.UserStatusInfoVo;
import com.douins.account.service.iml.UserAccountServiceImpl;
import com.douins.account.service.iml.UserAuthorityService;
import com.douins.account.service.iml.UserServiceImpl;
import com.douins.bank.dao.BankResponeDao;
import com.douins.bank.domain.enums.AccStatusCode;
import com.douins.bank.domain.enums.BankTransType;
import com.douins.bank.domain.enums.BankType;
import com.douins.bank.domain.enums.nybc.NYServiceType;
import com.douins.bank.domain.model.BankResponse;
import com.douins.bank.domain.model.BankResponseModel;
import com.douins.bank.domain.model.nybc.AppHead;
import com.douins.bank.domain.model.nybc.BankDict;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.bank.domain.model.nybc.NYTransRequest;
import com.douins.bank.domain.model.nybc.ProjecteRegistRequest;
import com.douins.bank.domain.model.nybc.ProjecteRegistRequestBody;
import com.douins.bank.domain.model.nybc.QueryAccountRequest;
import com.douins.bank.domain.model.nybc.QueryAccountRequestBody;
import com.douins.bank.domain.model.nybc.QueryAccountResponse;
import com.douins.bank.domain.model.nybc.QueryAccountResponseBody;
import com.douins.bank.domain.model.nybc.QueryPayResultRequest;
import com.douins.bank.domain.model.nybc.QueryPayResultRequestBody;
import com.douins.bank.domain.model.nybc.RegistAccountRequest;
import com.douins.bank.domain.model.nybc.RegistAccountRequestBody;
import com.douins.bank.domain.model.nybc.RegistAccountResponse;
import com.douins.bank.domain.model.nybc.RegistAccountResponseBody;
import com.douins.bank.domain.model.nybc.SystermHead;
import com.douins.bank.domain.model.nybc.VerifyIdentityRequest;
import com.douins.bank.domain.model.nybc.VerifyIdentityRequestBody;
import com.douins.bank.domain.model.nybc.VerifyIdentityResponse;
import com.douins.bank.domain.model.nybc.VerifyIdentityResponseBody;
import com.douins.bank.domain.vo.BankChangeRequest;
import com.douins.bank.domain.vo.BankChangeResponseVo;
import com.douins.bank.domain.vo.RegistAccountResponseVo;
import com.douins.bank.service.BankServiceIFC;
import com.douins.common.params.WebUseConstant;
import com.douins.common.rsa.MD5Utils;
import com.douins.common.rsa.RSAUtils;
import com.douins.common.util.Const;
import com.douins.common.util.HttpClientUtils2;
import com.douins.common.util.ReadConfig;
import com.douins.common.util.SeqNumUtils;
import com.douins.common.util.SystemConstant;
import com.douins.trans.domain.enums.BusinessTransStatus;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransChannel;
import com.douins.trans.domain.enums.TransType;
import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.douins.trans.service.TrasBusinessService;
import com.google.common.collect.Maps;
import com.mango.exception.DataBaseAccessException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @ClassName: NYBankService
 * @Description: NY Bank 业务处理
 * @author G. F.
 * @date 2015年12月6日 上午9:09:56
 * 
 */
@Service("nyBankService")
@Transactional
public class NYBankService implements BankServiceIFC {
	private static final Logger logger = Logger.getLogger(NYBankService.class);
	private static final String timeFormat = "yyyy-MM-dd";
	private String targetUrl;
	private static String privKey;
	private static String bankKey;
	private static String pubKey;

	static {
		privKey = loadKey(ReadConfig.get("key_priv_path_dy"));
		bankKey = loadKey(ReadConfig.get("key_pub_path_nybc"));
		pubKey = loadKey(ReadConfig.get("key_pub_path_dy"));
	}

	@Inject
	private UserServiceImpl userService;
	@Inject
	private UserAccountServiceImpl accountService;
	@Inject
	private BankResponeDao bankResponeDao;
	@Autowired
    private TrasBusinessService trasBusinessService;
	@Inject
	private UserAuthorityService authorityService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douins.bank.service.BankServiceIFC#loadPrivateKey()
	 */
	@Override
	public String loadPrivateKey() {
		String privKey = loadKey(ReadConfig.get("key_priv_path_dy"));
		return privKey;
	}
	public String loadPrivateKey(String key) {
		String returnKey = loadKey(ReadConfig.get(key));
		return returnKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douins.bank.service.BankServiceIFC#loadPublicKey()
	 */
	@Override
	public String loadPublicKey() {
		// TODO Auto-generated method stub
		return loadKey(ReadConfig.get("key_pub_path_dy"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douins.bank.service.BankServiceIFC#loadBankPublicKey()
	 */
	@Override
	public String loadBankPublicKey() {
		String bankKey = loadKey(ReadConfig.get("key_pub_path_nybc"));
		return bankKey;
	}

	/*
	 * (non-Javadoc) 身份核查接口
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#verifyIdentity(java.lang.Object)
	 */
	@Override
	public ResponseCode verifyIdentity(User user) {
		user.setCertiType(CertiType.ID.getValue()); // 身份证类型码
		// 交易流水号
		// String transId = SeqNumUtils.geneTxSN(SeqNumUtils.BNAK_VERIFY_ID);
		String transId = SeqNumUtils.geneTransNo("2", BankType.NYBC.getCode(),BankTransType.VERIFT_ID.getValue());
		// 具体内容请参考 nybc 文档的请求报文部分
		NYTransRequest request = new VerifyIdentityRequest();
		request.setSystermHead(genSysHead(transId, NYServiceType.VerifyIdentity));
		request.setAppHead(new AppHead());
		VerifyIdentityRequestBody requestBody = new VerifyIdentityRequestBody(user);
		request.setBody(requestBody);
		// 保存交易记录
		saveTransRecord(user, transId, request.getAppHead().getChannelCode(),BankTransType.VERIFT_ID.getValue());

		String xml = toXML(request, VerifyIdentityRequest.class);
		String reXml = send(xml);
		if (reXml == null || StringUtils.isBlank(reXml)) {
			return ResponseCode.RESPOSE_NULL; // 交易返回空值
		}

		// 响应对象
		VerifyIdentityResponse response = xml2Object(reXml,VerifyIdentityResponse.class);
		// 验签
		if (!verifySign(reXml, response.getSystermHead().getSign())) {
			return ResponseCode.BANK_SIGN_ERROR; // 验签失败
		}

		VerifyIdentityResponseBody body = response.getBody();
		// TODO 获取南粤响应状态
		ResponseCode responseCode = getResponseStatus(body);

		if (responseCode != null
				&& responseCode.getValue().equals(
						ResponseCode.BANK_SUCCESS.getValue())) {
			// 认证通过
			user.setStatus(UserStatus.VERIFY_SUCCESS.getValue());
			try {
				userService.update(null, user); // 更新用户状态
				updateTransRecord(transId,response.getSystermHead().getSeqNo(), body.getCode(),body.getMessage(), response.getSystermHead().getSign()); // 更新交易记录状态
			} catch (DataBaseAccessException e) {
				logger.error("update user status error. open account.", e);
			}
		}

		return responseCode;
	}
	
	  /**
		 * 5.22	业务状态流水查询接口
		 */
		@Override
		public String queryPayResult(String transNo) {
			NYTransRequest request = new QueryPayResultRequest();
			// 交易流水号
			// String transId = SeqNumUtils.geneTxSN(SeqNumUtils.BANK_QUERY_ACC);
			String transId = SeqNumUtils.geneTransNo("2", BankType.NYBC.getCode(),BankTransType.QUERY_ACCOUNT_REQUEST.getValue());

			request.setSystermHead(genSysHead(transId, NYServiceType.QueryAccountRequest));
			request.setAppHead(new AppHead());
			QueryPayResultRequestBody requestBody = new QueryPayResultRequestBody();
			requestBody.setTrantype("01");
			requestBody.setChanlflowno(transNo);
			request.setBody(requestBody);

			// 发送并接收返回数据
			String xml = toXML(request, QueryPayResultRequest.class);
			logger.info("请求查询南粤支付信息requestXml：");
			String responseXml = send(xml);
			logger.info("南粤响应支付信息responseXml"+responseXml);
			return responseXml;

		}
		
		 /**
		 * 
		 * 5.22.3	接口定义 e存管系统为客户提供“交易状态流水查询”接口，提供业务状态流水查询功能。

		 */
//		@Override
//		public String queryPayResultInfo(String tranType,String transNo) {
//			NYTransRequest request = new QueryPayResultRequest();
//			// 交易流水号
//			// String transId = SeqNumUtils.geneTxSN(SeqNumUtils.BANK_QUERY_ACC);
//			String transId = SeqNumUtils.geneTransNo("2", BankType.NYBC.getCode(),BankTransType.QUERY_ACCOUNT_REQUEST.getValue());
//
//			request.setSystermHead(genSysHead(transId, NYServiceType.QueryAccountRequest));
//			request.setAppHead(new AppHead());
//			QueryPayResultRequestBody requestBody = new QueryPayResultRequestBody();
//			requestBody.setTrantype("01");
//			requestBody.setChanlflowno(transNo);
//			request.setBody(requestBody);
//
//			// 发送并接收返回数据
//			String xml = toXML(request, QueryPayResultRequest.class);
//			return send(xml);
//
//		}


	/*
	 * (non-Javadoc) 注册电子账户
	 * 
	 * @see com.douins.bank.service.BankServiceIFC#openAccount(java.lang.Object)
	 */
	@Override
	@Transactional
	public RegistAccountResponseVo openAccount(User user) {
		RegistAccountResponseVo responseVo = null;
		// 更新用户信息
		ResponseCode responseCode = userService.updateUserInfo(user);
		if (!ResponseCode.USEROPENINFOSUCCESS.equals(responseCode)) {
			responseVo = new RegistAccountResponseVo();
			responseVo.setResponseCode(responseCode);
			return responseVo;
		}

		// 身份认证 
		if (validateUser(user)) {
			ResponseCode resCode = verifyIdentity(user);
			if (resCode != null&& !resCode.getValue().equals(ResponseCode.BANK_SUCCESS.getValue())) {
				responseVo = new RegistAccountResponseVo();
				responseVo.setResponseCode(resCode);
				return responseVo; // 认证失败，返回
			}
		}
		// 开户
		if (!user.getStatus().equals(UserStatus.SUCCESS.getValue())) {
			responseVo = openAccount2(user); // 之前未开户，或开户失败，则再次申请开户
		} else {
			// 已经开户成功
			ResponseCode code = ResponseCode.BANK_ACC_EXIST;
			responseVo = new RegistAccountResponseVo();
			responseVo.setResponseCode(code);
		}

		return responseVo;
	}

	private boolean validateUser(User user) {
		return user.getStatus().equals(UserStatus.INIT.getValue()) || user.getStatus().equals(UserStatus.VERIFY_FAILD.getValue());
	}

	/**
	 * 资金账户开户－注册电子账户
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public RegistAccountResponseVo openAccount2(User user) {
		RegistAccountResponseVo responseVo = new RegistAccountResponseVo();
		user.setCertiType(CertiType.ID.getValue()); // 身份证类型码
		// 交易流水号
		// String transId = SeqNumUtils.geneTxSN(SeqNumUtils.BANK_REGIST_ACC);
		String transId = SeqNumUtils.geneTransNo("2", BankType.NYBC.getCode(),
				BankTransType.REGIST_ACC.getValue());

		// 开户
		NYTransRequest request = new RegistAccountRequest();
		request.setSystermHead(genSysHead(transId, NYServiceType.RegistAccount));
		request.setAppHead(new AppHead());
		RegistAccountRequestBody requestBody = new RegistAccountRequestBody(
				user);
		request.setBody(requestBody);
		// 保存交易记录
		saveTransRecord(user, transId, request.getAppHead().getChannelCode(),
				BankTransType.REGIST_ACC.getValue());

		String xml = toXML(request, RegistAccountRequest.class);
		String reXml = send(xml);
		if (reXml == null || StringUtils.isBlank(reXml)) {
			responseVo.setResponseCode(ResponseCode.RESPOSE_NULL);
			return responseVo; // 交易返回空值
		}

		// 响应对象
		RegistAccountResponse response = xml2Object(reXml,RegistAccountResponse.class);
		// 验签
		if (!verifySign(reXml, response.getSystermHead().getSign())) {
			//logger.error("开户验签失败");
			responseVo.setResponseCode(ResponseCode.BANK_SIGN_ERROR);
			return responseVo; // 验签失败
		}

		RegistAccountResponseBody body = response.getBody();
		ResponseCode responseCode = getResponseStatus(body);

		try {
			if (responseCode != null&& ResponseCode.BANK_SUCCESS.getValue().equals(responseCode.getValue())) {
				logger.info("响应代码 ＝ " + responseCode.getValue());
				user.setStatus(UserStatus.SUCCESS.getValue());
				AccStatusCode statusCode = AccStatusCode.getRegistStatus(body.getUserStatus()); // 返回的用户账户状态
				logger.info("开户状态 ＝ " + statusCode.getValue());
				//开户时无论银行返回用户何种状态，都进行检查插入操作
//				if (statusCode.getValue().equals(AccStatusCode.REGIST_ACC_0.getValue())) {
					logger.info("保存账户信息");
					accountService.createUserAccount(user.getUserId(),body.getAccountNo(), body.getAccountId()); // 创建新账户
//				} else {
//					// 账户存在，更新返回的状态
//					// 暂时不需要
//				}
			} else {
				user.setStatus(UserStatus.FAILURE.getValue());
				user.setCertiCode("");
				user.setUserName("");
			}
			userService.update(null, user); // 更新用户状态
			updateTransRecord(transId, response.getSystermHead().getSeqNo(),body.getCode(), body.getMessage(), response
							.getSystermHead().getSign()); // 更新交易记录
		} catch (Exception e) {
			logger.error("update info error. open account.", e);
		}

		responseVo.setBody(body);
		responseVo.setResponseCode(responseCode);

		return responseVo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#queryAccount(java.lang.Object)
	 */
	@Override
	public ResponseCode queryAccount(String token) {
		NYTransRequest request = new QueryAccountRequest();
		User user = userService.getUser(token);
		// 交易流水号
		// String transId = SeqNumUtils.geneTxSN(SeqNumUtils.BANK_QUERY_ACC);
		String transId = SeqNumUtils.geneTransNo("2", BankType.NYBC.getCode(),
				BankTransType.QUERY_ACC.getValue());

		request.setSystermHead(genSysHead(transId, NYServiceType.QueryAccount));
		request.setAppHead(new AppHead());
		QueryAccountRequestBody requestBody = new QueryAccountRequestBody(user);
		request.setBody(requestBody);
		// 保存交易记录
		saveTransRecord(user, transId, request.getAppHead().getChannelCode(),
				BankTransType.QUERY_ACC.getValue());

		// 发送并接收返回数据
		String xml = toXML(request, QueryAccountRequest.class);
		String reXml = send(xml);
		if (reXml == null || StringUtils.isBlank(reXml)) {
			return ResponseCode.RESPOSE_NULL; // 交易返回空值
		}

		// 响应参数
		QueryAccountResponse response = xml2Object(reXml,
				QueryAccountResponse.class);
		// 验签
		if (!verifySign(reXml, response.getSystermHead().getSign())) {
			logger.info("验签失败");
			return ResponseCode.BANK_SIGN_ERROR;
		}

		QueryAccountResponseBody body = response.getBody();
		ResponseCode responseCode = getResponseStatus(body);

		try {
			if (responseCode != null
					&& responseCode.getValue().equals(
							ResponseCode.BANK_SUCCESS.getValue())) {
				// 查询成功
				UserAccount account = accountService.findOneByUserId(user.getUserId());
				account.setAccountAmount(new BigDecimal(body.getTotalBalance()));
				account.setAccountBalance(new BigDecimal(body.getAccBalance()));
				AccStatusCode accStatus = AccStatusCode.getActivFlag(body.getActiveFlag());
				account.setStatus(accStatus.getValue());
				AccStatusCode pwdStatus = AccStatusCode.getPwdFlag(body.getPwdFlag());
				account.setPasswordStatus(pwdStatus.getValue());
				accountService.update(null, account);
			} else {
				// 查询失败
				responseCode = ResponseCode.BANK_SYN_ERROR;
			}
			// 更新交易记录状态
			updateTransRecord(transId, response.getSystermHead().getSeqNo(),body.getCode(), body.getMessage(), response.getSystermHead().getSign());
		} catch (Exception e) {
			logger.error("query account error.", e);
		}

		return responseCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#signForRequest(java.lang.String)
	 */
	@Override
	public String signForRequest(String Params) {
		String md5Val = MD5Utils.getMd5(Params);
		String sign = getSignPrivKey(md5Val);
		String sign2 = null;
		try {
			sign2 = URLEncoder.encode(sign, "utf-8");
		} catch (Exception e) {
			logger.error("sign error.", e);
		}

		return sign2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#analyzeGatewayResponse(java.lang
	 * .String)
	 */
	@Override
	public ResponseCode analyzeGatewayResponse(String params, String token) {
		logger.info("网关返回参数＝" + params);
		User user = userService.getUser(token);
		ResponseCode responseCode = ResponseCode.FAILED;
		String[] paramsArr = params.split(","); // 按逗号分割
		Map<String, String> paramsMap = Maps.newHashMap();
		// 将参数写入键值对
		for (int i = 0; i < paramsArr.length; i++) {
			int index = paramsArr[i].indexOf("=");
			String name = paramsArr[i].substring(0, index);
			String val = paramsArr[i].substring(index + 1);
			paramsMap.put(name, val);
		}
		// 查询 chanlNo
		if (!paramsMap.containsKey("chanlNo")) {
			paramsMap.put("chanlNo",WebUseConstant.CHANL_NO);
		}

		// 封装响应模型
		BankResponseModel model = getResponseModel(paramsMap);
		model.setUserId(user.getUserId());
		// 签名值串
		String signVal = genSignDatas(paramsMap);
		// MD5
//		String signVal2 = signVal.replace("||", "|");
		String md5 = MD5Utils.getMd5(signVal);

		try {
			// 网关返回的签名，解签得到 md5
			String sign = model.getSign(); // 消除空格
			byte[] signBytes = Base64.decodeBase64(sign.getBytes());
			String md5Val = new String(RSAUtils.decryptByPublicKey(signBytes,bankKey), "UTF-8");
			if (md5.equals(md5Val)) {
				// 保存交易记录
				updateTransRecord(model);
				responseCode = ResponseCode.SUCCESS;
			} else {
				responseCode = ResponseCode.BANK_SIGN_ERROR; // 验签失败
			}
		} catch (Exception e) {
			logger.error("网关返回数据解签失败。", e);
		}

		return responseCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#saveTransRecord(com.douins.bank
	 * .domain.model.BankResponseModel)
	 */
	public void saveTransRecord(BankResponseModel response) {
		response.setBankCode(BankType.NYBC.getValue());
		bankResponeDao.add(response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#updateTransRecord(com.douins.bank
	 * .domain.model.BankResponseModel)
	 */
	@Override
	public void updateTransRecord(BankResponseModel response) {
		bankResponeDao.update(response);
	}

	/**
	 * 发送数据
	 * 
	 * @param xml
	 * @return
	 */
	public String send(String xml) {
		String reXml = null;
		try {
			// MD5 摘要
			String md5Val = MD5Utils.getMd5(xml);
			
			//1 测试 －－－douins_online_rsa_private_key.pem和douins_online_rsa_public_key
			// 私钥加签
			String sign = getSignPrivKey(md5Val,privKey);
			//TODO使用自己的公钥进行验签
			boolean verifySign = verifySign(xml, sign, pubKey);
			logger.info("使用dy自己公钥进行验签："+verifySign);
			
			// 公钥钥加签
			String pubSign = getSignPubKey(md5Val,pubKey);
			//TODO使用自己的私钥进行验签
			boolean privVerifySign = verifySign2(xml, pubSign,privKey);
			logger.info("使用dy自己私钥进行验签："+privVerifySign);

			
			// 2rsa_private_key.pem rsa_public_key.pem
			// 私钥加签
			String sign2 = getSignPrivKey(md5Val, loadKey("keys/nybc/rsa_private_key.pem"));
			logger.info("sign2===" + sign2);
			// TODO使用自己的公钥进行验签
			boolean verifySign2 = verifySign(xml, sign2, loadKey("keys/nybc/rsa_public_key.pem"));
			logger.info("使用dy自己公钥进行验签：" + verifySign2);
			// 公钥钥加签
			String pubSign2 = getSignPubKey(md5Val, loadKey("keys/nybc/rsa_public_key.pem"));
			// TODO使用自己的私钥进行验签
			boolean privVerifySign2 = verifySign2(xml, pubSign2, loadKey("keys/nybc/rsa_private_key.pem"));
			logger.info("使用dy自己私钥进行验签：" + privVerifySign2);
			
			// 将签文写入 xml
			String xml2 = addSign2Xml(xml, sign);
			logger.info("发送数据＝\r\n" + xml2);
			// 发送
			logger.info("南粤ip－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－" + targetUrl);
			logger.info("返回数据豆芽用私钥加密，南粤用豆芽提供的私钥解密后响应＝开始发送南粤－－－－－－－－－" );
			reXml = HttpClientUtils2.sendXml_Post_text(targetUrl, xml2, "utf-8");
			logger.info("返回数据豆芽用私钥加密，南粤用豆芽提供的私钥解密后响应＝\r\n" + reXml);
		} catch (IOException e) {
			logger.error("send error.", e);
		}

		return reXml;
	}
	
	public String send2(String xml) {
		String reXml = null;
		try {
			// MD5 摘要
			String md5Val = MD5Utils.getMd5(xml);
			// 私钥加签
			String sign = getSignPubKey(md5Val);
			System.out.println(getSignPubKey(sign));
	          
			// 将签文写入 xml
			String xml2 = addSign2Xml(xml, sign);
			logger.info("发送数据＝\r\n" + xml2);
			// 发送
			logger.info("南粤ip－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－" + targetUrl);
			logger.info("返回数据豆芽用私钥加密，南粤用豆芽提供的私钥解密后响应＝开始发送南粤－－－－－－－－－" );
			reXml = HttpClientUtils2.sendXml_Post_text(targetUrl, xml2, "utf-8");
			logger.info("返回数据豆芽用私钥加密，南粤用豆芽提供的私钥解密后响应＝\r\n" + reXml);
		} catch (IOException e) {
			logger.error("send error.", e);
		}

		return reXml;
	}

	/**
	 * 加载指定的秘钥文件
	 * 
	 * @param filePath
	 * @return
	 */
	private static String loadKey(String filePath) {
		String key = null;
		try {
			InputStream inputStream = NYBankService.class.getClassLoader().getResourceAsStream(filePath);
			StringBuilder strb = new StringBuilder();
			byte[] bytes = new byte[1024];
			int byteCount = 0;
			while ((byteCount = inputStream.read(bytes)) != -1) {
				strb.append(new String(bytes, 0, byteCount));
			}
			key = strb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("load key file error.", e);
		}

		return key;
	}

	/**
	 * 获取请求的系统头
	 * 
	 * @param transId
	 * @param type
	 * @return
	 */
	private SystermHead genSysHead(String transId, NYServiceType type) {
		SystermHead head = new SystermHead();
		head.setSeqNo(transId);
		head.setServiceCode(type.getCode());
		head.setScene(type.getScene());

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(timeFormat);
		String day = format.format(date);
		long timestamp = date.getTime();
		head.setDate(day);
		head.setTimestamp(String.valueOf(timestamp));

		head.setIp(getIp());
		return head;
	}

	/**
	 * 生成 xml 串
	 * 
	 * @param request
	 * @return
	 */
	private <T> String toXML(NYTransRequest request, Class<T> subClazz) {
		XStream xStream = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		xStream.processAnnotations(subClazz);
		String xml = Const.XML_HEAD_FLAG + "\r\n" + xStream.toXML(request);
		return xml;
	}

	/**
	 * 将 xml 流转成 java 对象
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T xml2Object(String xml, Class<T> clazz) {
		T t = null;
		try {
			if (xml != null && !StringUtils.isBlank(xml)) {
				XStream xStream = new XStream(new DomDriver("UTF-8",
						new NoNameCoder()));
				xStream.processAnnotations(clazz); // 处理别名映射
				t = (T) xStream.fromXML(xml);
			}
		} catch (Exception e) {
			logger.error("convert from xml error.", e);
		}

		return t;
	}

	/**
	 * 获取 IP
	 * 
	 * @return
	 */
	public String getIp() {
		String ip = ReadConfig.get("ip");
		targetUrl = ReadConfig.get("url_nybc");
		return ip;
	}

	/**
	 * 私钥加签
	 * 
	 * @param data
	 * @return
	 */
	public String getSignPrivKey(String data) {
		// String privKey = loadPrivateKey();
		String result = null;

		try {
			byte[] dataBytes = data.getBytes("utf-8");
			result = new String(
					org.apache.mina.util.Base64.encodeBase64(RSAUtils
							.encryptByPrivateKey(dataBytes, privKey)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("nybc sign error.", e);
		}

		return result;
	}
	
	/**
	 * 私钥加签
	 * 
	 * @param data
	 * @return
	 */
	public String getSignPrivKey(String data,String privKey) {
		// String privKey = loadPrivateKey();
		String result = null;

		try {
			byte[] dataBytes = data.getBytes("utf-8");
			result = new String(
					org.apache.mina.util.Base64.encodeBase64(RSAUtils
							.encryptByPrivateKey(dataBytes, privKey)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("nybc sign error.", e);
		}

		return result;
	}
	
	/**
	 * 公钥加签
	 * 
	 * @param data
	 * @return
	 */
	public String getSignPubKey(String data) {
		// String privKey = loadPrivateKey();
		String result = null;

		try {
			byte[] dataBytes = data.getBytes("utf-8");
			result = new String(
					org.apache.mina.util.Base64.encodeBase64(RSAUtils
							.encryptByPublicKey(dataBytes, bankKey)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("nybc sign error.", e);
		}

		return result;
	}
	/**
	 * 公钥加签
	 * 
	 * @param data
	 * @return
	 */
	public String getSignPubKey(String data,String pubKey) {
		// String privKey = loadPrivateKey();
		String result = null;

		try {
			byte[] dataBytes = data.getBytes("utf-8");
			result = new String(
					org.apache.mina.util.Base64.encodeBase64(RSAUtils
							.encryptByPublicKey(dataBytes, pubKey)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("nybc sign error.", e);
		}

		return result;
	}



    /**
     * 项目登记
     */
    @Override
    public String projecteRegist(ProjecteRegistRequestBody requestBody){
	        NYTransRequest request = new ProjecteRegistRequest();
	        // 交易流水号
	        String transId = SeqNumUtils.geneTransNo("2", BankType.NYBC.getCode(), BankTransType.PROJECTE_REGIST.getValue());
	        
	        request.setSystermHead(genSysHead(transId, NYServiceType.ProjecteRegist));
	        request.setAppHead(new AppHead());
	        request.setBody(requestBody);

	        // 发送并接收返回数据
	        String xml = toXML(request, ProjecteRegistRequest.class);
	        String reXml = send(xml);  	     
	        
	        return reXml;  	    
    }
    
      /*
	 * 对银行返回的 xml 报文验签
	 * 
	 * @param xml
	 *            @ sign
	 * @return
	 */
	public boolean verifySign(String xml, String sign) {
		boolean flag = false;
		try {
			logger.info("对银行响应报文开始验签＝＝＝＝＝＝＝＝＝＝＝＝＝＝（银行对响应我们的报文使用自己的私钥加密，我们豆芽使用银行提供我们的公钥验签）");
			byte[] signBytes = Base64.decodeBase64(sign.getBytes());
			String md5Val = new String(RSAUtils.decryptByPublicKey(signBytes,
					bankKey), "UTF-8");
			String xml2 = xml.replace(sign, "");
			String md5Val2 = MD5Utils.getMd5(xml2);
			if (md5Val2.equals(md5Val)) {
				flag = true;
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("银行响应报文验签失败，不支持的UnsupportedEncodingException", e);
		} catch (Exception e) {
			logger.error("银行响应报文验签失败2", e);
		} finally {
		}

		return flag;
	}

	
	  /*
		 * 对银行返回的 xml 报文验签
		 * 
		 * @param xml
		 *            @ sign
		 * @return
		 */
		public boolean verifySign(String xml, String sign,String pubKey) {
			boolean flag = false;
			try {
				logger.info("对银行响应报文开始验签＝＝＝＝＝＝＝＝＝＝＝＝＝＝（银行对响应我们的报文使用自己的私钥加密，我们豆芽使用银行提供我们的公钥验签）");
				byte[] signBytes = Base64.decodeBase64(sign.getBytes());
				String md5Val = new String(RSAUtils.decryptByPublicKey(signBytes,
						pubKey), "UTF-8");
				String xml2 = xml.replace(sign, "");
				String md5Val2 = MD5Utils.getMd5(xml2);
				if (md5Val2.equals(md5Val)) {
					flag = true;
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("银行响应报文验签失败，不支持的UnsupportedEncodingException", e);
			} catch (Exception e) {
				logger.error("银行响应报文验签失败2", e);
			} finally {
			}

			return flag;
		}
	 /*
		 * 对银行返回的 xml 报文验签
		 * 
		 * @param xml
		 *            @ sign
		 * @return
		 */
		public boolean verifySign2(String xml, String sign) {
			boolean flag = false;
			try {
				logger.info("verifySign2对银行响应报文开始验签＝＝＝＝＝＝＝＝＝＝＝＝＝＝（银行对响应我们的报文使用自己的私钥加密，我们豆芽使用银行提供我们的公钥验签）");
				byte[] signBytes = Base64.decodeBase64(sign.getBytes());
				String md5Val = new String(RSAUtils.decryptByPrivateKey(signBytes,
						privKey), "UTF-8");
				String xml2 = xml.replace(sign, "");
				String md5Val2 = MD5Utils.getMd5(xml2);
				if (md5Val2.equals(md5Val)) {
					flag = true;
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("verifySign2银行响应报文验签失败，不支持的UnsupportedEncodingException", e);
			} catch (Exception e) {
				logger.error("verifySign2银行响应报文验签失败2", e);
			} finally {
			}

			return flag;
		}
		
		
		 /*
		 * 对银行返回的 xml 报文验签
		 * 
		 * @param xml
		 *            @ sign
		 * @return
		 */
		public boolean verifySign2(String xml, String sign,String privKey) {
			boolean flag = false;
			try {
				logger.info("verifySign2对银行响应报文开始验签＝＝＝＝＝＝＝＝＝＝＝＝＝＝（银行对响应我们的报文使用自己的私钥加密，我们豆芽使用银行提供我们的公钥验签）");
				byte[] signBytes = Base64.decodeBase64(sign.getBytes());
				String md5Val = new String(RSAUtils.decryptByPrivateKey(signBytes,
						privKey), "UTF-8");
				String xml2 = xml.replace(sign, "");
				String md5Val2 = MD5Utils.getMd5(xml2);
				if (md5Val2.equals(md5Val)) {
					flag = true;
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("verifySign2银行响应报文验签失败，不支持的UnsupportedEncodingException", e);
			} catch (Exception e) {
				logger.error("verifySign2银行响应报文验签失败2", e);
			} finally {
			}

			return flag;
		}
	/**
	 * 添加签名到 xml
	 * 
	 * @param xml
	 * @param sign
	 * @return
	 */
	public String addSign2Xml(String xml, String sign) {
		String res = xml.replace("<SIGN_MSG></SIGN_MSG>", "<SIGN_MSG>" + sign
				+ "</SIGN_MSG>");
		return res;
	}

	/**
	 * 根据银行的返回码获取响应结果代码
	 * 
	 * @param response
	 * @return
	 */
	private ResponseCode getResponseStatus(BankResponse response) {
		ResponseCode resCode = ResponseCode.BANK_FAILED;
		logger.info("初始 ＝ " + resCode.getValue() + " bank="+ ResponseCode.BANK_FAILED.getValue());
		if (AccStatusCode.SUCCESS.getValue().equals(response.getCode())) {
			resCode = ResponseCode.BANK_SUCCESS;
		} else {
			String code = response.getCode();
			String message = response.getMessage();
			if (StringUtils.isEmpty(message)) {
				response.setMessage(resCode.getNameByValue(code));
			}
			
			resCode = resCode.getEnumByValue(code);
			logger.info("返回码 ＝ " + response.getCode());
			logger.info("返回信息 ＝ " + response.getMessage());
		}
		return resCode;
	}

	/**
	 * 保存交易记录
	 * 
	 * @param user
	 * @param transId
	 * @param chanlNo
	 * @param transType
	 */
	@Transactional
	@Override
	public void saveTransRecord(User user, String transId, String chanlNo,
			String transType) {
		BankResponseModel model = new BankResponseModel();
		model.setUserId(user.getUserId());
		// model.setBankCode(BankType.NYBC.getValue());
		model.setTransId(transId);
		model.setChannelNo(chanlNo);
		model.setTransType(transType);
		// model.setOperatType(transType);
		model.setStatus(AccStatusCode.NO_BACK.getValue());

		saveTransRecord(model);
	}
	
	
	/**
	 * 保存交易记录
	 * 
	 * @param user
	 * @param transId
	 * @param chanlNo
	 * @param transType
	 */
	@Transactional
	@Override
	public void callBack(User user, String transId,String transType) {
		
	}

	/**
	 * 更新交易记录的状态
	 * 
	 * @param transId
	 * @param bankTransId
	 * @param errCode
	 * @param errMsg
	 */
	@Transactional
	private void updateTransRecord(String transId, String bankTransId,
			String errCode, String errMsg, String sign) {
		BankResponseModel model = new BankResponseModel();
		model.setTransId(transId);
		model.setBankTransId(bankTransId);
		model.setErrorMsg(errMsg);
		model.setStatus(errCode);
		model.setSign(sign);

		updateTransRecord(model);
	}

	/**
	 * 组合成响应数据
	 * 
	 * @param params
	 * @return
	 */
	private BankResponseModel getResponseModel(Map<String, String> params) {
		BankResponseModel model = new BankResponseModel();
		model.setBankCode(BankType.NYBC.getValue());
		model.setBankTransId(params.get("flowNo"));
		model.setChannelNo(params.get("chanlNo"));
		model.setErrorMsg(params.get("errorMsg"));
		model.setSign(params.get("signMsg"));
		model.setStatus(params.get("errorCode"));
		model.setTransId(params.get("transId"));
		model.setTransType(params.get("transType"));
		// model.setOperatType(params.get("operatType"));
		return model;
	}

	/**
	 * 生成网关签名的参数字串
	 * 
	 * @param params
	 * @return
	 * 
	 */
	private String genSignDatas(Map<String, String> params) {
		Set<String> names = params.keySet();
		if (names.contains("transType")) {
			names.remove("transType");
		}
		// if(names.contains("operatType")){
		// names.remove("operatType");
		// }

		if (names.contains("transId")) {
			names.remove("transId");
		}

		if (names.contains("signMsg")) {
			params.put("signMsg", "");
		}
		if (names.contains("errorMsg")) {
			try {
				params.put("errorMsg",
						URLDecoder.decode(params.get("errorMsg"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("解码失败");
			}

		}

		// 排序
		Object[] names2 = names.toArray();
		Arrays.sort(names2);
		String[] values = new String[names2.length];

		for (int i = 0; i < names2.length; i++) {
			values[i] = params.get(names2[i]);
		}

		String result = StringUtils.join(values, "|");
		result = result.substring(0, result.length() - 1);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#queryRegistResult(java.lang.String
	 * )
	 */
	@Override
	public ResponseCode queryRegistResult(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douins.bank.service.BankServiceIFC#recharge(java.lang.String)
	 */
	@Override
	public ResponseCode recharge(String token, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douins.bank.service.BankServiceIFC#payment(java.lang.String)
	 */
	@Override
	public ResponseCode payment(String token, String bAccount,
			BigDecimal amoutn, BankTransType transType, String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.douins.bank.service.BankServiceIFC#queryConsumeRecords(java.lang.
	 * String)
	 */
	@Override
	public ResponseCode queryConsumeRecords(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.douins.bank.service.BankServiceIFC#withdraw(java.lang.String,
	 * java.math.BigDecimal)
	 */
	@Override
	public ResponseCode withdraw(String token, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAmountChange(String token, BigDecimal amount,
			String transType, String tradeNo, String bankName, String bankCode,
			String accountName, String accountNo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateAmountChange(String tradeNo, String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResponseCode QuerySingleResults(String token,
			BankTransType transType, String channelSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseCode queryBankNo(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseCode UnbindAccount(String token, String channelSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertNYCallbackInfo(CallBackRequest callbackInfo) {
		// TODO Auto-generated method stub
		return bankResponeDao.insertNYCallbackInfo(callbackInfo);
	}
	@Override
	@Transactional
	public ResponseTransVo<BankChangeResponseVo> AccoutManagerWithBank(BankChangeRequest trans) {
		ResponseTransVo<BankChangeResponseVo> responseTransVo = null;
		ResponseCode responseCode = ResponseCode.FAILED;
		ResponseTrans responseTrans = null;
		String accessToken = null;
	    String transId = null;
		try {
			transId = trans.getRequestTrans().getTransId();
			accessToken = trans.getAccessToken();
			UserStatusInfoVo userStatus = userService.getUserStatusInfo(accessToken);
			BankChangeResponseVo bankChangeResponseVo = new BankChangeResponseVo();
			/* 获取用户状态、密码状态、帐号状态 */
			bankChangeResponseVo.setUserStatus(userStatus.getUserStatus());
			bankChangeResponseVo.setPasswordStatus(userStatus.getPasswordStatus());
			bankChangeResponseVo.setAccountStatus(userStatus.getAccountStatus());
			// 如果用户未开户成功则不去拼接URL
			if (userStatus.getUserStatus() == null || !userStatus.getUserStatus().equals(UserStatus.SUCCESS.getValue())) {
				responseCode = ResponseCode.SUCCESS;
				responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId); 
				responseTransVo = new ResponseTransVo<BankChangeResponseVo> (responseTrans,accessToken,bankChangeResponseVo);
				return responseTransVo;
			}
			String urlType = trans.getRequestParams().getUrlType();
			String acctNoId = userStatus.getVirtualAccountId();
			
			//拼接URL
			if (urlType != null && !"".equals(urlType)) {
				/*拼接 设置支付密码、修改支付密码、重置支付密码 的URL*/
				bankChangeResponseVo.setSetPayPasswordUrl(buildUrl(acctNoId,userStatus.getGateWayUrl().getUrlPwdSetH5(),null,null));
				bankChangeResponseVo.setModifyPayPasswordUrl(buildUrl(acctNoId,userStatus.getGateWayUrl().getUrlPwdModifyH5(),null,null));
				bankChangeResponseVo.setResetPayPasswordUrl(buildUrl(acctNoId,userStatus.getGateWayUrl().getUrlPwdResetH5(),null,null));
				/*拼接充值、提现的URL*/
				if (urlType.equals(TransType.ADDMONEY.getValue()) || urlType.equals(TransType.DELETEMONEY.getValue())) {
					String payAmount = trans.getRequestParams().getPayAmount();
					if (payAmount == null || "".equals(payAmount))
						throw new NullArgumentException("充值/提现金额为空！");
					
					UserAuthority findUserByToken = authorityService.findUserByToken(accessToken);
					// 先保存数据库，再拼接URL
					BusinessTrans businessTrans = new BusinessTrans();
					businessTrans.setBusinessId(UUID.randomUUID().toString());
					businessTrans.setOpUser(SystemConstant.OP_USER);
					businessTrans.setIsvalid(SystemConstant.ISVALID_NO);
					businessTrans.setTransUserToken(findUserByToken.getUid());
					String transChannel = trans.getRequestTrans().getTransChannel();
					if (TransChannel.IOS.getValue().equals(transChannel)) {
						businessTrans.setTransChannel(transChannel);
					}
					if (TransChannel.AND.getValue().equals(transChannel)) {
						businessTrans.setTransChannel(transChannel);
					}
					businessTrans.setStatus(BusinessTransStatus.INIT.getValue());
					businessTrans.setTransNo(acctNoId);
					businessTrans.setPayMoney(new BigDecimal(payAmount));
					businessTrans.setTransId(transId);
					businessTrans.setTransTime(new Date());
					businessTrans.setUpdateDate(new Date());
					businessTrans.setCreateDate(new Date());
					
					if (TransType.ADDMONEY.getValue().equals(urlType)) {// 电子账户充值 urlType=10
						businessTrans.setTransType(urlType);
						trasBusinessService.saveTrasBusiness(businessTrans);
						bankChangeResponseVo.setRechargeUrl(buildUrl(acctNoId,userStatus.getGateWayUrl().getUrlRechargeH5(),payAmount,"recharge"));
					}
					if (TransType.DELETEMONEY.getValue().equals(urlType)) {// 电子账户取现 urlType=11
						businessTrans.setTransType(urlType);
						trasBusinessService.saveTrasBusiness(businessTrans);
						bankChangeResponseVo.setWithdrawUrl(buildUrl(acctNoId,userStatus.getGateWayUrl().getUrlWithdrawH5(),payAmount,"withdraw"));
					}
				}
			}
			responseCode = ResponseCode.SUCCESS;
			responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId); 
			responseTransVo = new ResponseTransVo<BankChangeResponseVo> (responseTrans,accessToken,bankChangeResponseVo);
		} catch (Exception e) {
			responseCode = ResponseCode.DATAREAD_ERROR;
			logger.error("get bankChangeUrl error!", e);
		}
		return responseTransVo;
	}
	
	private String buildUrl(String acctNoId,String remoteUrl,String payAmount,String flag){
		StringBuffer resultUrl = new StringBuffer();
		StringBuffer signParam = new StringBuffer();
		String signMsg = "";
		//String chanlNo = "PTP0000009";// 南粤银行要写死这样
		String chanlNo = WebUseConstant.CHANL_NO;// 南粤银行要写死这样
		//String url = "douins://douinsNyAccount";
		String url = WebUseConstant.URL;
		signParam.append(chanlNo).append("|").append(acctNoId);
		if(payAmount==null){
			signParam.append("|").append(url);
			signMsg = signForRequest(signParam.toString());
			resultUrl.append(remoteUrl).append("?chanlNo=").append(chanlNo).append("&acctNoId=").append(acctNoId).append("&url=").append(url).append("&signMsg=").append(signMsg);
		}else{//充值、提现
			long chanlFlowNo = System.currentTimeMillis();// 流水号
			signParam.append("|").append(chanlFlowNo).append("|").append(payAmount).append("|").append(url);
			signMsg = signForRequest(signParam.toString());
			resultUrl.append(remoteUrl).append("?chanlNo=").append(chanlNo).append("&acctNoId=").append(acctNoId).append("&chanlFlowNo=").append(chanlFlowNo);
			if("recharge".equals(flag)){//充值
				resultUrl.append("&payAmount=").append(payAmount);
			}else{//提现
				resultUrl.append("&tranAmt=").append(payAmount);
			}
			resultUrl.append("&url=").append(url).append("&signMsg=").append(signMsg);
		}
		return resultUrl.toString();
	}
	/**
	 * 
	 */
	public BankDict getBankDict(BankDict bankDict){
		return bankResponeDao.getBankDict(bankDict);
	}

	public List<String> getBankName(String payAcct) {
		return bankResponeDao.getBankName(payAcct);
	}
}

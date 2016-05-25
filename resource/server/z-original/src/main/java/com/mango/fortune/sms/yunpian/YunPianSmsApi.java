package com.mango.fortune.sms.yunpian;

import java.io.IOException;

import org.apache.commons.httpclient.NameValuePair;

import com.mango.fortune.util.HttpClientUtils;

/**
 * 短信http接口的java代码调用示例
 * @author jacky
 * @since 2013-12-1
 */
public class YunPianSmsApi {
	
	public static final String SUCCESSCODE = "0";
	
	/**
	 * 服务http地址
	 */
	private static String BASE_URI = "http://yunpian.com";
	/**
	 * 服务版本号
	 */
	private static String VERSION = "v1";
	/**
	 * 编码格式
	 */
	private static String ENCODING = "UTF-8";
	/**
	 * 查账户信息的http地址
	 */
	private static String URI_GET_USER_INFO = BASE_URI + "/" + VERSION + "/user/get.json";
	/**
	 * 通用发送接口的http地址
	 */
	private static String URI_SEND_SMS = BASE_URI + "/" + VERSION + "/sms/send.json";
	/**
	 * 模板发送接口的http地址
	 */
	private static String URI_TPL_SEND_SMS = BASE_URI + "/" + VERSION + "/sms/tpl_send.json";
	
	/**
	 * 取账户信息
	 * @return json格式字符串
	 * @throws IOException 
	 */
	public static String getUserInfo(String apikey) throws IOException{
		NameValuePair[] data = new NameValuePair[1];
		data[0] =  new NameValuePair("apikey",apikey);
		return HttpClientUtils.httpClientForm(URI_GET_USER_INFO, data);
	}
	/**
	 * 发短信
	 * @param apikey apikey
	 * @param text　短信内容　
	 * @param mobile　接受的手机号
	 * @return json格式字符串
	 * @throws IOException 
	 */
	public static String sendSms(String apikey, String text, String mobile) throws IOException{
		
		NameValuePair[] nameValuePairs = new NameValuePair[3];
		nameValuePairs[0] = new NameValuePair("apikey", apikey);
		nameValuePairs[1] = new NameValuePair("text", text);
		nameValuePairs[2] = new NameValuePair("mobile", mobile);		
		return HttpClientUtils.httpClientForm(URI_SEND_SMS, nameValuePairs);
		
	}
	
	/**
	 * 通过模板发送短信
	 * @param apikey apikey
	 * @param tpl_id　模板id
	 * @param tpl_value　模板变量值　
	 * @param mobile　接受的手机号
	 * @return json格式字符串
	 * @throws IOException 
	 */
	public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException{
	
		NameValuePair[] nameValuePairs = new NameValuePair[4];
		nameValuePairs[0] = new NameValuePair("apikey", apikey);
		nameValuePairs[1] = new NameValuePair("tpl_id", String.valueOf(tpl_id));
		nameValuePairs[2] = new NameValuePair("tpl_value", tpl_value);
		nameValuePairs[3] = new NameValuePair("mobile", mobile);		
		return HttpClientUtils.httpClientForm(URI_TPL_SEND_SMS, nameValuePairs);
	}
	
	public static void main(String[] args) throws IOException {
		//修改为您的apikey
		String apikey = "f24441bcc558f1f69151cf1f6e393095";
		//修改为您要发送的手机号
		String mobile = "18656516110";
		
		/**************** 查账户信息调用示例 *****************/
		//System.out.println(YunPianSmsApi.getUserInfo(apikey));
		
		/**************** 使用通用接口发短信 *****************/
		/*//设置您要发送的内容
		String text = "【红银财富】调试短信：温馨提醒：尊敬的王旭辉您好，您本次的理财金额10元已划扣成功。如有疑问请致电400-8800-688红银财富客户中心。";
		//发短信调用示例
		String msg = YunPianSmsApi.sendSms(apikey, text, mobile);
		System.out.println(msg);
		YunPianResponse resp = JsonUtils.readJson(msg, YunPianResponse.class);
		System.out.println("==code:"+resp.getCode()+";result.sid"+resp.getResult());
		if(resp.getResult()!=null){
			System.out.println("==result:"+resp.getResult().getSid());
		}*/
		
		/**//**************** 使用模板接口发短信 *****************//*
		//设置模板ID，如使用1号模板:您的验证码是#code#【#company#】
		long tpl_id=1;
		//设置对应的模板变量值
		String tpl_value="#code#=1234&#company#=云片网";
		//模板发送的调用示例
		System.out.println(YunPianSmsApi.tplSendSms(apikey, tpl_id, tpl_value, mobile));*/
	}
}               

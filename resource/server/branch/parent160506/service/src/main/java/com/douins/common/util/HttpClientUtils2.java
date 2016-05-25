/**
 * 
 */
package com.douins.common.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/** 
* @ClassName: HttpClientUtils2 
* @Description: 使用新版的 httpClient
* @author G. F. 
* @date 2015年12月6日 下午9:02:48 
*  
*/
public class HttpClientUtils2 {
    private static final Logger logger = Logger.getLogger(HttpClientUtils2.class);
    
    private static CloseableHttpClient httpClient;
    static{
        httpClient = HttpClients.createDefault();
    }
    
    /**
     * 配置请求头以及链接超时时间
     * @param requestBase
     */
    private static void config(HttpRequestBase requestBase){
        requestBase.setHeader("User-Agent", "Mozilla/5.0");
        requestBase.setHeader("Accept","text/plain,text/html,application/xhtml+xml,application/xml;application/x-www-form-urlencoded;q=0.9,*/*;q=0.8");
        requestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        requestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");
         
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(30000)
                .setSocketTimeout(30000)
                .build();
        requestBase.setConfig(requestConfig);  
    }
    
    /**
     * post 方式提交 xml 数据 form表单
     * @param url
     * @param xml
     * @param encode
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendXml_Post(String url, String xml, String encode) throws ClientProtocolException, IOException{
    	 String reXml= "";
    	try{
    		    HttpPost  httpPost = new HttpPost(url);
    	        config(httpPost);
    	        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(xml.getBytes(), ContentType.APPLICATION_FORM_URLENCODED);
    	        httpPost.setEntity(byteArrayEntity);
    	        CloseableHttpResponse response = httpClient.execute(httpPost);
    	        HttpEntity responseEntity = response.getEntity();
    	        reXml = EntityUtils.toString(responseEntity);
    	        response.close();
    	}catch(Exception e){
    		logger.error("http Client excute Exception", e);
    	}
       
        return reXml;
    }
    
    public static String sendXml_Post_text(String url, String xml, String encode) throws ClientProtocolException, IOException{
        HttpPost  httpPost = new HttpPost(url);
        config(httpPost);
        StringEntity requestEntity = new StringEntity(xml, encode);
        httpPost.setEntity(requestEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        String reXml = EntityUtils.toString(responseEntity);
        response.close();

        return reXml;
    }
    public static void main(String[] args) throws ClientProtocolException, IOException {
    	//String url ="http://127.0.0.1:8080/agencyapi/uw";
    	//String x ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PackageList><Package>好地方火锅/Package> </PackageList>";
    	//System.out.println("ss"+sendXml_Post_text(url,x,"utf-8"));
    	String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"<SDOROOT>"+"	<SYS_HEAD>"+"	<SIGN_MSG>qqlLzExAFg8L/Fxla/2BxHy3LERDZA12NULE7/64iYKp5PTm7jrCuTPS/lq0TJBczBg93nRZ/sFednI/ObzMAFi0UZkH6Qj9WUeHRrlPIqDa1LjC73AEkRlkVg5ut9ckJMSobbjBrjrAGajTgZa4p+VT8PHGXtK5/1jgleXfToc=</SIGN_MSG>"+"	</SYS_HEAD>"+"	<BODY>"+"		<FLOW_NO>0101062016020625637900</FLOW_NO>"+"		<CUST_NO>EC10000285592</CUST_NO>"+"		<CHANL_NO>PTP100000000001</CHANL_NO>"+"		<TRAN_DATE>20160206</TRAN_DATE>"+"		<TRAN_TIME>094152</TRAN_TIME>"+"		<STATUS>03</STATUS>"+"		<RET_CODE>0000</RET_CODE>"+"		<RET_MSG>交易成功</RET_MSG>"+"		<PROJ_CODE>PTP00224</PROJ_CODE>"+"		<PAY_ACCT>663300990000</PAY_ACCT>"+"		<PAY_OPENBRAN>1234</PAY_OPENBRAN>"+"		<PAY_ACCTNAME>324324132</PAY_ACCTNAME>"+"		<RCV_ACCT>3432</RCV_ACCT>"+"		<RCV_ACCTNAME>41</RCV_ACCTNAME>"+"		<RCV_BANK>14</RCV_BANK>"+"		<TRAN_TYPE>14</TRAN_TYPE>"+"		<CURR>CNY</CURR>"+"		<AMT>234</AMT>"+"		<FEE>234</FEE>"+"		<USAGE>324</USAGE>"+"		<PAY_TYPE>4231</PAY_TYPE>"+"		<FREEZE_NO>324</FREEZE_NO>"+"		<PROJ_NAME>324</PROJ_NAME>"+"		<TRAN_TITLE>1</TRAN_TITLE>"+"		<TRAN_DESC>23</TRAN_DESC>"+"		<CHANL_FLOW_NO>43242314</CHANL_FLOW_NO>"+"	</BODY>"+"</SDOROOT>";
		String encode="utf-8";
		System.out.println(HttpClientUtils2.sendXml_Post_text("http://127.0.0.1:8080/api/bank/ny/callback/payinfo", xml, encode));
		
	}
    
    /**
     * get 方式提交数据
     * @param url
     * @param datas
     * @param encode 
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendData_Get(String url, String datas, String encode) throws ClientProtocolException, IOException{
        HttpGet httpGet = new HttpGet(url + "?" + datas);
        config(httpGet);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        String result = EntityUtils.toString(responseEntity);
        response.close();
        
        return result;
    }
}

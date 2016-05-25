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

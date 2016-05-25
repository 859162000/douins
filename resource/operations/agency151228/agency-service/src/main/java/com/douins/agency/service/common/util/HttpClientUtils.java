/**
 * 
 */
package com.douins.agency.service.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

/** 
* @ClassName: HttpClientUtils2 
* @Description: 使用新版的 httpClient
* @author G. F. 
* @date 2015年12月6日 下午9:02:48 
*  
*/
public class HttpClientUtils {
    private static final Logger logger =  Logger.getLogger(HttpClientUtils.class);
    
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
        requestBase.setHeader("Accept","text/plain,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        requestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        requestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312,gbk;q=0.7,*;q=0.7");
         
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(30000)
                .setSocketTimeout(30000)
                .build();
        requestBase.setConfig(requestConfig);
    }
    
    /**
     * post 方式提交数据
     * @param url
     * @param data
     * @param encode
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String Post(String url, String data, String encode) throws ClientProtocolException, IOException{
        HttpPost  httpPost = new HttpPost(url);
        config(httpPost);
        
        StringEntity requestEntity = new StringEntity(data, encode);
        requestEntity.setContentEncoding(encode);
        httpPost.setEntity(requestEntity);
        String reXml = null;
        try{
        	logger.info(url);
        	logger.info(data);
        	logger.info(encode);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            reXml = EntityUtils.toString(responseEntity);
            response.close();
        }catch (Exception e){
            logger.error("send data error.", e);
        }

        return reXml;
    }
    
    public static String httpsPost (String url, String data, String encode) throws Exception {
        String result = null;
        File file = ResourceUtils.getFile("classpath:truststore/caTest2.jks");
        logger.info("cer = "  + file.getPath());
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(file, "123456".toCharArray(),
                        new TrustSelfSignedStrategy())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpPost = new HttpPost(url);
            
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        
        return result;
    }
    
    public static String httpsPostCCIC(String url, String data, String encode, String transCode){
        String reData = null;
        try {
            File file = null;
            String password = Configs.get("jsk_password_ccic");
            String evn = Configs.get("evn");
            if("dev".equals(evn)){
                file = ResourceUtils.getFile("classpath:truststore/caTest2.jks");
            }else if("online".equals(evn)){
                file = ResourceUtils.getFile("classpath:truststore/**.jks");
            }
            
            System.setProperty("javax.net.ssl.trustStore", file.getPath());    
            System.setProperty("javax.net.ssl.trustStorePassword", password);    
            System.setProperty("javax.net.ssl.keyStoreType","JKS");    
            System.setProperty("javax.net.ssl.keyStore",file.getPath()) ;    
            System.setProperty("javax.net.ssl.keyStorePassword", password) ;
            
            URL url2 = new URL(url);
            HttpsURLConnection http = (HttpsURLConnection) url2.openConnection();
            http.setDoOutput(true);  
            http.setDoInput(true);
            http.setRequestProperty("GW_CH_CODE", "310044");//渠道代码
            http.setRequestProperty("GW_CH_TX", transCode);//交易代码
            http.setRequestMethod("POST");  
            OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream());    
            String xmlInfo = data;
            out.write(xmlInfo);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(http
                    .getInputStream(), "GBK"));
            String line = "";
            StringBuilder result = new StringBuilder();
            for (line = br.readLine(); line != null; line = br.readLine()) {
                result.append(line);
            }
           
            // 转码
            reData = result.toString();//new String(result.toString().getBytes("iso-8859-1"), "GBK");
        } catch (Exception e) {
            logger.error("CCIC send data error.", e);
        }
        
        return reData;
    }
}

/**
 * 
 */
package com.douins.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/** 
* @ClassName: TrustStoreUtils 
* @Description: Https 信任证书管理工具
* @author G. F. 
* @date 2016年1月14日 下午8:05:44 
*  
*/
@Component
public class TrustStoreUtils {
    private final Logger logger =  Logger.getLogger(TrustStoreUtils.class);
    
    /**
     * 获得KeyStore.
     * @param keyStorePath
     *            密钥库路径
     * @param password
     *            密码
     * @return 密钥库
     * @throws Exception
     */
    public KeyStore getKeyStore(String keyStorePath, String password)
            throws Exception {
        // 实例化密钥库
        KeyStore ks = KeyStore.getInstance("JKS");
        logger.info("实例化密钥库:"+ks.toString());
        // 获得密钥库文件流
        FileInputStream is = new FileInputStream(keyStorePath);
        logger.info("获得密钥库文件流:"+is.toString());
        // 加载密钥库
        try {
        ks.load(is, password.toCharArray());
        logger.info(" 加载密钥库:"+ks.toString());
        }catch (Exception e) {
         logger.info(e);
       }
        // 关闭密钥库文件流
        is.close();
        return ks;
    }
    
    /**
     * 获得SSLSocketFactory.
     * @param password
     *            密码
     * @param keyStorePath
     *            密钥库路径
     * @param trustStorePath
     *            信任库路径
     * @return SSLSocketFactory
     * @throws Exception
     */
    public SSLContext getSSLContext(String password,
            String keyStorePath, String trustStorePath) throws Exception {
        // 实例化密钥库
        KeyManagerFactory keyManagerFactory = KeyManagerFactory
                .getInstance(KeyManagerFactory.getDefaultAlgorithm());
        logger.info("实例化密钥工厂:"+keyManagerFactory.toString());
        // 获得密钥库
        KeyStore keyStore = getKeyStore(keyStorePath, password);
        logger.info("获得密钥库:"+keyStore.toString()+"#"+keyStorePath+"#"+password);
        // 初始化密钥工厂
        keyManagerFactory.init(keyStore, password.toCharArray());
        logger.info("初始化密钥工厂:"+keyStore.toString()+"#"+keyManagerFactory.toString()+"#"+password);
        // 实例化信任库
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        logger.info("实例化信任库:"+trustManagerFactory.toString());
        // 获得信任库
        KeyStore trustStore = getKeyStore(trustStorePath, password);
        logger.info("获得信任库:"+trustStore.getType());
        // 初始化信任库
        trustManagerFactory.init(trustStore);
        logger.info("初始化信任库:"+trustManagerFactory.getAlgorithm());
        // 实例化SSL上下文
        SSLContext ctx = SSLContext.getInstance("TLS");
        logger.info("实例化SSL上下文:"+ctx.toString());
        // 初始化SSL上下文
        ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        logger.info("初始化SSL上下文:"+ctx.toString());
        // 获得SSLSocketFactory
        return ctx;
    }

    /**
     * 初始化HttpsURLConnection.
     * @param password
     *            密码
     * @param keyStorePath
     *            密钥库路径
     * @param trustStorePath
     *            信任库路径
     * @throws Exception
     */
    public void initHttpsURLConnection(String password,
        String keyStorePath, String trustStorePath) throws Exception {
        // 声明SSL上下文
        SSLContext sslContext = null;
        // 实例化主机名验证接口
        HostnameVerifier hnv = new TrustStoreUtils.MyHostnameVerifier();
        try {
        	logger.info("sslContext:start");
            sslContext = getSSLContext(password, keyStorePath, trustStorePath);
            
        } catch (GeneralSecurityException e) {
            logger.info("sslContext:fail");
            logger.error("trustStoreUtils: Https 连接初始化失败 ", e);
        }
        
        logger.info("sslContext:"+sslContext.toString());
      
        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }
        HttpsURLConnection.setDefaultHostnameVerifier(hnv);
    }
    
    /**
     * 发送请求.
     * @param httpsUrl
     *            请求的地址
     * @param xmlStr
     *            请求的数据
     */
    public void post(String httpsUrl, String xmlStr) {
        HttpsURLConnection urlCon = null;
        try {
            urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("POST");
            urlCon.setRequestProperty("Content-Length",
                    String.valueOf(xmlStr.getBytes().length));
            urlCon.setUseCaches(false);
            //设置为gbk可以解决服务器接收时读取的数据中文乱码问题
            urlCon.getOutputStream().write(xmlStr.getBytes("gbk"));
            urlCon.getOutputStream().flush();
            urlCon.getOutputStream().close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlCon.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现用于主机名验证的基接口。 
     * 在握手期间，如果 URL 的主机名和服务器的标识主机名不匹配，则验证机制可以回调此接口的实现程序来确定是否应该允许此连接。
     */
    public class MyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
//            if("localhost".equals(hostname)){
//                return true;
//            } else {
//                return false;
//            }
            return true;
        }
    }
    
    /**
     * CCIC 专用的 post 方法
     * @param url
     * @param data
     * @param encode
     * @param transCode
     * @return
     */
    public String httpsPostCCIC(String url, String data, String encode, String transCode){
        String reData = null;
        try {
            URL url2 = new URL(url);
            HttpsURLConnection http = (HttpsURLConnection) url2.openConnection();
            http.setDoOutput(true);  
            http.setDoInput(true);
            http.setRequestProperty("GW_CH_CODE", "310044");    // 渠道代码
            http.setRequestProperty("GW_CH_TX", transCode);         // 交易代码
            http.setRequestMethod("POST");  
            OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream());    
            String xmlInfo = data;
            out.write(xmlInfo);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), encode));
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
    
    public String httpsPostLianlife(String url, String data, String encode, String transCode){
    	  String reData = null;
    	  try {
              URL url2 = new URL(url);
              //HttpsURLConnection http = (HttpsURLConnection) url2.openConnection();
              HttpURLConnection http = (HttpURLConnection) url2.openConnection();
              http.setDoOutput(true);  
              http.setDoInput(true);
             // http.setRequestProperty("GW_CH_CODE", "310044");    // 渠道代码
             // http.setRequestProperty("GW_CH_TX", transCode);         // 交易代码
              http.setRequestMethod("POST");  
              OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream());    
              String xmlInfo = data;
              out.write(xmlInfo);
              out.flush();
              out.close();
              BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), encode));
              String line = "";
              StringBuilder result = new StringBuilder();
              for (line = br.readLine(); line != null; line = br.readLine()) {
                  result.append(line);
              }
             
              // 转码
              reData = result.toString();
          } catch (Exception e) {
              logger.error("lianlife send data error.", e);
          }

    	  return reData;
    }
    
    /**
     * 测试方法.
     * @param args
     * @throws Exception
     */
//    @Test
//    public  void test() throws Exception {
//        // 密码
//        String password = "123456";
//        // 密钥库
//        String keyStorePath = "/Users/gcl/Desktop/ccickeystore.jks";
//        // 信任库
//        String trustStorePath = "/Users/gcl/Desktop/ccictruststore.jks";
//        // 本地起的https服务
//        String httpsUrl = "https://ywtest.ccic-net.com.cn:8912/newlife";
//        // 传输文本
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fruitShop><fruits><fruit><kind>萝卜</kind></fruit><fruit><kind>菠萝</kind></fruit></fruits></fruitShop>";
//        initHttpsURLConnection(password, keyStorePath, trustStorePath);
//        // 发起请求
//        post(httpsUrl, xmlStr);
//        post(httpsUrl, xmlStr); post(httpsUrl, xmlStr);
//    }
    
    public String httpPostAgency(Map<String,String> map,String charset){
    	String url = Configs.get("agency_with_getSumProductSales");
    	String result = null;
    	HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                System.out.println("Warning: URL Host: " + urlHostName + " vs. "  + session.getPeerHost());
                return true;
            }
        };
    	try {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator(); 
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            } 
            String requestParam = URLEncodedUtils.format(list, charset); 
            URL requestUrl = new URL(url + "?" + requestParam); 
            /*测试环境加上以下两句，用于跳过https验证签名 正式环境注释掉*/
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            /*测试环境加上以下两句，用于跳过https验证签名 正式环境注释掉*/
    		HttpURLConnection http = (HttpURLConnection) requestUrl.openConnection();
            http.setDoOutput(true);  
            http.setDoInput(true);
            http.setRequestMethod("POST"); 
            int code = http.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), charset));
            if (HttpsURLConnection.HTTP_OK == code){ 
                String temp = in.readLine(); 
                while (temp != null) { 
                    if (result != null) 
                        result += temp; 
                    else 
                        result = temp; 
                    temp = in.readLine(); 
                } 
            } 
    	}catch (Exception e) {
            logger.error("get productSumSales data error.", e);
        }
    	return result;
    }
    
	private static void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}

	public static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}

}

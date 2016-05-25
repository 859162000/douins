/**
 * 
 */
package com.douins.agency.service.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

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
        // 获得密钥库文件流
        FileInputStream is = new FileInputStream(keyStorePath);
        // 加载密钥库
        ks.load(is, password.toCharArray());
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
        // 获得密钥库
        KeyStore keyStore = getKeyStore(keyStorePath, password);
        // 初始化密钥工厂
        keyManagerFactory.init(keyStore, password.toCharArray());

        // 实例化信任库
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        // 获得信任库
        KeyStore trustStore = getKeyStore(trustStorePath, password);
        // 初始化信任库
        trustManagerFactory.init(trustStore);
        // 实例化SSL上下文
        SSLContext ctx = SSLContext.getInstance("TLS");
        // 初始化SSL上下文
        ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
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
            sslContext = getSSLContext(password, keyStorePath, trustStorePath);
        } catch (GeneralSecurityException e) {
            logger.error("trustStoreUtils: Https 连接初始化失败 ", e);
        }
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
    
//    /**
//     * 测试方法.
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        // 密码
//        String password = "123456";
//        // 密钥库
//        String keyStorePath = "/Users/gaofu/Desktop/ccic/ccicKeystore.jks";
//        // 信任库
//        String trustStorePath = "/Users/gaofu/Desktop/ccic/ccictruststore.jks";
//        // 本地起的https服务
//        String httpsUrl = "https://ywtest.ccic-net.com.cn:8912/newlife";
//        // 传输文本
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fruitShop><fruits><fruit><kind>萝卜</kind></fruit><fruit><kind>菠萝</kind></fruit></fruits></fruitShop>";
//        initHttpsURLConnection(password, keyStorePath, trustStorePath);
//        // 发起请求
//        post(httpsUrl, xmlStr);
//        post(httpsUrl, xmlStr); post(httpsUrl, xmlStr);
//    }
}

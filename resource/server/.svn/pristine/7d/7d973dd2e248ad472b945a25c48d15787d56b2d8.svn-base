package com.mango.fortune.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.NestedBusinessException;



/**
 * 描述：<br>
 * 作者：wangpengfei <br>
 * 修改日期：2014-3-6上午09:10:39 <br>
 * E-mail: wangpengfei14079@sinosoft.com.cn <br>
 */
public class HttpClientUtils {

	private static SimpleLogger logger =  SimpleLogger.getLogger(HttpClientUtils.class);

	private static final MultiThreadedHttpConnectionManager connectionManager;
	private static HttpClient httpclient;
	private static HttpConnectionManagerParams managerParams;
	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		httpclient = new HttpClient(connectionManager);
		managerParams = httpclient.getHttpConnectionManager().getParams();
		managerParams.setMaxTotalConnections(600);
		managerParams.setDefaultMaxConnectionsPerHost(300);
		/* 连接超时 通过网络与服务器建立连接的超时时间 */
		managerParams.setConnectionTimeout(20000);
		/* 请求超时 从服务器获取响应数据需要等待的时间 */
		managerParams.setSoTimeout(30000);
	}
	/**
	 * 
	 * @param URL
	 * @param parameters pojo参数
	 * @return
	 */
	public static String httpClientForm(String URL, Object parameters) {
		// 组合请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Method[] ms = parameters.getClass().getMethods();
		for (int i = 0; i < ms.length; i++) {
			Method m = ms[i];
			String name = m.getName();
			if (name.startsWith("get")) {
				String param = name.substring(3, name.length());
				param = param.substring(0, 1).toLowerCase()	+ param.substring(1, param.length());
				if (param.equals("class")) {
					continue;
				}				
				try {
					Object  value = m.invoke(parameters, null);
					NameValuePair nvp = new NameValuePair(param,value.toString());
					list.add(nvp);
				} catch (Exception e) {
					e.printStackTrace();
					throw new NestedBusinessException("未知异常，未定义处理机制！", e);
				}
			}
		}
		NameValuePair[] nvps = new NameValuePair[list.size()];
		return httpClientForm(URL,nvps);	
	}
	

	public static String httpClientForm(String URL, NameValuePair[] data) {
		String result = null;
		logger.info("start +++ 请求接口地址：" + URL);
		logger.info("请求参数：" + ToStringBuilder.reflectionToString(data, ToStringStyle.MULTI_LINE_STYLE));
		PostMethod post = new PostMethod(URL.trim());
		InputStream res = null;
		try {
			post.getParams().setContentCharset("UTF-8");
			// 解决Httpclient远程请求所造成Socket没有释放
			post.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			post.addRequestHeader("Connection", "close");
			
			post.setRequestBody(data);
			// 使用POST方法
			httpclient.executeMethod(post);

			int code = post.getStatusCode();
			if (code < HttpURLConnection.HTTP_OK || code >= HttpURLConnection.HTTP_MULT_CHOICE) {
				post.abort();
				String errorMessage = "httpclient错误 : " + post.getStatusLine();
				logger.error(errorMessage);
				throw new IllegalStateException(errorMessage);
			}
			// 打印返回的信息
			res = post.getResponseBodyAsStream();
			if(res != null){
				result = getByInputStream(res, "UTF-8");
			}
		} catch (ConnectTimeoutException e) {
			post.abort();
			logger.error("与接口连接超时，无法连接到目标服务器", e);
			throw new NestedBusinessException("与接口超时，无法连接到目标服务器", e );
		} catch (NoRouteToHostException e) {
			post.abort();
			logger.error("与接口连接失败，可能是服务器所在网络异常", e);
			throw new NestedBusinessException("与接口连接失败，可能是服务器所在网络异常", e);
		} catch (Exception e) {
			post.abort();
			e.printStackTrace();
			logger.error("未知异常，未定义处理机制！", e);
			throw new NestedBusinessException("未知异常，未定义处理机制！", e);
		} finally {
			if(res != null){
                try {
                	res.close ();
            	}catch (IOException e){
                	e.printStackTrace ();
            	}
            }
			post.releaseConnection();
		}
		logger.info("返回结果：" + result);
		logger.info("end +++ 请求接口地址：" + URL);
		return result;
	}

	public static String getByInputStream(InputStream in, String encode) {
		BufferedReader bufferReader;
		InputStreamReader reader = null;
		try {
			if (encode == null) {
				reader = new InputStreamReader(in);
			} else {
				reader = new InputStreamReader(in, encode);
			}
			bufferReader = new BufferedReader(reader);
			StringBuffer buffer = new StringBuffer();
			while (true) {
				String line = bufferReader.readLine();
				if (line == null) {
					break;
				}
				buffer.append(line);
			}
			bufferReader.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {
				 reader.close ();
         	}catch (IOException e){
             	e.printStackTrace ();
         	}
		}
		return null;
	}

	public static void main(String[] args) {
		String URL = "http://84.239.61.2:9080/secret/sinosoft/desEncode.action";
		for (int i = 0; i < 2000; i++) {
			// 填入各个表单域的值
			NameValuePair[] data = { new NameValuePair("plainText", "发觉到了房间了房间按房间挨了"), new NameValuePair("key", "eService@icbc-axa#201407"), new NameValuePair("iv", "") };
			// 走本次测试
			httpClientForm(URL, data);
		}

	}
	
	public static String sendXml(String url, String xml, String encode) throws IOException {
		String retXml = "";
		try {
			// 建立链接
			URL gatewayUrl = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) gatewayUrl.openConnection();

			// 设置连接属性
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setUseCaches(false);

			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			byte[] datas = xml.getBytes(encode);

			// 设置请求属性
			httpURLConnection.setRequestProperty("Content-length", ""+ datas.length);
			httpURLConnection.setRequestProperty("Content-Type","application/octet-stream");

			// 建立输出流，并写入数据
			OutputStream outputStream = httpURLConnection.getOutputStream();
			outputStream.write(datas);
			outputStream.close();

			// 获得响应状态
			int responseCode = httpURLConnection.getResponseCode();

			if (HttpURLConnection.HTTP_OK == responseCode) {
				// 当正确响应时处理数据
				InputStream in=httpURLConnection.getInputStream();
				retXml = getByInputStream(in, encode);
				logger.info("retXml=====\n"+retXml);
			}
		} catch (IOException e) {
			logger.error("sendXml error", e);
			throw e;
		}

		return retXml;
	}
	
	
	public static NameValuePair[] object2NameValuePair(Object parameters) {
		// 组合请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Method[] ms = parameters.getClass().getMethods();
		for (int i = 0; i < ms.length; i++) {
			Method m = ms[i];
			String name = m.getName();
			if (name.startsWith("get")) {
				String param = name.substring(3, name.length());
				param = param.substring(0, 1).toLowerCase()	+ param.substring(1, param.length());
				if (param.equals("class")) {
					continue;
				}				
				try {
					Object  value = m.invoke(parameters, null);
					NameValuePair nvp = new NameValuePair(param,value.toString());
					list.add(nvp);
				} catch (Exception e) {
					e.printStackTrace();
					throw new NestedBusinessException("未知异常，未定义处理机制！", e);
				}
			}
		}
		NameValuePair[] nvps = new NameValuePair[list.size()];
		return list.toArray(nvps);	
	}
}

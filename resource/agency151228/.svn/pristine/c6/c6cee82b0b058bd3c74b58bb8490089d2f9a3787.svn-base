/**
 * 
 */
package com.douins.agency.service.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class HttpClientThreadUtil {

	private static Logger log = Logger.getLogger(HttpClientThreadUtil.class);
	/**
	 * requestList中Map的格式{url:'请求地址',params:{请求参数},encode:'编码'}
	 * @param requestList
	 * @return
	 */
	public static String[] threadPost(List<Map<String, Object>> requestList) {
		String[] results = new String[requestList.size()];
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		// 设置线程数最大100,如果超过100为请求个数
		connectionManager.setMaxTotal(requestList.size() > 100 ? requestList.size() : 100);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
		try {
			PostThread[] postThreads = new PostThread[requestList.size()];
			for (int i = 0; i < requestList.size(); i++) {
				Map<String, Object> request = requestList.get(i);
				HttpPost post = new HttpPost((String) request.get("url"));
				postThreads[i] = new PostThread(httpClient, post, (Map<String, Object>) request.get("params"), (String) request.get("encode"), i + 1);
			}
			// 执行线程
			for (PostThread postThread : postThreads) {
				postThread.start();
			}
			// 设置所有线程执行完毕之后再执行后续代码
			for (PostThread postThread : postThreads) {
				postThread.join();
			}
			for (int i = 0; i < requestList.size(); i++) {
				results[i] = postThreads[i].call();
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("多线程post方法异常：" + e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	/**
	 * requestList中Map的格式{url:'请求地址',params:{请求参数},encode:'编码'}
	 * @param requestList
	 * @return
	 */
	public static String[] threadGet(List<Map<String, Object>> requestList) {
		String[] results = new String[requestList.size()];
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		// 设置线程数最大100,如果超过100为请求个数
		connectionManager.setMaxTotal(requestList.size() > 100 ? requestList.size() : 100);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
		try {
			GetThread[] getThreads = new GetThread[requestList.size()];
			for (int i = 0; i < requestList.size(); i++) {
				Map<String, Object> request = requestList.get(i);
				HttpGet httpGet = new HttpGet((String) request.get("url"));
				getThreads[i] = new GetThread(httpClient, httpGet, i + 1);
			}
			// 执行线程
			for (GetThread getThread : getThreads) {
				getThread.start();
			}
			// 设置所有线程执行完毕之后再执行后续代码
			for (GetThread getThread : getThreads) {
				getThread.join();
			}
			for (int i = 0; i < requestList.size(); i++) {
				results[i] = getThreads[i].call();
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("多线程get方法异常：" + e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	/**
	 * 实现Callable回调接口
	 */
	static class PostThread extends Thread implements Callable<String> {

		private final CloseableHttpClient httpClient;
		private final HttpContext context;
		private final HttpPost httppost;
		private final int id;
		private String result = null;

		public PostThread(CloseableHttpClient httpClient, HttpPost httppost, Map<String, Object> params, String encode, int id) throws UnsupportedEncodingException {
			// 设置超时时间
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000).setSocketTimeout(30000).build();
			httppost.setConfig(requestConfig);
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					Object value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
			}
			if (pairs != null && pairs.size() > 0) {
				encode = encode == null ? "UTF-8" : encode;
				httppost.setEntity(new UrlEncodedFormEntity(pairs, encode));
			}
			this.httpClient = httpClient;
			this.context = new BasicHttpContext();
			this.httppost = httppost;
			this.id = id;
		}

		@Override
		public void run() {
			try {
				CloseableHttpResponse response = httpClient.execute(httppost, context);
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(entity);
					}
				} finally {
					response.close();
				}
			} catch (Exception e) {
				log.error(id + " - error: " + e);
			}
		}

		@Override
		public String call() throws Exception {
			return result;
		}
	}

	/**
	 * A thread that performs a GET.
	 */
	static class GetThread extends Thread implements Callable<String> {

		private final CloseableHttpClient httpClient;
		private final HttpContext context;
		private final HttpGet httpget;
		private final int id;
		private String result = null;

		public GetThread(CloseableHttpClient httpClient, HttpGet httpget, int id) {
			this.httpClient = httpClient;
			this.context = new BasicHttpContext();
			this.httpget = httpget;
			this.id = id;
		}

		/**
		 * Executes the GetMethod and prints some status information.
		 */
		@Override
		public void run() {
			try {
				System.out.println(id + " - about to get something from " + httpget.getURI());
				CloseableHttpResponse response = httpClient.execute(httpget, context);
				try {
					System.out.println(id + " - get executed");
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(entity);
					}
				} finally {
					response.close();
				}
			} catch (Exception e) {
				System.out.println(id + " - error: " + e);
			}
		}

		@Override
		public String call() throws Exception {
			return result;
		}
	}
}

/**
 * 
 */
package com.douins.agency.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/** 
* @ClassName: BaseController 
* @Description: Controoler 的基类
* @author G. F. 
* @date 2016年1月3日 下午4:20:48 
*  
*/
public abstract class BaseController {
    private Logger logger = Logger.getLogger(BaseController.class);
    
    /**
     * 提取所有的请求参数
     * @param request
     * @return
     */
    protected String getAllRequstParams(HttpServletRequest request){
    	
        Map<String, String[]> params = request.getParameterMap();
      /*  String queryStr ="";
        try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8")); // 实例化输入流，并获取网页代码
				String s; // 依次循环，至到读的值为空
				StringBuilder sb = new StringBuilder();
				while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			reader.close();
			queryStr= sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println(queryStr);
        return queryStr.toString();*/
        System.out.println(String.valueOf(request.getContentLength()));
        System.out.println(request.getContentType());
        System.out.println(request.getContextPath());
        StringBuilder queryStr = new StringBuilder();
        for(String key : params.keySet()){
            String[] values = params.get(key);
            queryStr.append(key);
            queryStr.append("=");
            for(String val : values){
                queryStr.append(val);
                queryStr.append("&");
            }
        }
        queryStr.deleteCharAt(queryStr.length() - 1);
        return queryStr.toString();
    }
    
    /**
     * 获取网传报文的内容
     * @param request
     * @return
     */
    protected String getRequestConent(HttpServletRequest request){
        String content = null;
        try {
            request.setCharacterEncoding("UTF-8");
            InputStream inputStream = request.getInputStream();
            StringBuilder str = new StringBuilder();
            byte [] bytes=new byte[1024];
            int count = 0;
            while((count = inputStream.read(bytes)) != -1){
                String temp = new String(bytes, 0, count);
                str.append(temp);
            }
            content = str.toString();
            logger.info("报文内容＝ \n"+ content);
        } catch (Exception e) {
            logger.error("input data error.", e);
        }
        
        return content;
    }
    
    /**
     * 获取网传报文header
     * @param reqXml
     * @return headerXml
     * @author panrui
     */
    protected String getRequestHeader(String reqXml){
    	Document doc = Jsoup.parse(reqXml, "", Parser.xmlParser());
        Elements header = doc.select("header");
        String headerXml = header.toString().replaceAll("[\\s]", "");
        return headerXml;
    }
}

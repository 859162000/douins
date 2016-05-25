/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.mango.api.basic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.trans.service.RequestTransService;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月22日 下午12:18:23   
 */
public class APICallInterceptor extends HandlerInterceptorAdapter  {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	private NamedThreadLocal<Long>  startTimeThreadLocal =  new NamedThreadLocal<Long>("WatchAPIRunTime-StartTime");
	
	@Autowired
	@Qualifier("requestTransService")
	private RequestTransService requestTransService;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("===preHandle===");
		long beginTime = System.currentTimeMillis();//1、开始时间  
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见） 
		String data = request.getParameter("data");
		String sign = request.getParameter("sign");
		String channel = request.getParameter("channel");
		String ip = this.getIpAddr(request);
		if(requestTransService.checkAndSaveTrans(data, sign, channel, ip)){
			return true;
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long endTime = System.currentTimeMillis();//2、结束时间  
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
        long consumeTime = endTime - beginTime;//3、消耗的时间  
        if(consumeTime > 4000) {//此处认为处理时间超过4秒的请求为慢请求 
        	logger.info(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));  
        }    
	}
	
	 private String getIpAddr(HttpServletRequest request)
	  {
	    String ip = request.getHeader("X-Real-IP");
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("X-Forwarded-For");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getRemoteAddr();
	    }
	    return ip;
	  }
	
}

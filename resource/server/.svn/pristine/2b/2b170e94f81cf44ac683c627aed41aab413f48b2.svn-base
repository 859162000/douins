/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.mango.fortune.basic.aspect;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mango.core.logger.SimpleLogger;
import com.mango.redis.RedisCacheService;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年6月17日 下午2:11:04
 */
@Aspect
// 该注解标示该类为切面类
@Component
// 注入依赖
public class CacheAspect {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	@Autowired
	private RedisCacheService redisCacheService;
	
	
	@Pointcut("@annotation(com.mango.fortune.basic.annotation.CacheAnnotation)")
	public void serviceAspect(){
		
	}

	@Around(value = "serviceAspect()")
	public Object doCacheService(ProceedingJoinPoint pjp) throws Exception {
		logger.info("===begin doCacheService==");
		Object[] parames = pjp.getArgs();// 获取目标方法体参数
		String key = "";
		String expireTime = "100"; 
		if(parames != null && parames.length >= 2){
			key = (String) parames[0];
			expireTime = (String) parames[1];
		}
		logger.info("==cache key is==="+key);
		Object retVal = null;
		HashMap hm = new HashMap();
		if (StringUtils.isBlank(key) || !redisCacheService.exists(key)) {
			logger.info("==cache key is not exists===");
			try {
				retVal = pjp.proceed();
				hm.put(key, retVal);
				if (StringUtils.isNotBlank(expireTime)) {
					redisCacheService.setValue(key, hm, Integer.valueOf(expireTime));	
				}else{
					redisCacheService.setValue(key, hm);
				}
			} catch (Throwable e) {
				logger.error("===error doCacheService",e);
				if (e instanceof Exception) {
					throw (Exception) e;
				}
			}
		}
		//从缓存中获取数据
		HashMap resultMap = redisCacheService.getValue(key);
		
		Object result = null;
		
		if(resultMap != null && !resultMap.isEmpty()){
			result = resultMap.get(key);
		}else{
			result = hm.get(key);
			logger.info("==resultMap is null or is Empty get result from db===");
		}
		
		logger.info("===end doCacheService");
		
		return result;
	}
}

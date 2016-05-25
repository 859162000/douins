/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.douins.common.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douins.common.mapper.JsonMapper;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;

/**
 * Controller基类
 * 
 * @version 2013-3-23
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * jsonMapper对象
	 */
	protected static final JsonMapper MAPPER  = new JsonMapper();
	
	
	protected ResponseCode responseCode = ResponseCode.FAILED;
	
	protected String transId="";

	
	/**
	 * JSON字符串转换为对象
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz){
		return JsonMapper.fromJsonString(jsonString, clazz);
	}
	
	
	/**
	 * 客户端返回JSON字符串
	 * @param <T>
	 * @param response
	 * @param object
	 * @return
	 */
	
	protected <T> String renderString(HttpServletResponse response,ResponseTransVo<T> ressponseVo) {
		
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		ressponseVo.setResponseTrans(responseTrans);
		return renderString(response, JsonMapper.toJsonString(ressponseVo), "application/json");
		
	}
	
	/**
	 * @param response
	 * @param object
	 * @return
	 */
//	protected String renderString(HttpServletResponse response, Object object) {
//		return renderString(response, JsonMapper.toJsonString(object), "application/json");
//	}
	
	protected String renderString(HttpServletResponse response, String respInfo) {
	return renderString(response, respInfo, "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param type返回数据格式类型，string 响应客户端数据 
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		
		System.out.println("响应信息"+string);
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	
}

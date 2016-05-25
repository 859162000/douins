/**
 * 
 */
package com.douins.common;

import java.lang.reflect.Method;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.enums.TokenVerifyEnum;
import com.douins.account.service.iml.UserAuthorityService;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.ResponseTrans;
import com.google.common.collect.Maps;

/** 
* @ClassName: ApiAround 
* @Description: 对 Api 注解的方法做验证处理
* @author G. F. 
* @date 2015年11月18日 下午3:53:52 
*  
*/
@Component
@Aspect
public class ApiAround {
    private static final Logger logger = Logger.getLogger(Api.class);
    
    @Inject
    private UserAuthorityService authorityService;
    
    @ResponseBody
    @Around("@annotation(com.douins.common.Api)")
    public String around(ProceedingJoinPoint point){
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Api api = method.getAnnotation(Api.class);
        
        Object[] args = point.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        ResponseCode responseCode=ResponseCode.SUCCESS;
        String transId = null;
        
        try {
            boolean flag = false;
            if(api.token() == true){
                // 需要身份验证才能访问
                String data = request.getParameter("data");
                logger.info("data = "+ data);
                String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);
                logger.info("token = "+ token);
                transId = JSON.parseObject(data).getJSONObject(Const.REQUEST_TRANS).getString(Const.TRANS_ID);
                logger.info("transId=" + transId);
                
                if(StringUtils.isBlank(token)){
                    logger.info("token 不能为空");
                    //returnVal = TokenVerifyEnum.NotExist.getContent();
                    responseCode = ResponseCode.INVALID_TOKEN;
                }else{
                    // 根据 token 验证用户是否存在
                    TokenVerifyEnum result = authorityService.varifyUserByToken(token);
                    if(!result.equals(TokenVerifyEnum.Normal)){
                        logger.info("token 无效。");
                        //returnVal = result.getContent();    // token 无效
                        responseCode = responseCode.EXPIRED_TOKEN;
                    }else {
                        flag = true;        // token 验证通过
                    }
                }
            }
            
            if(flag){  // token 有效
                Object resultVal = point.proceed(point.getArgs());     // 用修改后的参数执行目标方法，并获取返回结果
                return resultVal.toString();    // 此处直接就是 JSON 串，直接返回
            }
        }catch (Throwable e) {
           logger.error("注解拦截通知业务异常", e);
           responseCode = ResponseCode.FAILED;
        }
   
        // 返回错误信息
        ResponseTrans responseTrans=new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
        Map<String, Object> data = Maps.newHashMap();
        data.put("accessToken", "");
        data.put("responseTrans", responseTrans);
        String response = JsonOnlineUtils.object2json(data);
        logger.info(response);
        
        return response;
    }
}

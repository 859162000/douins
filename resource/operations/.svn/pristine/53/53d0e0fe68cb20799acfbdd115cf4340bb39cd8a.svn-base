/**
 * 
 */
package com.douins.agency.annotation;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.agency.service.douins.service.impl.IpAuthority;

/** 
* @ClassName: IpAuthAround 
* @Description: 验证 IP 访问权限
* @author G. F. 
* @date 2015年12月29日 下午2:24:11 
*  
*/
@Component
@Aspect
public class IpAuthAround {
    private Logger logger =Logger.getLogger(IpAuthAround.class);
    
    @Resource
    private IpAuthority ipAuth;
    
    @ResponseBody
    @Around("@annotation(com.douins.agency.annotation.IpAuth)")
    public String around(ProceedingJoinPoint point){
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        IpAuth auth = method.getAnnotation(IpAuth.class);
        Object[] args = point.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        String result = null;
       
        try {
            String isOk = null;
            if(auth.ipAuth() == true){
                isOk = ipAuth.verifyIpAuth(request);        // 验证 IP
            }else{
                isOk = "true";
            }
            
            if("true".equals(isOk)){
                Object  resultVal = point.proceed(point.getArgs());    // 用修改后的参数执行目标方法，并获取返回结果
                result = resultVal.toString();    // 此处直接就是响应结果，直接返回
            }else{
                result = isOk;
            }
        } catch (Throwable e) {
            logger.error("Ip 地址访问权限解析失败.", e);
        }
        
        return result;
    }
}

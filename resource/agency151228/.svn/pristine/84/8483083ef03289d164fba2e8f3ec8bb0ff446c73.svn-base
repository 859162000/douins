/**
 * 
 */
package com.douins.agency.service.douins.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.douins.agency.service.common.util.TemplateLoader;
import com.douins.agency.service.douins.dao.IpAuthorityDao;
import com.douins.agency.service.douins.domain.enums.DouinsException;
import com.google.common.collect.Maps;

/** 
* @ClassName: IpAuthority 
* @Description: IP 地址访问权限的验证
* @author G. F. 
* @date 2015年12月29日 下午1:52:04 
*  
*/
@Service
public class IpAuthority {
    private static Logger logger = Logger.getLogger(IpAuthority.class);
    
    @Resource
    private IpAuthorityDao ipDao;
    @Resource
    private TemplateLoader tempLoader;
    
    /**
     * 验证 IP 地址是否合法
     * @param request
     * @return
     *          成功则返回 “true”；失败则返回 exception.ftl 结构的异常信息
     */
    public String verifyIpAuth(HttpServletRequest request){
        Map<String, Object> root = Maps.newHashMap();
        root.put("errorCode", DouinsException.IP_ERROR.getValue());
        root.put("errorMsg", DouinsException.IP_ERROR.getName());
        
     // String ip = getIpAddress(request);
        String ip = getClientIpAddress(request);
        logger.info("real Ip = " + ip);
        String result = null;
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            result = tempLoader.processTemplate("/exception/exception.ftl", root);
        }

        // 到数据库查找 Ip，看是否是有效的 IP
        List<String> ips = ipDao.getIpList();
        if(ips != null && ips.size() > 0){
            if(ips.contains(ip)){
                result = "true";
            }else{
                result = tempLoader.processTemplate("/exception/exception.ftl", root);
            }
        }else{
            result = tempLoader.processTemplate("/exception/exception.ftl", root);
        }
        
        return result;
    }
    
    /**
     * 提取用户的真实 IP 地址
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
    	logger.info("------------------------------------x-forwarded-for------------------------------------------------");
        String ip = request.getHeader("x-forwarded-for");
        logger.info(ip+"-------------------------------------------------------");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	logger.info("------------------------------------Proxy-Client-IP------------------------------------------------");
            ip = request.getHeader("Proxy-Client-IP");
            logger.info(ip+"-------------------------------------------------------");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	logger.info("------------------------------------WL-Proxy-Client-IP------------------------------------------------");
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.info(ip+"-------------------------------------------------------");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	logger.info("------------------------------------HTTP_CLIENT_IP------------------------------------------------");
            ip = request.getHeader("HTTP_CLIENT_IP");
            logger.info(ip+"-------------------------------------------------------");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	logger.info("------------------------------------HTTP_X_FORWARDED_FOR------------------------------------------------");
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(ip+"-------------------------------------------------------");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	logger.info("------------------------------------unknown------------------------------------------------");
            ip = request.getRemoteAddr();
            logger.info(ip+"-------------------------------------------------------");
        }
        
        return ip;
    }
    
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            logger.info(header + "=" + ip);
            
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                if(ip.contains(",")){
                    ip = ip.split(",")[0];
                }
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
    
    private static final String[] HEADERS_TO_TRY = { 
            "X-Forwarded-For",
            "x-forwarded-for",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"
    };
}

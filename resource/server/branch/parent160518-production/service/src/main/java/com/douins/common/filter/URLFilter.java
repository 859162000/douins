/**
 * 
 */
package com.douins.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.alibaba.druid.support.logging.Log;
import com.mango.core.logger.SimpleLogger;

/** 
* @ClassName: URLFilter 
* @Description: URL 过滤器 
* @author G. F. 
* @date 2015年11月9日 上午10:19:45 
*  
*/
public class URLFilter implements Filter{
   SimpleLogger logger = SimpleLogger.getLogger(URLFilter.class);
    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest reqs, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest)reqs;
        String url = request.getRequestURI();
        logger.info("当前访问的URL：" + url);
        chain.doFilter(request, resp);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        logger.info("URL Filter Init...");
    }

}

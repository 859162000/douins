/**
 * 
 */
package com.douins.agency.service.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/** 
* @ClassName: URLFilter 
* @Description: URL 过滤器 
* @author G. F. 
* @date 2015年11月9日 上午10:19:45 
*  
*/
public class URLFilter implements Filter{
   Logger logger = Logger.getLogger(URLFilter.class);
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
        // add 编码处理
        HttpServletRequest request = (HttpServletRequest)reqs;
        HttpServletResponse response =(HttpServletResponse)resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String url = request.getRequestURI();
        logger.info("当前访问的URI：" + url);
        if(url.contains(",")){
            logger.error("URI 错误===============");
            String newURI = url.split(",")[0];
            logger.info("新的URI ＝"+newURI);
            String[] uri = newURI.split("/");
            request.getRequestDispatcher(uri[uri.length - 1]).forward(request, resp);
        }else{
            chain.doFilter(request, response);
        }
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

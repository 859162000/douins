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
import javax.servlet.http.HttpServletResponse;

/** 
* @ClassName: EncodingFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月2日 下午1:23:45 
*  
*/
public class EncodingFilter  implements Filter{
    private String encoding = "UTF-8";

    private static final String PARAM_ENCODING = "encoding";

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, res);
    }

    public void init(FilterConfig config) throws ServletException {
        String temp = config.getInitParameter(PARAM_ENCODING);
        if (temp != null) encoding = temp; 
    }
}

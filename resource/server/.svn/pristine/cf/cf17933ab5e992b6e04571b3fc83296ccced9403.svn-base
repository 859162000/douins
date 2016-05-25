package com.mango.framework.security;

import com.mango.core.logger.SimpleLogger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

public final class JcSimpleRedirectInvalidSessionStrategy implements InvalidSessionStrategy {
	SimpleLogger logger = SimpleLogger.getLogger(JcSimpleRedirectInvalidSessionStrategy.class);
	private final String destinationUrl;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private boolean createNewSession = true;

	public JcSimpleRedirectInvalidSessionStrategy(String invalidSessionUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
	}

	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (isAjax(request)) {
			String contentType = "application/json";
			response.setContentType(contentType);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Charset", "UTF-8");
			PrintWriter out = response.getWriter();
			StringBuffer rtn = new StringBuffer("{\"success\":false,\"msg\":\"");
			rtn.append("您的会话已失效,请重新登录!").append("\"}").toString();
			out.print(rtn.toString());
			out.flush();
			out.close();
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(" ajax 请求权限验证失败返回错误信息....");
			}
		} else {
			if (this.createNewSession) {
				request.getSession();
			}
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Starting new session (if required) and redirecting to '" + this.destinationUrl + "'");
			}
			this.redirectStrategy.sendRedirect(request, response, this.destinationUrl);
		}
	}

	private boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}
}

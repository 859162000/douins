package com.mango.framework.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

import com.mango.core.common.util.MD5;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

public class JcSecurityFilter extends AbstractAuthenticationProcessingFilter {
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "account";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
	private String usernameParameter = "userAccount";
	private String passwordParameter = "loginPassword";
	private String openIdParameter = "openId";
	public static final String SECURITY_ISENCRYPT_KEY = "encrypt";
	public static final String SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	private boolean postOnly = true;
	@Autowired
	private ImageCaptchaService captchaService;

	public JcSecurityFilter() {
		super("/dologin");
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		logger.info("******"+this.postOnly+"=======JcSecurityFilter begin======>");
		Authentication auth = null;
		if ((this.postOnly) && (!request.getMethod().equals("POST"))) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		checkValidateCode(request);
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		if(isEncrypt(request))
            password = MD5.getStrMD5(password);
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
		try {
			/*验证管理的主要类是AuthenticationManager,验证的调用入口是authenticate
			 * 返回一个Authentication对象来记录验证的结果，其中包含 了用户的验证信息，权限配置等，同时这个Authentication会以后被授权模块使用*/
			auth = getAuthenticationManager().authenticate(authRequest);
		} catch (AuthenticationException e) {
			logger.error("AuthenticationException",e);
			request.getSession().setAttribute("errorCode", Integer.valueOf(1));
			throw e;
		}
		
		return auth;
	}
	
	protected void checkValidateCode(HttpServletRequest request) {
		String captcha = request.getParameter("captcha");
		try {
			if (!this.captchaService.validateResponseForID(request.getSession().getId(), captcha).booleanValue()) {
				request.getSession().setAttribute("errorCode", Integer.valueOf(2));
				throw new AuthenticationServiceException("valid code error!");
			}
		} catch (CaptchaServiceException e) {
			throw new AuthenticationServiceException("valid code error!");
		}
	}

	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(this.passwordParameter);
	}

	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(this.usernameParameter);
	}

	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
	}

	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getUsernameParameter() {
		return this.usernameParameter;
	}

	public final String getPasswordParameter() {
		return this.passwordParameter;
	}
	
	protected String obtainOpendId(HttpServletRequest request) {
		return request.getParameter(this.openIdParameter);
	}
	
	public void setOpenIdParameter(String openIdParameter) {
		Assert.hasText(openIdParameter, "openId parameter must not be empty or null");
		this.openIdParameter = openIdParameter;
	}
	
	public final String getOpenIdParameter() {
		return this.openIdParameter;
	}
	
	protected boolean isEncrypt(HttpServletRequest request)
    {
        return request.getParameter("encrypt") != null ? Boolean.parseBoolean(request.getParameter("encrypt")) : Boolean.TRUE.booleanValue();
    }

}

package com.mango.framework.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mango.core.AppContext;
import com.mango.core.spring.SpringContextHelper;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

public class JcaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 1041579791246022805L;

	public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";

	private ImageCaptchaService captchaService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		captchaService = (ImageCaptchaService) SpringContextHelper.getBean("captchaService");
		String contextPath = appCtx.getServletContext().getRealPath("/");
		AppContext.setRealPath(contextPath);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			String captchaId = session.getId();
			BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, request.getLocale());
			// Jimi.putImage("image/jpeg", challenge, jpegOutputStream);
			ImageIO.write(challenge, CAPTCHA_IMAGE_FORMAT, jpegOutputStream);
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} catch (CaptchaServiceException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		// flush it in the response
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/" + CAPTCHA_IMAGE_FORMAT);
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}

package com.mango.framework.basic.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mango.fortune.user.model.User;
import com.mango.framework.basic.service.LoginUserService;
import com.mango.framework.controller.BasicController;

@Controller
@Scope("prototype")
public class LoginController extends BasicController {
	@Autowired
	@Qualifier("loginUserService")
	private LoginUserService loginUserService;

	@RequestMapping({ "/login" })
	public String login(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		int ecode = -1;
		String msg = null;
		if (session != null) {
			String errorCode = session.getAttribute("errorCode") == null ? null
					: String.valueOf(session.getAttribute("errorCode"));
			if (StringUtils.isNotBlank(errorCode)) {
				removeSessionAttributes(request, "errorCode");
				ecode = Integer.valueOf(errorCode).intValue();
			}
		}
		switch (ecode) {
		case 0:
			msg = "您输入的用户不存在或已被禁用,请联系管理员!";
			break;
		case 1:
			msg = "密码错误,请重新输入!";
			break;
		case 2:
			msg = "验证码错误,请重新输入!";
			break;
		}

		if (ecode != -1)
			model.addAttribute("errormsg", msg);
		return "login";
	}

	@RequestMapping({ "/index" })
	public String index(Model model, HttpServletRequest request) {
		getSessionData(request);	 	// 从 session 中提取用户信息
		if (StringUtils.isBlank(this.userId))
			return "redirect:/login";
		User user = new User();
		user.setUserId(this.userId);
		user.setLastLoginTime(new Date(System.currentTimeMillis()));
		String ipaddr = getIpAddr(request);
		user.setIpAddress(ipaddr);
		loginUserService.updateLoginInfo(user);
		return "index";
	}

	@RequestMapping({ "/invalidlogin" })
	public String invalidlogin() {
		return "redirect:/login";
	}

	@RequestMapping({ "/sessionexpired" })
	public String sessionexpired(HttpServletRequest request) {
		removeSession(request);
		return "expiredsession";
	}

	@RequestMapping({ "/logexit" })
	public String logexit(HttpServletRequest request) {
		removeSession(request);
		return "redirect:/login";
	}

	@RequestMapping({ "/error/{errorCode}" })
	public String base(@PathVariable String errorCode) {
		try {
			if (Integer.parseInt(errorCode) < 200)
				errorCode = "404";
		} catch (NumberFormatException e) {
			errorCode = "500";
		}
		return "p_error/" + errorCode;
	}
}
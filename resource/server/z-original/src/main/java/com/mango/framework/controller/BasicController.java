package com.mango.framework.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.mango.core.abstractclass.AbstractController;
import com.mango.core.propeditor.CustomTransferSymbolEditor;
import com.mango.core.propeditor.TimestampEditor;
import com.mango.orm.page.Page;

public class BasicController extends AbstractController {
	protected String userId;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(String.class,
				new CustomTransferSymbolEditor());
		binder.registerCustomEditor(Timestamp.class, new TimestampEditor());
	}

	protected void getSessionData(HttpServletRequest request) {
		HttpSession session = request.getSession(false);	// session 不存在时，不创建
		if (session == null)
			return;
		try {
			this.lock.lock();

			this.userId = (session.getAttribute("userId") == null ? null
					: String.valueOf(session.getAttribute("userId")));
			this.userAccount = (session.getAttribute("account") == null ? null
					: String.valueOf(session.getAttribute("account")));
			this.userName = (session.getAttribute("realName") == null ? null
					: String.valueOf(session.getAttribute("realName")));
			this.companyId = (session.getAttribute("companyId") == null ? null
					: String.valueOf(session.getAttribute("companyId")));
		} finally {
			this.lock.unlock();
		}
	}

	protected void removeSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		if (session.isNew())
			return;
		try {
			request.getSession().removeAttribute("userId");
			request.getSession().removeAttribute("account");
			request.getSession().removeAttribute("realName");
			request.getSession().removeAttribute("companyId");
			request.getSession().removeAttribute("errorCode");
		} catch (IllegalStateException e) {
			this.logger.error(
					"remove session attribute IllegalStateException:", e);
		}
	}

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	protected void setPage(HttpServletRequest request, Page<?> page) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("limit");
		try {
			if (StringUtils.isNotBlank(pageNo))
				page.setPageNo(Integer.parseInt(pageNo));
			if (StringUtils.isNotBlank(pageSize))
				page.setPageSize(Integer.parseInt(pageSize));
		} catch (NumberFormatException e) {
			this.logger.error("setPage NumberFormatException:", e);
		}
	}

	protected void formatPageSize(String pageNo, String limit, Page<?> page) {
		try {
			if (StringUtils.isNotBlank(pageNo)) {
				page.setPageNo(Integer.parseInt(pageNo));
			}
			if (StringUtils.isNotBlank(limit))
				page.setPageSize(Integer.parseInt(limit));
		} catch (NumberFormatException e) {
			this.logger.error("page format excption for "
					+ page.getClass().getName());
		}
	}

	protected String getSessionAttributes(HttpServletRequest request,
			String name) {
		HttpSession session = request.getSession(false);
		if (session == null)
			return null;
		return session.getAttribute(name) == null ? null : session
				.getAttribute(name).toString();
	}

	protected void removeSessionAttributes(HttpServletRequest request,
			String name) {
		HttpSession session = request.getSession(false);
		if (session == null)
			return;
		session.removeAttribute(name);
	}
}
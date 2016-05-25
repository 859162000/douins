package com.douins.agency.service.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;

public class QuartzContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("容器关闭了，手动关闭Quartz调度！");
		// web容器关闭的时候把调度也关闭掉
		WebApplicationContext webApplicationContext = (WebApplicationContext) contextEvent.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		org.quartz.impl.StdScheduler startQuertz = (org.quartz.impl.StdScheduler) webApplicationContext.getBean("startQuertz");
		if (startQuertz != null) {
			startQuertz.shutdown();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
	}

}

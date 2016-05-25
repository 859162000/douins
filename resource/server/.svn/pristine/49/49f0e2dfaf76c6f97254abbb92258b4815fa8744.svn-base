/*
 * Initialization.java
 * Copyright (c) 2013 by kinjo
 * All rights reserved.
 * --------------------------
 */
package com.mango.framework.servlet;

import org.springframework.context.SmartLifecycle;

import com.mango.core.common.service.LabelManager;
import com.mango.core.logger.SimpleLogger;

/**
 * 系统初始化类
 * 
 * @author kinjoYang
 * @version 1.0 2013-11-24
 * @since 1.0
 * @see org.springframework.context.SmartLifecycle
 */
public class Initialization implements SmartLifecycle {

	private volatile boolean isRun = Boolean.FALSE;

	private int phase = Integer.MAX_VALUE - 1;

	SimpleLogger logger = SimpleLogger.getLogger(Initialization.class);

	public void start() {
		if (!isRun) {
			initPara();
			isRun = !isRun;
		}
	}

	public void stop() {
		if (isRun) {
			isRun = !isRun;
		}
	}

	public boolean isRunning() {
		return isRun;
	}

	public int getPhase() {
		return phase;
	}

	public boolean isAutoStartup() {
		return Boolean.TRUE;
	}

	public void stop(Runnable callback) {
		// do nothing
	}

	// 系统信息初始化
	void initPara() {
		long begin = System.currentTimeMillis();
		logger.info("p2p server initialized begin......");
		LabelManager.init();
		long end = System.currentTimeMillis();
		logger.info("p2p server initialized processed in" + (begin - end) + " ms.....");
	}
}

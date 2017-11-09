package com.hanqian.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartAppListener extends ContextLoaderListener
		implements
			ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {

		try {
			super.contextInitialized(event);
			ServletContext scontext = event.getServletContext();
			WebApplicationContext wac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(scontext);

			// 加载系统配置
			SysCfg.load(wac);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}
}

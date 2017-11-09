package com.hanqian.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

public class CommonLog   {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(CommonLog.class);
	public void before(JoinPoint joinpoint) {

		joinpoint.getArgs();// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象

//		System.out.println("被拦截方法调用之前调用此方法，输出此语句");

	}

	public void after(JoinPoint joinpoint) {
		try {
			//BusinessModuleLogDAO dao = (BusinessModuleLogDAO)SysCfg.getBean("");
		} catch (Exception e) {
			logg.error("CommonLog-->after", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		joinpoint.getArgs();// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象

//		System.out.println("被拦截方法调用之前调用此方法，输出此语句");
	}

}

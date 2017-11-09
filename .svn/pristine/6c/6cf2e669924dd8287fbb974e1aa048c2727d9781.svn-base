package com.hanqian.util;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;

public class SysCfg {

	private static ApplicationContext wac;
	public static void load(ApplicationContext ctx) {
		try {
			SysCfg.wac = ctx;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Object getBean(String name) throws Exception {
		return wac.getBean(name);
	}
}

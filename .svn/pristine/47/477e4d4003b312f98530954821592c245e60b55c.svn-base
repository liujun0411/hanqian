package com.hanqian.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class RequestIntrospectHelper {

	protected static Log log = LogFactory.getLog(RequestIntrospectHelper.class);

	public static void introspect(Object form, ServletRequest request) {
		Map map = request.getParameterMap();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String name = (String) entry.getKey();
			try {
				Class clazz = PropertyUtils.getPropertyType(form, name);
				if (clazz == null)
					continue;

				String value[] = (String[]) entry.getValue();
				if (value == null)
					continue;

				if (clazz.isArray()) {
					BeanUtils.setProperty(form, name, value);
				} else {
					if (StringUtils.isBlank(value[0]))
						continue;
					
					BeanUtils.setProperty(form, name, value[0]);
				}
			} catch (Throwable e) {
				log.warn("Cannot Set bean.property.", e);
			}
		}
	}
}
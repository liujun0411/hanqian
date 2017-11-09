/**
 * 
 */
package com.hanqian.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本类是用来缓存已经执行过的sql语句,
 * key是执行sql的包名,类名,<strong>xml文件中定义的sqlid</strong>的字符串,例如:com.hanqian.logistics.business.EquipListBusiness.findEquipMaintenance<p>
 * value就是执行的sql语句.
 * 这里的sql语句是mybatis内的包含mybatis的语句的sql,
 * 
 * 注意本类不是线程安全类,也就意味着在真正的多线程情况下,本类会返回错误数据.
 * @author Eden
 *
 */
public class PerformSQLUtil {

	private static ConcurrentHashMap<String, Map> sql = new ConcurrentHashMap<String, Map>();
	
	/**
	 * 接收完整的关键字,得到sql语句以及sql内替换变量的map.
	 * map内sql语句的key是resultSql
	 * 
	 * @param fullkey 包名+类名+sqlId
	 * @return
	 */
	public static Map get(String fullkey) {
		return sql.get(fullkey);
	}
	
	/**
	 * 传入sqlId和执行类对象,本方法会拼接出完整关键字.
	 * map内sql语句的key是resultSql
	 * 
	 * @param sqlId
	 * @param c
	 * @return
	 */
	public static Map get(String sqlId, Object c) {
		if (c == null) return null;
		return get(c.getClass().getName() + "." + sqlId);
	}
	
	public static void put(String key, Map value) {
		sql.put(key, value);
	}
}

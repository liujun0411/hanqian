package com.hanqian.quartz.jobs;

import mondrian.olap.CacheControl;
import mondrian.olap.Connection;
import mondrian.olap.Cube;
import mondrian.olap.DriverManager;
import mondrian.olap.Util;

import org.apache.log4j.Logger;

import com.hanqian.constant.ConfigCST;

/**
 * 定时清理当前web项目下的mondrian缓存
 * 因为mondrian自身无法判断缓存与数据库是否同步,
 * mondrian对外仅仅提供api可以清理缓存,所以此操作由本类执行.
 * 数据仓库中每天进行一次etl操作,所以缓存清理的操作也仅仅需要一天一次就可以.
 * 现在定位每天5点,配合医院上班前,进行缓存清理工作.
 * 
 * @author Eden.Cui
 * @version 1.4 2014年2月26日
 * @see
 */
public class LocalMondrianClearCache {

	private static final Logger logg = Logger.getLogger(LocalMondrianClearCache.class);
			
	public void run () {
		logg.debug("开始进行mondrian缓存清理.");
		
//		try {
//			
//			//连接mondrian的属性类
//			final Util.PropertyList connectProperties = new Util.PropertyList();
//			connectProperties.put("Provider", "mondrian");	//定义连接协议
//			connectProperties.put("Jdbc", ConfigCST.CST_JDBC_URL);	//jdbc连接
//			connectProperties.put("JdbcUser", ConfigCST.CST_JDBC_USER);	//jdbc连接用户名
//			connectProperties.put("JdbcPassword", ConfigCST.CST_JDBC_PASSWORD);	//jdbc连接密码
//			connectProperties.put("JdbcDrivers", ConfigCST.CST_JDBC_DRIVER);	//jdbc连接驱动
//			connectProperties.put("catalog", ConfigCST.CST_CATALOG);	//分析xml所在路径
//			
//			log.debug("清理mondrian缓存时,构建的mondrian连接URL:" + connectProperties.toString());
//			final Connection connection = DriverManager.getConnection(connectProperties, null, null);
//			
//			CacheControl cacheControl = connection.getCacheControl(null);
//			
//			log.debug("mondrian连接的cube有:" + connection.getSchema().getCubes().length);
//			
//			//刷新全部的缓存.
//	        for (Cube cube : connection.getSchema().getCubes()) {
//	        	
//	        	log.debug("即将清理缓存的cube是-" + cube.getName());
//	        	CacheControl.CellRegion measuresRegion = cacheControl.createMeasuresRegion(cube);
//	            cacheControl.flush(measuresRegion);
//	            log.debug("已经清理结束缓存的cube是-" + cube.getName());
//	        }
//	        
//	        log.info("mondrian缓存已经清理完毕.");
//		} catch (Exception e) {
//			log.error("清理mondrian缓存时出现异常.", e);
//		}
		
        
	}
}

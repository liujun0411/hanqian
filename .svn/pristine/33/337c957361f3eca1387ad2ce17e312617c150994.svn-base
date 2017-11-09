package com.hanqian.dataInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.hanqian.business.DbPointBussiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

/**
 * 描 述: 本类从DB_POINT一次性导入所有的点位数据.
 *      按照刷新频率定时的向SDCD系统取得数据,
 *      然后写入controll_data.
 *      
 *      如果DB_POINT表内数据出现变动,本类将不会及时刷新数据.
 *      只能重新启动服务.
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author Eden.Cui
 * @version 1.4 2013年11月6日
 * @see
 */
public class DataCollectFromSdcd {

	private static final Logger log = Logger.getLogger(DataCollectFromSdcd.class);
	public static volatile boolean run = true;
	
	private DbPointBussiness dbPointBussiness;
	private Map codeMap = new HashMap();
//	private ArrayBlockingQueue<String> connectQueue;
	private String[] ips;
	
	/**
	 * 初始化方法.
	 * 从DB_POINT表内取得全部的点位数据.
	 * @param 2013年11月6日  
	 * @param        
	 * @return void
	 */
	public void init() {
		
		log.info("初始化向SDCD取数据的程序.");
		if (!"on".equalsIgnoreCase(ConfigCST.IS_SDCD_OPEN)) {
			log.info("本系统不需要从SDCD取得点位数据.请参考config.properties");
			return;
		}
		
		//查询出所有的点位
		RetCode code = dbPointBussiness.getAllPoint();
		
		if (code.getCode() != 0) {
			log.info("业务库内没有点位需要向SDCD取数据. 向sdcd取数据程序终止.");
			return;
		}
		
		List<Map> result = (List<Map>)code.getObj();
		log.info("取得点位数量:" + result.size());
		
		for (Map map:result) {
			
			//reflash单位是秒
			long reflash = Math.abs(StringUtil.getIntValue(map.get("reflash")));   //对结果取绝对值,如果reflash为空时,就按照1秒作为频率
			
			//按照频率对点位进行分类
			if (codeMap.containsKey(reflash)) {
				List list = (List)codeMap.get(reflash);
				list.add(map.get("project_point"));
			} else {
				List list = new ArrayList();
				list.add(map.get("project_point"));
				codeMap.put(reflash, list);
			}
		}
		
		//将map内的点位list转变为json字符串,已使得后续程序就无需每次进行json转换
		Set e1 = codeMap.keySet();
		for (Object key:e1) {
			List pointData = (List)codeMap.get(key);
			log.info("向sdcd取数据, 频率(秒):" + key + ", 点位数:" + pointData==null?"0":pointData.size());
			codeMap.put(key, JSONArray.fromObject(pointData).toString());
		}
		
		//创建连接到sdcd的多个ip的队列,达到轮番查询sdcd多个节点来取数据,而不是只对一个节点取数据
		String ipsStr = ConfigCST.SDCD_IPS;
		
		if (StringUtil.isEmpty(ipsStr)) {
			log.error("未提供SDCD连接IP地址,请参考config.properties.");
			return;
		}
		
		ips = StringUtil.str2array(ipsStr, ",");
		if (ips == null || ips.length == 0) {
			log.error("未提供SDCD连接IP地址,请参考config.properties.");
			return;
		}
		
//		if (connectQueue == null) connectQueue = new ArrayBlockingQueue<String>(ips.length, true);
//		for (int i = 0; i < ips.length; i++) {
//			connectQueue.offer(ips[i]);
//		}
		
		final List<ScheduledFuture> futureList = new ArrayList<ScheduledFuture>();
		
		//注册当java虚拟机关闭时的处理.主要目的是结束本线程.
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				DataCollectFromSdcd.run = false;
				
				for (ScheduledFuture<?> future:futureList) {
					future.cancel(true);
				}
			}
		});
		
		//创建定时器线程池,定时进行sdcd取数据
		 
		Set e2 = codeMap.keySet();
		for (Object key:e2) {
			
			//根据有多少种不同频率定义线程池个数
			ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
			
			final String pointData = (String)codeMap.get(key);
			
			log.info("创建向sdcd取数据线程,本线程取数据频率(秒):" + key);
			
			futureList.add(scheduled.scheduleWithFixedDelay(new Runnable() {
				
				private SdcdHttpsCollectData sdcdHttpsCollectData;
				@Override
				public void run() {
					try {
						
						if (DataCollectFromSdcd.run) {
							
							if (sdcdHttpsCollectData == null) {
								log.info("向SDCD取数据,创建https连接类......");
								sdcdHttpsCollectData = new SdcdHttpsCollectData();
								log.info("向SDCD取数据,创建https连接类结束.");
							}
							
							if (log.isDebugEnabled()) log.debug("sdcd取数据定时器开始运行."); 
							
							//采用轮训的形式发送指令
//							final String ip = connectQueue.poll();
//							if (log.isDebugEnabled()) log.debug("sdcd取数据ip:" + ip);
							
							sdcdHttpsCollectData.getData(pointData);
//							connectQueue.offer(ip);
							
							if (log.isDebugEnabled()) log.debug("sdcd取数据定时器运行结束.");
						}
					} catch (Exception e) {
						log.error("向sdcd取数据时出现异常.", e);
						
						//如果系统已经结束,则直接退出.
						if (!DataCollectFromSdcd.run) return;
					}
				}
			}, 10, StringUtil.getIntValue(key), TimeUnit.SECONDS));
		}
		
		log.info("向sdcd取数据线程池创建成功.");
		
		//监视sdcd取数据线程运行状态,每隔5秒汇报一次
		if (DataCollectFromSdcd.run) {
			for (ScheduledFuture<?> future:futureList) {
				log.info("向sdcd取数据线程运行状态  距离下次运行剩余秒数:" + future.getDelay(TimeUnit.SECONDS) + ",停止:" + future.isCancelled() + ",运行:" + future.isDone());
			}
			
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//			}
		}
	}
	
	/**
	 * @return the dbPointBussiness
	 */
	public DbPointBussiness getDbPointBussiness() {
		return dbPointBussiness;
	}

	/**
	 * @param dbPointBussiness the dbPointBussiness to set
	 */
	public void setDbPointBussiness(DbPointBussiness dbPointBussiness) {
		this.dbPointBussiness = dbPointBussiness;
	}
}

package com.hanqian.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hanqian.business.LogBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.AlarmLog;
import com.hanqian.pojo.BusinessModule;
import com.hanqian.pojo.BusinessModuleLog;
import com.hanqian.pojo.UserPermissionLog;
import com.hanqian.util.StringUtil;

/**
 * 
 * @author lg
 * 
 */	

public class LogFilter implements Filter {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(LogFilter.class);
	private LogBusiness logBusiness = new LogBusiness();
	
	private BusinessModule businessmodule = new BusinessModule();
	private static Map<String, String> permissionFilterMap;
	private static Map<String, String> operDesMap;
	public void destroy() {

	}
	/**
	 * 模块操作记录日志
	 * TEST 
	 *  
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)  {
		String tableName = "";
		String tableIdentify = "";
		String moduleDes = "";
		String operator = "";
		String oper = "";
		//模块配置信息初始化
		buildLogFilterMap();
		//获取模块参数
		if (request.getParameter("tableName")!=null) {
			tableName = request.getParameter("tableName").toString();
		}
		if (request.getParameter("tableIdentify")!=null) {
			tableIdentify = request.getParameter("tableIdentify").toString();
		}
		if (request.getParameter("moduleDes")!=null) {
			moduleDes = request.getParameter("moduleDes").toString();
		}
		if (request.getParameter("operator")!=null) {//操作员
			operator = request.getParameter("operator").toString();
		}
		if (request.getParameter("oper")!=null) {
			oper = request.getParameter("oper").toString();
		}
		if (StringUtil.isNotEmpty(tableName)&&StringUtil.isNotEmpty(operator)&&StringUtil.isNotEmpty(oper)) {
			moduleDes = operDesMap.get(oper);
			tableName =	tableName.trim().toUpperCase();
			for (String logKey :permissionFilterMap.keySet()) {
				if (StringUtil.isNotEmpty(permissionFilterMap.get(logKey))) {
					String [] values = permissionFilterMap.get(logKey).split(",");
					for (int i = 0; i < values.length; i++) {
						if (tableName.equals(values[0])) {
							try {
								//查询出该表为哪一模块
								businessmodule = logBusiness.findModuleByShort(logKey);
								if (businessmodule!=null) {
									if(logKey.equals("alarm")){//若为告警信息操作
										AlarmLog alarmLog = new AlarmLog();
										alarmLog.setBusinessmodule(businessmodule);
										alarmLog.setTableidentify(tableIdentify);
										alarmLog.setModuletable(tableName);
										alarmLog.setModuledes(moduleDes);
										alarmLog.setOperator(Integer.parseInt(operator));
										alarmLog.setOpertime(new Date());
										alarmLog.setOper(oper);
										logBusiness.insertAlarmModuleOperLog(alarmLog);
									}else if(logKey.equals("permission")){//若为用户权限
										UserPermissionLog userPermissionLog = new UserPermissionLog();
										userPermissionLog.setBusinessmodule(businessmodule);
										userPermissionLog.setTableidentify(tableIdentify);
										userPermissionLog.setModuletable(tableName);
										userPermissionLog.setModuledes(moduleDes);
										userPermissionLog.setOperator(Integer.parseInt(operator));
										userPermissionLog.setOpertime(new Date());
										userPermissionLog.setOper(oper);
										logBusiness.insertPermissionModuleOperLog(userPermissionLog);
									}else{
										BusinessModuleLog businessmodulelog = new BusinessModuleLog();
										businessmodulelog.setBusinessmodule(businessmodule);
										businessmodulelog.setTableidentify(tableIdentify);
										businessmodulelog.setModuletable(tableName);
										businessmodulelog.setModuledes(moduleDes);
										businessmodulelog.setOperator(Integer.parseInt(operator));
										businessmodulelog.setOpertime(new Date());
										businessmodulelog.setOper(oper);
										logBusiness.insertModuleOperLog(businessmodulelog);
									}
								}
							} catch (Exception e) {
								logg.error("LogFilter-->doFilter", e);
							}
						}
					}
				}
			}
		}
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			logg.error("LogFilter-->doFilter", e);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
	
	/**
	 * 需要获取模块信息的接口
	 * @return
	 */
	private void buildLogFilterMap() {
		try {
			if(permissionFilterMap == null || permissionFilterMap.isEmpty()) {
				permissionFilterMap = new HashMap<String, String>();
//				String hospInfo = ConfigUtil.getConfig("/config.properties","hospInfo");
//				String baseInfo = ConfigUtil.getConfig("/config.properties","baseInfo");
//				String monitor = ConfigUtil.getConfig("/config.properties","monitor");
//				String programa = ConfigUtil.getConfig("/config.properties","programa");
//				String dataReport = ConfigUtil.getConfig("/config.properties","dataReport");
//				String building = ConfigUtil.getConfig("/config.properties","building");
//				String traitArea = ConfigUtil.getConfig("/config.properties","traitArea");
//				String permission = ConfigUtil.getConfig("/config.properties","permission");
//				String alarm = ConfigUtil.getConfig("/config.properties","alarm");
//				String sysManager = ConfigUtil.getConfig("/config.properties","sysManager");
//				String deviceInfo =  ConfigUtil.getConfig("/config.properties","deviceInfo");
//				String assets =  ConfigUtil.getConfig("/config.properties","assets");
				
//				permissionFilterMap.put("hospInfo", hospInfo);
//				permissionFilterMap.put("baseInfo", baseInfo);
//				permissionFilterMap.put("monitor", monitor);
//				permissionFilterMap.put("programa", programa);
//				permissionFilterMap.put("dataReport", dataReport);
//				permissionFilterMap.put("building", building);
//				permissionFilterMap.put("traitArea", traitArea);
//				permissionFilterMap.put("permission", permission);
//				permissionFilterMap.put("alarm", alarm);
//				permissionFilterMap.put("sysManager", sysManager);
//				permissionFilterMap.put("deviceInfo", deviceInfo);
//				permissionFilterMap.put("assets", assets);
				permissionFilterMap.put("hospInfo", ConfigCST.CST_HOSP_INFO);
				permissionFilterMap.put("baseInfo",  ConfigCST.CST_BASE_INFO);
				permissionFilterMap.put("monitor", ConfigCST.CST_MONITOR);
				permissionFilterMap.put("programa", ConfigCST.CST_PROGRAMA);
				permissionFilterMap.put("dataReport", ConfigCST.CST_DATA_REPORT);
				permissionFilterMap.put("building", ConfigCST.CST_BUILDING);
				permissionFilterMap.put("traitArea", ConfigCST.CST_TRAITAREA);
				permissionFilterMap.put("permission", ConfigCST.CST_PERMISSION);
				permissionFilterMap.put("alarm", ConfigCST.CST_ALARM);
				permissionFilterMap.put("sysManager", ConfigCST.CST_SYSMANAGER);
				permissionFilterMap.put("deviceInfo", ConfigCST.CST_DEVICE_INFO);
				permissionFilterMap.put("assets", ConfigCST.CST_ASSETS);
			}
			if(operDesMap == null || operDesMap.isEmpty()){
				operDesMap = new HashMap<String, String>();
				//操作描述
//				String insertDes = ConfigUtil.getConfig("/config.properties", "insertDes");
//				String deleteDes = ConfigUtil.getConfig("/config.properties", "deleteDes");
//				String updateDes = ConfigUtil.getConfig("/config.properties", "updateDes");
//				operDesMap.put(EOperType.INSERT.toString(), insertDes);
//				operDesMap.put(EOperType.DELETE.toString(), deleteDes);
//				operDesMap.put(EOperType.UPDATE.toString(), updateDes);
				operDesMap.put(EOperType.INSERT.toString(), ConfigCST.CST_INSERT_DES);
				operDesMap.put(EOperType.DELETE.toString(), ConfigCST.CST_DELETE_DES);
				operDesMap.put(EOperType.UPDATE.toString(), ConfigCST.CST_UPDATE_DES);
			}
		}catch(Exception e) {
			logg.error("LogFilter-->buildLogFilterMap", e);
			e.printStackTrace();
		}
	}
	public LogBusiness getLogBusiness() {
		return logBusiness;
	}
	public void setLogBusiness(LogBusiness logBusiness) {
		this.logBusiness = logBusiness;
	}
	public BusinessModule getBusinessmodule() {
		return businessmodule;
	}
	public void setBusinessmodule(BusinessModule businessmodule) {
		this.businessmodule = businessmodule;
	}
}

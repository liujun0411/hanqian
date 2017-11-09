package com.hanqian.constant;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.hanqian.util.StringUtil;


/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author lg
 * @version 1.4 2013-8-6
 * @see
 */
public class ConfigCST {
	private static final Logger log = Logger.getLogger(ConfigCST.class);
	private static final ResourceBundle resb;
	static {
		resb = ResourceBundle.getBundle("config", Locale.getDefault());
	}
	//
	public static final String CST_HOSP_INFO = getField("hospInfo");
	public static final String CST_BASE_INFO = getField("baseInfo");
	public static final String CST_MONITOR = getField("monitor");
	public static final String CST_PROGRAMA = getField("programa");
	public static final String CST_DATA_REPORT = getField("dataReport");
	public static final String CST_BUILDING = getField("building");
	public static final String CST_TRAITAREA = getField("traitArea");
	public static final String CST_PERMISSION = getField("permission");
	public static final String CST_ALARM = getField("alarm");
	public static final String CST_SYSMANAGER = getField("sysManager");
	public static final String CST_DEVICE_INFO = getField("deviceInfo");
	public static final String CST_ASSETS = getField("assets");
	// 操作描述
	public static final String CST_INSERT_DES = getField("insertDes");
	public static final String CST_DELETE_DES = getField("deleteDes");
	public static final String CST_UPDATE_DES = getField("updateDes");
	//实况天气 
	public static final String CST_SKWEATHER_URL = getField("skWeatherUrl");
	//城市天气
	public static final String CST_CITYWEATHER_URL = getField("cityWeatherUrl");
	//获取城市id
	public static final String CST_CITYNAME_URL = getField("cityNameUrl");
	
	//天气预报是否显示
	public static final String CST_WEATHER_SHOWFLAG = getField("weatherShowFlag");
	
//	// 数据分析库地址,用户名和密码
//	public static final String CST_JDBC_URL = getField("jdbcUrl");
//
//	public static final String CST_JDBC_DRIVER = getField("jdbcDriver");
//
//	public static final String CST_JDBC_USER = getField("jdbcUser");
//
//	public static final String CST_JDBC_PASSWORD = getField("jdbcPassword");
//	
//	public static final String CST_CATALOG = getField("catalog");

	// 设备图纸存取位置
	public static final String CST_GROUP_PIC_LOCATION = getField("groupPicLocation");

	// 设备图纸存取位置
	public static final String CST_EQUIP_PIC_LOCATION = getField("equipPicLocation");

	// 楼宇图纸存取位置
	public static final String CST_BUILDING_PIC_LOCATION = getField("buildingPicLocation");

	// 标识当前医院
	public static final String CST_CURRENT_HOSP_CODE = getField("currentHospCode");

	// 标识数据分析模块
	public static final String CST_olapTab_Status = getField("olapTabStatus");

	// 标识是否显示loading文字和花瓣图片的标记
	public static final String CST_LOADING_FLAG = getField("loadingFlag");

	// Flash运行状态新标识
	public static final String CST_FLASH_NEWFLAG = getField("flashNewFlag");

	// 特殊设备标识speciaEqFlash 的值y代表flash展现 其他值代表html5展现
	public static final String CST_SPECIAEQ_FLASH = getField("speciaEqFlash");

	// 新版本flash和旧版本flash之间的切换展现,old代表老版本
	public static final String CST_FLASH_VERSION = getField("flashVersion");

	// 合同到期的弹性时间(填写正整数)
	public static final String CST_MEUU_OFFSET = getField("menuOffset");

	// 功能提示是否启用 on代表启用
	public static final String CST_MEUU_TIP = getField("menuTip");
	
	//平台静态历史数据默认显示DB_BUILDING_LOG的数据
	public static final String CST_TABLE_NAME = getField("tableName");
	
	//是否通过SDCD系统取得点位数据,on,off
	public static final String IS_SDCD_OPEN = getField("isSDCDOpen");
	
	//SDCD集群系统所在IP地址,可以包含多个ip地址,使用逗号分割
	public static final String SDCD_IPS = getField("sdcd_ips");
	
	//发送邮件的参数信息
	//发件人
	public static final String MAIL_FROMADDRESS = getField("from_address");
	//代理地址
	public static final String MAIL_SMTPHOST = getField("smtp_host");
	//端口
	public static final String MAIL_PORT = getField("port");
	//验证
	public static final boolean MAIL_VALIDATE = Boolean.valueOf(getField("validate"));
	//用户名
	public static final String MAIL_USERNAME = StringUtil.getBase64UnzipString(getField("user_name"));
	//密码
	public static final String MAIL_PASSWORD = StringUtil.getBase64UnzipString(getField("password"));
	//收件人
	public static final String MAIL_TO = getField("to");
	//标题
	public static final String MAIL_TITLE = getField("title");
	//正文内容
	public static final String MAIL_CONTENT = getField("content");
	
	/**
	 * 从config.properties内根据field读取对应的值,
	 * 如果这个field不存在,则返回null.
	 *   
	 * @param field
	 * @return field对应的返回值
	 */
	private static String getField(String field) {
		if (resb.containsKey(field)) {
			try {
				return resb.getString(field);
			} catch (Exception e) {
				log.error("config.properties文件内读取key=" + field + "的字段时出现异常.", e);
				return null;
			}
		} else {
			log.error("config.properties文件内不包含key=" + field + "的字段.");
			return null;
		}
	}
}

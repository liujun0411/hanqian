package com.hanqian.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;


public class SysUtil {
	private static HashMap<String,String> fm =null;		//精度
	
	static{
		fm  = new HashMap<String,String>();
		fm.put("1", "#0");	//空调系统
		fm.put("2", "#0");	//锅炉系统
		fm.put("3", "#0");	//照明系统
		fm.put("4", "#0");	//电梯系统
		fm.put("5", "#0.0");//生活水系统
		fm.put("6", "#0");	//集水井系统
		fm.put("7", "#0");	//医用气系统
		fm.put("8", "#0.00");	//空压系统
		fm.put("9", "#0.0");	//配电系统
		fm.put("10", "#0.0");	//能源计量
		fm.put("11", "#0.0");	//负压吸引
		
	}
	
	
	/**
	 * 获得数据精确度
	 * @param eqtype	设备类型 
	 * @return
	 */
	public static String getFmStr(String eqtype){		
		return fm.get(eqtype);
	}
	
	public static Map<String,String> getZhuanjiaMap(){
		Map<String, String> desValueMap = new HashMap<String, String>();
		//存储规则，故障状态在前，运行状态在后
		//空调系统
		desValueMap.put("1001","03,04");  //新风机
		desValueMap.put("1002","03,04");  //空调机
		
		//水冷机组
		desValueMap.put("1004001","04,03");   //冷却塔
		desValueMap.put("1004002","02,01");   //冷却泵
		desValueMap.put("1004003","02,01");   //冷冻泵
		desValueMap.put("1004004","02,01");   //冷冻机
		desValueMap.put("1004007","02,01");   //热水泵
		desValueMap.put("5003","01,02");      //热水泵

		//风冷机组
		desValueMap.put("1005001","03,02");   //风冷热泵
		desValueMap.put("1005004","02,01");   //循环泵
		
		//锅炉系统
		desValueMap.put("2001","11,10");   //锅炉本体
		desValueMap.put("2003","01,02");   //给水泵
		
		//集水井系统
		desValueMap.put("6002","02,01");   //排水泵
		
		//空压系统
		desValueMap.put("8001","02,01");   //空压机
		
		//负压吸引
		desValueMap.put("11002","04,03");   //吸引泵
		
		//氧气罐
		desValueMap.put("7001","01,02");   //氧气罐
		
		desValueMap.put("5004","01,02");  //冷水泵
		
		return desValueMap;
	}
	/**
	 * 去掉数字后缀.0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s){  
	     if(s.indexOf(".") > 0){  
	         s = s.replaceAll("0+?$", "");//去掉多余的0  
	         s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
	     }  
	     return s;  
	 }
	public static Map<String,String> getDesValue(){
		Map<String, String> desValueMap = new HashMap<String, String>();
		desValueMap.put("水位极低","0|正常|1|极低");
		desValueMap.put("水位极高","0|正常|1|极高");
		desValueMap.put("水位低","0|正常|1|低");
		desValueMap.put("水位正常","0|正常|1|正常");
		desValueMap.put("水位高","0|正常|1|高");
		//desValueMap.put("锅内压力","0|正常|1|超高");		
		desValueMap.put("故障状态","0|正常|1|故障");
		desValueMap.put("运行状态","0|停止|1|运行");
		desValueMap.put("水流开关","0|关|1|开");
		desValueMap.put("操作方式","0|手动|1|自动");
		desValueMap.put("滤网压差","0|正常|1|压差大");
		desValueMap.put("开关控制","0|关|1|开");
		desValueMap.put("溢水状态","0|正常|1|溢水");
		desValueMap.put("压力超低报警", "0|正常|1|欠压");
		desValueMap.put("压力超高报警", "0|正常|1|超压");
		desValueMap.put("开关状态", "0|关|1|开");
		desValueMap.put("水位低报警", "0|正常|1|低");
		desValueMap.put("水位高报警", "0|正常|1|高");
		desValueMap.put("故障报警", "0|正常|1|故障");
		desValueMap.put("运行模式","0|停止|1|上|2|下");
		desValueMap.put("锅炉启动状态","0|停止|1|启动");
		desValueMap.put("锅炉排烟温度超高停炉","0|正常|1|超高");
		desValueMap.put("液位低报警","0|正常|1|液位低");
		return desValueMap;
	}
	
	/**
	 * 监测点的前置条件(多点合成一个点)
	 * @param 2013-4-7  
	 * @param @return       
	 * @return Map<String,String>
	 */
	public static Map<String,String> getCondition(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("水位", "01,02,03,05,04");
		return conditionMap;
	}
	/**
	 * 虚拟点合成
	 * @param 2013-4-18  
	 * @param @return       
	 * @return Map<String,String>
	 */
	public static Map<String,String> getConditByEqTypeId(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		Object currentHospCode = ServletActionContext.getRequest().getSession().getAttribute("currentHospCode");
		if(!SysUtil.isNullObject(currentHospCode)){
			currentHospCode=currentHospCode.toString();
			if(currentHospCode.equals("RJ")){
				conditionMap.put("2002", "02");
				conditionMap.put("2004", "02");
			}
		}
		return conditionMap;
	}
	
	public static Map<String ,String > getCludStyle(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		//获取医院简编
		Object currentHospCode = ServletActionContext.getRequest().getSession().getAttribute("currentHospCode");
		if(!SysUtil.isNullObject(currentHospCode)){
			currentHospCode=currentHospCode.toString();
			if(currentHospCode.equals("RJ")){
				conditionMap.put("1002", "13");
				conditionMap.put("2002", "02");  //锅炉软水箱(累计流量)
				conditionMap.put("2004", "02,03");//分气缸(累计流量,压差）
			}
		}
		//conditionMap.put("2004", "03");  )
		conditionMap.put("1004004", "08,13");
		return conditionMap;
	}
	
	/**
	 * 根据备注名称显示展示名称
	 * @param 2013-4-8  
	 * @param @return       
	 * @return Map<String,String>
	 */
	public static Map<String ,String > getDesName(){
		Map<String, String> desNameMap = new HashMap<String, String>();
		desNameMap.put("水位","水位极低|水位极高|水位低|水位高|水位正常|水位低报警|水位高报警");
		desNameMap.put("运行状态","故障状态|运行状态");
		desNameMap.put("压力状态","压力超高报警|压力超低报警");
		return desNameMap;
	}
	
	public static Map<String,String> getColor(){
		Map<String, String> colorMap = new HashMap<String, String>();
		colorMap.put("43", "#d99594");
		colorMap.put("44", "#66ff66");
		colorMap.put("45", "#ffff00");		
		colorMap.put("46", "#66ccff");
		colorMap.put("47", "#ff6699");
		colorMap.put("48", "#00ff99");		
		colorMap.put("49", "#990033");
		colorMap.put("50", "#ff0066");
		colorMap.put("51", "#ff9933");		
		colorMap.put("52", "#3366ff");
		colorMap.put("53", "#7030a0");
		return colorMap;
	}
	/**
	 * 获得中文值
	 * 未找到匹配值则反回原值
	 * @param value		读数
	 * @param point		点名
	 * @param eqtype	设备类型
	 * @return
	 */
	@Deprecated
	public static String getCNVal(String value,String point,String eqtype){
		String str=value;
		if ("4".equals(eqtype)) {
			//电梯
			if (point != null) {				
				if (point.indexOf(".RS_CV")>0) {
					//运行状态
					if ("0".equals(value)) {
						str = "停";
					}else if ("1".equals(value)) {
						str = "运行";
					}else if ("2".equals(value)) {
						str = "故障";
					}					
				}else if(point.indexOf(".MD_CV")>0){
					//运行模式
					if ("0".equals(value)) {
						str = "停";
					}else if ("1".equals(value)) {
						str = "上";
					}else if ("2".equals(value)) {
						str = "下";
					}					
				}
				
			}
			
		}
		return str;
	}
	
	public static String getCNVal2(String value,String point,String eqtype){
		String str=value;
		if ("4".equals(eqtype)) {
			//电梯
			if (point != null) {
				
				if (point.indexOf(".RS_CV")>0)
				{
					//运行状态
						if ("1".equals(value) && point.indexOf(".RS_CV")>0) {

							str = "&lt;div  class='shishijiankong_jjyxzt1'&gt;正常运行&lt;/div&gt;";

						} else if ("0".equals(value)) {

							str = "&lt;div  class='shishijiankong_jjyxzt1'&gt;正常运行&lt;/div&gt;";

						}
				}
				else if(point.indexOf(".MD_CV")>0) {

					//运行模式
					if ("0".equals(value)) {
					
						str = "";   //|&lt;div class='shishijiankong_yxzt2'&gt;
					
					}else if ("1".equals(value) || "-1".equals(value)) {
					
						str = "&lt;img src='manager/images/control/shishijiankong_dianti_31.png' /&gt;";
					
					}else if ("2".equals(value)) {
					
						str = "&lt;img src='manager/images/control/shishijiankong_dianti_32.png' /&gt;";
				
					}					
				}
				
				if (point.indexOf(".FS_CV")>0)
				{
				
						//运行状态
						if ("0".equals(value)) {

							str = "&lt;div  class='shishijiankong_jjyxzt1'&gt;正常运行&lt;/div&gt;";

						} else if ("1".equals(value) || "-1".equals(value)) {

							str = "&lt;div class='shishijiankong_jjyxzt2'&gt;故障状态&lt;/div&gt;";

						} else if ("2".equals(value)) {

							str = "";

						}
				}
				else if(point.indexOf(".MD_CV")>0) {

					//运行模式
					if ("0".equals(value)) {
					
						str = "";   //|&lt;div class='shishijiankong_yxzt2'&gt;
					
					}else if ("1".equals(value) || "-1".equals(value) ) {
					
						str = "&lt;img src='manager/images/control/shishijiankong_dianti_31.png' /&gt;";
					
					}else if ("2".equals(value)) {
					
						str = "&lt;img src='manager/images/control/shishijiankong_dianti_32.png' /&gt;";
				
					}					
				}
				if (point.indexOf(".XF_CV")>0){
					if ("1".equals(value) || "-1".equals(value) ) {

						str = "&lt;div class='shishijiankong_jjyxzt3'&gt;消防状态&lt;/div&gt;";
						
					}else if ("0".equals(value)) { 
						
						str = "&lt;div class='shishijiankong_jjyxzt1'&gt;正常状态&lt;/div&gt;";
					}
				} 
			}
		}
		return str;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str 字符串
	 * @return boolean
	 */
	public static boolean isNull(String str){
		if(str == null || str.trim().length() == 0 || str.trim().equals("null")){
			return true;
		} else {
			return false;
		}		
	}
	
	/**
	 * 判断对象是否为空
	 * @param statement  
	 * @param        
	 * @return
	 */
	public static boolean isNullObject(Object obj){
		if(obj==null){
			return true;
		}
		return false;
	}
	
	/**
	 * 将字符串转成  int 数据
	 * @param str
	 * @return
	 */
	public static int toInt(String str){
		int result=0;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	/**
	 * 将字符串转成  double 数据
	 * @param str
	 * @return
	 */
	public static double toDouble(String str){
		double result=0D;
		try {
			result =Double.parseDouble(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	/**
	 * 将字符串转成  float 数据
	 * @param str
	 * @return
	 */
	public static float toFloat(String str){
		float result=0f;
		try {
			result =Float.parseFloat(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	
	/**
	 * 创建关于数据库的公用字段便于修改数据库后容易修改
	 * 公用表中的不同类型的标识
	 */
	/**
	 * 栏目
	 */
	public static String BASE_COMM_PROGRAMA = "1";
	
	/**
	 * 医院编码
	 */
	public static String BASE_COMM_HOSP_CODE = "2";
	
	/**
	 * 医院级别
	 */
	public static String BASE_COMM_HOSP_LEVELS = "3";
	
	/**
	 * 规划区域
	 */
	public static String BASE_COMM_HOSP_AREA = "4";
	
	/**
	 * 楼宇建筑状态
	 */
	public static String BASE_COMM_BUILD_BUILDING_STATE = "5";
	
	/**
	 * 楼宇图纸类型
	 */
	public static String BASE_COMM_BUILD_DRAWING_STATE = "6";
	
	/**
	 * 医院行政区域
	 */
	public static String BASE_COMM_HOSP_DIST = "7";
	
	/**
	 * 医院类型
	 */
	public static String BASE_COMM_HOSP_TYPE = "21";
	
	/**
	 * 服务类型
	 */
	public static String BASE_COMM_SERVICE_TYPE = "41";
	/**
	 * 班组
	 */
	public static String BASE_COMM_CLASS_TYPE = "42";
	
	
	/**
	 * 能源账单类型
	 */
	public static String BASE_COMM_ENERY_TYPE = "23";
	public static String replacePath(String rootPath) {
		if("\\".equals(File.separator)){   		 
			rootPath = rootPath.replace("/", "\\");
		}
		//linux下
		if("/".equals(File.separator)){   
			  rootPath = rootPath.replace("\\", "/");
		}
		return rootPath;
	}
}

package com.hanqian.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.hanqian.business.WeatherBusiness;
import com.hanqian.business.WeatherServiceBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.Weather;
import com.hanqian.util.DateUtil;
import com.hanqian.util.StringUtil;

import net.sf.json.JSONObject;
public class CityWeatherForecastTimers {
	private static final Log log = LogFactory.getLog(CityWeatherForecastTimers.class);
	
	@Resource
	private WeatherServiceBusiness weatherService;
//	@Resource
//	private MultiPropertiesUtils configMessage;
	
	//获取当天天气（半个小时更新一次）
	public void updateCityWeather(){
		

		WebApplicationContext context = ContextLoader
				.getCurrentWebApplicationContext();
		WeatherBusiness weatherBusiness = context.getBean("weatherBusiness",
				WeatherBusiness.class);

		
		
		Date ptime = DateUtil.getNowDateShort();
		List<Weather> weather_7day=null;//判断IM_WEATHER_7DAYS表里今天之后的六天天气信息是否已经存在

		
		// 获取cityID /D:/Workspace/hq-logistics/target/classes/
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath().toString();
		File temp = new File(path);
		String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
		String filePath = rootpath + File.separator + "manager"
				+ File.separator + "config" + File.separator + "data.json";
		log.info("天气文件路径----------" + filePath);
//		D:\Workspace\hq-logistics222\manager\config\data.json
		
		String jsonFileStr = getJsonFromFile(filePath);
		
		String cityName = getCityName();
		
		JSONObject cityInfoJson = JSONObject.fromObject(jsonFileStr);
		log.info("当前城市----------" + cityName);
	

		String citycode = null;
		String weatherData = "";

		try {
			citycode = (String) cityInfoJson.get(cityName);
		}catch (Exception e) {
			log.error("获取天气预报城市编码时出现错误",e);
		}
		//获取当天实时的温度
//		HttpGet httpget = new HttpGet("http://www.nmc.cn/service/data/real/"+citycode+".json");
		HttpGet httpget = new HttpGet("http://www.nmc.cn/f/rest/real/58367");
		CloseableHttpClient httpclient = HttpClients
				.custom().setDefaultRequestConfig(RequestConfig.custom()
						.setSocketTimeout(60000).setConnectTimeout(20000).setConnectionRequestTimeout(20000).build())
				.build();
		try {
			HttpResponse response = httpclient.execute(httpget,
					new BasicHttpContext());
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的数据 
				weatherData = EntityUtils.toString(entity, "UTF-8");
			}
		}catch (Exception e) {
			log.error("后台从中央气象台网获取当天天气的HTML对象时出错了！",e);
		}
		//把得到的当天天气数据放入数据库
		try {
			
			weatherService.addWeatherDay(weatherData,citycode);
			
			
		}catch (Exception e) {
			log.error("添加一条当天天气预报信息时出现错误",e);
		}
		//判断IM_WEATHER_7DAYS表里今天之后的六天天气信息是否已经存在
		try {	
			
		
			Date now = DateUtil.getNowDate();
			String nowDt = DateUtil.dateToString(now, "yyyy/MM/dd");

				Map<String,Object> map2 = new HashMap<String,Object>();
		    	map2.put("CITY_ID",Integer.parseInt( citycode));
		    	map2.put("DT",nowDt);
		    	com.hanqian.pojo.Weather w=  weatherService.selectWeatherOne(map2);

		    	//IM_WEATHER_7DAYS表里今天之后的六天天气信息如果不存在就去中国天气网获取
				if(w !=null){			
		
				String optime = DateUtil.dateToString(w.getOpTime(), "yyyy/MM/dd");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				Date dt1 = df.parse(optime);
				Date dt2 = df.parse(nowDt);
				
			     if (dt1.getTime() > dt2.getTime()) {
		                System.out.println("dt1 在dt2前");
		            } else if (dt1.getTime() < dt2.getTime()) {
						day7Weather(citycode,cityName);
						log.debug("获取当天天气预报信息！");
		                System.out.println("dt1在dt2后");
		            } 
//
				}else{
					day7Weather(citycode,cityName);
				}		
		    	
			
//			 weather_7day = weatherBusiness.findWeatherByDT(nowDt);
			
			
//			weather_7day = (List<Map<String, Object>>) weatherService.getWeather7day(ptime).get("weather_7day");
		}catch (Exception e) {
			log.error("获取当天天气预报信息时出现错误",e);
		}
		
		
	}
	
	//获取最近七天天气（一天更新一次）
	public void day7Weather(String citycode,String cityName){
		
	
		
		Date ptime = DateUtil.getNowDateShort();
		Date optime = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		WebClient wc = new WebClient();
		HtmlDivision div = null;
		wc.getOptions().setJavaScriptEnabled(false);
		try {			
			final HtmlPage page=wc.getPage("http://www.weather.com.cn/weather/"+citycode+".shtml");
			Boolean hasValue = false;
			for (int i = 0; i < 30; i++) {	            
	            //获取七天的天气温度
				div = (HtmlDivision) page.getElementById("7d");
				
	            if (div != null) {
	            	hasValue = true;
	            	break;
	            }	            
	            synchronized (page) {
	                page.wait(1000);
	            }
			}
			if (hasValue){
				//获取七天的天气温度
				
				DomNode ul = page.getFirstByXPath("//div[@id='7d']/ul");
				HtmlSpan day01_up = ul.getFirstByXPath("//li[1]/p[@class='tem']/span");	
				DomNode day01_down = ul.getFirstByXPath("//li[1]/p[@class='tem']/i");	
				HtmlSpan day02_up = ul.getFirstByXPath("//li[2]/p[@class='tem']/span");	
				DomNode day02_down = ul.getFirstByXPath("//li[2]/p[@class='tem']/i");	
				HtmlSpan day03_up = ul.getFirstByXPath("//li[3]/p[@class='tem']/span");	
				DomNode day03_down = ul.getFirstByXPath("//li[3]/p[@class='tem']/i");	
				HtmlSpan day04_up = ul.getFirstByXPath("//li[4]/p[@class='tem']/span");	
				DomNode day04_down = ul.getFirstByXPath("//li[4]/p[@class='tem']/i");	
				HtmlSpan day05_up = ul.getFirstByXPath("//li[5]/p[@class='tem']/span");	
				DomNode day05_down = ul.getFirstByXPath("//li[5]/p[@class='tem']/i");	
				HtmlSpan day06_up = ul.getFirstByXPath("//li[6]/p[@class='tem']/span");	
				DomNode day06_down = ul.getFirstByXPath("//li[6]/p[@class='tem']/i");	
				HtmlSpan day07_up = ul.getFirstByXPath("//li[7]/p[@class='tem']/span");	
				DomNode day07_down = ul.getFirstByXPath("//li[7]/p[@class='tem']/i");
				String date1_up = day01_up.asText();
				String date1_down = day01_down.asText();
				String date2_up = day02_up.asText();
				String date2_down = day02_down.asText();
				String date3_up = day03_up.asText();
				String date3_down = day03_down.asText();
				String date4_up = day04_up.asText();
				String date4_down = day04_down.asText();
				String date5_up = day05_up.asText();
				String date5_down = day05_down.asText();
				String date6_up = day06_up.asText();
				String date6_down = day06_down.asText();
				String date7_up = day07_up.asText();
				String date7_down = day07_down.asText();
				numberConversion(map,date1_up,1);
				numberConversion(map,date2_up,2);
				numberConversion(map,date3_up,3);
				numberConversion(map,date4_up,4);
				numberConversion(map,date5_up,5);
				numberConversion(map,date6_up,6);
				numberConversion(map,date7_up,7);
				number7dayConversion(map,date1_down,1);
				number7dayConversion(map,date2_down,2);							
				number7dayConversion(map,date3_down,3);	
				number7dayConversion(map,date4_down,4);	
				number7dayConversion(map,date5_down,5);	
				number7dayConversion(map,date6_down,6);	
				number7dayConversion(map,date7_down,7);	
			} else {
			    log.error("取xxxx界面,已经达到加载30秒还无法得到xxxxxx的数据");
			}	
		}catch (Exception e) {
			log.error("后台从中国天气网获取七天天气的HTML对象时出错了！",e);
		}

		//把得到的七天天气数据放入数据库
		try {
			Date now = DateUtil.getNowDate();
			String nowDt = DateUtil.dateToString(now, "yyyy/MM/dd");
	    	SimpleDateFormat sim=new SimpleDateFormat("hh:mm");
	    	map2.put("PTIME", sim.format(now));
	    	map2.put("CITY_ID",Integer.parseInt( citycode));
			map2.put("OP_TIME", now);
			map2.put("CITY", cityName);
			map2.put("IMG1", "");
			map2.put("IMG2", "");
			map2.put("WEATHER", "");		
			
			
			
				for(int i=0;i<7;i++){	
					Calendar   calendar   =   new   GregorianCalendar(); 
				     calendar.setTime(now); 
				    	 calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动  
				      Date date1=calendar.getTime();   //这个时间就是日期往后推一天的结果
				     map2.put("DT", DateUtil.dateToString(date1, "yyyy/MM/dd"));
				     
				  	 int ia =i+1; 
				       String temp1="date"+ia+"_temp_up";
					   String temp2="date"+ia+"_temp_down";	
	 			       map2.put("TEMP1",  map.get(temp1));//最高气温
					   map2.put("TEMP2",  map.get(temp2));//最低气温	
				     
				     
				     //根据当前日期去查，如果没有插入。如果有修改
					   com.hanqian.pojo.Weather w=  weatherService.selectWeatherOne(map2);
				      if(w==null ){
				  
						weatherService.addWeather7Day(map2); 
				   
				      }else{			  
					      weatherService.updateWeather7Day(map2); 

				    	 }
			
				
				
				}
	
				
			
			
		}catch (Exception e) {
			log.error("添加一条七天天气预报信息时出现错误",e);
		}
		
	}

	
	
	private void number7dayConversion(Map map, String date_down,int day) {
		String[] dataArr;
		int date_temp_down;
		if(date_down.endsWith("℃")){
			dataArr = date_down.split("℃");
			date_down = dataArr[0];
//			if(StringUtil.isNumber(date_down)){
				date_temp_down = StringUtil.getIntValue(date_down);
				map.put("date"+day+"_temp_down", date_temp_down);
//			}else{
////				log.error("取7天天气预报的第{}天数据时,取最低温度时无法转换为数字类型, 取得的内容是:{}",day);
//			}
		}else{
//			log.error("取7天天气预报的第{}天数据时,取最低温度时最后一位不是℃, 取得的内容是:{}",day,date_down);
		}
	}
	private void numberConversion(Map map, String date_up,int day) {
		int date_temp_up;		
//		if(StringUtil.isNumber(date_up)){
			date_temp_up = StringUtil.getIntValue(date_up);
			map.put("date"+day+"_temp_up", date_temp_up);
//		}else{
//			log.error("取7天天气预报的第{}天数据时,取最高温度时无法转换为数字类型, 取得的内容是:{}",day,date_up);
//		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 获取数据 从文件
	 * 
	 * @param 2013-8-16
	 * @param @param path
	 * @param @return
	 * @return String
	 */
	public static String getJsonFromFile(String path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			String tempString = null;
			int line = 1;
			File rf = new File(path);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					rf), "utf-8");
			reader = new BufferedReader(read);
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				laststr = laststr + tempString;
				line++;
			}
			reader.close();
			read.close();

		} catch (IOException e) {
			log.error("WeatherUtil-->getJsonFromFile", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					log.error("WeatherUtil-->getJsonFromFile", e1);
				}
			}
		}

		return laststr;
	}
	
	public static String getCityName() {
		String cityName = "";
		try {
			log.info("获取城市id的url:" + ConfigCST.CST_CITYNAME_URL);
			HttpGet httpget = new HttpGet(ConfigCST.CST_CITYNAME_URL);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpget,
					new BasicHttpContext());
			HttpEntity hentity = response.getEntity();
			String cityNameStr = "";
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的数据
				cityNameStr = EntityUtils.toString(hentity);

				JSONObject jsonObject = JSONObject.fromObject(cityNameStr);
				cityName = (String) jsonObject.get("city");
			} else {
				cityNameStr = EntityUtils.toString(hentity);
				log.error("获取城市id出现返回状态不正确.返回内容:" + cityNameStr);
			}
		} catch (ClientProtocolException e) {
			log.error("WeatherUtil-->getCityName==Client", e);
		} catch (IOException e) {
			log.error("WeatherUtil-->getCityName==IO", e);
		} catch (Exception e) {
			log.error("WeatherUtil-->getCityName", e);
		}
		return cityName;
	}
	
	
	
}

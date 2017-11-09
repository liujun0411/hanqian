package com.hanqian.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.WeatherBusiness;
import com.hanqian.business.WeatherServiceBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.Weather;
import com.hanqian.pojo.WeatherRt;
import com.hanqian.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 
 * 描 述: 天气预报
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author lg
 * @version 1.4 2013-8-12
 * @see
 */
public class WeatherUtilAction extends ActionSupport {
	private static final Log log = LogFactory.getLog(WeatherUtilAction.class);
	
	@Resource
	private WeatherServiceBusiness weatherService;

	private WeatherBusiness weatherBusiness;
	private WeatherRt weatherRt;
	private Weather weather;
    public void findWeatherInfo(){
    	Date now = DateUtil.getNowDate();
		String nowDt = DateUtil.dateToString(now, DateUtil.BACKSLASH_SHORT_TIME_FORMAT);
		List<Weather> weatherList = weatherBusiness.findWeatherByDT();
//		List<WeatherRt> weatherRtList = weatherBusiness.findWeatherRTByDT();
		JSONObject weatherInfoObj = new JSONObject();
		
		
	
		
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath().toString();
		File temp = new File(path);
		String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
		String filePath = rootpath + File.separator + "manager"
				+ File.separator + "config" + File.separator + "data.json";
		String jsonFileStr = getJsonFromFile(filePath);
		String cityName = getCityName();
		JSONObject cityInfoJson = JSONObject.fromObject(jsonFileStr);
		
		String citycode = null;
		try {
			citycode = (String) cityInfoJson.get(cityName);
		}catch (Exception e) {
			log.error("获取天气预报城市编码时出现错误",e);
		}

		
		Map<String,Object> map2 = new HashMap<String,Object>();
    	map2.put("CITY_ID",Integer.parseInt( citycode));
    	map2.put("DT",nowDt);
    	List<WeatherRt> weatherRtList=   weatherService.selectWeatherRTByDT(map2);
		
		
		
		if (weatherList!=null&&!weatherList.isEmpty()&&weatherList.size()>0) {
			weather = weatherList.get(weatherList.size()-1);
			weatherInfoObj.accumulate("city", weather.getCity());
			weatherInfoObj.accumulate("img1", weather.getImg1());
			weatherInfoObj.accumulate("img2", weather.getImg2());
			weatherInfoObj.accumulate("temp1", weather.getTemp1());
			weatherInfoObj.accumulate("temp2", weather.getTemp2());
//			weatherInfoObj.accumulate("weather", weather.getWeather());
			weatherInfoObj.accumulate("weatherPtime", weather.getPtime());
//			weatherInfoObj.accumulate("weatherDT", weather.getDt());
		}
		

		if (weatherRtList!=null&&!weatherRtList.isEmpty()&&weatherRtList.size()>0) {
			weatherRt = weatherRtList.get(0);
//			weatherInfoObj.accumulate("city", weather.getCity());
			weatherInfoObj.accumulate("SD", weatherRt.getSd());
			weatherInfoObj.accumulate("temp", weatherRt.getTemp());
			weatherInfoObj.accumulate("WD", weatherRt.getWd());
			weatherInfoObj.accumulate("WS", weatherRt.getWs());
		
			weatherInfoObj.accumulate("weatherRtPtime", weatherRt.getPtime());
			weatherInfoObj.accumulate("weatherRtDT", weatherRt.getDt());
			
			
			weatherInfoObj.accumulate("alarm", weatherRt.getAlarm());
			weatherInfoObj.accumulate("ALARM_URL", weatherRt.getAlarmurl());
			weatherInfoObj.accumulate("INFO", weatherRt.getWs());
			
			if(weatherRt.getOpTime()==null){
				weatherInfoObj.accumulate("diffDate", 2);
			}else{
				weatherInfoObj.accumulate("diffDate", DateUtil.diffDate(new Date(),weatherRt.getOpTime()));
			}
		}
		HttpServletResponse response;
    	PrintWriter out = null;
    	try{
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(weatherInfoObj.toString());
    	}catch (Exception e) {
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
     }

	/**
	 * @return the weatherRt
	 */
	public WeatherRt getWeatherRt() {
		return weatherRt;
	}

	/**
	 * @return the weather
	 */
	public Weather getWeather() {
		return weather;
	}

	/**
	 * @param weatherRt the weatherRt to set
	 */
	public void setWeatherRt(WeatherRt weatherRt) {
		this.weatherRt = weatherRt;
	}

	/**
	 * @param weather the weather to set
	 */
	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	/**
	 * @return the weatherBusiness
	 */
	public WeatherBusiness getWeatherBusiness() {
		return weatherBusiness;
	}

	/**
	 * @param weatherBusiness the weatherBusiness to set
	 */
	public void setWeatherBusiness(WeatherBusiness weatherBusiness) {
		this.weatherBusiness = weatherBusiness;
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

package com.hanqian.business;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hanqian.pojo.WeatherRt;
import com.hanqian.util.DateUtil;
import com.hanqian.util.StringUtil;

@Service("weatherServiceBusiness")
public class WeatherServiceBusiness extends BaseBusiness {
//	private static final Logger log = WeatherService2.getLogger();
	
	
	private static final Log log = LogFactory.getLog(WeatherServiceBusiness.class);
	
	
	
	
	public String getZipcode(String hosp_code) {
		String zipcode = //monitorDao.getZipcode(hosp_code);
		this.sqlSession.selectOne("getZipcode",hosp_code);
		return zipcode;
	}
	
	public com.hanqian.pojo.Weather selectWeatherOne(Map<String,Object> map) {
		
		return this.sqlSession.selectOne("selectWeatherOne",map);
	}
	
public List<WeatherRt> selectWeatherRTByDT(Map<String,Object> map) {
		
		return this.sqlSession.selectList("selectWeatherRTByDT",map);
	}
	
	
	public void updateWeather7Day(Map<String,Object> map){
		this.sqlSession.update("updateWeather7Day",map);
	}
		
	
	public String getCityCode(String zipcode) {
		String citycode = //monitorDao.getCityCode(zipcode);
		this.sqlSession.selectOne("getCityCode",zipcode);
		return citycode;
	}

	public void addWeatherDay(String weatherData,String citycode) throws UnsupportedEncodingException {
		Map map = new HashMap();
		Date optime = new Date();
		Gson gs = new Gson();
		Map jo = gs.fromJson(weatherData, Map.class);
		String url = "";
		String aqi = "";
//		String city_code = (String) jo.get("city_code");
		
		
		Map weather = (Map) jo.get("weather");
		
		Map warn = (Map) jo.get("warn");
		
		Map station = (Map) jo.get("station");
		
		Map wind = (Map) jo.get("wind");
		
		//发布时间
		String data_time = StringUtil.toString(jo.get("publish_time"));
		
		String temperature = StringUtil.toString(weather.get("temperature"));
		//天气状况
		String info = StringUtil.toString(weather.get("info"));		
		//湿度
		String humidity = StringUtil.toString(weather.get("humidity"));
		
		String alert = StringUtil.toString(warn.get("alert"));
		//城市名字
		String city = StringUtil.toString(station.get("city"));
		
		//风力
		String power = StringUtil.toString(wind.get("power"));
		//风向
		String direct = StringUtil.toString(wind.get("direct"));
		
		if(!alert.equals("9999")){
			url = StringUtil.toString(warn.get("url"));
//			byte[] bytes=url.getBytes("gbk");
//			url=new String(bytes,"utf-8");
			url = "http://www.nmc.cn"+url;
			String[] dataArr = alert.split("发布");
			alert = dataArr[1];
			
			//预警
			map.put("ALARM", alert);
			map.put("ALARM_URL", url);
		}	
		Date now = DateUtil.getNowDate();

		//城市名称
				map.put("INFO", info);
		
       //推送时间
 		map.put("DT", DateUtil.dateToString(now, "yyyy/MM/dd"));
		//城市id
		map.put("CITY_ID", Integer.parseInt(citycode));
		
		//城市名称
		map.put("CITY", city);
		//温度
		map.put("TEMP", Double.parseDouble(temperature));
		//湿度
		map.put("SD", humidity);
		//风向
		map.put("WD", direct);
		//风力
		map.put("WS", power);
		//推送时间
		map.put("PTIME", data_time.substring(11));
		//推送时间
				map.put("OP_TIME", now);				
				//操作实时
		this.sqlSession.insert("addWeatherDay", map);
	
	}
	public void addWeather7Day(Map map) {
		this.sqlSession.insert("addWeather7Day", map);	
	}
	
	


	
	
}

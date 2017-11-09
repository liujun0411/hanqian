package com.hanqian.weather;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hanqian.constant.ConfigCST;

public class CityWeatherForecastTimer {
	private static final Log log = LogFactory.getLog(CityWeatherForecastTimer.class);
	public void updateCityWeather() throws Exception{
		try {
			//chengshi 天气
//			String weather_url = ConfigUtil.getUrlConfig("/config.properties", "cityWeatherUrl");
			String entityStr = WeatherUtil.getEntityStr(ConfigCST.CST_CITYWEATHER_URL);
			WeatherUtil.writeDataBaseCity(entityStr);
//			WeatherUtil.writeFile("cityWeatherUrl.txt", entityStr+"\r\n");
			log.info("城市天气得到的数据："+entityStr);
		}catch(Exception e) {
			log.error("CityWeatherForecastTimer-->updateCityWeather(城市天气)"+e);
			throw e;
		}
	}
}

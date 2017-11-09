package com.hanqian.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hanqian.business.WeatherBusiness;
import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.Weather;
import com.hanqian.pojo.WeatherRt;
import com.hanqian.util.DateUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author lg
 * @version 1.4 2013-8-16
 * @see
 */
public class WeatherUtil {

	private static final Logger log = Logger.getLogger(WeatherUtil.class);

	/**
	 * 获取url返回的数据
	 * 
	 * @param 2013-5-16
	 * @param @param url
	 * @param @return
	 * @return String
	 */
	public static String getEntityStr(String url) {
//		http://www.weather.com.cn/data/cityinfo/
		String entityStr = null;
		try {
			// 获取cityID /D:/Workspace/hq-logistics/target/classes/
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath().toString();
			File temp = new File(path);
			String rootpath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
			String filePath = rootpath + File.separator + "manager"
					+ File.separator + "config" + File.separator + "data.json";
			log.info("天气文件路径----------" + filePath);
			
			String jsonFileStr = getJsonFromFile(filePath);
			
			String cityName = getCityName();
			
			JSONObject cityInfoJson = JSONObject.fromObject(jsonFileStr);
			log.info("当前城市----------" + cityName);
			String cityId = (String) cityInfoJson.get(cityName);
		
			
			// 组装天气预报链接 发送链接 获取数据
			url = url + cityId + ".html";
			
			
			
			log.info("天气预报链接-----------" + url);
			
			
			
			HttpGet httpget = new HttpGet(url);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpget,
					new BasicHttpContext());
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的数据
				entityStr = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			log.error("WeatherUtil-->getEntityStr==Client", e);
		} catch (IOException e) {
			log.error("WeatherUtil-->getEntityStr==IO", e);
		} catch (Exception e) {
			log.error("WeatherUtil-->getEntityStr", e);
		}

		return entityStr;
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

	/**
	 * 向文件写入
	 * 
	 * @param 2013-5-16
	 * @param @param fileName
	 * @param @param fileContent
	 * @return void
	 */
	public static void writeFile(String fileName, String fileContent) {
		// 获得当前类所在的路径
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath().toString();
		// 文件的保存目录
		File temp = new File(path);
		// 创建一个存放文件文件夹
		File pathe = new File(temp.getParentFile().getParentFile().getPath()
				+ File.separator + "weatherFile");
		if (!pathe.exists()) {
			pathe.mkdirs();
		}
		// 声明File对象
		File file = new File(pathe + File.separator + fileName);
		// 第2步、通过子类实例化父类对象
		Writer out = null;
		// 准备好一个输出的对象
		// 通过对象多态性，进行实例化
		// 第3步、进行写操作
		try {
			out = new FileWriter(file, true);
			out.write(fileContent);// 将内容输出，保存文件
		} catch (Exception e) {
			log.error("WeatherUtil-->writeFile", e);
		} finally {
			if (out != null) {
				try {
					// 第4步、关闭输出流
					out.close();
				} catch (Exception e) {

				}
			}
		}

	}

	/**
	 * 写入数据库 weather_RT (city)
	 * 
	 * @param 2013-8-9
	 * @param
	 * @return void
	 */
	public static void writeDataBaseCity(String entityStr) {

		WebApplicationContext context = ContextLoader
				.getCurrentWebApplicationContext();
		WeatherBusiness weatherBusiness = context.getBean("weatherBusiness",
				WeatherBusiness.class);

		try {
			Date now = DateUtil.getNowDate();
			String nowDt = DateUtil.dateToString(now, "yyyy/MM/dd");
			JSONObject jsonObject = JSONObject.fromObject(entityStr);
			Object weatherInfo = jsonObject.get("weatherinfo");
			JSONObject weatherObj = JSONObject.fromObject(weatherInfo);
			// 先查询
			List<Weather> weatherList = weatherBusiness.findWeatherByDT(nowDt);

			if (weatherList == null || weatherList.isEmpty()
					|| weatherList.size() < 1) {
				Weather weather = new Weather();
				weather.setCity(weatherObj.get("city").toString());
				weather.setCityId(Integer.parseInt(weatherObj.get("cityid")
						.toString()));
				weather.setTemp1(Double.parseDouble(weatherObj.get("temp1")
						.toString().replaceAll("℃", "")));
				weather.setTemp2(Double.parseDouble(weatherObj.get("temp2")
						.toString().replaceAll("℃", "")));
				weather.setOpTime(now);
				weather.setDt(nowDt);
				weather.setPtime(weatherObj.get("ptime").toString());
				weather.setWeather(weatherObj.get("weather").toString());
				weather.setImg1(weatherObj.get("img1").toString());
				weather.setImg2(weatherObj.get("img2").toString());
				weatherBusiness.insertWeather(weather);
			} else {
				Weather weather = weatherList.get(0);
				weather.setCity(weatherObj.get("city").toString());
				weather.setCityId(Integer.parseInt(weatherObj.get("cityid")
						.toString()));
				weather.setTemp1(Double.parseDouble(weatherObj.get("temp1")
						.toString().replaceAll("℃", "")));
				weather.setTemp2(Double.parseDouble(weatherObj.get("temp2")
						.toString().replaceAll("℃", "")));
				weather.setOpTime(now);
				weather.setDt(nowDt);
				weather.setPtime(weatherObj.get("ptime").toString());
				weather.setWeather(weatherObj.get("weather").toString());
				weather.setImg1(weatherObj.get("img1").toString());
				weather.setImg2(weatherObj.get("img2").toString());
				weatherBusiness.updateWeather(weather);
			}
		} catch (Exception e) {
			log.error("WeatherUtil-->writeDataBaseCity(写入db_weather_RT数据库出错)",
					e);
		}

	}

	/**
	 * 写入数据库 weather (sk)
	 * 
	 * @param 2013-8-9
	 * @param
	 * @return void
	 */
	public static void writeDataBase(String entityStr) {
		WebApplicationContext context = ContextLoader
				.getCurrentWebApplicationContext();
		WeatherBusiness weatherBusiness = context.getBean("weatherBusiness",
				WeatherBusiness.class);

		try {
			Date now = DateUtil.getNowDate();
			WeatherRt weatherRt = new WeatherRt();
			JSONObject jsonObject = JSONObject.fromObject(entityStr);
			Object weatherInfo = jsonObject.get("weatherinfo");
			JSONObject weatherObj = JSONObject.fromObject(weatherInfo);
			weatherRt.setCity(weatherObj.get("city").toString());
			weatherRt.setCityId(Integer.parseInt(weatherObj.get("cityid")
					.toString()));
			weatherRt.setTemp(Double.parseDouble(weatherObj.get("temp")
					.toString()));
			weatherRt.setOpTime(now);
			weatherRt.setDt(DateUtil.dateToString(now, "yyyy/MM/dd"));
			weatherRt.setPtime(weatherObj.get("time").toString());
			weatherRt.setWd(weatherObj.get("WD").toString());
			weatherRt.setWs(weatherObj.get("WS").toString());
			weatherRt.setSd(weatherObj.get("SD").toString());
			weatherBusiness.insertWeatherRt(weatherRt);
		} catch (Exception e) {
			log.error("WeatherUtil-->writeDataBase(写入db_weather数据库出错)", e);
		}
	}

	public static void main(String[] args) {
		System.out
				.println(getJsonFromFile("E:/hanqian-work/work/project/logisticsCSS/WebRoot/manager/config/data.json"));
	}
}

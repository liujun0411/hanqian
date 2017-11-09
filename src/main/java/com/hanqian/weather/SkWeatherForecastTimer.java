package com.hanqian.weather;



import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hanqian.constant.ConfigCST;
import com.hanqian.util.StringUtil;
import com.hanqian.util.mail.SimpleMailSender;
import com.hanqian.util.mail.pojo.MailInfo;


public class SkWeatherForecastTimer {

	private static final Log log = LogFactory.getLog(SkWeatherForecastTimer.class);
	private int exceptionNum = 0;
	public void updateSkWeather() throws Exception{
		try {
			//实况天气
//			String weather_url = ConfigUtil.getUrlConfig("/config.properties", "skWeatherUrl");
			String entityStr = WeatherUtil.getEntityStr(ConfigCST.CST_SKWEATHER_URL);
//			WeatherUtil.writeFile("skWeatherUrl.txt", entityStr+"\r\n");
//			int b = 0;
//			System.out.println(b/0);
			WeatherUtil.writeDataBase(entityStr);
			log.info("实况天气得到的数据："+entityStr);
			exceptionNum = 0;
		}catch(Exception e) {
			exceptionNum++;
			if(exceptionNum==2){
				//发送邮件
				String to = ConfigCST.MAIL_TO;
				String content = ConfigCST.MAIL_CONTENT.replace("{hosp}", ConfigCST.CST_CURRENT_HOSP_CODE).replace("{e}", e.toString());
				
				if (to != null && to.length() > 0) {
					
					List toList = StringUtil.str2List(to, ",", false);
					
					MailInfo mailInfo = new MailInfo();   
				    mailInfo.setMailServerHost(ConfigCST.MAIL_SMTPHOST);   
				    mailInfo.setMailServerPort(ConfigCST.MAIL_PORT);   
				    mailInfo.setValidate(ConfigCST.MAIL_VALIDATE);   
				    mailInfo.setUserName(ConfigCST.MAIL_USERNAME);   
				    mailInfo.setPassword(ConfigCST.MAIL_PASSWORD);//您的邮箱密码   
				    mailInfo.setFromAddress(ConfigCST.MAIL_FROMADDRESS);   
				    mailInfo.setToAddress(toList);   
				    mailInfo.setSubject(ConfigCST.MAIL_TITLE);   
				    mailInfo.setContent(content);   
				     
				    //这个类主要来发送邮件  
				    SimpleMailSender sms = new SimpleMailSender();  
				    sms.sendTextMail(mailInfo);//发送文体格式   
				    
				    log.info("天气预报连续2次接收数据异常. 已发送邮件给" + to + "进行报告.");
				} else {
					log.info("天气预报连续2次接收数据异常. 因为配置文件内无邮件设置参数缺少所以无外部提醒操作.");
				}
				exceptionNum = 0;
			}
			log.error("SkWeatherForecastTimer-->updateSkWeather"+e);
			throw e;
		}
	}
}

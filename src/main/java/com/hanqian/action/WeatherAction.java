package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.WeatherBusiness;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;


/**
 * 天气情况
 *
 */
public class WeatherAction {
	private HttpServletRequest request;
	private String imgPath;			//图片保存位置
	private WeatherBusiness weBs;		//业务
	
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void setWeBs(WeatherBusiness weBs) {
		this.weBs = weBs;
	}
	
	/**
	 * 登陆是否失效
	 * false 失效 ；true 正常
	 * @return
	 */
	private boolean LoadisFail(DbUsers dbusers){		
		if (null == dbusers) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 月平均气温
	 * @return
	 */
	public String showAvgWeather(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		RetCode rt = weBs.findAvgWeather(request.getRealPath(imgPath),imgPath);
		request.setAttribute("wobj", rt.getObj());
		request.setAttribute("message", rt.getDesc());
		
		return "avgair";
	}
	
	
}

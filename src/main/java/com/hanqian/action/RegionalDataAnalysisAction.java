package com.hanqian.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.RegionalDataAnalysisBusiness;
import com.hanqian.business.WeatherBusiness;
import com.hanqian.form.VOStatCondition;
import com.hanqian.form.VOWeather;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysQuYuName;



/**
 * 区域数据分析
 * @author clczp
 *
 */
public class RegionalDataAnalysisAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(RegionalDataAnalysisAction.class);
	
	private String imgPath;						//图片位置
	private WeatherBusiness wBs;				//天气情况
	private VOStatCondition  voobj;				//查询条件
	private HttpServletRequest  request;		//请求对象	
	private RegionalDataAnalysisBusiness regBs;	//区域数据分析Business
	
	
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
	 * 加载界面
	 * @return
	 */
	public String showUI(){
		request = ServletActionContext.getRequest();
		
		//判断Session 是否失效
		DbUsers dbusers = (DbUsers) request.getSession().getAttribute("users");
		if (!this.LoadisFail(dbusers)) {
			return "login";
		}
		
		Short yt = 1;
		boolean isAnalysis = false;
		try {
			yt = Short.parseShort(request.getParameter("yt")+"");
			isAnalysis = Boolean.parseBoolean(request.getParameter("isAnalysis"));			
		} catch (Exception e) {
			logg.error("RegionalDataAnalysisAction-->showUI", e);
		}
		
		
		//查询数据
		RetCode rt=regBs.findData(voobj, yt, dbusers,request.getRealPath(imgPath), imgPath,isAnalysis);
		
		//输出
		if (null != rt.getObj()) {
			request.setAttribute("obj", rt.getObj());
			request.setAttribute("name", SysQuYuName.getName(yt+"")+"区域能耗量");
			//天气情况
			VOWeather vowe = wBs.findSHMonthWeather("上海",null,null,request.getRealPath(imgPath),imgPath);
			request.setAttribute("vowe",vowe);
		}else{
			request.setAttribute("message", rt.getDesc());
		}
		
		request.setAttribute("action", "regionaldataanalysis_showUI?yt="+yt);
		request.setAttribute("name", SysQuYuName.getName(yt+"")+"区域能耗量");
		
		return "show";
	}

	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public void setVoobj(VOStatCondition voobj) {
		this.voobj = voobj;
	}

	public void setRegBs(RegionalDataAnalysisBusiness regBs) {
		this.regBs = regBs;
	}

	public void setwBs(WeatherBusiness wBs) {
		this.wBs = wBs;
	}
	public VOStatCondition getVoobj() {
		return voobj;
	}
		
	
}

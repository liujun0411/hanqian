package com.hanqian.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.SerMainBusiness;
import com.opensymphony.xwork2.ActionSupport;

public class AlarmTimeAction extends ActionSupport{
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(AlarmTimeAction.class);
	private MenuInterceptor menuInterceptor;
	private SerMainBusiness serMainBusiness;
	/**
	 * @param 2013-1-14  
	 * @param @return       
	 * @return short
	 */
	
	private List alarmList;
	private String level1;
	private String level2;
	private String level3;
	
	public String toAlarmTime(){
		alarmList=serMainBusiness.findALertLevelIntervalDate();
		return "alarmTime";
	}
	
	public String updateAlarmTime(){

		//修改
		serMainBusiness.updateALertLevelIntervalDate(level1, level2, level3);
		HttpServletResponse res=ServletActionContext.getResponse();

	    res.setContentType("text/html; charset=UTF-8"); 
		res.setCharacterEncoding("UTF-8");
//		res.setContentType("UTF-8");
		PrintWriter out=null;
		try {
			out = res.getWriter();
			String str="<script language='javascript'>alert('修改成功!');" +
					"window.location.href='alarmTime!toAlarmTime.action';" +
			"</script>";
			out.write(str);	
		} catch (IOException e) {
logg.error("AlarmTimeAction-->updateAlarmTime", e);
			e.printStackTrace();
		}finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}
	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}
	public SerMainBusiness getSerMainBusiness() {
		return serMainBusiness;
	}
	public void setSerMainBusiness(SerMainBusiness serMainBusiness) {
		this.serMainBusiness = serMainBusiness;
	}



	public List getAlarmList() {
		return alarmList;
	}



	public void setAlarmList(List alarmList) {
		this.alarmList = alarmList;
	}

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	public String getLevel3() {
		return level3;
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	} 
	
	
	
	
}
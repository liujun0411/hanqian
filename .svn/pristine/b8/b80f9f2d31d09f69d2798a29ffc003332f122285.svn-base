package com.hanqian.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.DbAlarmBusiness;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-12-12
 * @see
 */
public class StatisticsSafeAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(StatisticsSafeAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;

	private DbAlarmBusiness alarmBusiness;

	// 查询历史告警的个数
	public void findAlarmCount() {
		HttpServletResponse response;
		PrintWriter out = null;
		try {
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			// 读取xml文件，算出该显示的状态
			out = response.getWriter();
			String jsonStr=alarmBusiness.findAlarmInfo();
			out.print(jsonStr);
		} catch (Exception e) {
			logg.error("StatisticsSafeAction-->findAlarmCount", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 
	 * 查询告警数量
	 * 为首页面表盘查询数据
	 * 一共分为4个档位
	 * 1.无告警
	 * 2.1级告警
	 * 3.2及告警
	 * 4.3级告警
	 * 
	 */
	public void findAlarmHighchartsCount() {
		HttpServletResponse response;
		PrintWriter out = null;
		try {
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			// 读取xml文件，算出该显示的状态
			out = response.getWriter();
			String jsonStr=alarmBusiness.findAlarmHighchartsCount();
			out.print(jsonStr);
		} catch (Exception e) {
			logg.error("StatisticsSafeAction-->findAlarmCount", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	// 查询历史告警的个数
	public void findSpecialAlarmCount() {
		HttpServletResponse response;
		PrintWriter out = null;
		try {
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			// 读取xml文件，算出该显示的状态
			out = response.getWriter();
			String jsonStr=alarmBusiness.findSpecialAlarmCount();
			out.print(jsonStr);
		} catch (Exception e) {
			logg.error("StatisticsSafeAction-->findSpecialAlarmCount", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	// 查询历史告警的个数
	public void findSpecialAlarmCountByHtml5() {
		HttpServletResponse response;
		PrintWriter out = null;
		try {
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			// 读取xml文件，算出该显示的状态
			out = response.getWriter();
			String jsonStr=alarmBusiness.findSpecialAlarmCountByHtml5();
			out.print(jsonStr);
		} catch (Exception e) {
			logg.error("StatisticsSafeAction-->findSpecialAlarmCountByHtml5", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	// 查询历史告警的个数
	public void findAlarmHisCount() {
		HttpServletResponse response;
		PrintWriter out = null;
		try {
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			// 读取xml文件，算出该显示的状态
			out = response.getWriter();
			String jsonStr=alarmBusiness.findAlarmHisCount();
			out.print(jsonStr);
		} catch (Exception e) {
			logg.error("StatisticsSafeAction-->findAlarmHisCount", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public DbAlarmBusiness getAlarmBusiness() {
		return alarmBusiness;
	}

	public void setAlarmBusiness(DbAlarmBusiness alarmBusiness) {
		this.alarmBusiness = alarmBusiness;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}

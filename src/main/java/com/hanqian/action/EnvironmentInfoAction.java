package com.hanqian.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.EnvironmentBusiness;
import com.opensymphony.xwork2.ActionSupport;

public class EnvironmentInfoAction extends ActionSupport{

	private static final Log log = LogFactory.getLog(EnvironmentInfoAction.class);

	private EnvironmentBusiness environmentBusiness;
	
	public EnvironmentBusiness getEnvironmentBusiness() {
		return environmentBusiness;
	}

	public void setEnvironmentBusiness(EnvironmentBusiness environmentBusiness) {
		this.environmentBusiness = environmentBusiness;
	}

	/**
	 * 查询当前室外照度
	 * @return
	 */
	public String showIllumination() {
		System.out.println("----------------------进室外照度action");
		HttpServletRequest request = ServletActionContext.getRequest();
		List list = environmentBusiness.showIllumination();
		request.setAttribute("illuminationList", list);
		return "illuminationPage";
	}
	
	
	/**
	 * 查询热泵设备
	 * @return
	 */
	public String showHeatPumpSystem(){
		System.out.println("----------------------进热泵设备action");
		HttpServletRequest request = ServletActionContext.getRequest();
		List list = environmentBusiness.showHeatPumpSystem();
		request.setAttribute("heatPumpSystemList", list);
		return "heatPumpSystemPage";
	}
	
}

package com.hanqian.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.ReportRateBusiness;
import com.hanqian.business.ReportTypeBusiness;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbReportType;
import com.hanqian.util.RetCode;

public class ReportTemporaryAction {

	private ReportRateBusiness reportRateBusiness;
	private ReportTypeBusiness reportTypeBusiness;

	public String showReportTemporary(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//status 1.动态 2.静态
		//静态的列表
		DbReportType reportType=new DbReportType();
		reportType.setStatus((short)1);
		RetCode staticType=reportTypeBusiness.findREportType(reportType);
		if(staticType.getObj()!=null){
			List<DbReportType> dbReportTypes=(List<DbReportType>) staticType.getObj();
			request.setAttribute("staticType", dbReportTypes);
		}
		
		//动态的列表
		reportType.setStatus((short)2);
		RetCode trendsType=reportTypeBusiness.findREportType(reportType);
		if(trendsType.getObj()!=null){
			List<DbReportType> dbReportTypes=(List<DbReportType>) trendsType.getObj();
			request.setAttribute("trendsType", dbReportTypes);
		}
		return "show";
	}
	public String addTempReport(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String typeidStr=request.getParameter("typeid");
		String[] reportTypes;
		Date startTime=new Date();
		reportTypes=typeidStr.split(",");
		if(reportTypes != null && reportTypes.length >0){
			for(String type : reportTypes){
				DbReportRate rate = new DbReportRate();
				rate.setRate(Short.valueOf("6"));
				rate.setDbReportType(reportTypeBusiness.findById(Integer.parseInt(type)));
				rate.setReportdate(new Date());
				int endTime=new Date().compareTo(startTime)/3600;
				rate.setReporttime(String.valueOf(endTime));
				reportRateBusiness.insertReportRate(rate);				
			}
		}
		return showReportTemporary();
	}
	/**
	 * @return the reportRateBusiness
	 */
	public ReportRateBusiness getReportRateBusiness() {
		return reportRateBusiness;
	}
	/**
	 * @param reportRateBusiness the reportRateBusiness to set
	 */
	public void setReportRateBusiness(ReportRateBusiness reportRateBusiness) {
		this.reportRateBusiness = reportRateBusiness;
	}
	/**
	 * @return the reportTypeBusiness
	 */
	public ReportTypeBusiness getReportTypeBusiness() {
		return reportTypeBusiness;
	}
	/**
	 * @param reportTypeBusiness the reportTypeBusiness to set
	 */
	public void setReportTypeBusiness(ReportTypeBusiness reportTypeBusiness) {
		this.reportTypeBusiness = reportTypeBusiness;
	}

}

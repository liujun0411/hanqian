package com.hanqian.action;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.ReportDetailBusiness;
import com.hanqian.business.ReportTypeBusiness;
import com.hanqian.pojo.DbReportDetail;
import com.hanqian.pojo.DbReportType;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

public class ReportDetailAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(ReportDetailAction.class);
	private ReportDetailBusiness reportDetailBusiness;
	private ReportTypeBusiness reportTypeBusiness;
	private String echoTime; 
	private String echoContent;
	private String echoStatus;
	private Page page;
	private Integer pageSize = 10;
	private Integer currentPage;
	
	public String getEchoTime() {
		return echoTime;
	}
	public void setEchoTime(String echoTime) {
		this.echoTime = echoTime;
	}
	public String getEchoContent() {
		return echoContent;
	}
	public void setEchoContent(String echoContent) {
		this.echoContent = echoContent;
	}
	public String getEchoStatus() {
		return echoStatus;
	}
	public void setEchoStatus(String echoStatus) {
		this.echoStatus = echoStatus;
	}
	// 日志列表
	/**
	 * 1.获取参数(1.上报时间,2.上报内容(是个对象),3.上报状态(是否成功)) 2.列出数据 3.筛选日志
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String showReportDetail() {
		try {
			if(currentPage==null){
	    		currentPage=1;
	    	}
			HttpServletRequest request = ServletActionContext.getRequest();
			String strdate = request.getParameter("strdate");
			String status = request.getParameter("status");
			String typeid = request.getParameter("typeid");
			DbReportDetail reportDateil = new DbReportDetail();
			// 上报日期的判断
			if (!SysUtil.isNull(strdate)) {
				echoTime=strdate;
				// NEW 当前日期对象
				Date dates = new Date();
				int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
						31};
				if ((dates.getYear() % 4 == 0 && dates.getYear() % 100 != 0)
						|| dates.getYear() % 400 == 0) {
					monthDay[1]++;
				}
				if (strdate.equals("1")) {
					Long myTime = ((dates.getTime() / 1000) - 3600 * 24 * 1);
					dates.setTime(myTime * 1000);
					reportDateil.setReporttime(dates);
				} else if (strdate == "2") {
					Long myTime = ((dates.getTime() / 1000) - 3600 * 24 * 7);
					dates.setTime(myTime * 1000);
					reportDateil.setReporttime(dates);
				} else if (strdate == "3") {
					Long myTime = ((dates.getTime() / 1000) - 3600 * 24 * (monthDay[(dates
							.getDay() + 1)]));
					dates.setTime(myTime * 1000);
					reportDateil.setReporttime(dates);
				} else if (strdate == "4") {
					int i = monthDay[(dates.getDay() + 1)];
					int j = monthDay[(dates.getDay())];
					int k = monthDay[(dates.getDay() - 1)];
					Long myTime = ((dates.getTime() / 1000) - 3600 * 24 * (i
							+ j + k));
					dates.setTime(myTime * 1000);
					reportDateil.setReporttime(dates);
				} else if (strdate == "5") {
					int i = monthDay[(dates.getDay() + 1)];
					int j = monthDay[(dates.getDay())];
					int k = monthDay[(dates.getDay() - 1)];
					int x = monthDay[(dates.getDay() - 2)];
					int y = monthDay[(dates.getDay() - 3)];
					int z = monthDay[(dates.getDay() - 4)];
					Long myTime = ((dates.getTime() / 1000) - 3600 * 24 * (i
							+ j + k + x + y + z));
					dates.setTime(myTime * 1000);
					reportDateil.setReporttime(dates);
				}
			}
			if (!SysUtil.isNull(status)) {
					reportDateil.setStatus(Short.parseShort(status));
				echoStatus=status;
			}
			if (!SysUtil.isNull(typeid)) {
				DbReportType dbReportType = new DbReportType();
				dbReportType.setTypeId(Integer.parseInt(typeid));
				reportDateil.setDbReportType(dbReportType);
				echoContent=typeid;
			}
			RetCode reportDateilCode = reportDetailBusiness.findREportRate(
					reportDateil, currentPage, pageSize);
			if (reportDateilCode.getCode() == 0) {
				List<DbReportDetail> reportDetails = (List<DbReportDetail>) reportDateilCode
						.getObj();
				page = reportDateilCode.getPage();
				request.setAttribute("reportDetailList", reportDetails);
				request.setAttribute("page", page);
			}
			RetCode typeCode = reportTypeBusiness.findREportType(new DbReportType());
			if (typeCode.getCode() == 0) {
				List<DbReportType> type = (List<DbReportType>) typeCode
						.getObj();
				request.setAttribute("ReportType", type);
			}

		} catch (Exception e) {
			logg.error("ReportDetailAction-->showReportDetail", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	/**
	 * @return the reportDetailBusiness
	 */
	public ReportDetailBusiness getReportDetailBusiness() {
		return reportDetailBusiness;
	}
	/**
	 * @param reportDetailBusiness the reportDetailBusiness to set
	 */
	public void setReportDetailBusiness(ReportDetailBusiness reportDetailBusiness) {
		this.reportDetailBusiness = reportDetailBusiness;
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
	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	} 

}

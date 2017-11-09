package com.hanqian.action;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.ReportRateBusiness;
import com.hanqian.business.ReportTypeBusiness;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbReportType;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;

public class ReportRateAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(ReportRateAction.class);
	private HttpServletRequest request; 
	private ReportRateBusiness reportRateBusiness;
	private ReportTypeBusiness reportTypeBusiness;
	private Page page;
	private Integer pageSize = 10;
	private Integer currentPage;
//
	/**
	 * 1.获取所有上报类型. 2.修改上报设置.
	 */

	public String showReportRate() {
		//
		 request = ServletActionContext.getRequest();
		// 获取所有的上报类型
		RetCode reporttypeCode = reportTypeBusiness
				.findREportType(new DbReportType());
		if (reporttypeCode.getObj() != null) {
			List<DbReportType> types = (List<DbReportType>) reporttypeCode
					.getObj();
			request.setAttribute("reportTypes", types);
		}
		// 
		RetCode reportRateCode = reportRateBusiness
				.findReportRate(new DbReportRate());
		if (reportRateCode.getObj() != null) {
			List<DbReportRate> reportRates = (List<DbReportRate>) reportRateCode
					.getObj();
			DbReportRate reportRate = reportRates.get(0);
			request.setAttribute("reportRate", reportRate);
		}

		String strip = reportRateBusiness.getReportIp();
		String[] strchar = strip.split(",");
		request.setAttribute("reportIp", strchar);
		return "show";
	}
	
	/**
	 * 
	 * @return
	 */
	public String updateReportRate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取到上报数据对象..
		// 修改数据上报设置成功返回TRUE失败返回FALSE

		String json = request.getParameter("json");
		String typeid = request.getParameter("typeid");
		// 转换成JSON
		JSONObject jsonObject = JSONObject.fromObject(json);
		// 用对象保存数据
		
		// 注意此处还要添加一个医院ID....
//		DbHospitalinfo hospitalinfo = new DbHospitalinfo();
//		DbUsers users = (DbUsers) ServletActionContext.getContext()
//		.getSession().get("user");
//		hospitalinfo.setSequence(users.getDbHospitalinfo().getSequence());
		
		// 设置类型ID
		DbReportType type = new DbReportType();
		type.setTypeId(Integer.parseInt(typeid));
		DbReportRate reportRate=new DbReportRate();
		//reportRate.setDbHospitalinfo(hospitalinfo);		
		reportRate.setDbReportType(type);
		// 设置表本身的数据
		reportRate.setReportip1(jsonObject.getString("reportip1"));
		reportRate.setReporttime(jsonObject.getString("reporttime"));
		reportRate.setRate(Short.parseShort(jsonObject.getString("rate")));
		boolean bool=false;
		try {
			RetCode ReportTypeCode=reportTypeBusiness.findREportType(type);
			if(ReportTypeCode.getCode()==0){
				List<DbReportType> reportTypes= (List<DbReportType>) ReportTypeCode.getObj();
				DbReportType reportType=reportTypes.get(0);
				DbReportRate dbReportRate=new DbReportRate();
				dbReportRate.setDbReportType(reportType);
				RetCode ReportRateCode=reportRateBusiness.findReportRate(dbReportRate);
				
				if(ReportRateCode.getCode()==0){
					List<DbReportRate> dbReportRates= (List<DbReportRate>) ReportRateCode.getObj();
					reportRate.setId(dbReportRates.get(0).getId());
					DbReportRate rate= reportRateBusiness.updateReportRate(reportRate);
					if(rate!=null){
						bool=true;
					}else{
						bool=false;
					}
				}else{
					reportRateBusiness.insertReportRate(reportRate);
				}
				bool=true;
			}
			ServletActionContext.getResponse().getWriter().print(bool);
		} catch (IOException e) {
			logg.error("ReportRateAction-->updateReportRate", e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 查询数据上报--频率列表
	 * @return
	 */
	public String showReportRateAll(){
		 if(currentPage==null){
			 currentPage=1;
		 }
		request = ServletActionContext.getRequest();
		RetCode rateCode=reportRateBusiness.findReportRate(new DbReportRate(),currentPage,pageSize);
		if(rateCode.getObj()!=null){
			List<DbReportRate> dbReportRates=(List<DbReportRate>) rateCode.getObj();
			request.setAttribute("reportRateList", dbReportRates);	
		}
		page = rateCode.getPage();
		return "showAll";
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

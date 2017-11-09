package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbReportDetail;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
@Service
public class ReportDetailBusiness     extends BaseBusiness {
	private static final Logger logg = Logger.getLogger(ReportDetailBusiness.class);
//	@Resource
//	private ReportDetailMgr reportDetailMgr; 
	
	
	private Map setReportDetailMap(DbReportDetail reportDateil) {
		Map map = new HashMap();
		if (reportDateil != null) {
			if (reportDateil.getReporttime() != null) {
				map.put("reporttime", reportDateil.getReporttime());				
			}else{
				map.put("reporttime", null);
			}
			if (reportDateil.getDbReportType() != null) {
				map.put("dbReportType", reportDateil.getDbReportType());				
			}else{
				map.put("dbReportType", null);	
			}
			if (reportDateil.getStatus()!=null) {
				map.put("status", reportDateil.getStatus());					
			}else{
				map.put("status", null);	
			}
			
		}		
		return map;
	}
	
	
	public RetCode findREportRate(DbReportDetail reportDateil, int currentPage,
			int pageSize) {
		//return reportDetailMgr.findREportRate(reportDateil, currentPage, pageSize);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.findMaterial!对象DbReportDetail:"+reportDateil+" currentPage:"+currentPage +" pageSize:"+pageSize  );	
		Map map = new HashMap();
		map = this.setReportDetailMap(reportDateil);
		return this.getPageData("findREportRate", map, currentPage, pageSize);
	}
	
	public Map getFindREportRateSql() {
		return PerformSQLUtil.get("findREportRate", this);
	}
	
	public void insertReportDetail(DbReportDetail detail){
		//reportDetailMgr.insertReportDetail(detail);
		if (logg.isDebugEnabled())
			logg.debug("进入MaterialBusiness.findMaterial!对象DbReportDetail:"+detail);	
		sqlSession.insert("insertReportDetail", detail);
	}
	
	public Map getInsertReportDetailSql() {
		return PerformSQLUtil.get("insertReportDetail", this);
	}
	
	
	

	/**
	 * @return the reportDetailMgr
	 */
//	public ReportDetailMgr getReportDetailMgr() {
//		return reportDetailMgr;
//	}
//	/**
//	 * @param reportDetailMgr the reportDetailMgr to set
//	 */
//	public void setReportDetailMgr(ReportDetailMgr reportDetailMgr) {
//		this.reportDetailMgr = reportDetailMgr;
//	}
}

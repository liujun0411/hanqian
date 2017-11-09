package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbReportRate;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

@Service
public class ReportRateBusiness   extends BaseBusiness  {
	public static final Logger log = Logger.getLogger(ReportRateBusiness.class);
	
//	@Resource
//	private ReportRateMgr reportRateMgr;
	private String reportIp="218.1.21.18";
	/**
	 * @return the reportIp
	 */
	public String getReportIp() {
//		if(StringUtil.isEmpty(reportIp)){
//			this.reportIp = reportRateMgr.getReportIp();
//		}
		return reportIp;
	}
	/**
	 * @param reportIp the reportIp to set
	 */
	public void setReportIp(String reportIp) {
//		if(StringUtil.isNotEmpty(reportIp)){
//			this.reportIp = reportIp;
//		}else{
//			this.reportIp = reportRateMgr.getReportIp();
//		}
		this.reportIp = reportIp;
	}
	/**
	 * 查询所有数据上报类型
	 */
	private Map getReportRateCrit(DbReportRate reportRate) {
		Map map = new HashMap();
		if (reportRate != null && reportRate.getDbReportType() != null) {
//			dc.add(Restrictions
//					.eq("dbReportType", reportRate.getDbReportType()));
			map.put("typeId", reportRate.getDbReportType().getTypeId());
		}		
		return map;
	}
	

	
	public RetCode findReportRate(DbReportRate reportRate,Integer currentPage,Integer pageSize) {
		//return reportRateMgr.findReportRate(reportRate, currentPage, pageSize);
		if (log.isDebugEnabled())
			log.debug("进入ReportRateBusiness.findReportRate!对象DbReportRate:"+reportRate+" currentPage:"+currentPage+" pageSize:"+pageSize);
		Map map = this.getReportRateCrit(reportRate);
		return this.getPageData("findReportRate", map, currentPage, pageSize);
	}
	

	
	public RetCode findReportRate(DbReportRate reportRate) {
		//return reportRateMgr.findReportRate(reportRate);
		if (log.isDebugEnabled())
			log.debug("进入ReportRateBusiness.findReportRate!对象DbReportRate:"+reportRate);
		Map map = this.getReportRateCrit(reportRate);
		return this.getData("findReportRate", map);
	}
	
	public Map getFindReportRateSql() {
		return PerformSQLUtil.get("findReportRate", this);
	}
	/**
	 * 修改数据上报类型
	 * 
	 * @param reportRate
	 * @return
	 */
	public DbReportRate updateReportRate(DbReportRate reportRate) {
		//return reportRateMgr.updateReportRate(reportRate);
		if (log.isDebugEnabled())
			log.debug("进入ReportRateBusiness.updateReportRate!对象DbReportRate:"+reportRate);
		sqlSession.update("updateReportRate", reportRate);
		return reportRate;
	}
	
	public Map getUpdateReportRateSql() {
		return PerformSQLUtil.get("updateReportRate", this);
	}
	
	
	
	// 判断上报类型是否存在数据上报设置里面
	public void insertReportRate(DbReportRate reportRate) {
		//reportRateMgr.insertReportRate(reportRate);
		if (log.isDebugEnabled())
			log.debug("进入ReportRateBusiness.updateReportRate!对象DbReportRate:"+reportRate);
		sqlSession.insert("insertReportRate",reportRate);
	}
	
	public Map getInsertReportRateSql() {
		return PerformSQLUtil.get("insertReportRate", this);
	}

	

	public RetCode findReportRatesql(DbReportRate reportRate) {
		//return reportRateMgr.findReportRatesql(reportRate);
		RetCode rc = new RetCode();
		if (log.isDebugEnabled())
			log.debug("进入ReportRateBusiness.findReportRatesql!对象DbReportRate:"+reportRate);
		
		if (reportRate != null) {
			if (reportRate.getDbReportType().getStatus() !=null) {
//				sql = "select type_id,reportdate from db_report_rate where type_id in(select type_id from db_report_type where status="
//						+ reportRate.getDbReportType().getStatus() + ")";
				Map map =new HashMap();
				map.put("status", reportRate.getDbReportType().getStatus());
				rc = this.getData("findReportRatesqlStatus", map);
				
			}
			if (reportRate.getDbReportType().getTypeId()!=null) {
//				sql = "select type_id,reportdate from db_report_rate where type_id ="
//						+ reportRate.getDbReportType().getTypeId();
				Map map =new HashMap();
				map.put("typeId", reportRate.getDbReportType().getTypeId());
				rc = this.getData("findReportRatesqlTypeId", map);
			}
		}
		
		
		return rc;
	}
	
	public Map getFindReportRatesqlSql() {
		return PerformSQLUtil.get("findReportRatesqlTypeId", this);
	}
	/**
	 * @return the reportRateMgr
	 */
//	public ReportRateMgr getReportRateMgr() {
//		return reportRateMgr;
//	}
//	/**
//	 * @param reportRateMgr the reportRateMgr to set
//	 */
//	public void setReportRateMgr(ReportRateMgr reportRateMgr) {
//		this.reportRateMgr = reportRateMgr;
//	}
}

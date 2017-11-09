package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbReportType;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
@Service
public class ReportTypeBusiness     extends BaseBusiness {
	private static final Logger logg = Logger.getLogger(ReportTypeBusiness.class);
//	@Resource
//	private ReportTypeMgr reportTypeMgr;
	
	
	private Map setReportTypeMap(DbReportType reportType) {
		Map map = new HashMap();

		if (reportType != null) {
			if (reportType.getTypeId() != null) {
				map.put("typeId", reportType.getTypeId());				
			}else{
				map.put("typeId", null);
			}
			if(null != reportType.getStatus()){
				map.put("status",  reportType.getStatus());					
			}else{
				map.put("status",  null);	
			}
		}
	
		return map;
	}
	

	public RetCode findREportType(DbReportType reportType) {
		//return reportTypeMgr.findREportType(reportType);
		if (logg.isDebugEnabled())
			logg.debug("进入ReportTypeBusiness.findMaterial!对象DbReportType:"+reportType);	
		Map map = new HashMap();
		map= this.setReportTypeMap(reportType);
		return this.getData("findREportType", map);
	}
	
	public Map getFindREportTypeSql() {
		return PerformSQLUtil.get("findREportType", this);
	}
	
	public DbReportType findById(Integer id){
		//return reportTypeMgr.findById(id);
		if (logg.isDebugEnabled())
			logg.debug("进入ReportTypeBusiness.findById!参数id:"+id);	
		DbReportType dbReportType = sqlSession.selectOne("findByIdDbReportType", id);
		return dbReportType;
	}
	
	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findByIdDbReportType", this);
	}

	/**
	 * @return the reportTypeMgr
	 */
//	public ReportTypeMgr getReportTypeMgr() {
//		return reportTypeMgr;
//	}
//
//	/**
//	 * @param reportTypeMgr the reportTypeMgr to set
//	 */
//	public void setReportTypeMgr(ReportTypeMgr reportTypeMgr) {
//		this.reportTypeMgr = reportTypeMgr;
//	}
}

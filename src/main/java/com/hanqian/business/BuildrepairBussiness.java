package com.hanqian.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBuildingRepair;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

@Service("BuildRepairBusiness")
public class BuildrepairBussiness extends BaseBusiness {
	private static final Logger log = Logger
			.getLogger(BuildrepairBussiness.class);

	public RetCode findBuildingRepair(DbBuildingRepair repair) {
		// return repairMgr.findBuildingRepair(repair);
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.findBuildingRepair!对象DbBuildingRepair:"
					+ repair);
		Map map = new HashMap();
		if (repair != null) {
			if (repair.getDbBuilding() != null) {
				map.put("buildingId", repair.getDbBuilding().getBuildingId());
			} else {
				map.put("buildingId", null);
			}
			if (repair.getSeq() != null) {
				map.put("seq", repair.getSeq());
			} else {
				map.put("seq", null);
			}
			if (repair.getOpertime() != null) {
				map.put("opertime", repair.getOpertime());
			} else {
				map.put("opertime", null);
			}

		} else {
			map.put("buildingId", null);
			map.put("seq", null);
			map.put("opertime", null);
		}

		return this.getData("findBuildingRepair", map);
	}

	public Map getFindBuildingRepairSql() {
		return PerformSQLUtil.get("findBuildingRepair", this);
	}

	public RetCode findBuildingRe(DbBuildingRepair repair) {
		// return repairMgr.findBuildingRe(repair);
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.findBuildingRe!对象DbBuildingRepair:"
					+ repair);
		return this.getData("findBuildingRe", null);
	}

	public Map getFindBuildingReSql() {
		return PerformSQLUtil.get("findBuildingRe", this);
	}

	public DbBuildingRepair findById(int id) {
		// return repairMgr.findById(id);
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.findById!参数id:" + id);
		DbBuildingRepair dbBuildingRepair = sqlSession.selectOne(
				"findByIdDbBuildingRepair", id);

		return dbBuildingRepair;
	}

	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findByIdDbBuildingRepair", this);
	}

	/**
	 * 分页查找维修信息
	 * 
	 * @param repair
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public RetCode findBuildingRepair(DbBuildingRepair dbBuildingRepair,
			String beginDate, String endDate, int currentPage, int pageSize) {
		// return repairMgr.findBuildingRepair(dbBuildingRepair, beginDate,
		// endDate, currentPage, pageSize);
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.findBuildingRepair!对象DbBuildingRepair:"
					+ dbBuildingRepair);
		Map map = new HashMap();

		if (dbBuildingRepair != null) {
			if (dbBuildingRepair.getDbBuilding().getBuildingId() != null
					&& dbBuildingRepair.getDbBuilding().getBuildingId() != 0) {

				map.put("buildingId", dbBuildingRepair.getDbBuilding()
						.getBuildingId());
			} else {
				map.put("buildingId", null);
			}
			if (beginDate != null && beginDate != "") {
				map.put("beginDate", beginDate);
			} else {
				map.put("beginDate", null);
			}
			if (endDate != null && endDate != "") {
				map.put("endDate", endDate);
			} else {
				map.put("endDate", null);
			}
			if (beginDate != null && endDate != null && beginDate != ""
					&& endDate != "") {
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
			}
		}
		return this.getPageData("findBuildingRepairPage", map, currentPage, pageSize);

	}

	public Map getFindBuildingRepairByPageSql() {
		return PerformSQLUtil.get("findBuildingRepairPage", this);
	}

	public boolean updateBuildingRepair(DbBuildingRepair repair) {
		// return repairMgr.updateBuildingRepair(repair);
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.updateBuildingRepair!对象DbBuildingRepair:"
					+ repair);
		boolean bool = true;
		try {
			sqlSession.update("updateBuildingRepair", repair);
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public Map getUpdateBuildingRepairSql() {
		return PerformSQLUtil.get("updateBuildingRepair", this);
	}

	public boolean insertBuildingRepair(DbBuildingRepair repair) {
		// return repairMgr.insertBuildingRepair(repair);
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.insertBuildingRepair!对象DbBuildingRepair:"
					+ repair);
		boolean bool = true;
		try {
			sqlSession.insert("insertBuildingRepair", repair);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getInsertBuildingRepairSql() {
		return PerformSQLUtil.get("insertBuildingRepair", this);
	}

	/**
	 * 删除楼宇维修信息
	 * */
	public int deleteBuildingRepair(Integer id) {
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.deleteBuildingRepair!参数id:" + id);
		return sqlSession.update("deleteBuildingRepair", id);
	}

	public Map getDeleteBuildingRepairSql() {
		return PerformSQLUtil.get("deleteBuildingRepair", this);
	}

	/**
	 * 数据上报
	 * */
	public RetCode reportBuildRepair(Date lastTime) {
		// return repairMgr.reportBuildRepair(lastTime);
		if (log.isDebugEnabled())
			log.debug("进入BuildrepairBussiness.reportBuildRepair!参数Date:"
					+ lastTime);
		Map map = new HashMap();
		map.put("lastTime", lastTime);
		return this.getData("reportBuildRepair", map);
	}

	public Map getReportBuildRepairSql() {
		return PerformSQLUtil.get("reportBuildRepair", this);
	}
}

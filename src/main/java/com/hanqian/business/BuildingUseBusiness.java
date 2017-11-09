package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 楼宇用途
 * 
 * @author LG
 *
 */
@Service
public class BuildingUseBusiness extends BaseBusiness {
	public static final Logger log = Logger.getLogger("BuildingUseBusiness");

	/**
	 * 获取用途列表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public RetCode findBuildingUse() throws Exception {
		// return buildingUseMgr.findBuilndingUse();
		if (log.isDebugEnabled())
			log.debug("进入BuildingUseBusiness.findBuildingUse!");
		Map map = new HashMap();
		map.put("hospArea", SysUtil.BASE_COMM_HOSP_AREA);
		return this.getData("findBuilndingUse", map);
	}

	public Map getFindBuildingUseSql() {
		return PerformSQLUtil.get("findBuilndingUse", this);
	}

	/**
	 * 获取单体楼宇面积信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public RetCode findBuildingArea() throws Exception {
		// return buildingUseMgr.findBuildingArea();
		if (log.isDebugEnabled())
			log.debug("进入BuildingUseBusiness.findBuildingArea!");
		return this.getData("findBuildingArea", null);
	}

	public Map getFindBuildingAreaSql() {
		return PerformSQLUtil.get("findBuildingArea", this);
	}

	/**
	 * 获取单体楼用途面积
	 * 
	 * @return
	 * @throws Exception
	 */
	public RetCode findBuildUseArea() throws Exception {
		// return buildingUseMgr.findBuildUseArea();
		if (log.isDebugEnabled())
			log.debug("进入BuildingUseBusiness.findBuildUseArea!");
		return this.getData("findBuildUseArea", null);
	}

	public Map getFindBuildUseAreaSql() {
		return PerformSQLUtil.get("findBuildUseArea", this);
	}
}

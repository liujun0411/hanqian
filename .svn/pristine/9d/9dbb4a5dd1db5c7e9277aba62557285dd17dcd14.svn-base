package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.form.VOMaintenance;
import com.hanqian.pojo.DbMaintenance;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service("equipMaintenanceBusiness")
public class EquipMaintenanceBusiness  extends BaseBusiness{
	private static final Logger log = Logger.getLogger(EquipMaintenanceBusiness.class);
	/**
	 * 根据设备ID查询当前设备的所有维护信息
	 * 
	 * @param equipId
	 * @return
	 */
	public RetCode findAllMaintenance(DbMaintenance dbMaintenance,
			int currentPage, int pageSize) {
		//return equipMaintenanceMgr.findAllMaintenance(dbMaintenance,currentPage, pageSize);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findAllMaintenance,参数DbMaintenance:"
					+ dbMaintenance);	
		Map map = new HashMap();
		map.put("equipId", dbMaintenance.getDbEquipList().getEquipId());
		return this.getPageData("findAllMaintenance", map, currentPage, pageSize);
				
	}
	public Map getFindAllMaintenanceSql() {
		return PerformSQLUtil.get("findAllMaintenance", this);
	}

	/**
	 * 添加设备维护信息
	 * 
	 * @param maintenance
	 * @return
	 */
	public boolean insertMaintenance(DbMaintenance maintenance) {
		//return equipMaintenanceMgr.insertMaintenance(maintenance);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.insertMaintenance,参数DbMaintenance:"
					+ maintenance);	
		boolean bool = true;
		try {
			sqlSession.insert("insertMaintenance", maintenance);
		} catch (Exception e) {
			bool = false;
		}
		
		return bool;
	}
	
	public Map getInsertMaintenanceSql() {
		return PerformSQLUtil.get("insertMaintenance", this);
	}

	/**
	 * 根据维护信息列表
	 * 
	 * @param dbMaintenance
	 * @param 当前页，每页个数，开始时间，结束时间
	 * @return
	 */
	public RetCode findMaintenance(VOMaintenance maintenance,
			int currentPage, int pageSize) {
		//return equipMaintenanceMgr.findMaintenance(dbMaintenance, currentPage,pageSize);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findMaintenance,参数VOMaintenance:"
					+ maintenance);	
		Map map = new HashMap();
		
		if (!SysUtil.isNullObject(maintenance)) {
			// 楼名
			if (!SysUtil.isNull(maintenance.getBuildName())) {
				if (!"0".equals(maintenance.getBuildName())) {
					map.put("buildName", maintenance.getBuildName());
				}else{
					map.put("buildName", null);
				}
			}else{
				map.put("buildName", null);
			}
			// 设备名称
			if (!SysUtil.isNull(maintenance.getEqName())) {
				map.put("eqName", maintenance.getEqName());				
			}else{
				map.put("eqName", null);		
			}
			// 楼层
			if (!SysUtil.isNull(maintenance.getStorey())) {
				map.put("storey", maintenance.getStorey());				
			}else{
				map.put("storey", null);			
			}
			// 楼宇曾用名
			if (!SysUtil.isNull(maintenance.getHisName())) {
				map.put("hisName", maintenance.getHisName());				
			}else{
				map.put("hisName", null);
			}
			// 安装位置
			if (!SysUtil.isNull(maintenance.getPlace())) {
				map.put("place", maintenance.getPlace());					
			}else{
				map.put("place",null);	
			}
			
			// 设备类型
			if (!SysUtil.isNull(maintenance.getEqTypeId())) {
				map.put("eqTypeId", maintenance.getEqTypeId());				
			}else{
				map.put("eqTypeId", null);		
			}
			// 型号
			if (!SysUtil.isNull(maintenance.getUnitType())) {
				map.put("unitType", maintenance.getUnitType());				
			}else{
				map.put("unitType",null);		
			}
			// 维护时间起
			if (!SysUtil.isNull(maintenance.getServiceDateStart())) {
				map.put("serviceDateStart", maintenance.getServiceDateStart()+" 00:00:00");				
			}else{
				map.put("serviceDateStart",null);		
			}
			// 结束
			if (!SysUtil.isNull(maintenance.getServiceDateEnd())) {
				map.put("serviceDateEnd", maintenance.getServiceDateEnd()+" 00:00:00");						
			}else{
				map.put("serviceDateEnd", null);	
			}
			// 维护类型
			if (!SysUtil.isNull(maintenance.getMaintenancetype())) {
				map.put("maintenancetype", maintenance.getMaintenancetype());						
			}else{
				map.put("maintenancetype", null);	
			}
		}else{
			map.put("buildName", null);
			map.put("eqName", null);
			map.put("storey", null);
			map.put("hisName", null);
			map.put("place",null);	
			map.put("eqTypeId", null);	
			map.put("unitType",null);
			map.put("serviceDateStart",null);
			map.put("serviceDateEnd", null);
			map.put("maintenancetype", null);
		}
		
		return this.getPageData("findMaintenance", map, currentPage, pageSize);
	}
	
	public Map getFindMaintenanceSql() {
		return PerformSQLUtil.get("findMaintenance", this);
	}
	
	/**
	 * 根据保养id查找保养信息
	 * 
	 * @param mid
	 * @return
	 */
	public DbMaintenance findMaintenanceById(int mid) {
		//return equipMaintenanceMgr.findMaintenanceById(mid);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.findMaintenanceById,参数mid:"
					+ mid);	
		DbMaintenance dbMaintenance = sqlSession.selectOne("findMaintenanceById", mid);
		return dbMaintenance;
	}
	
	public Map getFindMaintenanceByIdSql() {
		return PerformSQLUtil.get("findMaintenanceById", this);
	}
	
	/**
	 * 修改保养记录
	 * 
	 * @param maintenance
	 * @return boolean
	 */
	public boolean updateMaintenance(DbMaintenance maintenance){
		//return equipMaintenanceMgr.updateMaintenance(maintenance);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.updateMaintenance,参数maintenance:"
					+ maintenance);	
		boolean bool= true;
		try {
			sqlSession.update("updateMaintenance", maintenance);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}
	
	public Map getUpdateMaintenanceSql() {
		return PerformSQLUtil.get("updateMaintenance", this);
	}
	
	/**
	 * 删除保养记录
	 * 
	 * @param mid
	 * @return boolean
	 */
	public boolean deleteMaintenance(int mid){
		//return equipMaintenanceMgr.deleteMaintenance(mid);
		if (log.isDebugEnabled())
			log.debug("进入EquipMaintenanceBusiness.deleteMaintenance,参数mid:"
					+ mid);	
		boolean bool= true;
		try {
			sqlSession.delete("deleteMaintenance", mid);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}
	
	public Map getDeleteMaintenanceSql() {
		return PerformSQLUtil.get("deleteMaintenance", this);
	}
}

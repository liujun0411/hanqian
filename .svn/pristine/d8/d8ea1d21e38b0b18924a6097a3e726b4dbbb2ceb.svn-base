package com.hanqian.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 李江
 * @version 1.4 2012-9-6
 * @see
 */
@Service
public class BuildingBusiness extends BaseBusiness {
	private static final Logger logg = Logger.getLogger(BuildingBusiness.class);

	public List findAll() {

		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findAll!");
		return sqlSession.selectList("findAllBuilding");
	}

	public Map getFindAllSql() {
		return PerformSQLUtil.get("findAllBuilding", this);
	}

	public DbBuilding findById(Integer buildingId) {
		// return buildMgr.findById(buildingId);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findById!参数buildingId:" + buildingId);
		return sqlSession.selectOne("findByIdDbBUILDING", buildingId);
	}

	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findByIdDbBUILDING", this);
	}

	/**
	 * 根据楼宇ID查询楼宇信息
	 * 
	 * @param statement
	 * @param buildingId
	 * @return building
	 */
	public DbBuilding findBuildingById(DbBuilding building) {
		// return buildMgr.findBuildingById(building.getBuildingId());
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findBuildingById!对象DbBuilding:"
					+ building);
		int buildingId = building.getBuildingId();
		return sqlSession.selectOne("findBuildingById", buildingId);
	}

	public Map getFindBuildingByIdSql() {
		return PerformSQLUtil.get("findBuildingById", this);
	}

	/**
	 * 加载楼宇信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findDBBuilding(DbBuilding building) {
		// return buildMgr.findBuilding(building);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findDBBuilding!对象DbBuilding:"
					+ building);
		Map map = new HashMap();
		map.put("buildingId", building.getBuildingId());

		if (building.getDbHospInfo() != null) {
			if (building.getDbHospInfo().getSeqHosp() != null) {
				map.put("hospId", building.getDbHospInfo().getSeqHosp());
			} else {
				map.put("hospId", null);
			}
		} else {
			map.put("hospId", null);
		}
		map.put("buildingName", building.getBuildingName());
		if (null == building.getDbBaseComm()) {
			map.put("status", null);
		} else {
			if (null == building.getDbBaseComm().getDbBaseType()) {
				map.put("status", null);
			} else {
				map.put("status", building.getDbBaseComm().getDbBaseType()
						.getSeq());
			}
		}

		return this.getData("findDBBuilding", map);
	}

	public Map getFindDBBuildingSql() {
		return PerformSQLUtil.get("findDBBuilding", this);
	}

	/**
	 * 根据SQL 楼宇的所有名称与id
	 * 
	 * @param statement
	 * @param
	 * @return 返回结果集
	 */

	public RetCode findBuildingBySql() {

		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findBuildingBySql!");
		return this.getData("findBuildingBySql", null);
	}

	public Map getFindBuildingBySqlSql() {
		return PerformSQLUtil.get("findBuildingBySql", this);
	}

	/**
	 * 根据楼宇编号获取楼宇层数
	 * 
	 * @param statement
	 * @param
	 * @return 返回结果集
	 */
	public RetCode findBuildingStroey(int buildingId) {

		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findBuildingStroey!参数buildingId:"
					+ buildingId);
		Map map = new HashMap();
		map.put("buildingId", buildingId);
		return this.getData("findBuildingStroey", map);
	}

	public Map getFindBuildingStroeySql() {
		return PerformSQLUtil.get("findBuildingStroey", this);
	}

	/**
	 * 根据医院Id查询楼宇 sequence楼宇Id,name楼宇名称
	 * 
	 * @param hospId
	 * @return
	 */
	public RetCode findBuildNameAndSequence(Integer hospId) {
		// return buildMgr.findBuildNameAndSequence(hospId);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findBuildNameAndSequence!参数hospId:"
					+ hospId);
		Map map = new HashMap();
		map.put("hospId", hospId);
		return this.getData("findBuildNameAndSequenceBuildingBusiness", map);
	}

	public Map getFindBuildNameAndSequenceSql() {
		return PerformSQLUtil.get("findBuildNameAndSequenceBuildingBusiness",
				this);
	}

	/**
	 * 查询楼宇信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findBuilding(DbBuilding building, int currentPage,
			int pageSize) {
		// return buildMgr.findDbBuilding(building, currentPage, pageSize);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findBuilding!对象DbBuilding:"
					+ building);
		Map map = new HashMap();

		if (null == building.getDbBaseComm()) {
			map.put("buildstatus", null);
		} else {
			if (null == building.getDbBaseComm().getSeq()) {
				map.put("buildstatus", null);
			} else {
				map.put("buildstatus", building.getDbBaseComm().getSeq());
			}
		}

		map.put("buildingName", building.getBuildingName());
		map.put("hisName", building.getHisName());
		map.put("structure", building.getStructure());
		return this.getPageData("findBuilding", map, currentPage, pageSize);
	}

	public Map getFindBuildingSql() {
		return PerformSQLUtil.get("findBuilding", this);
	}

	/**
	 * 添加楼宇信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public void insertObj(Object obj) {
		// buildMgr.insertObj(obj);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.insertObj,对象obj:" + obj);
		if (obj instanceof DbBuilding) {
			sqlSession.insert("insertDbBuilding", obj);
		}
	}

	public Map getInsertDbBuildingSql() {
		return PerformSQLUtil.get("insertDbBuilding", this);
	}

	/**
	 * 删除楼宇信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public void deleteBuilding(DbBuilding building) {
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.deleteBuilding,对象DbBuilding:"
					+ building);
		// buildMgr.deleteBuilding(building);
		// building.setStatus(1);
		this.updateBuilding(building);
	}

	public Map getDeleteBuildingSql() {
		return PerformSQLUtil.get("updateBuilding", this);
	}

	/**
	 * 修改楼宇信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public void updateBuilding(DbBuilding building) {
		// buildMgr.updateBuilding(building);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.updateBuilding,对象DbBuilding:"
					+ building);
		sqlSession.update("updateBuilding", building);
	}
	/**
	 * 删除大修信息减去一次大修次数
	 * @param building
	 */
	public void updateBuildingForMendNum(DbBuilding building) {
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.updateBuildingForMendNum,对象DbBuilding:"	+ building);
		sqlSession.update("updateBuildingForMendNum", building);
	}
	/**
	 * 计算大修次数
	 * @param building
	 */
	public void updateBuildingForMendNumPlus(DbBuilding building) {
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.updateBuildingForMendNumPlus,对象DbBuilding:"	+ building);
		sqlSession.update("updateBuildingForMendNumPlus", building);
	}

	public Map getUpdateBuildingSql() {
		return PerformSQLUtil.get("updateBuilding", this);
	}

	/**
	 * 根据设备类型，获得设备类型所在的楼宇(jkj)
	 * 
	 * @param dbEquipList
	 * @return
	 */
	public RetCode findBuildByParam(DbEquipList dbEquipList) {
		// return buildMgr.findBuildByParam(dbEquipList);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.updateBuilding,对象dbEquipList:"
					+ dbEquipList);
		Map map = new HashMap();
		map.put("equipTypeId", dbEquipList.getDbEquipType().getEquipTypeId());
		return this.getData("findBuildByParam", map);
	}

	public Map getFindBuildByParamSql() {
		return PerformSQLUtil.get("findBuildByParam", this);
	}

	public RetCode findBuildByEquipId(String equipId) {
		// return buildMgr.findBuildByEquipId(equipId);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.updateBuilding,参数equipId:" + equipId);
		Map map = new HashMap();
		map.put("equipId", equipId);
		return this.getData("findBuildByEquipId", map);
	}

	public Map getFindBuildByEquipIdSql() {
		return PerformSQLUtil.get("findBuildByEquipId", this);
	}

	public RetCode reportBuilding(Date lastTime) {
		// return buildMgr.reportBuilding(lastTime);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.reportBuilding,参数lastTime:"
					+ lastTime);
		Map map = new HashMap();
		map.put("lastTime", lastTime);
		return this.getData("reportBuilding", map);
	}

	public Map getReportBuildingSql() {
		return PerformSQLUtil.get("reportBuilding", this);
	}

}

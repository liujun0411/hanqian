package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbBaseType;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 刘新
 * @version 1.4 2012-9-14
 * @see
 */
@Service
public class BaseCommBusiness extends BaseBusiness {
	private static final Logger log = Logger.getLogger(BaseCommBusiness.class);

	public RetCode findArea(DbBaseComm baseComm) {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findArea,参数DbBaseComm:" + baseComm);
		return this.findBaseComm(baseComm);
	}

	public Map getFindAreaSql() {
		return PerformSQLUtil.get("findBaseComm", this);
	}

	public RetCode findBaseComm(DbBaseComm baseComm) {
		RetCode rt = new RetCode(1001, "查无数据！", "查无数据！");
		Map mapvalue = new HashMap();
		try {

			// 查询医院级别时
			if (baseComm.getDbBaseType().getSeq() == SysUtil
					.toInt(SysUtil.BASE_COMM_HOSP_LEVELS)) {

				mapvalue.put("hosplevels", "ture");
			}
			// 查询医院类型
			if (baseComm.getDbBaseType().getSeq() == SysUtil
					.toInt(SysUtil.BASE_COMM_HOSP_TYPE)) {

				mapvalue.put("hosptype", "ture");
			}
			// 查询医院编码
			if (baseComm.getDbBaseType().getSeq() == SysUtil
					.toInt(SysUtil.BASE_COMM_HOSP_CODE)) {

				mapvalue.put("hospcode", "ture");
			}
			// 查询医院区域
			if (baseComm.getDbBaseType().getSeq() == SysUtil
					.toInt(SysUtil.BASE_COMM_HOSP_DIST)) {

				mapvalue.put("hospdist", "ture");
			}
			if (baseComm.getDbBaseType().getSeq() == SysUtil
					.toInt(SysUtil.BASE_COMM_HOSP_AREA)) {

				mapvalue.put("hosparea", "ture");
			}

			rt = this.getData("findBaseComm", mapvalue);
		} catch (Exception e) {
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			rt.setDetail("操作失败！");
			log.error("BaseCommBusiness-->findAreaName", e);
			rt.setObj(null);
		}
		return rt;
	}

	/**
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findBuildUsetype(DbBaseComm baseComm) {
		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findBuildUsetype,参数DbBaseComm:"
					+ baseComm);
		return this.getData("findByExampleDbBaseComm", null);

	}

	public Map getFindBuildUsetypeSql() {
		return PerformSQLUtil.get("findByExampleDbBaseComm", this);
	}

	/**
	 * 查询医院相关的外键列
	 * 
	 * @param statement
	 * @param logisticsNews
	 * @return
	 */
	public List<DbBaseComm> findHospRelated(String seq) {
		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findHospRelated,参数seq:" + seq);
		List<DbBaseComm> list = null;
		// 公用类
		DbBaseComm baseComm = new DbBaseComm();
		// 公用类的字段类型
		DbBaseType dbBaseType = new DbBaseType();
		dbBaseType.setSeq(SysUtil.toInt(seq));
		baseComm.setDbBaseType(dbBaseType);
		// 查询

		RetCode rt = this.findBaseComm(baseComm);
		if (rt.getObj() != null) {
			// 医院列表
			list = (List<DbBaseComm>) rt.getObj();
		}
		return list;
	}

	public Map getFindHospRelatedSql() {
		return PerformSQLUtil.get("findBaseComm", this);
	}

	public RetCode findAreaId() {
		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findAreaId,参数BASE_COMM_HOSP_AREA:"
					+ SysUtil.BASE_COMM_HOSP_AREA);
		Map map = new HashMap();
		map.put("hospArea", SysUtil.BASE_COMM_HOSP_AREA);
		return this.getData("findAreaId", map);

	}

	public Map getFindAreaIdSql() {
		return PerformSQLUtil.get("findAreaId", this);
	}

	public RetCode findAreaName() {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findAreaId,参数BASE_COMM_HOSP_AREA:"
					+ SysUtil.BASE_COMM_HOSP_AREA);
		Map map = new HashMap();
		map.put("hospArea", SysUtil.BASE_COMM_HOSP_AREA);
		return this.getData("findAreaName", map);
	}

	public Map getFindAreaNameSql() {
		return PerformSQLUtil.get("findAreaName", this);
	}

	/**
	 * 查询所有建筑状态
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findBuildingStatus() {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findBuildingStatus!");

		return this.getData("findBuildingStatus", null);
	}

	public Map getFindBuildingStatusSql() {
		return PerformSQLUtil.get("findBuildingStatus", this);
	}

	/**
	 * 获得所有基础数据
	 * 
	 * @param 2013-12-20
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findAllBaseData(String seq, int currentPage, int pageSize) {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findAllBaseData!参数seq:" + seq);
		Map param = new HashMap();
		param.put("seq", seq);
		return this
				.getPageData("findAllBaseData", param, currentPage, pageSize);
	}

	public Map getFindAllBaseDataSql() {
		return PerformSQLUtil.get("findAllBaseData", this);
	}

	public RetCode findAllBaseType() {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.findAllBaseType!");

		return this.getData("findAllBaseType", null);
	}

	public Map getFindAllBaseTypeSql() {
		return PerformSQLUtil.get("findAllBaseType", this);
	}

	public boolean addBaseData(Map map) {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.addBaseData!参数map:" + map);
		boolean bool = true;
		try {
			sqlSession.insert("addBaseData", map);

		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getAddBaseDataSql() {
		return PerformSQLUtil.get("addBaseData", this);
	}

	public boolean updateBaseData(Map map) {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.updateBaseData!参数map:" + map);
		boolean bool = true;
		try {
			sqlSession.update("updateBaseData", map);

		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getUpdateBaseDataSql() {
		return PerformSQLUtil.get("updateBaseData", this);
	}

	public boolean deleteBaseData(int id) {

		if (log.isDebugEnabled())
			log.debug("进入BaseCommBusiness.deleteBaseData!参数id:" + id);
		boolean bool = true;
		try {
			Map map = new HashMap();
			map.put("id", id);
			sqlSession.delete("deleteBaseData", map);

		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getDeleteBaseDataSql() {
		return PerformSQLUtil.get("deleteBaseData", this);
	}

}

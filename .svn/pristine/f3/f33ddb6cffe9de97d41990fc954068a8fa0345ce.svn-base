package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBuildMater;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author
 * @version 1.4 2012-9-10
 * @see
 */
@Service("buildMaterBusiness")
public class BuildMaterBusiness1 extends BaseBusiness {
	private static final Logger logg = Logger
			.getLogger(BuildMaterBusiness1.class);

	/**
	 * 获取楼宇结构材料信息
	 * */
	public RetCode findBuildingMater(DbBuildMater mater) {
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findAll!对象mater:" + mater);
		// return buildMaterMgr.findBuildingMater(mater);
		Map map = new HashMap();
		map.put("materId", mater.getMaterId());
		return this.getData("findBuildingMater", map);
	}

	public Map getFindBuildingMaterSql() {
		return PerformSQLUtil.get("findBuildingMater", this);
	}

	/**
	 * 获取楼宇用途
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findBuildUseType(DbBuildMater buildMater) {
		// return buildMaterMgr.findBuildUsetype(buildMater);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findBuildUseType!对象buildMater:"
					+ buildMater);
		Map map = new HashMap();
		map.put("materId", buildMater.getMaterId());
		return this.getData("findBuildUseType", map);
	}

	public Map getFindBuildUseTypeSql() {
		return PerformSQLUtil.get("findBuildUseType", this);
	}

	/**
	 * 根据父类ID查询楼宇结构
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findAllBuildMater(int materId) {
		// return buildMaterMgr.findAllBuildMater(materId);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findAllBuildMater!参数materId:"
					+ materId);
		Map map = new HashMap();
		map.put("materId", materId);
		return this.getData("findAllBuildMater", map);

	}

	public Map getFindAllBuildMaterSql() {
		return PerformSQLUtil.get("findAllBuildMater", this);
	}

	public DbBuildMater findById(int materId) {
		// return buildMaterMgr.findById(materId);
		if (logg.isDebugEnabled())
			logg.debug("进入BuildingBusiness.findById!参数materId:" + materId);
		Map map = new HashMap();
		map.put("materId", materId);
		DbBuildMater dbBuildMater = sqlSession.selectOne(
				"findByIdBuildMaterBusiness1", map);
		return dbBuildMater;
	}

	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findByIdBuildMaterBusiness1", this);
	}

}

package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBuildingStoreyHis;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 刘新
 * @version 1.4 2012-9-27
 * @see
 */
@Service("buildStoreyHisBusiness")
public class DBBuildingStoreyHisBusiness extends BaseBusiness {

	public static final Logger log = Logger
			.getLogger(DBBuildingStoreyHisBusiness.class);

	public List find(int buildingId, int storey, int areaId) {
		// return storeyHisMgr.find(buildingId, storey, areaId);
		if (log.isDebugEnabled())
			log.debug("进入DBBuildingStoreyHisBusiness.find!参数buildingId:"
					+ buildingId + " storey:" + storey + " areaId:" + areaId);
		Map map = new HashMap();
		map.put("buildingId", buildingId);
		map.put("storey", storey);
		map.put("areaId", areaId);
		RetCode rt = this.getData("findDBBuildingStoreyHis", map);
		List list = null;
		if (rt != null && rt.getObj() != null) {
			list = (List) rt.getObj();
		}
		return list;
	}

	public Map getFindSql() {
		return PerformSQLUtil.get("findDBBuildingStoreyHis", this);
	}

	public DbBuildingStoreyHis findBySeq(int seq) {
		// return storeyHisMgr.findBySeq(seq);
		if (log.isDebugEnabled())
			log.debug("进入DBBuildingStoreyHisBusiness.findBySeq!参数seq:" + seq);
		DbBuildingStoreyHis dbBuildingStoreyHis = sqlSession.selectOne(
				"findBySeq", seq);
		return dbBuildingStoreyHis;
	}

	public Map getFindBySeqSql() {
		return PerformSQLUtil.get("findBySeq", this);
	}

	/**
	 * 添加楼层历史记录
	 * 
	 * @param storeyHis
	 */
	public void insert(DbBuildingStoreyHis storeyHis) {
		// storeyHisMgr.insert(storeyHis);
		if (log.isDebugEnabled())
			log.debug("进入DBBuildingStoreyHisBusiness.insert!参数DbBuildingStoreyHis:"
					+ storeyHis);
		sqlSession.insert("insertDBBuildingStoreyHis", storeyHis);

	}

	public Map getInsertSql() {
		return PerformSQLUtil.get("insertDBBuildingStoreyHis", this);
	}

	/**
	 * 修改楼层历史记录
	 * 
	 * @param storeyHis
	 * @return
	 */
	public DbBuildingStoreyHis update(DbBuildingStoreyHis storeyHis) {
		// return storeyHisMgr.update(storeyHis);
		if (log.isDebugEnabled())
			log.debug("进入DBBuildingStoreyHisBusiness.update!参数DbBuildingStoreyHis:"
					+ storeyHis);
		sqlSession.update("updateDbBuildingStoreyHis", storeyHis);
		return storeyHis;
	}

	public Map getUpdateSql() {
		return PerformSQLUtil.get("updateDbBuildingStoreyHis", this);
	}
}

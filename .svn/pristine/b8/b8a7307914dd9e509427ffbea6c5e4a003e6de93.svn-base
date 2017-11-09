package com.hanqian.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBuildingStorey;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 楼宇楼层 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author
 * @version 1.4 2012-9-13
 * @see
 */
@Service
public class BuildStoreyBusiness extends BaseBusiness {
	public static final Logger log = Logger
			.getLogger(BuildStoreyBusiness.class);

	public DbBuildingStorey findBuildStorey(int seq) {
		// return buildStoreyMgr.findBuildStorey(seq);if (log.isDebugEnabled())
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.findBuildStorey!参数seq:" + seq);
		DbBuildingStorey dbstorey = sqlSession.selectOne("findBuildStorey", seq);
		return dbstorey;
	}

	public Map getFindBuildStoreySql() {
		return PerformSQLUtil.get("findBuildStorey", this);
	}

	/**
	 * 根据楼宇id查询各区域的总面积
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public List findBuildingGroup(Integer buildingId) {
		// return buildStoreyMgr.findBuildingGroup(buildingId);
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.findBuildingGroup!参数buildingId:"
					+ buildingId);
		List lst = sqlSession.selectList("findBuildingGroup", buildingId);
		return lst;
	}

	public Map getFindBuildingGroupSql() {
		return PerformSQLUtil.get("findBuildingGroup", this);
	}

	/**
	 * 获取单个用途总面积
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public List findBuildinguseArea() {
		// return buildStoreyMgr.findBuildinguseArea();
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.findBuildinguseArea!");
		List lst = sqlSession.selectList("findBuildinguseArea");
		return lst;
	}

	public Map getFindBuildinguseAreaSql() {
		return PerformSQLUtil.get("findBuildinguseArea", this);
	}

	/**
	 * 获取医院近期总面积
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public List findHospInfo(Integer hospid) {
		// return buildStoreyMgr.findHospInfo(hospid);
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.findHospInfo!参数hospid:" + hospid);
		List lst = sqlSession.selectList("findHospInfo", hospid);
		return lst;
	}

	public Map getFindHospInfoSql() {
		return PerformSQLUtil.get("findHospInfo", this);
	}

	/**
	 * 添加
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public void insertBuildingStorey(DbBuildingStorey buildingStorey) {
		// buildStoreyMgr.insertBuildingStorey(buildingStorey);
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.insertBuildingStorey!参数buildingStorey:"
					+ buildingStorey);
		sqlSession.insert("insertBuildingStorey", buildingStorey);

	}

	public Map getInsertBuildingStoreySql() {
		return PerformSQLUtil.get("insertBuildingStorey", this);
	}

	/**
	 * 修改楼层信息
	 * 
	 * @param dbBuildingStorey
	 */
	public boolean updateDbBuildingStorey(DbBuildingStorey buildingStorey) {
		// return buildStoreyMgr.updateDbBuildingStorey(buildingStorey);
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.updateDbBuildingStorey!参数buildingStorey:"
					+ buildingStorey);
		boolean bool = true;
		try {
			sqlSession.update("updateDbBuildingStorey", buildingStorey);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getUpdateDbBuildingStoreySql() {
		return PerformSQLUtil.get("updateDbBuildingStorey", this);
	}

	/**
	 * 获取楼宇区域信息(非分頁)
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findBuildingStorey(DbBuildingStorey buildingStorey) {
		// return buildStoreyMgr.findBuildingStorey(buildingStorey);
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.findBuildingStorey!对象DbBuildingStorey:"
					+ buildingStorey);
		Map map = new HashMap();

		if (buildingStorey != null) {
			if (!SysUtil.isNullObject(buildingStorey.getSeq())) {
				map.put("seq", buildingStorey.getSeq());
			} else {
				map.put("seq", null);
			}
			if (buildingStorey.getDbBuilding() != null
					&& !SysUtil.isNullObject(buildingStorey.getDbBuilding()
							.getBuildingId())) {
				map.put("buildingId", buildingStorey.getDbBuilding()
						.getBuildingId());
			} else {
				map.put("buildingId", null);
			}
			if (buildingStorey.getStorey() != null) {
				map.put("storey", buildingStorey.getStorey());
			} else {
				map.put("storey", null);
			}
		}
		return this.getData("findBuildingStorey", map);
	}

	public Map getFindBuildingStoreySql() {
		return PerformSQLUtil.get("findBuildingStorey", this);
	}

	/**
	 * 楼宇面积详情列表
	 */
	public RetCode callBuiildStorey(Integer buildingId) {
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.callBuiildStorey!参数buildingId:"
					+ buildingId);

		RetCode rc = this.callBuiildStoreyMethod(buildingId);
		return rc;

	}

	public Map getCallBuiildStoreySql() {
		return PerformSQLUtil.get("callBuiildStoreyMethod", this);
	}

	public RetCode callBuiildStoreyMethod(Integer buildingId) {
		RetCode rc = new RetCode();
		RetCode rcresult = new RetCode();
		String callstr = "";
		List<Map> listresult = new ArrayList();
		if (buildingId != null) {
			Map mapvalue = new HashMap();
			mapvalue.put("buildingId", buildingId);
			List lst = sqlSession.selectList("addStringSQL");
			for (int i = 0; i < lst.size(); i++) {
				Map map = (Map) lst.get(i);
				callstr += ", max(decode(area_id," + map.get("seq").toString()
						+ ", acreage||'#'||remarks||'#'||seq||'#'||"
						+ map.get("seq").toString() + ")) as "
						+ map.get("content1").toString() + "  ";
			}
			callstr = callstr.substring(1);
			mapvalue.put("callstr", callstr);
			rc = this.getData("callBuiildStoreyMethod", mapvalue);

			List lresult = null;

			if (rc.getObj() != null) {
				List l = (List) rc.getObj();

				for (int i = 0; i < l.size(); i++) {
					Map map = new HashMap();
					int col = ((Map) (l.get(i))).size();
					String[] clName = new String[col];
					map = (Map) l.get(i);
					
					
					//排序  楼层,门诊,急诊,住院,医技,保障,行政,科研,教育,生活,车库,其他
					Map map1 = new  LinkedHashMap();
					Set set = map.keySet();
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("楼层")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("门诊")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("急诊")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("住院")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("医技")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("保障")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("行政")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("科研")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("教育")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("生活")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("车库")){
							map1.put(key, map.get(key));
						}
					}
					for (Object key : set) {
						if(StringUtils.trim(key.toString()).equals("其他")){
							map1.put(key, map.get(key));
						}
					}
					map1.keySet().toArray();
					Set keys1 =map1.keySet();
					Iterator<String> iterator1 = keys1.iterator();
					while (iterator1.hasNext()) {

						String str = iterator1.next().toString();
						if ("columnname".equals(str)) {
							keys1.remove(iterator1.next());
						}
					}

					Iterator<String> iterator = keys1.iterator();
					int inum = 0;
					while (iterator.hasNext()) {

						String str = iterator.next().toString();
						clName[inum] = str;
						inum++;

					}

					map.put("ColumnName", clName);
					listresult.add(map);
				}

			}

			if (listresult.size() > 0) {
				rcresult.setCode(0);
				rcresult.setDesc("操作成功");
				rcresult.setDetail("操作成功");
				rcresult.setObj(listresult);
			}

		} else {
			return null;
		}
		return rcresult;
	}

	/**
	 * 数据上报
	 *
	 */
	public RetCode reportBuildingStorey(Date lastTime) {
		// return buildStoreyMgr.reportBuildingStorey(lastTime);
		if (log.isDebugEnabled())
			log.debug("进入BuildStoreyBusiness.reportBuildingStorey!参数Date:"
					+ lastTime);
		Map map = new HashMap();
		if (lastTime != null) {
			map.put("opertime", lastTime);
		} else {
			map.put("opertime", null);
		}
		return this.getData("reportBuildingStorey", map);
	}

	public Map getReportBuildingStoreySql() {
		return PerformSQLUtil.get("reportBuildingStorey", this);
	}

}

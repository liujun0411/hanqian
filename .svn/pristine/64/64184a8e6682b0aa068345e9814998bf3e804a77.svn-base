package com.hanqian.business;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbAlarm;
import com.hanqian.pojo.DbAlarmHis;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-26
 * @see
 */
@Service
public class DbAlarmBusiness extends BaseBusiness {
	/**
	 * log4g日志
	 */

	public static final Logger logg = Logger.getLogger(DbAlarmBusiness.class);

	/**
	 * 根据设备查询当前设备的告警信息
	 * 
	 * @param dbPointEquip
	 * @return
	 */
	public RetCode findAlarmPointByEquip(String equipId) {
		// return dbAlarmMgr.findAlarmPointByEquip(equipId);
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmPointByEquip,参数equipId:"
					+ equipId);

		Map map = new HashMap();
		map.put("equipId", Integer.parseInt(equipId));
		return this.getData("findAlarmPointByEquip", map);
	}

	public Map getFindAlarmPointByEquipSql() {
		return PerformSQLUtil.get("findAlarmPointByEquip", this);
	}

	/**
	 * 根据设备查询当前设备的告警信息
	 * 
	 * @param dbPointEquip
	 * @return
	 */
	public RetCode findAlarmPointByGroup(String groupId) {
		// return dbAlarmMgr.findAlarmPointByGroup(groupId);
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmPointByGroup,参数groupId:"
					+ groupId);

		Map map = new HashMap();
		map.put("groupId", groupId);
		return this.getData("findAlarmPointByGroup", map);
	}

	public Map getFindAlarmPointByGroupSql() {
		return PerformSQLUtil.get("findAlarmPointByGroup", this);
	}

	/**
	 * 根据设备查询当前设备的告警信息(分页)
	 * 
	 * @param dbPointEquip
	 * @return
	 */
	public RetCode findAlarmPointByEquipPage(String equipId, int currentpage,
			int pageSize) {
		// return dbAlarmMgr.findAlarmPointByEquipPage(equipId,
		// currentpage,pageSize);
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmPointByEquipPage,参数equipId:"
					+ equipId + " currentpage:" + currentpage + " pageSize:"
					+ pageSize);

		Map map = new HashMap();
		map.put("equipId", Integer.parseInt(equipId));
		return this.getPageData("findAlarmPointByEquipPage", map, currentpage,
				pageSize);
	}

	public Map getFindAlarmPointByEquipPageSql() {
		return PerformSQLUtil.get("findAlarmPointByEquipPage", this);
	}

	public RetCode findAlarmPointByEquip(String equipId, int currentpage,
			int pageSize) {
		// return dbAlarmMgr.findAlarmPointByEquip(equipId,
		// currentpage,pageSize);
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmPointByEquip,参数equipId:"
					+ equipId + " currentpage:" + currentpage + " pageSize:"
					+ pageSize);

		Map map = new HashMap();
		map.put("equipId", equipId);
		return this.getPageData("findAlarmPointByEquip", map, currentpage,
				pageSize);
	}

	public Map getFindAlarmPointByEquipByPageSql() {
		return PerformSQLUtil.get("findAlarmPointByEquip", this);
	}

	/**
	 * 根据设备查询当前设备的告警信息(分页)
	 * 
	 * @param dbPointEquip
	 * @return
	 */
	public String findAlarmAboutInfo(Integer seq) {
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmAboutInfo,参数seq:" + seq);

		// RetCode retCode = dbAlarmMgr.findAlarmAboutInfo(seq);
		Map mapparam = new HashMap();
		mapparam.put("seq", seq);
		RetCode retCode = this.getPageData("findAlarmAboutInfo", mapparam);

		List list = (List) retCode.getObj();
		JSONObject infoJson = new JSONObject();
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			infoJson.accumulate("equipName", map.get("equip_name"));
			infoJson.accumulate("place", map.get("place"));
			infoJson.accumulate("remarks", map.get("remarks"));
			infoJson.accumulate("name", map.get("name"));
			infoJson.accumulate("deleteflag", map.get("deleteflag"));
			if(map.get("update_date")==null){
				infoJson.accumulate("updateDate","");
			}else{
				infoJson.accumulate("updateDate", (map.get("update_date")).toString());
			}
		}
		return infoJson.toString();
	}

	public Map getFindAlarmAboutInfoSql() {
		return PerformSQLUtil.get("findAlarmAboutInfo", this);
	}

	/**
	 * 任意提醒个数
	 * 
	 * @param 2015-03-23
	 * @param @return
	 * @return String
	 */
	public String findCurrentAlarmCountSuiYi() {
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findCurrentAlarmCountSuiYi!");

		RetCode retCode = this.getPageData("findCurrentAlarmSuiYiTiXingCount",
				null);

		// RetCode retCode = dbAlarmMgr.findCurrentAlarmSuiYiTiXingCount();
		// 需要维修设备个数
		List list = (List) retCode.getObj();
		JSONObject levelJson = new JSONObject();
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			// 数据库里面readed为空就显示为0不为空就显示个数
			levelJson.accumulate("suiyiCounts", map.get("readed") == null ? 0
					: map.get("readed"));
		}
		return levelJson.toString();
	}

	public Map getFindCurrentAlarmCountSuiYiSql() {
		return PerformSQLUtil.get("findCurrentAlarmSuiYiTiXingCount", this);
	}

	/**
	 * 查询任意提醒的列表页面
	 * 
	 * @param 2015-3-13
	 * @param @param level
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findCurrentAlarmInfoSuIYitixing(int level, int currentPage,
			int pageSize) {

		RetCode rt = new RetCode();
		try {
			// RetCode rc = dbAlarmMgr.findCurrentAlarmInfoSuiYitixing(level,
			// currentPage, pageSize);
			if (logg.isDebugEnabled())
				logg.debug("进入DbAlarmBusiness.findCurrentAlarmInfoSuIYitixing,参数level:"
						+ level
						+ " currentPage:"
						+ currentPage
						+ " pageSize:"
						+ pageSize);
			Map map = new HashMap();
			map.put("level", level); // 原来的Manager中完全未用到level

			RetCode rc = this.getPageData("findCurrentAlarmInfoSuiYitixingAAA",
					null, currentPage, pageSize);

			rt.setObj(rc.getObj());
			rt.setPage(rc.getPage());
			if (rc.getObj() == null) {
				rt.setDesc("暂无数据!");
			}
		} catch (Exception e) {
			logg.error("DbAlarmBusiness-->findCurrentAlarmInfo", e);
			rt.setDesc(e.getMessage());
		}
		return rt;
	}

	public Map getFindCurrentAlarmInfoSuIYitixingSql() {
		return PerformSQLUtil.get("findCurrentAlarmInfoSuIYitixing", this);
	}

	/**
	 * 查询任意提醒单个记录
	 * 
	 * @param 2015-3-13
	 * @param @param level
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findCurrentAlarmInfoSuIYitixingfillId(int seqId) {

		RetCode rt = new RetCode();
		try {
			// RetCode rc =
			// dbAlarmMgr.findCurrentAlarmInfoSuIYitixingfillId(seqId);
			if (logg.isDebugEnabled())
				logg.debug("进入DbAlarmBusiness.findCurrentAlarmInfoSuIYitixing,参数seqId:"
						+ seqId);
			Map map = new HashMap();
			map.put("seqId", seqId);

			RetCode rc = this.getData("findCurrentAlarmInfoSuIYitixingfillId",
					map);

			rt.setObj(rc.getObj());
			if (rc.getObj() == null) {
				rt.setDesc("暂无数据!");
			}
		} catch (Exception e) {
			logg.error(
					"DbAlarmBusiness-->findCurrentAlarmInfoSuIYitixingfillId",
					e);
			rt.setDesc(e.getMessage());
		}
		return rt;
	}

	public Map getFindCurrentAlarmInfoSuIYitixingfillIdSql() {
		return PerformSQLUtil
				.get("findCurrentAlarmInfoSuIYitixingfillId", this);
	}

	/**
	 * 查询列表之前必须修改db_aries中readed为1
	 * 
	 * @param sequence
	 * @param controlpoint
	 * @param remarks
	 * @return
	 */
	public int updateRemarksSuiYi() {
		// return dbAlarmMgr.updateRemarksSuiYi();
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.updateRemarksSuiYi!");
		return sqlSession.update("updateRemarksSuiYi");
	}

	/**
	 * 若显示“确认执行”，则点击按钮修改为is_done 状态为“1” (已执行)
	 * 
	 * @param sequence
	 * @param controlpoint
	 * @param remarks
	 * @return
	 */
	public int updateRemarksSuiYiIsDone(String seqId) {
		// return dbAlarmMgr.updateRemarksSuiYi();
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.updateRemarksSuiYiIsDone!");
		Map map = new HashMap();
		map.put("seq", seqId);
		return sqlSession.update("updateRemarksSuiYiIsDoneSeq", map);
	}

	public Map getUpdateRemarksSuiYiSql() {
		return PerformSQLUtil.get("updateRemarksSuiYi", this);
	}

	/**
	 * 根据设备查询当前设备的告警信息(分页)
	 * 
	 * @param dbPointEquip
	 * @return
	 */
	public String findHisAlarmAboutInfo(Integer seq) {
		// RetCode retCode = dbAlarmMgr.findHisAlarmAboutInfo(seq);

		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findHisAlarmAboutInfo,参数seq:" + seq);
		Map mapparam = new HashMap();
		mapparam.put("seq", seq);

		RetCode retCode = this.getData("findHisAlarmAboutInfo", mapparam);

		List list = (List) retCode.getObj();
		JSONObject infoJson = new JSONObject();
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			infoJson.accumulate("equipName", map.get("equip_name"));
			infoJson.accumulate("oper", map.get("oper"));
			infoJson.accumulate("place", map.get("place"));
			infoJson.accumulate("remarks", map.get("remarks"));
			infoJson.accumulate("name", map.get("name"));
		}
		return infoJson.toString();
	}

	public Map getFindHisAlarmAboutInfoSql() {
		return PerformSQLUtil.get("findHisAlarmAboutInfo", this);
	}

	/**
	 * 分组groupId
	 * 
	 * @param 2012-10-17
	 * @param @param groupId
	 * @param @return
	 * @return DbAlarm
	 */
	public RetCode findCurrentAlarmInfoByGroupId(String groupId,
			int currentPage, int pageSize) {
		// return dbAlarmMgr.findCurrentAlarmInfoByGroupId(groupId,
		// currentPage,pageSize);
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findCurrentAlarmInfoByGroupId,参数groupId:"
					+ groupId
					+ " currentPage:"
					+ currentPage
					+ " pageSize:"
					+ pageSize);
		Map mapparam = new HashMap();
		mapparam.put("groupId", Integer.parseInt(groupId));

		RetCode retCode = this.getPageData("findCurrentAlarmInfoByGroupId",
				mapparam, currentPage, pageSize);
		return retCode;
	}

	public Map getFindCurrentAlarmInfoByGroupIdSql() {
		return PerformSQLUtil.get("findCurrentAlarmInfoByGroupId", this);
	}

	/**
	 * 设备Id
	 * 
	 * @param 2012-10-17
	 * @param @param equipId
	 * @param @return
	 * @return DbAlarm
	 */
	public RetCode findCurrentAlarmInfoByEquipId(String equipId,
			int currentPage, int pageSize) {
		// return dbAlarmMgr.findCurrentAlarmInfoByEquipId(equipId,
		// currentPage,pageSize);
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findCurrentAlarmInfoByEquipId,参数equipId:"
					+ equipId
					+ " currentPage:"
					+ currentPage
					+ " pageSize:"
					+ pageSize);

		Map mapparam = new HashMap();
		mapparam.put("equipId", Integer.parseInt(equipId));
		RetCode retCode = this.getPageData("findCurrentAlarmInfoByEquipId",
				mapparam, currentPage, pageSize);
		return retCode;

	}

	public Map getFindCurrentAlarmInfoByEquipIdSql() {
		return PerformSQLUtil.get("findCurrentAlarmInfoByEquipId", this);
	}

	/**
	 * 
	 * 获取历史告警信息
	 * 
	 * @param 2012-9-29
	 * @param @param level
	 * @param @param name
	 * @param @param startTime
	 * @param @param endTime
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @return
	 * @return RetCode
	 */

	public Map findHiaPage(String timeType, Short level, String name,
			String startTime, String endTime, int currentPage, int pageSize) {
		Map map = new HashMap();

		if (StringUtil.isNotEmpty(startTime) && StringUtil.isNotEmpty(endTime)) {

			map.put("startTime", startTime);
			map.put("endTime", endTime);
		} else if (StringUtil.isNotEmpty(startTime)) {

			map.put("startTime", startTime);
			map.put("endTime", null);
		} else if (StringUtil.isNotEmpty(endTime)) {

			map.put("startTime", null);
			map.put("endTime", endTime);
		}

		if (level != null && level != 0) {

			map.put("alertlevel", level);
		} else {
			map.put("alertlevel", null);
		}
		if (StringUtil.isNotEmpty(name)) {

			map.put("name", name);
		} else {
			map.put("name", null);
		}
		return map;
	}

	public RetCode findAlarmHisInfo(String timeType, Short level, String name,
			String startTime, String endTime, int currentPage, int pageSize) {
		RetCode rt = new RetCode();
		try {
			// RetCode rc = dbAlarmMgr.findHiaPage(timeType,level, name,
			// startTime,endTime, currentPage, pageSize);

			if (logg.isDebugEnabled())
				logg.debug("进入DbAlarmBusiness.findCurrentAlarmInfoByEquipId,参数timeType:"
						+ timeType
						+ " level:"
						+ level
						+ " name:"
						+ name
						+ " startTime:"
						+ startTime
						+ " endTime:"
						+ endTime
						+ " currentPage:"
						+ currentPage
						+ " pageSize:"
						+ pageSize);

			Map mapparam = new HashMap();
			mapparam = this.findHiaPage(timeType, level, name, startTime,
					endTime, currentPage, pageSize);

			RetCode rc = null;

			if ("1".equals(timeType)) {// 告警时间 start
				rc = this.getPageData("findHiaPage1", mapparam, currentPage,
						pageSize);
			} else if ("2".equals(timeType)) {// 消除时间 end
				rc = this.getPageData("findHiaPage2", mapparam, currentPage,
						pageSize);
			} else {
				rc = this.getPageData("findHiaPage", mapparam, currentPage,
						pageSize);
			}

			if (rc.getObj() == null) {
				rt.setDesc("暂无数据!");
			}
			rt.setObj(rc.getObj());
			rt.setPage(rc.getPage());

		} catch (Exception e) {
			logg.error("DbAlarmBusiness-->findAlarmHisInfo", e);
			rt.setObj(null);
			rt.setDesc(e.getMessage());
		}
		return rt;
	}

	/**
	 * 获取当前各个级别的告警数
	 * 
	 * @param 2012-9-29
	 * @param @return
	 * @return String
	 */
	public String findCurrentAlarmCount() {
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findCurrentAlarmCount!");
		RetCode retCode = this.getData("findCurrentAlarmCountDbAlarm", null);
		// List list = sqlSession.selectList("findCurrentAlarmCount");
		// RetCode retCode = dbAlarmMgr.findCurrentAlarmCount();
		// 需要维修设备个数
		// RetCode rCode = equipListMgr.findEquipMaintenance("1",1,10);
		List list = (List) retCode.getObj();
		// List rList = (List)rCode.getObj();
		JSONObject levelJson = new JSONObject();
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			levelJson.accumulate("levelOne",
					map.get("one") == null ? 0 : map.get("one"));
			levelJson.accumulate("levelTwo",
					map.get("two") == null ? 0 : map.get("two"));
			levelJson.accumulate("levelThree", map.get("three") == null ? 0
					: map.get("three"));
		}

		return levelJson.toString();
	}

	public Map getFindCurrentAlarmCountSql() {
		return PerformSQLUtil.get("findCurrentAlarmCountDbAlarm", this);
	}

	public RetCode findCurrentAlarmInfo(int level, int currentPage, int pageSize) {
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findCurrentAlarmInfo!参数level:"
					+ level + " currentPage:" + currentPage + " pageSize:"
					+ pageSize);
		RetCode rt = new RetCode();
		try {
			// RetCode rc = dbAlarmMgr.findCurrentAlarmInfo(level,
			// currentPage,pageSize);
			Map mapparam = new HashMap();
			if (level > 0) {

				mapparam.put("alertlevel", Short.parseShort(level + ""));
			} else {
				mapparam.put("alertlevel", null);
			}
			mapparam.put("showflag", Short.parseShort(1 + ""));
			//分页
			RetCode rc = this.getPageData("findCurrentAlarmInfo", mapparam, currentPage, pageSize);

			rt.setObj(rc.getObj());
			rt.setPage(rc.getPage());
			if (rc.getObj() == null) {
				rt.setDesc("暂无数据!");
			}
		} catch (Exception e) {
			logg.error("DbAlarmBusiness-->findCurrentAlarmInfo", e);
			rt.setDesc(e.getMessage());
		}
		return rt;
	}

	public Map getFindCurrentAlarmInfoSql() {
		return PerformSQLUtil.get("findCurrentAlarmInfo", this);
	}

	/**
	 * 更新当前告警备注
	 * 
	 * @param sequence
	 * @param controlpoint
	 * @param remarks
	 * @return
	 */
	public int updateRemarks(String seq, String remarks) {
		// return dbAlarmMgr.updateRemarks(seq, remarks);
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.updateRemarks!参数seq:" + seq
					+ " remarks:" + remarks);
		DbAlarm dbAlarm = new DbAlarm();
		dbAlarm.setSeq(Integer.valueOf(seq));
		dbAlarm.setRemarks(remarks);
		return sqlSession.update("updateRemarks", dbAlarm);

	}

	public Map getUpdateRemarksSql() {
		return PerformSQLUtil.get("updateRemarks", this);
	}

	/**
	 * 消除告警
	 * 
	 * @param remarks
	 *            备注
	 * @param oper
	 *            操作人
	 * @return
	 */
	public String removeAlarm(Integer sequence, String remarks, String oper) {
		String msg = "";
		try {
			if (logg.isDebugEnabled())
				logg.debug("进入DbAlarmBusiness.removeAlarm!参数sequence:"
						+ sequence + " remarks:" + remarks + " oper:" + oper);
			// DbAlarm dbalarm = dbAlarmMgr.findCurrentAlarmInfoBySeq(sequence);
			DbAlarm dbalarm = sqlSession.selectOne("findCurrentAlarmInfoBySeq",
					sequence);

			// msg = dbAlarmMgr.deleteAlarm(dbalarm, remarks, oper);

			try {
				sqlSession.delete("deleteAlarm", dbalarm);

				DbAlarmHis alarmHis = new DbAlarmHis();
				alarmHis.setAlarmSeq(dbalarm.getSeq());
				alarmHis.setAlertlevel(dbalarm.getAlertlevel());
				alarmHis.setDbPoint(dbalarm.getDbPoint());
				alarmHis.setStarttime(dbalarm.getStarttime());
				alarmHis.setName(dbalarm.getName());
				alarmHis.setDes(dbalarm.getRemarks());
				alarmHis.setProjectPoint(dbalarm.getProjectPoint());
				alarmHis.setEndtime(new Date());
				alarmHis.setOper(oper);
				alarmHis.setRecovertime(dbalarm.getUpdateDate());
				alarmHis.setRemarks(remarks);
				try {
					// dbAlarmHisDAO.save(alarmHis);
					sqlSession.insert("insertdbAlarmHis", alarmHis);
					return "操作成功";
				} catch (Exception e) {
					logg.error("DbAlarmMgr-->deleteAlarm", e);
					e.printStackTrace();
					return "消除失败!请联系管理员!";
				}
			} catch (Exception e) {
				logg.error("DbAlarmMgr-->deleteAlarm", e);
				return "消除失败!请联系管理员!";
			}

		} catch (Exception e) {
			logg.error("DbAlarmBusiness-->removeAlarm", e);
			msg = "消除失败!请联系管理员!";
		}
		return msg;
	}

	public Map getRemoveAlarmSql() {
		return PerformSQLUtil.get("deleteAlarm", this);
	}

	
	
	
	/**
	 * 
	 * 
	 * 查询告警等级并添加到json一般页面需要
	 * 
	 * 
	 */
	public String findAlarmHighchartsCount()  throws Exception{
		// RetCode retCode = dbAlarmMgr.findAlarmCount();
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmHighchartsCount!");
		//RetCode retCode = this.getData("findAlarmHighchartsCount", null);
		// 每个告警级的总数量
		JSONObject levelJson = new JSONObject();
		RetCode rc = this.getData("findAlarmHighchartsCount", null);
		List list = (List<Map>) rc.getObj();
			// 将每个级别数量加入json
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
					levelJson.accumulate("a", map.get("one") == null ? 0 : map.get("one"));
					levelJson.accumulate("b", map.get("two") == null ? 0 : map.get("two"));
					levelJson.accumulate("c", map.get("three") == null ? 0 : map.get("three"));
			}
		return levelJson.toString();
	}
	
	
	/**
	 * 查询每个历史告警级别总数以及告警数量
	 * 
	 * @param 2013-4-16
	 * @param @return
	 * @return String
	 * @throws Exception
	 */
	public String findAlarmInfo() throws Exception {
		// RetCode retCode = dbAlarmMgr.findAlarmCount();
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmInfo!");
		RetCode retCode = this.getData("findAlarmCount", null);

		// 历史告警
		List list = (List) retCode.getObj();
		JSONObject levelJson = new JSONObject();
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			levelJson.accumulate("levelOne",
					map.get("one") == null ? 0 : map.get("one"));
			levelJson.accumulate("levelTwo",
					map.get("two") == null ? 0 : map.get("two"));
			levelJson.accumulate("levelThree", map.get("three") == null ? 0
					: map.get("three"));
		}

		String criterionval = getElementNode("safesave", "criterionval");
		levelJson.accumulate("criterionval",
				Double.parseDouble(criterionval) * 100);

		// 每个告警级的总数量

		RetCode rc = this.getData("findBySQLColumn", null);
		list = (List<Map>) rc.getObj();
		// 将每个级别数量加入json
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			if (map.get("alert_level").toString().equals("1")) {
				levelJson.accumulate("levelCountOne", map.get("alarmcount"));
			}
			if (map.get("alert_level").toString().equals("2")) {
				levelJson.accumulate("levelCountTwo", map.get("alarmcount"));
			}
			if (map.get("alert_level").toString().equals("3")) {
				levelJson.accumulate("levelCountThree", map.get("alarmcount"));
			}
		}

		return levelJson.toString();
	}

	public Map getFindAlarmInfoSql() {
		return PerformSQLUtil.get("findBySQLColumn", this);
	}

	/**
	 * 获取当前各个级别的历史告警数
	 * 
	 * @param 2012-9-29
	 * @param @return
	 * @return String
	 */
	public String findAlarmCount() throws Exception {
		// RetCode retCode = dbAlarmMgr.findAlarmCount();
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmCount!");
		RetCode retCode = this.getData("findAlarmCount", null);
		// 历史告警
		List list = (List) retCode.getObj();
		JSONObject levelJson = new JSONObject();
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			levelJson.accumulate("levelOne",
					map.get("one") == null ? 0 : map.get("one"));
			levelJson.accumulate("levelTwo",
					map.get("two") == null ? 0 : map.get("two"));
			levelJson.accumulate("levelThree", map.get("three") == null ? 0
					: map.get("three"));
		}

		String criterionval = getElementNode("safesave", "criterionval");
		levelJson.accumulate("criterionval",
				Double.parseDouble(criterionval) * 100);
		return levelJson.toString();
	}

	public Map getFindAlarmCountSql() {
		return PerformSQLUtil.get("findAlarmCount", this);
	}

	/**
	 * 获取当前各个级别的历史告警数
	 * 
	 * @param 2012-9-29
	 * @param @return
	 * @return String
	 */
	public String findSpecialAlarmCount() throws Exception {
		// RetCode retCode = dbAlarmMgr.findSpecialAlarmCount();
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findSpecialAlarmCount!");

		RetCode retCode = this.getData("findSpecialAlarmCount", null);
		// 历史告警
		List list = (List) retCode.getObj();
		String equiptype = "";
		String levelOne = "";
		String levelTwo = "";
		String levelThree = "";
		String returnStr = "";
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				// 得到设备类型ID，和个等级的比例
				equiptype = map.get("equip_type_id") + "";
				levelOne = map.get("per_o") + "";
				levelTwo = map.get("per_t") + "";
				levelThree = map.get("per_th") + "";
				returnStr += levelOne + "," + levelTwo + "," + levelThree + ";";
			}
		}
		if (returnStr != "") {
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		return returnStr;
	}

	public Map getFindSpecialAlarmCountSql() {
		return PerformSQLUtil.get("findSpecialAlarmCount", this);
	}

	public String findSpecialAlarmCountByHtml5() throws Exception {
		// RetCode retCode = dbAlarmMgr.findSpecialAlarmCountByHtml5();
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findSpecialAlarmCountByHtml5!");

		RetCode retCode = this.getData("findSpecialAlarmCountByHtml5", null);
		// 历史告警
		List list = (List) retCode.getObj();
		String equipType = "";
		String levelOne = "";
		String levelTwo = "";
		String levelThree = "";
		String returnStr = "";
		JSONArray array = new JSONArray();
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				// 得到设备类型ID，和个等级的比例
				equipType = map.get("equip_type_id") + "";

				levelOne = map.get("one") + "";
				levelTwo = map.get("two") + "";
				levelThree = map.get("three") + "";
				returnStr = levelOne + "," + levelTwo + "," + levelThree;

				array.add(returnStr);
			}
		}
		return array.toString();
	}

	public Map getFindSpecialAlarmCountByHtml5Sql() {
		return PerformSQLUtil.get("findSpecialAlarmCountByHtml5", this);
	}

	/**
	 * 获取当前各个级别的历史告警数
	 * 
	 * @param 2012-9-29
	 * @param @return
	 * @return String
	 */
	public String findAlarmHisCount() throws Exception {
		if (logg.isDebugEnabled())
			logg.debug("进入DbAlarmBusiness.findAlarmHisCount!");

		String startTime = getElementNode("history", "startTime");
		String endTime = getElementNode("history", "endTime");

		Map mapvalue = new HashMap();
		mapvalue.put("startTime", startTime);
		mapvalue.put("endTime", endTime);
		if (startTime != null && startTime != "" && endTime != null
				&& endTime != "") {
			mapvalue.put("signtype", "go");
		} else {
			mapvalue.put("signtype", null);
		}
		RetCode retCode = this.getData("findAlarmHisCount", mapvalue);

		// RetCode retCode = dbAlarmMgr.findAlarmHisCount(startTime,endTime);
		// 历史告警
		List list = (List) retCode.getObj();
		String returnStr = "";
		String levelOne = "";
		String levelTwo = "";
		String levelThree = "";
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			levelOne = map.get("one") + "";
			levelTwo = map.get("two") + "";
			levelThree = map.get("three") + "";
			Double anquan = 1 - SysUtil.toDouble(levelOne)
					- SysUtil.toDouble(levelTwo) - SysUtil.toDouble(levelThree);
			returnStr += "" + anquan + "," + levelThree + "," + levelTwo + ","
					+ levelOne;
		}
		return returnStr;
	}

	public Map getFindAlarmHisCountSql() {
		return PerformSQLUtil.get("findAlarmHisCount", this);
	}

	// 得到基准值
	public String getElementNode(String type, String nodeStr) throws Exception {
		// 要读取xml文件
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath().toString();
		File temp = new File(path);
		String basePath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
		String retuStr = "1";// 基准参数
		temp = new File(
				basePath
						+ "src/main/webapp/manager/monitoring/control/electricity/config/targetConfig.xml");
		if (temp.exists()) {
			// 如果文件不存在就没有
			// 文件存在，读取xml数值
			XMLWriter writer = null;// 声明写XML的对象
			SAXReader reader = new SAXReader();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GBK");
			Document document = reader.read(temp);// 读取XML文件
			Element root = document.getRootElement();// 得到根节点
			Element node = (Element) root.elementIterator(type).next();
			retuStr = node.selectSingleNode(nodeStr).getText().toString();
		}
		return retuStr;
	}

}

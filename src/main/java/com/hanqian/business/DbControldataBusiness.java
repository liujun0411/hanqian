package com.hanqian.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.hanqian.form.VOElevatorPoint;
import com.hanqian.form.VOIllmineFloor;
import com.hanqian.form.VOIllminePoint;
import com.hanqian.form.VOShowPoint;
import com.hanqian.util.HashList;
import com.hanqian.util.Page;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.SysUtil;
import com.mysql.jdbc.Connection;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service(value="controlDataBusiness")
public class DbControldataBusiness extends BaseBusiness{

	public static final Logger log = Logger
			.getLogger(DbControldataBusiness.class);

	private Map<Object, Map> dbPointMap = new HashMap<Object, Map>();
	
	//测试的时候使用src开头的目录，上线的时候使用没有src开头的目录
private static final String basePath = "//src//main//webapp//manager//monitoring//controlJson//";
//	private static final String basePath = "//manager//monitoring//controlJson//";
	
	/**
	 * 根据设备ID获取设备信息
	 * @param equipId
	 * @return
	 */
	public RetCode findEquipment(String equipId) {
		// return dbControlDataMgr.findControlData(equipId);
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findEquipment!参数equipId:"
					+ equipId);
		Map map = new HashMap();
		map.put("equipId", equipId);
		RetCode rt = this.getData("findControlData", map);

		return rt;
	}

	public Map getFindEquipmentSql() {
		return PerformSQLUtil.get("findControlData", this);
	}
	
	/**
	 * 修改数据
	 * @param map
	 * @return
	 */
	public int updateData(Map map) {

		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.updateData!参数map:" + map);
		Map sendmap = new HashMap();
		sendmap.put("recode", map.get("RECORD"));
		sendmap.put("recodetime", map.get("RECORDTIME"));
		sendmap.put("projectpoint", map.get("PROJECT_POINT"));
		return sqlSession.update("updateControlData", sendmap);
	}

	public Map getUpdateDataSql() {
		return PerformSQLUtil.get("updateControlData", this);
	}

	/**
	 * 生成单个设备的监控点位读数JSON文件
	 * 
	 * @param equipId
	 * @return
	 */
	public void findEquipControlData(String equipId, String eqTypeId) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findEquipControlData!参数equipId:"
					+ equipId);
		List pointList = new ArrayList();
		// RetCode rc = dbControlDataMgr.findControlData(equipId);
		Map map = new HashMap();
		map.put("equipId", equipId);
		RetCode rc = this.getData("findControlData", map);

		if (rc.getObj() != null) {
			pointList = (List) rc.getObj();
		}
		JSONArray json = JSONArray.fromObject(pointList);
		createJSON(equipId, eqTypeId, json.toString());
	}

	public Map getFindControlDataSql() {
		return PerformSQLUtil.get("findControlData", this);
	}

	/**
	 * add by lg
	 * 
	 * @param 2013-5-24
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @param type 标识查询某一种数据
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findControlDataList(Integer currentPage, Integer pageSize,
			int type) {
		// RetCode rc =
		// dbControlDataMgr.findControlDataList(currentPage,pageSize,type);
		// return rc;
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlDataList!参数type:"
					+ type);

		if (type == 1) {// 总点位数:不包括未监控
			return this.getPageData("findControlDataList1", null, currentPage, pageSize);
		} else if (type == 2) {// 数字点位数
			return this.getPageData("findControlDataList2", null, currentPage, pageSize);
		} else if (type == 3) {// 状态点位数
			return this.getPageData("findControlDataList3", null, currentPage, pageSize);
		} else if (type == 4) {// 告警点位
			return this.getPageData("findControlDataList4", null, currentPage, pageSize);
		} else if (type == 5) {// 未监控
			return this.getPageData("findControlDataList5", null, currentPage, pageSize);
		} else {// 全部 （未监控，已监控）
			return this.getPageData("findControlDataList", null, currentPage, pageSize);
		}
	}
	/**
	 * 生产数据库的列表List页面
	 * 
	 * @param 2015-11-30
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @param 
	 * @param @return
	 * @return RetCode
	 */
	public RetCode selectfindDianweiInfoList(Integer currentPage, Integer pageSize,
			String pointname,String projectpoint,String equipname,String starttime,String endtime,String dwbmSDCD) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.selectfindDianweiInfoList!参数type:");
		Map map = new HashMap();
		if (StringUtil.isNotEmpty(pointname)) {
			map.put("pointname", pointname);
		}else{
			map.put("pointname", null);
		}
		if (StringUtil.isNotEmpty(projectpoint)) {
			map.put("projectpoint", projectpoint);
		}else{
			map.put("projectpoint", null);
		}
		if (StringUtil.isNotEmpty(equipname)) {
			map.put("equipname", equipname);
		}else{
			map.put("equipname", null);
		}
		if (StringUtil.isNotEmpty(dwbmSDCD)) {
			map.put("dwbmSDCD", dwbmSDCD);
		}else{
			map.put("dwbmSDCD", null);
		}
		/**
		 * 1.开始--结束  结束默认为昨天
		 * 2.开始(没值)--结束   开始 默认为最前的时间
		 * 3.开始--结束(没值)  结束默认为昨天
		 */
		if (StringUtil.isNotEmpty(starttime) && StringUtil.isNotEmpty(endtime)) {
			map.put("starttime", starttime);
			map.put("endtime", endtime);
		} else if (StringUtil.isNotEmpty(starttime)) {
			map.put("starttime", starttime);
			map.put("endtime", null);
		} else if (StringUtil.isNotEmpty(endtime)) {
			map.put("starttime", null);
			map.put("endtime", endtime);
		}
			return this.getPageData("selectfindDianweiInfoList", map, currentPage, pageSize);
	}
	/**
	 *SDCD的列表List页面
	 * 
	 * @param 2015-11-30
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @param 
	 * @param @return
	 * @return RetCode
	 */
/*	public RetCode sDCDfindDiwanweiInfoList(Integer currentPage, Integer pageSize,
			String projectpoint,String datetime) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.sDCDfindDiwanweiInfoList!参数type:");
		Map map = new HashMap();
		if (StringUtil.isNotEmpty(projectpoint)) {
			map.put("projectpoint", projectpoint);
		}else{
			map.put("projectpoint", null);
		}
		if (StringUtil.isNotEmpty(datetime)) {
			map.put("datetime", datetime);
		}else{
			map.put("datetime", null);
		}
			return this.getPageData("sDCDfindDiwanweiInfoList", map, currentPage, pageSize);
	}*/
	/**
	 * 
	 * 获取Excel表头信息
	 * 
	 * @return List
	 * @param 
	 * @Date 2015-12-02
	 */
	public List selectfindDianweiBiaoTaoCount() {
		List list1 = null;
		try{
			//放在Map中
			RetCode retCode =  this.getData("selectfindDianweiBiaoTaoCount",null);
			List list = (List) retCode.getObj();
			list1 = new ArrayList();
			String cols =null;
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map) list.get(i);
				cols = map.get("cols").toString();
				list1.add(cols);
			 }
		}catch(Exception e){
			log.error("获取表头失败"+e);
		}
		return list1;
	}
	/**
	 * 获取Excel导出时的数据集信息
	 * @param pointname
	 * @param projectpoint
	 * @param equipname
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List selectfindDianweiExcelList(String pointname,String equipname,String starttime,String endtime,String dwbmSDCD) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.selectfindDianweiInfoList!参数type:");
		Map map = new HashMap();
		if (StringUtil.isNotEmpty(pointname)) {
			map.put("pointname", pointname);
		}else{
			map.put("pointname", null);
		}
		if (StringUtil.isNotEmpty(dwbmSDCD)) {
			map.put("dwbmSDCD", dwbmSDCD);
		}else{
			map.put("dwbmSDCD", null);
		}
		if (StringUtil.isNotEmpty(equipname)) {
			map.put("equipname", equipname);
		}else{
			map.put("equipname", null);
		}
		/**
		 * 1.开始--结束  结束默认为昨天
		 * 2.开始(没值)--结束   开始 默认为最前的时间
		 * 3.开始--结束(没值)  结束默认为昨天
		 */
		if (StringUtil.isNotEmpty(starttime) && StringUtil.isNotEmpty(endtime)) {
			map.put("starttime", starttime);
			map.put("endtime", endtime);
		} else if (StringUtil.isNotEmpty(starttime)) {
			map.put("starttime", starttime);
			map.put("endtime", null);
		} else if (StringUtil.isNotEmpty(endtime)) {
			map.put("starttime", null);
			map.put("endtime", endtime);
		}
			RetCode retCode =  this.getData("selectfindDianweiExcelList", map);
			List list = (List) retCode.getObj();
			return list;
	}
	
	
	
	/**
	 * add by lg
	 * 
	 * @param 2013-5-24
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @param type 标识查询某一种类型数据
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findControlDataListByEquipType(Integer currentPage,Integer pageSize, int type) {
		// RetCode rc =
		// dbControlDataMgr.findControlDataListByEquipType(currentPage,pageSize,type);
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlDataListByEquipType!参数type:"
					+ type);
		Map map = new HashMap();
		map.put("type", type);
		//RetCode rc = this.getData("findControlDataListByEquipType", map);
		RetCode rc = this.getPageData("findControlDataListByEquipType", map, currentPage, pageSize);

		return rc;
	}

	public Map getFindControlDataListByEquipTypeSql() {
		return PerformSQLUtil.get("findControlDataListByEquipType", this);
	}

	/**
	 * 
	 * add by lg 5-24
	 * @return
	 */
	public int[] findControlCount() {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlCount!");
		// RetCode retCode = dbControlDataMgr.findControlCount();
		RetCode retCode = this.getData("findControlCount", null);
		List list = (List) retCode.getObj();
		int[] count = new int[4];
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			count[0] = map.get("totaldatacount") == null ? 0 : Integer
					.parseInt(map.get("totaldatacount").toString());
			count[1] = map.get("collectdatacount") == null ? 0 : Integer
					.parseInt(map.get("collectdatacount").toString());
			count[2] = map.get("statusdatacount") == null ? 0 : Integer
					.parseInt(map.get("statusdatacount").toString());
			count[3] = map.get("alarmdatacount") == null ? 0 : Integer
					.parseInt(map.get("alarmdatacount").toString());

		}
		return count;
	}

	public Map getFindControlCountSql() {
		return PerformSQLUtil.get("findControlCount", this);
	}

	/**
	 * 
	 * add by lg 6-18
	 * @return
	 */
	public int[] findControlCountByEquipType() {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlCountByEquipType!");
		// RetCode retCode = dbControlDataMgr.findControlCountByEquipType();
		RetCode retCode = this.getData("findControlCountByEquipType", null);

		List list = (List) retCode.getObj();
		int[] count = new int[4];
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			count[0] = map.get("boildercount") == null ? 0 : Integer
					.parseInt(map.get("boildercount").toString());
			count[1] = map.get("shuilengcount") == null ? 0 : Integer
					.parseInt(map.get("shuilengcount").toString());
			count[2] = map.get("watercount") == null ? 0 : Integer.parseInt(map
					.get("watercount").toString());
			count[3] = map.get("elecount") == null ? 0 : Integer.parseInt(map
					.get("elecount").toString());

		}
		return count;
	}

	public Map getFindControlCountByEquipTypeSql() {
		return PerformSQLUtil.get("findControlCountByEquipType", this);
	}
	
	/**
	 * 创建json文件
	 * @param equipId
	 * @param eqTypeId
	 * @param json
	 */
	public void createJSON(String equipId, String eqTypeId, String json) {
		// 获得当前类所在的路径
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath().toString();
		// 文件的保存目录
		File temp = new File(path);
		// 创建一个存放文件文件夹
		temp = new File(temp.getParentFile().getParentFile().getPath());
		if (!temp.exists()) {
			temp.mkdirs(); // 创建文件夹目录
		}
		File file = new File(temp.getPath() + basePath + eqTypeId);
		if (!file.exists()) {
			file.mkdirs(); // 创建文件夹目录
		}
		try {
			File realFile = new File(file, eqTypeId + "_" + equipId + ".json");
			if (realFile.exists()) {
				realFile.delete();
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(realFile), "UTF-8"));
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("DbControldataBusiness-->createJSON", e);
			e.printStackTrace();
		}
	}

	/**
	 * 根据设备组ID，获得所有的检测点位读数
	 * 
	 * @param 2012-10-31
	 * @param @return
	 * @return DbControldataDAO
	 */
	public RetCode findControlDataByGroup(String groupId) {
		// return dbControlDataMgr.findControlDataByGroup(groupId);
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlDataByGroup!参数groupId:"
					+ groupId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		RetCode rc = this.getData("findControlDataByGroup", map);
		return rc;
	}

	public Map getFindControlDataByGroupSql() {
		return PerformSQLUtil.get("findControlDataByGroup", this);
	}

	/**
	 * 根据设备组ID，获得所有的检测点位读数
	 * 
	 * @param 2012-10-31
	 * @param @return
	 * @return DbControldataDAO
	 */
	public RetCode findControlDataByGroupJsj(String groupId) {
		// return dbControlDataMgr.findControlDataByGroupByJsj(groupId);
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlDataByGroupJsj!参数groupId:"
					+ groupId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		RetCode rc = this.getData("findControlDataByGroupByJsj", map);
		return rc;
	}

	public Map getFindControlDataByGroupJsjSql() {
		return PerformSQLUtil.get("findControlDataByGroupJsj", this);
	}

	/**
	 * 根据分组，及 自定义where字符串条件（集水井）
	 * @param groupId
	 * @param list
	 * @return
	 */
	public RetCode findControlDataByGroupJsj(String groupId, List<Map> list) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlDataByGroupJsj!参数groupId:"
					+ groupId + " List<Map>" + list);

		String whereSql = "";
		String caseWhen = "case    ";
		int flag = 0;
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			String id = map.get("id").toString();
			if (i + 1 == list.size()) {
				whereSql = whereSql + "'" + id + ".01_CV'" + ",'" + id
						+ ".02_CV'";
			} else {
				whereSql = whereSql + "'" + id + ".01_CV'" + ",'" + id
						+ ".02_CV',";
			}
			if (map.get("number") != null) {
				flag++;
				caseWhen = caseWhen
						+ " when substr(c.project_point,0,instr(c.project_point,'.')-1)='"
						+ id + "' then " + map.get("number") + " ";
			}
		}
		if (flag > 0) {
			caseWhen = caseWhen + "end";
		} else {
			caseWhen = "";
		}

		Map map = new HashMap();
		map.put("groupId", groupId);
		map.put("whereSql", whereSql);
		map.put("caseWhen", caseWhen);

		RetCode rc = this.getData("findControlDataByGroupByJsjReason", map);
		return rc;

	}

	public Map getFindControlDataByGroupJsjReasonSql() {
		return PerformSQLUtil.get("findControlDataByGroupByJsjReason", this);
	}

	/**
	 * 根据分组，及泵号
	 * @param groupId
	 * @param bengIndex
	 * @return
	 */
	public RetCode findControlDataByGroupJsj(String groupId, int bengIndex) {
		// return
		// dbControlDataMgr.findControlDataByGroupByJsj(groupId,bengIndex);
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findControlDataByGroupJsj!参数groupId:"
					+ groupId + "  bengIndex:" + bengIndex);
		Map map = new HashMap();
		map.put("groupId", groupId);
		map.put("bengIndex", bengIndex);
		RetCode rc = this.getData("findControlDataByGroupByJsjIndex", map);
		return rc;
	}

	public Map getFindControlDataByGroupJsjIndexSql() {
		return PerformSQLUtil.get("findControlDataByGroupByJsjIndex", this);
	}

	/**
	 * 生成设备组的监控点位读数JSON文件
	 * 
	 * @param groupId
	 * @param eqTypeId
	 * @return
	 */
	public void findGroupControlData(String groupId, String eqTypeId) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findGroupControlData!参数groupId:"
					+ groupId + "  eqTypeId:" + eqTypeId);

		RetCode rc = new RetCode();
		List pList = new ArrayList();
		// rc = dbControlDataMgr.findControlDataByGroup(groupId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		rc = this.getData("findControlDataByGroup", map);

		if (rc.getObj() != null) {
			pList = (List) rc.getObj();
		}
		JSONArray jsonArr = JSONArray.fromObject(pList);
		String result = jsonArr.toString();
		createJSON(groupId, eqTypeId, result);
	}

	public Map getFindGroupControlDataSql() {
		return PerformSQLUtil.get("findControlDataByGroup", this);
	}

	/**
	 * 根据组Id查询得到
	 * 
	 * @param 2012-11-5
	 * @param
	 * @return void
	 */
	public RetCode findElevatorControlData(String groupId, int currentPage,
			int pageSize) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findElevatorControlData!参数groupId:"
					+ groupId);

		RetCode rc = new RetCode();
		HashList<VOElevatorPoint> rlist = new HashList<VOElevatorPoint>();
		try {
			// 得到查询的值
			// List list = dbControlDataMgr.findElevatorControlData(groupId);
			RetCode rt = new RetCode();
			List list = null;
			Map map = new HashMap();
			map.put("groupId", groupId);
			rt = this.getData("findElevatorControlData", map);
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}
			
			//获取“RJ”进行判断是仁济医院执行并且是电梯系统中(3号楼电梯系统groupId=538)
			String rj_ = (String) ServletActionContext.getRequest()
					.getSession().getAttribute("currentHospCode");
					List lists =new ArrayList();
					//1.判断是否是仁济
					//2.判断是groupId=538
					if(("RJ").equals(rj_)){
						if(("538").equals(groupId)){
							Map mm = null;
							String equipId = null;
							for(int b=0;b<list.size();b++){
								//把list的数据按照自己排序的方法排序
								mm = (Map) list.get(b);
								equipId = mm.get("equip_id")+ "";
								if(("21084").equals(equipId)){
									lists.add(list.get(b-30));
								}
								if(("21085").equals(equipId)){
									lists.add(list.get(b-40));
								}
								if(("21079").equals(equipId)){
									lists.add(list.get(b));
								}
								if(("21080").equals(equipId)){
									lists.add(list.get(b));
								}
								if(("21081").equals(equipId)){
									lists.add(list.get(b));
								}
								if(("21082").equals(equipId)){
									lists.add(list.get(b));
								}
								if(("21083").equals(equipId)){
									lists.add(list.get(b));
								}
								if(("21078").equals(equipId)){
									lists.add(list.get(b+35));
								}
								if(("21077").equals(equipId)){
									lists.add(list.get(b+35));
								}
							}
							list=lists;
						}
					}
			if (list != null) {
				// 如果有值
				Map m = null;
				VOElevatorPoint obj = null;
				VOShowPoint sp = null;
				String seq = null;
				String equipId = null;
				String equipName = null;
				String pointName = null;
				String projectPoint = null;
				String record = null;
				String storeyUp = null;
				String storeyDown = null;
				for (int i = 0; i < list.size(); i++) {
					m = (Map) list.get(i);
					seq = m.get("seq") + "";
					equipId = m.get("equip_id") + "";
					equipName = m.get("equip_name") + "";
					pointName = m.get("point_name") + "";
					projectPoint = m.get("project_point") + "";
					/**
					 * 修改  去掉后缀"。0"
					 * 
					 * Date：2015-07-08
					 * 
					 */
					record = SysUtil.subZeroAndDot((Double)m.get("record")+"");
					storeyUp = m.get("up") + "";
					storeyDown = m.get("down") + "";
					obj = rlist.find(equipId);
					if (obj == null) {
						// 用VOElevatorPoint做实体类
						obj = new VOElevatorPoint(equipId, storeyUp,
								storeyDown, equipName);
						obj.setShowPointList(new ArrayList<VOShowPoint>());
						// 放到集合中
						rlist.add(equipId, obj);
					}
					// 查询时候有点位在里面
					if (obj.findEqPoint(seq) == null) {
						sp = new VOShowPoint(equipId, pointName, projectPoint,
								record);
						obj.getShowPointList().add(sp);
					}
				}
			}
		} catch (Exception e) {
			log.error("DbControldataBusiness-->findElevatorControlData", e);
			e.printStackTrace();
		}
		if (rlist != null) {
			HashList<VOElevatorPoint> list = new HashList<VOElevatorPoint>();
			for (int i = (currentPage - 1) * pageSize; i < rlist.size()
					&& i < currentPage * pageSize; i++) {
				list.add(rlist.get(i));
			}
			rc.setObj(list);
			rc.setPage(new Page(currentPage, rlist.size(), pageSize));
		}
		return rc;
	}

	public Map getFindElevatorControlDataSql() {
		return PerformSQLUtil.get("findElevatorControlData", this);
	}

	/**
	 * 根据组Id查询得到照明设备点位读数
	 * 
	 * @param 2012-11-5
	 * @param
	 * @return void
	 */
	public RetCode findIllmineControlData(String buldId, String groupId) {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findElevatorControlData!参数groupId:"
					+ groupId + " buldId:" + buldId);
		RetCode rc = new RetCode();
		HashList<VOIllmineFloor> controlMap = new HashList<VOIllmineFloor>();
		// 关于设备信息
		HashList<VOIllminePoint> rlist = new HashList<VOIllminePoint>();
		try {
			// 得到查询的值
			// List list =
			// dbControlDataMgr.findIllumineControlData(buldId,groupId);

			RetCode rt = new RetCode();
			List list = null;
			Map map = new HashMap();
			map.put("groupId", groupId);
			map.put("buldId", buldId);
			rt = this.getData("findIllumineControlData", map);
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}

			if (list != null) {
				// 如果有值
				Map m = null;
				VOIllmineFloor floorObj = null;
				VOIllminePoint obj = null;
				VOShowPoint sp = null;
				String seq = null;
				String buildId = null;
				String equipId = null;
				String storeyDown = null;
				String storeyUp = null;
				String pointName = null;
				String projectPoint = null;
				String controlpoint = null;
				String record = null;
				String floor = null;
				for (int i = 0; i < list.size(); i++) {
					m = (Map) list.get(i);
					seq = m.get("inde") + "";
					buildId = m.get("build_id") + "";
					equipId = m.get("equip_id") + "";
					storeyDown = m.get("storey_num_down") + "";
					storeyUp = m.get("storey_num_up") + "";
					projectPoint = m.get("projectpoint") + "";
					controlpoint = m.get("controlpoint") + "";
					record = m.get("record") + "";
					floor = m.get("floor") + "";
					obj = rlist.find(equipId);
					// 设备
					if (obj == null) {
						// 用VOElevatorPoint做实体类
						obj = new VOIllminePoint(buildId, storeyDown, storeyUp,
								floor, equipId);
						obj.setShowPointList(new ArrayList<VOShowPoint>());
						// 放到集合中
						rlist.add(equipId, obj);
					}
					sp = new VOShowPoint(equipId, pointName, projectPoint,
							controlpoint, record);
					obj.getShowPointList().add(sp);

					floorObj = controlMap.find(floor);
					// 查询这个楼是否有次设备
					if (floorObj == null) {
						floorObj = new VOIllmineFloor(buildId, storeyDown,
								storeyUp, floor, 1);
						floorObj.setShowIllmineList(new ArrayList<VOIllminePoint>());
						controlMap.add(floor, floorObj);
					}
					// 查询时候有点位在里面
					if (floorObj.findEqPoint(equipId) == null) {
						floorObj.getShowIllmineList().add(obj);
					}

				}
			}
			rc.setObj(controlMap);
		} catch (Exception e) {
			log.error("DbControldataBusiness-->findIllmineControlData", e);
			e.printStackTrace();
		}
		return rc;

	}

	public Map getFindIllmineControlDataSql() {
		return PerformSQLUtil.get("findIllumineControlData", this);
	}

	/**
	 * 查询室外照度
	 * 
	 * @author wy
	 * @param groupId
	 * @return
	 */
	public RetCode findOutdoorIllumination() {
		// return dbControlDataMgr.findOutdoorIllumination();
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findElevatorControlData!");
		RetCode rt = new RetCode();
		List list = null;

		rt = this.getData("findOutdoorIllumination", null);
		return rt;
	}

	public Map getFindOutdoorIlluminationSql() {
		return PerformSQLUtil.get("findOutdoorIllumination", this);
	}
	
	/**
	 * 
	 * 新增加<风冷机组显示>和<水冷机组显示>【室外温度和室外湿度】
	 * 
	 * @author fm
	 * @param 
	 * @return
	 * @Date 2015-06-05
	 */
	public RetCode findAirFlSljzControl() {
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.findElevatorControlData!");
		RetCode rt = new RetCode();
		rt = this.getData("findAirFlSljzControl", null);
		return rt;
	}

}

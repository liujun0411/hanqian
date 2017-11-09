package com.hanqian.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BaseCommBusiness;
import com.hanqian.business.BuildStoreyBusiness;
import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.DBBuildingStoreyHisBusiness;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbBuildingStorey;
import com.hanqian.pojo.DbBuildingStoreyHis;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 刘新
 * @version 1.4 2012-9-25
 * @see
 */
public class BuildingStoreyAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(BuildingStoreyAction.class);
	private HttpServletRequest request;
	private List listStorey;
	private Integer buildingId;
	private BuildStoreyBusiness buildStoreyBusiness;
	private DBBuildingStoreyHisBusiness buildingStoreyHisBusiness;
	private BuildingBusiness buildingBusiness;
	private BaseCommBusiness baseCommBusiness;
	private String resultJSON; // JSON格式字符串
	
	
	/**
	 * 楼宇楼层面积详情列表
	 * @return
	 */
	public String buildingStorey() {
		request = ServletActionContext.getRequest();
		List valueList = new ArrayList();
		List remarksList = new ArrayList();
		List remarkList = new ArrayList();
		List seqList = new ArrayList();
		List areaIdList = new ArrayList();
		Map map = new HashMap();
		String buildsss=request.getParameter("buildingId");
		RetCode rc=new RetCode();
		if(StringUtils.isNotEmpty(buildsss)){
			buildingId=Integer.parseInt(buildsss);
		}
		rc = buildStoreyBusiness.callBuiildStorey(buildingId);
		if (rc.getObj() != null) {
			listStorey = (List) rc.getObj();
		}
		// 如果楼层信息为空，手动给该栋楼添加楼层信息
		if (listStorey == null) {
			// 添加操作
			// request.setAttribute("flag", true);
			DbBuilding building = buildingBusiness.findById(buildingId);
			int storeyNum = building.getStoreyNumUp();
			int storeyDown = building.getStoreyNumDown();
			List<Integer> stroeyList = new ArrayList();
			if (building != null && building.getBuildingId() != null) {
				for (int i = storeyNum; i > 0; i--) {
					stroeyList.add(i);
				}
				for (int i = 1; i <= storeyDown; i++) {
					stroeyList.add(0 - i);
				}
			}

			// 获得所有的区域ID
			List areaList = new ArrayList();
			RetCode r = baseCommBusiness.findAreaId();
			if (r.getObj() != null) {
				areaList = (List) r.getObj();
			}

			for (int i = 0; i < stroeyList.size(); i++) {
				for (int j = 0; j < areaList.size(); j++) {
					DbBuildingStorey buildingStorey = new DbBuildingStorey();
					building = new DbBuilding();
					DbBaseComm base = new DbBaseComm();
					DbUsers dbUsers = new DbUsers();
					dbUsers.setSeq(2);
					Map mapSeq = new HashMap();
					mapSeq = (Map) areaList.get(j);
					base.setSeq(Integer.parseInt(mapSeq.get("seq").toString()));
					DbHospInfo hospInfo = new DbHospInfo();
					building.setBuildingId(buildingId);
					hospInfo.setSeqHosp(1);
					buildingStorey.setDbBuilding(building);
					buildingStorey.setDbHospInfo(hospInfo);
					buildingStorey.setStatus((short) 0);
					buildingStorey.setOpertime(new Date());
					buildingStorey.setStorey(stroeyList.get(i));
					buildingStorey.setDbBaseComm(base);
					buildingStorey.setDbUsers(dbUsers);
					buildingStorey.setAcreage((double) 0);
					try {
						buildStoreyBusiness
								.insertBuildingStorey(buildingStorey);
					} catch (Exception e) {
						logg.error("BuildingStoreyAction-->buildingStorey", e);
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}

			for (int i = 0; i < stroeyList.size(); i++) {
				for (int j = 0; j < areaList.size(); j++) {
					DbBuildingStoreyHis buildingStoreyHis = new DbBuildingStoreyHis();
					building = new DbBuilding();
					DbBaseComm base = new DbBaseComm();
					DbUsers dbUsers = new DbUsers();
					dbUsers.setSeq(2);
					Map mapSeq = new HashMap();
					mapSeq = (Map) areaList.get(j);
					base.setSeq(Integer.parseInt(mapSeq.get("seq").toString()));
					DbHospInfo hospInfo = new DbHospInfo();
					building.setBuildingId(buildingId);
					hospInfo.setSeqHosp(1);
					buildingStoreyHis.setDbBuilding(building);
					buildingStoreyHis.setDbHospInfo(hospInfo);
					buildingStoreyHis.setStatus((short) 0);
					buildingStoreyHis.setOpertime(new Date());
					buildingStoreyHis.setStorey(stroeyList.get(i));
					buildingStoreyHis.setDbBaseComm(base);
					buildingStoreyHis.setOper(dbUsers.getSeq());
					buildingStoreyHis.setAcreage((double) 0);
					buildingStoreyHis.setRecordDate("201210");
					try {
						buildingStoreyHisBusiness.insert(buildingStoreyHis);
					} catch (Exception e) {
						logg.error("BuildingStoreyAction-->buildingStorey", e);
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}

			rc = buildStoreyBusiness.callBuiildStorey(buildingId);
		}
		if (rc.getObj() != null) {
			listStorey = (List) rc.getObj();
			// request.setAttribute("flag", false);
		}
		String[] columnName = new String[]{};
		List l = new ArrayList();
		List sList = new ArrayList();
		if (listStorey != null) {
			for (int i = 0; i < listStorey.size(); i++) {
				map = (HashMap) listStorey.get(i);
				columnName = (String[]) map.get("ColumnName");
				String val = "";
				valueList = new ArrayList();// 面积
				remarkList = new ArrayList();// 备注
				seqList = new ArrayList();// 主键
				for (int j = 0; j < columnName.length; j++) {
					String key = columnName[j];
					String values = "";
					Object vale = map.get(key);
					if (vale != null) {
						values = vale.toString();
					}
					if (values != null) {
						if (values.contains("#")) {
							val = values.substring(0, values.indexOf("#"));
							valueList.add(Double.parseDouble(val));
						} else {
							valueList.add(map.get(key));
						}

						String remarks = "";
						if (values.contains("#")) {
							remarks = values.substring(values.indexOf("#") + 1,
									values.indexOf("#",
											(values.indexOf("#") + 1)));
							remarkList.add(remarks);
						}

						int seq = 0;
						if (values.contains("#")) {
							String vl = values
									.substring(values.indexOf("#") + 1);
							vl = vl.substring(vl.indexOf("#") + 1, vl
									.lastIndexOf("#"));
							seqList.add(vl);
						} else if (values == null || values == "") {
							seqList.add(null);
						}

						int areaId = 0;
						if (values.contains("#")) {
							areaId = Integer.parseInt(values.substring(values
									.lastIndexOf("#") + 1));
							areaIdList.add(areaId);
						} else if (values == null || values == "") {
							areaIdList.add(null);
						}
					}
				}
				sList.add(seqList);
				l.add(valueList);
				remarksList.add(remarkList);
			}
		}
		Integer hospId=1;
		DbHospInfo hospInfo = new DbHospInfo();
		hospInfo.setSeqHosp(hospId);
		DbBuilding b=new DbBuilding();
		b.setDbHospInfo(hospInfo);
		RetCode rcc=buildingBusiness.findDBBuilding(b);
		DbBuilding build = buildingBusiness.findById(buildingId);
		request.setAttribute("buildingId", buildingId);
		request.setAttribute("seqList", sList);
		request.setAttribute("buildingName", build.getBuildingName());
		request.setAttribute("remarksList", remarksList);
		request.setAttribute("valueList", l);
		request.setAttribute("columnName", columnName);
		request.setAttribute("listStorey", listStorey);
		request.setAttribute("areaIdList", areaIdList);
		request.setAttribute("listB", rcc.getObj());
		return "input";
	}

	public String getHisStoreyDate() throws Exception {
		try{
		request = ServletActionContext.getRequest();
		String time = "failed";
		int storey = Integer.parseInt(request.getParameter("storey"));
		int buildingId = Integer.parseInt(request.getParameter("buildingId"));
		String _aeraId = request.getParameter("areaId");
		if(StringUtils.isBlank(_aeraId)){
			_aeraId = "0";
		}
		int areaId = Integer.parseInt(_aeraId);
		List dbHis = buildingStoreyHisBusiness.find(buildingId, storey, areaId);
		Map dbHisMap = new HashMap();
		if (null != dbHis && dbHis.size() > 0) {
			dbHisMap = (Map) dbHis.get(0);
			time = dbHisMap.get("record_date").toString();
		}
		resultJSON = time;
			ServletActionContext.getResponse().getWriter().print(resultJSON);
		}catch (Exception e) {
			logg.error("BuildingStoreyAction-->getHisStoreyDate", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改面积详情
	 * 
	 * @return
	 */
	public String updateStorey() throws Exception {
		String recordDate = "";
		request = ServletActionContext.getRequest();
		// Map<String, String> maps = new HashMap<String, String>();
		int seq = Integer.parseInt(request.getParameter("seq"));
		double acreage = Double
				.parseDouble(request.getParameter("mj").trim() == ""
						? "0.0"
						: request.getParameter("mj")); // 获得面积
		String remarks = request.getParameter("bz"); // 获得备注
		int buildingId = Integer.parseInt(request.getParameter("buildingId"));
		int storey = Integer.parseInt(request.getParameter("storey"));
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		String inputDate = request.getParameter("dt");
		DbUsers use = (DbUsers) request.getSession().getAttribute("users"); // 获得登陆用户对象
		DbUsers user = new DbUsers();
		user.setSeq(use.getSeq());
		List dbHis = buildingStoreyHisBusiness.find(buildingId, storey, areaId);
		Map dbHisMap = new HashMap();
		if (dbHis.size() > 0) {
			dbHisMap = (Map) dbHis.get(0);
			recordDate = dbHisMap.get("record_date").toString();
		}
		DbBuildingStoreyHis storeyHis = new DbBuildingStoreyHis();
		DbBuilding building = new DbBuilding();
		DbHospInfo dbHospInfo = new DbHospInfo();
		dbHospInfo.setSeqHosp(1);
		building.setBuildingId(buildingId);
		DbBaseComm base = new DbBaseComm();
		base.setSeq(areaId);
		if (inputDate.equals(recordDate)) {
			int seqHis = 0;
			Map hisMap = new HashMap();
			if (dbHis.size() > 0) {
				hisMap = (Map) dbHis.get(0);
				seqHis = Integer.parseInt(hisMap.get("seq").toString());
			}
			DbBuildingStoreyHis his = buildingStoreyHisBusiness
					.findBySeq(seqHis);
			his.setAcreage(acreage);
			his.setRemarks(remarks);
			his.setRecordDate(inputDate);
			buildingStoreyHisBusiness.update(his);
			
		} else {
			storeyHis.setDbBuilding(building);
			storeyHis.setAcreage(acreage);
			storeyHis.setDbHospInfo(dbHospInfo);
			storeyHis.setStorey(storey);
			storeyHis.setStatus((short) 0);
			storeyHis.setRecordDate(inputDate);
			storeyHis.setRemarks(remarks);
			storeyHis.setInput(user.getSeq());
			storeyHis.setInputtime(new Date());
			storeyHis.setOper(user.getSeq());
			storeyHis.setOpertime(new Date());
			storeyHis.setDbBaseComm(base);
			buildingStoreyHisBusiness.insert(storeyHis);
		}
		// 判断记录是否存在
		DbBuildingStorey dbss = buildStoreyBusiness.findBuildStorey(seq);
			dbss.setAcreage(acreage);
			dbss.setRemarks(remarks);
			dbss.setOpertime(new Date());

		if (dbss != null) { // 如果存在
			boolean result = buildStoreyBusiness.updateDbBuildingStorey(dbss);
			if (result) {
				resultJSON = "{'result':'success'}";
			} else {
				resultJSON = "{'result':'failed'}";
			}
		} else {

		}
		ServletActionContext.getResponse().getWriter().write(resultJSON);
		return null;
	}
	
	//取上报数据
	public void getBuilduse(HashMap reportMap, DbReportRate reportRate) {
		RetCode BuildUseCode = buildStoreyBusiness.reportBuildingStorey(reportRate
				.getReportdate());
		if (BuildUseCode.getObj() != null) {
			reportMap.put(reportRate.getDbReportType().getTypeId() + "",
					BuildUseCode.getObj());
		}
	}

	public BuildStoreyBusiness getBuildStoreyBusiness() {
		return buildStoreyBusiness;
	}
	public void setBuildStoreyBusiness(BuildStoreyBusiness buildStoreyBusiness) {
		this.buildStoreyBusiness = buildStoreyBusiness;
	}
	public Integer getbuildingId() {
		return buildingId;
	}
	public void setbuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public List getListStorey() {
		return listStorey;
	}
	public void setListStorey(List listStorey) {
		this.listStorey = listStorey;
	}
	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}
	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}

	public BaseCommBusiness getBaseCommBusiness() {
		return baseCommBusiness;
	}

	public void setBaseCommBusiness(BaseCommBusiness baseCommBusiness) {
		this.baseCommBusiness = baseCommBusiness;
	}

	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}
	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public DBBuildingStoreyHisBusiness getBuildingStoreyHisBusiness() {
		return buildingStoreyHisBusiness;
	}

	public void setBuildingStoreyHisBusiness(
			DBBuildingStoreyHisBusiness buildingStoreyHisBusiness) {
		this.buildingStoreyHisBusiness = buildingStoreyHisBusiness;
	}
	public static void main(String[] args) {
		String values = "0##1160#43";
		System.out.println(values.substring(values.indexOf("#") + 1, values
				.indexOf("#", (values.indexOf("#") + 1))));
	}
}

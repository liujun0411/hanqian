package com.hanqian.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jfree.util.Log;

import com.hanqian.business.BaseCommBusiness;
import com.hanqian.business.BuildMaterBusiness1;
import com.hanqian.business.BuildStoreyBusiness;
import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.BuildrepairBussiness;
import com.hanqian.business.DBBuildingStoreyHisBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbBaseType;
import com.hanqian.pojo.DbBuildMater;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbBuildingRepair;
import com.hanqian.pojo.DbBuildingStorey;
import com.hanqian.pojo.DbBuildingStoreyHis;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
//import com.hanqian.util.SysUtil;
import com.hanqian.util.SysUtil;

public class BuildDetailsAction {

	private BuildingBusiness buildingBusiness;
	private HospInfoBusiness hospBussiness;
	private BuildrepairBussiness repairMgr;
	private BuildStoreyBusiness storeyMgr;
	private BuildMaterBusiness1 materBusiness;
	private List<DbBuildMater> listMater;
	private List<DbHospInfo> listHosp;//医院集合
	private List<DbBuildingRepair> listRepair;
	private BaseCommBusiness baseCommBusiness;
	private List listArea;
	private List<DbBuilding> listBuild;
	private List<DbBuilding> listB;  //楼宇集合
	private Integer buildingId;//楼宇id
	private List listStorey;
	private BuildStoreyBusiness buildStoreyBusiness;
	private DBBuildingStoreyHisBusiness buildingStoreyHisBusiness;
	private List<DbBuildingRepair> builds; 
	private Integer hospid;
	private Map map;
	private String editFlag;
	private HttpServletRequest request;
	private String message;
	private String messages;
	private List<DbBuildingStorey> buildarea;   //楼宇区域面积
	private List<DbBuildingStorey> builduseArea;   //单个用途总面积
	private List hospitalArea;                //医院近期总面积
	private String showOrhide;
	private String myfirst;            //首界面的action
	private String hideOrshow;
	private String tag;
    private MenuInterceptor menuInterceptor;
    
    private DbBuilding build=new DbBuilding();
    
    private DbBuildMater mater=new DbBuildMater();
    
    private Map sqlmap = new HashMap();
    
    private Map seqMap = new HashMap();
	
	private Map outpatientMap = new HashMap();
	
	private Map emergencyMap = new HashMap();
	
	private Map hospitalizationMap = new HashMap();
	
	private Map medicalMap = new HashMap();
	
	private Map securityMap = new HashMap();
	
	private Map administrationMap = new HashMap();
	
	private Map scientificMap = new HashMap();
	
	private Map educationMap = new HashMap();
	
	private Map lifeMap = new HashMap();
	
	private Map garageMap = new HashMap();
	
	private Map othersMap = new HashMap();
	
	private Map remindmapAll = new HashMap();
	
	private Map sqlmapJB = new HashMap();
	
	private Map totalAreaMap = new HashMap();
	
	private Map buildTotalAreaMap = new HashMap();
	
	private Map amountMap = new HashMap();
	
	private Map completimeMap = new HashMap();
	
	private Map structureMap = new HashMap();
	
	private Map storeyNumMap = new HashMap();
	
	private Map buildMaterByQuakeproofMap = new HashMap();
	
	private Map heightMap = new HashMap();
	
	private Map remindmapAllJB = new HashMap();
	
	private Map sqlmapJG = new HashMap();
	
	private Map buildMaterByCostaccordMap = new HashMap();
	
	private Map proCostMap = new HashMap();
	
	private Map auditsMap = new HashMap();
	
	private Map mendNumMap = new HashMap();
	
	private Map buildMaterByProblemMap = new HashMap();
	
	private Map structuresMap = new HashMap();
	
	private Map outWallMap = new HashMap();
	
	private Map doorMaterMap = new HashMap();
	
	private Map windowMaterMap = new HashMap();
	
	private Map ceilingMaterMap = new HashMap();
	
	private Map wallMaterMap = new HashMap();
	
	private Map floorMaterMap = new HashMap();
	
	private Map buildMaterByWaterproofMap = new HashMap();
	
	private Map buildMaterByQuakeproofsMap = new HashMap();
	
	private Map remindmapAllJG = new HashMap();
	
	private Map menuMap;
	private static final Logger logg = Logger.getLogger(BuildDetailsAction.class);
	
	/**
	 * 获取楼宇信息
	 * @return
	 */
	private String transString(List<DbBuilding> listBuild){
		String result = "";
		Map map = null;
		if(listBuild != null && listBuild.size()>0){
			map = new HashMap();
			try {
				for(DbBuilding build : listBuild){
					if(map.get(build.getDbHospInfo().getSeqHosp()) == null){
						StringBuffer sb = new StringBuffer();
						sb.append(build.getDbHospInfo().getSeqHosp() 
								+ ":{builds:[{id:\""+build.getBuildingId()+"\",name:\"" 
								+ build.getBuildingName()+ "\"}");
						map.put(build.getDbHospInfo().getSeqHosp(), sb);
					} else {
						StringBuffer sb = (StringBuffer) map.get(build.getDbHospInfo().getSeqHosp());
						sb.append(",{id:\""+build.getBuildingId()+"\",name:\"" 
								+ build.getBuildingName()+ "\"}");
					}
				}
				
			} catch (Exception e) {
				logg.error("BuildDetailsAction-->transString",e);
			}
		}
		if(map != null && !map.isEmpty()){
			Iterator iter = map.values().iterator();
			StringBuffer sb = new StringBuffer();
			while(iter.hasNext()){
				sb.append(iter.next().toString());
				sb.append("]},");
			}
			String sss = sb.toString();
			int len = sss.length() -1;
			result = "{"+sss.substring(0, len) + "};";
		}
		return result;
	}
	
	
	/**
	 * 
	 * 
	 */
	private void findBuildDetails() {
		request = ServletActionContext.getRequest();
		if (request.getParameter("buildingId")!=null) {
			buildingId = Integer.parseInt(request.getParameter("buildingId"));
		}
		String tabIndex=request.getParameter("tabIndex");
		if(StringUtil.isNotEmpty(tabIndex)){
			request.setAttribute("tabIndex", tabIndex);
		}else{
			request.setAttribute("tabIndex", "1");
		}
		// 查询医院信息，获取医院名称
		DbHospInfo dbhosp =new DbHospInfo();
		dbhosp.setSeqHosp(1);
		RetCode rtHos = hospBussiness.findHospInfo(dbhosp);
		if (rtHos.getObj() != null) {
			listHosp = (List<DbHospInfo>) rtHos.getObj();
		}
		
		//获取医院所有楼宇
		RetCode rtBuild = buildingBusiness.findDBBuilding(new DbBuilding());
		if(rtBuild.getObj() != null){
			
			listBuild = (List<DbBuilding>) rtBuild.getObj();
			for (int i = 0; i < listBuild.size(); i++) {
				logg.debug("       listBuild=           "+listBuild.get(i));
			}
			try {
				//转换成医院对应楼宇
				String builds = transString(listBuild);
				request.setAttribute("buildMap", builds);
				
			} catch (Exception e) {
				logg.error("BuildDetailsAction-->transString", e);
			}
		}

		
		//根据医院ID查询楼宇
		if(request.getSession().getAttribute("users")!=null){
			DbUsers user = (DbUsers) request.getSession().getAttribute("users");
			hospid = user.getDbHospInfo().getSeqHosp();
		}
		if (!SysUtil.isNull(hospid.toString())) {
			DbHospInfo hospInfo = new DbHospInfo();
			hospInfo.setSeqHosp(hospid);
			DbBuilding b=new DbBuilding();
			b.setDbHospInfo(hospInfo);
			RetCode rcc=buildingBusiness.findDBBuilding(b);
			
			if(rcc.getObj()!=null){
				listB=(List<DbBuilding>) rcc.getObj();
				if(buildingId==null){
					buildingId=listB.get(0).getBuildingId();
				}
			}
			DbBuilding bui = new DbBuilding();
			if (!SysUtil.isNull(buildingId.toString())) {
				bui.setBuildingId(buildingId);
			}else if(listB!=null){
				bui.setBuildingId(listB.get(0).getBuildingId());
			}
			// 查询楼宇区域信息
			buildarea=storeyMgr.findBuildingGroup(buildingId);
		
			//获取单个用途总面积
			builduseArea=storeyMgr.findBuildinguseArea();
		
			//获取医院总面积
			hospitalArea=storeyMgr.findHospInfo(hospid);
				if (bui != null) {
					DbBuildingStorey storey = new DbBuildingStorey();
					storey.setDbBuilding(bui);
					HttpServletRequest request = ServletActionContext.getRequest();
					request.setAttribute("LIST", map);
				
					// 查询楼宇维修信息
					
					
					DbBuildingRepair repair=new DbBuildingRepair();
					DbBuilding dbBuilding=new DbBuilding();
					dbBuilding.setBuildingId(buildingId);
					repair.setDbBuilding(dbBuilding);
					RetCode rtRepair = repairMgr.findBuildingRepair(repair);
					if (rtRepair.getObj() != null) {
						listRepair = (List<DbBuildingRepair>) rtRepair.getObj();
					}
				}
				
				RetCode rc=buildingBusiness.findDBBuilding(bui);
				if(rc.getObj()!=null){
					List<DbBuilding> list=new ArrayList<DbBuilding>();
					list=(List<DbBuilding>) rc.getObj();
					logg.debug("!!!!!!list.get(0).getBuildingId()="+list.get(0).getBuildingId());
					request.setAttribute("HOS", list.get(0).getDbHospInfo().getHospName());  //医院名称
					request.setAttribute("BUI", list.get(0).getBuildingName());  //楼宇名称
					request.setAttribute("BUIAREA",list.get(0).getBuildingAreas() );  //楼宇总面积
					request.setAttribute("hospid", list.get(0).getDbHospInfo().getSeqHosp());  //医院ID
					request.setAttribute("buildingId", list.get(0).getBuildingId());  //楼宇ID
				}
			
		}
		
		getDbMater();
		// 查询用途
		DbBaseComm baseComm=new DbBaseComm();
		DbBaseType dbBaseType=new DbBaseType();
		dbBaseType.setSeq(SysUtil.toInt(SysUtil.BASE_COMM_HOSP_AREA));
		baseComm.setDbBaseType(dbBaseType);
		RetCode rcArea =baseCommBusiness.findArea(baseComm);
		if (rcArea.getObj() != null) {
			listArea = (List) rcArea.getObj();
		}
	}
	
	/**
	 * 获取医院的所有楼宇
	 * @return
	 */
	public String findBuild(){
		Map maps;
		request=ServletActionContext.getRequest();
		if(request.getSession().getAttribute("users")!=null){
			DbUsers user = (DbUsers) request.getSession().getAttribute("users");
			hospid = user.getDbHospInfo().getSeqHosp();
		}
		String tabIndex=request.getParameter("tabIndex");
		if(StringUtil.isNotEmpty(tabIndex)){
			request.setAttribute("tabIndex", tabIndex);
		}else{
			request.setAttribute("tabIndex", "1");
		}
		logg.info("tabIndex="+tabIndex+";hospid="+hospid);
		DbHospInfo hospInfo = new DbHospInfo();
		hospInfo.setSeqHosp(hospid);
		DbBuilding b=new DbBuilding();
		b.setDbHospInfo(hospInfo);
		RetCode rcc=buildingBusiness.findDBBuilding(b);
		
		
		String hospId=request.getParameter("hospid");
		if(StringUtils.isNotEmpty(hospId)){
			hospid=Integer.parseInt(hospId);
		}
		request.setAttribute("hospid", hospid);
		if(rcc.getObj()!=null){
			listB=(List<DbBuilding>) rcc.getObj();
			if(buildingId==null){
				buildingId=(Integer)listB.get(0).getBuildingId();
			}
		}
		DbBuilding bui = new DbBuilding();
		if (buildingId!=null) {
			bui.setBuildingId(buildingId);
		}
		RetCode c=buildingBusiness.findDBBuilding(bui);
		if(c.getObj()!=null){
			List<DbBuilding> list=new ArrayList<DbBuilding>();
		    list=(List<DbBuilding>) c.getObj();
		    request.setAttribute("BUIAREA",list.get(0).getBuildingAreas() );  //楼宇总面积
		}
		if (buildingId!=null) {
			// 根据楼宇id查询各区域的总面积
//			List<Map> buildarea2=storeyMgr.findBuildingGroup(buildingId);
//			for (Map map:buildarea2){
//				map.put("mj", Double.valueOf((String) map.get("mj")));
//				logg.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+map);
//				logg.debug("******** type="+map.get("mj").getClass());
//			}
			buildarea=storeyMgr.findBuildingGroup(buildingId);
			request.setAttribute("buildarea", buildarea);
		}
		logg.info("根据楼宇id查询各区域的总面积：buildarea="+buildarea);
		
		
		//获取单个区域的总面积
		List<Map> builduseArea2 = storeyMgr.findBuildinguseArea();
		try {
			for (Map map:builduseArea2){
				map.put("mj",map.get("mj"));
			}
		}catch(Exception e){
			logg.debug(e);
		}
		request.setAttribute("builduseArea", builduseArea2);
		logg.info("获取单个区域的总面积：builduseArea="+builduseArea2);
		
		//获取医院总面积
		List<Map> hospitalArea=storeyMgr.findHospInfo(hospid);
		for (Map map:hospitalArea){
			map.put("mj", map.get("mj"));
		}
		request.setAttribute("hospitalArea", hospitalArea);
		logg.info("获取医院的总面积：builduseArea="+hospitalArea);
		DbBuildingRepair buildRepair=new DbBuildingRepair();
		DbBuilding dbBuilding=new DbBuilding();
		if (buildingId!=null) {
			dbBuilding.setBuildingId(buildingId);
			buildRepair.setDbBuilding(dbBuilding);
		}
		RetCode retCode = repairMgr.findBuildingRepair(buildRepair);
		if(retCode!=null&&retCode.getObj()!=null){
			listRepair = (List<DbBuildingRepair>) retCode.getObj();
		}
		List valueList = new ArrayList();
		List remarksList = new ArrayList();
		List remarkList = new ArrayList();
		List seqList = new ArrayList();
		List areaIdList = new ArrayList();
		Map map = new HashMap();
		if (buildingId!=null) {
		RetCode rc = buildStoreyBusiness.callBuiildStorey(buildingId);
		if (rc!=null&&rc.getObj() != null) {
			listStorey = (List) rc.getObj();
		}
		// 如果楼层信息为空，手动给该栋楼添加楼层信息
		if (listStorey == null) {
			// 添加操作
			// request.setAttribute("flag", true);
			DbBuilding building = new DbBuilding();
			int storeyNum = 0;
			int storeyDown = 0;
			if (buildingId!=null) {
				building = buildingBusiness.findById(buildingId);
				storeyNum = building.getStoreyNumUp();
				storeyDown = building.getStoreyNumDown();
			}
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
					DbHospInfo hospInfo1 = new DbHospInfo();
					building.setBuildingId(buildingId);
					hospInfo1.setSeqHosp(1);
					buildingStorey.setDbBuilding(building);
					buildingStorey.setDbHospInfo(hospInfo1);
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
						logg.error("BuildDetailsAction-->findBuild", e);
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
					DbHospInfo hospInfo2 = new DbHospInfo();
					building.setBuildingId(buildingId);
					hospInfo2.setSeqHosp(1);
					buildingStoreyHis.setDbBuilding(building);
					buildingStoreyHis.setDbHospInfo(hospInfo2);
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
						logg.error("BuildDetailsAction-->findBuild", e);
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}

			rc = buildStoreyBusiness.callBuiildStorey(buildingId);
		}
		if (rc!=null&&rc.getObj() != null) {
			listStorey = (List) rc.getObj();
			// request.setAttribute("flag", false);
		}
		
		String[] columnName = new String[]{};
		List l = new ArrayList();
		List sList = new ArrayList();
		if (listStorey != null) {
			for (int i = 0; i < listStorey.size(); i++) {
				map = (Map) listStorey.get(i);
				columnName = (String[]) map.get("columnname");

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
							if(StringUtil.isEmpty(val)){
								valueList.add(0);
							}else{
								valueList.add(Double.parseDouble(val));
							}
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
		request.setAttribute("remarksList", remarksList);
		request.setAttribute("seqList", sList);
		request.setAttribute("valueList", l);
		//进行columnName排序 楼层,门诊,急诊,住院,医技,保障,行政,科研,教育,生活,车库,其他
		String[] columnName2 = new String[columnName.length];
		for (int j = columnName.length; j >= 0; j--) {
			for (int i = 0; i < columnName.length; i++) {
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("楼层"))){
					columnName2[0] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("门诊"))){
					columnName2[1] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("急诊"))){
					columnName2[2] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("住院"))){
					columnName2[3] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("医技"))){
					columnName2[4] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("保障"))){
					columnName2[5] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("行政"))){
					columnName2[6] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("科研"))){
					columnName2[7] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("教育"))){
					columnName2[8] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("生活"))){
					columnName2[9] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("车库"))){
					columnName2[10] =columnName[i];
				}
				if(StringUtils.trim(columnName[i]).equals(StringUtils.trim("其他"))){
					columnName2[columnName.length-1] =columnName[i];
				}
			}
		}
		
		request.setAttribute("columnName", columnName2);
		}
		DbHospInfo hospInfo3 = new DbHospInfo();
		hospInfo3.setSeqHosp(hospid);
		DbBuilding building=new DbBuilding();
		building.setDbHospInfo(hospInfo3);
		RetCode rccc=buildingBusiness.findDBBuilding(building);
		DbBuilding build = new DbBuilding();
		String buildingName = "";
		if (buildingId!=null) {
			build = buildingBusiness.findById(buildingId);
			buildingName = build.getBuildingName() ;
		}
		request.setAttribute("buildingId", buildingId);
		
//		request.setAttribute("buildingName",buildingName );
//		request.setAttribute("remarksList", remarksList);
//		request.setAttribute("valueList", l);
//		request.setAttribute("columnName", columnName);
		request.setAttribute("listStorey", listStorey);
		request.setAttribute("areaIdList", areaIdList);
		
		
		if(rccc!=null&&rccc.getObj()!=null){
			request.setAttribute("listB", rccc.getObj());
		}else{
			request.setAttribute("listB", null);
		}
		
		
		//调用抽取任意提醒需要的数据的方法
		remindBuildInfoDate();
		
		return "input";
	}
	
	/**
	 * 根据楼宇ID医院ID获取楼宇信息
	 * *//*
	public String findbyBuildId() {
		request=ServletActionContext.getRequest();
		buildingId=Integer.parseInt(request.getParameter("buildingId"));
		//request.setAttribute("shows", request.getParameter("shows"));
		Integer hospid=1;
		if(!SysUtil.isNull(hospid.toString())){
			listBuild=new ArrayList<DbBuilding>();
			DbHospInfo hospinfo=new DbHospInfo();
			hospinfo.setSeqHosp(hospid);
			
			// 根据楼宇id查询各区域的总面积
			buildarea=storeyMgr.findBuildingGroup(buildingId);
			request.setAttribute("buildarea", buildarea);
			
		
			//获取单个区域的总面积
			builduseArea=storeyMgr.findBuildinguseArea();
			request.setAttribute("builduseArea", builduseArea);
		
			//获取医院总面积
			hospitalArea=storeyMgr.findHospInfo(hospid);
			request.setAttribute("hospitalArea", hospitalArea);
			
			
			DbBuildingRepair buildRepair=new DbBuildingRepair();
			DbBuilding dbBuilding=new DbBuilding();
			dbBuilding.setBuildingId(buildingId);
			buildRepair.setDbBuilding(dbBuilding);
			RetCode retCode = repairMgr.findBuildingRepair(buildRepair);
			if(retCode.getObj()!=null){
				listRepair = (List<DbBuildingRepair>) retCode.getObj();
			}
			DbBuilding build=new DbBuilding();
			build.setBuildingId(Integer.parseInt(request.getParameter("buildingId")));
			build.setDbHospInfo(hospinfo);
			RetCode rc=buildingBusiness.findDBBuilding(build);
			if(rc.getObj()!=null){
				listBuild=(List<DbBuilding>) rc.getObj();
			}
			request.setAttribute("BUIAREA",listBuild.get(0).getBuildingAreas() );  //楼宇总面积
		}
		getDbMater();
		Integer hospId=1;
		DbHospInfo hospInfo = new DbHospInfo();
		hospInfo.setSeqHosp(hospId);
		DbBuilding b=new DbBuilding();
		b.setDbHospInfo(hospInfo);
		RetCode rcc=buildingBusiness.findDBBuilding(b);
		if(rcc.getObj()!=null){
			listB=(List<DbBuilding>) rcc.getObj();
		}
		request.setAttribute("buildingId", buildingId);
		
		return "input";
	}*/
	
	
	
	/**
	 *  获取楼宇基本信息
	 * @return
	 */

	public String showBuildingbasic(){
		getListBuilds();
		remindBuildInfoDate();
		return "lyxqJB";
	} 
	
	/**
	 * 获取楼宇结构信息和大修信息
	 * */
	public String showBuildStruct(){
		menuMap=menuInterceptor.menuIntercept("2002003004");
		//若为空 ，进入登录界面
		if(menuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}
		// 获取楼宇材料字典表
		getDbMater();
		getListBuilds();
		remindBuildInfoDate();
		return "lyxqJG";
	}
	

	/**
	 * 获取楼宇材料字典表
	 * */
	private void getDbMater() {
		try{
			RetCode rc1 = materBusiness.findBuildingMater(mater);
			listMater = (List<DbBuildMater>) rc1.getObj();
		}catch(Exception e){
			logg.error("BuildDetailsAction-->getDbMater", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据楼宇ID医院ID获取楼宇信息
	 * */
	private void getListBuilds() {
		int currentPage = 1;
		int pageSize = 10;
		request=ServletActionContext.getRequest();
		String buildId=request.getParameter("buildingId");
		String tabIndex=request.getParameter("tabIndex");
		if(StringUtil.isNotEmpty(tabIndex)){
			request.setAttribute("tabIndex", tabIndex);
		}
		if(StringUtils.isNotEmpty(buildId)){
			buildingId=Integer.parseInt(buildId);
		}
		if(buildingId!=null){
		hospid=buildingBusiness.findById(buildingId).getDbHospInfo().getSeqHosp();
		request.setAttribute("shows", request.getParameter("shows"));
		if(!SysUtil.isNull(hospid.toString())){
			
			DbBuildingRepair repair = new DbBuildingRepair();
			RetCode reCode = repairMgr.findBuildingRe(repair);
			builds = (List<DbBuildingRepair>) reCode.getObj();
			listBuild=new ArrayList<DbBuilding>();
			DbHospInfo hospinfo=new DbHospInfo();
			hospinfo.setSeqHosp(hospid);
			// 查询楼宇区域信息
			buildarea=storeyMgr.findBuildingGroup(buildingId);
			//获取单个用途总面积
			builduseArea=storeyMgr.findBuildinguseArea();
			//获取医院总面积
			hospitalArea=storeyMgr.findHospInfo(hospid);//获取医院总面积
			
			
			DbBuildingRepair buildRepair=new DbBuildingRepair();
			DbBuilding dbBuilding=new DbBuilding();
			dbBuilding.setBuildingId(buildingId);
			buildRepair.setDbBuilding(dbBuilding);
			String beginDate = null;
			String endDate = null;
			RetCode retCode = repairMgr.findBuildingRepair(buildRepair, beginDate, endDate, currentPage, pageSize);
			if(retCode.getObj()!=null){
				listRepair = (List<DbBuildingRepair>) retCode.getObj();
			}
			build.setBuildingId(Integer.parseInt(request.getParameter("buildingId")));
			RetCode rc=buildingBusiness.findDBBuilding(build);
			if(rc.getObj()!=null){
				listBuild=(List<DbBuilding>) rc.getObj();
			
				String struts=listBuild.get(0).getStructure();
			
			String CNStrurts="";
			String to="";
			if(StringUtils.isNotEmpty(struts)){
				//if(struts.indexOf(",")>0){
					//strutures=struts.split(",");
					//for(int i=0;i<strutures.length;i++){
						//根据Id获得建材结构名称
						CNStrurts=materBusiness.findById(Integer.parseInt(struts)).getMaterName();
						//to+=CNStrurts+",";
					//}
					//钢结构,框架结构
					//to=to.substring(0, to.lastIndexOf(","));
				//}
			}
			listBuild.get(0).setStructure(CNStrurts);
			
			
			String ceilingMater=listBuild.get(0).getCeilingMater();
			String CNcelilingMater="";
			String tn="";
			String [] CelilingMateres=null; 
			if(StringUtils.isNotEmpty(ceilingMater)){
				System.out.println(ceilingMater.indexOf(","));
				if(ceilingMater.indexOf(",")>0){
					CelilingMateres=ceilingMater.split(",");
					for(int i=0;i<CelilingMateres.length;i++){
						//根据Id获得建材结构名称
						CNcelilingMater=materBusiness.findById(Integer.parseInt(CelilingMateres[i].trim())).getMaterName();
						tn+=CNcelilingMater+",";
					}
					//钢结构,框架结构
					tn=tn.substring(0, tn.lastIndexOf(","));
					listBuild.get(0).setCeilingMater(tn);
				}
			}
			
			request.setAttribute("BUIAREA",listBuild.get(0).getBuildingAreas() );  //
			}
		}
		getDbMater();
		request.setAttribute("buildingId", buildingId);
		}
	}
	
	/**
	 * 将任意提醒的数据打包成map
	 * @param 
	 * @param
	 * @return
	 */
	public String remindBuildInfoDate() {

		request = ServletActionContext.getRequest();
		try {
			buildStoreyBusiness.findBuildinguseArea();
			//获取医院详情sql对象放到Map对象中
			sqlmap  = storeyMgr.getFindBuildingGroupSql();
			
			logg.debug("***************sqlmap="+sqlmap);
			
			RetCode rcc=buildingBusiness.findDBBuilding(build);
			
			
			if(rcc.getObj()!=null){
				listB=(List<DbBuilding>) rcc.getObj();
				if(buildingId==null){
					buildingId=listB.get(0).getBuildingId();
				}
			}
			seqMap.put("buildingId", buildingId);
			
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			outpatientMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			outpatientMap.put("sqlField", "outpatient");
			//中文字段名
			outpatientMap.put("fieldNm", "门诊");
			//输入类型 ：1-文本;2-数字;3-时间
			outpatientMap.put("ariesInputType", 2);
			//sql
			outpatientMap.putAll(sqlmap);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			outpatientMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			outpatientMap.put("updateDateField", "inputtime");
			//用来判断数据是否更新的sql
			outpatientMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
//			remindmap.put("initFunc", "testDate");

			//（emergency）
			emergencyMap.put("ariesPreForm", "1,2,3");
			emergencyMap.put("sqlField", "emergency");
			emergencyMap.put("fieldNm", "急诊");
			emergencyMap.put("ariesInputType", 2);
			emergencyMap.putAll(sqlmap);
			emergencyMap.put("sqlFieldKey", seqMap);
			emergencyMap.put("updateDateField", "inputtime");
			emergencyMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（hospitalization）
			hospitalizationMap.put("ariesPreForm", "1,2,3");
			hospitalizationMap.put("sqlField", "hospitalization");
			hospitalizationMap.put("fieldNm", "住院");
			hospitalizationMap.put("ariesInputType", 2);
			hospitalizationMap.putAll(sqlmap);
			hospitalizationMap.put("sqlFieldKey", seqMap);
			hospitalizationMap.put("updateDateField", "inputtime");
			hospitalizationMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（medical）
			medicalMap.put("ariesPreForm", "1,2,3");
			medicalMap.put("sqlField", "medical");
			medicalMap.put("fieldNm", "医技");
			medicalMap.put("ariesInputType", 2);
			medicalMap.putAll(sqlmap);
			medicalMap.put("sqlFieldKey", seqMap);
			medicalMap.put("updateDateField", "inputtime");
			medicalMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（security）
			securityMap.put("ariesPreForm", "1,2,3");
			securityMap.put("sqlField", "security");
			securityMap.put("fieldNm", "保障");
			securityMap.put("ariesInputType", 2);
			securityMap.putAll(sqlmap);
			securityMap.put("sqlFieldKey", seqMap);
			securityMap.put("updateDateField", "inputtime");
			securityMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（administration）
			administrationMap.put("ariesPreForm", "1,2,3");
			administrationMap.put("sqlField", "administration");
			administrationMap.put("fieldNm", "行政");
			administrationMap.put("ariesInputType", 2);
			administrationMap.putAll(sqlmap);
			administrationMap.put("sqlFieldKey", seqMap);
			administrationMap.put("updateDateField", "inputtime");
			administrationMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（scientific）
			scientificMap.put("ariesPreForm", "1,2,3");
			scientificMap.put("sqlField", "scientific");
			scientificMap.put("fieldNm", "科研");
			scientificMap.put("ariesInputType", 2);
			scientificMap.putAll(sqlmap);
			scientificMap.put("sqlFieldKey", seqMap);
			scientificMap.put("updateDateField", "inputtime");
			scientificMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（education）
			educationMap.put("ariesPreForm", "1,2,3");
			educationMap.put("sqlField", "education");
			educationMap.put("fieldNm", "教育");
			educationMap.put("ariesInputType", 2);
			educationMap.putAll(sqlmap);
			educationMap.put("sqlFieldKey", seqMap);
			educationMap.put("updateDateField", "inputtime");
			educationMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（life）
			lifeMap.put("ariesPreForm", "1,2,3");
			lifeMap.put("sqlField", "life");
			lifeMap.put("fieldNm", "生活");
			lifeMap.put("ariesInputType", 2);
			lifeMap.putAll(sqlmap);
			lifeMap.put("sqlFieldKey", seqMap);
			lifeMap.put("updateDateField", "inputtime");
			lifeMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（garage）
			garageMap.put("ariesPreForm", "1,2,3");
			garageMap.put("sqlField", "garage");
			garageMap.put("fieldNm", "车库");
			garageMap.put("ariesInputType", 2);
			garageMap.putAll(sqlmap);
			garageMap.put("sqlFieldKey", seqMap);
			garageMap.put("updateDateField", "inputtime");
			garageMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			//（others）
			othersMap.put("ariesPreForm", "1,2,3");
			othersMap.put("sqlField", "others");
			othersMap.put("fieldNm", "其它");
			othersMap.put("ariesInputType", 2);
			othersMap.putAll(sqlmap);
			othersMap.put("sqlFieldKey", seqMap);
			othersMap.put("updateDateField", "inputtime");
			othersMap.put("updateFieldSql", "select sum(ACREAGE) as mj from DB_BUILDING_STOREY  group by AREA_ID order by AREA_ID");
			
			
			//将所有需要提醒的数据打包成map
			remindmapAll.put("outpatient", outpatientMap);
			remindmapAll.put("emergency", emergencyMap);
			remindmapAll.put("hospitalization", hospitalizationMap);
			remindmapAll.put("medical", medicalMap);
			remindmapAll.put("security", securityMap);
			remindmapAll.put("administration", administrationMap);
			remindmapAll.put("scientific", scientificMap);
			remindmapAll.put("education", educationMap);
			remindmapAll.put("life", lifeMap);
			remindmapAll.put("garage", garageMap);
			remindmapAll.put("others", othersMap);
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			/**
			 * lyxqJB
			 */
			build.setBuildingId(buildingId);
			buildingBusiness.findDBBuilding(build);
			sqlmapJB = buildingBusiness.getFindDBBuildingSql();
			
			//（totalArea）
			totalAreaMap.put("ariesPreForm", "1,2,3");
			totalAreaMap.put("sqlField", "totalArea");
			totalAreaMap.put("fieldNm", "总面积");
			totalAreaMap.put("ariesInputType", 2);
			totalAreaMap.putAll(sqlmapJB);
			totalAreaMap.put("sqlFieldKey", seqMap);
			totalAreaMap.put("updateDateField", "inputtime");
			totalAreaMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（ buildTotalArea）
			buildTotalAreaMap.put("ariesPreForm", "1,2,3");
			buildTotalAreaMap.put("sqlField", "buildTotalArea");
			buildTotalAreaMap.put("fieldNm", "楼宇占医院总面积");
			buildTotalAreaMap.put("ariesInputType", 2);
			buildTotalAreaMap.putAll(sqlmapJB);
			buildTotalAreaMap.put("sqlFieldKey", seqMap);
			buildTotalAreaMap.put("updateDateField", "inputtime");
			buildTotalAreaMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（amount）
			amountMap.put("ariesPreForm", "1,2,3");
			amountMap.put("sqlField", "amount");
			amountMap.put("fieldNm", "投资额");
			amountMap.put("ariesInputType", 2);
			amountMap.putAll(sqlmapJB);
			amountMap.put("sqlFieldKey", seqMap);
			amountMap.put("updateDateField", "inputtime");
			amountMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（completime）
			completimeMap.put("ariesPreForm", "1,2,3");
			completimeMap.put("sqlField", "completime");
			completimeMap.put("fieldNm", "竣工时间");
			completimeMap.put("ariesInputType", 3);
			completimeMap.putAll(sqlmapJB);
			completimeMap.put("sqlFieldKey", seqMap);
			completimeMap.put("updateDateField", "inputtime");
			completimeMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（structure）
			structureMap.put("ariesPreForm", "1,2,3");
			structureMap.put("sqlField", "structure");
			structureMap.put("fieldNm", "建筑结构");
			structureMap.put("ariesInputType", 1);
			structureMap.putAll(sqlmapJB);
			structureMap.put("sqlFieldKey", seqMap);
			structureMap.put("updateDateField", "inputtime");
			structureMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（storeyNum）
			storeyNumMap.put("ariesPreForm", "1,2,3");
			storeyNumMap.put("sqlField", "storeyNum");
			storeyNumMap.put("fieldNm", "楼层总数");
			storeyNumMap.put("ariesInputType", 2);
			storeyNumMap.putAll(sqlmapJB);
			storeyNumMap.put("sqlFieldKey", seqMap);
			storeyNumMap.put("updateDateField", "inputtime");
			storeyNumMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（buildMaterByQuakeproof）
			buildMaterByQuakeproofMap.put("ariesPreForm", "1,2,3");
			buildMaterByQuakeproofMap.put("sqlField", "buildMaterByQuakeproof");
			buildMaterByQuakeproofMap.put("fieldNm", "抗震烈度");
			buildMaterByQuakeproofMap.put("ariesInputType", 2);
			buildMaterByQuakeproofMap.putAll(sqlmapJB);
			buildMaterByQuakeproofMap.put("sqlFieldKey", seqMap);
			buildMaterByQuakeproofMap.put("updateDateField", "inputtime");
			buildMaterByQuakeproofMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（buildHeight）
			heightMap.put("ariesPreForm", "1,2,3");
			heightMap.put("sqlField", "buildHeight");
			heightMap.put("fieldNm", "高度");
			heightMap.put("ariesInputType", 2);
			heightMap.putAll(sqlmapJB);
			heightMap.put("sqlFieldKey", seqMap);
			heightMap.put("updateDateField", "inputtime");
			heightMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			remindmapAllJB.put("totalArea", totalAreaMap);
			remindmapAllJB.put("buildTotalArea", buildTotalAreaMap);
			remindmapAllJB.put("amount", amountMap);
			remindmapAllJB.put("completime", completimeMap);
			remindmapAllJB.put("structure", structureMap);
			remindmapAllJB.put("storeyNum", storeyNumMap);
			remindmapAllJB.put("buildMaterByQuakeproof", buildMaterByQuakeproofMap);
			remindmapAllJB.put("buildHeight", heightMap);
			
			JSONObject remindJsonJB = JSONObject.fromObject(remindmapAllJB);
			
			
			/**
			 * lyxqJG
			 */
//			materBusiness.findBuildingMater(mater);
			
//			sqlmapJG = materBusiness.getFindBuildingMaterSql();
			
			//（buildMaterByCostaccord）
			buildMaterByCostaccordMap.put("ariesPreForm", "1,2,3");
			buildMaterByCostaccordMap.put("sqlField", "buildMaterByCostaccord");
			buildMaterByCostaccordMap.put("fieldNm", "造价依据");
			buildMaterByCostaccordMap.put("ariesInputType", 1);
			buildMaterByCostaccordMap.putAll(sqlmapJB);
			buildMaterByCostaccordMap.put("sqlFieldKey", seqMap);
			buildMaterByCostaccordMap.put("updateDateField", "inputtime");
			buildMaterByCostaccordMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			//（ proCost）
			proCostMap.put("ariesPreForm", "1,2,3");
			proCostMap.put("sqlField", "proCost");
			proCostMap.put("fieldNm", "建安工程造价");
			proCostMap.put("ariesInputType", 2);
			proCostMap.putAll(sqlmapJB);
			proCostMap.put("sqlFieldKey", seqMap);
			proCostMap.put("updateDateField", "inputtime");
			proCostMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（amount）
			auditsMap.put("ariesPreForm", "1,2,3");
			auditsMap.put("sqlField", "amount");
			auditsMap.put("fieldNm", "审计结果");
			auditsMap.put("ariesInputType", 1);
			auditsMap.putAll(sqlmapJB);
			auditsMap.put("sqlFieldKey", seqMap);
			auditsMap.put("updateDateField", "inputtime");
			auditsMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（mendNum）
			mendNumMap.put("ariesPreForm", "1,2,3");
			mendNumMap.put("sqlField", "mendNum");
			mendNumMap.put("fieldNm", "大修次数");
			mendNumMap.put("ariesInputType", 2);
			mendNumMap.putAll(sqlmapJB);
			mendNumMap.put("sqlFieldKey", seqMap);
			mendNumMap.put("updateDateField", "inputtime");
			mendNumMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（buildMaterByProblem）
			buildMaterByProblemMap.put("ariesPreForm", "1,2,3");
			buildMaterByProblemMap.put("sqlField", "buildMaterByProblem");
			buildMaterByProblemMap.put("fieldNm", "改造前主要问题");
			buildMaterByProblemMap.put("ariesInputType", 1);
			buildMaterByProblemMap.putAll(sqlmapJB);
			buildMaterByProblemMap.put("sqlFieldKey", seqMap);
			buildMaterByProblemMap.put("updateDateField", "inputtime");
			buildMaterByProblemMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（structures）
			structuresMap.put("ariesPreForm", "1,2,3");
			structuresMap.put("sqlField", "structures");
			structuresMap.put("fieldNm", "建筑结构");
			structuresMap.put("ariesInputType", 1);
			structuresMap.putAll(sqlmapJB);
			structuresMap.put("sqlFieldKey", seqMap);
			structuresMap.put("updateDateField", "inputtime");
			structuresMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（outWall）
			outWallMap.put("ariesPreForm", "1,2,3");
			outWallMap.put("sqlField", "outWall");
			outWallMap.put("fieldNm", "外墙材料");
			outWallMap.put("ariesInputType", 1);
			outWallMap.putAll(sqlmapJB);
			outWallMap.put("sqlFieldKey", seqMap);
			outWallMap.put("updateDateField", "inputtime");
			outWallMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（doorMater）
			doorMaterMap.put("ariesPreForm", "1,2,3");
			doorMaterMap.put("sqlField", "doorMater");
			doorMaterMap.put("fieldNm", "门用材料");
			doorMaterMap.put("ariesInputType", 1);
			doorMaterMap.putAll(sqlmapJB);
			doorMaterMap.put("sqlFieldKey", seqMap);
			doorMaterMap.put("updateDateField", "inputtime");
			doorMaterMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");

			//（windowMater）
			windowMaterMap.put("ariesPreForm", "1,2,3");
			windowMaterMap.put("sqlField", "windowMater");
			windowMaterMap.put("fieldNm", "窗用材料");
			windowMaterMap.put("ariesInputType", 1);
			windowMaterMap.putAll(sqlmapJB);
			windowMaterMap.put("sqlFieldKey", seqMap);
			windowMaterMap.put("updateDateField", "inputtime");
			windowMaterMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			//（ceilingMater）
			ceilingMaterMap.put("ariesPreForm", "1,2,3");
			ceilingMaterMap.put("sqlField", "ceilingMater");
			ceilingMaterMap.put("fieldNm", "屋内顶材料");
			ceilingMaterMap.put("ariesInputType", 1);
			ceilingMaterMap.putAll(sqlmapJB);
			ceilingMaterMap.put("sqlFieldKey", seqMap);
			ceilingMaterMap.put("updateDateField", "inputtime");
			ceilingMaterMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			//（wallMater）
			wallMaterMap.put("ariesPreForm", "1,2,3");
			wallMaterMap.put("sqlField", "wallMater");
			wallMaterMap.put("fieldNm", "墙体材料");
			wallMaterMap.put("ariesInputType", 1);
			wallMaterMap.putAll(sqlmapJB);
			wallMaterMap.put("sqlFieldKey", seqMap);
			wallMaterMap.put("updateDateField", "inputtime");
			wallMaterMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			//（floorMater）
			floorMaterMap.put("ariesPreForm", "1,2,3");
			floorMaterMap.put("sqlField", "floorMater");
			floorMaterMap.put("fieldNm", "地板材料");
			floorMaterMap.put("ariesInputType", 1);
			floorMaterMap.putAll(sqlmapJB);
			floorMaterMap.put("sqlFieldKey", seqMap);
			floorMaterMap.put("updateDateField", "inputtime");
			floorMaterMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			//（buildMaterByWaterproof）
			buildMaterByWaterproofMap.put("ariesPreForm", "1,2,3");
			buildMaterByWaterproofMap.put("sqlField", "buildMaterByWaterproof");
			buildMaterByWaterproofMap.put("fieldNm", "屋面防水等级");
			buildMaterByWaterproofMap.put("ariesInputType", 1);
			buildMaterByWaterproofMap.putAll(sqlmapJB);
			buildMaterByWaterproofMap.put("sqlFieldKey", seqMap);
			buildMaterByWaterproofMap.put("updateDateField", "inputtime");
			buildMaterByWaterproofMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			//（buildMaterByQuakeproofs）
			buildMaterByQuakeproofsMap.put("ariesPreForm", "1,2,3");
			buildMaterByQuakeproofsMap.put("sqlField", "buildMaterByQuakeproofs");
			buildMaterByQuakeproofsMap.put("fieldNm", "抗震烈度");
			buildMaterByQuakeproofsMap.put("ariesInputType", 1);
			buildMaterByQuakeproofsMap.putAll(sqlmapJB);
			buildMaterByQuakeproofsMap.put("sqlFieldKey", seqMap);
			buildMaterByQuakeproofsMap.put("updateDateField", "inputtime");
			buildMaterByQuakeproofsMap.put("updateFieldSql", "select inputtime from DB_BUILDING where 1=1 and BUILDING_ID=#{buildingId}");
			
			remindmapAllJG.put("buildMaterByCostaccord", buildMaterByCostaccordMap);
			remindmapAllJG.put("proCost", proCostMap);
			remindmapAllJG.put("audits", auditsMap);
			remindmapAllJG.put("mendNum", mendNumMap);
			remindmapAllJG.put("buildMaterByProblem", buildMaterByProblemMap);
			remindmapAllJG.put("structures", structuresMap);
			remindmapAllJG.put("outWall", outWallMap);
			remindmapAllJG.put("doorMater", doorMaterMap);
			remindmapAllJG.put("windowMater", windowMaterMap);
			remindmapAllJG.put("ceilingMater", ceilingMaterMap);
			remindmapAllJG.put("wallMater", wallMaterMap);
			remindmapAllJG.put("floorMater", floorMaterMap);
			remindmapAllJG.put("buildMaterByWaterproof", buildMaterByWaterproofMap);
			remindmapAllJG.put("buildMaterByQuakeproofs", buildMaterByQuakeproofsMap);
			
			JSONObject remindJsonJG = JSONObject.fromObject(remindmapAllJG);
			
			request.setAttribute("remindJson", remindJson);
			request.setAttribute("remindJsonJB", remindJsonJB);
			request.setAttribute("remindJsonJG", remindJsonJG);
			logg.debug("    remindJson=   "+remindJson);
			logg.debug("    remindJsonJB=   "+remindJsonJB);
			logg.debug("    remindJsonJG=   "+remindJsonJG);
		}catch (Exception e) {
			logg.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}


	// 跳转到显示界面
	public String showBuildDetails() {
		this.findBuildDetails();
		return "input";
	}



//	public BuildRepairMgr getRepairMgr() {
//		return repairMgr;
//	}
//
//	public void setRepairMgr(BuildRepairMgr repairMgr) {
//		this.repairMgr = repairMgr;
//	}

	public BuildrepairBussiness getRepairMgr() {
		return repairMgr;
	}


	public void setRepairMgr(BuildrepairBussiness repairMgr) {
		this.repairMgr = repairMgr;
	}


//	public BuildStoreyMgr getStoreyMgr() {
//		return storeyMgr;
//	}
//
//	public void setStoreyMgr(BuildStoreyMgr storeyMgr) {
//		this.storeyMgr = storeyMgr;
//	}


	public BuildStoreyBusiness getStoreyMgr() {
		return storeyMgr;
	}


	public void setStoreyMgr(BuildStoreyBusiness storeyMgr) {
		this.storeyMgr = storeyMgr;
	}


	public BuildMaterBusiness1 getMaterBusiness() {
		return materBusiness;
	}

	public void setMaterBusiness(BuildMaterBusiness1 materBusiness) {
		this.materBusiness = materBusiness;
	}

	public List<DbBuildMater> getListMater() {
		return listMater;
	}

	public void setListMater(List<DbBuildMater> listMater) {
		this.listMater = listMater;
	}

	public List<DbHospInfo> getListHosp() {
		return listHosp;
	}

	public void setListHosp(List<DbHospInfo> listHosp) {
		this.listHosp = listHosp;
	}

	public List<DbBuildingRepair> getListRepair() {
		return listRepair;
	}

	public void setListRepair(List<DbBuildingRepair> listRepair) {
		this.listRepair = listRepair;
	}

	public List getListArea() {
		return listArea;
	}

	public void setListArea(List listArea) {
		this.listArea = listArea;
	}

	public Integer getbuildingId() {
		return buildingId;
	}

	public void setbuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getHospid() {
		return hospid;
	}

	public void setHospid(Integer hospid) {
		this.hospid = hospid;
	}

	public List<DbBuildingRepair> getBuilds() {
		return builds;
	}

	public void setBuilds(List<DbBuildingRepair> builds) {
		this.builds = builds;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<DbBuildingStorey> getBuildarea() {
		return buildarea;
	}

	public void setBuildarea(List<DbBuildingStorey> buildarea) {
		this.buildarea = buildarea;
	}

	public List<DbBuildingStorey> getBuilduseArea() {
		return builduseArea;
	}

	public void setBuilduseArea(List<DbBuildingStorey> builduseArea) {
		this.builduseArea = builduseArea;
	}

	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}

	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}

	public HospInfoBusiness getHospBussiness() {
		return hospBussiness;
	}

	public void setHospBussiness(HospInfoBusiness hospBussiness) {
		this.hospBussiness = hospBussiness;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}


	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}


	public List<DbBuilding> getListBuild() {
		return listBuild;
	}

	public void setListBuild(List<DbBuilding> listBuild) {
		this.listBuild = listBuild;
	}

	public Integer getHosinfoid() {
		return hospid;
	}

	public void setHosinfoid(Integer hosinfoid) {
		this.hospid = hosinfoid;
	}

	public List<DbBuilding> getListB() {
		return listB;
	}

	public void setListB(List<DbBuilding> listB) {
		this.listB = listB;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}


	public List getHospitalArea() {
		return hospitalArea;
	}

	public void setHospitalArea(List hospitalArea) {
		this.hospitalArea = hospitalArea;
	}

	public String getShowOrhide() {
		return showOrhide;
	}

	public void setShowOrhide(String showOrhide) {
		this.showOrhide = showOrhide;
	}

	public String getMyfirst() {
		return myfirst;
	}

	public void setMyfirst(String myfirst) {
		this.myfirst = myfirst;
	}

	public String getHideOrshow() {
		return hideOrshow;
	}

	public void setHideOrshow(String hideOrshow) {
		this.hideOrshow = hideOrshow;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public BaseCommBusiness getBaseCommBusiness() {
		return baseCommBusiness;
	}

	public void setBaseCommBusiness(BaseCommBusiness baseCommBusiness) {
		this.baseCommBusiness = baseCommBusiness;
	}
	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public List getListStorey() {
		return listStorey;
	}

	public void setListStorey(List listStorey) {
		this.listStorey = listStorey;
	}

	public BuildStoreyBusiness getBuildStoreyBusiness() {
		return buildStoreyBusiness;
	}

	public void setBuildStoreyBusiness(BuildStoreyBusiness buildStoreyBusiness) {
		this.buildStoreyBusiness = buildStoreyBusiness;
	}

	public DBBuildingStoreyHisBusiness getBuildingStoreyHisBusiness() {
		return buildingStoreyHisBusiness;
	}

	public void setBuildingStoreyHisBusiness(
			DBBuildingStoreyHisBusiness buildingStoreyHisBusiness) {
		this.buildingStoreyHisBusiness = buildingStoreyHisBusiness;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}
	
}

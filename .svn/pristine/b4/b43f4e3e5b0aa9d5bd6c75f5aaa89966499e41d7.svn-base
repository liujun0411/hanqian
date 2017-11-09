package com.hanqian.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.EquipGroupBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.EquipTypeBusiness;
import com.hanqian.form.VOEquipGroup;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.pojo.DbUsers;
import com.hanqian.pojo.EquipService;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 实时监控页面 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-23
 * @see
 */
public class ControlPageAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(ControlPageAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	private String resultJSON; // 返回JSON格式的文本
	private Page page;
	private Integer pageSize = 10;
	private Integer currentPage;
	private DbEquipList dbEquipList;
	private List listEquipList;
	private List threePointList;
	private List listBuilding; // 楼宇信息列表
	private EquipListBusiness equipListBusiness; // 设备列表
	private BuildingBusiness buildBusiness; // 楼宇类型
	private EquipTypeBusiness equipTypeBusiness; // 设备类型
	private EquipGroupBusiness equipGroupBusiness; // 设备分组
	private List<VOEquipGroup> equipGroupList; // 设备分组列表
	private List dbBuildingService;
	private List buildList;
	private List groupList;
	private List groupSelectList;
	private List storeyList;

	/**
	 * 返回监控设备列表
	 * 
	 * @return
	 */
//	public String showEquipList() {
//		request = ServletActionContext.getRequest();
//		page = null;
//		String redirect = "";
//		RetCode rc = new RetCode();
//		String pageIndex=request.getParameter("unGrcurrentPage");
//		if (StringUtils.isNotEmpty(pageIndex)) {
//			currentPage = Integer.parseInt(pageIndex);
//		}else{
//			currentPage=1;
//		}
//		
//		//获取设备类型ID
//		String eqTypeId=request.getParameter("eqTypeId");
//		if(StringUtils.isNotEmpty(eqTypeId)){
//			request.setAttribute("eqTypeId", eqTypeId);
//		}
//		String HasTab=request.getParameter("HasTab");
//        if(StringUtils.isNotEmpty(HasTab)){
//			request.setAttribute("HasTab", HasTab);
//		}
//		if (StringUtils.isNotEmpty(eqTypeId)) {
//			// 判断是否为配电系统
//			if (eqTypeId.equals("9")) {
//				return showPeidian(eqTypeId);
//			}else {
//				// 判断是否设备类型是否分组
//				DbEquipType dbEquipType = new DbEquipType();
//				dbEquipType = equipTypeBusiness.findById(Integer.parseInt(eqTypeId));
//				if (dbEquipType != null) {
//					// 如果分组
//					if (dbEquipType.getGroupStatus()) {
//						showControlGroup(request, rc);
//						redirect = "groupEquip";
//					} else {
//						// 不分组(电表水表)
//						if(eqTypeId.equals("10001") || eqTypeId.equals("10002")||eqTypeId.equals("12001")){
//							showControlEquip(eqTypeId, rc, dbEquipType, request);
//						}else{
//							showControlEquipList(eqTypeId,rc,dbEquipType,request);
//						}
//						redirect = "unGroupEquip";
//					}
//				}
//			}
//		}
//		return redirect;
//	}
	
	
	/**
	 * 太平间监控系统
	 * 
	 *【(仁济)单独要求：现在只有仁济医院有这个功能，其他医院没有这个功能】
	 * 
	 * 
	 * @param eqTypeId
	 * @param rc
	 * @param dbEquipType
	 */
	public void showControlEquipTaipingjian(String eqTypeId, RetCode rc,
			DbEquipType dbEquipType, HttpServletRequest request) {
	    listEquipList = new ArrayList();
	    dbEquipList=new DbEquipList();
	    DbBuilding building=new DbBuilding();
	    String buildId=request.getParameter("buildId");
	    if(StringUtils.isNotEmpty(buildId)&&!"0".equals(buildId)){
		   DbBuilding build=new DbBuilding();
		   build.setBuildingId(Integer.parseInt(buildId));
		   dbEquipList.setDbBuilding(build);
		   building=buildBusiness.findBuildingById(build);
		   request.setAttribute("buildId", buildId);
	    }
	    String storey= request.getParameter("storey");
	    if(StringUtils.isNotEmpty(storey) && (!storey.trim().equals(""))&&!"0".equals(storey)){
		   dbEquipList.setStorey(Double.parseDouble(storey));
		   request.setAttribute("storey", request.getParameter("storey"));
	    }
        if(StringUtils.isNotEmpty(request.getParameter("place"))){
     	   dbEquipList.setPlace(request.getParameter("place"));
     	   request.setAttribute("place", request.getParameter("place"));
	    }
	    if(StringUtils.isNotEmpty(request.getParameter("unitType"))){
		   dbEquipList.setUnitType(request.getParameter("unitType"));
		   request.setAttribute("unitType", request.getParameter("unitType"));
	    }
		if(building.getBuildingId()!=null){
			int storyUp=building.getStoreyNumUp();
			int storyDown=building.getStoreyNumDown();
			storeyList=new ArrayList<Integer>();
			for(int i=storyUp;i>0;i--){
				storeyList.add(i);
			}
			for(int i=1;i<=storyDown;i++){
				storeyList.add(0-i);
			}
		}
		String serviceBuildId = request.getParameter("serviceBuildId");
		String serviceStorey = request.getParameter("serviceStorey");
		String serviceAreas = request.getParameter("serviceAreas");
		request.setAttribute("serviceBuildId", serviceBuildId);
		request.setAttribute("serviceStorey", serviceStorey);
		request.setAttribute("serviceAreas", serviceAreas);
		
		// 根据条件获取设备列表信息
		dbEquipList.setControlFlag(Short.parseShort("1")); // 监控类型
		if (StringUtils.isNotEmpty(eqTypeId)) {
			dbEquipType.setEquipTypeId(Integer.parseInt(eqTypeId));
			request.setAttribute("eqTypeId", eqTypeId);
			dbEquipList.setDbEquipType(dbEquipType);
			// 加载楼宇列表
			if (buildBusiness.findBuildByParam(dbEquipList).getObj() != null) {
				listBuilding = (List) buildBusiness.findBuildByParam(
						dbEquipList).getObj();
			}
			dbBuildingService=buildBusiness.findAll();
		}
		EquipService equipService = new EquipService();
		//判断条件是否为空
		if (!StringUtil.isEmpty(serviceBuildId)&&!"0".equals(serviceBuildId)) {
			//服务楼宇
			equipService.setBuildId(Integer.parseInt(serviceBuildId));
		}
		if(!StringUtil.isEmpty(serviceStorey)&&!"0".equals(serviceStorey)){
			//服务楼层
			equipService.setStorey(Integer.parseInt(serviceStorey));
		}
		if(!StringUtil.isEmpty(serviceAreas)){
			DbBaseComm dbBaseComm=new DbBaseComm();
			//服务区域
//			dbBaseComm.setSeq(Integer.parseInt(serviceAreas));
			dbBaseComm.setContent1(serviceAreas);
			equipService.setDbBaseComm(dbBaseComm);
		}
		rc = equipListBusiness.findALlEquipTaipingjianList(dbEquipList, equipService, currentPage,
				pageSize);
		listEquipList=new ArrayList();
		if (rc.getObj() != null) {
			listEquipList = (List) rc.getObj();
			page = rc.getPage();
		}
	}
	/***
	 * 6院和一妇婴保健院特有的
	 * 显示第三方点位信息
	 * Date：2016-08-04
	 */
	public String threePoint(){
		if (currentPage == null) {
			currentPage = 1;
		}
		RetCode rc = equipListBusiness.threePoint(currentPage,pageSize);
		threePointList=new ArrayList();
		if (rc.getObj() != null) {
			threePointList = (List) rc.getObj();
			page = rc.getPage();
		}
		return "threePointExhibition";
	}
	/**
	 * 返回监控设备列表
	 * 【(胸科)单独要求：现在只有胸科医院有电力系统功能，其他医院没有这个功能】
	 * 【(仁济)单独要求：现在只有仁济医院有太平间系统功能，其他医院没有这个功能】
	 * 通过hospName（医院编号）来判断：XK为胸科，RJ为仁济
	 * falg：1为电力系统，2是太平间 ，3是能源计量
	 * 2015-05-22
	 * 
	 * @return
	 */
	public String showEquipList() {
		request = ServletActionContext.getRequest();
		page = null;
		String redirect = "";
		RetCode rc = new RetCode();
		String pageIndex=request.getParameter("unGrcurrentPage");
		String falg=request.getParameter("falg");
		String hospName = (String) ServletActionContext.getRequest()
		.getSession().getAttribute("currentHospCode");
		if (StringUtils.isNotEmpty(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		}else{
			currentPage=1;
		}
		
		//获取设备类型ID
		String eqTypeId=request.getParameter("eqTypeId");
		if(StringUtils.isNotEmpty(eqTypeId)){
			request.setAttribute("eqTypeId", eqTypeId);
		}
		String HasTab=request.getParameter("HasTab");
        if(StringUtils.isNotEmpty(HasTab)){
			request.setAttribute("HasTab", HasTab);
		}
		if (StringUtils.isNotEmpty(eqTypeId)) {
			// 判断是否为配电系统
			if (eqTypeId.equals("9")) {
				return showPeidian(eqTypeId);
			}else {
				// 判断是否设备类型是否分组
				DbEquipType dbEquipType = new DbEquipType();
				dbEquipType = equipTypeBusiness.findById(Integer
						.parseInt(eqTypeId));
				if (dbEquipType != null) {
					// 如果分组
					if (dbEquipType.getGroupStatus()) {
						showControlGroup(request, rc);
						redirect = "groupEquip";
					} else {
                        if(("1").equals(falg) && hospName.equals("XK")){
							//用电系统显示的数据List
							if(eqTypeId.equals("10001") ){
								showControlEquipyongdianxitong(eqTypeId, rc, dbEquipType, request);
							}
							request.setAttribute("falg", falg);
							redirect = "yongdianxitong";
						}else if(("2").equals(falg) && hospName.equals("RJ")){
								//太平间监控显示的数据List
								if(eqTypeId.equals("10001")|| eqTypeId.equals("10002") ){
									showControlEquipTaipingjian(eqTypeId, rc, dbEquipType, request);
								}
								request.setAttribute("falg", falg);
								redirect = "showTaiPingJian";
						 }else{
							// 不分组(电表水表)
							if(eqTypeId.equals("10001") || eqTypeId.equals("10002")||eqTypeId.equals("12001")){
								showControlEquip(eqTypeId, rc, dbEquipType, request);
							}else{
								showControlEquipList(eqTypeId,rc,dbEquipType,request);
							}
							redirect = "unGroupEquip";
						}
					}
				}
			}
		}
		return redirect;
	}
	
	/**
	 * 电力安全
	 * 
	 * 【(胸科)单独要求：现在只有胸科医院有这个功能，其他医院没有这个功能】
	 * 
	 * 2015-05-22
	 * 
	 * @param eqTypeId
	 * @param rc
	 * @param dbEquipType
	 */
	public void showControlEquipyongdianxitong(String eqTypeId, RetCode rc,
			DbEquipType dbEquipType, HttpServletRequest request) {
	    listEquipList = new ArrayList();
	    dbEquipList=new DbEquipList();
	    DbBuilding building=new DbBuilding();
	    String buildId=request.getParameter("buildId");
	    if(StringUtils.isNotEmpty(buildId)&&!"0".equals(buildId)){
		   DbBuilding build=new DbBuilding();
		   build.setBuildingId(Integer.parseInt(buildId));
		   dbEquipList.setDbBuilding(build);
		   building=buildBusiness.findBuildingById(build);
		   request.setAttribute("buildId", buildId);
	    }
	    String storey= request.getParameter("storey");
	    if(StringUtils.isNotEmpty(storey) && (!storey.trim().equals(""))&&!"0".equals(storey)){
		   dbEquipList.setStorey(Double.parseDouble(storey));
		   request.setAttribute("storey", request.getParameter("storey"));
	    }
        if(StringUtils.isNotEmpty(request.getParameter("place"))){
     	   dbEquipList.setPlace(request.getParameter("place"));
     	   request.setAttribute("place", request.getParameter("place"));
	    }
	    if(StringUtils.isNotEmpty(request.getParameter("unitType"))){
		   dbEquipList.setUnitType(request.getParameter("unitType"));
		   request.setAttribute("unitType", request.getParameter("unitType"));
	    }
		if(building.getBuildingId()!=null){
			int storyUp=building.getStoreyNumUp();
			int storyDown=building.getStoreyNumDown();
			storeyList=new ArrayList<Integer>();
			for(int i=storyUp;i>0;i--){
				storeyList.add(i);
			}
			for(int i=1;i<=storyDown;i++){
				storeyList.add(0-i);
			}
		}
		String serviceBuildId = request.getParameter("serviceBuildId");
		String serviceStorey = request.getParameter("serviceStorey");
		String serviceAreas = request.getParameter("serviceAreas");
		request.setAttribute("serviceBuildId", serviceBuildId);
		request.setAttribute("serviceStorey", serviceStorey);
		request.setAttribute("serviceAreas", serviceAreas);
		
		// 根据条件获取设备列表信息
		dbEquipList.setControlFlag(Short.parseShort("1")); // 监控类型
		if (StringUtils.isNotEmpty(eqTypeId)) {
			dbEquipType.setEquipTypeId(Integer.parseInt(eqTypeId));
			request.setAttribute("eqTypeId", eqTypeId);
			dbEquipList.setDbEquipType(dbEquipType);
			// 加载楼宇列表
			if (buildBusiness.findBuildByParam(dbEquipList).getObj() != null) {
				listBuilding = (List) buildBusiness.findBuildByParam(
						dbEquipList).getObj();
			}
			dbBuildingService=buildBusiness.findAll();
		}
		EquipService equipService = new EquipService();
		//判断条件是否为空
		if (!StringUtil.isEmpty(serviceBuildId)&&!"0".equals(serviceBuildId)) {
			//服务楼宇
			equipService.setBuildId(Integer.parseInt(serviceBuildId));
		}
		if(!StringUtil.isEmpty(serviceStorey)&&!"0".equals(serviceStorey)){
			//服务楼层
			equipService.setStorey(Integer.parseInt(serviceStorey));
		}
		if(!StringUtil.isEmpty(serviceAreas)){
			DbBaseComm dbBaseComm=new DbBaseComm();
			//服务区域
//			dbBaseComm.setSeq(Integer.parseInt(serviceAreas));
			dbBaseComm.setContent1(serviceAreas);
			equipService.setDbBaseComm(dbBaseComm);
		}
		rc = equipListBusiness.findALlEquipListyongdianxitong(dbEquipList, equipService, currentPage,
				pageSize);
		listEquipList=new ArrayList();
		if (rc.getObj() != null) {
			listEquipList = (List) rc.getObj();
			page = rc.getPage();
		}
	}
	
	
	
	
	
	
	/**
	 * 显示配电系统
	 * 
	 * @param 2012-11-6
	 * @param
	 * @return void
	 */
    public String showPeidian(String eqTypeId){
		RetCode rc=new RetCode();
		String stroey = request.getParameter("storey");
		String buildingId=request.getParameter("buildId");
		equipGroupList=new ArrayList();
		if (StringUtils.isNotEmpty(eqTypeId)) {
			rc = equipGroupBusiness.findPeiDian(buildingId, stroey,
					eqTypeId, currentPage, pageSize);
			if (rc.getObj() != null) {
				equipGroupList = (List) rc.getObj();
				request.setAttribute("page", rc.getPage());
				Map map=new HashMap();
				map=(Map)equipGroupList.get(0);
				request.setAttribute("title",map.get("group_name"));
			}
		}
		DbBuilding building=new DbBuilding();
		DbBuilding build=new DbBuilding();
		if(StringUtils.isNotEmpty(buildingId)&&!"0".equals(buildingId)){
			building.setBuildingId(Integer.parseInt(buildingId));
			build=buildBusiness.findBuildingById(building);
		}
		if(build.getBuildingId()!=null&&0!=build.getBuildingId()){
			int storyUp=build.getStoreyNumUp();
			int storyDown=build.getStoreyNumDown();
			storeyList=new ArrayList<Integer>();
			for(int i=storyUp;i>0;i--){
				storeyList.add(i);
			}
			for(int i=1;i<=storyDown;i++){
				storeyList.add(0-i);
			}
		}
		request.setAttribute("storey",stroey);
		request.setAttribute("buildId", buildingId);
		//查询所有楼宇
		buildList=buildBusiness.findAll();
		return "peidian";
	}
	
	/**
	 * 获得设备类型下面所有的组设备列表集合（分组设备）
	 * 
	 * @param request
	 */
	public void showControlGroup(HttpServletRequest request, RetCode rc) {
		DbUsers users = (DbUsers) request.getSession().getAttribute("users");
		// 得到要查询的设备类型
		String eqTypeId = request.getParameter("eqTypeId");
		// 查询条件,设备组Id，备注
		String groupId = request.getParameter("groupId");
		String remark = request.getParameter("remark");
		try {
			if (StringUtils.isNotEmpty(eqTypeId)) {
				// 查询
				rc = equipGroupBusiness.findGroupList(currentPage, pageSize,
						eqTypeId, groupId);
				// 判断是否查到数据
				equipGroupList=new ArrayList();
				if (rc != null) {
					if (rc.getObj() != null) {
						equipGroupList = (List<VOEquipGroup>) rc.getObj();
						if(equipGroupList!=null&&equipGroupList.size()>0){
							request.setAttribute("title", equipGroupList.get(0)
									.getTypeName());
						}
					}
					page = rc.getPage();
				}
				RetCode rt=new RetCode();
				rt=equipGroupBusiness.findGroupByEQTypeId(eqTypeId);
				groupList=new ArrayList();
				if(rt.getObj()!=null){
					groupList=(List)rt.getObj();
				}
			}
			request.setAttribute("groupId", groupId);
			request.setAttribute("remark", remark);
		} catch (Exception e) {
			logg.error("ControlPageAction-->showControlGroup", e);
			e.printStackTrace();
		}
	}

	/**
	 * 根据设备类型ID,获得当前设备下所有的设备信息集合（单组设备）
	 * 
	 * @param eqTypeId
	 * @param rc
	 * @param dbEquipType
	 */
	public void showControlEquip(String eqTypeId, RetCode rc,
			DbEquipType dbEquipType, HttpServletRequest request) {
	    listEquipList = new ArrayList();
	    dbEquipList=new DbEquipList();
	    DbBuilding building=new DbBuilding();
	    String buildId=request.getParameter("buildId");
	    if(StringUtils.isNotEmpty(buildId)&&!"0".equals(buildId)){
		   DbBuilding build=new DbBuilding();
		   build.setBuildingId(Integer.parseInt(buildId));
		   dbEquipList.setDbBuilding(build);
		   building=buildBusiness.findBuildingById(build);
		   request.setAttribute("buildId", buildId);
	    }
	    String storey= request.getParameter("storey");
	    if(StringUtils.isNotEmpty(storey) && (!storey.trim().equals(""))&&!"0".equals(storey)){
		   dbEquipList.setStorey(Double.parseDouble(storey));
		   request.setAttribute("storey", request.getParameter("storey"));
	    }
        if(StringUtils.isNotEmpty(request.getParameter("place"))){
     	   dbEquipList.setPlace(request.getParameter("place"));
     	   request.setAttribute("place", request.getParameter("place"));
	    }
	    if(StringUtils.isNotEmpty(request.getParameter("unitType"))){
		   dbEquipList.setUnitType(request.getParameter("unitType"));
		   request.setAttribute("unitType", request.getParameter("unitType"));
	    }
		if(building.getBuildingId()!=null){
			int storyUp=building.getStoreyNumUp();
			int storyDown=building.getStoreyNumDown();
			storeyList=new ArrayList<Integer>();
			for(int i=storyUp;i>0;i--){
				storeyList.add(i);
			}
			for(int i=1;i<=storyDown;i++){
				storeyList.add(0-i);
			}
		}
		String serviceBuildId = request.getParameter("serviceBuildId");
		String serviceStorey = request.getParameter("serviceStorey");
		String serviceAreas = StringUtil.toString(request.getParameter("serviceAreas")).replace(" ","");//去除空格 by yangmin
		request.setAttribute("serviceBuildId", serviceBuildId);
		request.setAttribute("serviceStorey", serviceStorey);
		request.setAttribute("serviceAreas", serviceAreas);
		
		// 根据条件获取设备列表信息
		dbEquipList.setControlFlag(Short.parseShort("1")); // 监控类型
		if (StringUtils.isNotEmpty(eqTypeId)) {
			dbEquipType.setEquipTypeId(Integer.parseInt(eqTypeId));
			request.setAttribute("eqTypeId", eqTypeId);
			dbEquipList.setDbEquipType(dbEquipType);
			// 加载楼宇列表
			if (buildBusiness.findBuildByParam(dbEquipList).getObj() != null) {
				listBuilding = (List) buildBusiness.findBuildByParam(
						dbEquipList).getObj();
			}
			dbBuildingService=buildBusiness.findAll();
		}
		EquipService equipService = new EquipService();
		//判断条件是否为空
		if (!StringUtil.isEmpty(serviceBuildId)&&!"0".equals(serviceBuildId)) {
			//服务楼宇
			equipService.setBuildId(Integer.parseInt(serviceBuildId));
		}
		if(!StringUtil.isEmpty(serviceStorey)&&!"0".equals(serviceStorey)){
			//服务楼层
			equipService.setStorey(Integer.parseInt(serviceStorey));
		}
		if(!StringUtil.isEmpty(serviceAreas)){
			DbBaseComm dbBaseComm=new DbBaseComm();
			//服务区域
//			dbBaseComm.setSeq(Integer.parseInt(serviceAreas));
			dbBaseComm.setContent1(serviceAreas);
			equipService.setDbBaseComm(dbBaseComm);
		}
		rc = equipListBusiness.findALlEquipList(dbEquipList, equipService, currentPage,pageSize);
		listEquipList=new ArrayList();
		if (rc.getObj() != null) {
			listEquipList = (List) rc.getObj();
			page = rc.getPage();
		}
	}

	/**
	 * 查询监控设备列表
	 * @param eqTypeId
	 * @param rc
	 * @param dbEquipType
	 * @param request
	 */
	public void showControlEquipList(String eqTypeId,RetCode rc,DbEquipType dbEquipType,HttpServletRequest request){
		   listEquipList = new ArrayList();
		   dbEquipList=new DbEquipList();
		   DbBuilding building=new DbBuilding();
		   String buildId=request.getParameter("buildId");
		   if(StringUtils.isNotEmpty(buildId)&&!"0".equals(buildId)){
			   DbBuilding build=new DbBuilding();
			   build.setBuildingId(Integer.parseInt(buildId));
			   dbEquipList.setDbBuilding(build);
			   building=buildBusiness.findBuildingById(build);
			   request.setAttribute("buildId", buildId);//楼宇
		   }
		   String storey= request.getParameter("storey");
		   if(StringUtils.isNotEmpty(request.getParameter("storey")) && (!storey.trim().toString().equals(""))&&!"0".equals(storey)){
			   dbEquipList.setStorey(Double.parseDouble(storey));//楼层
			   request.setAttribute("storey", request.getParameter("storey"));
		   }
           if(StringUtils.isNotEmpty(request.getParameter("place"))){
        	   dbEquipList.setPlace(request.getParameter("place"));//安装位置
        	   request.setAttribute("place", request.getParameter("place"));
		   }
		   if(StringUtils.isNotEmpty(request.getParameter("unitType"))){
			   dbEquipList.setUnitType(request.getParameter("unitType"));//设备型号
			   request.setAttribute("unitType", request.getParameter("unitType"));
		   }
		   if(building!=null){
				if(building.getBuildingId()!=null&&building.getBuildingId()!=0){
					int storyUp=building.getStoreyNumUp();
					int storyDown=building.getStoreyNumDown();
					storeyList=new ArrayList<Integer>();
					for(int i=storyUp;i>0;i--){
						storeyList.add(i);
					}
					for(int i=1;i<=storyDown;i++){
						storeyList.add(0-i);
					}
				}
		   }
		   // 根据条件获取设备列表信息
		   dbEquipList.setControlFlag(Short.parseShort("1")); // 监控类型
		   if (StringUtils.isNotEmpty(eqTypeId)) {
				dbEquipType.setEquipTypeId(Integer.parseInt(eqTypeId));
				request.setAttribute("eqTypeId", eqTypeId);
				dbEquipList.setDbEquipType(dbEquipType);
				// 加载楼宇列表
				if (buildBusiness.findBuildByParam(dbEquipList).getObj() != null) {
					listBuilding = (List) buildBusiness.findBuildByParam(dbEquipList).getObj();
				}
			}
			rc = equipListBusiness.findALlEquipListInfo(dbEquipList, currentPage,pageSize);
			listEquipList=new ArrayList();
			if (rc.getObj() != null) {
				listEquipList = (List) rc.getObj();
				page = rc.getPage();
			}
	}
		

	public String getResultJSON() {
		return resultJSON;
	}
	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public DbEquipList getDbEquipList() {
		return dbEquipList;
	}

	public void setDbEquipList(DbEquipList dbEquipList) {
		this.dbEquipList = dbEquipList;
	}

	public List getListEquipList() {
		return listEquipList;
	}

	public void setListEquipList(List listEquipList) {
		this.listEquipList = listEquipList;
	}

	public List getListBuilding() {
		return listBuilding;
	}

	public void setListBuilding(List listBuilding) {
		this.listBuilding = listBuilding;
	}

	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}

	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
	}

	public BuildingBusiness getBuildBusiness() {
		return buildBusiness;
	}

	public void setBuildBusiness(BuildingBusiness buildBusiness) {
		this.buildBusiness = buildBusiness;
	}

	public EquipTypeBusiness getEquipTypeBusiness() {
		return equipTypeBusiness;
	}

	public void setEquipTypeBusiness(EquipTypeBusiness equipTypeBusiness) {
		this.equipTypeBusiness = equipTypeBusiness;
	}

	public EquipGroupBusiness getEquipGroupBusiness() {
		return equipGroupBusiness;
	}

	public void setEquipGroupBusiness(EquipGroupBusiness equipGroupBusiness) {
		this.equipGroupBusiness = equipGroupBusiness;
	}

	public List<VOEquipGroup> getEquipGroupList() {
		return equipGroupList;
	}

	public void setEquipGroupList(List<VOEquipGroup> equipGroupList) {
		this.equipGroupList = equipGroupList;
	}
	public List getDbBuildingService() {
		return dbBuildingService;
	}
	public void setDbBuildingService(List dbBuildingService) {
		this.dbBuildingService = dbBuildingService;
	}
		public List getBuildList() {
		return buildList;
	}

	public void setBuildList(List buildList) {
		this.buildList = buildList;
	}

	public List getGroupList() {
		return groupList;
	}
	public List getStoreyList() {
		return storeyList;
	}
	public void setStoreyList(List storeyList) {
		this.storeyList = storeyList;
	}
	public List getThreePointList() {
		return threePointList;
	}
	public void setThreePointList(List threePointList) {
		this.threePointList = threePointList;
	}
}

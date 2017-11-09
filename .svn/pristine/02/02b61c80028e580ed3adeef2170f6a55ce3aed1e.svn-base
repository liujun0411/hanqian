package com.hanqian.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
//import org.jfree.util.Log;




import com.hanqian.business.BaseCommBusiness;
import com.hanqian.business.BuildMaterBusiness1;
import com.hanqian.business.BuildStoreyBusiness;
import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.EquipListBusiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.business.UsersBusiness;
import com.hanqian.pojo.DbBaseComm;
import com.hanqian.pojo.DbBaseType;
import com.hanqian.pojo.DbBuildMater;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbEquipList;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 
 * 描 述: 楼宇基建 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author
 * @version 1.4 2012-9-14
 * @see
 */
public class BuildingAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(BuildingAction.class);
	private BuildingBusiness buildingBusiness; // 楼宇信息

	private HttpServletRequest request;

	private DbBuildMater buildMater; // 楼宇用途实体类

	private HospInfoBusiness hospInfoBusiness; // 医院信息

	private BuildStoreyBusiness buildStoreyBusiness; // 楼宇楼层信息

	private DbBuilding building = new DbBuilding(); // 楼宇实体类

	private UsersBusiness usersBusiness; // 用户信息

	private BaseCommBusiness baseCommBusiness; // 公用信息业务类

	private BuildMaterBusiness1 buildMaterBusiness; // 楼宇用途（建筑数据）

	private Integer buildingId; // 楼宇ID

	private Integer hospInfoId; // 医院ID

	private String editFlag; // 编辑状态标志

	private String showOrhide;

	private Map mapBuildMap = new HashMap();

	private String path; // 路径

	private Integer status; // 状态ID

	// private DbBaseComm baseComm; //公用信息实体类

	private List<DbBaseComm> listBaseComm; // 公用信息集合

	private HashMap dicMap = null; // 用来保存字典表的id和name值

	private DbBuildMater buildMaterByUsetype; // 楼宇用途

	private List<DbHospInfo> listHosp; // 医院信息集合

	private List<DbBuilding> listBuilding; // 楼宇信息集合

	private List<DbBuildMater> listMater; // 楼宇材料集合

	private List<DbBuildMater> listMater_WQ; // 外墙材料

	private List<DbBuildMater> listMater_KZ; // 抗震烈度

	private List<DbBuildMater> listMater_GZ; // 改造前主要问题

	private List<DbBuildMater> listMater_CY; // 窗用材料

	private List<DbBuildMater> listMater_QT; // 墙体材料

	private List<DbBuildMater> listMater_JZ; // 建筑材料度

	private List<DbBuildMater> listMater_FS; // 防水等级

	private List<DbBuildMater> listMater_ZJ; // 造价依据

	private List<DbBuildMater> listMater_MY; // 门用材料

	private List<DbBuildMater> listMater_WND; // 屋内顶材料

	private List<DbBuildMater> listMater_DP; // 地坪材料

	private List<DbBuildMater> listMater_YT; // 用途

	private String message;

	private Integer materId;
	
	private Map buildingMenuMap;
	
	private MenuInterceptor menuInterceptor;
	
	private EquipListBusiness equipListBusiness;
	
	private String resultJSON;
	
	private DbBuilding temp;
	
	private Map sqlmap = new HashMap();
    
    private Map seqMap = new HashMap();

    private Map remindmapAll = new HashMap();
    
    private Map hisNameMap = new HashMap();
	
	private Map storeyNumMap = new HashMap();
	
	private Map buildingAreasMap = new HashMap();
	
	private Map amountMap = new HashMap();
	
	private Map mendNumMap = new HashMap();
	
	private Map buildingStatusMap = new HashMap();
	
	private RetCode rt;

	/**
	 * 获取所有楼宇信息
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String showAllBuilding() {
		request = ServletActionContext.getRequest();

		building = buildingBusiness.findBuildingById(building);

		request.setAttribute("building", building);
		return "show";
	}

	/**
	 * 查询楼宇信息（楼宇导航）
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String findBuild() {
		int currentPage = 1;
		int pageSize = 10;
		request = ServletActionContext.getRequest();
		
		//权限配置
		buildingMenuMap=menuInterceptor.menuIntercept("2002002002");
		//若为空 ，进入登录界面
		if(buildingMenuMap==null){
			MenuInterceptor.toLoginjsp();
			return null;
		}

		// 获取当前页数
		String page = request.getParameter("page");
		if (!SysUtil.isNull(page)) {
			showOrhide = "1";
			currentPage = Integer.parseInt(page);
			}
		
		// 获得所有医院信息
		DbHospInfo hospInfo = new DbHospInfo();
		RetCode rtHos = hospInfoBusiness.findAllHos();
		if (rtHos.getObj() != null) {
			listHosp = (List<DbHospInfo>) rtHos.getObj();
			RetCode dicRt = baseCommBusiness.findBuildingStatus();
			if (dicRt != null && dicRt.getObj() != null) {
				listBaseComm = (List<DbBaseComm>) dicRt.getObj();
			}
		}

		// 获得所有状态
		RetCode dicRt = baseCommBusiness.findBuildingStatus();
		if (dicRt.getObj() != null) {
			listBaseComm = (List) dicRt.getObj();
		}
		// 获取楼宇结构材料信息
		DbBuildMater buildingMater = new DbBuildMater();
		Integer materId = 1;
		buildingMater.setMaterId(materId);
		if (buildingMater != null) {
			try {
				RetCode rcMater = buildMaterBusiness
						.findAllBuildMater(buildingMater.getMaterId());
				if (rcMater.getObj() != null) {
					listMater = (List<DbBuildMater>) rcMater.getObj();
				}
			} catch (Exception e) {
				logg.error("BuildingAction-->findBuild", e);
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		// 医院ID
		Integer hospId = 1;
		if (!SysUtil.isNull(editFlag)) {
			editFlag = request.getParameter("editFlag");
		}
		if (!SysUtil.isNull(showOrhide)) {
			showOrhide = request.getParameter("showOrhide");
		}
		setHospInfoId(hospId);
		// 根据医院及状态查询楼宇信息
		
		DbBaseComm baseCom = new DbBaseComm();
		DbBaseType baseType = new DbBaseType();
		if (!SysUtil.isNullObject(hospId)) {
			hospInfo.setSeqHosp(hospId);
			if (hospInfo != null) {
				building.setDbHospInfo(hospInfo);
			}
			// 楼宇状态
			if (!SysUtil.isNull(request
					.getParameter("building.dbBaseComm.dbBaseType.seq"))) {
				baseCom.setSeq(Integer.parseInt(request
						.getParameter("building.dbBaseComm.dbBaseType.seq")));
//				baseCom.setDbBaseType(baseType);
				building.setDbBaseComm(baseCom);
			}
			// 楼宇名称

			if (!SysUtil.isNull(request.getParameter("building.buildingName"))) {
				building.setBuildingName(request
						.getParameter("building.buildingName"));
			}
			// 曾用名
			if (!SysUtil.isNull(request.getParameter("building.hisName"))) {
				building.setHisName(request.getParameter("building.hisName"));
			}
			// 结构类型
			if (!SysUtil.isNull(request.getParameter("buildMater.materId"))) {
				building.setStructure(request
						.getParameter("buildMater.materId"));
			}
		}

		// 获取医院信息
		RetCode rCode = buildingBusiness.findBuildNameAndSequence(hospId);
		List<Map> list = (List) rCode.getObj();

		if(list!=null&&!list.isEmpty()&&list.size()>0){
				for (Map map : list) {
					// map.put("building_name", "building_id");
					mapBuildMap.put(map.get("building_name"), map.get("building_id"));
		
				}
		}

		// 获取楼宇信息
		rt = buildingBusiness.findBuilding(building, currentPage,pageSize);
		if (rt.getObj() != null) {
			listBuilding = (List<DbBuilding>) rt.getObj();
			logg.debug("    buildstatus=   "+listBuilding.get(0).getDbBaseComm().getContent1());
		}
		request.setAttribute("page", rt.getPage());
		request.setAttribute("hospId", hospId);
		
		
		//调用抽取任意提醒需要的数据的方法
		if(listBuilding != null){
			remindBuildInfoDate(currentPage);
		}
		
		return "input";
	}

	/**
	 * 添加楼宇信息OR修改
	 * 
	 * @param statement
	 * @param
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	public String showAddBuilding() throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
		logg.debug("进入showAddBuilding");
		request = ServletActionContext.getRequest();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd"); // 当前时间
		String date = sim.format(new Date());
		DbUsers use = (DbUsers) request.getSession().getAttribute("users"); // 获得登陆用户对象
		// building.setDbHospInfo(use.getDbHospitalinfo());
		// building.setOper(use.getUsername()); // 操作员
		DbUsers user = new DbUsers();
		user.setSeq(use.getSeq());
		building.setDbUsersByOper(user);
		building.setOpertime(java.sql.Date.valueOf(date)); // 操作时间
		// buildingId = request.getParameter("buildingId");

		if (!SysUtil.isNullObject(building.getBuildingId())) {
			/**
			 * 修改
			 * */
			try {
				message = "";
				DbHospInfo hospInfo = new DbHospInfo();
				hospInfo.setSeqHosp(1);

				building.setDbHospInfo(hospInfo);
				building.setStatus(0);
				if (building.getDbBuildMaterByProblem()==null||building.getDbBuildMaterByProblem().getMaterId()==null) {
					building.setDbBuildMaterByProblem(null);
				}
				buildingBusiness.updateBuilding(building);
			} catch (Exception e) {
				logg.error("BuildingAction-->showAddBuilding", e);
				message = "2";
				e.printStackTrace();
			}
		} else {
			// 添加
			try {
				Short mendnum = 0;
				building.setMendNum(mendnum);

				DbHospInfo hospInfo = new DbHospInfo();
				hospInfo.setSeqHosp(1);

				building.setDbHospInfo(hospInfo);
				building.setStatus(0);

				building.setDbBuildMaterByProblem(null);

				//buildingBusiness.insertObj(building);
//				building.dbBuildMaterByCostaccord.materId
//				building.setDbBuildMaterByCostaccord(buildMater);
				 buildMater = new DbBuildMater();
				 buildMater.setMaterId(building.getDbBuildMaterByCostaccord().getMaterId());
				 building.setDbBuildMaterByCostaccord(buildMater);

				// 抗震烈度
				buildMater = new DbBuildMater();
				buildMater.setMaterId(building.getDbBuildMaterByQuakeproof()
						.getMaterId());
				building.setDbBuildMaterByQuakeproof(buildMater);
				// 楼宇用途（自有/租赁）
				buildMater = new DbBuildMater();
				buildMater.setMaterId(building.getDbBuildMaterByUsetype()
						.getMaterId());
				building.setDbBuildMaterByUsetype(buildMater);
				// 防水等级
				buildMater = new DbBuildMater();
				buildMater.setMaterId(building.getDbBuildMaterByWaterproof()
						.getMaterId());
				building.setDbBuildMaterByWaterproof(buildMater);

				// System.out.println(building.getDbBuildMaterByCostaccord().getMaterId());
				buildingBusiness.insertObj(building);
		 
			} catch (Exception e) {
				logg.error("BuildingAction-->showAddBuilding", e);
				e.printStackTrace();
			}
		}
		message ="alert"; 
		this.editFlag = "2";
		return "showBuild";

	}

	/**
	 * 根据ID，跳到修改界面
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String updateBuilding() {
		request = ServletActionContext.getRequest();
		if (!SysUtil.isNullObject(buildingId)) {
			DbBuilding building = new DbBuilding();
			building.setBuildingId(buildingId);
			RetCode rc = buildingBusiness.findDBBuilding(building);
			if (rc.getObj() != null) {
				listBuilding = (List<DbBuilding>) rc.getObj();
				
				logg.debug("   listBuilding.BuildingId=   "+listBuilding.get(0).getBuildingId());
				
				
				request.setAttribute("listBuilding", listBuilding.get(0));
				temp = listBuilding.get(0);
				request.getSession().setAttribute("temp", temp);
				request.setAttribute("building", listBuilding.get(0)
						.getBuildingId());
			}
		}
		this.showBuilding();
		return "addBuild";
	}

	/**
	 * 删除
	 * 
	 * @param statement
	 * @param
	 * @return
	 * 
	 * 
	 * 继续修改修改的方法
	 * 
	 * 
	 */
	public String deleteBuilding() {
		request = ServletActionContext.getRequest();
		List<DbEquipList> equipList = new ArrayList<DbEquipList>();
		try {
			DbBuilding building = new DbBuilding();
			// 获取页面所选择删除的该楼的楼宇ID
			String buildingId = request.getParameter("buildingId");
			if (StringUtils.isNotEmpty(buildingId)) {
				building.setBuildingId(Integer.parseInt(buildingId));
			}
			RetCode rcBuild = buildingBusiness.findDBBuilding(building);
			if (rcBuild.getObj() != null) {

				List<DbBuilding> list = new ArrayList<DbBuilding>();
				list = (List<DbBuilding>) rcBuild.getObj();
				list.get(0).setStatus(1);
				RetCode rcEquip=equipListBusiness.findEquipByBuildingId(buildingId);
				equipList=(List<DbEquipList>)rcEquip.getObj();
				/**
				 * 2015-08-28修改
				 * 
				 */
				List lists = (List)rcEquip.getObj();
				boolean numCount=false;
				if(lists!=null){
					for(int i=0;i<lists.size();i++){
						Map map =(Map)lists.get(i);
						String status= map.get("status").toString();
						if(("0").equals(status)){
							numCount=true;
							break;
						}else if(("1").equals(status)){
							numCount=false;
						}
					}
					if(numCount==true){
						message="该楼宇里面存在设备，不能删除，要先删除该楼宇的所有设备后才能删除该楼宇!";
					}else{
						buildingBusiness.deleteBuilding(list.get(0));
					}
					
				}else{
					buildingBusiness.deleteBuilding(list.get(0));
				}
//				if(rcEquip.getObj()==null){
//				    buildingBusiness.deleteBuilding(list.get(0));
//				}else{
//					message="该楼宇里面存在设备，不能删除，要先删除该楼宇的所有设备后才能删除该楼宇!";
//				}
			}
			//message = "1";
			this.findBuild();
		} catch (Exception e) {
			logg.error("BuildingAction-->deleteBuilding", e);
			e.printStackTrace();
			message = "2";
		} finally {
			request.setAttribute("message", message);
			request.setAttribute("equipList", equipList);
		}
		return "showBuild";
	}
	
	/**
	 * 用来验证该楼宇时候包含设备（Ajax）
	 * @param 2013-4-11  
	 * @param        
	 * @return void
	 */
	public void findChildEquipByBuildId(){
		request = ServletActionContext.getRequest();
		List<DbEquipList> equipList = new ArrayList<DbEquipList>();
		try {
			DbBuilding building = new DbBuilding();
			// 获取页面所选择删除的该楼的楼宇ID
			String buildingId = request.getParameter("buildingId");
			if (StringUtils.isNotEmpty(buildingId)) {
				building.setBuildingId(Integer.parseInt(buildingId));
			}
			RetCode rcBuild = buildingBusiness.findDBBuilding(building);
			if (rcBuild.getObj() != null) {
				List<DbBuilding> list = new ArrayList<DbBuilding>();
				list = (List<DbBuilding>) rcBuild.getObj();
				list.get(0).setStatus(1);
				RetCode rcEquip=equipListBusiness.findEquipByBuildingId(buildingId);
				List lists = (List)rcEquip.getObj();
				//equipList=(List<DbEquipList>)rcEquip.getObj();
				/**
				 * 如果DB_EQUIP_LIST 中对表的
				 * 
				 * status=0 处于正常状态
				 * status=1 处于报废状态
				 * 
				 * 楼宇删除的前提：只有在设备处于报废的情况下，才可以删除的
				 * 
				 * 
				 * control_flag=0 处于不被监控的状态
				 * control_flag=1 处于监控的状态
				 * 
				 */
				JSONObject obj  = new JSONObject();
				String numCount="";
				if(lists!=null){
					for(int i=0;i<lists.size();i++){
						Map map =(Map)lists.get(i);
						String status= map.get("status").toString();
						if(("0").equals(status)){
							numCount="1";
							break;
						}else if(("1").equals(status)){
							numCount="";
						}
					}
					obj.accumulate("countFlag", numCount);
				}else{
					obj.accumulate("countFlag", numCount);
				}
			
//				if(equipList!=null){
//					Short confrgofla=equipList.get(0).getControlFlag();
//					if(confrgofla == 0){
//						obj.accumulate("countFlag", 1);
//					}else{
//						obj.accumulate("countFlag", "");
//					}
//				}else{
//					obj.accumulate("countFlag", "");
//				}

				/*if(equipList!=null){
					obj.accumulate("countFlag", 1);
				}else{
					obj.accumulate("countFlag", "");
				}*/
				resultJSON = obj.toString();
			}
			ServletActionContext.getResponse().getWriter().write(resultJSON);
		} catch (Exception e) {
			logg.error("BuildingAction-->findChildEquipByBuildId", e);
			e.printStackTrace();
		} 
	}

	/**
	 * 跳转到添加界面
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public String showBuilding() {
		this.findBuildMater();
		return "addBuild";
	}

	/**
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public void findBuildMater() {
		request = ServletActionContext.getRequest();
		String addOredit = request.getParameter("addOredit");
		request.setAttribute("addOredit", addOredit);
		/**
		 * 加载界面时
		 * */
		// 获得所有状态
		RetCode dicRt = baseCommBusiness.findBuildingStatus();
		if (dicRt.getObj() != null) {
			listBaseComm = (List<DbBaseComm>) dicRt.getObj();
			// System.out.println(listBaseComm.get(0).getDbBaseType().getSeq()+"\n\n"+listBaseComm.get(0).getContent1()
			// );
		}

		DbBuildMater buildMater; // 建筑数据

		// 外墙材料
		buildMater = new DbBuildMater();
		Integer materId = 2;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rcMater = buildMaterBusiness.findAllBuildMater(materId);
			if (rcMater.getObj() != null) {
				listMater_WQ = (List<DbBuildMater>) rcMater.getObj();
			}
		}

		// 抗震烈度
		buildMater = new DbBuildMater();
		materId = 4;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_KZ = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_KZ.getObj() != null) {
				listMater_KZ = (List<DbBuildMater>) rc_KZ.getObj();
			}
		}

		// 改造前主要问题
		buildMater = new DbBuildMater();
		materId = 6;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_GZ = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_GZ.getObj() != null) {
				listMater_GZ = (List<DbBuildMater>) rc_GZ.getObj();
			}
		}

		// 窗用材料
		buildMater = new DbBuildMater();
		materId = 8;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_CY = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_CY.getObj() != null) {
				listMater_CY = (List<DbBuildMater>) rc_CY.getObj();
			}
		}

		// 墙体材料
		buildMater = new DbBuildMater();
		materId = 10;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_QT = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_QT.getObj() != null) {
				listMater_QT = (List<DbBuildMater>) rc_QT.getObj();
			}
		}

		// 建筑结构
		buildMater = new DbBuildMater();
		materId = 1;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_JZ = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_JZ.getObj() != null) {
				listMater_JZ = (List<DbBuildMater>) rc_JZ.getObj();
			}
		}

		// 屋面防水等级
		buildMater = new DbBuildMater();
		materId = 3;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_FS = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_FS.getObj() != null) {
				listMater_FS = (List<DbBuildMater>) rc_FS.getObj();
			}
		}

		// 造价依据
		buildMater = new DbBuildMater();
		materId = 5;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_ZJ = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_ZJ.getObj() != null) {
				listMater_ZJ = (List<DbBuildMater>) rc_ZJ.getObj();
			}
		}

		// 门用材料
		buildMater = new DbBuildMater();
		materId = 7;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_MY = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_MY.getObj() != null) {
				listMater_MY = (List<DbBuildMater>) rc_MY.getObj();
			}
		}

		// 屋内顶材料
		buildMater = new DbBuildMater();
		materId = 9;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_WND = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_WND.getObj() != null) {
				listMater_WND = (List<DbBuildMater>) rc_WND.getObj();
			}
		}

		// 地坪材料
		buildMater = new DbBuildMater();
		materId = 11;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_DP = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_DP.getObj() != null) {
				listMater_DP = (List<DbBuildMater>) rc_DP.getObj();
			}
		}

		// 用途
		buildMater = new DbBuildMater();
		materId = 12;
		buildMater.setMaterId(materId);
		if (buildMater != null) {
			RetCode rc_YT = buildMaterBusiness.findAllBuildMater(materId);
			if (rc_YT.getObj() != null) {
				listMater_YT = (List<DbBuildMater>) rc_YT.getObj();
			}
		}
	}
	// 取上报数据
	public void getBuilding(HashMap reportMap, DbReportRate reportRate) {
		RetCode buildingCode = buildingBusiness.reportBuilding(reportRate.getReportdate());
		if (buildingCode.getObj() != null) {
			reportMap.put(reportRate.getDbReportType().getTypeId() + "",
					buildingCode.getObj());
		}
	}
	
	
	/**
	 * 将任意提醒的数据打包成map
	 * @param 
	 * @param
	 * @return
	 */
	public String remindBuildInfoDate(int currentPage) {
		int pageSize = 10;
		request = ServletActionContext.getRequest();
		try {
			
			//获取医院详情sql对象放到Map对象中
			sqlmap  = buildingBusiness.getFindBuildingSql();
			
			logg.debug("***************sqlmap="+sqlmap);
			
			rt = buildingBusiness.findBuilding(building, currentPage, pageSize);
			
			if(rt.getObj()!=null){
				listBuilding=(List<DbBuilding>) rt.getObj();
				if(buildingId==null){
					buildingId=listBuilding.get(0).getBuildingId();
				}
			}
			seqMap.put("buildingId", buildingId);
			
			//（hisName）
			//提醒方式:1、短信提醒；2、邮件提醒；3、平台内部提醒
			hisNameMap.put("ariesPreForm", "1,2,3");
			//sql字段名称
			hisNameMap.put("sqlField", "hisName");
			//中文字段名
			hisNameMap.put("fieldNm", "曾用名");
			//输入类型 ：1-文本;2-数字;3-时间
			hisNameMap.put("ariesInputType", 1);
			//sql
			hisNameMap.putAll(sqlmap);
			//含有多条，或者分页数据是用来对应数据用，数据是查出来的某条数据中有标识性的字段，如seq、ID等
			hisNameMap.put("sqlFieldKey", seqMap);
			//用来判断数据是否更新的时间字段，通过sql查询出来的结果集中的时间字段等来判断
			hisNameMap.put("updateDateField", "inputtime");
			//用来判断数据是否更新的sql
			hisNameMap.put("updateFieldSql", "select inputtime from "
					+ "db_building t inner join db_base_comm c on t.buildstatus=c.seq where t.status = 0");
//			remindmap.put("initFunc", "testDate");

			//（storeyNum）
			storeyNumMap.put("ariesPreForm", "1,2,3");
			storeyNumMap.put("sqlField", "storeyNum");
			storeyNumMap.put("fieldNm", "层数");
			storeyNumMap.put("ariesInputType", 2);
			storeyNumMap.putAll(sqlmap);
			storeyNumMap.put("sqlFieldKey", seqMap);
			storeyNumMap.put("updateDateField", "inputtime");
			storeyNumMap.put("updateFieldSql", "select inputtime from "
					+ "db_building t inner join db_base_comm c on t.buildstatus=c.seq where t.status = 0");
			
			//（buildingAreas）
			buildingAreasMap.put("ariesPreForm", "1,2,3");
			buildingAreasMap.put("sqlField", "buildingAreas");
			buildingAreasMap.put("fieldNm", "总面积");
			buildingAreasMap.put("ariesInputType", 2);
			buildingAreasMap.putAll(sqlmap);
			buildingAreasMap.put("sqlFieldKey", seqMap);
			buildingAreasMap.put("updateDateField", "inputtime");
			buildingAreasMap.put("updateFieldSql", "select inputtime from "
					+ "db_building t inner join db_base_comm c on t.buildstatus=c.seq where t.status = 0");
			
			//（amount）
			amountMap.put("ariesPreForm", "1,2,3");
			amountMap.put("sqlField", "amount");
			amountMap.put("fieldNm", "投资额");
			amountMap.put("ariesInputType", 2);
			amountMap.putAll(sqlmap);
			amountMap.put("sqlFieldKey", seqMap);
			amountMap.put("updateDateField", "inputtime");
			amountMap.put("updateFieldSql", "select inputtime from "
					+ "db_building t inner join db_base_comm c on t.buildstatus=c.seq where t.status = 0");
			
			//（mendNum）
			mendNumMap.put("ariesPreForm", "1,2,3");
			mendNumMap.put("sqlField", "mendNum");
			mendNumMap.put("fieldNm", "大修次数");
			mendNumMap.put("ariesInputType", 2);
			mendNumMap.putAll(sqlmap);
			mendNumMap.put("sqlFieldKey", seqMap);
			mendNumMap.put("updateDateField", "inputtime");
			mendNumMap.put("updateFieldSql", "select inputtime from "
					+ "db_building t inner join db_base_comm c on t.buildstatus=c.seq where t.status = 0");
			
			//（buildingStatus）
			buildingStatusMap.put("ariesPreForm", "1,2,3");
			buildingStatusMap.put("sqlField", "buildingStatus");
			buildingStatusMap.put("fieldNm", "建筑状态");
			buildingStatusMap.put("ariesInputType", 2);
			buildingStatusMap.putAll(sqlmap);
			buildingStatusMap.put("sqlFieldKey", seqMap);
			buildingStatusMap.put("updateDateField", "inputtime");
			buildingStatusMap.put("updateFieldSql", "select inputtime from "
					+ "db_building t inner join db_base_comm c on t.buildstatus=c.seq where t.status = 0");
			
			//将所有需要提醒的数据打包成map
			if(listBuilding != null){
				for (int i = 0; i < listBuilding.size(); i++) {
					remindmapAll.put("hisName"+i, hisNameMap);
					remindmapAll.put("storeyNum"+i, storeyNumMap);
					remindmapAll.put("buildingAreas"+i, buildingAreasMap);
					remindmapAll.put("amount"+i, amountMap);
					remindmapAll.put("mendNum"+i, mendNumMap);
					remindmapAll.put("buildingStatus"+i, buildingStatusMap);
				}
			}
			
			//将已经打包好的MAP转换成JSON
			JSONObject remindJson = JSONObject.fromObject(remindmapAll);
			
			request.setAttribute("remindJson", remindJson);
			logg.debug("    remindJson=   "+remindJson);
		}catch (Exception e) {
			logg.error("HospDetailAction-->showHospDetail", e);
		}
		return "show";
	}
	
	
	
	
	
	
	
	
	
	
	public UsersBusiness getUsersBusiness() {
		return usersBusiness;
	}

	public void setUsersBusiness(UsersBusiness usersBusiness) {
		this.usersBusiness = usersBusiness;
	}

	public Integer getMaterId() {
		return materId;
	}

	public void setMaterId(Integer materId) {
		this.materId = materId;
	}

	public DbBuildMater getBuildMater() {
		return buildMater;
	}

	public void setBuildMater(DbBuildMater buildMater) {
		this.buildMater = buildMater;
	}

	public DbBuildMater getBuildMaterByUsetype() {
		return buildMaterByUsetype;
	}

	public void setBuildMaterByUsetype(DbBuildMater buildMaterByUsetype) {
		this.buildMaterByUsetype = buildMaterByUsetype;
	}

	public HashMap getDicMap() {
		return dicMap;
	}

	public void setDicMap(HashMap dicMap) {
		this.dicMap = dicMap;
	}

	public List<DbBaseComm> getListBaseComm() {
		return listBaseComm;
	}

	public void setListBaseComm(List<DbBaseComm> listBaseComm) {
		this.listBaseComm = listBaseComm;
	}

	public BuildStoreyBusiness getBuildStoreyBusiness() {
		return buildStoreyBusiness;
	}

	public void setBuildStoreyBusiness(BuildStoreyBusiness buildStoreyBusiness) {
		this.buildStoreyBusiness = buildStoreyBusiness;
	}

	public List<DbBuildMater> getListMater_WQ() {
		return listMater_WQ;
	}

	public void setListMater_WQ(List<DbBuildMater> listMaterWQ) {
		listMater_WQ = listMaterWQ;
	}

	public List<DbBuildMater> getListMater_KZ() {
		return listMater_KZ;
	}

	public void setListMater_KZ(List<DbBuildMater> listMaterKZ) {
		listMater_KZ = listMaterKZ;
	}

	public List<DbBuildMater> getListMater_GZ() {
		return listMater_GZ;
	}

	public void setListMater_GZ(List<DbBuildMater> listMaterGZ) {
		listMater_GZ = listMaterGZ;
	}

	public List<DbBuildMater> getListMater_CY() {
		return listMater_CY;
	}

	public void setListMater_CY(List<DbBuildMater> listMaterCY) {
		listMater_CY = listMaterCY;
	}

	public List<DbBuildMater> getListMater_QT() {
		return listMater_QT;
	}

	public void setListMater_QT(List<DbBuildMater> listMaterQT) {
		listMater_QT = listMaterQT;
	}

	public List<DbBuildMater> getListMater_JZ() {
		return listMater_JZ;
	}

	public void setListMater_JZ(List<DbBuildMater> listMaterJZ) {
		listMater_JZ = listMaterJZ;
	}

	public List<DbBuildMater> getListMater_FS() {
		return listMater_FS;
	}

	public void setListMater_FS(List<DbBuildMater> listMaterFS) {
		listMater_FS = listMaterFS;
	}

	public List<DbBuildMater> getListMater_ZJ() {
		return listMater_ZJ;
	}

	public void setListMater_ZJ(List<DbBuildMater> listMaterZJ) {
		listMater_ZJ = listMaterZJ;
	}

	public List<DbBuildMater> getListMater_MY() {
		return listMater_MY;
	}

	public void setListMater_MY(List<DbBuildMater> listMaterMY) {
		listMater_MY = listMaterMY;
	}

	public List<DbBuildMater> getListMater_WND() {
		return listMater_WND;
	}

	public void setListMater_WND(List<DbBuildMater> listMaterWND) {
		listMater_WND = listMaterWND;
	}

	public List<DbBuildMater> getListMater_DP() {
		return listMater_DP;
	}

	public void setListMater_DP(List<DbBuildMater> listMaterDP) {
		listMater_DP = listMaterDP;
	}

	public List<DbBuildMater> getListMater_YT() {
		return listMater_YT;
	}

	public void setListMater_YT(List<DbBuildMater> listMaterYT) {
		listMater_YT = listMaterYT;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<DbBuildMater> getListMater() {
		return listMater;
	}

	public void setListMater(List<DbBuildMater> listMater) {
		this.listMater = listMater;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<DbBuilding> getListBuilding() {
		return listBuilding;
	}

	public void setListBuilding(List<DbBuilding> listBuilding) {
		this.listBuilding = listBuilding;
	}

	public Map getMapBuildMap() {
		return mapBuildMap;
	}

	public void setMapBuildMap(Map mapBuildMap) {
		this.mapBuildMap = mapBuildMap;
	}

	public BaseCommBusiness getBaseCommBusiness() {
		return baseCommBusiness;
	}

	public void setBaseCommBusiness(BaseCommBusiness baseCommBusiness) {
		this.baseCommBusiness = baseCommBusiness;
	}

	public BuildMaterBusiness1 getBuildMaterBusiness() {
		return buildMaterBusiness;
	}

	public void setBuildMaterBusiness(BuildMaterBusiness1 buildMaterBusiness) {
		this.buildMaterBusiness = buildMaterBusiness;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public List<DbHospInfo> getListHosp() {
		return listHosp;
	}

	public void setListHosp(List<DbHospInfo> listHosp) {
		this.listHosp = listHosp;
	}

	public Integer getHospInfoId() {
		return hospInfoId;
	}

	public void setHospInfoId(Integer hospInfoId) {
		this.hospInfoId = hospInfoId;
	}

	public HospInfoBusiness getHospInfoBusiness() {
		return hospInfoBusiness;
	}

	public void setHospInfoBusiness(HospInfoBusiness hospInfoBusiness) {
		this.hospInfoBusiness = hospInfoBusiness;
	}

	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}

	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}

	public DbBuilding getBuilding() {
		return building;
	}

	public void setBuilding(DbBuilding building) {
		this.building = building;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getShowOrhide() {
		return showOrhide;
	}

	public void setShowOrhide(String showOrhide) {
		this.showOrhide = showOrhide;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map getBuildingMenuMap() {
		return buildingMenuMap;
	}

	public void setBuildingMenuMap(Map buildingMenuMap) {
		this.buildingMenuMap = buildingMenuMap;
	}

	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}

	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}


	public EquipListBusiness getEquipListBusiness() {
		return equipListBusiness;
	}


	public void setEquipListBusiness(EquipListBusiness equipListBusiness) {
		this.equipListBusiness = equipListBusiness;
	}

	// public DbBaseComm getBaseComm() {
	// return baseComm;
	// }
	//
	// public void setBaseComm(DbBaseComm baseComm) {
	// this.baseComm = baseComm;
	// }
	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}
  public DbBuilding getTemp() {
		return temp;
	}

	public void setTemp(DbBuilding temp) {
		this.temp = temp;
	}
}

package com.hanqian.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildMaterBusiness1;
import com.hanqian.business.BuildStoreyBusiness;
import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.BuildrepairBussiness;
import com.hanqian.business.HospInfoBusiness;
import com.hanqian.form.VOBuildingRepair;
import com.hanqian.pojo.DbBuildMater;
import com.hanqian.pojo.DbBuilding;
import com.hanqian.pojo.DbBuildingRepair;
import com.hanqian.pojo.DbBuildingStorey;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbReportRate;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;


public class BuildRepairAction {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(BuildRepairAction.class);
	private VOBuildingRepair voBuildingRepair=new VOBuildingRepair();
	private Integer buildingId; 
	private BuildingBusiness buildingBussiness;
	private List<DbBuilding> listBuild;
	private BuildMaterBusiness1 materBusiness;
	private BuildrepairBussiness buildRepairBussiness;
	private List<DbBuildMater> listMater_KZ; 
	private List<DbBuildMater> listMater_GZ;
	private List<DbBuildMater> listMater_FS;
	private List<DbBuildMater> listMater_ZJ;
	private List<DbBuildMater> listMater_WQ;
	private HttpServletRequest request;
	private List<DbBuildingRepair> listRepair;
	private DbBuildingRepair dbBuildingRepair;
	private String message;
	private String showOrhide;
	private String editFlag;
	private Integer repid; // 点击修改时，传到后台的维修ID
	private String hideOrshow;
	private String tag;
	private HospInfoBusiness hospBusiness;
	private List<DbHospInfo> listHosp; // 医院集合
	private List<DbBuildMater> listMater;
	private String isShowHosp;
	private String hospName;
	private List<DbBuildingStorey> buildarea;   //楼宇区域面积
	private List<DbBuildingStorey> builduseArea;   //单个用途总面积
	private List hospitalArea;  
	private BuildStoreyBusiness storeyMgr;
	Integer hospid;
	private String tabIndex;


	
	public void setStoreyMgr(BuildStoreyBusiness storeyMgr) {
		this.storeyMgr = storeyMgr;
	}

	
	/**
	 * 
	 * @param listBuild
	 * @return
	 */
	private String transString(List<DbBuilding> listBuild) {
		String result = "";
		Map map = null;
		if (listBuild != null && listBuild.size() > 0) {
			map = new HashMap();
			for (DbBuilding build : listBuild) {
				if (map.get(build.getDbHospInfo().getSeqHosp()) == null) {
					StringBuffer sb = new StringBuffer();
					sb.append(build.getDbHospInfo().getSeqHosp()
							+ ":{builds:[{id:\"" + build.getBuildingId()
							+ "\",name:\"" + build.getBuildingName() + "\"}");
					map.put(build.getDbHospInfo().getSeqHosp(), sb);
				} else {
					StringBuffer sb = (StringBuffer) map.get(build
							.getDbHospInfo().getSeqHosp());
					sb.append(",{id:\"" + build.getBuildingName() + "\",name:\""
							+ build.getBuildingName()+ "\"}");
				}
			}
		}
		if (map != null && !map.isEmpty()) {
			Iterator iter = map.values().iterator();
			StringBuffer sb = new StringBuffer();
			while (iter.hasNext()) {
				sb.append(iter.next().toString());
				sb.append("]},");
			}
			String sss = sb.toString();
			int len = sss.length() - 1;
			result = "{" + sss.substring(0, len) + "};";
		}

		return result;
	}

	
	/**
	 * 查询楼宇大修信息列表
	 * @return 
	 */
	public String findBuildingRepair() {
		int currentPage = 1;
		int pageSize = 10;

 		request = ServletActionContext.getRequest();
		// 获取当前页数
 		String page = request.getParameter("page");
		if (!SysUtil.isNull(page)) {
			currentPage = Integer.parseInt(page);
		}
		//获取类型字典表
		DbBuildMater mater = new DbBuildMater();
		RetCode rc1 = materBusiness.findBuildingMater(mater);
		listMater = (List<DbBuildMater>) rc1.getObj();
 		DbBuildingRepair repair1=new DbBuildingRepair();
 		List<DbBuilding> buildings = null;
 		if(request.getSession().getAttribute("users")!=null){
			DbUsers user = (DbUsers) request.getSession().getAttribute("users");
			hospid = user.getDbHospInfo().getSeqHosp();
		}
		if(!SysUtil.isNull(hospid.toString())){
			DbBuilding build = new DbBuilding();
			DbHospInfo hosp=new DbHospInfo();
			hosp.setSeqHosp(hospid);
			build.setDbHospInfo(hosp);
			RetCode r = buildingBussiness.findDBBuilding(build);
			buildings = (List<DbBuilding>) r.getObj();
			String buildId=request.getParameter("buildingId");
			if(StringUtils.isNotEmpty(buildId)){
				buildingId=Integer.parseInt(buildId);
			}
			build.setBuildingId(buildingId);
			repair1.setDbBuilding(build);
		}
		request.setAttribute("buildings", buildings);
		request.setAttribute("buildingId", buildingId);
		String begin = request.getParameter("beginDate");
		String end = request.getParameter("endDate");
		//按条件查询楼宇大修信息
		RetCode rc = buildRepairBussiness.findBuildingRepair(repair1,begin,end,currentPage, pageSize);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		listRepair = (List<DbBuildingRepair>) rc.getObj();
		request.setAttribute("page", rc.getPage());
		
		
		
		DbBuildingRepair repair = new DbBuildingRepair();
		RetCode r = buildRepairBussiness.findBuildingRe(repair);
		List<DbBuildingRepair> builds = null;
		builds = (List<DbBuildingRepair>) r.getObj();
		request.setAttribute("builds", builds);
		
		String status=request.getParameter("xX");
		if("1".equals(status)){
			// 查询楼宇区域信息
			Integer buildingId=Integer.parseInt(request.getParameter("buildingId"));
			buildarea=storeyMgr.findBuildingGroup(buildingId);
			//获取单个用途总面积
			builduseArea=storeyMgr.findBuildinguseArea();
			//获取医院总面积
			hospitalArea=storeyMgr.findHospInfo(hospid);//获取医院总面积
			
			listBuild=new ArrayList<DbBuilding>();
			DbHospInfo hospinfo=new DbHospInfo();
			hospinfo.setSeqHosp(hospid);
			DbBuilding build=new DbBuilding();
			build.setBuildingId(Integer.parseInt(request.getParameter("buildingId")));
			build.setDbHospInfo(hospinfo);
			RetCode reCode=buildingBussiness.findDBBuilding(build);
			if(reCode.getObj()!=null){
				listBuild=(List<DbBuilding>) reCode.getObj();
			}
			request.setAttribute("BUIAREA",listBuild.get(0).getBuildingAreas() );  //楼宇总面积
			request.setAttribute("buildingId", buildingId);
			request.setAttribute("shows", request.getParameter("shows"));
			return "lyxqJG";
		}else{
			return "showRepairList";
		}
	}
	

	/**
	 * 添加、修改大修信息
	 * 
	 * @return
	 */
	public String showAddBuildRepair() {
		request = ServletActionContext.getRequest();

		DbBuilding bu = new DbBuilding();
		DbUsers use = (DbUsers) request.getSession().getAttribute("users"); // 获得登陆用户对象
		/*DbUsers user = new DbUsers();
		user.setSeq(use.getSeq());*/
		bu.setBuildingId(Integer.parseInt(request.getParameter("buildingId")));
		dbBuildingRepair.setDbBuilding(bu);
		dbBuildingRepair.setOper(use.getSeq());// 操作员
		dbBuildingRepair.setOpertime(new Date()); // 操作时间
		dbBuildingRepair.setDatastatus(0);//0为显示

		String reid = request.getParameter("repid");
		if (!SysUtil.isNull(reid)) {
			 //修改
			try {
				dbBuildingRepair.setSeq(Integer.parseInt(reid));
				buildRepairBussiness.updateBuildingRepair(dbBuildingRepair);
				message = "1";
			} catch (Exception e) {
				logg.error("BuildRepairAction-->showAddBuildRepair", e);
				message = "2";
			} finally {
				request.setAttribute("message", message);
			}
		} else {
			 //添加
			try {
				boolean flag= buildRepairBussiness.insertBuildingRepair(dbBuildingRepair);
				if(flag){
					//大修次数 ,添加楼宇大修信息次数加一
					DbBuilding dbBuilding=new DbBuilding();
					dbBuilding.setBuildingId(Integer.valueOf(buildingId));
					buildingBussiness.updateBuildingForMendNumPlus(dbBuilding);
				}
				
				message = "1";
			} catch (Exception e) {
				logg.error("BuildRepairAction-->showAddBuildRepair", e);
				message = "2";
			} finally {
				request.setAttribute("message", message);
			}
		}
		if ( request.getParameter("buildingId")!=null) {
			buildingId =  Integer.parseInt(request.getParameter("buildingId"));
		}
		request.setAttribute("buildingId", buildingId);
		showOrhide="dx";
		editFlag="2";
		tabIndex="4";
		return "showList";
	}

	
	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String showBuildRepairDic() {
			this.findBuildRepairDic();
			return "addRepair";
	}
	
	
	/**
	 * 初始化添加页面
	 * 
	 * */
	private void findBuildRepairDic() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String addOredit = request.getParameter("addOredit");
		request.setAttribute("ADDorEDIT", addOredit);

		Integer buildingId=Integer.parseInt(request.getParameter("buildingId"));
		if (!SysUtil.isNull(buildingId.toString())) {
			DbBuilding build = new DbBuilding();
			build.setBuildingId(buildingId);
			RetCode rc = buildingBussiness.findDBBuilding(build);
			if (rc.getObj() != null) {
				listBuild = (List<DbBuilding>) rc.getObj();
				request.setAttribute("BUILD", listBuild.get(0));
				request.setAttribute("hospid", listBuild.get(0).getDbHospInfo());
			}
		}

		DbBuildMater mater;

		// 外墙材料
		mater = new DbBuildMater();
		mater.setMaterId(2);
		if (mater != null) {
			RetCode rc_WQ = materBusiness.findAllBuildMater(mater.getMaterId());
			if (rc_WQ.getObj() != null) {
				listMater_WQ = (List<DbBuildMater>) rc_WQ.getObj();
			}
		}

		// 抗震烈度
		mater = new DbBuildMater();
		mater.setMaterId(4);
		if (mater != null) {
			RetCode rc_KZ = materBusiness.findAllBuildMater(mater.getMaterId());
			if (rc_KZ.getObj() != null) {
				listMater_KZ = (List<DbBuildMater>) rc_KZ.getObj();
			}
		}

		//改造前主要问题
		mater = new DbBuildMater();
		mater.setMaterId(6);
		if (mater != null) {
			RetCode rc_GZ = materBusiness.findAllBuildMater(mater.getMaterId());
			if (rc_GZ.getObj() != null) {
				listMater_GZ = (List<DbBuildMater>) rc_GZ.getObj();
			}
		}

		// 防水等级
		mater = new DbBuildMater();
		mater.setMaterId(3);
		if (mater != null) {
			RetCode rc_FS = materBusiness.findAllBuildMater(mater.getMaterId());
			if (rc_FS.getObj() != null) {
				listMater_FS = (List<DbBuildMater>) rc_FS.getObj();
			}
		}

		// 造价依据
		mater = new DbBuildMater();
		mater.setMaterId(5);
		if (mater != null) {
			RetCode rc_ZJ = materBusiness.findAllBuildMater(mater.getMaterId());
			if (rc_ZJ.getObj() != null) {
				listMater_ZJ = (List<DbBuildMater>) rc_ZJ.getObj();
			}
		}
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateShowRepair() {
		request = ServletActionContext.getRequest();
		if (!SysUtil.isNull(repid.toString())) {
			DbBuildingRepair rep = new DbBuildingRepair();
			rep.setSeq(repid);
			RetCode rc = buildRepairBussiness.findBuildingRepair(rep);
			if (rc.getObj() != null) {
				listRepair = (List<DbBuildingRepair>) rc.getObj();
				request.setAttribute("REPAIR", listRepair.get(0)); 
				listRepair.get(0).getDbBuilding();
				request.setAttribute("BUILD", listRepair.get(0).getDbBuilding());
				request.setAttribute("REPID", repid);
			}
		}
		this.findBuildRepairDic();
		request.setAttribute("buildingId", request.getParameter("buildingId"));
		request.setAttribute("tabIndex", "4");
		String detail=request.getParameter("details");
		tabIndex="4";
		if("details".equals(detail)){
			return "repairDetails";
		}else{
			return "addRepair";
		}
	}
	
	/**
	 * 删除楼宇大修记录
	 * @return
	 */
	public String deleteBuildRepair() {
		request = ServletActionContext.getRequest();
		String buildingId=request.getParameter("buildingId");
		try {
			DbBuildingRepair rap = new DbBuildingRepair();
			rap.setSeq(repid);
			int result=buildRepairBussiness.deleteBuildingRepair(repid);
			if(result>0){
				//大修次数 ,删除哪个楼宇的 几个大修信息就减去多少次
				DbBuilding dbBuilding=new DbBuilding();
				dbBuilding.setBuildingId(Integer.valueOf(buildingId));
				for (int i = 0; i < result; i++) {
					buildingBussiness.updateBuildingForMendNum(dbBuilding);
				}
				message = "1";
			}else{
				message = "2";
			}
		} finally {
			request.setAttribute("message", message);
			request.setAttribute("buildingId", buildingId);
			tabIndex="4";
		}
		return "showList";
	}
	
	/**
	 * 
	 * @param reportMap
	 * @param reportRate
	 */
	public void getBuildrepair(Map reportMap, DbReportRate reportRate) {
		RetCode RepairCode = buildRepairBussiness.reportBuildRepair(reportRate
				.getReportdate());
		if (RepairCode.getObj() != null) {
			reportMap.put(reportRate.getDbReportType().getTypeId() + "",
					RepairCode.getObj());
		}
	}
	
	public List<DbBuildingRepair> getListRepair() {
		return listRepair;
	}
	public void setListRepair(List<DbBuildingRepair> listRepair) {
		this.listRepair = listRepair;
	}
	public List<DbBuildMater> getListMater_WQ() {
		return listMater_WQ;
	}
	public void setListMater_WQ(List<DbBuildMater> listMaterWQ) {
		listMater_WQ = listMaterWQ;
	}
	public List<DbBuildMater> getListMater_ZJ() {
		return listMater_ZJ;
	}
	public void setListMater_ZJ(List<DbBuildMater> listMaterZJ) {
		listMater_ZJ = listMaterZJ;
	}
	public List<DbBuildMater> getListMater_FS() {
		return listMater_FS;
	}
	public void setListMater_FS(List<DbBuildMater> listMaterFS) {
		listMater_FS = listMaterFS;
	}
	public List<DbBuildMater> getListMater_GZ() {
		return listMater_GZ;
	}
	public void setListMater_GZ(List<DbBuildMater> listMaterGZ) {
		listMater_GZ = listMaterGZ;
	}
	public List<DbBuildMater> getListMater_KZ() {
		return listMater_KZ;
	}
	public void setListMater_KZ(List<DbBuildMater> listMaterKZ) {
		listMater_KZ = listMaterKZ;
	}


	public List<DbBuilding> getListBuild() {
		return listBuild;
	}
	public void setListBuild(List<DbBuilding> listBuild) {
		this.listBuild = listBuild;
	}
	public BuildingBusiness getBuildingBussiness() {
		return buildingBussiness;
	}
	public void setBuildingBussiness(BuildingBusiness buildingBussiness) {
		this.buildingBussiness = buildingBussiness;
	}

	
	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public BuildrepairBussiness getBuildRepairBussiness() {
		return buildRepairBussiness;
	}
	public void setBuildRepairBussiness(BuildrepairBussiness buildRepairBussiness) {
		this.buildRepairBussiness = buildRepairBussiness;
	}
	public DbBuildingRepair getDbBuildingRepair() {
		return dbBuildingRepair;
	}

	public void setDbBuildingRepair(DbBuildingRepair dbBuildingRepair) {
		this.dbBuildingRepair = dbBuildingRepair;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShowOrhide() {
		return showOrhide;
	}

	public void setShowOrhide(String showOrhide) {
		this.showOrhide = showOrhide;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public Integer getRepid() {
		return repid;
	}

	public void setRepid(Integer repid) {
		this.repid = repid;
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
	public HospInfoBusiness getHospBusiness() {
		return hospBusiness;
	}


	public void setHospBusiness(HospInfoBusiness hospBusiness) {
		this.hospBusiness = hospBusiness;
	}

	public List<DbHospInfo> getListHosp() {
		return listHosp;
	}

	public void setListHosp(List<DbHospInfo> listHosp) {
		this.listHosp = listHosp;
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
	public String getIsShowHosp() {
		return isShowHosp;
	}
	public void setIsShowHosp(String isShowHosp) {
		this.isShowHosp = isShowHosp;
	}
	public VOBuildingRepair getVoBuildingRepair() {
		return voBuildingRepair;
	}
	public void setVoBuildingRepair(VOBuildingRepair voBuildingRepair) {
		this.voBuildingRepair = voBuildingRepair;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
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
	public List getHospitalArea() {
		return hospitalArea;
	}
	public void setHospitalArea(List hospitalArea) {
		this.hospitalArea = hospitalArea;
	}

	public Integer getHospid() {
		return hospid;
	}


	public void setHospid(Integer hospid) {
		this.hospid = hospid;
	}


	/**
	 * @return the tabIndex
	 */
	public String getTabIndex() {
		return tabIndex;
	}


	/**
	 * @param tabIndex the tabIndex to set
	 */
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}

}

package com.hanqian.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.EquipGroupBusiness;
import com.hanqian.business.SerMainBusiness;
import com.hanqian.business.SermainDetailBsiness;
import com.hanqian.pojo.DbSermainDetail;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.opensymphony.xwork2.ActionSupport;

public class SerMainAction extends ActionSupport{

	private SerMainBusiness serMainBusiness;
	//权限
	private MenuInterceptor menuInterceptor;
	//
	private Map menuMap;
	//设备分组
	private EquipGroupBusiness	equipGroupBusiness;
	private BuildingBusiness buildingBusiness;
	//设备类型 集合
	private List eqtypelist ;
	// 分页
	private Page page;
	private int pageSize = 5;
	private int currentPage = 1;
	// 日志
	private static final Log log = LogFactory.getLog(UsersAction.class);
	
	//班组集合
	private List groupList;
	//一发短信列表集合
	private List cueLogList;
	//设备ID
	private String equipId;
	//维修人员
	private String detailId;
	//维修人员集合
	private List sermainDetailList;
	//班组名称
	private String groupName;
	//班组id
	private String groupId;
	//修改页面 表示
	private String update;
	//告警级别
	private String repairLevel;
	//
	private SermainDetailBsiness sermainDetailBsiness;
	private DbSermainDetail dbSermainDetail;	//维修人员表
	
	
	//UserId 
	private String name;
	//
	private String menName;
	//
	private String sendTime;
	


	/**
	 * 查询班组信息
	 * @param 2012-12-6  
	 * @param @return       
	 * @return short
	 */
	public String findSerMain(){
		//权限过滤
 
		menuMap=menuInterceptor.menuIntercept("5007001");
		if(menuMap==null){
			menuInterceptor.toLoginjsp();
			return null;
		}
		//查询
		RetCode rc=serMainBusiness.findSerMain(groupName, pageSize, currentPage);
		if(null!=rc && null!=rc.getObj()){
			groupList= (List)rc.getObj();
		}
		if(null==rc.getPage()){
			page=new Page(1, 0, 10);
		}else{
			page=rc.getPage();
		}
		return "serMainList";
	}

	/**
	 * 
	 * 
	 * 
	 * 查询已发送短信列表
	 * @param 2015-11-12
	 * @param @return       
	 * @return String
	 * 
	 * 
	 */
	public String findSerCueLog(){
		//权限过滤
		 
		menuMap=menuInterceptor.menuIntercept("5007004");
		if(menuMap==null){
			menuInterceptor.toLoginjsp();
			return null;
		}
/*		  
 *    分页 10条记录
 *    
 * HttpServletRequest  request =ServletActionContext.getRequest();
		String pageIndex = request.getParameter("currentPage");
		if (StringUtils.isNotEmpty(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		} else {
			currentPage = 1;
		}
		pageSize = 10;*/
		//查询
		RetCode rc=serMainBusiness.findSerCueLog(name,menName,sendTime, pageSize, currentPage);
		/*
		 * 10条记录
		 * if(null!=rc && null!=rc.getObj()){
			cueLogList= (List)rc.getObj();
			page=rc.getPage();
		}*/
		/**
		 * 分页5条记录的那种
		 * 
		 */
		if(null!=rc && null!=rc.getObj()){
			cueLogList= (List)rc.getObj();
		}
		if(null==rc.getPage()){
			page=new Page(1, 0, 10);
		}else{
			page=rc.getPage();
		}
		return "serCueLogList";
	}
	
	/**
	 * 去到添加页面
	 * @param 2012-12-6  
	 * @param @return       
	 * @return String
	 */
	public String toAddSerMain(){
		//获取父级 设备类型
		eqtypelist=equipGroupBusiness.findEqTypeListNotPei();
		//获取所有维修人员
		sermainDetailList=sermainDetailBsiness.findSerMainAll();

		return "serMainAddOrUpdate";
	}
	
	/**
	 * 添加
	 * @param 2012-12-10  
	 * @param @return       
	 * @return String
	 */
	public String addSerMain(){
		String []eqId=null;
		String []dlId=null;
		//获取设备ID  数组
		if(equipId!=null && !equipId.equals("")){
			eqId=equipId.split(",");	
		}
		//获取设备维修人员
		if(detailId!=null && !detailId.equals("")){
			dlId=detailId.split(",");	
		}
//		System.out.println(equipId+"  :   "+detailId);
		boolean check=serMainBusiness.insertSerMainAll(groupName, eqId, dlId,repairLevel);
		
		return this.findSerMain();
	}
	
	
	/**
	 * 去到修改页面
	 * @param 2012-12-6  
	 * @param @return       
	 * @return String
	 */
	public String toUpdateSerMain(){
		HttpServletRequest req=ServletActionContext.getRequest();
		try {
		System.out.println("进入修改"+groupId+ groupName);
		this.update="update";
		//获取父级 设备类型
		eqtypelist=equipGroupBusiness.findEqTypeListNotPei();
		//获取未被自己所有维修人员
		sermainDetailList=sermainDetailBsiness.findSerMainNotUserAll(groupId);
		//该组以有的维修人员；
		List repairList=sermainDetailBsiness.findSerMainUserAll(groupId);
		//获取维修班组里设备;
		List serMainEquipList=serMainBusiness.findSerMianEquip(groupId);
		//获取告警级别信息
		List gaojingjibie=serMainBusiness.gaojingjibie(groupId);
		req.setAttribute("updateFlag", "11");
		req.setAttribute("serMainEquipList", serMainEquipList);
		req.setAttribute("repairList", repairList);
		req.setAttribute("gaojingjibie", gaojingjibie.get(0));
		} catch (Exception e) {
			log.error("toUpdateSerMain"+e);
		}
		return "serMainAddOrUpdate";
	}
	
	/**
	 * 修改班组以及关系
	 * @param 2012-12-10  
	 * @param @return       
	 * @return String
	 */
	public String updateSerMain(){
//		System.out.println("修改");
		String []eqId=null;
		String []dlId=null;
		
		//获取设备ID  数组
		if(equipId!=null && !equipId.equals("")){
			eqId=equipId.split(",");	
		}
		//获取设备维修人员
		if(detailId!=null && !detailId.equals("")){
			dlId=detailId.split(",");	
		}
//		if(eqId!=null){
//			System.out.print("eqid: ");
//			for (int i = 0; i < eqId.length; i++) {
//				System.out.print(eqId[i]+"   ");
//			}
//		}
//		if(dlId!=null){
//			System.out.print("dlid: ");
//			for (int i = 0; i < dlId.length; i++) {
//				System.out.print(dlId[i]+"   ");
//			}
//		}
		//修改
		serMainBusiness.updateSerMainAll(groupId,groupName, eqId, dlId,repairLevel);
		
		return this.findSerMain();
	}
 
 

	/**
	 * 逻辑删除
	 * @param 2012-12-6  
	 * @param @return       
	 * @return String
	 */
	public String deleteSerMain(){
	try {
			//逻辑删除
			if(null!=groupId && !"".equals(groupId)){
				//逻辑删除班组信息
				serMainBusiness.deleteSerMain(groupId);
				//物理删除班组和设备的关系表信息db_equip_sermain
				serMainBusiness.deleteEquipSerMain(groupId);
				//物理删除班组和人员的关系表信息db_equip_sermain
				serMainBusiness.deleteSerMainDetail(groupId);
			}
		} catch (Exception e) {
			log.error("deleteSerMain-班组和(设备-人员)关系表删除失败"+e);
		}
		return this.findSerMain();
	}
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public SerMainBusiness getSerMainBusiness() {
		return serMainBusiness;
	}


	public void setSerMainBusiness(SerMainBusiness serMainBusiness) {
		this.serMainBusiness = serMainBusiness;
	}


	public MenuInterceptor getMenuInterceptor() {
		return menuInterceptor;
	}


	public void setMenuInterceptor(MenuInterceptor menuInterceptor) {
		this.menuInterceptor = menuInterceptor;
	}


	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public List getEqtypelist() {
		return eqtypelist;
	}

	public void setEqtypelist(List eqtypelist) {
		this.eqtypelist = eqtypelist;
	}

	public EquipGroupBusiness getEquipGroupBusiness() {
		return equipGroupBusiness;
	}

	public void setEquipGroupBusiness(EquipGroupBusiness equipGroupBusiness) {
		this.equipGroupBusiness = equipGroupBusiness;
	}

	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public List getGroupList() {
		return groupList;
	}


	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}


	public static Log getLog() {
		return log;
	}


	public String getUpdate() {
		return update;
	}


	public void setUpdate(String update) {
		this.update = update;
	}

	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}

	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}

	public SermainDetailBsiness getSermainDetailBsiness() {
		return sermainDetailBsiness;
	}

	public List getSermainDetailList() {
		return sermainDetailList;
	}

	public void setSermainDetailList(List sermainDetailList) {
		this.sermainDetailList = sermainDetailList;
	}

	public void setSermainDetailBsiness(SermainDetailBsiness sermainDetailBsiness) {
		this.sermainDetailBsiness = sermainDetailBsiness;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public DbSermainDetail getDbSermainDetail() {
		return dbSermainDetail;
	}

	public void setDbSermainDetail(DbSermainDetail dbSermainDetail) {
		this.dbSermainDetail = dbSermainDetail;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}
	/**
	 * @return the repairLevel
	 */
	public String getRepairLevel() {
		return repairLevel;
	}

	/**
	 * @param repairLevel the repairLevel to set
	 */
	public void setRepairLevel(String repairLevel) {
		this.repairLevel = repairLevel;
	}

	public List getCueLogList() {
		return cueLogList;
	}

	public void setCueLogList(List cueLogList) {
		this.cueLogList = cueLogList;
	}



	
	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenName() {
		return menName;
	}

	public void setMenName(String menName) {
		this.menName = menName;
	}
	
}
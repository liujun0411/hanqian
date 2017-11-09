package com.hanqian.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hanqian.business.BuildingBusiness;
import com.hanqian.business.EqNavigationBusiness;
import com.hanqian.form.VOEquipBuild;
import com.hanqian.util.Page;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

public class EqNavigationAction {

	private HttpServletRequest request;
	private Page page;
	private Integer pageSize = 10;
	private Integer currentPage;
	private EqNavigationBusiness eqNavigationBusiness;
	private BuildingBusiness buildingBusiness;
	private VOEquipBuild voEquipBuild;
	/**
	 * 返回监控设备列表
	 * 
	 * @return
	 */
	public String showEquipList() {
		request = ServletActionContext.getRequest();
		if (currentPage == null) {
			currentPage = 1;
		}
		//查询条件		
		//类型名称
		String typeName = request.getParameter("typeName");
		//获取设备类型ID
		String eqTypeId=request.getParameter("eqTypeId");
		//楼宇
		String buildId = request.getParameter("buildId");
		//设备型号	
		String unitType = request.getParameter("unitType");
		//安装位置
		String site = request.getParameter("place");
		//所在楼宇
		String storey = request.getParameter("storey");
		//服务楼层
		String serviceStorey = request.getParameter("serviceStorey");
		//服务楼宇
		String serviceBuildId = request.getParameter("serviceBuildId");
		//服务面积
		String areas = request.getParameter("areas");
		//服务区域
		String square = request.getParameter("square");
		RetCode rt = eqNavigationBusiness.findEquipBuilding(eqTypeId,buildId,unitType,site,storey,serviceStorey,serviceBuildId,areas,square,currentPage,pageSize);
		List<VOEquipBuild> equipBuildList = new  ArrayList<VOEquipBuild>();
		if (rt.getObj()!=null) {
			List list = (List)rt.getObj();
			for (int i = 0; i < list.size(); i++) {
				VOEquipBuild voEqBuild = new  VOEquipBuild();
				Map map = (Map)list.get(i);
				if (map.get("building_id")!=null) {
					voEqBuild.setBuildId(map.get("building_id").toString());
				}else{
					voEqBuild.setBuildId(null);
				}
				if (map.get("building_name")!=null) {
					voEqBuild.setBuildName(map.get("building_name").toString());
				}else{
					voEqBuild.setBuildName(null);
				}
				if (map.get("equip_type_id")!=null) {
					voEqBuild.setEqTypeId(map.get("equip_type_id").toString());
				}else{
					voEqBuild.setEqTypeId(null);
				}
				if (map.get("equip_id")!=null) {
					voEqBuild.setEquipId(map.get("equip_id").toString());
				}else{
					voEqBuild.setEquipId(null);
				}
				if (map.get("storey")!=null) {
					voEqBuild.setStorey(map.get("storey").toString().replace("-", "B"));
				}else{
					voEqBuild.setStorey(null);
				}
				if (map.get("type_name")!=null) {
					voEqBuild.setTypeName(map.get("type_name").toString());
				}else{
					voEqBuild.setTypeName(null);
				}
				if (map.get("unit_type")!=null) {
					voEqBuild.setUnitType(map.get("unit_type").toString());
				}else{
					voEqBuild.setUnitType(null);
				}
				if (map.get("place")!=null) {
					voEqBuild.setPlace(map.get("place").toString());
				}else{
					voEqBuild.setPlace(null);
				}
				if (map.get("equip_name")!=null) {
					voEqBuild.setEquipName(map.get("equip_name").toString());
				}else{
					voEqBuild.setEquipName(null);
				}
				if (map.get("servicestorey")!=null) {
					voEqBuild.setServiceStorey(map.get("servicestorey").toString().replace("-", "B"));
				}else{
					voEqBuild.setServiceStorey(null);
				}
				if (map.get("servicebuildid")!=null&&!"0".equals(serviceBuildId)) {
					voEqBuild.setServiceBuildId(map.get("servicebuildid").toString());
				}else{
					voEqBuild.setServiceBuildId(null);
				}if (map.get("areas")!=null) {
					voEqBuild.setAreas(map.get("areas").toString());
				}else{
					voEqBuild.setAreas(null);
				}
				if (map.get("square")!=null) {
					voEqBuild.setSquare(map.get("square").toString());
				}else{
					voEqBuild.setSquare(null);
				}
				equipBuildList.add(voEqBuild);
			}
		}
		//查询条件
		voEquipBuild = new VOEquipBuild();
		voEquipBuild.setBuildId(buildId);
		voEquipBuild.setEqTypeId(eqTypeId);
		voEquipBuild.setStorey(storey);
		voEquipBuild.setPlace(site);
		voEquipBuild.setUnitType(unitType);
		voEquipBuild.setTypeName(typeName);
		voEquipBuild.setAreas(areas);
		voEquipBuild.setSquare(square);
		voEquipBuild.setServiceStorey(serviceStorey);
		voEquipBuild.setServiceBuildId(serviceBuildId);
		
		request.setAttribute("voEquipBuild", voEquipBuild);
		request.setAttribute("equipBuildlist", equipBuildList);
		request.setAttribute("message", rt.getDesc());
		request.setAttribute("buildList", getBuildingInfoList());
		page = rt.getPage();
		return "equipBuild";
	}
	private List getBuildingInfoList(){
		RetCode rc = buildingBusiness.findBuildingBySql();
		List buildList = (List)rc.getObj();
		return buildList;
	}
//	private 
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the eqNavigationBusiness
	 */
	public EqNavigationBusiness getEqNavigationBusiness() {
		return eqNavigationBusiness;
	}
	/**
	 * @param eqNavigationBusiness the eqNavigationBusiness to set
	 */
	public void setEqNavigationBusiness(EqNavigationBusiness eqNavigationBusiness) {
		this.eqNavigationBusiness = eqNavigationBusiness;
	}
	/**
	 * @return the buildingBusiness
	 */
	public BuildingBusiness getBuildingBusiness() {
		return buildingBusiness;
	}
	/**
	 * @param buildingBusiness the buildingBusiness to set
	 */
	public void setBuildingBusiness(BuildingBusiness buildingBusiness) {
		this.buildingBusiness = buildingBusiness;
	}
}

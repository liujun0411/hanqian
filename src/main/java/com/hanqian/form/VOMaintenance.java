package com.hanqian.form;

/**
 * 描 述: 用于查询数据
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-13
 * @see
 */
public class VOMaintenance {

	private String eqName;  //设备名称
	
	private String buildName; //楼名
	
	private String hisName;  //曾用名
	
	private String eqTypeId; //设备类型ＩＤ
	
	private String eqTypeName; //设备类型name
	
	private String place;   //安装位置
	
	private String storey;  //楼层
	
	private String unitType;  //型号
	
	private String serviceDateStart;
	
	private String serviceDateEnd;
	
	private String maintenancetype;// 维护类型

	public String getMaintenancetype() {
		return maintenancetype;
	}

	public void setMaintenancetype(String maintenancetype) {
		this.maintenancetype = maintenancetype;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getHisName() {
		return hisName;
	}

	public void setHisName(String hisName) {
		this.hisName = hisName;
	}

	public String getEqTypeId() {
		return eqTypeId;
	}

	public void setEqTypeId(String eqTypeId) {
		this.eqTypeId = eqTypeId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStorey() {
		return storey;
	}

	public void setStorey(String storey) {
		this.storey = storey;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getServiceDateStart() {
		return serviceDateStart;
	}

	public void setServiceDateStart(String serviceDateStart) {
		this.serviceDateStart = serviceDateStart;
	}

	public String getServiceDateEnd() {
		return serviceDateEnd;
	}

	public void setServiceDateEnd(String serviceDateEnd) {
		this.serviceDateEnd = serviceDateEnd;
	}

	public String getEqTypeName() {
		return eqTypeName;
	}

	public void setEqTypeName(String eqTypeName) {
		this.eqTypeName = eqTypeName;
	}
	
	
}

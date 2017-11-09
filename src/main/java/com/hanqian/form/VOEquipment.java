package com.hanqian.form;

public class VOEquipment {
	
	private String eqId;				//设备ID
	
	private String hospitalid;        	//医院ID

	private String installStart;	 	//安装日期(查询起始)

	private String installEnd;		  	//安装日期(查询结束)

	private String building;		  	//建筑ID
	
	private String eqName;
	
	private String production;
	
	private String eqtype;
	
	private String eqCode;
	
	private String unittype;  //设备型号
	
	private Integer storey;	//所在楼层
	
	private String site;	//安装位置
	
	public String getEqCode() {
		return eqCode;
	}

	public void setEqCode(String eqCode) {
		this.eqCode = eqCode;
	}
	
	public String getEqtype() {
		return eqtype;
	}

	public void setEqtype(String eqtype) {
		this.eqtype = eqtype;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}

	public String getInstallStart() {
		return installStart;
	}

	public void setInstallStart(String installStart) {
		this.installStart = installStart;
	}

	public String getInstallEnd() {
		return installEnd;
	}

	public void setInstallEnd(String installEnd) {
		this.installEnd = installEnd;
	}
	
	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	

	public Integer getStorey() {
		return storey;
	}

	public void setStorey(Integer storey) {
		this.storey = storey;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

}

package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbBuilding entity. @author MyEclipse Persistence Tools
 */

public class DbBuilding  implements java.io.Serializable {


    // Fields    

     private Integer buildingId;
     private DbUsers dbUsersByInput;
     private DbBuildMater dbBuildMaterByProblem;	//改造前主要问题
     private DbHospInfo dbHospInfo;
     private DbBuildMater dbBuildMaterByQuakeproof;	//抗震烈度
     private DbBuildMater dbBuildMaterByWaterproof;  //防水等级
     private DbBuildMater dbBuildMaterByUsetype;	//楼宇用途
     private DbBaseComm dbBaseComm;	
     private DbUsers dbUsersByOper;
     private DbBuildMater dbBuildMaterByCostaccord;	//造价依据
     private String buildingCode;
     private String buildingName;
     private String hisName;
     private String buildingEnCode;
     private Double buildingAreas;
     private Double amount;
     private String completime;
     private Short storeyNumDown;
     private Short storeyNumUp;
     private Short mendNum;
     private String structure;
     private Double height;
     private String outWall;
     private String audits;
     private Double proCost;
     private String doorMater;
     private String windowMater;
     private String ceilingMater;
     private String wallMater;
     private String floorMater;
     private String place;
     private String ower;
     private Integer status;
     private Date inputtime;
     private Date opertime;
     private Set dbBuildingStoreies = new HashSet(0);
     private Set dbEquipLists = new HashSet(0);
     private Set dbBuildAreas = new HashSet(0);
     private Set dbBuildingRepairs = new HashSet(0);
     private Set dbBuildingStoreyHises = new HashSet(0);
     private Set dbBuildingPics = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbBuilding() {
    }

	/** minimal constructor */
    public DbBuilding(DbUsers dbUsersByOper, String buildingName, Double buildingAreas, Short storeyNumDown, Short storeyNumUp, Integer status, Date opertime) {
        this.dbUsersByOper = dbUsersByOper;
        this.buildingName = buildingName;
        this.buildingAreas = buildingAreas;
        this.storeyNumDown = storeyNumDown;
        this.storeyNumUp = storeyNumUp;
        this.status = status;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbBuilding(DbUsers dbUsersByInput, DbBuildMater dbBuildMaterByProblem, DbHospInfo dbHospInfo, DbBuildMater dbBuildMaterByQuakeproof, DbBuildMater dbBuildMaterByWaterproof, DbBuildMater dbBuildMaterByUsetype, DbBaseComm dbBaseComm, DbUsers dbUsersByOper, DbBuildMater dbBuildMaterByCostaccord, String buildingCode, String buildingName, String hisName, String buildingEnCode, Double buildingAreas, Double amount, String completime, Short storeyNumDown, Short storeyNumUp, Short mendNum, String structure, Double height, String outWall, String audits, Double proCost, String doorMater, String windowMater, String ceilingMater, String wallMater, String floorMater, String place, String ower, Integer status, Date inputtime, Date opertime, Set dbBuildingStoreies, Set dbEquipLists, Set dbBuildAreas, Set dbBuildingRepairs, Set dbBuildingStoreyHises, Set dbBuildingPics) {
        this.dbUsersByInput = dbUsersByInput;
        this.dbBuildMaterByProblem = dbBuildMaterByProblem;
        this.dbHospInfo = dbHospInfo;
        this.dbBuildMaterByQuakeproof = dbBuildMaterByQuakeproof;
        this.dbBuildMaterByWaterproof = dbBuildMaterByWaterproof;
        this.dbBuildMaterByUsetype = dbBuildMaterByUsetype;
        this.dbBaseComm = dbBaseComm;
        this.dbUsersByOper = dbUsersByOper;
        this.dbBuildMaterByCostaccord = dbBuildMaterByCostaccord;
        this.buildingCode = buildingCode;
        this.buildingName = buildingName;
        this.hisName = hisName;
        this.buildingEnCode = buildingEnCode;
        this.buildingAreas = buildingAreas;
        this.amount = amount;
        this.completime = completime;
        this.storeyNumDown = storeyNumDown;
        this.storeyNumUp = storeyNumUp;
        this.mendNum = mendNum;
        this.structure = structure;
        this.height = height;
        this.outWall = outWall;
        this.audits = audits;
        this.proCost = proCost;
        this.doorMater = doorMater;
        this.windowMater = windowMater;
        this.ceilingMater = ceilingMater;
        this.wallMater = wallMater;
        this.floorMater = floorMater;
        this.place = place;
        this.ower = ower;
        this.status = status;
        this.inputtime = inputtime;
        this.opertime = opertime;
        this.dbBuildingStoreies = dbBuildingStoreies;
        this.dbEquipLists = dbEquipLists;
        this.dbBuildAreas = dbBuildAreas;
        this.dbBuildingRepairs = dbBuildingRepairs;
        this.dbBuildingStoreyHises = dbBuildingStoreyHises;
        this.dbBuildingPics = dbBuildingPics;
    }

   
    // Property accessors

    public Integer getBuildingId() {
        return this.buildingId;
    }
    
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public DbUsers getDbUsersByInput() {
        return this.dbUsersByInput;
    }
    
    public void setDbUsersByInput(DbUsers dbUsersByInput) {
        this.dbUsersByInput = dbUsersByInput;
    }

    public DbBuildMater getDbBuildMaterByProblem() {
        return this.dbBuildMaterByProblem;
    }
    
    public void setDbBuildMaterByProblem(DbBuildMater dbBuildMaterByProblem) {
        this.dbBuildMaterByProblem = dbBuildMaterByProblem;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbBuildMater getDbBuildMaterByQuakeproof() {
        return this.dbBuildMaterByQuakeproof;
    }
    
    public void setDbBuildMaterByQuakeproof(DbBuildMater dbBuildMaterByQuakeproof) {
        this.dbBuildMaterByQuakeproof = dbBuildMaterByQuakeproof;
    }

    public DbBuildMater getDbBuildMaterByWaterproof() {
        return this.dbBuildMaterByWaterproof;
    }
    
    public void setDbBuildMaterByWaterproof(DbBuildMater dbBuildMaterByWaterproof) {
        this.dbBuildMaterByWaterproof = dbBuildMaterByWaterproof;
    }

    public DbBuildMater getDbBuildMaterByUsetype() {
        return this.dbBuildMaterByUsetype;
    }
    
    public void setDbBuildMaterByUsetype(DbBuildMater dbBuildMaterByUsetype) {
        this.dbBuildMaterByUsetype = dbBuildMaterByUsetype;
    }

    public DbBaseComm getDbBaseComm() {
        return this.dbBaseComm;
    }
    
    public void setDbBaseComm(DbBaseComm dbBaseComm) {
        this.dbBaseComm = dbBaseComm;
    }

    public DbUsers getDbUsersByOper() {
        return this.dbUsersByOper;
    }
    
    public void setDbUsersByOper(DbUsers dbUsersByOper) {
        this.dbUsersByOper = dbUsersByOper;
    }

    public DbBuildMater getDbBuildMaterByCostaccord() {
        return this.dbBuildMaterByCostaccord;
    }
    
    public void setDbBuildMaterByCostaccord(DbBuildMater dbBuildMaterByCostaccord) {
        this.dbBuildMaterByCostaccord = dbBuildMaterByCostaccord;
    }

    public String getBuildingCode() {
        return this.buildingCode;
    }
    
    public void setBuildingCode(String buildingCode) {
        if("".equals(buildingCode))
    		buildingCode=null;
    	this.buildingCode = buildingCode;
    }

    public String getBuildingName() {
        return this.buildingName;
    }
    
    public void setBuildingName(String buildingName) {
        if("".equals(buildingName))
    		buildingName=null;
    	 this.buildingName = buildingName;
    }

    public String getHisName() {
        return this.hisName;
    }
    
    public void setHisName(String hisName) {
        if("".equals(hisName))
    		hisName=null;
    	this.hisName = hisName;
    }

    public String getBuildingEnCode() {
        return this.buildingEnCode;
    }
    
    public void setBuildingEnCode(String buildingEnCode) {
        if("".equals(buildingEnCode))
    		buildingEnCode=null;
    	this.buildingEnCode = buildingEnCode;
    }

    public Double getBuildingAreas() {
        return this.buildingAreas;
    }
    
    public void setBuildingAreas(Double buildingAreas) {
        this.buildingAreas = buildingAreas;
    }

    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCompletime() {
        return this.completime;
    }
    
    public void setCompletime(String completime) {
        if("".equals(completime))
    		completime=null;
    	this.completime = completime;
    }

    public Short getStoreyNumDown() {
        return this.storeyNumDown;
    }
    
    public void setStoreyNumDown(Short storeyNumDown) {
        this.storeyNumDown = storeyNumDown;
    }

    public Short getStoreyNumUp() {
        return this.storeyNumUp;
    }
    
    public void setStoreyNumUp(Short storeyNumUp) {
        this.storeyNumUp = storeyNumUp;
    }

    public Short getMendNum() {
        return this.mendNum;
    }
    
    public void setMendNum(Short mendNum) {
        this.mendNum = mendNum;
    }

    public String getStructure() {
        return this.structure;
    }
    
    public void setStructure(String structure) {
        if("".equals(structure)||"-1".equals(structure))
    		structure=null;
    	this.structure = structure;
    }

    public Double getHeight() {
        return this.height;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }

    public String getOutWall() {
        return this.outWall;
    }
    
    public void setOutWall(String outWall) {
        if("".equals(outWall))
    		outWall=null;
    	this.outWall = outWall;
    }

    public String getAudits() {
        return this.audits;
    }
    
    public void setAudits(String audits) {
        if("".equals(audits))
    		audits=null;
    	this.audits = audits;
    }

    public Double getProCost() {
        return this.proCost;
    }
    
    public void setProCost(Double proCost) {
        this.proCost = proCost;
    }

    public String getDoorMater() {
        return this.doorMater;
    }
    
    public void setDoorMater(String doorMater) {
        if("".equals(doorMater))
    		doorMater=null;
    	this.doorMater = doorMater;
    }

    public String getWindowMater() {
        return this.windowMater;
    }
    
    public void setWindowMater(String windowMater) {
        if("".equals(windowMater))
    		windowMater=null;
    	this.windowMater = windowMater;
    }

    public String getCeilingMater() {
        return this.ceilingMater;
    }
    
    public void setCeilingMater(String ceilingMater) {
        if("".equals(ceilingMater))
    		ceilingMater=null;
    	this.ceilingMater = ceilingMater;
    }

    public String getWallMater() {
        return this.wallMater;
    }
    
    public void setWallMater(String wallMater) {
       if("".equals(wallMater))
    		wallMater=null;
    	this.wallMater = wallMater;
    }

    public String getFloorMater() {
        return this.floorMater;
    }
    
    public void setFloorMater(String floorMater) {
        if("".equals(floorMater))
    		floorMater=null;
    	this.floorMater = floorMater;
    }

    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
       if("".equals(place))
    		place=null;
    	this.place = place;
    }

    public String getOwer() {
        return this.ower;
    }
    
    public void setOwer(String ower) {
        if("".equals(ower))
    		ower=null;
    	this.ower = ower;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInputtime() {
        return this.inputtime;
    }
    
    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Set getDbBuildingStoreies() {
        return this.dbBuildingStoreies;
    }
    
    public void setDbBuildingStoreies(Set dbBuildingStoreies) {
        this.dbBuildingStoreies = dbBuildingStoreies;
    }

    public Set getDbEquipLists() {
        return this.dbEquipLists;
    }
    
    public void setDbEquipLists(Set dbEquipLists) {
        this.dbEquipLists = dbEquipLists;
    }

    public Set getDbBuildAreas() {
        return this.dbBuildAreas;
    }
    
    public void setDbBuildAreas(Set dbBuildAreas) {
        this.dbBuildAreas = dbBuildAreas;
    }

    public Set getDbBuildingRepairs() {
        return this.dbBuildingRepairs;
    }
    
    public void setDbBuildingRepairs(Set dbBuildingRepairs) {
        this.dbBuildingRepairs = dbBuildingRepairs;
    }

    public Set getDbBuildingStoreyHises() {
        return this.dbBuildingStoreyHises;
    }
    
    public void setDbBuildingStoreyHises(Set dbBuildingStoreyHises) {
        this.dbBuildingStoreyHises = dbBuildingStoreyHises;
    }

    public Set getDbBuildingPics() {
        return this.dbBuildingPics;
    }
    
    public void setDbBuildingPics(Set dbBuildingPics) {
        this.dbBuildingPics = dbBuildingPics;
    }
   








}
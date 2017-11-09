package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbBuildingPic entity. @author MyEclipse Persistence Tools
 */

public class DbBuildingPic  implements java.io.Serializable {


    // Fields    

     private Integer picId;
     private DbUsers dbUsersByInput;
     private DbHospInfo dbHospInfo;
     private DbBuilding dbBuilding;
     private DbUsers dbUsersByOper;
     private Short storey;
     private String picAddress;
     private String picName;
     private Short status;
     private Date inputtime;
     private Date opertime;
     private DbBaseComm picType;

    // Constructors

    /** default constructor */
    public DbBuildingPic() {
    }

	/** minimal constructor */
    public DbBuildingPic(DbUsers dbUsersByOper, Date opertime) {
        this.dbUsersByOper = dbUsersByOper;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbBuildingPic(DbUsers dbUsersByInput, DbHospInfo dbHospInfo, DbBuilding dbBuilding, DbUsers dbUsersByOper, Short storey, String picAddress, String picName, Short status, Date inputtime, Date opertime) {
        this.dbUsersByInput = dbUsersByInput;
        this.dbHospInfo = dbHospInfo;
        this.dbBuilding = dbBuilding;
        this.dbUsersByOper = dbUsersByOper;
        this.storey = storey;
        this.picAddress = picAddress;
        this.picName = picName;
        this.status = status;
        this.inputtime = inputtime;
        this.opertime = opertime;
    }

   
    // Property accessors

    public Integer getPicId() {
        return this.picId;
    }
    
    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public DbUsers getDbUsersByInput() {
        return this.dbUsersByInput;
    }
    
    public void setDbUsersByInput(DbUsers dbUsersByInput) {
        this.dbUsersByInput = dbUsersByInput;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbBuilding getDbBuilding() {
        return this.dbBuilding;
    }
    
    public void setDbBuilding(DbBuilding dbBuilding) {
        this.dbBuilding = dbBuilding;
    }

    public DbUsers getDbUsersByOper() {
        return this.dbUsersByOper;
    }
    
    public void setDbUsersByOper(DbUsers dbUsersByOper) {
        this.dbUsersByOper = dbUsersByOper;
    }

    public Short getStorey() {
        return this.storey;
    }
    
    public void setStorey(Short storey) {
        this.storey = storey;
    }

    public String getPicAddress() {
        return this.picAddress;
    }
    
    public void setPicAddress(String picAddress) {
        if("".equals(picAddress))
    		picAddress=null;
        this.picAddress = picAddress;
    }

    public String getPicName() {
        return this.picName;
    }
    
    public void setPicName(String picName) {
        if("".equals(picName))
    		picName=null;
        this.picName = picName;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
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

	public DbBaseComm getPicType() {
		return picType;
	}

	public void setPicType(DbBaseComm picType) {
		this.picType = picType;
	}
   








}
package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbBuildingStorey entity. @author MyEclipse Persistence Tools
 */

public class DbBuildingStorey  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbHospInfo dbHospInfo;
     private DbBaseComm dbBaseComm;
     private DbBuilding dbBuilding;
     private DbUsers dbUsers;
     private Integer storey;
     private Double acreage;
     private String remarks;
     private Short status;
     private Integer input;
     private Date inputtime;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbBuildingStorey() {
    }

	/** minimal constructor */
    public DbBuildingStorey(DbHospInfo dbHospInfo, DbBaseComm dbBaseComm, DbBuilding dbBuilding, DbUsers dbUsers, Integer storey, Short status, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbBaseComm = dbBaseComm;
        this.dbBuilding = dbBuilding;
        this.dbUsers = dbUsers;
        this.storey = storey;
        this.status = status;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbBuildingStorey(DbHospInfo dbHospInfo, DbBaseComm dbBaseComm, DbBuilding dbBuilding, DbUsers dbUsers, Integer storey, Double acreage, String remarks, Short status, Integer input, Date inputtime, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbBaseComm = dbBaseComm;
        this.dbBuilding = dbBuilding;
        this.dbUsers = dbUsers;
        this.storey = storey;
        this.acreage = acreage;
        this.remarks = remarks;
        this.status = status;
        this.input = input;
        this.inputtime = inputtime;
        this.opertime = opertime;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbBaseComm getDbBaseComm() {
        return this.dbBaseComm;
    }
    
    public void setDbBaseComm(DbBaseComm dbBaseComm) {
        this.dbBaseComm = dbBaseComm;
    }

    public DbBuilding getDbBuilding() {
        return this.dbBuilding;
    }
    
    public void setDbBuilding(DbBuilding dbBuilding) {
        this.dbBuilding = dbBuilding;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public Integer getStorey() {
        return this.storey;
    }
    
    public void setStorey(Integer storey) {
        this.storey = storey;
    }

    public Double getAcreage() {
        return this.acreage;
    }
    
    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        if("".equals(remarks))
    		remarks=null;
        this.remarks = remarks;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getInput() {
        return this.input;
    }
    
    public void setInput(Integer input) {
        this.input = input;
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
   








}
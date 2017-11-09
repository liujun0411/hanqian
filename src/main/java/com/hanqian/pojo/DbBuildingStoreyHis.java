package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbBuildingStoreyHis entity. @author MyEclipse Persistence Tools
 */

public class DbBuildingStoreyHis  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbHospInfo dbHospInfo;
     private DbBaseComm dbBaseComm;
     private DbBuilding dbBuilding;
     private Integer storey;
     private Double acreage;
     private String recordDate;
     private String remarks;
     private Short status;
     private Integer input;
     private Date inputtime;
     private Integer oper;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbBuildingStoreyHis() {
    }

	/** minimal constructor */
    public DbBuildingStoreyHis(DbHospInfo dbHospInfo, DbBaseComm dbBaseComm, DbBuilding dbBuilding, Integer storey, Short status, Integer oper, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbBaseComm = dbBaseComm;
        this.dbBuilding = dbBuilding;
        this.storey = storey;
        this.status = status;
        this.oper = oper;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbBuildingStoreyHis(DbHospInfo dbHospInfo, DbBaseComm dbBaseComm, DbBuilding dbBuilding, Integer storey, Double acreage, String recordDate, String remarks, Short status, Integer input, Date inputtime, Integer oper, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbBaseComm = dbBaseComm;
        this.dbBuilding = dbBuilding;
        this.storey = storey;
        this.acreage = acreage;
        this.recordDate = recordDate;
        this.remarks = remarks;
        this.status = status;
        this.input = input;
        this.inputtime = inputtime;
        this.oper = oper;
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

    public String getRecordDate() {
        return this.recordDate;
    }
    
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
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

    public Integer getOper() {
        return this.oper;
    }
    
    public void setOper(Integer oper) {
        this.oper = oper;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }
   








}
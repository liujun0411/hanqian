package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbBuildLog entity. @author MyEclipse Persistence Tools
 */

public class DbBuildLog  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbHospInfo dbHospInfo;
     private DbUsers dbUsers;
     private DbProjectModule dbProjectModule;
     private String dbTable;
     private String depict;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbBuildLog() {
    }

	/** minimal constructor */
    public DbBuildLog(DbHospInfo dbHospInfo, DbUsers dbUsers, String depict, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbUsers = dbUsers;
        this.depict = depict;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbBuildLog(DbHospInfo dbHospInfo, DbUsers dbUsers, DbProjectModule dbProjectModule, String dbTable, String depict, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbUsers = dbUsers;
        this.dbProjectModule = dbProjectModule;
        this.dbTable = dbTable;
        this.depict = depict;
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

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public DbProjectModule getDbProjectModule() {
        return this.dbProjectModule;
    }
    
    public void setDbProjectModule(DbProjectModule dbProjectModule) {
        this.dbProjectModule = dbProjectModule;
    }

    public String getDbTable() {
        return this.dbTable;
    }
    
    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }

    public String getDepict() {
        return this.depict;
    }
    
    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }
   








}
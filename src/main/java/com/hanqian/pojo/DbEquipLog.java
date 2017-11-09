package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbEquipLog entity. @author MyEclipse Persistence Tools
 */

public class DbEquipLog  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbUsers dbUsers;
     private DbProjectModule dbProjectModule;
     private Integer hospId;
     private String dbTable;
     private String depict;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbEquipLog() {
    }

	/** minimal constructor */
    public DbEquipLog(DbUsers dbUsers, Integer hospId, String depict, Date opertime) {
        this.dbUsers = dbUsers;
        this.hospId = hospId;
        this.depict = depict;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbEquipLog(DbUsers dbUsers, DbProjectModule dbProjectModule, Integer hospId, String dbTable, String depict, Date opertime) {
        this.dbUsers = dbUsers;
        this.dbProjectModule = dbProjectModule;
        this.hospId = hospId;
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

    public Integer getHospId() {
        return this.hospId;
    }
    
    public void setHospId(Integer hospId) {
        this.hospId = hospId;
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
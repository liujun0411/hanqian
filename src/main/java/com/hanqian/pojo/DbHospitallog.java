package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbHospitallog entity. @author MyEclipse Persistence Tools
 */

public class DbHospitallog  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbProjectModule dbProjectModule;
     private DbUsers dbUsers;
     private String hospId;
     private String dbTable;
     private String depict;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbHospitallog() {
    }

	/** minimal constructor */
    public DbHospitallog(DbUsers dbUsers, String hospId, String depict, Date opertime) {
        this.dbUsers = dbUsers;
        this.hospId = hospId;
        this.depict = depict;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbHospitallog(DbProjectModule dbProjectModule, DbUsers dbUsers, String hospId, String dbTable, String depict, Date opertime) {
        this.dbProjectModule = dbProjectModule;
        this.dbUsers = dbUsers;
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

    public DbProjectModule getDbProjectModule() {
        return this.dbProjectModule;
    }
    
    public void setDbProjectModule(DbProjectModule dbProjectModule) {
        this.dbProjectModule = dbProjectModule;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public String getHospId() {
        return this.hospId;
    }
    
    public void setHospId(String hospId) {
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
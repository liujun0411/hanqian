package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbProgramaLog entity. @author MyEclipse Persistence Tools
 */

public class DbProgramaLog  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbHospInfo dbHospInfo;
     private DbProjectModule dbProjectModule;
     private String dbTable;
     private String depict;
     private Integer oper;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbProgramaLog() {
    }

	/** minimal constructor */
    public DbProgramaLog(DbHospInfo dbHospInfo, String depict, Integer oper, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.depict = depict;
        this.oper = oper;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbProgramaLog(DbHospInfo dbHospInfo, DbProjectModule dbProjectModule, String dbTable, String depict, Integer oper, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbProjectModule = dbProjectModule;
        this.dbTable = dbTable;
        this.depict = depict;
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
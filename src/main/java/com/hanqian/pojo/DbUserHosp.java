package com.hanqian.pojo;



/**
 * DbUserHosp entity. @author MyEclipse Persistence Tools
 */

public class DbUserHosp  implements java.io.Serializable {


    // Fields    

     private DbUserHospId id;
     private DbUsers dbUsers;
     private DbDept dbDept;
     private DbHospInfo dbHospInfo;


    // Constructors

    /** default constructor */
    public DbUserHosp() {
    }

	/** minimal constructor */
    public DbUserHosp(DbUserHospId id) {
        this.id = id;
    }
    
    /** full constructor */
    public DbUserHosp(DbUserHospId id, DbUsers dbUsers, DbDept dbDept, DbHospInfo dbHospInfo) {
        this.id = id;
        this.dbUsers = dbUsers;
        this.dbDept = dbDept;
        this.dbHospInfo = dbHospInfo;
    }

   
    // Property accessors

    public DbUserHospId getId() {
        return this.id;
    }
    
    public void setId(DbUserHospId id) {
        this.id = id;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public DbDept getDbDept() {
        return this.dbDept;
    }
    
    public void setDbDept(DbDept dbDept) {
        this.dbDept = dbDept;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }
   








}
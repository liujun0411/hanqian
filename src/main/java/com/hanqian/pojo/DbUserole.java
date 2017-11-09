package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbUserole entity. @author MyEclipse Persistence Tools
 */

public class DbUserole  implements java.io.Serializable {


    // Fields    

     private DbUseroleId id;
     private DbUsers dbUsers;
     private DbRoles dbRoles;
     private Integer oper;
     private Date opertime;
     private Integer status;


    // Constructors

    /** default constructor */
    public DbUserole() {
    }

	/** minimal constructor */
    public DbUserole(DbUseroleId id, DbUsers dbUsers, DbRoles dbRoles) {
        this.id = id;
        this.dbUsers = dbUsers;
        this.dbRoles = dbRoles;
    }
    
    /** full constructor */
    public DbUserole(DbUseroleId id, DbUsers dbUsers, DbRoles dbRoles, Integer oper, Date opertime, Integer status) {
        this.id = id;
        this.dbUsers = dbUsers;
        this.dbRoles = dbRoles;
        this.oper = oper;
        this.opertime = opertime;
        this.status = status;
    }

   
    // Property accessors

    public DbUseroleId getId() {
        return this.id;
    }
    
    public void setId(DbUseroleId id) {
        this.id = id;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public DbRoles getDbRoles() {
        return this.dbRoles;
    }
    
    public void setDbRoles(DbRoles dbRoles) {
        this.dbRoles = dbRoles;
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

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}
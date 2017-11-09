package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbRoleMenu entity. @author MyEclipse Persistence Tools
 */

public class DbRoleMenu  implements java.io.Serializable {


    // Fields    

     private DbRoleMenuId id;
     private DbMenus dbMenus;
     private DbRoles dbRoles;
     private Integer oper;
     private Date opertime;
     private Integer status;


    // Constructors

    /** default constructor */
    public DbRoleMenu() {
    }

	/** minimal constructor */
    public DbRoleMenu(DbRoleMenuId id, DbMenus dbMenus, DbRoles dbRoles) {
        this.id = id;
        this.dbMenus = dbMenus;
        this.dbRoles = dbRoles;
    }
    
    /** full constructor */
    public DbRoleMenu(DbRoleMenuId id, DbMenus dbMenus, DbRoles dbRoles, Integer oper, Date opertime, Integer status) {
        this.id = id;
        this.dbMenus = dbMenus;
        this.dbRoles = dbRoles;
        this.oper = oper;
        this.opertime = opertime;
        this.status = status;
    }

   
    // Property accessors

    public DbRoleMenuId getId() {
        return this.id;
    }
    
    public void setId(DbRoleMenuId id) {
        this.id = id;
    }

    public DbMenus getDbMenus() {
        return this.dbMenus;
    }
    
    public void setDbMenus(DbMenus dbMenus) {
        this.dbMenus = dbMenus;
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
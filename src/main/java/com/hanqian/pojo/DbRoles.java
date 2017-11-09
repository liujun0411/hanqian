package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbRoles entity. @author MyEclipse Persistence Tools
 */

public class DbRoles  implements java.io.Serializable {


    // Fields    

     private Integer roleId;
     private String name;
     private String depict;
     private Integer roleLevel;
     private Integer oper;
     private Date opertime;
     private Integer updater;
     private Date updatertime;
     private Integer status;
     private Set dbUseroles = new HashSet(0);
     private Set dbRoleMenus = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbRoles() {
    }

    
    /** full constructor */
    public DbRoles(String name, String depict, Integer roleLevel, Integer oper, Date opertime, Integer updater, Date updatertime, Integer status, Set dbUseroles, Set dbRoleMenus) {
        this.name = name;
        this.depict = depict;
        this.roleLevel = roleLevel;
        this.oper = oper;
        this.opertime = opertime;
        this.updater = updater;
        this.updatertime = updatertime;
        this.status = status;
        this.dbUseroles = dbUseroles;
        this.dbRoleMenus = dbRoleMenus;
    }

   
    // Property accessors

    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDepict() {
        return this.depict;
    }
    
    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Integer getRoleLevel() {
        return this.roleLevel;
    }
    
    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
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

    public Integer getUpdater() {
        return this.updater;
    }
    
    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getUpdatertime() {
        return this.updatertime;
    }
    
    public void setUpdatertime(Date updatertime) {
        this.updatertime = updatertime;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set getDbUseroles() {
        return this.dbUseroles;
    }
    
    public void setDbUseroles(Set dbUseroles) {
        this.dbUseroles = dbUseroles;
    }

    public Set getDbRoleMenus() {
        return this.dbRoleMenus;
    }
    
    public void setDbRoleMenus(Set dbRoleMenus) {
        this.dbRoleMenus = dbRoleMenus;
    }
   








}
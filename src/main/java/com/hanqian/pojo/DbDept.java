package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbDept entity. @author MyEclipse Persistence Tools
 */

public class DbDept  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbUsers dbUsersByUpdater;
     private DbUsers dbUsersByOper;
     private String name;
     private String principal;
     private Date opertime;
     private Date updatetime;
     private Integer status;
     private Set dbUserHosps = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbDept() {
    }

    
    /** full constructor */
    public DbDept(DbUsers dbUsersByUpdater, DbUsers dbUsersByOper, String name, String principal, Date opertime, Date updatetime, Integer status, Set dbUserHosps) {
        this.dbUsersByUpdater = dbUsersByUpdater;
        this.dbUsersByOper = dbUsersByOper;
        this.name = name;
        this.principal = principal;
        this.opertime = opertime;
        this.updatetime = updatetime;
        this.status = status;
        this.dbUserHosps = dbUserHosps;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbUsers getDbUsersByUpdater() {
        return this.dbUsersByUpdater;
    }
    
    public void setDbUsersByUpdater(DbUsers dbUsersByUpdater) {
        this.dbUsersByUpdater = dbUsersByUpdater;
    }

    public DbUsers getDbUsersByOper() {
        return this.dbUsersByOper;
    }
    
    public void setDbUsersByOper(DbUsers dbUsersByOper) {
        this.dbUsersByOper = dbUsersByOper;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPrincipal() {
        return this.principal;
    }
    
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }
    
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set getDbUserHosps() {
        return this.dbUserHosps;
    }
    
    public void setDbUserHosps(Set dbUserHosps) {
        this.dbUserHosps = dbUserHosps;
    }
   








}
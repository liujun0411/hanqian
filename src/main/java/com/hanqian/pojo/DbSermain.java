package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbSermain entity. @author MyEclipse Persistence Tools
 */

public class DbSermain  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbService dbService;
     private String team;
     private String desc;
     private Integer input;
     private Date inputtime;
     private Integer oper;
     private Date opertime;
     private Set dbSermainDetails = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbSermain() {
    }

    
    /** full constructor */
    public DbSermain(DbService dbService, String team, String desc, Integer input, Date inputtime, Integer oper, Date opertime, Set dbSermainDetails) {
        this.dbService = dbService;
        this.team = team;
        this.desc = desc;
        this.input = input;
        this.inputtime = inputtime;
        this.oper = oper;
        this.opertime = opertime;
        this.dbSermainDetails = dbSermainDetails;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbService getDbService() {
        return this.dbService;
    }
    
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public String getTeam() {
        return this.team;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }

    public String getDesc() {
        return this.desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
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

    public Set getDbSermainDetails() {
        return this.dbSermainDetails;
    }
    
    public void setDbSermainDetails(Set dbSermainDetails) {
        this.dbSermainDetails = dbSermainDetails;
    }
   








}
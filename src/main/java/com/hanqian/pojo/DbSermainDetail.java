package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbSermainDetail entity. @author MyEclipse Persistence Tools
 */

public class DbSermainDetail  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbSermain dbSermain;
     private String memName;
     private String memMobtele;
     private String memTele;
     private Integer input;
     private Date inputtime;
     private Integer oper;
     private Date opertime;
     private Set dbAlarmCueUsers = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbSermainDetail() {
    }

    
    /** full constructor */
    public DbSermainDetail(DbSermain dbSermain, String memName, String memMobtele, String memTele, Integer input, Date inputtime, Integer oper, Date opertime, Set dbAlarmCueUsers) {
        this.dbSermain = dbSermain;
        this.memName = memName;
        this.memMobtele = memMobtele;
        this.memTele = memTele;
        this.input = input;
        this.inputtime = inputtime;
        this.oper = oper;
        this.opertime = opertime;
        this.dbAlarmCueUsers = dbAlarmCueUsers;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbSermain getDbSermain() {
        return this.dbSermain;
    }
    
    public void setDbSermain(DbSermain dbSermain) {
        this.dbSermain = dbSermain;
    }

    public String getMemName() {
        return this.memName;
    }
    
    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemMobtele() {
        return this.memMobtele;
    }
    
    public void setMemMobtele(String memMobtele) {
        this.memMobtele = memMobtele;
    }

    public String getMemTele() {
        return this.memTele;
    }
    
    public void setMemTele(String memTele) {
        this.memTele = memTele;
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

    public Set getDbAlarmCueUsers() {
        return this.dbAlarmCueUsers;
    }
    
    public void setDbAlarmCueUsers(Set dbAlarmCueUsers) {
        this.dbAlarmCueUsers = dbAlarmCueUsers;
    }
   








}
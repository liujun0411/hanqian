package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbServiceType entity. @author MyEclipse Persistence Tools
 */

public class DbServiceType  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private String typename;
     private String desc;
     private Integer input;
     private Date inputtime;
     private Integer oper;
     private Date opertime;
     private Set dbServices = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbServiceType() {
    }

    
    /** full constructor */
    public DbServiceType(String typename, String desc, Integer input, Date inputtime, Integer oper, Date opertime, Set dbServices) {
        this.typename = typename;
        this.desc = desc;
        this.input = input;
        this.inputtime = inputtime;
        this.oper = oper;
        this.opertime = opertime;
        this.dbServices = dbServices;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTypename() {
        return this.typename;
    }
    
    public void setTypename(String typename) {
        this.typename = typename;
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

    public Set getDbServices() {
        return this.dbServices;
    }
    
    public void setDbServices(Set dbServices) {
        this.dbServices = dbServices;
    }
   








}
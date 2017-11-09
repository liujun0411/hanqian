package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbFeaturelog entity. @author MyEclipse Persistence Tools
 */

public class DbFeaturelog  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private Integer diagram;
     private String dbTable;
     private String depict;
     private Integer oper;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbFeaturelog() {
    }

	/** minimal constructor */
    public DbFeaturelog(String depict, Integer oper, Date opertime) {
        this.depict = depict;
        this.oper = oper;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbFeaturelog(Integer diagram, String dbTable, String depict, Integer oper, Date opertime) {
        this.diagram = diagram;
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

    public Integer getDiagram() {
        return this.diagram;
    }
    
    public void setDiagram(Integer diagram) {
        this.diagram = diagram;
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
package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbMonitorlog entity. @author MyEclipse Persistence Tools
 */

public class DbMonitorlog  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbEquipList dbEquipList;
     private Integer diagram;
     private String dbTable;
     private String depict;
     private Integer oper;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbMonitorlog() {
    }

	/** minimal constructor */
    public DbMonitorlog(Integer diagram, String depict, Integer oper, Date opertime) {
        this.diagram = diagram;
        this.depict = depict;
        this.oper = oper;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbMonitorlog(DbEquipList dbEquipList, Integer diagram, String dbTable, String depict, Integer oper, Date opertime) {
        this.dbEquipList = dbEquipList;
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

    public DbEquipList getDbEquipList() {
        return this.dbEquipList;
    }
    
    public void setDbEquipList(DbEquipList dbEquipList) {
        this.dbEquipList = dbEquipList;
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
package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbBuildArea entity. @author MyEclipse Persistence Tools
 */

public class DbBuildArea  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbBuilding dbBuilding;
     private Double area;
     private Date changeDates;


    // Constructors

    /** default constructor */
    public DbBuildArea() {
    }

    
    /** full constructor */
    public DbBuildArea(DbBuilding dbBuilding, Double area, Date changeDates) {
        this.dbBuilding = dbBuilding;
        this.area = area;
        this.changeDates = changeDates;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbBuilding getDbBuilding() {
        return this.dbBuilding;
    }
    
    public void setDbBuilding(DbBuilding dbBuilding) {
        this.dbBuilding = dbBuilding;
    }

    public Double getArea() {
        return this.area;
    }
    
    public void setArea(Double area) {
        this.area = area;
    }

    public Date getChangeDates() {
        return this.changeDates;
    }
    
    public void setChangeDates(Date changeDates) {
        this.changeDates = changeDates;
    }
   








}
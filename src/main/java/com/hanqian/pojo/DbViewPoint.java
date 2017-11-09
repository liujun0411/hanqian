package com.hanqian.pojo;

import java.lang.Integer;


/**
 * DbViewPoint entity. @author MyEclipse Persistence Tools
 */

public class DbViewPoint  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbEquipType dbEquipTypeBySysType;
     private DbEquipType dbEquipTypeByEquipTypeId;
     private String title;
     private String subTitle;
     private String depict;
     private String controlPoint;
     private String unit;
     private Boolean VStatus;
     private Boolean sumflag;
     private String divStyle;
     private Boolean showFlag;


    // Constructors

    /** default constructor */
    public DbViewPoint() {
    }

	/** minimal constructor */
    public DbViewPoint(DbEquipType dbEquipTypeByEquipTypeId, String controlPoint) {
        this.dbEquipTypeByEquipTypeId = dbEquipTypeByEquipTypeId;
        this.controlPoint = controlPoint;
    }
    
    /** full constructor */
    public DbViewPoint(DbEquipType dbEquipTypeBySysType, DbEquipType dbEquipTypeByEquipTypeId, String title, String subTitle, String depict, String controlPoint, String unit, Boolean VStatus, Boolean sumflag, String divStyle, Boolean showFlag) {
        this.dbEquipTypeBySysType = dbEquipTypeBySysType;
        this.dbEquipTypeByEquipTypeId = dbEquipTypeByEquipTypeId;
        this.title = title;
        this.subTitle = subTitle;
        this.depict = depict;
        this.controlPoint = controlPoint;
        this.unit = unit;
        this.VStatus = VStatus;
        this.sumflag = sumflag;
        this.divStyle = divStyle;
        this.showFlag = showFlag;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbEquipType getDbEquipTypeBySysType() {
        return this.dbEquipTypeBySysType;
    }
    
    public void setDbEquipTypeBySysType(DbEquipType dbEquipTypeBySysType) {
        this.dbEquipTypeBySysType = dbEquipTypeBySysType;
    }

    public DbEquipType getDbEquipTypeByEquipTypeId() {
        return this.dbEquipTypeByEquipTypeId;
    }
    
    public void setDbEquipTypeByEquipTypeId(DbEquipType dbEquipTypeByEquipTypeId) {
        this.dbEquipTypeByEquipTypeId = dbEquipTypeByEquipTypeId;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }
    
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDepict() {
        return this.depict;
    }
    
    public void setDepict(String depict) {
        this.depict = depict;
    }

    public String getControlPoint() {
        return this.controlPoint;
    }
    
    public void setControlPoint(String controlPoint) {
        this.controlPoint = controlPoint;
    }

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getVStatus() {
        return this.VStatus;
    }
    
    public void setVStatus(Boolean VStatus) {
        this.VStatus = VStatus;
    }

    public Boolean getSumflag() {
        return this.sumflag;
    }
    
    public void setSumflag(Boolean sumflag) {
        this.sumflag = sumflag;
    }

    public String getDivStyle() {
        return this.divStyle;
    }
    
    public void setDivStyle(String divStyle) {
        this.divStyle = divStyle;
    }

    public Boolean getShowFlag() {
        return this.showFlag;
    }
    
    public void setShowFlag(Boolean showFlag) {
        this.showFlag = showFlag;
    }
   








}
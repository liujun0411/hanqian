package com.hanqian.pojo;

import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;


/**
 * DicEqParameter entity. @author MyEclipse Persistence Tools
 */

public class DicEqParameter  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbEquipType dbEquipType;
     private String paramName;
     private String paramUnit;
     private Short status;
     private Set dbEqParams = new HashSet(0);


    // Constructors

    /** default constructor */
    public DicEqParameter() {
    }

    
    /** full constructor */
    public DicEqParameter(DbEquipType dbEquipType, String paramName, String paramUnit, Short status, Set dbEqParams) {
        this.dbEquipType = dbEquipType;
        this.paramName = paramName;
        this.paramUnit = paramUnit;
        this.status = status;
        this.dbEqParams = dbEqParams;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbEquipType getDbEquipType() {
        return this.dbEquipType;
    }
    
    public void setDbEquipType(DbEquipType dbEquipType) {
        this.dbEquipType = dbEquipType;
    }

    public String getParamName() {
        return this.paramName;
    }
    
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamUnit() {
        return this.paramUnit;
    }
    
    public void setParamUnit(String paramUnit) {
        this.paramUnit = paramUnit;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    public Set getDbEqParams() {
        return this.dbEqParams;
    }
    
    public void setDbEqParams(Set dbEqParams) {
        this.dbEqParams = dbEqParams;
    }
   








}
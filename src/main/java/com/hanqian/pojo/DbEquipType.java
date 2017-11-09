package com.hanqian.pojo;

import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;


/**
 * DbEquipType entity. @author MyEclipse Persistence Tools
 */

public class DbEquipType  implements java.io.Serializable {


    // Fields    

     private Integer equipTypeId;
     private DbEquipType dbEquipType;
     private String typeName;
     private String depict;
     private Boolean groupStatus;
     private Boolean controlSource;
     private Short powerType;
     private String enName;
     private String equipTypeCode;
     private Set dicEqParameters = new HashSet(0);
     private Set dbEquipGroups = new HashSet(0);
     private Set dbEquipLists = new HashSet(0);
     private Set dbViewPointsForEquipTypeId = new HashSet(0);
     private Set dbPointEquips = new HashSet(0);
     private Set dbViewPointsForSysType = new HashSet(0);
     private Set dbEquipTypes = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbEquipType() {
    }

	/** minimal constructor */
    public DbEquipType(String typeName, Boolean groupStatus) {
        this.typeName = typeName;
        this.groupStatus = groupStatus;
    }
    
    /** full constructor */
    public DbEquipType(DbEquipType dbEquipType, String typeName, String depict, Boolean groupStatus, Boolean controlSource, Short powerType, String enName, String equipTypeCode, Set dicEqParameters, Set dbEquipGroups, Set dbEquipLists, Set dbViewPointsForEquipTypeId, Set dbPointEquips, Set dbViewPointsForSysType, Set dbEquipTypes) {
        this.dbEquipType = dbEquipType;
        this.typeName = typeName;
        this.depict = depict;
        this.groupStatus = groupStatus;
        this.controlSource = controlSource;
        this.powerType = powerType;
        this.enName = enName;
        this.equipTypeCode = equipTypeCode;
        this.dicEqParameters = dicEqParameters;
        this.dbEquipGroups = dbEquipGroups;
        this.dbEquipLists = dbEquipLists;
        this.dbViewPointsForEquipTypeId = dbViewPointsForEquipTypeId;
        this.dbPointEquips = dbPointEquips;
        this.dbViewPointsForSysType = dbViewPointsForSysType;
        this.dbEquipTypes = dbEquipTypes;
    }

   
    // Property accessors

    public Integer getEquipTypeId() {
        return this.equipTypeId;
    }
    
    public void setEquipTypeId(Integer equipTypeId) {
        this.equipTypeId = equipTypeId;
    }

    public DbEquipType getDbEquipType() {
        return this.dbEquipType;
    }
    
    public void setDbEquipType(DbEquipType dbEquipType) {
        this.dbEquipType = dbEquipType;
    }

    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        if("".equals(typeName))
    		typeName=null;
        this.typeName = typeName;
    }

    public String getDepict() {
        return this.depict;
    }
    
    public void setDepict(String depict) {
        if("".equals(depict))
    		depict=null;
        this.depict = depict;
    }

    public Boolean getGroupStatus() {
        return this.groupStatus;
    }
    
    public void setGroupStatus(Boolean groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Boolean getControlSource() {
        return this.controlSource;
    }
    
    public void setControlSource(Boolean controlSource) {
        this.controlSource = controlSource;
    }

    public Short getPowerType() {
        return this.powerType;
    }
    
    public void setPowerType(Short powerType) {
        this.powerType = powerType;
    }

    public String getEnName() {
        return this.enName;
    }
    
    public void setEnName(String enName) {
        if("".equals(enName))
    		enName=null;
        this.enName = enName;
    }

    public String getEquipTypeCode() {
        return this.equipTypeCode;
    }
    
    public void setEquipTypeCode(String equipTypeCode) {
        if("".equals(equipTypeCode))
    		equipTypeCode=null;
        this.equipTypeCode = equipTypeCode;
    }

    public Set getDicEqParameters() {
        return this.dicEqParameters;
    }
    
    public void setDicEqParameters(Set dicEqParameters) {
        this.dicEqParameters = dicEqParameters;
    }

    public Set getDbEquipGroups() {
        return this.dbEquipGroups;
    }
    
    public void setDbEquipGroups(Set dbEquipGroups) {
        this.dbEquipGroups = dbEquipGroups;
    }

    public Set getDbEquipLists() {
        return this.dbEquipLists;
    }
    
    public void setDbEquipLists(Set dbEquipLists) {
        this.dbEquipLists = dbEquipLists;
    }

    public Set getDbViewPointsForEquipTypeId() {
        return this.dbViewPointsForEquipTypeId;
    }
    
    public void setDbViewPointsForEquipTypeId(Set dbViewPointsForEquipTypeId) {
        this.dbViewPointsForEquipTypeId = dbViewPointsForEquipTypeId;
    }

    public Set getDbPointEquips() {
        return this.dbPointEquips;
    }
    
    public void setDbPointEquips(Set dbPointEquips) {
        this.dbPointEquips = dbPointEquips;
    }

    public Set getDbViewPointsForSysType() {
        return this.dbViewPointsForSysType;
    }
    
    public void setDbViewPointsForSysType(Set dbViewPointsForSysType) {
        this.dbViewPointsForSysType = dbViewPointsForSysType;
    }

    public Set getDbEquipTypes() {
        return this.dbEquipTypes;
    }
    
    public void setDbEquipTypes(Set dbEquipTypes) {
        this.dbEquipTypes = dbEquipTypes;
    }
   








}
package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbEquipPic entity. @author MyEclipse Persistence Tools
 */

public class DbEquipPic  implements java.io.Serializable {


    // Fields    

     private Integer equipPicId;
     private DbEquipPicType dbEquipPicType;
     private DbUsers dbUsers;
     private String equipPicName;
     private String picAddress;
     private String picCode;
     private Integer buildId;
     private Integer storey;
     private String remarks;
     private Short status;
     private Integer hospitalid;
     private Date opertime;
     private Set dbEquipPicRels = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbEquipPic() {
    }

	/** minimal constructor */
    public DbEquipPic(Short status) {
        this.status = status;
    }
    
    /** full constructor */
    public DbEquipPic(DbEquipPicType dbEquipPicType, DbUsers dbUsers, String equipPicName, String picAddress, String picCode, Integer buildId, Integer storey, String remarks, Short status, Integer hospitalid, Date opertime, Set dbEquipPicRels) {
        this.dbEquipPicType = dbEquipPicType;
        this.dbUsers = dbUsers;
        this.equipPicName = equipPicName;
        this.picAddress = picAddress;
        this.picCode = picCode;
        this.buildId = buildId;
        this.storey = storey;
        this.remarks = remarks;
        this.status = status;
        this.hospitalid = hospitalid;
        this.opertime = opertime;
        this.dbEquipPicRels = dbEquipPicRels;
    }

   
    // Property accessors

    public Integer getEquipPicId() {
        return this.equipPicId;
    }
    
    public void setEquipPicId(Integer equipPicId) {
        this.equipPicId = equipPicId;
    }

    public DbEquipPicType getDbEquipPicType() {
        return this.dbEquipPicType;
    }
    
    public void setDbEquipPicType(DbEquipPicType dbEquipPicType) {
        this.dbEquipPicType = dbEquipPicType;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public String getEquipPicName() {
        return this.equipPicName;
    }
    
    public void setEquipPicName(String equipPicName) {
        if("".equals(equipPicName))
    		equipPicName=null;
        this.equipPicName = equipPicName;
    }

    public String getPicAddress() {
        return this.picAddress;
    }
    
    public void setPicAddress(String picAddress) {
        if("".equals(picAddress))
    		picAddress=null;
        this.picAddress = picAddress;
    }

    public String getPicCode() {
        return this.picCode;
    }
    
    public void setPicCode(String picCode) {
        if("".equals(picCode))
    		picCode=null;
        this.picCode = picCode;
    }

    public Integer getBuildId() {
        return this.buildId;
    }
    
    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getStorey() {
        return this.storey;
    }
    
    public void setStorey(Integer storey) {
        this.storey = storey;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        if("".equals(remarks))
    		remarks=null;
        this.remarks = remarks;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getHospitalid() {
        return this.hospitalid;
    }
    
    public void setHospitalid(Integer hospitalid) {
        this.hospitalid = hospitalid;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Set getDbEquipPicRels() {
        return this.dbEquipPicRels;
    }
    
    public void setDbEquipPicRels(Set dbEquipPicRels) {
        this.dbEquipPicRels = dbEquipPicRels;
    }
   








}
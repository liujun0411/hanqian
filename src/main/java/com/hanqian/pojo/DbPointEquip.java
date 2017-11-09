package com.hanqian.pojo;



/**
 * DbPointEquip entity. @author MyEclipse Persistence Tools
 */

public class DbPointEquip  implements java.io.Serializable {


    // Fields    

     private DbPointEquipId id;
     private DbHospInfo dbHospInfo;
     private DbPoint dbPoint;
     private DbEquipList dbEquipList;
     private DbEquipType dbEquipType;


    // Constructors

    /** default constructor */
    public DbPointEquip() {
    }

	/** minimal constructor */
    public DbPointEquip(DbPointEquipId id, DbHospInfo dbHospInfo, DbPoint dbPoint, DbEquipList dbEquipList) {
        this.id = id;
        this.dbHospInfo = dbHospInfo;
        this.dbPoint = dbPoint;
        this.dbEquipList = dbEquipList;
    }
    
    /** full constructor */
    public DbPointEquip(DbPointEquipId id, DbHospInfo dbHospInfo, DbPoint dbPoint, DbEquipList dbEquipList, DbEquipType dbEquipType) {
        this.id = id;
        this.dbHospInfo = dbHospInfo;
        this.dbPoint = dbPoint;
        this.dbEquipList = dbEquipList;
        this.dbEquipType = dbEquipType;
    }

   
    // Property accessors

    public DbPointEquipId getId() {
        return this.id;
    }
    
    public void setId(DbPointEquipId id) {
        this.id = id;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbPoint getDbPoint() {
        return this.dbPoint;
    }
    
    public void setDbPoint(DbPoint dbPoint) {
        this.dbPoint = dbPoint;
    }

    public DbEquipList getDbEquipList() {
        return this.dbEquipList;
    }
    
    public void setDbEquipList(DbEquipList dbEquipList) {
        this.dbEquipList = dbEquipList;
    }

    public DbEquipType getDbEquipType() {
        return this.dbEquipType;
    }
    
    public void setDbEquipType(DbEquipType dbEquipType) {
        this.dbEquipType = dbEquipType;
    }
   








}
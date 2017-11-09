package com.hanqian.pojo;

import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;


/**
 * DbBuildMater entity. @author MyEclipse Persistence Tools
 */

public class DbBuildMater  implements java.io.Serializable {


    // Fields    

     private Integer materId;
     private DbBuildMater dbBuildMater;
     private String materName;
     private String depict;
     private Set dbBuildingsForWaterproof = new HashSet(0);
     private Set dbBuildingRepairsForCostAccord = new HashSet(0);
     private Set dbBuildMaters = new HashSet(0);
     private Set dbBuildingRepairsForWaterProof = new HashSet(0);
     private Set dbBuildingsForUsetype = new HashSet(0);
     private Set dbBuildingsForProblem = new HashSet(0);
     private Set dbBuildingsForCostaccord = new HashSet(0);
     private Set dbBuildingsForQuakeproof = new HashSet(0);
     private Set dbBuildingRepairsForQuakeProof = new HashSet(0);
     private Set dbBuildingRepairsForProblem = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbBuildMater() {
    }

	/** minimal constructor */
    public DbBuildMater(String materName) {
        this.materName = materName;
    }
    
    /** full constructor */
    public DbBuildMater(DbBuildMater dbBuildMater, String materName, String depict, Set dbBuildingsForWaterproof, Set dbBuildingRepairsForCostAccord, Set dbBuildMaters, Set dbBuildingRepairsForWaterProof, Set dbBuildingsForUsetype, Set dbBuildingsForProblem, Set dbBuildingsForCostaccord, Set dbBuildingsForQuakeproof, Set dbBuildingRepairsForQuakeProof, Set dbBuildingRepairsForProblem) {
        this.dbBuildMater = dbBuildMater;
        this.materName = materName;
        this.depict = depict;
        this.dbBuildingsForWaterproof = dbBuildingsForWaterproof;
        this.dbBuildingRepairsForCostAccord = dbBuildingRepairsForCostAccord;
        this.dbBuildMaters = dbBuildMaters;
        this.dbBuildingRepairsForWaterProof = dbBuildingRepairsForWaterProof;
        this.dbBuildingsForUsetype = dbBuildingsForUsetype;
        this.dbBuildingsForProblem = dbBuildingsForProblem;
        this.dbBuildingsForCostaccord = dbBuildingsForCostaccord;
        this.dbBuildingsForQuakeproof = dbBuildingsForQuakeproof;
        this.dbBuildingRepairsForQuakeProof = dbBuildingRepairsForQuakeProof;
        this.dbBuildingRepairsForProblem = dbBuildingRepairsForProblem;
    }

   
    // Property accessors

    public Integer getMaterId() {
        return this.materId;
    }
    
    public void setMaterId(Integer materId) {
        this.materId = materId;
    }

    public DbBuildMater getDbBuildMater() {
        return this.dbBuildMater;
    }
    
    public void setDbBuildMater(DbBuildMater dbBuildMater) {
        this.dbBuildMater = dbBuildMater;
    }

    public String getMaterName() {
        return this.materName;
    }
    
    public void setMaterName(String materName) {
        if("".equals(materName))
    		materName=null;
        this.materName = materName;
    }

    public String getDepict() {
        return this.depict;
    }
    
    public void setDepict(String depict) {
        if("".equals(depict))
    		depict=null;
        this.depict = depict;
    }

    public Set getDbBuildingsForWaterproof() {
        return this.dbBuildingsForWaterproof;
    }
    
    public void setDbBuildingsForWaterproof(Set dbBuildingsForWaterproof) {
        this.dbBuildingsForWaterproof = dbBuildingsForWaterproof;
    }

    public Set getDbBuildingRepairsForCostAccord() {
        return this.dbBuildingRepairsForCostAccord;
    }
    
    public void setDbBuildingRepairsForCostAccord(Set dbBuildingRepairsForCostAccord) {
        this.dbBuildingRepairsForCostAccord = dbBuildingRepairsForCostAccord;
    }

    public Set getDbBuildMaters() {
        return this.dbBuildMaters;
    }
    
    public void setDbBuildMaters(Set dbBuildMaters) {
        this.dbBuildMaters = dbBuildMaters;
    }

    public Set getDbBuildingRepairsForWaterProof() {
        return this.dbBuildingRepairsForWaterProof;
    }
    
    public void setDbBuildingRepairsForWaterProof(Set dbBuildingRepairsForWaterProof) {
        this.dbBuildingRepairsForWaterProof = dbBuildingRepairsForWaterProof;
    }

    public Set getDbBuildingsForUsetype() {
        return this.dbBuildingsForUsetype;
    }
    
    public void setDbBuildingsForUsetype(Set dbBuildingsForUsetype) {
        this.dbBuildingsForUsetype = dbBuildingsForUsetype;
    }

    public Set getDbBuildingsForProblem() {
        return this.dbBuildingsForProblem;
    }
    
    public void setDbBuildingsForProblem(Set dbBuildingsForProblem) {
        this.dbBuildingsForProblem = dbBuildingsForProblem;
    }

    public Set getDbBuildingsForCostaccord() {
        return this.dbBuildingsForCostaccord;
    }
    
    public void setDbBuildingsForCostaccord(Set dbBuildingsForCostaccord) {
        this.dbBuildingsForCostaccord = dbBuildingsForCostaccord;
    }

    public Set getDbBuildingsForQuakeproof() {
        return this.dbBuildingsForQuakeproof;
    }
    
    public void setDbBuildingsForQuakeproof(Set dbBuildingsForQuakeproof) {
        this.dbBuildingsForQuakeproof = dbBuildingsForQuakeproof;
    }

    public Set getDbBuildingRepairsForQuakeProof() {
        return this.dbBuildingRepairsForQuakeProof;
    }
    
    public void setDbBuildingRepairsForQuakeProof(Set dbBuildingRepairsForQuakeProof) {
        this.dbBuildingRepairsForQuakeProof = dbBuildingRepairsForQuakeProof;
    }

    public Set getDbBuildingRepairsForProblem() {
        return this.dbBuildingRepairsForProblem;
    }
    
    public void setDbBuildingRepairsForProblem(Set dbBuildingRepairsForProblem) {
        this.dbBuildingRepairsForProblem = dbBuildingRepairsForProblem;
    }
   








}
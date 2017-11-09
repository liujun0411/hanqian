package com.hanqian.pojo;

import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;


/**
 * DbProjectModule entity. @author MyEclipse Persistence Tools
 */

public class DbProjectModule  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbProjectModule dbProjectModule;
     private String name;
     private String depict;
     private Set dbProgramaLogs = new HashSet(0);
     private Set dbBuildLogs = new HashSet(0);
     private Set dbEquipLogs = new HashSet(0);
     private Set dbHospitallogs = new HashSet(0);
     private Set dbProjectModules = new HashSet(0);
     private Set dbUsersLogs = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbProjectModule() {
    }

    
    /** full constructor */
    public DbProjectModule(DbProjectModule dbProjectModule, String name, String depict, Set dbProgramaLogs, Set dbBuildLogs, Set dbEquipLogs, Set dbHospitallogs, Set dbProjectModules, Set dbUsersLogs) {
        this.dbProjectModule = dbProjectModule;
        this.name = name;
        this.depict = depict;
        this.dbProgramaLogs = dbProgramaLogs;
        this.dbBuildLogs = dbBuildLogs;
        this.dbEquipLogs = dbEquipLogs;
        this.dbHospitallogs = dbHospitallogs;
        this.dbProjectModules = dbProjectModules;
        this.dbUsersLogs = dbUsersLogs;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbProjectModule getDbProjectModule() {
        return this.dbProjectModule;
    }
    
    public void setDbProjectModule(DbProjectModule dbProjectModule) {
        this.dbProjectModule = dbProjectModule;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDepict() {
        return this.depict;
    }
    
    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Set getDbProgramaLogs() {
        return this.dbProgramaLogs;
    }
    
    public void setDbProgramaLogs(Set dbProgramaLogs) {
        this.dbProgramaLogs = dbProgramaLogs;
    }

    public Set getDbBuildLogs() {
        return this.dbBuildLogs;
    }
    
    public void setDbBuildLogs(Set dbBuildLogs) {
        this.dbBuildLogs = dbBuildLogs;
    }

    public Set getDbEquipLogs() {
        return this.dbEquipLogs;
    }
    
    public void setDbEquipLogs(Set dbEquipLogs) {
        this.dbEquipLogs = dbEquipLogs;
    }

    public Set getDbHospitallogs() {
        return this.dbHospitallogs;
    }
    
    public void setDbHospitallogs(Set dbHospitallogs) {
        this.dbHospitallogs = dbHospitallogs;
    }

    public Set getDbProjectModules() {
        return this.dbProjectModules;
    }
    
    public void setDbProjectModules(Set dbProjectModules) {
        this.dbProjectModules = dbProjectModules;
    }

    public Set getDbUsersLogs() {
        return this.dbUsersLogs;
    }
    
    public void setDbUsersLogs(Set dbUsersLogs) {
        this.dbUsersLogs = dbUsersLogs;
    }
   








}
package com.hanqian.pojo;

import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;


/**
 * DbAlarmCue entity. @author MyEclipse Persistence Tools
 */

public class DbAlarmCue  implements java.io.Serializable {


    // Fields    

     private Integer cueId;
     private String eqType;
     private String controlPoint;
     private String cueDepict;
     private Set dbAlarmCueUsers = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbAlarmCue() {
    }

	/** minimal constructor */
    public DbAlarmCue(String controlPoint) {
        this.controlPoint = controlPoint;
    }
    
    /** full constructor */
    public DbAlarmCue(String eqType, String controlPoint, String cueDepict, Set dbAlarmCueUsers) {
        this.eqType = eqType;
        this.controlPoint = controlPoint;
        this.cueDepict = cueDepict;
        this.dbAlarmCueUsers = dbAlarmCueUsers;
    }

   
    // Property accessors

    public Integer getCueId() {
        return this.cueId;
    }
    
    public void setCueId(Integer cueId) {
        this.cueId = cueId;
    }

    public String getEqType() {
        return this.eqType;
    }
    
    public void setEqType(String eqType) {
        this.eqType = eqType;
    }

    public String getControlPoint() {
        return this.controlPoint;
    }
    
    public void setControlPoint(String controlPoint) {
        this.controlPoint = controlPoint;
    }

    public String getCueDepict() {
        return this.cueDepict;
    }
    
    public void setCueDepict(String cueDepict) {
        this.cueDepict = cueDepict;
    }

    public Set getDbAlarmCueUsers() {
        return this.dbAlarmCueUsers;
    }
    
    public void setDbAlarmCueUsers(Set dbAlarmCueUsers) {
        this.dbAlarmCueUsers = dbAlarmCueUsers;
    }
   








}
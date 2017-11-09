package com.hanqian.pojo;

import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;


/**
 * DbPoint entity. @author MyEclipse Persistence Tools
 */

public class DbPoint  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private String controlpoint;
     private String projectPoint;
     private String pointName;
     private String hospId;
     private String serialport;
     private String unitType;
     private Double rate;
     private String sensor;
     private Short alertLevel;
     private Double upperValues;
     private Double lowerValues;
     private Integer delayTime;
     private Short powerType;
     private String descr;
     private Integer reflash;
     private String alarmCue;
     private String diV0Cue;
     private String diV1Cue;
     private Set dbPointEquips = new HashSet(0);
     private Set dbAlarms = new HashSet(0);
     private Set dbAlarmHises = new HashSet(0);
     private Set dbKeyEqs = new HashSet(0);
     private Set dbControldatas = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbPoint() {
    }

    
    /** full constructor */
    public DbPoint(String controlpoint, String projectPoint, String pointName, String hospId, String serialport, String unitType, Double rate, String sensor, Short alertLevel, Double upperValues, Double lowerValues, Integer delayTime, Short powerType, String descr, Integer reflash, String alarmCue, String diV0Cue, String diV1Cue, Set dbPointEquips, Set dbAlarms, Set dbAlarmHises, Set dbKeyEqs, Set dbControldatas) {
        this.controlpoint = controlpoint;
        this.projectPoint = projectPoint;
        this.pointName = pointName;
        this.hospId = hospId;
        this.serialport = serialport;
        this.unitType = unitType;
        this.rate = rate;
        this.sensor = sensor;
        this.alertLevel = alertLevel;
        this.upperValues = upperValues;
        this.lowerValues = lowerValues;
        this.delayTime = delayTime;
        this.powerType = powerType;
        this.descr = descr;
        this.reflash = reflash;
        this.alarmCue = alarmCue;
        this.diV0Cue = diV0Cue;
        this.diV1Cue = diV1Cue;
        this.dbPointEquips = dbPointEquips;
        this.dbAlarms = dbAlarms;
        this.dbAlarmHises = dbAlarmHises;
        this.dbKeyEqs = dbKeyEqs;
        this.dbControldatas = dbControldatas;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getControlpoint() {
        return this.controlpoint;
    }
    
    public void setControlpoint(String controlpoint) {
        this.controlpoint = controlpoint;
    }

    public String getProjectPoint() {
        return this.projectPoint;
    }
    
    public void setProjectPoint(String projectPoint) {
        this.projectPoint = projectPoint;
    }

    public String getPointName() {
        return this.pointName;
    }
    
    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getHospId() {
        return this.hospId;
    }
    
    public void setHospId(String hospId) {
        this.hospId = hospId;
    }

    public String getSerialport() {
        return this.serialport;
    }
    
    public void setSerialport(String serialport) {
        this.serialport = serialport;
    }

    public String getUnitType() {
        return this.unitType;
    }
    
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Double getRate() {
        return this.rate;
    }
    
    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getSensor() {
        return this.sensor;
    }
    
    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public Short getAlertLevel() {
        return this.alertLevel;
    }
    
    public void setAlertLevel(Short alertLevel) {
        this.alertLevel = alertLevel;
    }

    public Double getUpperValues() {
        return this.upperValues;
    }
    
    public void setUpperValues(Double upperValues) {
        this.upperValues = upperValues;
    }

    public Double getLowerValues() {
        return this.lowerValues;
    }
    
    public void setLowerValues(Double lowerValues) {
        this.lowerValues = lowerValues;
    }

    public Integer getDelayTime() {
        return this.delayTime;
    }
    
    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Short getPowerType() {
        return this.powerType;
    }
    
    public void setPowerType(Short powerType) {
        this.powerType = powerType;
    }

    public String getDescr() {
        return this.descr;
    }
    
    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getReflash() {
        return this.reflash;
    }
    
    public void setReflash(Integer reflash) {
        this.reflash = reflash;
    }

    public String getAlarmCue() {
        return this.alarmCue;
    }
    
    public void setAlarmCue(String alarmCue) {
        this.alarmCue = alarmCue;
    }

    public String getDiV0Cue() {
        return this.diV0Cue;
    }
    
    public void setDiV0Cue(String diV0Cue) {
        this.diV0Cue = diV0Cue;
    }

    public String getDiV1Cue() {
        return this.diV1Cue;
    }
    
    public void setDiV1Cue(String diV1Cue) {
        this.diV1Cue = diV1Cue;
    }

    public Set getDbPointEquips() {
        return this.dbPointEquips;
    }
    
    public void setDbPointEquips(Set dbPointEquips) {
        this.dbPointEquips = dbPointEquips;
    }

    public Set getDbAlarms() {
        return this.dbAlarms;
    }
    
    public void setDbAlarms(Set dbAlarms) {
        this.dbAlarms = dbAlarms;
    }

    public Set getDbAlarmHises() {
        return this.dbAlarmHises;
    }
    
    public void setDbAlarmHises(Set dbAlarmHises) {
        this.dbAlarmHises = dbAlarmHises;
    }

    public Set getDbKeyEqs() {
        return this.dbKeyEqs;
    }
    
    public void setDbKeyEqs(Set dbKeyEqs) {
        this.dbKeyEqs = dbKeyEqs;
    }

    public Set getDbControldatas() {
        return this.dbControldatas;
    }
    
    public void setDbControldatas(Set dbControldatas) {
        this.dbControldatas = dbControldatas;
    }
   








}
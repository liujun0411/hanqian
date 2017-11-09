package com.hanqian.pojo;



/**
 * DbAlarmCueUser entity. @author MyEclipse Persistence Tools
 */

public class DbAlarmCueUser  implements java.io.Serializable {


    // Fields    

     private DbAlarmCueUserId id;
     private DbAlarm dbAlarm;
     private DbSermainDetail dbSermainDetail;
     private DbAlarmCue dbAlarmCue;


    // Constructors

    /** default constructor */
    public DbAlarmCueUser() {
    }

    
    /** full constructor */
    public DbAlarmCueUser(DbAlarmCueUserId id, DbAlarm dbAlarm, DbSermainDetail dbSermainDetail, DbAlarmCue dbAlarmCue) {
        this.id = id;
        this.dbAlarm = dbAlarm;
        this.dbSermainDetail = dbSermainDetail;
        this.dbAlarmCue = dbAlarmCue;
    }

   
    // Property accessors

    public DbAlarmCueUserId getId() {
        return this.id;
    }
    
    public void setId(DbAlarmCueUserId id) {
        this.id = id;
    }

    public DbAlarm getDbAlarm() {
        return this.dbAlarm;
    }
    
    public void setDbAlarm(DbAlarm dbAlarm) {
        this.dbAlarm = dbAlarm;
    }

    public DbSermainDetail getDbSermainDetail() {
        return this.dbSermainDetail;
    }
    
    public void setDbSermainDetail(DbSermainDetail dbSermainDetail) {
        this.dbSermainDetail = dbSermainDetail;
    }

    public DbAlarmCue getDbAlarmCue() {
        return this.dbAlarmCue;
    }
    
    public void setDbAlarmCue(DbAlarmCue dbAlarmCue) {
        this.dbAlarmCue = dbAlarmCue;
    }
   








}
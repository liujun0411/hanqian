package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbAlarm entity. @author MyEclipse Persistence Tools
 */

public class DbAlarm  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbPoint dbPoint;
     private String projectPoint;
     private String name;
     private Short alertlevel;
     private Date starttime;
     private String remarks;
     private Integer delayTimes;
     private Short showflag;
     private String alarmDesc;
     private String deleteflag;
     private Date updateDate;
     private Set dbAlarmCueUsers = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbAlarm() {
    }

    
    /** full constructor */
    public DbAlarm(DbPoint dbPoint, String projectPoint, String name, Short alertlevel, Date starttime, String remarks, Integer delayTimes, Short showflag, Set dbAlarmCueUsers,String deleteflag,Date updateDate,String alarmDesc) {
        this.dbPoint = dbPoint;
        this.projectPoint = projectPoint;
        this.name = name;
        this.alertlevel = alertlevel;
        this.starttime = starttime;
        this.remarks = remarks;
        this.delayTimes = delayTimes;
        this.alarmDesc = alarmDesc;
        this.showflag = showflag;
        this.alarmDesc=alarmDesc;
        this.deleteflag=deleteflag;
        this.dbAlarmCueUsers = dbAlarmCueUsers;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbPoint getDbPoint() {
        return this.dbPoint;
    }
    
    public void setDbPoint(DbPoint dbPoint) {
        this.dbPoint = dbPoint;
    }

    public String getProjectPoint() {
        return this.projectPoint;
    }
    
    public void setProjectPoint(String projectPoint) {
        this.projectPoint = projectPoint;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Short getAlertlevel() {
        return this.alertlevel;
    }
    
    public void setAlertlevel(Short alertlevel) {
        this.alertlevel = alertlevel;
    }

    public Date getStarttime() {
        return this.starttime;
    }
    
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDelayTimes() {
        return this.delayTimes;
    }
    
    public void setDelayTimes(Integer delayTimes) {
        this.delayTimes = delayTimes;
    }

    public Short getShowflag() {
        return this.showflag;
    }
    
    public void setShowflag(Short showflag) {
        this.showflag = showflag;
    }

    public Set getDbAlarmCueUsers() {
        return this.dbAlarmCueUsers;
    }
    
    public void setDbAlarmCueUsers(Set dbAlarmCueUsers) {
        this.dbAlarmCueUsers = dbAlarmCueUsers;
    }


	/**
	 * @return the alarmDesc
	 */
	public String getAlarmDesc() {
		return alarmDesc;
	}


	/**
	 * @param alarmDesc the alarmDesc to set
	 */
	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}


	/**
	 * @return the deleteflag
	 */
	public String getDeleteflag() {
		return deleteflag;
	}


	/**
	 * @param deleteflag the deleteflag to set
	 */
	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}


	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}


	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
   








}
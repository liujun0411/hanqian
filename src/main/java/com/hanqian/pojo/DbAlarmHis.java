package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbAlarmHis entity. @author MyEclipse Persistence Tools
 */

public class DbAlarmHis  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbPoint dbPoint;
     private Integer alarmSeq;
     private String projectPoint;
     private String name;
     private Short alertlevel;
     private Date starttime;
     private Date endtime;
     private String oper;
     private String remarks;
     private String des;
     private Date recovertime;

    // Constructors

    /** default constructor */
    public DbAlarmHis() {
    }

    
    /** full constructor */
    public DbAlarmHis(DbPoint dbPoint, Integer alarmSeq, String projectPoint, String name, Short alertlevel, Date starttime, Date endtime, String oper, String remarks,String des,Date recovertime) {
        this.dbPoint = dbPoint;
        this.alarmSeq = alarmSeq;
        this.projectPoint = projectPoint;
        this.name = name;
        this.alertlevel = alertlevel;
        this.starttime = starttime;
        this.endtime = endtime;
        this.oper = oper;
        this.remarks = remarks;
        this.des = des;
        this.recovertime=recovertime;
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

    public Integer getAlarmSeq() {
        return this.alarmSeq;
    }
    
    public void setAlarmSeq(Integer alarmSeq) {
        this.alarmSeq = alarmSeq;
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

    public Date getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getOper() {
        return this.oper;
    }
    
    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}


	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}


	/**
	 * @return the recovertime
	 */
	public Date getRecovertime() {
		return recovertime;
	}


	/**
	 * @param recovertime the recovertime to set
	 */
	public void setRecovertime(Date recovertime) {
		this.recovertime = recovertime;
	}
   








}
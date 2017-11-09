package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbHospDate entity. @author MyEclipse Persistence Tools
 */

public class DbHospDate  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private Date starttime;
     private Date endtime;


    // Constructors

    /** default constructor */
    public DbHospDate() {
    }

    
    /** full constructor */
    public DbHospDate(Date starttime, Date endtime) {
        this.starttime = starttime;
        this.endtime = endtime;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
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
   








}
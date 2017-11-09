package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbAnswerBoard entity. @author MyEclipse Persistence Tools
 */

public class DbAnswerBoard  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbUsers dbUsers;
     private DbHospInfo dbHospInfo;
     private DbMessageBoard dbMessageBoard;
     private String content;
     private Date ansTime;


    // Constructors

    /** default constructor */
    public DbAnswerBoard() {
    }

    
    /** full constructor */
    public DbAnswerBoard(DbUsers dbUsers, DbHospInfo dbHospInfo, DbMessageBoard dbMessageBoard, String content, Date ansTime) {
        this.dbUsers = dbUsers;
        this.dbHospInfo = dbHospInfo;
        this.dbMessageBoard = dbMessageBoard;
        this.content = content;
        this.ansTime = ansTime;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbMessageBoard getDbMessageBoard() {
        return this.dbMessageBoard;
    }
    
    public void setDbMessageBoard(DbMessageBoard dbMessageBoard) {
        this.dbMessageBoard = dbMessageBoard;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Date getAnsTime() {
        return this.ansTime;
    }
    
    public void setAnsTime(Date ansTime) {
        this.ansTime = ansTime;
    }
   








}
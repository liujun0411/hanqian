package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbBoardLog entity. @author MyEclipse Persistence Tools
 */

public class DbBoardLog  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbMessageBoard dbMessageBoard;
     private DbUsers dbUsers;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbBoardLog() {
    }

    
    /** full constructor */
    public DbBoardLog(DbMessageBoard dbMessageBoard, DbUsers dbUsers, Date opertime) {
        this.dbMessageBoard = dbMessageBoard;
        this.dbUsers = dbUsers;
        this.opertime = opertime;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbMessageBoard getDbMessageBoard() {
        return this.dbMessageBoard;
    }
    
    public void setDbMessageBoard(DbMessageBoard dbMessageBoard) {
        this.dbMessageBoard = dbMessageBoard;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }
   








}
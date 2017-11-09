package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbMessageBoard entity. @author MyEclipse Persistence Tools
 */

public class DbMessageBoard  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbHospInfo dbHospInfo;
     private DbUsers dbUsers;
     private String title;
     private String content;
     private Date opertime;
     private Set dbMessagetRecipents = new HashSet(0);
     private Set dbBoardLogs = new HashSet(0);
     private Set dbAnswerBoards = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbMessageBoard() {
    }

    
    /** full constructor */
    public DbMessageBoard(DbHospInfo dbHospInfo, DbUsers dbUsers, String title, String content, Date opertime, Set dbMessagetRecipents, Set dbBoardLogs, Set dbAnswerBoards) {
        this.dbHospInfo = dbHospInfo;
        this.dbUsers = dbUsers;
        this.title = title;
        this.content = content;
        this.opertime = opertime;
        this.dbMessagetRecipents = dbMessagetRecipents;
        this.dbBoardLogs = dbBoardLogs;
        this.dbAnswerBoards = dbAnswerBoards;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Set getDbMessagetRecipents() {
        return this.dbMessagetRecipents;
    }
    
    public void setDbMessagetRecipents(Set dbMessagetRecipents) {
        this.dbMessagetRecipents = dbMessagetRecipents;
    }

    public Set getDbBoardLogs() {
        return this.dbBoardLogs;
    }
    
    public void setDbBoardLogs(Set dbBoardLogs) {
        this.dbBoardLogs = dbBoardLogs;
    }

    public Set getDbAnswerBoards() {
        return this.dbAnswerBoards;
    }
    
    public void setDbAnswerBoards(Set dbAnswerBoards) {
        this.dbAnswerBoards = dbAnswerBoards;
    }
   








}
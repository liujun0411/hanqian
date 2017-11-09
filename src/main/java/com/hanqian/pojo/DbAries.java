package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbAlarm entity. @author MyEclipse Persistence Tools
 */

public class DbAries  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private Integer readed;
     private String content;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbAries() {
    }

    
    /** full constructor */
    public DbAries(Integer readed, String content, Date opertime) {
        this.readed = readed;
        this.content = content;
        this.opertime = opertime;
    }


	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}


	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	/**
	 * @return the readed
	 */
	public Integer getReaded() {
		return readed;
	}


	/**
	 * @param readed the readed to set
	 */
	public void setReaded(Integer readed) {
		this.readed = readed;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the opertime
	 */
	public Date getOpertime() {
		return opertime;
	}


	/**
	 * @param opertime the opertime to set
	 */
	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

   







}
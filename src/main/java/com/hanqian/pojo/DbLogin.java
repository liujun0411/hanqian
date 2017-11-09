package com.hanqian.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DbLogin entity. @author MyEclipse Persistence Tools
 */

public class DbLogin implements java.io.Serializable {

	// Fields

	private Integer seq;
	private DbUsers dbUsers;
	private String ip;
	private Date logintime;
	private Date logouttime;

	private Integer systemCount;
	// Constructors

	/** default constructor */
	public DbLogin() {
	}

	/** full constructor */
	public DbLogin(DbUsers dbUsers, String ip, Date logintime, Date logouttime) {
		this.dbUsers = dbUsers;
		this.ip = ip;
		this.logintime = logintime;
		this.logouttime = logouttime;
	}

	// Property accessors

	 

	public DbUsers getDbUsers() {
		return this.dbUsers;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public void setDbUsers(DbUsers dbUsers) {
		this.dbUsers = dbUsers;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLogouttime() {
		return this.logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}

	public Integer getSystemCount() {
		return systemCount;
	}

	public void setSystemCount(Integer systemCount) {
		this.systemCount = systemCount;
	}

}
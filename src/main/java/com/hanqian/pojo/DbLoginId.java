package com.hanqian.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DbLoginId entity. @author MyEclipse Persistence Tools
 */

public class DbLoginId implements java.io.Serializable {

	// Fields

	private BigDecimal seq;
	private String ip;
	private BigDecimal oper;
	private Date logintime;

	// Constructors

	/** default constructor */
	public DbLoginId() {
	}

	/** minimal constructor */
	public DbLoginId(BigDecimal seq) {
		this.seq = seq;
	}

	/** full constructor */
	public DbLoginId(BigDecimal seq, String ip, BigDecimal oper, Date logintime) {
		this.seq = seq;
		this.ip = ip;
		this.oper = oper;
		this.logintime = logintime;
	}

	// Property accessors

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public BigDecimal getOper() {
		return this.oper;
	}

	public void setOper(BigDecimal oper) {
		this.oper = oper;
	}

	public Date getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DbLoginId))
			return false;
		DbLoginId castOther = (DbLoginId) other;

		return ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null
				&& castOther.getSeq() != null && this.getSeq().equals(
				castOther.getSeq())))
				&& ((this.getIp() == castOther.getIp()) || (this.getIp() != null
						&& castOther.getIp() != null && this.getIp().equals(
						castOther.getIp())))
				&& ((this.getOper() == castOther.getOper()) || (this.getOper() != null
						&& castOther.getOper() != null && this.getOper()
						.equals(castOther.getOper())))
				&& ((this.getLogintime() == castOther.getLogintime()) || (this
						.getLogintime() != null
						&& castOther.getLogintime() != null && this
						.getLogintime().equals(castOther.getLogintime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSeq() == null ? 0 : this.getSeq().hashCode());
		result = 37 * result + (getIp() == null ? 0 : this.getIp().hashCode());
		result = 37 * result
				+ (getOper() == null ? 0 : this.getOper().hashCode());
		result = 37 * result
				+ (getLogintime() == null ? 0 : this.getLogintime().hashCode());
		return result;
	}

}
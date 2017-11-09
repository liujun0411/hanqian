package com.hanqian.pojo;

import java.lang.Integer;

/**
 * DbUseroleId entity. @author MyEclipse Persistence Tools
 */

public class DbUseroleId implements java.io.Serializable {

	// Fields

	private Integer userid;
	private Short roleid;

	// Constructors

	/** default constructor */
	public DbUseroleId() {
	}

	/** full constructor */
	public DbUseroleId(Integer userid, Short roleid) {
		this.userid = userid;
		this.roleid = roleid;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Short getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Short roleid) {
		this.roleid = roleid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DbUseroleId))
			return false;
		DbUseroleId castOther = (DbUseroleId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null
				&& castOther.getUserid() != null && this.getUserid().equals(
				castOther.getUserid())))
				&& ((this.getRoleid() == castOther.getRoleid()) || (this
						.getRoleid() != null
						&& castOther.getRoleid() != null && this.getRoleid()
						.equals(castOther.getRoleid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		return result;
	}

}
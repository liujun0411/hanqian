package com.hanqian.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * DbReportType entity. @author MyEclipse Persistence Tools
 */

public class DbReportType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;
	private String descr;
	private Short status;
	private Set dbDatacommitlogs = new HashSet(0);
	private Set dbReportRates = new HashSet(0);
	private Set dbReportDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public DbReportType() {
	}

	/** full constructor */
	public DbReportType(String name, String descr, Short status,
			Set dbDatacommitlogs, Set dbReportRates, Set dbReportDetails) {
		this.name = name;
		this.descr = descr;
		this.status = status;
		this.dbDatacommitlogs = dbDatacommitlogs;
		this.dbReportRates = dbReportRates;
		this.dbReportDetails = dbReportDetails;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Set getDbDatacommitlogs() {
		return this.dbDatacommitlogs;
	}

	public void setDbDatacommitlogs(Set dbDatacommitlogs) {
		this.dbDatacommitlogs = dbDatacommitlogs;
	}

	public Set getDbReportRates() {
		return this.dbReportRates;
	}

	public void setDbReportRates(Set dbReportRates) {
		this.dbReportRates = dbReportRates;
	}

	public Set getDbReportDetails() {
		return this.dbReportDetails;
	}

	public void setDbReportDetails(Set dbReportDetails) {
		this.dbReportDetails = dbReportDetails;
	}

}
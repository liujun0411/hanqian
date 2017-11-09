package com.hanqian.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DbReportDetail entity. @author MyEclipse Persistence Tools
 */

public class DbReportDetail implements java.io.Serializable {

	// Fields

	private String id;
	private DbReportType dbReportType;
	private Date reporttime;
	private Date endtime;
	private BigDecimal lh;
	private String source;
	private String datadescr;
	private Short status;
	private String statusdescr;

	// Constructors

	/** default constructor */
	public DbReportDetail() {
	}

	/** full constructor */
	public DbReportDetail(DbReportType dbReportType, Date reporttime,
			Date endtime, BigDecimal lh, String source, String datadescr,
			Short status, String statusdescr) {
		this.dbReportType = dbReportType;
		this.reporttime = reporttime;
		this.endtime = endtime;
		this.lh = lh;
		this.source = source;
		this.datadescr = datadescr;
		this.status = status;
		this.statusdescr = statusdescr;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DbReportType getDbReportType() {
		return this.dbReportType;
	}

	public void setDbReportType(DbReportType dbReportType) {
		this.dbReportType = dbReportType;
	}

	public Date getReporttime() {
		return this.reporttime;
	}

	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public BigDecimal getLh() {
		return this.lh;
	}

	public void setLh(BigDecimal lh) {
		this.lh = lh;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDatadescr() {
		return this.datadescr;
	}

	public void setDatadescr(String datadescr) {
		this.datadescr = datadescr;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStatusdescr() {
		return this.statusdescr;
	}

	public void setStatusdescr(String statusdescr) {
		this.statusdescr = statusdescr;
	}

}
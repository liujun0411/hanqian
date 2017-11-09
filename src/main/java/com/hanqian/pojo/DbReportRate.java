package com.hanqian.pojo;

import java.util.Date;

/**
 * DbReportRate entity. @author MyEclipse Persistence Tools
 */

public class DbReportRate implements java.io.Serializable {

	// Fields

	private String id;
	private DbReportType dbReportType;
	private Short rate;
	private String reporttime;
	private String reportip1;
	private String reportip4;
	private String reportip3;
	private String reportip2;
	private String source;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
	private Date reportdate;

	// Constructors

	/** default constructor */
	public DbReportRate() {
	}

	/** full constructor */
	public DbReportRate(DbReportType dbReportType, Short rate,
			String reporttime, String reportip1, String reportip4,
			String reportip3, String reportip2, String source, String remark1,
			String remark2, String remark3, String remark4, String remark5,
			Date reportdate) {
		this.dbReportType = dbReportType;
		this.rate = rate;
		this.reporttime = reporttime;
		this.reportip1 = reportip1;
		this.reportip4 = reportip4;
		this.reportip3 = reportip3;
		this.reportip2 = reportip2;
		this.source = source;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
		this.remark4 = remark4;
		this.remark5 = remark5;
		this.reportdate = reportdate;
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

	public Short getRate() {
		return this.rate;
	}

	public void setRate(Short rate) {
		this.rate = rate;
	}

	public String getReporttime() {
		return this.reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getReportip1() {
		return this.reportip1;
	}

	public void setReportip1(String reportip1) {
		this.reportip1 = reportip1;
	}

	public String getReportip4() {
		return this.reportip4;
	}

	public void setReportip4(String reportip4) {
		this.reportip4 = reportip4;
	}

	public String getReportip3() {
		return this.reportip3;
	}

	public void setReportip3(String reportip3) {
		this.reportip3 = reportip3;
	}

	public String getReportip2() {
		return this.reportip2;
	}

	public void setReportip2(String reportip2) {
		this.reportip2 = reportip2;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return this.remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark5() {
		return this.remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public Date getReportdate() {
		return this.reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}

}
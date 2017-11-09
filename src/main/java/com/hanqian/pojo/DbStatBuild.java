package com.hanqian.pojo;

/**
 * DbStatBuild entity. @author MyEclipse Persistence Tools
 */

public class DbStatBuild implements java.io.Serializable {

	// Fields

	private String id;
	private String hospid;
	private String buildid;
	private Short power;
	private Double wastage;
	private Double convert;
	private String stattime;

	// Constructors

	/** default constructor */
	public DbStatBuild() {
	}

	/** full constructor */
	public DbStatBuild(String hospid, String buildid, Short power,
			Double wastage, Double convert, String stattime) {
		this.hospid = hospid;
		this.buildid = buildid;
		this.power = power;
		this.wastage = wastage;
		this.convert = convert;
		this.stattime = stattime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHospid() {
		return this.hospid;
	}

	public void setHospid(String hospid) {
		this.hospid = hospid;
	}

	public String getBuildid() {
		return this.buildid;
	}

	public void setBuildid(String buildid) {
		this.buildid = buildid;
	}

	public Short getPower() {
		return this.power;
	}

	public void setPower(Short power) {
		this.power = power;
	}

	public Double getWastage() {
		return this.wastage;
	}

	public void setWastage(Double wastage) {
		this.wastage = wastage;
	}

	public Double getConvert() {
		return this.convert;
	}

	public void setConvert(Double convert) {
		this.convert = convert;
	}

	public String getStattime() {
		return this.stattime;
	}

	public void setStattime(String stattime) {
		this.stattime = stattime;
	}

}
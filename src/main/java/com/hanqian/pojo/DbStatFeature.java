package com.hanqian.pojo;

/**
 * DbStatFeature entity. @author MyEclipse Persistence Tools
 */

public class DbStatFeature implements java.io.Serializable {

	// Fields

	private String id;
	private Short yt;
	private Short power;
	private Double areas;
	private Double wastage;
	private Double convert;
	private String stattime;

	// Constructors

	/** default constructor */
	public DbStatFeature() {
	}

	/** full constructor */
	public DbStatFeature(Short yt, Short power, Double areas, Double wastage,
			Double convert, String stattime) {
		this.yt = yt;
		this.power = power;
		this.areas = areas;
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

	public Short getYt() {
		return this.yt;
	}

	public void setYt(Short yt) {
		this.yt = yt;
	}

	public Short getPower() {
		return this.power;
	}

	public void setPower(Short power) {
		this.power = power;
	}

	public Double getAreas() {
		return this.areas;
	}

	public void setAreas(Double areas) {
		this.areas = areas;
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
package com.hanqian.pojo;

/**
 * DbStatHospital entity. @author MyEclipse Persistence Tools
 */

public class DbStatHospital implements java.io.Serializable {

	// Fields

	private String id;
	private Short power;
	private Double wastage;
	private Double convert;
	private String hospid;
	private String stattime;

	// Constructors

	/** default constructor */
	public DbStatHospital() {
	}

	/** full constructor */
	public DbStatHospital(Short power, Double wastage, Double convert,
			String hospid, String stattime) {
		this.power = power;
		this.wastage = wastage;
		this.convert = convert;
		this.hospid = hospid;
		this.stattime = stattime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getHospid() {
		return this.hospid;
	}

	public void setHospid(String hospid) {
		this.hospid = hospid;
	}

	public String getStattime() {
		return this.stattime;
	}

	public void setStattime(String stattime) {
		this.stattime = stattime;
	}

}
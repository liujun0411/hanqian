package com.hanqian.pojo;

/**
 * DbStatBusiness entity. @author MyEclipse Persistence Tools
 */

public class DbStatBusiness implements java.io.Serializable {

	// Fields

	private String id;
	private Short businesstype;
	private Double business;
	private Double arearate;
	private Double wastage;
	private Short power;
	private Double convert;
	private String stattime;
	private String hospid;

	// Constructors

	/** default constructor */
	public DbStatBusiness() {
	}

	/** full constructor */
	public DbStatBusiness(Short businesstype, Double business, Double arearate,
			Double wastage, Short power, Double convert, String stattime,
			String hospid) {
		this.businesstype = businesstype;
		this.business = business;
		this.arearate = arearate;
		this.wastage = wastage;
		this.power = power;
		this.convert = convert;
		this.stattime = stattime;
		this.hospid = hospid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getBusinesstype() {
		return this.businesstype;
	}

	public void setBusinesstype(Short businesstype) {
		this.businesstype = businesstype;
	}

	public Double getBusiness() {
		return this.business;
	}

	public void setBusiness(Double business) {
		this.business = business;
	}

	public Double getArearate() {
		return this.arearate;
	}

	public void setArearate(Double arearate) {
		this.arearate = arearate;
	}

	public Double getWastage() {
		return this.wastage;
	}

	public void setWastage(Double wastage) {
		this.wastage = wastage;
	}

	public Short getPower() {
		return this.power;
	}

	public void setPower(Short power) {
		this.power = power;
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

	public String getHospid() {
		return this.hospid;
	}

	public void setHospid(String hospid) {
		this.hospid = hospid;
	}

}
package com.hanqian.pojo;

/**
 * DbBuildingrate entity. @author MyEclipse Persistence Tools
 */

public class DbBuildingrate implements java.io.Serializable {

	// Fields

	private String sequence;
	private String infoyear;
	private Double fz;
	private String yt;
	private String fh;

	// Constructors

	/** default constructor */
	public DbBuildingrate() {
	}

	/** full constructor */
	public DbBuildingrate(String infoyear, Double fz, String yt, String fh) {
		this.infoyear = infoyear;
		this.fz = fz;
		this.yt = yt;
		this.fh = fh;
	}
	public DbBuildingrate(String infoyear, Double fz, String yt) {
		this.infoyear = infoyear;
		this.fz = fz;
		this.yt = yt;
	}

	// Property accessors

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getInfoyear() {
		return this.infoyear;
	}

	public void setInfoyear(String infoyear) {
		this.infoyear = infoyear;
	}

	public Double getFz() {
		return this.fz;
	}

	public void setFz(Double fz) {
		this.fz = fz;
	}

	public String getYt() {
		return this.yt;
	}

	public void setYt(String yt) {
		this.yt = yt;
	}

	public String getFh() {
		return this.fh;
	}

	public void setFh(String fh) {
		this.fh = fh;
	}

}
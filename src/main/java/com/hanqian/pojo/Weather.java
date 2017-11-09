package com.hanqian.pojo;

import java.util.Date;

/**
 * Weather entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Weather implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dt;
	private Integer cityId;
	private String city;
	private Double temp1;
	private Double temp2;
	private String img1;
	private String img2;
	private String weather;
	private String ptime;
	private Date opTime;
	// Constructors

	/** default constructor */
	public Weather() {
	}

	/** full constructor */
	public Weather(String dt, Integer cityId, String city, Double temp1,
			Double temp2,String img1,String img2, String weather, String ptime, Date opTime) {
		this.dt = dt;
		this.cityId = cityId;
		this.city = city;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.img1 = img1;
		this.img2 = img2;
		this.weather = weather;
		this.ptime = ptime;
		this.opTime = opTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDt() {
		return this.dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getTemp1() {
		return this.temp1;
	}

	public void setTemp1(Double temp1) {
		this.temp1 = temp1;
	}

	public Double getTemp2() {
		return this.temp2;
	}

	public void setTemp2(Double temp2) {
		this.temp2 = temp2;
	}
	public String getImg1() {
		return this.img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return this.img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getWeather() {
		return this.weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getPtime() {
		return this.ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public Date getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

}
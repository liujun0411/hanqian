package com.hanqian.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DbUnitPrice entity. @author MyEclipse Persistence Tools
 */

public class DbUnitPrice implements java.io.Serializable {

	// Fields

	private Integer id;
	private Short type;
	private String priiceMonthRangStart;
	private String priiceMonthRangEnd;
	private Double unitPrice;
	private String customerCode;
	private Date opTime;
	private Set dbEnergyBills = new HashSet(0);

	// Constructors

	/** default constructor */
	public DbUnitPrice() {
	}

	/** minimal constructor */
	public DbUnitPrice(Short type, String priiceMonthRangStart,
			Double unitPrice, String customerCode, Date opTime) {
		this.type = type;
		this.priiceMonthRangStart = priiceMonthRangStart;
		this.unitPrice = unitPrice;
		this.customerCode = customerCode;
		this.opTime = opTime;
	}

	/** full constructor */
	public DbUnitPrice(Short type, String priiceMonthRangStart,
			String priiceMonthRangEnd, Double unitPrice, String customerCode,
			Date opTime, Set dbEnergyBills) {
		this.type = type;
		this.priiceMonthRangStart = priiceMonthRangStart;
		this.priiceMonthRangEnd = priiceMonthRangEnd;
		this.unitPrice = unitPrice;
		this.customerCode = customerCode;
		this.opTime = opTime;
		this.dbEnergyBills = dbEnergyBills;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getPriiceMonthRangStart() {
		return this.priiceMonthRangStart;
	}

	public void setPriiceMonthRangStart(String priiceMonthRangStart) {
		this.priiceMonthRangStart = priiceMonthRangStart;
	}

	public String getPriiceMonthRangEnd() {
		return this.priiceMonthRangEnd;
	}

	public void setPriiceMonthRangEnd(String priiceMonthRangEnd) {
		this.priiceMonthRangEnd = priiceMonthRangEnd;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Date getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public Set getDbEnergyBills() {
		return this.dbEnergyBills;
	}

	public void setDbEnergyBills(Set dbEnergyBills) {
		this.dbEnergyBills = dbEnergyBills;
	}

}
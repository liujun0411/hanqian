package com.hanqian.pojo;

import java.util.Date;

/**
 * DbEnergyBill entity. @author MyEclipse Persistence Tools
 */

public class DbEnergyBill implements java.io.Serializable {

	// Fields

	private Integer id;
	private DbUnitPrice dbUnitPrice;
	private Short type;
	private String billMonth;
	private Double sum;
	private String customerCode;
	private String barCode;
	private String des;
	private String used;
	private Date opTime;
	private Float consumption;
	// Constructors

	/**
	 * @return the consumption
	 */
	public Float getConsumption() {
		return consumption;
	}

	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	/** default constructor */
	public DbEnergyBill() {
	}

	/** full constructor */
	public DbEnergyBill(DbUnitPrice dbUnitPrice, Short type,
			String billMonth, Double sum, String customerCode,String barCode ,Date opTime,Float consumption) {
		this.dbUnitPrice = dbUnitPrice;
		this.type = type;
		this.billMonth = billMonth;
		this.sum = sum;
		this.customerCode = customerCode;
		this.barCode = barCode;
		this.opTime = opTime;
		this.consumption=consumption;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DbUnitPrice getDbUnitPrice() {
		return this.dbUnitPrice;
	}

	public void setDbUnitPrice(DbUnitPrice dbUnitPrice) {
		this.dbUnitPrice = dbUnitPrice;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getBillMonth() {
		return this.billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public Double getSum() {
		return this.sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @param barCode the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @return the used
	 */
	public String getUsed() {
		return used;
	}

	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(String used) {
		this.used = used;
	}

	public Date getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

}
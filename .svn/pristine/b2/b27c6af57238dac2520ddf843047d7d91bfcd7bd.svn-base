package com.hanqian.pojo;

import java.util.Date;

/**
 * Businessmodulelog entity. @author MyEclipse Persistence Tools
 */

public class BusinessModuleLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private BusinessModule businessmodule;
	private String moduledes;
	private String moduletable;
	private String tableidentify;
	private Integer operator;
	private String oper;

	private Date opertime;

	// Constructors

	/** default constructor */
	public BusinessModuleLog() {
	}

	/** minimal constructor */
	public BusinessModuleLog(BusinessModule businessmodule, String moduletable,Integer operator,Date opertime,String oper) {
		this.businessmodule = businessmodule;
		this.moduletable = moduletable;
		this.operator = operator;
		this.opertime = opertime;
		this.oper = oper;
	}

	/** full constructor */
	public BusinessModuleLog(BusinessModule businessmodule, String moduledes,
			String moduletable, String tableidentify,Integer operator,Date opertime,String oper) {
		this.businessmodule = businessmodule;
		this.moduledes = moduledes;
		this.moduletable = moduletable;
		this.tableidentify = tableidentify;
		this.operator = operator;
		this.opertime = opertime;
		this.oper = oper;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BusinessModule getBusinessmodule() {
		return this.businessmodule;
	}

	public void setBusinessmodule(BusinessModule businessmodule) {
		this.businessmodule = businessmodule;
	}

	public String getModuledes() {
		return this.moduledes;
	}

	public void setModuledes(String moduledes) {
		this.moduledes = moduledes;
	}

	public String getModuletable() {
		return this.moduletable;
	}

	public void setModuletable(String moduletable) {
		this.moduletable = moduletable;
	}

	public String getTableidentify() {
		return this.tableidentify;
	}

	public void setTableidentify(String tableidentify) {
		this.tableidentify = tableidentify;
	}
	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}
}
package com.hanqian.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Businessmodule entity. @author MyEclipse Persistence Tools
 */

public class BusinessModule implements java.io.Serializable {

	// Fields

	private Integer id;
	private String businessname;
	private String businessdes;
	private String moduleshort;
	private Set businessmodulelogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public BusinessModule() {
	}

	/** minimal constructor */
	public BusinessModule(String businessname, String moduleshort) {
		this.businessname = businessname;
		this.moduleshort = moduleshort;
	}

	/** full constructor */
	public BusinessModule(String businessname, String businessdes,
			String moduleshort, Set businessmodulelogs) {
		this.businessname = businessname;
		this.businessdes = businessdes;
		this.moduleshort = moduleshort;
		this.businessmodulelogs = businessmodulelogs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusinessname() {
		return this.businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getBusinessdes() {
		return this.businessdes;
	}

	public void setBusinessdes(String businessdes) {
		this.businessdes = businessdes;
	}

	public String getModuleshort() {
		return this.moduleshort;
	}

	public void setModuleshort(String moduleshort) {
		this.moduleshort = moduleshort;
	}

	public Set getBusinessmodulelogs() {
		return this.businessmodulelogs;
	}

	public void setBusinessmodulelogs(Set businessmodulelogs) {
		this.businessmodulelogs = businessmodulelogs;
	}

}
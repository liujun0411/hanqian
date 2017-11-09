package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DbAnalyType entity. @author MyEclipse Persistence Tools
 */

public class DbAnalyType implements java.io.Serializable {

	// Fields

	private Integer seq;
	private String typename;
	private String typedesc;
	private Integer input;
	private Date inputtime;
	private Integer oper;
	private Date opertime;
	private Set dbAnalyDocs = new HashSet(0);

	// Constructors

	/** default constructor */
	public DbAnalyType() {
	}

	/** full constructor */
	public DbAnalyType(String typename, String typedesc, Integer input,
			Date inputtime, Integer oper, Date opertime, Set dbAnalyDocs) {
		this.typename = typename;
		this.typedesc = typedesc;
		this.input = input;
		this.inputtime = inputtime;
		this.oper = oper;
		this.opertime = opertime;
		this.dbAnalyDocs = dbAnalyDocs;
	}

	// Property accessors

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypedesc() {
		return this.typedesc;
	}

	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}

	public Integer getInput() {
		return this.input;
	}

	public void setInput(Integer input) {
		this.input = input;
	}

	public Date getInputtime() {
		return this.inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	public Integer getOper() {
		return this.oper;
	}

	public void setOper(Integer oper) {
		this.oper = oper;
	}

	public Date getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public Set getDbAnalyDocs() {
		return this.dbAnalyDocs;
	}

	public void setDbAnalyDocs(Set dbAnalyDocs) {
		this.dbAnalyDocs = dbAnalyDocs;
	}

}
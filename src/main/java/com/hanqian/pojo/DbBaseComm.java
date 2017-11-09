package com.hanqian.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DbBaseComm entity. @author MyEclipse Persistence Tools
 */

public class DbBaseComm implements java.io.Serializable {

	// Fields

	private Integer seq;
	private DbBaseType dbBaseType;
	private String content1;
	private String content2;
	private Integer deletFlag;
	private Integer input;
	private Date inputtime;
	private Integer oper;
	private Date opertime;
	private Set dbHospInfosForKind = new HashSet(0);
	private Set dbHospInfosForHospdist = new HashSet(0);
	private Set dbHospInfosForHosplevel = new HashSet(0);
	private Set dbHospDates = new HashSet(0);
	//private Set dbSermainRepairs = new HashSet(0);
	private Set dbHospInfosForHospCode = new HashSet(0);
	private Set dbBuildingPics = new HashSet(0);
	private Set dbBuildings = new HashSet(0);
	private Set dbHospAreaFacts = new HashSet(0);
	private Set dbBuildingStoreyHises = new HashSet(0);
	//private Set dbEquipSermains = new HashSet(0);
	private Set dbServices = new HashSet(0);
	private Set equipServices = new HashSet(0);
	private Set dbBuildingStoreies = new HashSet(0);
	private Set dbRoleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public DbBaseComm() {
	}

	/** full constructor */
	public DbBaseComm(DbBaseType dbBaseType, String content1, String content2,
			Integer deletFlag, Integer input, Date inputtime,
			Integer oper, Date opertime, Set dbHospInfosForKind,
			Set dbHospInfosForHospdist, Set dbHospInfosForHosplevel,
			Set dbHospDates, Set dbSermainRepairs, Set dbHospInfosForHospCode,
			Set dbBuildingPics, Set dbBuildings, Set dbHospAreaFacts,
			Set dbBuildingStoreyHises, Set dbEquipSermains, Set dbServices,
			Set equipServices, Set dbBuildingStoreies, Set dbRoleses) {
		this.dbBaseType = dbBaseType;
		this.content1 = content1;
		this.content2 = content2;
		this.deletFlag = deletFlag;
		this.input = input;
		this.inputtime = inputtime;
		this.oper = oper;
		this.opertime = opertime;
		this.dbHospInfosForKind = dbHospInfosForKind;
		this.dbHospInfosForHospdist = dbHospInfosForHospdist;
		this.dbHospInfosForHosplevel = dbHospInfosForHosplevel;
		this.dbHospDates = dbHospDates;
		//this.dbSermainRepairs = dbSermainRepairs;
		this.dbHospInfosForHospCode = dbHospInfosForHospCode;
		this.dbBuildingPics = dbBuildingPics;
		this.dbBuildings = dbBuildings;
		this.dbHospAreaFacts = dbHospAreaFacts;
		this.dbBuildingStoreyHises = dbBuildingStoreyHises;
		//this.dbEquipSermains = dbEquipSermains;
		this.dbServices = dbServices;
		this.equipServices = equipServices;
		this.dbBuildingStoreies = dbBuildingStoreies;
		this.dbRoleses = dbRoleses;
	}

	// Property accessors

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public DbBaseType getDbBaseType() {
		return this.dbBaseType;
	}

	public void setDbBaseType(DbBaseType dbBaseType) {
		this.dbBaseType = dbBaseType;
	}

	public String getContent1() {
		return this.content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return this.content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public Integer getDeletFlag() {
		return this.deletFlag;
	}

	public void setDeletFlag(Integer deletFlag) {
		this.deletFlag = deletFlag;
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

	public Set getDbHospInfosForKind() {
		return this.dbHospInfosForKind;
	}

	public void setDbHospInfosForKind(Set dbHospInfosForKind) {
		this.dbHospInfosForKind = dbHospInfosForKind;
	}

	public Set getDbHospInfosForHospdist() {
		return this.dbHospInfosForHospdist;
	}

	public void setDbHospInfosForHospdist(Set dbHospInfosForHospdist) {
		this.dbHospInfosForHospdist = dbHospInfosForHospdist;
	}

	public Set getDbHospInfosForHosplevel() {
		return this.dbHospInfosForHosplevel;
	}

	public void setDbHospInfosForHosplevel(Set dbHospInfosForHosplevel) {
		this.dbHospInfosForHosplevel = dbHospInfosForHosplevel;
	}

	public Set getDbHospDates() {
		return this.dbHospDates;
	}

	public void setDbHospDates(Set dbHospDates) {
		this.dbHospDates = dbHospDates;
	}

/*	public Set getDbSermainRepairs() {
		return this.dbSermainRepairs;
	}

	public void setDbSermainRepairs(Set dbSermainRepairs) {
		this.dbSermainRepairs = dbSermainRepairs;
	}*/

	public Set getDbHospInfosForHospCode() {
		return this.dbHospInfosForHospCode;
	}

	public void setDbHospInfosForHospCode(Set dbHospInfosForHospCode) {
		this.dbHospInfosForHospCode = dbHospInfosForHospCode;
	}

	public Set getDbBuildingPics() {
		return this.dbBuildingPics;
	}

	public void setDbBuildingPics(Set dbBuildingPics) {
		this.dbBuildingPics = dbBuildingPics;
	}

	public Set getDbBuildings() {
		return this.dbBuildings;
	}

	public void setDbBuildings(Set dbBuildings) {
		this.dbBuildings = dbBuildings;
	}

	public Set getDbHospAreaFacts() {
		return this.dbHospAreaFacts;
	}

	public void setDbHospAreaFacts(Set dbHospAreaFacts) {
		this.dbHospAreaFacts = dbHospAreaFacts;
	}

	public Set getDbBuildingStoreyHises() {
		return this.dbBuildingStoreyHises;
	}

	public void setDbBuildingStoreyHises(Set dbBuildingStoreyHises) {
		this.dbBuildingStoreyHises = dbBuildingStoreyHises;
	}

/*	public Set getDbEquipSermains() {
		return this.dbEquipSermains;
	}

	public void setDbEquipSermains(Set dbEquipSermains) {
		this.dbEquipSermains = dbEquipSermains;
	}*/

	public Set getDbServices() {
		return this.dbServices;
	}

	public void setDbServices(Set dbServices) {
		this.dbServices = dbServices;
	}

	public Set getEquipServices() {
		return this.equipServices;
	}

	public void setEquipServices(Set equipServices) {
		this.equipServices = equipServices;
	}

	public Set getDbBuildingStoreies() {
		return this.dbBuildingStoreies;
	}

	public void setDbBuildingStoreies(Set dbBuildingStoreies) {
		this.dbBuildingStoreies = dbBuildingStoreies;
	}

	public Set getDbRoleses() {
		return this.dbRoleses;
	}

	public void setDbRoleses(Set dbRoleses) {
		this.dbRoleses = dbRoleses;
	}

}
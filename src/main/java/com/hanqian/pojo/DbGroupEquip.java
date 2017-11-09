package com.hanqian.pojo;

/**
 * DbGroupEquip entity. @author MyEclipse Persistence Tools
 */

public class DbGroupEquip implements java.io.Serializable {

	// Fields

	private DbGroupEquipId id;
	private DbEquipList dbEquipList;
	private DbEquipGroup dbEquipGroup;

	// Constructors

	/** default constructor */
	public DbGroupEquip() {
	}

	/** full constructor */
	public DbGroupEquip(DbGroupEquipId id, DbEquipList dbEquipList,
			DbEquipGroup dbEquipGroup) {
		this.id = id;
		this.dbEquipList = dbEquipList;
		this.dbEquipGroup = dbEquipGroup;
	}

	// Property accessors

	public DbGroupEquipId getId() {
		return this.id;
	}

	public void setId(DbGroupEquipId id) {
		this.id = id;
	}

	public DbEquipList getDbEquipList() {
		return this.dbEquipList;
	}

	public void setDbEquipList(DbEquipList dbEquipList) {
		this.dbEquipList = dbEquipList;
	}

	public DbEquipGroup getDbEquipGroup() {
		return this.dbEquipGroup;
	}

	public void setDbEquipGroup(DbEquipGroup dbEquipGroup) {
		this.dbEquipGroup = dbEquipGroup;
	}

}
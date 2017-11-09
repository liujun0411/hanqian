package com.hanqian.pojo;


/**
 * EquipService entity. @author MyEclipse Persistence Tools
 */

public class EquipService implements java.io.Serializable {

	// Fields

	private Integer seq;
	private DbEnergyType dbEnergyType;
	private DbBaseComm dbBaseComm;
	private DbEquipList dbEquipList;
	private Integer buildId;
	private Integer storey;
	private Double square;
	private String comments;

	// Constructors

	/** default constructor */
	public EquipService() {
	}

	/** full constructor */
	public EquipService(DbEnergyType dbEnergyType, DbBaseComm dbBaseComm,
			DbEquipList dbEquipList, Integer buildId, Integer storey,
			String areas,Double square) {
		this.dbEnergyType = dbEnergyType;
		this.dbBaseComm = dbBaseComm;
		this.dbEquipList = dbEquipList;
		this.buildId = buildId;
		this.storey = storey;
		this.square = square;
	}

	// Property accessors

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public DbEnergyType getDbEnergyType() {
		return this.dbEnergyType;
	}

	public void setDbEnergyType(DbEnergyType dbEnergyType) {
		this.dbEnergyType = dbEnergyType;
	}

	public DbBaseComm getDbBaseComm() {
		return this.dbBaseComm;
	}

	public void setDbBaseComm(DbBaseComm dbBaseComm) {
		this.dbBaseComm = dbBaseComm;
	}

	public DbEquipList getDbEquipList() {
		return this.dbEquipList;
	}

	public void setDbEquipList(DbEquipList dbEquipList) {
		this.dbEquipList = dbEquipList;
	}

	public Integer getBuildId() {
		return this.buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	public Integer getStorey() {
		return this.storey;
	}

	public void setStorey(Integer storey) {
		this.storey = storey;
	}

	public Double getSquare() {
		return this.square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


}
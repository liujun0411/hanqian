package com.hanqian.pojo;


/**
 * DbGroupEquipId entity. @author MyEclipse Persistence Tools
 */

public class DbGroupEquipId implements java.io.Serializable {

	// Fields

	private Integer equipGroup;
	private Integer equipId;

	// Constructors

	/** default constructor */
	public DbGroupEquipId() {
	}

	/** full constructor */
	public DbGroupEquipId(Integer equipGroup, Integer equipId) {
		this.equipGroup = equipGroup;
		this.equipId = equipId;
	}

	// Property accessors

	public Integer getEquipGroup() {
		return this.equipGroup;
	}

	public void setEquipGroup(Integer equipGroup) {
		this.equipGroup = equipGroup;
	}

	public Integer getEquipId() {
		return this.equipId;
	}

	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DbGroupEquipId))
			return false;
		DbGroupEquipId castOther = (DbGroupEquipId) other;

		return ((this.getEquipGroup() == castOther.getEquipGroup()) || (this
				.getEquipGroup() != null
				&& castOther.getEquipGroup() != null && this.getEquipGroup()
				.equals(castOther.getEquipGroup())))
				&& ((this.getEquipId() == castOther.getEquipId()) || (this
						.getEquipId() != null
						&& castOther.getEquipId() != null && this.getEquipId()
						.equals(castOther.getEquipId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEquipGroup() == null ? 0 : this.getEquipGroup()
						.hashCode());
		result = 37 * result
				+ (getEquipId() == null ? 0 : this.getEquipId().hashCode());
		return result;
	}

}
package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbEquipGroup entity. @author MyEclipse Persistence Tools
 */

public class DbEquipGroup  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbUsers dbUsersByInput;
     private DbEquipType dbEquipType;
     private DbUsers dbUsersByOper;
     private String groupId;
     private String groupName;
     private String PGroupId;
     private Date inputtime;
     private Date opertime;
     private String remark;
     private String groupPic;
     private Integer buildId;
     private float storey;
     private String groupCode;
     private Set dbEquipLists = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbEquipGroup() {
    }

    
    /** full constructor */
    public DbEquipGroup(DbUsers dbUsersByInput, DbEquipType dbEquipType, DbUsers dbUsersByOper, String groupId, String groupName, String PGroupId, Date inputtime, Date opertime, Set dbEquipLists) {
        this.dbUsersByInput = dbUsersByInput;
        this.dbEquipType = dbEquipType;
        this.dbUsersByOper = dbUsersByOper;
        this.groupId = groupId;
        this.groupName = groupName;
        this.PGroupId = PGroupId;
        this.inputtime = inputtime;
        this.opertime = opertime;
        this.dbEquipLists = dbEquipLists;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbUsers getDbUsersByInput() {
        return this.dbUsersByInput;
    }
    
    public void setDbUsersByInput(DbUsers dbUsersByInput) {
        this.dbUsersByInput = dbUsersByInput;
    }

    public DbEquipType getDbEquipType() {
        return this.dbEquipType;
    }
    
    public void setDbEquipType(DbEquipType dbEquipType) {
        this.dbEquipType = dbEquipType;
    }

    public DbUsers getDbUsersByOper() {
        return this.dbUsersByOper;
    }
    
    public void setDbUsersByOper(DbUsers dbUsersByOper) {
        this.dbUsersByOper = dbUsersByOper;
    }

    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        if("".equals(groupId))
    		groupId=null;
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(String groupName) {
        if("".equals(groupName))
    		groupName=null;
        this.groupName = groupName;
    }

    public String getPGroupId() {
        return this.PGroupId;
    }
    
    public void setPGroupId(String PGroupId) {
        if("".equals(PGroupId))
    		PGroupId=null;
        this.PGroupId = PGroupId;
    }

    public Date getInputtime() {
        return this.inputtime;
    }
    
    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Set getDbEquipLists() {
        return this.dbEquipLists;
    }
    
    public void setDbEquipLists(Set dbEquipLists) {
        this.dbEquipLists = dbEquipLists;
    }


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		if("".equals(remark))
			remark=null;
		this.remark = remark;
	}


	/**
	 * @return the groupPic
	 */
	public String getGroupPic() {
		return groupPic;
	}


	/**
	 * @param groupPic the groupPic to set
	 */
	public void setGroupPic(String groupPic) {
		if("".equals(groupPic))
			groupPic=null;
		this.groupPic = groupPic;
	}


	/**
	 * @return the buildId
	 */
	public Integer getBuildId() {
		return buildId;
	}


	/**
	 * @param buildId the buildId to set
	 */
	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}


	/**
	 * @return the storey
	 */
	public float getStorey() {
		return storey;
	}


	/**
	 * @param storey the storey to set
	 */
	public void setStorey(float storey) {
		this.storey = storey;
	}


	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}


	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		if("".equals(groupCode))
			groupCode=null;
		this.groupCode = groupCode;
	}
}
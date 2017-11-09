package com.hanqian.form;

import java.util.List;

/**
 * 描 述: 设备组
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-19
 * @see
 */
public class VOEquipGroup {
	
	private int index;  //页面显示下标
	
	private int seq;   //序号
	
	private String groupId;  //组Id
	
	private String groupName;  //组名
	
	private String equipType;  //设备类型Id
	
	private String typeName;  //设备组名称
	private String picName;//组图纸
	private String buildId;//楼宇ID
	
	private String groupRemark;  //组名
	
	private String groupCode;
	
	private List<VOEquipList> equipList;
	
	/**
	 * 查询设备
	 * @param equipId
	 * @return
	 */
	public VOEquipList findEqList(String equipId){
		VOEquipList obj=null;
		if (equipList != null) {
			for (VOEquipList eq:equipList) {
				if (equipId.equals(eq.getEquipId())) {
					obj = eq;
					break;
				}
			}
		}
		return obj;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<VOEquipList> getEquipList() {
		return equipList;
	}

	public void setEquipList(List<VOEquipList> equipList) {
		this.equipList = equipList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getEquipType() {
		return equipType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the picName
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * @param picName the picName to set
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}

	/**
	 * @return the buildId
	 */
	public String getBuildId() {
		return buildId;
	}

	/**
	 * @param buildId the buildId to set
	 */
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}

	public String getGroupRemark() {
		return groupRemark;
	}

	public void setGroupRemark(String groupRemark) {
		this.groupRemark = groupRemark;
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
		this.groupCode = groupCode;
	}

}

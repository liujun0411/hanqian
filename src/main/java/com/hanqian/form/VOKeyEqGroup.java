package com.hanqian.form;

import java.util.List;



/**
 * 
 * 描 述: 关键设备组
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 李江
 * @version 1.4 2012-9-19
 * @see
 */
public class VOKeyEqGroup {
	private int    at;			//序号
	
	private Integer groupId;		//组编号
	
	private String groupName;	//组名称
	
	private List<VOKeyEqS>   equipList;		//设备集
	
	
	/**
	 * 查询设备
	 * @param eqcode
	 * @return
	 */
	public VOKeyEqS findEq(Integer equipId){
		VOKeyEqS obj=null;
		if (equipList != null) {
			for (VOKeyEqS eq:equipList) {
				if (equipId.equals(eq.getEquipId())) {
					obj =eq;
					break;
				}
			}
		}
		
		return obj;
	}
	
	
	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<VOKeyEqS> getEquipList() {
		return equipList;
	}

	public void setEquipList(List<VOKeyEqS> equipList) {
		this.equipList = equipList;
	}

}

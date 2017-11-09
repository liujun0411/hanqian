package com.hanqian.form;

import java.util.List;

/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-11-13
 * @see
 */
public class VOIllmineFloor {
	
	//楼层
	private String buildId;
	
	private String storeyDown;
	
	private String storeyUp;
	
	private String floor;
	
	private int number;
	
	private List<VOIllminePoint> showIllmineList;
	
	/**
	 * 查询这个设备时候有点位
	 * @param seq
	 * @return
	 */
	public VOIllminePoint findEqPoint(String seq){
		VOIllminePoint obj=null;
		if (showIllmineList != null) {
			for (VOIllminePoint eq:showIllmineList) {
				if (seq.equals(eq.getEquipId())) {
					obj = eq;
					break;
				}
			}
		}
		return obj;
	}

	
	/**
	 * @param buildId
	 * @param storeyDown
	 * @param storeyUp
	 * @param floor
	 * @param number
	 */
	public VOIllmineFloor(String buildId, String storeyDown, String storeyUp,
			String floor, int number) {
		super();
		this.buildId = buildId;
		this.storeyDown = storeyDown;
		this.storeyUp = storeyUp;
		this.floor = floor;
		this.number = number;
	}

	public String getBuildId() {
		return buildId;
	}

	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}

	public String getStoreyDown() {
		return storeyDown;
	}

	public void setStoreyDown(String storeyDown) {
		this.storeyDown = storeyDown;
	}

	public String getStoreyUp() {
		return storeyUp;
	}

	public void setStoreyUp(String storeyUp) {
		this.storeyUp = storeyUp;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<VOIllminePoint> getShowIllmineList() {
		return showIllmineList;
	}

	public void setShowIllmineList(List<VOIllminePoint> showIllmineList) {
		this.showIllmineList = showIllmineList;
	}
	
}

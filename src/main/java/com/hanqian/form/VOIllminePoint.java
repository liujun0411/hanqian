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
 * @version 1.4 2012-11-12
 * @see
 */
public class VOIllminePoint {
	
	//楼层
	private String buildId;
	
	private String storeyDown;
	
	private String storeyUp;
	
	private String floor;
	
	private String equipId;
	
	private List<VOShowPoint> showPointList;
	
	

	/**
	 * @param buildId
	 * @param storeyDown
	 * @param storeyUp
	 * @param floor
	 * @param equipId
	 */
	public VOIllminePoint(String buildId, String storeyDown, String storeyUp,
			String floor, String equipId) {
		super();
		this.buildId = buildId;
		this.storeyDown = storeyDown;
		this.storeyUp = storeyUp;
		this.floor = floor;
		this.equipId = equipId;
	}




	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public List<VOShowPoint> getShowPointList() {
		return showPointList;
	}

	public void setShowPointList(List<VOShowPoint> showPointList) {
		this.showPointList = showPointList;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
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
	
	
}

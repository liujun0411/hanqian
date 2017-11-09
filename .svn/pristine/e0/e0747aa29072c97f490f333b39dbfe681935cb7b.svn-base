package com.hanqian.form;

import java.util.List;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-11-2
 * @see
 */
public class VOElevatorPoint {

	private String seq;
	
	private String equipId;
	
	private String storeyUp;
	
	private String storeyDown;
	
	private String equipName;
	
	private List<VOShowPoint> showPointList;
	
	/**
	 * 查询这个设备时候有点位
	 * @param seq
	 * @return
	 */
	public VOShowPoint findEqPoint(String seq){
		VOShowPoint obj=null;
		if (showPointList != null) {
			for (VOShowPoint eq:showPointList) {
				if (seq.equals(eq.getEquipId())) {
					obj = eq;
					break;
				}
			}
		}
		return obj;
	}
	

	/**
	 * @param equipId
	 * @param storey
	 * @param equipName
	 */
	public VOElevatorPoint(String equipId, String storeyUp,String storeyDown,
			String equipName) {
		this.equipId = equipId;
		this.storeyUp = storeyUp;
		this.storeyDown = storeyDown;
		this.equipName = equipName;
	}

	
	
	/**
	 * 
	 */
	public VOElevatorPoint() {
		super();
	}


	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	
	public String getStoreyUp() {
		return storeyUp;
	}


	public void setStoreyUp(String storeyUp) {
		this.storeyUp = storeyUp;
	}


	public String getStoreyDown() {
		return storeyDown;
	}


	public void setStoreyDown(String storeyDown) {
		this.storeyDown = storeyDown;
	}


	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public List<VOShowPoint> getShowPointList() {
		return showPointList;
	}

	public void setShowPointList(List<VOShowPoint> showPointList) {
		this.showPointList = showPointList;
	}


	public String getSeq() {
		return seq;
	}


	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}

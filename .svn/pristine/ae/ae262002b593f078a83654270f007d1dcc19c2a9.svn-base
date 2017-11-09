package com.hanqian.form;

import java.util.List;


/**
 * 
 * 描 述: 关键设备
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
public class VOKeyEqS {
	private Integer typeId;		//设备类型
	
	private String equipCode;		//设备编号
	
	private String name;		//设备名称
	
	private List<VOKeyPoint>   points;		//点位集合
	
	private Integer equipId; //设备ID
	
	/**
	 * 查询设备
	 * @param eqcode
	 * @return
	 */
	public VOKeyPoint findPoint(String keypoint){
		VOKeyPoint obj=null;
		if (keypoint != null) {
			for (VOKeyPoint point:points) {
				if (keypoint.equals(point.getKeyPoint())) {
					obj =point;
					break;
				}
			}
		}
		
		return obj;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VOKeyPoint> getPoints() {
		return points;
	}

	public void setPoints(List<VOKeyPoint> points) {
		this.points = points;
	}

	public Integer getEquipId() {
		return equipId;
	}

	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}

}

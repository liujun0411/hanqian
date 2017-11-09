package com.hanqian.form;

import java.util.ArrayList;
/**
 * 
 * 描 述: 
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
public class VoKeyEq {
	public class KeyEqPoint{
		
		private String depict;
		
		private String unit;
		
		private String value;

		public String getDepict() {
			return depict;
		}
		public void setDepict(String depict) {
			this.depict = depict;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}		
	}
	
	private String equipName;
	private String equipType;
	private ArrayList<KeyEqPoint> keyPoint;
	
	
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public ArrayList<KeyEqPoint> getKeyPoint() {
		return keyPoint;
	}
	public void setKeyPoint(ArrayList<KeyEqPoint> keyPoint) {
		this.keyPoint = keyPoint;
	}
}

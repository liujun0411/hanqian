package com.hanqian.pojo;

/**
 * @author lizhenhua
 *  天气规则 35度返回值 
 */
public class Watthourmeter {
	//电流峰值
	private Double record;
	//峰值时间
	private String recordtime;
	//额定电流
	private String paramvalue;
	//项目名称
	private String equipname;
	//占比
	private String proportion;
	
	public Double getRecord() {
		return record;
	}
	public void setRecord(Double record) {
		this.record = record;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getEquipname() {
		return equipname;
	}
	public void setEquipname(String equipname) {
		this.equipname = equipname;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
	
	

}

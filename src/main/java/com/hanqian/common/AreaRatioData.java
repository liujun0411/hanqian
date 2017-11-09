package com.hanqian.common;


/**
 * 面积比例
 *
 */
public class AreaRatioData {
	private String id;				//ID
	private String name;			//名称
	private String unitname;		//单元名
	private double datavlue;		//数据值
	private String note1;			//备注1
	private String note2;			//备注2
	
	
	public AreaRatioData() {
		super();
	}
	public AreaRatioData(String id, String name, String unitname, double datavlue) {
		super();
		this.id = id;
		this.name = name;
		this.unitname = unitname;
		this.datavlue = datavlue;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getUnitname() {
		return unitname;
	}
	public double getDatavlue() {
		return datavlue;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public void setDatavlue(double datavlue) {
		this.datavlue = datavlue;
	}
	public String getNote1() {
		return note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	
	@Override
	public String toString(){
		return "id:"+this.id+"\t name:"+this.name+"\t unitname:"
		+this.unitname+"\t datavalue:"+this.datavlue+"\t note1:"
		+this.note1+"\t note2:"+this.note2;
	}
}


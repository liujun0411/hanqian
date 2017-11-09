package com.hanqian.form;



/**
 * 面积比例记录
 * @author clczp
 *
 */
public class VOMyRecord {
	private String id;					//ID
	private String name;				//名称
	private String comparechar;			//比较符
	private float datavalue;			//比例
	private float area;					//面积
	
	
	
	public VOMyRecord() {
		super();
	}
	public VOMyRecord(String id, String name, String comparechar,
			float datavalue) {
		super();
		this.id = id;
		this.name = name;
		this.comparechar = comparechar;
		this.datavalue = datavalue;
	}
	
	
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getComparechar() {
		return comparechar;
	}
	public float getDatavalue() {
		return datavalue;
	}
	public void setComparechar(String comparechar) {
		this.comparechar = comparechar;
	}
	public void setDatavalue(float datavalue) {
		this.datavalue = datavalue;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}		
}

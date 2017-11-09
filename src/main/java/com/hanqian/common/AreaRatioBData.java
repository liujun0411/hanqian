package com.hanqian.common;


/**
 * 面积比例基准
 * @author clczp
 *
 */
public class AreaRatioBData {
	private String id;					//ID
	private String name;				//名称
	private String comparechar;			//比较符
	private double datavlue;			//数据值
	
	
	
	
	public AreaRatioBData() {
		super();
	}
	public AreaRatioBData(String id, String name, String comparechar,
			double datavlue) {
		super();
		this.id = id;
		this.name = name;
		this.comparechar = comparechar;
		this.datavlue = datavlue;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getComparechar() {
		if (null == comparechar) {
			comparechar ="";
		}
		return comparechar;
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
	public void setComparechar(String comparechar) {
		this.comparechar = comparechar;
	}
	public void setDatavlue(double datavlue) {
		this.datavlue = datavlue;
	}
	
	
}

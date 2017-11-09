package com.hanqian.form;


/**
 * 特征区域
 * @author clczp
 *
 */
public class FeatureData {
	private String yt;		//域编号
	private String name;	//名称
	private Double areas;	//面积
	private Double wastage;	//能耗量
	private Double convert;	//价值
	private String stattime;//时间
	public String getYt() {
		return yt;
	}
	public String getName() {
		return name;
	}
	public Double getAreas() {
		return areas;
	}
	public Double getWastage() {
		return wastage;
	}
	public Double getConvert() {
		return convert;
	}
	public String getStattime() {
		return stattime;
	}
	public void setYt(String yt) {
		this.yt = yt;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAreas(Double areas) {
		this.areas = areas;
	}
	public void setWastage(Double wastage) {
		this.wastage = wastage;
	}
	public void setConvert(Double convert) {
		this.convert = convert;
	}
	public void setStattime(String stattime) {
		this.stattime = stattime;
	}
	
	
	
}

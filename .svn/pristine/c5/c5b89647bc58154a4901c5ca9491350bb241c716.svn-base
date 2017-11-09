package com.hanqian.form;

import com.hanqian.util.ETimeStep;


/**
 * 数据分析条件
 * @author clczp
 *
 */
public class VOStatCondition {
	private Short  power;				//能源类型
	private String startDate;			//开始时间	
	private String endDate;				//结束时间
	private ETimeStep timeStep;			//时间隔
	private String  hospid;				//医院Id
	private String selUnits;			//统计单元
	private String baseLine;			//基准
	private Double pcha;				//偏差
	private String goWhere;				//查询内容(能耗量/能耗价值)
	
	
	
	public String getGoWhere() {
		return goWhere;
	}
	public void setGoWhere(String goWhere) {
		this.goWhere = goWhere;
	}
	public String getBaseLine() {
		return baseLine;
	}
	public Double getPcha() {
		return pcha;
	}
	public void setBaseLine(String baseLine) {
		this.baseLine = baseLine;
	}
	public void setPcha(Double pcha) {
		this.pcha = pcha;
	}
	public String getSelUnits() {
		return selUnits;
	}
	public void setSelUnits(String selUnits) {
		this.selUnits = selUnits;
	}
	public Short getPower() {
		return power;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public ETimeStep getTimeStep() {
		return timeStep;
	}
	public void setPower(Short power) {
		this.power = power;
	}	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setTimeStep(ETimeStep timeStep) {
		this.timeStep = timeStep;
	}
	public String getHospid() {
		return hospid;
	}
	public void setHospid(String hospid) {
		this.hospid = hospid;
	}
	
	
	
	/**
	 * 备份对象
	 * @return
	 */
	public VOStatCondition getBack(){
		VOStatCondition obj = new VOStatCondition();
		obj.setHospid(this.hospid);
		obj.setPower(this.power);
		obj.setStartDate(this.startDate);
		obj.setEndDate(this.endDate);
		obj.setTimeStep(this.timeStep);
		obj.setSelUnits(this.selUnits);
		
		return obj;
	}
	
	/**
	 * 重写toString方法
	 */
	public String toString(){
		String msg = "hospid:" + this.hospid + ",power:" + this.power
				+ ",startDate:" + this.startDate + ",endDate:" + this.endDate
				+ ",timeStep:" + this.timeStep + ",selUnits:" + this.selUnits
				+ ",baseLine:" + this.baseLine + ",pcha:" + this.pcha;
		return msg;
	}
}

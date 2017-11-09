package com.hanqian.form;

import com.hanqian.util.ETimeStep;
import com.hanqian.util.SysUtil;


/**
 * 设备能耗VO
 * @author clczp
 *
 */
public class VOEqAnalysis {
	private String hospid;				//医院Id
	private Short  power;				//能源类型
	private String startDate;			//开始时间	
	private String endDate;				//结束时间
	private ETimeStep timeStep;			//时间隔
	
	private String eqtype;				//设备类型
	private String unittype;			//型号
	private String production;			//生产厂商
	private String ftyid;				//首类型
	private String styid;				//子类型
	private String coname;				//厂商名(name,name,...)
	private String eqcodes;				//设备编号(eqcode,eqcode,...)
	
	
	
	public String getEqcodes() {
		return eqcodes;
	}
	public void setEqcodes(String eqcodes) {
		this.eqcodes = eqcodes;
	}
	public String getFtyid() {
		return ftyid;
	}
	public String getStyid() {
		return styid;
	}
	public String getConame() {
		return coname;
	}
	public void setFtyid(String ftyid) {
		this.ftyid = ftyid;
	}
	public void setStyid(String styid) {
		this.styid = styid;
	}
	public void setConame(String coname) {
		this.coname = coname;
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
	public String getHospid() {
		return hospid;
	}
	public String getEqtype() {
		return eqtype;
	}
	public String getUnittype() {
		return unittype;
	}
	public String getProduction() {
		return production;
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
	public void setHospid(String hospid) {
		this.hospid = hospid;
	}
	public void setEqtype(String eqtype) {
		this.eqtype = eqtype;
	}
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	
	/**
	 * 重载 toString()
	 */
	@Override
	public String toString(){
		return "hospid:"+this.hospid+"  power:"+this.power+
		" startDate:"+this.startDate+" endDate:"+this.endDate+
		" timeStep:"+this.timeStep+" eqtype:"+this.eqtype+
		" unittype:"+this.unittype+" production:"+this.production+
		" ftyid:"+this.ftyid+" styid:"+this.styid+" coname:"+this.coname+
		" eqcodes:"+this.eqcodes;
	}
	
	/**
	 * 对象备份
	 * @return
	 */
	public VOEqAnalysis getBack(){
		VOEqAnalysis obj = new VOEqAnalysis();
		obj.setHospid(this.hospid);
		obj.setPower(this.power);
		obj.setStartDate(this.startDate);
		obj.setEndDate(this.endDate);
		obj.setTimeStep(this.timeStep);
		obj.setEqtype(this.eqtype);
		obj.setUnittype(this.unittype);
		obj.setProduction(this.production);
		obj.setFtyid(this.ftyid);
		obj.setStyid(this.styid);
		obj.setEqcodes(this.eqcodes);
		obj.setConame(this.coname);
		if (SysUtil.isNull(this.styid)) {
			obj.setEqtype(this.ftyid);
		}else{
			obj.setEqtype(this.styid);
		}
		
		return obj;
	}
}

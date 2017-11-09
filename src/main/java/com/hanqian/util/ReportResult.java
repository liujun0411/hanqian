package com.hanqian.util;

public class ReportResult {
	private String source;//数据编号
	private int status;//上报状态（1：成功 2：失败）
	private String statusdescr;//上报结果 
	private int typeid;//上报类型
	private int succedConut;//成功信息条数
	private int errorCount;//错误信息条数
	private int sumCount;//总共条数
	private String datadescr;//错误信息
	
	public String getDatadescr() {
		return datadescr;
	}
	public void setDatadescr(String datadescr) {
		this.datadescr = datadescr;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusdescr() {
		return statusdescr;
	}
	public void setStatusdescr(String statusdescr) {
		this.statusdescr = statusdescr;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getSuccedConut() {
		return succedConut;
	}
	public void setSuccedConut(int succedConut) {
		this.succedConut = succedConut;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	public ReportResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportResult(String source, int status, String statusdescr,
			int typeid, int succedConut, int errorCount, int sumCount,
			String datadescr) {
		super();
		this.source = source;
		this.status = status;
		this.statusdescr = statusdescr;
		this.typeid = typeid;
		this.succedConut = succedConut;
		this.errorCount = errorCount;
		this.sumCount = sumCount;
		this.datadescr = datadescr;
	}

}

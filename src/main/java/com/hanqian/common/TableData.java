package com.hanqian.common;

import java.util.List;


/**
 * 表格数据
 * @author clczp
 *
 */
public class TableData {
	private String title;			//标题
	private List<String> keys;		//列
	private List<Object> values;	//记录
	
	
	
	public TableData() {
		super();
	}
	public TableData(List<String> keys, List<Object> values) {
		this.keys = keys;
		this.values = values;
	}
	public List<String> getKeys() {
		return keys;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	
	
}

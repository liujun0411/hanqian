package com.hanqian.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.hanqian.pojo.DbStatEq;


/**
 * 设备能耗数据转换
 * @author clczp
 *
 */
public class EqAntoShowData {
	
		
	
	/**
	 * 同类设备各设备能耗图形数据
	 * @param clist
	 * @return
	 */
	public static List<ChartData> toEqChartData(List<DbStatEq> dlist){
		List<ChartData> list =null;
		if (null != dlist) {
			list = new ArrayList<ChartData>();
			ChartData obj = null;
			for (DbStatEq co:dlist) {
				obj = new ChartData();
				obj.setRowKey(co.getEqname());
				obj.setColumnKey(co.getStattime());
				obj.setValue(co.getWastage());
				
				obj.setMapTitle("");
				list.add(obj);
			}
		}
		
		return list;
	}
	
	
	/**
	 * 同类设备各厂商能耗图形数据
	 * @param clist
	 * @return
	 */
	public static List<ChartData> toChartData(List<CoData> clist){
		List<ChartData> list =null;
		if (null != clist) {
			list = new ArrayList<ChartData>();
			ChartData obj = null;
			for (CoData co:clist) {
				obj = new ChartData();
				obj.setRowKey(co.getName());
				obj.setColumnKey(co.getStattime());
				obj.setValue(co.getValue());
				
				obj.setMapTitle("");
				list.add(obj);
			}
		}
		
		return list;
	}

	/**
	 * 能耗表格数据
	 * @param clist
	 * @return
	 */
	public static TableData toTableData(List<ChartData> clist,String unit){
		TableData obj = new TableData();
		
		HashMap<String,HashMap<String,Double>> cm= new HashMap<String,HashMap<String,Double>>();
		HashMap<String,Double> rm= new HashMap<String,Double>();
		HashMap<String,Object> cl= new HashMap<String,Object>();
		String date=null;
		for (ChartData cd:clist) {
			date = cd.getColumnKey()+"";
			if (null == cm.get(date)) {
				cm.put(date, new HashMap<String,Double>());
			}
			rm = cm.get(date);
			if (null == rm.get(cd.getRowKey()+"")) {
				rm.put(cd.getRowKey()+"", cd.getValue().doubleValue());
			}
			if (null == cl.get(cd.getRowKey()+"")) {
				cl.put(cd.getRowKey()+"", true);
			}
		}
		
		//列
		List<String> keys= new ArrayList<String>();
		
		keys.add("时间");
		for (Iterator<String> it=cl.keySet().iterator();it.hasNext();) {
			keys.add(it.next()+"("+unit+")");
		}		
		obj.setKeys(keys);
		
		
		//行
		List<Object> values=new ArrayList<Object>();
		HashMap<String,Double> tm=null;
		List<Object> re=null;
		String time=null;
		for (Iterator<String> it=cm.keySet().iterator();it.hasNext();) {
			time = it.next();
			tm= cm.get(time);
			re= new ArrayList<Object>();
			re.add(time);
			for (Iterator<String> itl=cl.keySet().iterator();itl.hasNext();) {
				re.add(tm.get(itl.next()));
			}
			values.add(re);
		}
		obj.setValues(values);
		return obj;
	}
}

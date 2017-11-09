package com.hanqian.common;


import java.util.Comparator;



/**
 * ChartList 排序
 * @author clczp
 *
 */
public class ComparatorCharList implements Comparator {
	public int compare(Object obja, Object objb) {
		ChartData obj1= (ChartData)obja;
		ChartData obj2= (ChartData)objb;
		//比较
		int flag =0;
		if (obj1 !=null && obj2 !=null) {
			flag =((String)obj1.getColumnKey()).compareTo((String)obj2.getColumnKey());
		}
		
		return flag;
	}
}

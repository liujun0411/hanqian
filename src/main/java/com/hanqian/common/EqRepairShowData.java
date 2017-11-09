package com.hanqian.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.hanqian.form.EqRepairData;


/**
 * 设备维修比较数据转换类
 * @author clczp
 *
 */
public class EqRepairShowData {
	private String tkey;				//时间
	private String ekey;				//设备名称
	private String unit;				//单位
	private List<ChartData> clist;		//图形数据
	private HashMap<String,HashMap<String,EqRepairData>> tmap;	//按时间划分
	private HashMap<String,HashMap<String,EqRepairData>> emap;  //按设备名称划分
	
	public EqRepairShowData(List<EqRepairData> elist,String unit){
		this.unit = unit;		
		if (elist !=null) {
			ChartData cData=null;
			clist = new ArrayList<ChartData>();
			tmap = new HashMap<String, HashMap<String,EqRepairData>>();
			emap = new HashMap<String, HashMap<String,EqRepairData>>();
			for (EqRepairData obj:elist) {
				tkey = obj.getStattime();
				ekey = obj.getEqname();
				if (tmap.get(tkey)==null) {
					tmap.put(tkey, new HashMap<String, EqRepairData>());
				}
				if (tmap.get(tkey).get(ekey)==null) {
					tmap.get(tkey).put(ekey, obj);
				}
				
				if (emap.get(ekey)==null) {
					emap.put(ekey, new HashMap<String, EqRepairData>());
				}
				if (emap.get(ekey).get(tkey)==null) {
					emap.get(ekey).put(tkey, obj);
				}
				cData = new ChartData();
				cData.setRowKey(ekey);
				cData.setColumnKey(tkey);
				cData.setValue(obj.getRcount());
				cData.setMapTitle("");
				
				clist.add(cData);
			}
		}
	}
	
	/**
	 * 图形数据
	 * @return
	 */
	public  List<ChartData> getChartData(){
		return clist;
	}
	
	
	/**
	 * 表格数据
	 * @return
	 */
	public  TableData getTableData(){
		TableData obj = new TableData();
		
		List<String> keys=new ArrayList<String>();
		keys.add("时间");		
		if (emap != null) {
			for (Iterator<String> eit=emap.keySet().iterator();eit.hasNext();) {
				keys.add(eit.next()+"("+unit+")");
			}
		}
		obj.setKeys(keys);
		
		
		List<Object> re=null;
		EqRepairData er=null;
		List<Object> values=new ArrayList<Object>();
		if (tmap !=null) {
			for (Iterator<String> tit=tmap.keySet().iterator();tit.hasNext();) {
				tkey=tit.next();
				re = new ArrayList<Object>();
				re.add(tkey);
				for (Iterator<String> eit=emap.keySet().iterator();
										eit.hasNext();) {
					
					ekey=eit.next();
					er = tmap.get(tkey).get(ekey);
					if (er ==null) {
						er = new EqRepairData();
						er.setEqname(ekey);
						er.setRcount(0);
						er.setStattime(tkey);
					}
					re.add(er);					
				}
				values.add(re);
			}
		}
		
		obj.setValues(values);
		
		return obj;
	}
}


package com.hanqian.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hanqian.util.SysPower;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;


/**
 * 楼宇能耗数据转换类
 * @author clczp
 *
 */
public class BuildAtoShowDate {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(BuildAtoShowDate.class);
	private Short powerId;					//能源类型
	private double rate=1;					//倍率
	
	
	//记录排序
	class ComparatorValList implements Comparator {

		public int compare(Object obja, Object objb) {
			List<Object> obj1= (List<Object>)obja;
			List<Object> obj2= (List<Object>)objb;
			//比较
			int flag =0;
			if (obj1 !=null && obj2 !=null) {
				flag =((String)obj1.get(0)).compareTo((String)obj2.get(0));
			}
			
			return flag;
		}
	}
	
	public BuildAtoShowDate(Short powerId) {
		super();
		this.powerId = powerId;
		if (1==powerId) {
			rate =1000;
		}
		
	}
		

	/**
	 * 楼宇能耗图形数据
	 * @param rlist
	 * @param isWastage
	 * @param power
	 * @return
	 */
	public List<ChartData> getChartData(List rlist,String valKey,String valUnit,Short power,String unit){
		
		List<ChartData> data = null;
		if (null != rlist) {
			data = new ArrayList<ChartData>();
			ChartData rData=null;
			String stattime = null;
			String  id = power+"";
			String name = null;
			String powerName = SysPower.getName(power);
			HashMap rm = null;
			Double val = null;			

			for(int i=0;i<rlist.size();i++){
				rm = (HashMap)rlist.get(i);
				val = SysUtil.toDouble(rm.get(valKey)+"");
				stattime = rm.get("stattime")+"";
				name = rm.get("name")+"";
				rData = new ChartData();
				
				rData.setId(id);			
				rData.setValue(val);
				rData.setRowKey(name);				
				rData.setColumnKey(stattime);				
				rData.setMapTitle(name+stattime+powerName+"能源"+valUnit+"="+val+unit);
				data.add(rData);
			}
		}
		
		return data;
	}
	
	/**
	 * 吨标煤
	 * @param rlist
	 * @return
	 */
	public TableData getDBMTableDate(List clist){
		TableData data = new TableData();
//		
//		try {
//			//数据处理			
//			HashMap<String,Object> clm = new HashMap<String,Object>();//列
//			HashMap<String, HashMap<String, Double>> rwm = 
//				new HashMap<String, HashMap<String, Double>>(); // 记录			
//			
//			String stattime = null;		//时间轴
//			String buildName = null;	//字段轴
//			Double val = null;			//项
//			for (ChartData obj:clist) {
//				stattime = obj.getColumnKey().toString();
//				buildName = obj.getRowKey().toString();
//				val = obj.getValue().doubleValue();
//				
//				//保存字段
//				if (clm.get(stattime) == null) {
//					clm.put(stattime, true);
//				}
//				
//				//保存记录
//				if (rwm.get(stattime) == null) {
//					rwm.put(stattime, new HashMap<String,Double>());
//				}
//				if (rwm.get(stattime).get(buildName) == null) {
//					rwm.get(stattime).put(buildName, val);
//				}
//			}
//			
//			//重序字段
//			List<String> cllist = new ArrayList<String>();
//			for (Iterator<String> it = clm.keySet().iterator();it.hasNext();) {
//				cllist.add(it.next());
//			}
//			Collections.sort(cllist);
//			
//			//列
//			List<String> keys = new ArrayList<String>();
//			keys.add("时间");
//			for (String key:cllist) {
//				keys.add(key);
//			}
//			data.setKeys(keys);
//			
//			//记录
//			List<Object> values = new ArrayList<Object>();
//			List<Object> vls = new ArrayList<Object>();
//			String stime = null;
//			for (Iterator<String> it = rwm.keySet().iterator();it.hasNext();) {
//				stattime = it.next();
//				if (stattime.length()==4) {
//					stime = stattime+"年";
//				}else{
//					stime = Systime.DateToString(Systime.StringToDate(stattime, "yyyyMM"), "yyyy年MM月");
//				}
//				vls.add(stime);
//				for (String key:cllist) {
//					val = rwm.get(stattime).get(key);
//					vls.add(val);
//				}
//				values.add(vls);
//			}
//			//重序
//			Collections.sort(values,new ComparatorValList());			
//			data.setValues(values);
//			
//		} catch (Exception e) {
//			
//		}
		
		return data;
	}
	
	/**
	 * (楼宇)表格数据
	 * @param clist
	 * @param unit
	 * @param blist
	 * @param pCha
	 * @return
	 */
	public TableData getTableDate(List<ChartData> clist,String unit,
			List<ChartData> blist,Double pCha){
		
		TableData data = new TableData();
		
		HashMap<String,Double> bm = new HashMap<String, Double>();
		try {
			if (blist != null && pCha !=null) {
				String key = null;
				for (ChartData obj:blist) {
					key = obj.getColumnKey().toString();
					if (bm.get(key)==null) {
						bm.put(key, obj.getValue().doubleValue());
					}
				}
			}
		} catch (Exception e) {
			logg.error("BuildAtoShowDate-->getTableDate", e);
			// TODO: handle exception
		}
		
		try {
			//数据处理			
			HashMap<String,Object> clm = new HashMap<String,Object>();//列
			HashMap<String, HashMap<String, Double>> rwm = 
				new HashMap<String, HashMap<String, Double>>(); // 记录			
			
			String stattime = null;		//时间轴
			String buildName = null;	//字段轴
			Double val = null;			//项
			
			
			for (ChartData obj:clist) {
				stattime = obj.getColumnKey().toString();
				buildName = obj.getRowKey().toString();				
				val = obj.getValue().doubleValue();
				
				//保存字段
				if (clm.get(buildName) == null) {
					clm.put(buildName, true);
				}
				
				//保存记录
				if (rwm.get(stattime) == null) {
					rwm.put(stattime, new HashMap<String,Double>());
				}
				if (rwm.get(stattime).get(buildName) == null) {
					rwm.get(stattime).put(buildName, val);
				}
			}
			
			//重序字段
			List<String> cllist = new ArrayList<String>();
			for (Iterator<String> it = clm.keySet().iterator();it.hasNext();) {
				cllist.add(it.next());
			}
			Collections.sort(cllist);
			
			//列
			List<String> keys = new ArrayList<String>();
			keys.add("时间");
			if (unit == null || unit.length()<1) {
				unit = "";
			}else{
				unit = "("+unit+")";
			}
			for (String key:cllist) {
				keys.add(key+unit);				
			}
			data.setKeys(keys);
			
			//记录
			List<Object> values = new ArrayList<Object>();
			List<Object> vls = null;
			String stime = null;
			for (Iterator<String> it = rwm.keySet().iterator();it.hasNext();) {
				stattime = it.next();
				if (stattime.length()==4) {
					stime = stattime+"年";
				}else{
					stime = Systime.DateToString(Systime.StringToDate(stattime, "yyyyMM"), "yyyy年MM月");
				}
				vls =  new ArrayList<Object>();
				vls.add(stime);
				 
				if (blist != null && pCha !=null) {
					Double bval = null;
					for (String key:cllist) {
						val = rwm.get(stattime).get(key);
						bval = bm.get(stattime);
						if (bval != null) {
							if (val>bval+(pCha/100)) {
								vls.add("<b><font  color='#FF0000' title='高于标准偏差范围\n标准:"+bval+"' style='cursor:pointer'>"+val+"</font></b>");
							}else if(val+(pCha/100)<bval){
								vls.add("<b><font  color='#7030A0' title='低于标准偏差范围\n标准:"+bval+"' style='cursor:pointer'>"+val+"</font></b>");
							}else{
								vls.add(val);
							}
						}else{
							vls.add(val);
						}
					}
				}else{
					for (String key:cllist) {
						val = rwm.get(stattime).get(key);					
						vls.add(val);
					}
				}
				values.add(vls);
			}
			//重序
			Collections.sort(values,new ComparatorValList());			
			data.setValues(values);
			
		} catch (Exception e) {
			logg.error("BuildAtoShowDate-->getTableDate", e);
		}
		
		
		return data;
	}
	
	
	
}


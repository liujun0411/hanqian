package com.hanqian.common;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hanqian.pojo.DbStatHospital;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.SysPower;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;


/**
 * 医院能耗数据转换类
 * @author clczp
 *
 */
public class HosAtoShowDate {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(HosAtoShowDate.class);
	private List<DbStatHospital> list;						//能耗数据
	private DecimalFormat fm = new DecimalFormat("#0.00");	//数据格式化
	private Short powerId;					//能源类型
	private double rate=1;					//倍率
	
	class ComparatorChartData implements Comparator {

		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			ChartData menu1 = (ChartData) o1;
			ChartData menu2 = (ChartData) o2;
			// 比较menuid
			int flag =0;
			if (null != menu1.getColumnKey() && null !=menu2.getColumnKey()) {
				flag = menu1.getColumnKey().compareTo(menu2.getColumnKey());
			}else{
				flag = menu1.getColumnKey().compareTo(menu2.getColumnKey());
			}
			
			return flag;
		}
	}
	
	
	private HosAtoShowDate(){
		super();
	}
	
	public HosAtoShowDate(List<DbStatHospital> list,Short powerId) {
		super();
		this.list = list;
		this.powerId = powerId;
		if (1==powerId) {
			rate =1000;
		}
		
	}
	public HosAtoShowDate(Short powerId) {
		super();
		this.powerId = powerId;
		if (1==powerId) {
			rate =1000;
		}
		
	}
	
	
	@Deprecated
	private String getPowerName(Short powid){
		String name="";
		if (1==powid) {
			name="水";
		}else if (2==powid) {
			name="电";
		}else if (3==powid) {
			name="油";
		}else if (4==powid) {
			name="气(医用)";
		}else if (5==powid) {
			name="气(燃气)";
		}
		return name;
	}
	
	/**
	 * 图形数据
	 * @return
	 */
	@Deprecated
	public List<ChartData> getChartData(boolean isWastage,ETimeStep step){
		
		List<ChartData> data = null;
		if (null != list) {
			data = new ArrayList<ChartData>();
			ChartData rData=null;
			if (ETimeStep.year == step) {
				HashMap<String,Double> m = new HashMap<String,Double>();
				
				//统计
				String key=null;
				Short  power=null;
				for(DbStatHospital obj:list){
					key = obj.getStattime().substring(0, 4);
					if (null == m.get(key)) {
						if (isWastage) {
							m.put(key, obj.getWastage());
						}else{
							m.put(key, obj.getConvert());
						}
					}else{
						if (isWastage) {
							m.put(key, obj.getWastage()+m.get(key));
						}else{
							m.put(key, obj.getConvert()+m.get(key));
						}
						
					}
					if (null == power) {
						power = obj.getPower();
					}
				}
				
				//梆定
				for (Iterator it=m.keySet().iterator();it.hasNext();) {
					key = (String)it.next();
					rData = new ChartData();
					rData.setValue(m.get(key));
					
					if (isWastage) {
						rData.setRowKey(this.getPowerName(power)+"使用量");
					}else{
						rData.setRowKey(this.getPowerName(power)+"使用金额");
					}
					rData.setColumnKey(key);
					
					data.add(rData);
				}
				ComparatorChartData comparator=new ComparatorChartData();
				Collections.sort(data, comparator);
			}else{
				for(DbStatHospital obj:list){
					rData = new ChartData();
					rData.setId(obj.getId());
					if (isWastage) {
						rData.setValue(obj.getWastage());
						rData.setRowKey(this.getPowerName(obj.getPower())+"使用量");
					}else{
						rData.setValue(obj.getConvert());
						rData.setRowKey(this.getPowerName(obj.getPower())+"使用金额");
					}
					rData.setColumnKey(obj.getStattime());
					rData.setMapTitle("提示信息");
					
					data.add(rData);
				}
			}
		}
		
		return data;
	}
	

	/**
	 * 医院能耗图形数据
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
			String powerName = SysPower.getName(power);
			HashMap rm = null;
			Double val = null;			

			for(int i=0;i<rlist.size();i++){
				rm = (HashMap)rlist.get(i);
				val = SysUtil.toDouble(rm.get(valKey)+"");
				stattime = rm.get("stattime")+"";
				rData = new ChartData();
				
				rData.setId(id);			
				rData.setValue(val);
				rData.setRowKey(powerName + valUnit);				
				rData.setColumnKey(stattime);				
				rData.setMapTitle(stattime+powerName+"能源"+ valUnit+"="+val+unit);
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
	public TableData getTableDate(List rlist){
		TableData data = new TableData();		
		
		// 列
		List<String> keys = new ArrayList<String>();
		keys.add("时间");
		keys.add("总用量");
		keys.add("折算金额(元)");
		keys.add("吨标煤");
		data.setKeys(keys);
			
		// 行<记录>
		double sumareaW = 0; // 合计用量
		double sumareaC = 0; // 合计折算金额
		double sumareaD = 0; // 合计吨标煤
		List<Object> rclist = null;

		String keyTime = null;
		String keyW = "wastage";
		String keyC = "convert";
		String keyD = "dbm";
		Double w = 0D;
		Double c = 0D;
		Double d = 0D;

		List<Object> values = new ArrayList<Object>();
		HashMap rm = null;
				
		for (int i = 0; i < rlist.size(); i++) {
			rm = (HashMap) rlist.get(i);
			w = SysUtil.toDouble(rm.get(keyW) + "");
			c = SysUtil.toDouble(rm.get(keyC) + "");
			d = SysUtil.toDouble(rm.get(keyD) + "");

			rclist = new ArrayList<Object>();

			keyTime = rm.get("stattime") + "";
			if (keyTime.length() == 4) {
				keyTime = Systime.DateToString((Systime.StringToDate(keyTime,
						"yyyy")), "yyyy年");
			} else {
				keyTime = Systime.DateToString((Systime.StringToDate(keyTime,
						"yyyyMM")), "yyyy年MM月");
			}
			rclist.add(keyTime);// 时间
			rclist.add(w); // 使用量
			rclist.add(c); // 折算金额
			rclist.add(d); // 吨标煤
			values.add(rclist);

			sumareaW += w;
			sumareaC += c;
			sumareaD += d;
		}
		List<Object> stmp = new ArrayList<Object>();
		stmp.add("合计");
		stmp.add(sumareaW);
		stmp.add(sumareaC);
		stmp.add(sumareaD);
		values.add(stmp);

		data.setValues(values);
		return data;
	}
	
	/**
	 * 表格数据
	 * @param lists 使用量
	 * @param listf 使用金 额
	 * @return
	 */
	public TableData getTableDate(List<ChartData> lists,List<ChartData> listf){
		TableData data = new TableData();
		
		try {
			//列
			List<String> keys = new ArrayList<String>();
			keys.add("时间");
			keys.add("使用量("+SysPower.getUnitName(powerId)+")");
			keys.add("折算金额(元)");
			data.setKeys(keys);
			
				//行<记录>
				double sumarea =0;	//总计
				double sumarea2 =0; //总计二
				List<Object> recordlist =null;
				
				String stime=null;
				List<Object> values= new ArrayList<Object>();				
				for (int i = 0; i < lists.size(); i++) {
					recordlist =new ArrayList<Object>();					
					ChartData obj = lists.get(i);
					stime = obj.getColumnKey()+"";
					if (stime.length()==4) {
						stime = Systime.DateToString((Systime.StringToDate(stime, "yyyy")), "yyyy年");
					}else{
						stime = Systime.DateToString((Systime.StringToDate(stime, "yyyyMM")), "yyyy年MM月");
					}
					recordlist.add(stime);	//时间				
					recordlist.add(fm.format(obj.getValue().doubleValue()));//使用量
					recordlist.add(fm.format(listf.get(i).getValue()));//使用金额
					values.add(recordlist);
					
					sumarea += obj.getValue().doubleValue();
					sumarea2 += listf.get(i).getValue().doubleValue();
				}
				List<Object> stmp =new ArrayList<Object>();
				stmp.add("合计");
				stmp.add(SysUtil.toDouble(fm.format(sumarea)));
				stmp.add(SysUtil.toDouble(fm.format(sumarea2)));
				
				
				values.add(stmp);
		
			data.setValues(values);
		} catch (Exception e) {
logg.error("HosAtoShowDate-->getTableDate", e);
			// TODO: handle exception
		}
		
		
		return data;
	}
}


package com.hanqian.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hanqian.util.SysUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-12-10
 * @see
 */
public class ElectChartData {

	public ElectChartData() {

	}
	
	/**
	 * 格式化三相电流、综合电流的展示
	 * @param rlist
	 * @param valKey
	 * @param valUnit
	 * @param unit
	 * @param flag
	 * @param falgSA
	 * @return
	 */
	public List<ChartData> getChartData(List rlist, String valKey,
			String valUnit, String unit,String flag,String falgSA) {
		List<ChartData> data = null;
		if (null != rlist) {
			data = new ArrayList<ChartData>();
			ChartData rData = null;
			String stattime = null;
			String id = flag+"";
			String name = null;
			Map rm = null;
			Double val = null;
            String substringRecordtimes=null;
			for (int i = 0; i < rlist.size(); i++) {
				rm = (Map) rlist.get(i);
				val = SysUtil.toDouble(rm.get(valKey) + "");
				//stattime=String.valueOf(i+1);
				substringRecordtimes= rm.get("recordtimes") + "";
				stattime = substringRecordtimes.substring(substringRecordtimes.length()-4,substringRecordtimes.length()-2)+"日"+substringRecordtimes.substring(substringRecordtimes.length()-2, substringRecordtimes.length())+"时";
				//三相电流显示
				if(("3A").equals(falgSA)){
					name = rm.get("point_name") + "";
				}//综合电流显示
				else if(("ZH").equals(falgSA)){
					name = rm.get("descr") + "";
				}
				rData = new ChartData();
				rData.setId(id);
				rData.setValue(val);
				rData.setRowKey(name);
				rData.setColumnKey(stattime);
				rData.setMapTitle(name + stattime + "能源" + valUnit + "=" + val
						+ unit);
				data.add(rData);
			}
		}
		return data;
	}
}

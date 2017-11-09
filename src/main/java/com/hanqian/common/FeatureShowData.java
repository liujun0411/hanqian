package com.hanqian.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.hanqian.form.FeatureData;


/**
 * 特征区域数据转换类
 * @author clczp
 *
 */
public class FeatureShowData {
	
	
	/**
	 * 表格数据
	 * @param flist
	 * @return
	 */
	public static TableData getTableData(List<FeatureData> flist,String unit){
		TableData tData=null;
		if (flist !=null) {
			tData =new TableData();
			String yt=null;
			String key=null;			
			List<Object> re=null;
			List<String> keys= new ArrayList<String>();
			List<Object> values=new ArrayList<Object>();			
			HashMap<String,Object> ytMap=new HashMap<String, Object>();
			HashMap<String,HashMap<String,FeatureData>> tMap= new HashMap<String, 
													HashMap<String,FeatureData>>();
			
			keys.add("时间");
			for (FeatureData obj:flist) {
				key=obj.getStattime();
				yt=obj.getName();
				
				if (ytMap.get(yt)==null) {
					ytMap.put(yt, yt);					
				}
				if (tMap.get(key) == null) {
					tMap.put(key, new HashMap<String,FeatureData>());
				}
				if (tMap.get(key).get(yt)==null) {
					tMap.get(key).put(yt, obj);
				}
				
			}
			//按区域排序
			Object[] ykey=ytMap.keySet().toArray();
			Arrays.sort(ykey);
			for (int i = 0; i < ykey.length; i++) {
				keys.add(ykey[i]+"("+unit+")");
			}
			tData.setKeys(keys);
			
			//按时间排序
			Object[] tkey =tMap.keySet().toArray();
			Arrays.sort(tkey);
			for (int i=0;i<tkey.length;i++) {				
				key=(String)tkey[i];
				re = new ArrayList<Object>();
				re.add(key);
				for (int j = 0; j < ykey.length; j++) {
					yt=(String)ykey[j];
					re.add(tMap.get(key).get(yt));
				}
				values.add(re);
			}
			tData.setValues(values);
		}
		
		return tData;
	}
}

package com.hanqian.common;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hanqian.business.HospInfoBusiness;
import com.hanqian.util.SysUtil;

/**
 * 数据转换
 * @author clczp
 *
 */
public class CMyListData {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(CMyListData.class);
	private List<AreaRatioData> oldlist;		//实际数据
	private List<AreaRatioBData> baselist;		//标准数据
	private double sumArea=0D;					//总面积
	private double piancha=0D;					//偏差值
	private String unitname=null;				//单元名
	private DecimalFormat fm = new DecimalFormat("#0.00");	//数据格式化
	private HospInfoBusiness hospInfoDAO = new HospInfoBusiness();
	
	/**
	 * 获得实际数据Map
	 * @return
	 */
	private Map<String,AreaRatioData> getActualDataMap(){
		HashMap<String,AreaRatioData> actualM = new HashMap<String,AreaRatioData>();
		sumArea=0D;
		for (AreaRatioData obj:oldlist) {
			sumArea += obj.getDatavlue();
			if (null == unitname) {
				unitname = obj.getUnitname();
			}
			actualM.put(obj.getId(), obj);
		}
		sumArea=SysUtil.toDouble(fm.format(sumArea));
		return actualM;
	}
	/**
	 * 获得标准数据Map
	 * @param key
	 * @return
	 */
	private Map<String,AreaRatioBData> getStandardDataMap(){
		HashMap<String,AreaRatioBData> standardMap = new HashMap<String,AreaRatioBData>();
		for (AreaRatioBData obj:baselist) {	
			standardMap.put(obj.getId(), obj);
		}
		return standardMap;
	}
	
	/**
	 * 偏差计算
	 * @param comChart	比较符
	 * @param value		实际值
	 * @param comValue	标准值
	 * @return
	 */
	private String getPianchaStr(String comChart,double value,double comValue){		
		String title="";
//		if ("<=".equals(comChart)) {
//			piancha=value-comValue;
//			if (value>comValue) {
//				title ="偏差:"+SysUtil.toDouble(fm.format(piancha))+"%";
//			}
//			
//		}else if (">=".equals(comChart)) {
//			piancha=comValue-value;
//			if (value<comValue) {
//				title ="偏差:-"+SysUtil.toDouble(fm.format(piancha))+"%";
//			}			
//		}else{
			piancha=value-comValue;
			title ="偏差:";			
			title +=SysUtil.toDouble(fm.format(piancha))+"%";
//		}		
		return title;
	}
	/**
	 * 医院别名
	 * @param hospid 医院Id
	 * @return
	 */
	private String getHospAliasName(String hospid){
		return hospInfoDAO.findHospInfoForId(Integer.parseInt(hospid)).getShortName();
	}
	
	public CMyListData(){}
	
	public CMyListData(List<AreaRatioData> oldlist,
			List<AreaRatioBData> baselist) {
		super();
		this.oldlist = oldlist;
		this.baselist = baselist;
	}

	
	/**
	 * 多医院单区域面积比例
	 * @return
	 */
	public List<ChartData> getManyHospOneUseChartData(String useId,String useName){
		List<ChartData> list =new ArrayList<ChartData>();		
		
		String hospName=null;		
		AreaRatioBData bobj=this.getStandardDataMap().get(useId);//标准
		
		//添加基准
		ChartData datab= new ChartData();
		list.add(datab);
		
		for (AreaRatioData obj:oldlist) {			
			
			hospName = this.getHospAliasName(obj.getUnitname().trim());
									
			ChartData data= new ChartData();
			data.setId(useId);					//编号 
			data.setValue(obj.getDatavlue());	//比例
			data.setRowKey(useName);			//区域		
			data.setColumnKey(hospName);		//医院
			data.setMapTitle(hospName+"("+useName+"区域)\n"+
					"医院总面积:"+obj.getNote2()+"㎡\n"+
					"面积:"+obj.getNote1()+"㎡\n"+
					"实际比例:"+obj.getDatavlue()+"%\n"+
					"标准比例:"+bobj.getComparechar().trim()+bobj.getDatavlue()+"%\n"+
					this.getPianchaStr(bobj.getComparechar().trim(), obj.getDatavlue(), bobj.getDatavlue()));
			
			list.add(data);
		}
		
		//标准
		datab.setId(useId);
		datab.setValue(bobj.getDatavlue());
		datab.setRowKey(useName);
		datab.setColumnKey("标准");
		datab.setMapTitle(useName+"区域\n标准:"+bobj.getComparechar().trim()+bobj.getDatavlue()+"%");
		return list;
	}
	

	/**
	 * 各医院面积情况
	 * @return
	 */
	public List<ChartData> getManyHospAreaChartData(){
		List<ChartData> list =new ArrayList<ChartData>();
		String str=null;
		
		for (AreaRatioData obj:oldlist) {
			ChartData data= new ChartData();
			str = this.getHospAliasName(obj.getUnitname().trim());
			
			data.setId(obj.getId());	
			data.setValue(obj.getDatavlue());
			data.setRowKey(obj.getName());
			data.setColumnKey(str);	
			
			data.setMapTitle(str+"\n"+obj.getName()+":"+obj.getDatavlue()+"㎡");
			list.add(data);
		}
		
		return list;
	}
	/**
	 * 获得区域面积比例 图形数据
	 * @return
	 */
	public List<ChartData> getBiliChartData(){
		List<ChartData> list =new ArrayList<ChartData>();		
		if (null !=oldlist) {
			Map<String,AreaRatioData> actualM = getActualDataMap();//实际数据处理
			String comChart=null;	//比较符
			double comValue=0D;		//比较值
			double value=0D;		//实际值
			ChartData sData=null;	//标准数据
			ChartData aData=null;	//实际数据
			String str=null;
			
			//遍历标准数据
			for (AreaRatioBData obj:baselist) {				
				AreaRatioData oldobj= actualM.get(obj.getId());	//原始数据
				comChart = obj.getComparechar().trim();			//比较符
				comValue = obj.getDatavlue()>0?obj.getDatavlue():obj.getDatavlue()*100;//比较值
									
				//标准				
				sData= new ChartData();
				sData.setId(obj.getId().toString());
				sData.setValue(obj.getDatavlue());
				sData.setRowKey("标准");
				sData.setColumnKey(obj.getName());
				sData.setMapTitle(obj.getName()+"标准\n"+"标准:"+comChart+comValue+"%");			
				list.add(sData);
				
				//实际
				if (null == oldobj) {
					oldobj =new AreaRatioData();
					value =0D;
				}else{
					value=SysUtil.toDouble(fm.format((oldobj.getDatavlue()/sumArea)*100));				
				}
				str = this.getPianchaStr(comChart, value, comValue);
				str = (str.length()>0?str+"\n":"");
				
				aData= new ChartData();				
				aData.setId(obj.getId().toString());
				aData.setValue(value);
				aData.setRowKey(unitname);
				aData.setColumnKey(obj.getName());				
				aData.setMapTitle(obj.getName()+"区域\n"+
						"占比:"+value+"%\n"+
						"标准:"+comChart+comValue+"%\n"+
						str+
						"面积:"+oldobj.getDatavlue()+"㎡\n"+	
						"医院总面积:"+sumArea+"㎡"
						);			
				list.add(aData);
			}
		}
		
		return list;
	}
	
	
	
	
	/**
	 * 单医院各区域面积比例
	 * @return
	 */
	public List<ChartData> getOneHospAreaChartData(){
		List<ChartData> list =new ArrayList<ChartData>();
		
		if (null !=oldlist) {
			Map<String,AreaRatioData> actualM = getActualDataMap();//实际数据处理
			String comChart=null;	//比较符
			double comValue=0D;		//比较值
			double value=0D;		//实际值
			ChartData aData=null;	//实际数据
			
			//转换成图表数据
			for (AreaRatioBData obj:baselist) {				
				AreaRatioData oldobj= actualM.get(obj.getId());	//原始数据
				comChart = obj.getComparechar().trim();			//比较符
				comValue = obj.getDatavlue()>0?obj.getDatavlue():obj.getDatavlue()*100;	//比较值
				
				//实际				
				if (null == oldobj) {
					oldobj =new AreaRatioData();				
				}else{
					value=SysUtil.toDouble(fm.format((oldobj.getDatavlue()/sumArea)*100));				
				}
				
				aData= new ChartData();
				aData.setId(obj.getId().toString());
				aData.setValue(oldobj.getDatavlue());
				aData.setRowKey(unitname);
				aData.setColumnKey(obj.getName());
				aData.setMapTitle(obj.getName()+"区域\n"+
						"面积:"+oldobj.getDatavlue()+"㎡\n"+
						"总面积:"+sumArea+"㎡\n"+						
						"占比:"+value+"%\n"+
						"标准:"+comChart+comValue+"%\n"+
						this.getPianchaStr(comChart, value, comValue));			
				list.add(aData);
			}
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 图表数据
	 * (基于标准数据对比)
	 * @return
	 */
	public List<ChartData> getChartListData(){
		List<ChartData> list =new ArrayList<ChartData>();
		
		
		return list;
	}
	
	/**
	 * 表格数据
	 * @return
	 */
	public TableData  getTableListData(List<ChartData> clist){
		TableData data = null;
		
		return data;
	}
	
	
	
	/**
	 * 获得单医院(表格数据 {面积比例,区域面积})
	 * @param clist		比例
	 * @param clist2	面积
	 * @return
	 */
	public TableData  getOneHospitalTableData(List<ChartData> listBili,List<ChartData> listArea){
		TableData data = new TableData();		
		
		try {
			//列
			List<String> keys = new ArrayList<String>();
			keys.add("区域");
			keys.add("面积"+"(㎡)");
			keys.add("比例");
			keys.add("标准");
			keys.add("偏差");
			data.setKeys(keys);
			
			//行
			List<Object> values= new ArrayList<Object>();
			//汇总
			double sumarea=0;
			if (null !=listBili && null !=listArea) {
				String comChart=null;	//比较符
				double comValue=0D;		//比较值
				double value=0D;		//实际值	
				ChartData area=null;	//面积
				List<Object> recordlist=null;	//记录
				Map<String,AreaRatioBData>  bMap=getStandardDataMap();//标准数据
				
				for (int i=0,j=0; i < listBili.size(); i++) {
					ChartData bili = listBili.get(i);		//遍历比例数据 （移除标准值）			
					if (bili.getRowKey().toString().equals("标准")) {
						continue;
					}
					area = listArea.get(j);		//遍历面积数据					
					recordlist =new ArrayList<Object>();
					AreaRatioBData  baseobj = bMap.get(area.getId());	//标准
					value = bili.getValue().doubleValue();				//实际值
					comValue =baseobj.getDatavlue();					//标准值
					comChart = baseobj.getComparechar().trim();			//比较符
					
					
					recordlist.add(area.getColumnKey());	//区域
					recordlist.add(area.getValue());	//面积					
					recordlist.add(bili.getValue()+"%");	//比例					
					recordlist.add(baseobj.getComparechar().trim()+baseobj.getDatavlue()+"%");	//标准									
					recordlist.add("<font color='#FF0000'>"+this.getPianchaStr(comChart, value, comValue).replace("偏差:", "")+"</font>");//偏差
					values.add(recordlist);
					
					sumarea += area.getValue().doubleValue();//总计
					j++;
				}
				
				//添加总计
				List<Object> stmp =new ArrayList<Object>();
				stmp.add("总计");
				stmp.add(SysUtil.toDouble(fm.format(sumarea)));
				stmp.add("");
				stmp.add("");
				stmp.add("");
				
				values.add(stmp);
			}
			data.setValues(values);
		} catch (Exception e) {
			logg.error("CMyListData-->getOneHospitalTableData", e);
			// TODO: handle exception
		}
		
		
		return data;
	}
	
	/**
	 * 表格数据(医院总面积)
	 * @param listArea	面积
	 * @return
	 */
	public TableData  getManyHospTableData(List<ChartData> listArea){
		TableData data = new TableData();
		
		try {
			//列
			List<String> keys = new ArrayList<String>();
			keys.add("医院");
			keys.add("面积");					
			data.setKeys(keys);
			
				//行<记录>
				double sumarea =0;	//总计
				List<Object> recordlist =null;
				
				
				List<Object> values= new ArrayList<Object>();				
				for (int i = 0; i < listArea.size(); i++) {
					recordlist =new ArrayList<Object>();					
					ChartData obj = listArea.get(i);
					
					recordlist.add(obj.getColumnKey());	//医院					
					recordlist.add(obj.getValue()+"㎡");	//面积
					values.add(recordlist);
					
					sumarea += obj.getValue().doubleValue();
				}
				List<Object> stmp =new ArrayList<Object>();
				stmp.add("合计");
				stmp.add(SysUtil.toDouble(fm.format(sumarea))+"㎡");
				
				
				values.add(stmp);
		
			data.setValues(values);
		} catch (Exception e) {
			logg.error("CMyListData-->getManyHospTableData", e);
			// TODO: handle exception
		}
		
		
		return data;
	}
	
	/**
	 * 表格数据(各医院某区域比例)
	 * @param clist
	 * @return
	 */
	public TableData  getManyHospOneUseTableData(List<ChartData> listBiLi,String useId){
		TableData data = new TableData();
		
		try {
			//列
			List<String> keys = new ArrayList<String>();
			keys.add("医院");
			keys.add("比例");
			keys.add("标准");
			keys.add("偏差");
			data.setKeys(keys);
			
			//行
			List<Object> values= new ArrayList<Object>();
			ChartData bili=null;		//比例
			List<Object> recordlist =null;		//记录
			AreaRatioBData bData=this.getStandardDataMap().get(useId);	//标准			
			double value=0D;
			double comValue=bData.getDatavlue();
			String comChart=bData.getComparechar().trim();
			
			for (int i = 0; i < listBiLi.size(); i++) {
					bili = listBiLi.get(i);
					if ("标准".equals(bili.getColumnKey().toString())) {
						continue;
					}
										
					recordlist =new ArrayList<Object>();
					value = bili.getValue().doubleValue();
					
					recordlist.add(bili.getColumnKey().toString());//医院
					recordlist.add(value+"%");//比例
					recordlist.add(comChart+comValue+"%");//标准
					recordlist.add("<font color='#FF0000'>"+this.getPianchaStr(comChart, value, comValue)+"</font>");//偏差					
					values.add(recordlist);
				}
		
			data.setValues(values);
		} catch (Exception e) {
			logg.error("CMyListData-->getManyHospOneUseTableData", e);
			// TODO: handle exception
		}
		
		
		return data;
	}
	
	
	public List<AreaRatioData> getOldlist() {
		return oldlist;
	}

	public List<AreaRatioBData> getBaselist() {
		return baselist;
	}

	public void setOldlist(List<AreaRatioData> oldlist) {
		this.oldlist = oldlist;
	}

	public void setBaselist(List<AreaRatioBData> baselist) {
		this.baselist = baselist;
	}
}

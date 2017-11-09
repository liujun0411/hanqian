package com.hanqian.business;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanqian.common.CMyShowData;
import com.hanqian.common.ChartData;
import com.hanqian.common.HosAtoShowDate;
import com.hanqian.common.HosLineChart;
import com.hanqian.common.WeatherRate;
import com.hanqian.form.VOStatCondition;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbStatHospital;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysPower;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;


/**
 * 医院数据分析
 * @author clczp
 *
 */
@Service("HospDataAnalysisBusiness")
public class HospDataAnalysisBusiness   extends BaseBusiness{
	/**
	 * log4g日志
	 */
	private static final Logger logg = Logger.getLogger(HospDataAnalysisBusiness.class);
	//private static final Log logg = LogFactory.getLog(HospDataAnalysisBusiness.class);
//	@Resource    
//	private HospDataAnalysisMgr sthosMgr;	//医院数据Mgr
//	@Resource 
//	private HospInfoMgr  hosMgr;		//医院Mgr
	@Autowired
	private WeatherBusiness weatherBusiness;				//天气情况
	
	/**
	 * 请求信息处理
	 * @param vs
	 * @return
	 */
	private VOStatCondition getVOStHospDA(VOStatCondition  vs,DbUsers dbusers){		
		if (logg.isDebugEnabled()) {
			logg.debug("进入FeatureBusiness.HospDataAnalysisBusiness,对象VOStatCondition:"+vs+ " DbUsers:"+dbusers );	
		}
		if (null == vs) {
			vs= new VOStatCondition();
		}
		
		//初始化时间
		if (SysUtil.isNull(vs.getStartDate())  &&  SysUtil.isNull(vs.getEndDate())) {
			if (null == vs.getTimeStep() || vs.getTimeStep()==ETimeStep.month) {
				vs.setTimeStep(ETimeStep.month);
				//最近一年
				Date d= new Date();				
				vs.setEndDate(Systime.DateToString(d, "yyyyMM"));
				d.setYear(d.getYear()-1);
				d.setMonth(d.getMonth()+1);
				vs.setStartDate(Systime.DateToString(d, "yyyyMM"));
			}else{
				vs.setTimeStep(ETimeStep.year);
				//最近5年
				Date d= new Date();
				vs.setEndDate(Systime.DateToString(d, "yyyy"));
				d.setYear(d.getYear()-5);
				vs.setStartDate(Systime.DateToString(d, "yyyy"));
			}
		}
		
		//初始化能源类型
		if (SysUtil.isNull(vs.getPower()+"")) {
			vs.setPower((short) 2);
		}
		//初始化医院
		if (SysUtil.isNull(vs.getHospid())) {
//			Hibernate.initialize("");
//			DbHospInfo hosp=
//				(new ArrayList<DbUserHosp>(dbusers.getDbUserHosps()))
//			.get(0).getDbHospInfo() ;
			
			
			//Map hospInfoMap=hosMgr.findHospInfoBySql(dbusers);
			Map hospInfoMap=null;
			Map map = new HashMap();
			map.put("seq", dbusers.getSeq());
			RetCode rc = this.getData("", map);
			List lst=(List)rc.getObj();
			if(lst!=null){
				hospInfoMap = (Map)lst.get(0);
			}
			
			
			
			
////			System.out.println(hospInfoMap.get("hosp_name"));
//			System.out.println(" 医院分析  医院信息 "+hosp);
//			.get(0).getDbHospInfo() ;
			if (hospInfoMap !=null) {
				vs.setHospid(hospInfoMap.get("seq_hosp").toString());
			}else{
				Map hospInfoMap1=null;
				Map map1 = new HashMap();
				map1.put("seq", dbusers.getSeq());
				RetCode rc1 = this.getData("", map);
				List lst1=(List)rc1.getObj();
				if(lst1!=null){
					hospInfoMap1 = (Map)lst1.get(0);
				}
				//vs.setHospid(hosMgr.findHospInfoBySql(dbusers).get("seq_hosp").toString());
				vs.setHospid(hospInfoMap1.get("seq_hosp").toString());
			}
		}
				
		return vs;
	}
	
	/**
	 * 医院能耗(含吨标煤)
	 * @param vosth
	 * @param savePath
	 * @param showPath
	 * @param dbusers
	 * @return
	 */
	public RetCode findAnalysisZH(VOStatCondition  voobj,String savePath,
			String showPath,DbUsers dbusers){
		if (logg.isDebugEnabled()) {
			logg.debug("进入FeatureBusiness.findAnalysisZH,对象VOStatCondition:"+voobj+ " DbUsers:"+dbusers );	
		}
		CMyShowData obj = null;
		Log log = LogFactory.getLog(HospDataAnalysisBusiness.class);
		
		RetCode rt = new RetCode();
		try {			
			//查询数据
			VOStatCondition vs = this.getVOStHospDA(voobj,dbusers);
			
			List dblist=this.findStatHospital(vs.getHospid(),
					vs.getPower(),vs.getStartDate(),vs.getEndDate(),vs.getTimeStep());
			if (null != dblist ) {
				obj = new CMyShowData();
				//String hosName=hosMgr.findNameById(vs.getHospid());
				String hosName="";
				int seq = Integer.valueOf(vs.getHospid());
				DbHospInfo dbHospInfo =sqlSession.selectOne("findNameByIdHospDataAnalysis", seq);
				hosName = dbHospInfo.getHospName();
				
				
				
				String title = hosName+SysPower.getName(vs.getPower())+"能源";
				
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				HosAtoShowDate  toDate= new HosAtoShowDate(vs.getPower());				
				
				String valKey = null;
				String valUntit = null;
				if (0 != vs.getPower()) {
					//绘图使用量
					valKey = "wastage";
					valUntit = "使用量";
					List<ChartData> listf= toDate.getChartData(dblist,valKey,
							valUntit,vs.getPower(),SysPower.getUnitEN(vs.getPower()));
					
					//添加倍率化后的气温
					List<ChartData> wlist = null;
					boolean addRate = false;
					if (vs.getTimeStep().equals(ETimeStep.month) && vs.getPower()!=0) {
						float rate = WeatherRate.findReate(listf);
						wlist =  weatherBusiness.findAvgWeatherRate("上海",vs.getStartDate(),
								vs.getEndDate(), rate);
						addRate = listf.addAll(wlist);
					}
					
					
					String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
					String smPath= savePath +File.separator+"jfreechartNxiao"+ name+".png";		//生成图片名	
					String shPath = showPath+File.separator+"jfreechartNxiao"+ name+".png";		//显示图片路径
					shPath=SysUtil.replacePath(shPath);
					smPath=SysUtil.replacePath(smPath);

					if(this.saveDraw(listf, info, 650, 400, smPath, 
							title+valUntit,SysPower.getUnitEN(vs.getPower())) ){
						obj.setMyDrawF(shPath.replaceAll("\\\\", "/"));
						obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
					}
					
					//移除倍化后的气温
					if (addRate) {
						listf.removeAll(wlist);
					}
					
					//绘图折算金额
					valKey = "convert";
					valUntit = "折算金额";
					List<ChartData> lists= toDate.getChartData(dblist,
							valKey,valUntit,vs.getPower(),"元");
					name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
					smPath= savePath +File.separator+"jfreechartNxiao"+ name+"2.png";			//生成图片名				
					shPath = showPath+"jfreechartNxiao"+ name+"2.png";		//显示图片路径
//					shPath = shPath.replace("\\", "/");
					shPath=SysUtil.replacePath(shPath);
					if(this.saveDraw(lists, info, 650, 400, smPath, title+valUntit,"元") ){
						obj.setMyDrawS(shPath.replaceAll("\\\\", "/"));
						obj.setUseMapS(ChartUtilities.getImageMap("useMapS", info));
					}
					
					//表格数据
					obj.setMytable(toDate.getTableDate(listf,lists));
					obj.getMytable().setTitle(title+"能耗信息");
				}else{
					//绘图吨标煤使用量
					valKey = "dbm";
					valUntit = "使用量";
					List<ChartData> listf= toDate.getChartData(dblist,valKey,
							valUntit,vs.getPower(),"吨标煤");
					String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
					String smPath= savePath +File.separator+"jfreechartNxiao"+ name+".png";		//生成图片名				
					String shPath = showPath+"jfreechartNxiao"+ name+".png";		//显示图片路径
//					shPath = shPath.replace("\\", "/");	
					shPath=SysUtil.replacePath(shPath);
					if(this.saveDraw(listf, info, 650, 400, smPath, 
							title+valUntit,SysPower.getUnitEN(vs.getPower())) ){
						
						obj.setMyDrawF(shPath.replaceAll("\\\\", "/"));
						obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
					}
					
					//表格数据
					obj.setMytable(toDate.getTableDate(dblist));
					obj.getMytable().setTitle(title+"能耗信息");
				}
				
				rt.setObj(new Object[]{obj,vs});
			}else{
				rt.setCode(1002);
				rt.setDesc("暂无数据！");								
			}
		} catch (Exception e) {
			logg.error("HospDataAnalysisBusiness-->findAnalysisZH", e);
			
			// TODO: handle exception
			rt.setCode(1004);
			rt.setDesc("操作失败！");	
			e.printStackTrace();
			log.debug(e);
		}
		
		return rt;
	}
	
	
	public Map getFindAnalysisZHSql() {
		return PerformSQLUtil.get("findNameByIdHospDataAnalysis", this);
	}

	
	/**
	 * 医院能耗(asc)
	 * 能耗量wastage,价值convert[,吨标煤dbm],时间stattime
	 * @param hospid	医院Id
	 * @param power		能源类型
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param step		月/年度
	 * @return
	 */
	public List findStatHospital(String hospid,Short power,String startDate,String endDate,ETimeStep step){
		if (endDate != null && endDate.length()==4) {
			Date d = new Date();
			d.setYear(Systime.StringToDate(endDate, "yyyy").getYear()+1);
			endDate = Systime.DateToString(d, "yyyy");
		}
		if (SysUtil.isNull(startDate)) {
			startDate = "0";
		}
		if (SysUtil.isNull(endDate)) {
			endDate = "999999";
		}
		
		boolean isYear = ETimeStep.month.equals(step)?false:true;
		
		if (0 == power) {
			//综合
			//return findZH(hospid, startDate, endDate ,isYear);
			List list = null;
			Map map = new HashMap();
			if (isYear) {
				map.put("isYear", "isYear");
			}else{
				map.put("isYear", null);
			}
			map.put("hospid", hospid);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			RetCode rt = this.getData("findZHHospDataAnalysis", map);
			if (rt !=null && rt.getObj() !=null) {
				list = (List)rt.getObj();
			}
			
			return list;
			
		}else{
			//return findStH(hospid, power, startDate, endDate ,isYear);
			List list = null;
			Map map = new HashMap();
			if (isYear) {
				map.put("isYear", "isYear");
			}else{
				map.put("isYear", null);
			}
			map.put("power", power);
			map.put("hospid", hospid);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			RetCode rt = this.getData("findStHHospDataAnalysis", map);
			if (rt !=null && rt.getObj() !=null) {
				list = (List)rt.getObj();
			}
			
			return list;
		}
		
	}
	
	/**
	 * 绘图（医院能耗分析图）
	 * @param list			//数据集
	 * @param info			
	 * @param width			//宽
	 * @param height		//高
	 * @param imagePath		//保存路径
	 * @param title			//标题
	 * @return
	 */
	public boolean saveDraw(List<ChartData> list,ChartRenderingInfo info,int width,int height,String imagePath,String title,String unit){
		JFreeChart chart = (new HosLineChart(list,title,unit)).getChart();
		try {			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	/**
	 * 医院能耗
	 * @param vosth
	 * @param savePath	图片保存路径
	 * @param showPath	图生展示路径
	 * @return
	 */
	@Deprecated
	public RetCode findAnalysis(VOStatCondition  vosth,String savePath,String showPath,
			DbUsers dbusers){
		CMyShowData obj = new CMyShowData();
		
		RetCode rt = new RetCode();
		
		try {
			
			//查询数据
			VOStatCondition vs = this.getVOStHospDA(vosth,dbusers);
			/*
			List<DbStatHospital> dblist=sthosMgr.findDbStatHospital(vs.getHospid(),
					vs.getPower(),vs.getStartDate(),vs.getEndDate());
					*/
			List<DbStatHospital>  dblist = null;
			Map map = new HashMap();
			map.put("hospid", vs.getHospid());
			map.put("power", vs.getPower());
			map.put("startDate", vs.getStartDate());
			map.put("endDate", vs.getEndDate());
			RetCode rtdb = this.getData("findDbStatHospital", map);
			if (rtdb !=null && rtdb.getObj() !=null) {
				dblist = (List)rtdb.getObj();
			}
			
			
			
			if (null != dblist && dblist.size()>0) {
				//String hosName=hosMgr.findNameById(vs.getHospid());
				String hosName="";
				int seq = Integer.valueOf(vs.getHospid());
				DbHospInfo dbHospInfo =sqlSession.selectOne("findNameByIdHospDataAnalysis", seq);
				hosName = dbHospInfo.getHospName();
				
				String title = hosName+SysPower.getName(vs.getPower())+"能源";
				
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				HosAtoShowDate  toDate= new HosAtoShowDate(dblist,vs.getPower());				
				//绘图F
				List<ChartData> listf= toDate.getChartData(true,vs.getTimeStep());
				String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				String smPath= savePath +File.separator+"jfreechartNxiao"+ name+".png";		//生成图片名				
				String shPath = showPath+"jfreechartNxiao"+ name+".png";		//显示图片路径
//				shPath = shPath.replace("\\", "/");	
				shPath=SysUtil.replacePath(shPath);
				if(this.saveDraw(listf, info, 650, 400, smPath, title+"使用量",
						SysPower.getUnitEN(vs.getPower())) ){
					obj.setMyDrawF(shPath.replaceAll("\\\\", "/"));
					obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
				}
				
				
				//绘图S
				List<ChartData> lists= toDate.getChartData(false,vs.getTimeStep());
				name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				smPath= savePath +File.separator+"jfreechartNxiao"+ name+"2.png";			//生成图片名				
				shPath = showPath+"jfreechartNxiao"+ name+"2.png";		//显示图片路径
//				shPath = shPath.replace("\\", "/");
				shPath=SysUtil.replacePath(shPath);
				if(this.saveDraw(lists, info, 650, 400, smPath, title+"使用金额","元") ){
					obj.setMyDrawS(shPath.replaceAll("\\\\", "/"));
					obj.setUseMapS(ChartUtilities.getImageMap("useMapS", info));
				}
				
				//表格数据
				obj.setMytable(toDate.getTableDate(listf,lists));
				obj.getMytable().setTitle(title+"使用情况");
				
				
				//天气情况
				RetCode rw =  findAvgWeather(savePath, showPath);
				if (rw.getObj() != null) {
					CMyShowData wobj = (CMyShowData)rw.getObj();
					obj.setMyDrawT(wobj.getMyDrawF());
					obj.setUseMapT(wobj.getUseMapT());
				}
				
				rt.setObj(obj);
			}else{
				rt.setCode(1002);
				rt.setDesc("暂无数据！");
				rt.setDetail("暂无数据！");
				rt.setObj(null);
			}
		} catch (Exception e) {
			logg.error("HospDataAnalysisBusiness-->findAnalysis", e);
			// TODO: handle exception
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			rt.setDetail("操作失败！");
			rt.setObj(null);
			e.printStackTrace();
		}
		
		return rt;
	}
	
	
	
	
	
	
	
	/**
	 * 月平均气温
	 * @return
	 */
	@Deprecated
	private RetCode findAvgWeather(String savePath,String imagePath){
		RetCode rt = new RetCode();
		try {
			List list = weatherBusiness.findAvgWeather();
			CMyShowData obj = null;
			if (list != null) {	
				HashMap<String,Object> m = null;
				Float reading = null;
				String address = null;
				String stattime = null;
				String unit = "℃";
				
				ChartData cobj = null;
				List<ChartData>  clist = new ArrayList<ChartData>();				
				for (int i = 0; i < list.size(); i++) {
					m = (HashMap<String,Object>)list.get(i);
					reading = Float.parseFloat((m.get("reading")+""));
					address = (String)m.get("address");
					stattime = (String)m.get("stattime");
					cobj = new ChartData();
					cobj.setColumnKey(stattime);
					cobj.setRowKey(address);
					cobj.setValue(reading);
					cobj.setMapTitle(address+stattime+"平均气温:"+reading);
					clist.add(cobj);
				}
				String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				String saPath= savePath +File.separator+"weather"+name+".png";		//生成图片名				
				String showPath = imagePath+"weather"+name+".png";		//显示图片路径
				showPath = SysUtil.replacePath(showPath);

				
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				if (weatherBusiness.saveDraw(clist, info, 650, 400, saPath, "上海2012年月平均气温", unit)) {
					obj = new CMyShowData();
					//显示路径
					obj.setMyDrawF(showPath.replaceAll("\\\\", "/"));
					//提示信息
					obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
					rt.setObj(obj);
				}
			}
			if (obj != null) {
				rt.setDesc("暂无数据!");
			}			
		} catch (Exception e) {
			logg.error("HospDataAnalysisBusiness-->findAvgWeather", e);
			e.printStackTrace();
			rt.setDesc("查询异常!");
		}
		
		return rt;
		
	}

//	public void setSthosMgr(HospDataAnalysisMgr sthosMgr) {
//		this.sthosMgr = sthosMgr;
//	}
//
//	public void setHosMgr(HospInfoMgr hosMgr) {
//		this.hosMgr = hosMgr;
//	}

	/**
	 * @return the weatherBusiness
	 */
	public WeatherBusiness getWeatherBusiness() {
		return weatherBusiness;
	}

	/**
	 * @param weatherBusiness the weatherBusiness to set
	 */
	@Autowired
	public void setWeatherBusiness(WeatherBusiness weatherBusiness) {
		this.weatherBusiness = weatherBusiness;
	}
}

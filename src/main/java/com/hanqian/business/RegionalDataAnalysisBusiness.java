package com.hanqian.business;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanqian.common.CMyShowData;
import com.hanqian.common.ChartData;
import com.hanqian.common.RegionalAtoShowDate;
import com.hanqian.common.RegionalLineChart;
import com.hanqian.common.WeatherRate;
import com.hanqian.form.VOStatCondition;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysPower;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;



/**
 * 区域数据分析
 * @author clczp
 *
 */
@Service("RegionalDataAnalysisBusiness")
public class RegionalDataAnalysisBusiness   extends BaseBusiness {
	/**
	 * log4g日志
	 */
	public static final Logger logg = Logger.getLogger(RegionalDataAnalysisBusiness.class);
	
//	private static final Logger logg = Logger.getLogger(RegionalDataAnalysisBusiness.class);
//	private HospInfoMgr		hosMgr;			//医院Mgr
//	private StatStandardMgr    standMgr;		//标准Mgr
//	private RegionalDataAnalysisMgr regMgr;		//区域数据分析
	@Autowired
	private WeatherBusiness weatherBusiness;				//天气情况
	
	
	/**
	 * 请求信息处理
	 * @param vs
	 * @return
	 */
	private VOStatCondition getVOStHospDA(VOStatCondition  vs,DbUsers dbusers){	
		if (logg.isDebugEnabled())
			logg.debug("进入RegionalDataAnalysisBusiness.getVOStHospDA,对象VOStatCondition:" + vs+" DbUsers:"+dbusers);
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
			DbHospInfo hosp=dbusers.getDbHospInfo();
			if (hosp !=null) {
				vs.setSelUnits(hosp.getSeqHosp().toString());
			}
		}
		
		//初始化医院(默认5条记录)  
		if (SysUtil.isNull(vs.getSelUnits())) {
			//List<DbHospInfo> rlist = hosMgr.findAllHos();
			List<DbHospInfo> rlist = sqlSession.selectList("findAllHosVOStatCondition");
			
			
			if (rlist != null) {
				DbHospInfo m = null;
				String hospids = "";
				for (int i = 0; i < rlist.size() && i<=5; i++) {
					if (i!=0) {
						hospids +="','";
					}
					m = rlist.get(i);
					hospids += m.getSeqHosp();
				}
				vs.setSelUnits(hospids);
			}
		}	
		
		return vs;
	}
	
	public Map getGetVOStHospDASql() {
		return PerformSQLUtil.get("findAllHosVOStatCondition", this);
	}
	
	public List findStandard(String name,String startDate,String endDate,ETimeStep step){
		List list = null;
		
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
		Map mapvalue = new HashMap();
		mapvalue.put("name", name);
		mapvalue.put("startDate", startDate);
		mapvalue.put("endDate", endDate);
		if (isYear) {
			mapvalue.put("isYear", "isYear");
		}else{
			mapvalue.put("isYear", null);
		}			
		
		RetCode rt = this.getData("findStandard", mapvalue);
		if (rt !=null && rt.getObj() !=null) {
			list = (List)rt.getObj();
		}
		
		return list;
	}
	
	public Map getFindStandardSql() {
		return PerformSQLUtil.get("findStandard", this);
	}
	
	
	/**
	 * 区域能耗数据查询
	 * @param voreg
	 * @param quyuId
	 * @param savePath
	 * @param showPath
	 * @return
	 */
	public RetCode findData(VOStatCondition vs,Short yt,DbUsers dbusers,String savePath,
			String showPath,boolean isAnalysis){
		
		CMyShowData obj = new CMyShowData();
		
		RetCode rt = new RetCode();
		
		try {
			
			//查询数据
			vs = this.getVOStHospDA(vs,dbusers);
			
			//区域能耗数据查询
			List  rlist = null;
			List blist = null;
			if (isAnalysis) {
				rlist = this.findStatAreaAnalysis(yt,vs.getSelUnits(),vs.getPower(),vs.getStartDate(),
						vs.getEndDate(),vs.getTimeStep());
				blist = this.findStandard(vs.getBaseLine(), vs.getStartDate(), vs.getEndDate(), vs.getTimeStep());
			}else{
				rlist = this.findStatArea(yt,vs.getSelUnits(),vs.getPower(),vs.getStartDate(),
					vs.getEndDate(),vs.getTimeStep());
			}
						
			if (null !=rlist ){
				String title = SysPower.getName(vs.getPower())+"能源";
				
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				RegionalAtoShowDate  toDate= new RegionalAtoShowDate(vs.getPower());				
				
				String valKey = "wastage";
				String valUntit = "使用量";
				String unit = SysPower.getUnitName(vs.getPower());
				String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				String smPath= savePath +"\\regional"+ name+".png";		//生成图片名				
				String shPath = showPath+"regional"+ name+".png";		//显示图片路径
				//shPath = shPath.replace("\\", "/");		

				smPath =SysUtil.replacePath(smPath);
				shPath=SysUtil.replacePath(shPath);
				title += valUntit;
				
				
				if (isAnalysis) {
					title +="分析";
					unit = "占比";
				}
				
				List<ChartData> rclist = null;//源
				List<ChartData> bclist = null;//标准
				if (0 == vs.getPower()) {					
					//吨标煤
					valKey = "dbm";												
				}
				
				rclist = toDate.getChartData(rlist,valKey,
						valUntit,vs.getPower(),SysPower.getUnitEN(vs.getPower()));	
				
				//加入标准
				boolean lastBlack = false;
				if (blist != null) {
					bclist = toDate.getChartData(blist,"value",
							"标准",vs.getPower(),"");
					rclist.addAll(bclist);
					lastBlack  = true;
				}
				
				//添加倍率化后的气温
				boolean addRate = false;
				List<ChartData> wlist = null;
				if (!isAnalysis && !lastBlack && vs.getTimeStep().equals(ETimeStep.month) && vs.getPower()!=0) {					
					float rate = WeatherRate.findReate(rclist);
					wlist =  weatherBusiness.findAvgWeatherRate("上海",vs.getStartDate(),
							vs.getEndDate(), rate);
					addRate = rclist.addAll(wlist);
				}
				
				//绘图
				if(this.saveDraw(rclist, info, 650, 400, smPath, 
						title,unit,lastBlack) ){
					
					obj.setMyDrawF(shPath.replaceAll("\\\\", "/"));
					obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
				}
				
				
				//移除倍化后的气温
				if (addRate) {
					rclist.removeAll(wlist);
				}
				
				
				//表格数据
				if (bclist != null) {
					//移除标准
					rclist.removeAll(bclist);					
				}
				
				
				obj.setMytable(toDate.getTableDate(rclist,unit,bclist,vs.getPcha()));
				obj.getMytable().setTitle(title);
				
				
				rt.setObj(obj);
			}else{
				rt.setCode(1002);
				rt.setDesc("暂无数据！");
				rt.setDetail("暂无数据！");
				rt.setObj(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			rt.setDetail("操作失败！");
			rt.setObj(null);
			logg.error("RegionalDataAnalysisBusiness-->findData", e);
			e.printStackTrace();
		}
		
		return rt;
	}
	
	
	public List findStatAreaAnalysis(Short yt,String hospids,Short power,String startDate,String endDate,ETimeStep step){
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
			//return findZHAna(yt,hospids,startDate, endDate ,isYear);
			List list = null;
			Map mapvalue = new HashMap();
			mapvalue.put("yt", yt);
			mapvalue.put("hospids", hospids);
			mapvalue.put("startDate", startDate);
			mapvalue.put("endDate", endDate);
			if (isYear) {
				mapvalue.put("isYear", isYear);
			}else{
				mapvalue.put("isYear", null);
			}			
			RetCode rt =  this.getData("findZHAna", mapvalue);
			if (rt != null && rt.getObj() != null) {
				list = (List)rt.getObj();
			}
			return list;
		}else{
			//return findStatAna(yt,hospids,power, startDate, endDate ,isYear);
			List list = null;
			Map mapvalue = new HashMap();
			mapvalue.put("yt", yt);
			mapvalue.put("hospids", hospids);
			mapvalue.put("power", power);
			mapvalue.put("startDate", startDate);
			mapvalue.put("endDate", endDate);
			if (isYear) {
				mapvalue.put("isYear", isYear);
			}else{
				mapvalue.put("isYear", null);
			}			
			RetCode rt =  this.getData("findStatAna", mapvalue);
			if (rt != null && rt.getObj() != null) {
				list = (List)rt.getObj();
			}
			return list;	
		}		
	}
	
	
	public Map getFindStatAreaAnalysisSql() {
		return PerformSQLUtil.get("findStatAna", this);
	}
	
	public List findStatArea(Short yt,String hospids,Short power,String startDate,String endDate,ETimeStep step){
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
			//return findZH(yt,hospids,startDate, endDate ,isYear);
			List list = null;
			Map mapvalue = new HashMap();
			mapvalue.put("yt", yt);
			mapvalue.put("hospids", hospids);
			mapvalue.put("startDate", startDate);
			mapvalue.put("endDate", endDate);
			if (isYear) {
				mapvalue.put("isYear", isYear);
			}else{
				mapvalue.put("isYear", null);
			}			
			RetCode rt =  this.getData("findZH", mapvalue);
			if (rt != null && rt.getObj() != null) {
				list = (List)rt.getObj();
			}
			return list;
		}else{
			//return findStat(yt,hospids,power, startDate, endDate ,isYear);
			List list = null;
			Map mapvalue = new HashMap();
			mapvalue.put("yt", yt);
			mapvalue.put("hospids", hospids);
			mapvalue.put("power", power);
			mapvalue.put("startDate", startDate);
			mapvalue.put("endDate", endDate);
			if (isYear) {
				mapvalue.put("isYear", isYear);
			}else{
				mapvalue.put("isYear", null);
			}			
			RetCode rt =  this.getData("findStat", mapvalue);
			if (rt != null && rt.getObj() != null) {
				list = (List)rt.getObj();
			}
			return list;	
		}		
	}
	
	
	
	
	
	public boolean saveDraw(List<ChartData> list,ChartRenderingInfo info,int width,
			int height,String imagePath,String title,String unit,boolean lastBlack){
		
		JFreeChart chart = (new RegionalLineChart(list,title,unit,lastBlack)).getChart();
		try {			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
			
			return true;
		} catch (Exception e) {
			logg.error("RegionalDataAnalysisBusiness-->saveDraw", e);
			//e.printStackTrace();
		}
		
		return false;
	}
	
	
	
//	public void setStandMgr(StatStandardMgr standMgr) {
//		this.standMgr = standMgr;
//	}
//
//
//	public void setHosMgr(HospInfoMgr hosMgr) {
//		this.hosMgr = hosMgr;
//	}	
//	
//	public void setRegMgr(RegionalDataAnalysisMgr regMgr) {
//		this.regMgr = regMgr;
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


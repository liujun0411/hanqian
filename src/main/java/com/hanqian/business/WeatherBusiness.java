package com.hanqian.business;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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
import org.springframework.stereotype.Service;

import com.hanqian.common.CMyShowData;
import com.hanqian.common.ChartData;
import com.hanqian.common.WeatherLineChart;
import com.hanqian.common.WeatherRate;
import com.hanqian.form.VOWeather;
import com.hanqian.pojo.Weather;
import com.hanqian.pojo.WeatherRt;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;


/**
 * 天气情况业务层
 * @author clczp
 *
 */
@Service
public class WeatherBusiness extends BaseBusiness {
	
	/**
	 * log4g日志
	 */
	private static final Logger logg = Logger.getLogger(WeatherBusiness.class);
		
	/**
	 * 月平均气温
	 * reading,address,stattime
	 * @return
	 */
	@Deprecated
	public List findAvgWeather(){
		return this.sqlSession.selectList("findAvgWeather");
	}
	
	/**
	 * 月平均气温
	 * @return
	 */
	@Deprecated
	public RetCode findAvgWeather(String savePath,String imagePath){
		RetCode rt = new RetCode();
		try {
			List list = this.sqlSession.selectList("findAvgWeather");
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
//				showPath = showPath.replace("\\", "/");
				showPath=SysUtil.replacePath(showPath);
				
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				if (saveDraw(clist, info, 650, 400, saPath, "上海2011年月平均气温", unit)) {
					obj = new CMyShowData();
					//显示路径
					obj.setMyDrawF(showPath.replaceAll("\\\\", "/"));
					//提示信息
					obj.setUseMapF(ChartUtilities.getImageMap("useMapW", info));
					rt.setObj(obj);
				}
			}
			if (obj == null) {
				rt.setDesc("暂无数据!");
			}			
		} catch (Exception e) {
			logg.error("WeatherBusiness-->findAvgWeather", e);
			rt.setDesc("查询异常!");
		}
		
		return rt;
		
	}
	
	/**
	 * 月平均气温
	 * @return
	 */
	public VOWeather findSHMonthWeather(String key,String startTime,String endTime,String savePath,String imagePath){
		VOWeather obj = null;
		Log log = LogFactory.getLog(HospDataAnalysisBusiness.class);
		
		try {
			Map paramMap = new HashMap();
			paramMap.put("key", key);
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			List list = this.sqlSession.selectList("findAvgWeather2", paramMap);
			paramMap = null;
			
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
					cobj.setMapTitle(address+stattime+"平均气温="+reading+unit);
					clist.add(cobj);
				}
				String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				String saPath= savePath +File.separator+"weather"+name+".png";		//生成图片名				
				String showPath = imagePath+"weather"+name+".png";		//显示图片路径
//				showPath = showPath.replace("\\", "/");
				showPath=SysUtil.replacePath(showPath);
				

				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				if (saveDraw(clist, info, 650, 400, saPath, key+"2012年月平均气温", unit)) {
					obj = new VOWeather();
					//显示路径
					obj.setImgURL(showPath.replaceAll("\\\\", "/"));
					//提示信息
					obj.setImgMap(ChartUtilities.getImageMap("imgMap", info));					
				}
			}			
		} catch (Exception e) {
			logg.error("WeatherBusiness-->findSHMonthWeather", e);
		}
		
		return obj;
		
	}
	
	
	/**
	 * 上海月平均气温
	 * @return
	 */
	@Deprecated
	public VOWeather findSHMonthWeather(String savePath,String imagePath){
		VOWeather obj = null;
		Log log = LogFactory.getLog(HospDataAnalysisBusiness.class);
		
		try {
			List list = this.sqlSession.selectList("findAvgWeather");
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
					cobj.setMapTitle(address+stattime+"平均气温="+reading+unit);
					clist.add(cobj);
				}
				String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				String saPath= savePath +File.separator+"weather"+name+".png";		//生成图片名				
				String showPath = imagePath+"weather"+name+".png";		//显示图片路径
//				showPath = showPath.replace("\\", "/");
				showPath=SysUtil.replacePath(showPath);
				
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				if (saveDraw(clist, info, 650, 400, saPath, "上海2011年月平均气温", unit)) {
					obj = new VOWeather();
					//显示路径
					obj.setImgURL(showPath.replaceAll("\\\\", "/"));
					//提示信息
					obj.setImgMap(ChartUtilities.getImageMap("imgMap", info));					
				}
			}			
		} catch (Exception e) {
			logg.error("WeatherBusiness-->findSHMonthWeather", e);
		}
		
		return obj;
		
	}
	
	public  List<Weather> findWeatherByDT(String date){
		return this.sqlSession.selectList("findWeatherByDT", date);
	}
	
	/**
	 * city
	 * @param 2013-8-12  
	 * @param @param obj       
	 * @return void
	 */
	public void insertWeather(Weather obj) {
		try {
			this.sqlSession.insert("insertWeather", obj);
		} catch (Exception e) {
			logg.error("WeatherUtilMgr-->insertWeather", e);
		}
	}
	
	/**
	 * update city weather
	 * @param 2013-8-12  
	 * @param @param obj       
	 * @return void
	 */
	public void updateWeather(Weather obj){
		try {
			this.sqlSession.update("updateWeather", obj);
		} catch (Exception e) {
			logg.error("WeatherUtilMgr-->updateWeather", e);
		}
	}
	
	/**
	 * sk
	 * @param 2013-8-12  
	 * @param @param obj       
	 * @return void
	 */
	public void insertWeatherRt(WeatherRt obj) {
		try {
			this.sqlSession.insert("insertWeatherRt", obj);
		} catch (Exception e) {
			logg.error("WeatherUtilMgr-->insertWeatherRt", e);
		}
	}
	
	public  List<Weather> findWeatherByDT(){
		return this.sqlSession.selectList("findWeatherByDT");
	}
	
	public  List<WeatherRt> findWeatherRTByDT(){
		return this.sqlSession.selectList("findWeatherRTByDT");
	}
	
	/**
	 * 绘图
	 * @param list
	 * @param info
	 * @param width
	 * @param height
	 * @param imagePath
	 * @param title
	 * @param unit
	 * @return
	 */
	public boolean saveDraw(List<ChartData> list,ChartRenderingInfo info,int width,int height,String imagePath,String title,String unit){
		JFreeChart chart = (new WeatherLineChart(list,title,unit)).getChart();
		try {			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
			
			return true;
		} catch (Exception e) {
			logg.error("WeatherMgr-->saveDraw",e);
		}
		
		return false;
	}
	
	/**
	 * 以倍率放大后的气温
	 * @param key			地区
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param rate			倍率
	 * @return
	 */
	public List<ChartData> findAvgWeatherRate(String key,String startTime,String endTime,
			float rate){
		
		List<ChartData> list = new ArrayList<ChartData>();
		Map m = null;
		ChartData obj = null;
		
		Map paramMap = new HashMap();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		List rlist = this.sqlSession.selectList("findAvgWeather2", paramMap);
		paramMap = null;
				
		if (rlist != null && rlist.size()>0) {
			
			for (int i = 0; i < rlist.size(); i++) {
				m = (HashMap)rlist.get(i);
				obj = new ChartData();
				obj.setRowKey(m.get("address")+"");
				obj.setColumnKey(m.get("stattime")+"");
				obj.setValue(((BigDecimal)m.get("reading")));
				obj.setMapTitle(obj.getRowKey().toString()+
						obj.getColumnKey().toString()+"="+m.get("reading")+"℃");
				
				list.add(obj);
			}
			float rNow = WeatherRate.findReate(list);
			for (ChartData now:list) {
				if(now!=null && now.getValue()!=null)
					now.setValue(now.getValue().floatValue()*(rate/rNow));
			}
			
		}
		return list;
	}
}

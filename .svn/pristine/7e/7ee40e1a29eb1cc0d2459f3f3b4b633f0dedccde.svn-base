package com.hanqian.business;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Service;

import com.hanqian.common.BiLiChart;
import com.hanqian.common.ChartData;
import com.hanqian.common.QuYuChart;
import com.hanqian.pojo.DbMenus;
import com.hanqian.util.DrawType;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysPower;
import com.hanqian.util.SysUtil;


@Service
public class NengXiaoBusiness extends BaseBusiness{
	private static final Logger log = Logger.getLogger(NengXiaoBusiness.class);	
	
	private String valuestr=null;
	private String typestr=null;
	private String wherestr=null;
	private StringBuffer buildidstr=null;
	private StringBuffer sDate=null;
	private StringBuffer eDate=null;
	private String showName=null;
	
	private List findBuilding(){
		
		RetCode rt = this.getData("findBuildingNengXiaoBusiness", null);
		return (List)rt.getObj();
	}
	
	
	
	/**
	 * 设置楼宇
	 * 最多8项
	 * @param buildid
	 */
	private void setBuildidstr(String[] buildid){
		buildidstr = new StringBuffer();
		if (null !=buildid  && buildid.length>0) {			
			buildidstr.append("  and build_.buildid in(");
			for (int i = 0; i < buildid.length && i<6; i++) {
				if (i!=0) {
					buildidstr.append(",");
				}
				buildidstr.append("'"+buildid[i]+"'");				
			}
			buildidstr.append(" )");
		}else{
			List list = this.findBuilding();
			HashMap m = null;
			if (list !=null) {
				buildidstr.append("  and build_.buildid in(");
			
				for (int i = 0; i < list.size() && i<6; i++) {
					m = (HashMap)list.get(i);
					if (i!=0) {
						buildidstr.append(",");
					}
					buildidstr.append("'"+m.get("sequence")+"'");
				}
				buildidstr.append(" )");
			}
			
		}
	}
	
	/**
	 * 设置时间
	 * @param startDate
	 * @param endDate
	 */
	private void setDate(String startDate,String endDate){
		sDate=new StringBuffer();
		eDate=new StringBuffer();
		
		if (!SysUtil.isNull(startDate)) {
			sDate.append(" and "+typestr +">=" +startDate);
		}
		if ( !SysUtil.isNull(endDate) ) {
			eDate.append(" and "+typestr +"<=" +endDate);
		}
	}
	
	/**
	 * 门诊、急诊、住院
	 * @param bustype
	 */
	private void setShowName(int bustype){
		if (1==bustype) {
			showName="门诊";
		}else if (2==bustype) {
			showName="急诊";
		}else if (3==bustype) {
			showName="住院";
		}
	}

	
		
	
	
	
	/**
	 * 楼宇能源使用量 /折算价值
	 * @param hospid  	医院Id
	 * @param buildid  	楼宇列表
	 * @param power		能源类型  
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param step		时间间隔
	 * @param unit		单位
	 * @return
	 */
	public RetCode findPowerUseB(String hospid,String[] buildid,Short power,String startDate,String endDate,String step,float unit){
		setDate(startDate,endDate);
		 RetCode rc = new  RetCode();
		setBuildidstr(buildid);
		Map param = new HashMap();
		param.put("unit", unit);
		param.put("hospid", hospid);
		param.put("power", power);
		param.put("buildidstr", buildidstr);
		param.put("sDate", sDate);
		param.put("eDate", eDate);
		if (null !=step && "month".equals(step)) {
			rc = this.getData("findPowerUseB", param);
		}else{
			rc = this.getData("findPowerUseBB", param);
		}
		return rc;
	}
	
	/**
	 * 楼宇能源使用量 /折算价值
	 * @param hospid  	医院Id
	 * @param buildid  	楼宇列表
	 * @param power		能源类型  
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param step		时间间隔
	 * @param unit		单位
	 * @return
	 */
	public RetCode findPowerUse(String hospid,String[] buildid,Short power,String startDate,String endDate,String step,float unit){
		 RetCode rc = new  RetCode();
		setDate(startDate,endDate);
		setBuildidstr(buildid);
		Map param = new HashMap();
		param.put("unit", unit);
		param.put("hospid", hospid);
		param.put("power", power);
		param.put("buildidstr", buildidstr);
		param.put("sDate", sDate);
		param.put("eDate", eDate);
		if (null !=step && "month".equals(step)) {
			rc = this.getData("findPowerUseUp", param);
		}else{
			rc = this.getData("findPowerUseDown", param);
		}		
		return rc;
	}
	
	
	/**
	 * 楼宇能源使用量标准化 /折算价值
	 * @param hospid	医院Id
	 * @param buildid	楼宇列表
	 * @param power		能源类型
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param step		时间间隔
	 * @param unit		单位
	 * @return
	 */
	public RetCode findPowerUseAvg(String hospid,String[] buildid,Short power,String startDate,String endDate,String step,float unit){
		 RetCode rc = new  RetCode();
			setDate(startDate,endDate);
			setBuildidstr(buildid);
			Map param = new HashMap();
			param.put("unit", unit);
			param.put("hospid", hospid);
			param.put("power", power);
			param.put("buildidstr", buildidstr);
			param.put("sDate", sDate);
			param.put("eDate", eDate);
			
		if (null !=step && "month".equals(step)) {
			rc = this.getData("findPowerUseAvgUp", param);
		}else{
			rc = this.getData("findPowerUseAvgDown", param);
		}

		return rc;
	}
	
	
	/**
	 * 能源使用量标准化基线
	 * @param startDate
	 * @param endDate
	 * @param step
	 * @param jiZhun
	 * @return
	 */
	public RetCode findBaseLine(String startDate,String endDate,String step,short jiZhun){
		
		RetCode rc = new RetCode();
		
		if (jiZhun !=1) {
			return rc;
		}
		
		int sDate=0;
		int eDate=0;
		int imin=0;
		int imax=0;
		try {
			if (null == startDate ) {
				startDate = "month".equals(step)?"200907":"2009";
			}
			if (null == endDate) {
				endDate = "month".equals(step)?"201108":"2011";
			}
			sDate=Integer.parseInt(startDate);
			eDate=Integer.parseInt(endDate);
		} catch (Exception e) {
			log.error("NengXiaoMgr-->findBaseLine", e);
		}
		List<Map> list = new ArrayList<Map>();
		Double[] vlues;
		String[] types;
		if ("month".equals(step)) {
			vlues= new Double[]{1.12,1.13,1.04,0.92,0.88,1.04,
					0.9,0.86,0.81,0.82,0.86,0.91,1.13,1.15,1.05,0.93,0.85,1.06,
					0.92,0.89,0.82,0.84,0.86,0.95,1.16,1.13,1.08,0.97,0.88,1.05,};
			types = new String[]{"200907","200908","200909","200908","200910","200911","200912",
					"201001","201002","201003","201004","201005","201006","201007","201008","201009","201010","201011","201012",
					"201101","201102","201103","201104","201105","201106","201107","201108","201109","201110","201111","201112",};
		}else{		
			vlues= new Double[]{0.88,0.76,0.90};
			types = new String[]{"2009","2010","2011"};
		}
		
		for (int i = 0; i < types.length; i++) {
			if((sDate+"").equals(types[i])){
				imin=i;
			}
			
			if((eDate+"").equals(types[i])){
				imax=i;
				break;
			}
		}
		
		
		
		String   name = "基准";
		try{
			for (int i = 0; imin <= imax; i++,imin++) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("value", vlues[imin]);
				m.put("name", name);
				m.put("type", types[imin]);
				
				list.add(m);
			}
			
			rc.setObj(list);
		}catch(Exception e){
			log.error("NengXiaoMgr-->findBaseLine", e);
		}
		
		return rc;
	}
	
	/**
	 *医院能耗量 / 能耗折算价值
	 * @param hospid	医院Id
	 * @param power		能源类型
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param step		时间间隔
	 * @return
	 */
	public RetCode findOutpatientsPowerByHospital(String hospid,Short power,String startDate,String endDate,String step){
		 	RetCode rc = new  RetCode();
		 	setDate(startDate,endDate);
			Map param = new HashMap();
			param.put("hospid", hospid);
			param.put("power", power);			
			param.put("sDate", sDate);
			param.put("eDate", eDate);
		String value2str=null;
		if (null !=step && "month".equals(step)) {
			rc = this.getData("findOutpatientsPowerByHospitalUp", param);
		}else{
			rc = this.getData("findOutpatientsPowerByHospitalDown", param);
		}
		
		return rc;
	}
	
	
	/**
	 * 业务量能耗 /折算价值
	 * @param hospid	医院Id
	 * @param power		能源类型
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param step		时间间隔
	 * @param bustype	业务类型
	 * @param unit		单位
	 * @return
	 */
	public RetCode findOutpatientsPower(String hospid,Short power,String startDate,String endDate,String step,int bustype,float unit){
		String sname ="";
		setDate(startDate,endDate);
		setShowName(bustype);
		if(unit!=1f || unit==0f){
			sname = "折算价值";
		}else{
			sname = "("+SysPower.getUnitName(power)+")";
		}
		
		String value2str=null;
		
		RetCode rc = new  RetCode();
	 	setDate(startDate,endDate);
		Map param = new HashMap();
		param.put("hospid", hospid);
		param.put("power", power);			
		param.put("sDate", sDate);
		param.put("eDate", eDate);
		param.put("unit", unit);
		param.put("bustype", bustype);
		param.put("showName", showName);
		
		if (null !=step && "month".equals(step)) {
			rc = this.getData("findOutpatientsPowerUp", param);
		}else{
			rc = this.getData("findOutpatientsPowerDown", param);
		}
		return rc;
	}
	
	
	
	/**
	 *业务量平均能耗  /平均折算价值
	 * @param hospid
	 * @param buildid
	 * @param power
	 * @param startDate
	 * @param endDate
	 * @param step
	 * @return
	 */
	public RetCode findOutpatientsPowerAvg(String hospid,Short power,String startDate,String endDate,String step,int bustype,float unit){
		String sname ="";
		RetCode rc = new  RetCode();
		setDate(startDate,endDate);
		setShowName(bustype);

		
		
		if(unit!=1f || unit==0f){
			sname="折算价值";
		}else{
			sname = "("+SysPower.getUnitName(power)+")";
		}
		Map param = new HashMap();
		param.put("hospid", hospid);
		param.put("power", power);			
		param.put("sDate", sDate);
		param.put("eDate", eDate);
		param.put("sname", sname);
		param.put("unit", unit);
		param.put("bustype", bustype);
		param.put("showName", showName);
		
		if (null !=step && "month".equals(step)) {
			rc = this.getData("findOutpatientsPowerAvgUp", param)	;	
		}else{
			rc = this.getData("findOutpatientsPowerAvgDown", param);		
		}		
		
		return rc;
	}
	
	
	

	
	
	
	
	/**
	 * 生成图片
	 * @param drewtype	图片类形
	 * @param datalist	数据集
	 * @param baselist	基准数据集
	 * @param imagePath	图片保存地址
	 * @return
	 */
	public boolean saveDraw(DrawType drawType,List datalist,List baselist,String imagePath,int width,int height,ChartRenderingInfo info){		
		JFreeChart chart = (new QuYuChart(datalist,baselist)).getChart(drawType);
		try {			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
			
			return true;
		} catch (Exception e) {
			log.error("NengXiaoMgr-->boolean saveDraw", e);
		}
		
		return false;
	}
	
	public void saveDraw(List<ChartData> list,ChartRenderingInfo info,int width,int height,String imagePath,String zChart){
		JFreeChart chart = (new BiLiChart(list,null)).getChart();
		try {			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
		} catch (Exception e) {
			log.error("NengXiaoMgr-->void saveDraw", e);
		}
	}
	
	/**
	 * 能效菜单(总站)
	 * @param userid
	 * @return
	 */
	public RetCode findNengXiaoMenu(){
		return this.getData("findNengXiaoMenuNull", null);	
	}
	/**
	 * 能效菜单(分站)
	 * @param hospid
	 * @return
	 */
	public RetCode findNengXiaoMenu(String hospid){
		Map map = new HashMap();
		map.put("hospid", hospid);
		return this.getData("findNengXiaoMenu", map);
	}
	
	
	
	

	/**
	 * 菜单
	 * */
	public RetCode findMenus(DbMenus menus) {
		RetCode rc = new RetCode(1001, "无查询数据", "未找到查询的相应数据");
		try {
			Map map = this.getDbMenusCrit(menus);
			return this.getData("findMenusNengXiaoBusiness", map); 
		} catch (Exception e) {
			rc.setCode(1004);
			rc.setDesc("查询失败");
			rc.setDetail("查询失败");
			rc.setObj(null);
			e.printStackTrace();
			log.error("NengXiaoMgr-->findMenus", e);
		}
		return rc;
	}
	
	
	
	
	

	/**
	 * 获取医院用途比例设置，有年份或ID就根据年份，id查询，没有就查询所有
	 * */
	private Map getDbMenusCrit(DbMenus menus) {
		Map map = new HashMap();
		map.put("parentid", 4);
		return map;
	}

	
	
}

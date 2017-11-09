package com.hanqian.business;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.springframework.stereotype.Service;

import com.hanqian.action.ElectricityControlAction;
import com.hanqian.common.ChartData;
import com.hanqian.common.ElectChartData;
import com.hanqian.common.ElectLineChart;
import com.hanqian.form.VOElectCondition;
import com.hanqian.form.VOElectricity;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;
import com.hanqian.util.Systime;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-12-10
 * @see
 */
@Service("electricityBusiness")
public class ElectricityControlBusiness  extends BaseBusiness {

	private static final Logger log = Logger.getLogger(ElectricityControlAction.class);
	
	/**
	 * 获取三相电流的数据
	 * @param voElectCondition
	 * @param savePath
	 * @param showPath
	 * @return
	 */
	public List createAndResultPic(VOElectCondition voElectCondition,String savePath,String showPath){
		if (log.isDebugEnabled())
			log.debug("进入ElectricityControlBusiness.createAndResultPic,参数showPath:"
					+ showPath);	
		List resultList=new ArrayList();
		Log log = LogFactory.getLog(ElectricityControlBusiness.class);
		//变压器列表
		List list=new ArrayList();
		//变压器电流类型
		String [] typeList=new String[]{"IA","IB","IC"};
		String falgSA="3A";
		List dataList=new ArrayList();
		List<ChartData> rclist = null;//源数据
		List<ChartData> wlist = null;//源数据
		list=getAllPotential(voElectCondition.getEqTypeId());
		voElectCondition=returnNewVoElect(voElectCondition);
		log.debug("(3)final:voElectCondition.getStartTime():"+voElectCondition.getStartTime()+" voElectCondition.getEndTime()："+voElectCondition.getEndTime());
		if(list.size()>0){
			dataList=this.findElectRecordHis(voElectCondition);
			for(int i=0;i<list.size();i++){
				wlist=null;
				Map map=new HashMap();
				map=(Map)list.get(i);
				String equipId="";
				String eqName="";
				if(map.get("equip_id")!=null){
				   equipId=map.get("equip_id").toString();
				   eqName=map.get("equip_name").toString();
				   StandardEntityCollection sec = new StandardEntityCollection();
				   ChartRenderingInfo info = new ChartRenderingInfo(sec);
				   ElectChartData  toDate= new ElectChartData();	
				   String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				   String smPath= savePath +"\\electricity"+ name+".png";		//生成图片名				
				   String shPath = showPath+"electricity"+ name+".png";		//显示图片路径
				   smPath =this.replacePath(smPath);
				   shPath=this.replacePath(shPath);
				   String valKey = "recordinfo";
				   String valUntit ="当前值";
				   rclist =null;
				   rclist=new ArrayList<ChartData>();
				   boolean addRate = false;
				   for(int j=0;j<typeList.length;j++){
					   wlist=new ArrayList<ChartData>();
//					   dataList=new ArrayList();
//					   dataList=this.findElectRecordHis(typeList[j], voElectCondition,equipId);
					   List dataLists= this.internal(dataList,equipId);
					   if(dataLists!=null){
						   if(dataLists.size()>0){
							   wlist=toDate.getChartData(dataLists, valKey, valUntit, "A",typeList[j],falgSA);
							   addRate = rclist.addAll(wlist);
						   }
					   }
				   }
				   if(rclist!=null){
					   if(rclist.size()>0){
						    VOElectricity  vOElectricity=new VOElectricity();
						    //绘图
						    String fileStreampath=this.saveDraw(rclist, info, 650, 400, smPath, 
									eqName,"A",true,"singel",null);
							if(StringUtils.isNotEmpty(fileStreampath)){
								shPath=shPath.replaceAll("\\\\", "/");
								vOElectricity.setPath(fileStreampath);
								vOElectricity.setTitle(eqName);
								resultList.add(vOElectricity);
							}
					   }
				   }
				}
			}
		}
		return resultList;
	}
	
	public List findElectRecordHis(VOElectCondition voElectCondition){
		if (log.isDebugEnabled())
			log.debug("进入ElectricityControlBusiness.findElectRecordHis,参数EquipId:");	
		//格式化电压表开始结束时间格式，去掉分钟
		if(voElectCondition!=null&&voElectCondition.geteTimeStep()!=null){
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmm");
		    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHH");
		    if(voElectCondition.getStartTime()!=null&&voElectCondition.getEndTime()!=null&&voElectCondition.getStartTime().length()==12){
		    	try {
		    		Date startTime_date = formatter1.parse(voElectCondition.getStartTime());
		    		Date endTime_date = formatter1.parse(voElectCondition.getEndTime());
		    		String startTime=formatter2.format(startTime_date);
		    		String endTime=formatter2.format(endTime_date);
		    		voElectCondition.setStartTime(startTime);
		    		voElectCondition.setEndTime(endTime);
		    	} catch (ParseException e) {
		    		e.printStackTrace();
		    	}
		    }else{
				log.debug("voElectCondition的StartTime或EndTime为空或voElectCondition.getStartTime().length()不为12");
			}
		}else{
			log.debug("voElectCondition或voElectCondition.geteTimeStep()为空");
		}
		List recordList=null;
		String dateFormat="";
//		if(voElectCondition.geteTimeStep()!=null){
//		    dateFormat="YYYYMMDDHH24MI";
//		}
		
//		Map map = new HashMap();
//		map.put("condition", condition);
//		map.put("EquipId", EquipId);
//		map.put("startTime", voElectCondition.getStartTime());
//		map.put("endTime", voElectCondition.getEndTime());
		
		Map map = new HashMap();
		if(voElectCondition!=null){
			map.put("startTime", voElectCondition.getStartTime());
			map.put("endTime", voElectCondition.getEndTime());
		}else{
			log.debug("voElectCondition为空");
		}
		
		RetCode rc=this.getData("findElectRecordHis", map);
		if(rc!=null){
			if(rc.getObj()!=null){
				recordList=(List)rc.getObj();
			}
		}
		return recordList;
	}
	
	/**
	 * 根据设备ID获取所有三项电流、综合电流的结果集
	 * 付萌萌
	 */
	public List internal(List list,String id){
			List<Map>  returnInternal=new ArrayList<Map>();
			if(list==null||list.size()<0){
				return null;
			}else{
				for(int i=0;i<list.size();i++){
					Map map=new HashMap();
					map=(Map)list.get(i); 
						String equipId=map.get("equip_id").toString();
						if(id.equals(equipId)){
							returnInternal.add(map);
						}
				}
			}
			return returnInternal;
		}
	
	public Map getFindElectRecordHisSql() {
		return PerformSQLUtil.get("findElectRecordHis", this);
	}
	
	/**
	 * 展示三相电流、综合电流的折线图
	 * @param list 数据结果集
	 * @param info 
	 * @param width 宽度
	 * @param height 高度
	 * @param imagePath 图片路径
	 * @param title 标题
	 * @param unit 单位
	 * @param lastBlack 
	 * @param flag 
	 * @param maxVal 最大值
	 * @return
	 */
	public String saveDraw(List<ChartData> list,ChartRenderingInfo info,int width,int height,
			String imagePath,String title,String unit,boolean lastBlack,String flag,Integer maxVal){
		JFreeChart chart = (new ElectLineChart(list,title,unit,lastBlack,flag,maxVal)).getChart();
		String fileStream ="";
		try {		
			BufferedImage bi = chart.createBufferedImage(width, height);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();

			boolean isOk = ImageIO.write(bi, "png", bout);
//			System.out.println("isOk = " + isOk);
			if (isOk) {
				fileStream= StringUtil.toBase64String(bout.toByteArray());
			}
			
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height,info);				
			fileout.close();
		} catch (Exception e) {
			log.error("ElectricityControlBusiness-->saveDraw", e);
		}
		return fileStream;
	}
	
	/**
	 * 综合电流趋势图
	 * @param 2012-12-11  
	 * @param @param voElectCondition
	 * @param @param savePath
	 * @param @param showPath
	 * @param @return       
	 * @return List
	 */
	public List createTotalPic(VOElectCondition voElectCondition,String savePath,String showPath){
		if (log.isDebugEnabled())
			log.debug("进入ElectricityControlBusiness.createTotalPic,参数showPath:"
					+ showPath);	
		List resultList=new ArrayList();
		Log log = LogFactory.getLog(ElectricityControlBusiness.class);
		//变压器列表
		List list=new ArrayList();
		String falgSA="ZH";
		List dataList=new ArrayList();
		List dataListId=new ArrayList();
		List<ChartData> rclist = null;//源数据
		List<ChartData> wlist = null;//源数据
		list=getAllPotential(voElectCondition.getEqTypeId());
		if(StringUtil.isNotEmpty(voElectCondition.getStartTime()) && StringUtil.isNotEmpty(voElectCondition.getEndTime())){
			log.debug("notEmpty:voElectCondition.getStartTime():"+voElectCondition.getStartTime()+" voElectCondition.getEndTime()："+voElectCondition.getEndTime());
		}else{
			voElectCondition=returnNewVoElect(voElectCondition);
		}
		log.debug("final:voElectCondition.getStartTime():"+voElectCondition.getStartTime()+" voElectCondition.getEndTime()："+voElectCondition.getEndTime());
		//
		if(list.size()>0){
			//查询所用综合电流数据
		    //dataList=this.findElectRecordTotal(null,null);
			for(int i=0;i<list.size();i++){
				wlist=null;
				Map map=null;
				map=(Map)list.get(i);
				String equipId="";
				String eqName="";
				if(map.get("equip_id")!=null){
				   equipId=map.get("equip_id").toString();
				   eqName=map.get("equip_name").toString();
				   int MaxVal=this.findMaxValue(equipId);
				   StandardEntityCollection sec = new StandardEntityCollection();
				   ChartRenderingInfo info = new ChartRenderingInfo(sec);
				   ElectChartData  toDate= new ElectChartData();	
				   String name = Systime.DateToString(new Date(),"yyyyMMddHHmmss");
				   String smPath= savePath +"\\composite"+ name+".png";		//生成图片名				
				   String shPath = showPath+"composite"+ name+".png";		//显示图片路径
				   smPath =this.replacePath(smPath);
				   shPath=this.replacePath(shPath);
				   String valKey = "recordinfo";
				   String valUntit ="当前值";
				   rclist =null;
				   rclist=new ArrayList<ChartData>();
				   boolean addRate = false;
				   dataList=this.findElectRecordTotal(voElectCondition, equipId);
				   /*List dataListss= this.internal(dataList, equipId);
				   if(dataListss!=null){
					   //绘图
					   if(dataListss.size()>0){
					       rclist=toDate.getChartData(dataListss, valKey, valUntit, "A","aa",falgSA);
					       VOElectricity  vOElectricity=new VOElectricity();
					       String fileStreamPath=this.saveDraw(rclist, info, 650, 400, smPath, 
									eqName,"A",true, "total",MaxVal);
						   if(StringUtils.isNotEmpty(fileStreamPath) ){
							  shPath=shPath.replaceAll("\\\\", "/");
							  vOElectricity.setPath(fileStreamPath);
							  vOElectricity.setTitle(eqName);
							  resultList.add(vOElectricity);
						   }
					   }
				    }*/
				   if(dataList!=null){
					   //绘图
					   if(dataList.size()>0){
					       rclist=toDate.getChartData(dataList, valKey, valUntit, "A","aa",falgSA);
					       VOElectricity  vOElectricity=new VOElectricity();
					       String fileStreamPath=this.saveDraw(rclist, info, 650, 400, smPath, 
									eqName,"A",true, "total",MaxVal);
						   if(StringUtils.isNotEmpty(fileStreamPath) ){
							  shPath=shPath.replaceAll("\\\\", "/");
							  vOElectricity.setPath(fileStreamPath);
							  vOElectricity.setTitle(eqName);
							  resultList.add(vOElectricity);
						   }
					   }
				    }
				 }
			}
		}
		return resultList;
	}
	
	
	/**
	 * 获取所有综合电流的读数
	 * 付萌萌
	 * @return 
	 */
	/**
	 * @param voElectCondition
	 * @param equipId
	 * @return
	 */
	public List findElectRecordTotal(VOElectCondition voElectCondition,String equipId){
		if (log.isDebugEnabled())
			log.debug("进入ElectricityControlBusiness.findElectRecordTotal,参数EquipId:");	
		List recordList=null;
		//格式化电压表开始结束时间格式，去掉分钟
		if(voElectCondition!=null&&voElectCondition.geteTimeStep()!=null){
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmm");
		    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHH");
		    if(voElectCondition.getStartTime()!=null&&voElectCondition.getEndTime()!=null&&voElectCondition.getStartTime().length()==12){
		    	try {
		    		Date startTime_date = formatter1.parse(voElectCondition.getStartTime());
		    		Date endTime_date = formatter1.parse(voElectCondition.getEndTime());
		    		String startTime=formatter2.format(startTime_date);
		    		String endTime=formatter2.format(endTime_date);
		    		voElectCondition.setStartTime(startTime);
		    		voElectCondition.setEndTime(endTime);
		    	} catch (ParseException e) {
		    		e.printStackTrace();
		    	}
		    }else{
				log.debug("voElectCondition的StartTime或EndTime为空或voElectCondition.getStartTime().length()不为12");
			}
		}else{
			log.debug("voElectCondition或voElectCondition.geteTimeStep()为空");
		}
		
		Map map = new HashMap();
		map.put("equipId", equipId);
		if(voElectCondition!=null){
			map.put("startTime", voElectCondition.getStartTime());
			map.put("endTime", voElectCondition.getEndTime());
		}else{
			log.debug("voElectCondition为空");
		}
		RetCode rc=this.getData("findElectRecordTotal", map);
	  	if(rc!=null){
			if(rc.getObj()!=null){
				recordList=(List)rc.getObj();
			}
		}
		return recordList;
	}
	
	public Map getFindElectRecordTotalSql() {
		return PerformSQLUtil.get("findElectRecordTotal", this);
	}
	
	
	public Integer findMaxValue(String equipId){
		Integer result=null;
		RetCode rc=new RetCode();
		try{
			if(StringUtils.isNotEmpty(equipId)){
			   //String sql="select m.equip_id,m.param_value from db_eq_param m where m.equip_id='"+equipId+"'";
			   //rc= this.findBySQL(dbEquipListDAO, sql);
				Map map = new HashMap();
				map.put("equipId", equipId);
				rc= this.getData("findMaxValue", map);
			}
		}catch (Exception e) {
			log.error("com.hanqian.business.ElectricityControlBusiness-->findMaxValue", e);
			rc.setObj(null);
		}
		if(rc.getObj()!=null){
			List list=(List)rc.getObj();
			Map hashMap=new HashMap();
			if(list.size()>0){
				hashMap=(Map)list.get(0);
				result= Integer.parseInt(hashMap.get("param_value").toString());
			}else{
				result=0;
			}
		}else{
			result=0;
		}
		return result;
	}
	
	public Map getFindMaxValueSql() {
		return PerformSQLUtil.get("findMaxValue", this);
	}
	
	
	
	private static String replacePath(String rootPath) {
		if("\\".equals(File.separator)){   		 
			rootPath = rootPath.replace("/", "\\");
		}
		//linux下
		if("/".equals(File.separator)){   
			  rootPath = rootPath.replace("\\", "/");
		}
		return rootPath;
	}
	
	/**
	 * 获取到所有的变压器类型
	 * @param 2012-12-10  
	 * @param @param eqTypeId
	 * @param @return       
	 * @return List
	 */
	public List getAllPotential(String eqTypeId){
		if (log.isDebugEnabled())
			log.debug("进入ElectricityControlBusiness.getAllPotential,参数eqTypeId:"
					+ eqTypeId);	
		List list=new ArrayList();
		//RetCode rc=equipListMgr.findEquipListByTypeId(eqTypeId);
		Map map =new HashMap();
		map.put("eqTypeId", Integer.valueOf(eqTypeId));
		RetCode rc= this.getData("findEquipListByTypeId", map);
		if(rc.getObj()!=null){
			list=(List)rc.getObj();
		}
		return list;
	}
	
	public Map getGetAllPotentialSql() {
		return PerformSQLUtil.get("findEquipListByTypeId", this);
	}
	
	
	/**
	 * 获取开始时间，结束时间，时间间隔，
	 * @param 2012-12-10  
	 * @param @return       
	 * @return VOElectCondition
	 */
	public VOElectCondition returnNewVoElect(VOElectCondition voElectCondition){
		if(voElectCondition==null){
			voElectCondition=new VOElectCondition();
			log.debug("voElectCondition==null");
		}
		//获取当前系统时间
		if(voElectCondition.getStartTime()==null && voElectCondition.getEndTime()==null){
			//如果是小时
			if(voElectCondition.geteTimeStep()==ETimeStep.hour){
				Date d= new Date();
				voElectCondition.setEndTime(Systime.DateToString(d, "yyyyMMddHHmm"));
				if(StringUtils.isNotEmpty(voElectCondition.getConfigValue())){
					int hours=Integer.parseInt(voElectCondition.getConfigValue());
					d.setHours(d.getHours()-hours);
				}else{
					log.debug("voElectCondition.getConfigValue()(hour) is Empty");
				}
				voElectCondition.setStartTime(Systime.DateToString(d, "yyyyMMddHHmm"));
				log.debug("is Empty voElectCondition.getStartTime:"+voElectCondition.getStartTime());
			}
			if(voElectCondition.geteTimeStep()==ETimeStep.day){
				Date d= new Date();
				voElectCondition.setStartTime(Systime.DateToString(d, "yyyyMMdd"));
				if(StringUtils.isNotEmpty(voElectCondition.getConfigValue())){
					int day=Integer.parseInt(voElectCondition.getConfigValue());
					//d.setHours(d.getHours()-hours);
					d.setHours(d.getHours()+24);
				}else{
					log.debug("voElectCondition.getConfigValue()(day) is Empty");
				}
				voElectCondition.setEndTime(Systime.DateToString(d, "yyyyMMdd"));
				log.debug("is Empty voElectCondition.getEndTime:"+voElectCondition.getEndTime());
			}
		}else{
			log.debug("voElectCondition.getStartTime()==null || voElectCondition.getEndTime()==null");
		}
		return voElectCondition;
	}
	
	/**
	 * 返回节能指数
	 * @param 2012-12-17  
	 * @param @return       
	 * @return VOElectCondition
	 */
	public VOElectCondition getEnergySaveing(VOElectCondition voElectCondition,String hospId,String hospKind){
		if (log.isDebugEnabled())
			log.debug("进入ElectricityControlBusiness.getEnergySaveing,参数hospId:"
					+ hospId);	
		VOElectCondition reultVOElect=new VOElectCondition();
		if(voElectCondition!=null){
			if(voElectCondition.getStartTime()!=null && hospId!=null){
				RetCode rc=this.getEnergySaving(voElectCondition, hospId,hospKind);
				if(rc.getObj()!=null){
					List list=(List)rc.getObj();
					Map map=new HashMap();
					map=(Map)list.get(0);
					String eellcal=map.get("eellclval").toString();
					String reasonableval=map.get("reasonableval").toString();
					String fiducialval=map.get("fiducialval").toString();
					DecimalFormat df = new DecimalFormat(".00");
					if(Double.parseDouble(eellcal)<=Double.parseDouble(reasonableval)){
						reultVOElect.setResultEnergy("A");
//						double result=Math.abs((Double.parseDouble(eellcal)-Double.parseDouble(reasonableval))/Double.parseDouble(reasonableval)*100);
						double result=(Double.parseDouble(eellcal)/Double.parseDouble(reasonableval)*100);
						reultVOElect.setResultSaveVal(Double.parseDouble(df.format(result)));
					log.info("返回节能指数--[A];result="+Math.abs((Double.parseDouble(eellcal)-Double.parseDouble(reasonableval))/Double.parseDouble(reasonableval)*100)+";eellcal="+Math.abs(Double.parseDouble(eellcal))+";reasonableval="+Math.abs((Double.parseDouble(reasonableval)))+";fiducialval="+Math.abs((Double.parseDouble(fiducialval))));
					}
					if(Double.parseDouble(eellcal)>Double.parseDouble(reasonableval) && Double.parseDouble(eellcal)<=Double.parseDouble(fiducialval)){
						reultVOElect.setResultEnergy("B");
						double result=Math.abs((Double.parseDouble(eellcal)-Double.parseDouble(reasonableval))/Double.parseDouble(reasonableval)*100);
						reultVOElect.setResultSaveVal(Double.parseDouble(df.format(result)));
						log.info("返回节能指数--[B];result="+Math.abs((Double.parseDouble(eellcal)-Double.parseDouble(reasonableval))/Double.parseDouble(reasonableval)*100)+";eellcal="+Math.abs(Double.parseDouble(eellcal))+";reasonableval="+Math.abs((Double.parseDouble(reasonableval)))+";fiducialval="+Math.abs((Double.parseDouble(fiducialval))));
					}
					if(Double.parseDouble(eellcal)>Double.parseDouble(fiducialval)){
						reultVOElect.setResultEnergy("C");
						double result=Math.abs((Double.parseDouble(eellcal)-Double.parseDouble(reasonableval))/Double.parseDouble(reasonableval)*100);
						reultVOElect.setResultSaveVal(Double.parseDouble(df.format(result)));
						log.info("返回节能指数--[C];result="+Math.abs((Double.parseDouble(eellcal)-Double.parseDouble(reasonableval))/Double.parseDouble(reasonableval)*100)+";eellcal="+Math.abs(Double.parseDouble(eellcal))+";reasonableval="+Math.abs((Double.parseDouble(reasonableval)))+";fiducialval="+Math.abs((Double.parseDouble(fiducialval))));
					}
				}
			}
		}
		return reultVOElect;
	}
	
	
	public RetCode getEnergySaving(VOElectCondition voElectCondition,String hospId,String hospKind){
		RetCode rc=new RetCode();
		try{
			Map map = new HashMap();
			map.put("startTime", voElectCondition.getStartTime());
			map.put("hospId", hospId);
				rc=this.getData("getEnergySaving79", map);
		}catch (Exception e) {
			log.error("ElectricityControlBusiness-->getEnergySaving", e);
			rc.setObj(null);
		}
		return rc;
	}
	
}

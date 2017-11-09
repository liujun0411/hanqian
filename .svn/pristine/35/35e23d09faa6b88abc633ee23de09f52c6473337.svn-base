package com.hanqian.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.common.CMyShowData;
import com.hanqian.common.FeatureShowData;
import com.hanqian.form.FeatureData;
import com.hanqian.form.VOStatCondition;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysPower;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

/**
 * 特征区域能效分析业务类
 * @author clczp
 *
 */
@Service("FeatureBusiness")
public class FeatureBusiness   extends BaseBusiness {
	/**
	 * log4g日志
	 */

	//private static final Log logg = LogFactory.getLog(FeatureBusiness.class);
	//private static final Log logg = LogFactory.getLog(FeatureBusiness.class);
	private static final Logger logg = Logger.getLogger(FeatureBusiness.class);
//	@Resource    
//	private FeatureMgr feMgr;
//
//	public void setFeMgr(FeatureMgr feMgr) {
//		this.feMgr = feMgr;
//	}	
	
	
	/**
	 * 请求信息处理
	 * @param vosth
	 * @return
	 */
	private void setVOStatCondition(VOStatCondition  nowvo){	
		if (logg.isDebugEnabled()) {
			logg.debug("进入FeatureBusiness.setVOStatCondition,对象VOStatCondition:"+nowvo);	
		}
		if (SysUtil.isNull(nowvo.getStartDate())  
				&&  SysUtil.isNull(nowvo.getEndDate())) {
			Date d= new Date();
			if (nowvo.getTimeStep()==null
				|| nowvo.getTimeStep()==ETimeStep.month) {
				nowvo.setTimeStep(ETimeStep.month);								
				nowvo.setEndDate(Systime.DateToString(d, "yyyyMM"));
				d.setYear(d.getYear()-1);
				d.setMonth(d.getMonth()+1);
				nowvo.setStartDate(Systime.DateToString(d, "yyyyMM"));				
			}
			else{
				nowvo.setTimeStep(ETimeStep.year);
				nowvo.setEndDate(Systime.DateToString(d, "yyyy"));
				d.setYear(d.getYear()-4);
				nowvo.setStartDate(Systime.DateToString(d, "yyyy"));				
			}
			
		}
		
		if (SysUtil.isNull(nowvo.getPower()+"")) {
			nowvo.setPower((short) 2);			
		}
	}
	
	
	/**
	 * 查询数据
	 * @param vo
	 * @return
	 */
	public RetCode findFeatureData(VOStatCondition vo){
		if (logg.isDebugEnabled()) {
			logg.debug("进入FeatureBusiness.findFeatureData,对象VOStatCondition:"+vo);	
		}
		this.setVOStatCondition(vo);
		String title=null;
		String unitN=null;
		String unit=null;
		CMyShowData obj = new CMyShowData();
		RetCode rt = new RetCode();
				
		try {
			/*
			List<FeatureData> list = feMgr.findFeature(vo.getPower(),
								vo.getStartDate(), 
								vo.getEndDate(), 
								vo.getTimeStep());
								*/
			Map map = new HashMap();
			if (ETimeStep.year.equals(vo.getTimeStep())) {
				map.put("timestr", "substr(stattime,0,4)");				
			}	else{
				map.put("timestr", null);			
			}
			
			if (!SysUtil.isNull(vo.getStartDate())) {				
				map.put("statDate", vo.getStartDate());
			}else{
				map.put("statDate", null);
			}
			if (!SysUtil.isNull(vo.getEndDate())) {				
				map.put("endDate", vo.getEndDate());
			}else{
				map.put("endDate", null);
			}
			map.put("powerid", vo.getPower());
			
			RetCode rce = this.getData("findFeature", map);
			List<FeatureData> list = null;	
			if (rt !=null && rt.getObj() !=null) {
				list = (List)rt.getObj();
			}
			
			
			if(list !=null){
				unitN=SysPower.getName(vo.getPower());				
				if ("价值".equals(vo.getGoWhere())) {
					unit="元";
				}else{
					unit=SysPower.getUnitName(vo.getPower());
				}
				title="特征区域"+unitN+"能耗"+vo.getGoWhere();				
				obj.setMytable(FeatureShowData.getTableData(list,unit));
				obj.getMytable().setTitle(title);
				rt.setObj(obj);
			}
			else{
				rt.setCode(1002);
				rt.setDesc("暂无数据！");
				rt.setDetail("暂无数据！");
				rt.setObj(null);
			}
		}
		catch (Exception e) {
			logg.error("FeatureBusiness0-->findFeatureData", e);
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			rt.setDetail("操作失败！");
			rt.setObj(null);
		}
		
		return rt; 
	}
	
	public Map getFindFeatureDataSql() {
		return PerformSQLUtil.get("findFeature", this);
	}

	
	
	
}

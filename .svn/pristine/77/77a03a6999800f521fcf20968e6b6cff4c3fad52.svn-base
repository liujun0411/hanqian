package com.hanqian.business;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Service;

import com.hanqian.util.ColorLibray;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.PieChart;
import com.hanqian.util.RetCode;

/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 刘新
 * @version 1.4 2012-9-7
 * @see
 */
@Service("ScaledrawingBusiness")
public class ScaledrawingBusiness  extends BaseBusiness{
	public static final Logger log = Logger.getLogger(ScaledrawingBusiness.class);
//	@Resource
//	private ScaledrawingMgr drawMgr; // 比例图
//
//	public ScaledrawingMgr getDrawMgr() {
//		return drawMgr;
//	}
//
//	public void setDrawMgr(ScaledrawingMgr drawMgr) {
//		this.drawMgr = drawMgr;
//	}
	/**
	 * 楼宇用途 / 医院用途 (二选一)
	 * @param hospid
	 * @param buildid
	 * @return
	 */
	public HashMap<String,Object> findBuildArea(Integer hospid,Integer buildingId){
		//return drawMgr.findBuildArea(hospid, buildingId);
		if (log.isDebugEnabled())
			log.debug("进入ScaledrawingBusiness.findBuildArea,参数hospid:"
					+ hospid+" buildingId:"+buildingId);
		//ScaledrawingBusiness sbss = new ScaledrawingBusiness();
		return this.findBuildAreaMethod(hospid, buildingId);
	}
	
	/**
	 * 楼宇用途 / 医院用途 (二选一)
	 * 
	 * @param hospid
	 * @param buildid
	 * @return
	 */
	public HashMap<String, Object> findBuildAreaMethod(Integer hospId,
			Integer buildingId) {
		HashMap<String, Object> obj = new HashMap<String, Object>();

		// 条件
		String where = null;
		if (hospId != null) {
			where = "seqHosp='" + hospId + "' ";
		} else if (buildingId != null) {
			where = "buildingId='" + buildingId + "' ";
		} else {
			return obj;
		}
		Map mapvalue = new HashMap();
		mapvalue.put("buildingId", buildingId);
		mapvalue.put("seqHosp", hospId);
		RetCode rt = this.getData("findBuildArea", mapvalue);
		//RetCode rt = findBySQL(useDao, sql);
		if (rt.getObj() != null) {
			// 总面积
			double sumarea = 0;
			List list = (List) rt.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				sumarea += Double.parseDouble(m.get("area").toString());
			}
			if (sumarea == 0) {
				sumarea = 1;
			}

			Map color = ColorLibray.getColorStringMap();
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				m.put("color", color.get(m.get("area_id") + ""));// 颜色
				m.put("bili", new DecimalFormat("#0.00").format(Double
						.parseDouble(m.get("area").toString())
						/ sumarea * 100)); // 比例 四舍五入
			}

			obj.put("sumarea", new DecimalFormat("#0.00").format(sumarea));
			obj.put("obj", list);
		}

		return obj;
	}
	
	public Map getFindBuildAreaSql() {
		return PerformSQLUtil.get("findBuildArea", this);
	}

	
	/**
	 * 楼层用途
	 * @param hospitalid
	 * @param buildingid
	 * @param storey
	 * @return
	 */
	public HashMap<String,Object> findStoreyArea(Integer buildingId,int storey){
		//return drawMgr.findStoreyArea(buildingId, storey);
		if (log.isDebugEnabled())
			log.debug("进入ScaledrawingBusiness.findStoreyArea,参数storey:"
					+ storey+" buildingId:"+buildingId);
		return this.findStoreyAreaMethod(buildingId, storey);
	}
	
	public HashMap<String, Object> findStoreyAreaMethod(Integer buildingId, int storey) {
		HashMap<String, Object> obj = new HashMap<String, Object>();

		Map mapvalue = new HashMap();
		mapvalue.put("buildingId", buildingId);
		mapvalue.put("storey", storey);
		RetCode rt = this.getData("findStoreyAreaMethod", mapvalue);
		//RetCode rt = findBySQL(useDao, sql);
		if (rt.getObj() != null) {

			// 总面积
			double sumeare = 0;
			List list = (List) rt.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				sumeare += Double.parseDouble(m.get("acreage").toString());
			}

			// 提示信息
			Map color = ColorLibray.getColorStringMap();
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				m.put("color", color.get(m.get("area_id") + ""));// 颜色
				m.put("bili", new DecimalFormat("#0.00").format(Double
						.parseDouble(m.get("acreage").toString())
						/ sumeare * 100)); // 比例 四舍五入
			}

			obj.put("sumarea", new DecimalFormat("#0.00").format(sumeare));
			obj.put("obj", list);
		}
		return obj;
	}
	
	public Map getFindStoreyAreaSql() {
		return PerformSQLUtil.get("findStoreyArea", this);
	}
	
	
	
	
	/**
	 * 饼形比例图
	 * @param hospitalid	医院id 必需
	 * @param buildingid	楼宇id
	 * @param useImage		图片名
	 */
	public  void  findScaleDrawing(Integer hospId,Integer buildingId, String useImage){
		//drawMgr.findScaleDrawing(hospId, buildingId, useImage);
		if (log.isDebugEnabled())
			log.debug("进入ScaledrawingBusiness.findScaleDrawing,参数hospId:"
					+ hospId+" buildingId:"+buildingId+" useImage:"+useImage);
		this.findScaleDrawingMethod(hospId, buildingId, useImage);
	}
	
	public void findScaleDrawingMethod(Integer hospId, Integer buildingId,
			String useImage) {
		String sql = null;

		if (hospId != null) {
			Double rate = 0.05;
			
			Map mapvalue = new HashMap();
			mapvalue.put("buildingId", buildingId);
			mapvalue.put("hospId", hospId);
			mapvalue.put("useImage", useImage);
			RetCode rt = this.getData("findScaleDrawing", mapvalue);
			//RetCode rt = findBySQL(useDao, sql);
			List list = (List) rt.getObj();


			JFreeChart chart = (new PieChart(list)).getChart();
			if (chart != null) {
				FileOutputStream fileout = null;

				try {
					fileout = new FileOutputStream(useImage);
					ChartUtilities.writeChartAsPNG(fileout, chart, 600, 400);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ScaledrawingMgr-->findScaleDrawing",e);
					
				} finally {
					try {
						fileout.close();
					} catch (Exception ex) {
						// ex.printStackTrace();
					}
				}
			}
		}
	}

	public Map getFindScaleDrawingMethodSql() {
		return PerformSQLUtil.get("findScaleDrawing", this);
	}
	
	
}

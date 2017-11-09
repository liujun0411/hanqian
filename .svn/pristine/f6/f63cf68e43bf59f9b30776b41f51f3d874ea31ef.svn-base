package com.hanqian.business;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.springframework.stereotype.Service;

import com.hanqian.common.CMyShowData;
import com.hanqian.common.ChartData;
import com.hanqian.common.CoData;
import com.hanqian.common.EqAntoShowData;
import com.hanqian.common.EqArticleShowData;
import com.hanqian.common.EqLineChart;
import com.hanqian.common.TableData;
import com.hanqian.form.VOCoData;
import com.hanqian.form.VOEqAnalysis;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysPower;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

/**
 * 设备能耗分析
 * 
 * @author clczp
 *
 */

@Service("EqAnalysisBusiness")
public class EqAnalysisBusiness extends BaseBusiness {
	/**
	 * log4g日志
	 */
	private static final Logger logg = Logger
			.getLogger(EqAnalysisBusiness.class);

	/**
	 * 请求信息处理
	 * 
	 * @param vosth
	 * @return
	 */
	private VOEqAnalysis initVo(VOEqAnalysis vosth) {
		if (logg.isDebugEnabled())
			logg.debug("进入EqAnalysisBusiness.initVo!对象VOEqAnalysis:" + vosth);
		VOEqAnalysis vs = null;

		if (null == vosth) {
			vosth = new VOEqAnalysis();
			vs = new VOEqAnalysis();
		} else {
			vs = vosth.getBack();
		}

		// 初始化时间
		if (SysUtil.isNull(vs.getStartDate())
				&& SysUtil.isNull(vs.getEndDate())) {
			if (null == vs.getTimeStep() || vs.getTimeStep() == ETimeStep.month) {
				vs.setTimeStep(ETimeStep.month);
				// 最近一年
				Date d = new Date();
				vs.setEndDate(Systime.DateToString(d, "yyyyMM"));
				d.setYear(d.getYear() - 1);
				vs.setStartDate(Systime.DateToString(d, "yyyyMM"));
			} else {
				vs.setTimeStep(ETimeStep.year);
				// 最近5年
				Date d = new Date();
				vs.setEndDate(Systime.DateToString(d, "yyyy"));
				d.setYear(d.getYear() - 5);
				vs.setStartDate(Systime.DateToString(d, "yyyy"));
			}
			vosth.setEndDate(vs.getEndDate());
			vosth.setStartDate(vs.getStartDate());
		}

		// 初始化能源类型
		if (SysUtil.isNull(vs.getPower() + "")) {
			vs.setPower((short) 2);
			vosth.setPower(vs.getPower());
		}
		// 初始化医院
		if (SysUtil.isNull(vs.getHospid())) {

		}

		return vs;
	}

	/**
	 * 同类各设备查询
	 * 
	 * @return
	 */
	public EqArticleShowData findEqSelData() {
		if (logg.isDebugEnabled())
			logg.debug("进入EqAnalysisBusiness.findEqSelData!");
		EqArticleShowData obj = new EqArticleShowData();

		// 设备类型

		List<DbEquipType> typelist = sqlSession
				.selectList("findAllEqTypeEqArticleShowData");

		// 设备
		List<VOCoData> eqList = null;
		RetCode rt = this.getData("findeEqNameEqArticleShowData", null);
		if (null != rt.getObj()) {
			eqList = new ArrayList<VOCoData>();
			VOCoData objVOCoData = null;

			List rlist = (List) rt.getObj();
			Map m = null;
			for (int i = 0; i < rlist.size(); i++) {
				objVOCoData = new VOCoData();
				m = (Map) rlist.get(i);
				objVOCoData.setEqid(m.get("eqid") + "");
				objVOCoData.setEqcode(m.get("equipcode") + "");
				objVOCoData.setName(m.get("name") + "");
				objVOCoData.setEqtype(m.get("eq_type") + "");

				eqList.add(objVOCoData);
			}
		}

		obj.setTypelist(typelist);
		obj.setEqList(eqList);

		return obj;
	}

	public Map getFindEqSelDataSql() {
		return PerformSQLUtil.get("findAllEqTypeEqArticleShowData", this);
	}

	/**
	 * 同类各厂商查询
	 * 
	 * @return
	 */
	public EqArticleShowData findSelData() {
		if (logg.isDebugEnabled())
			logg.debug("进入EqAnalysisBusiness.findSelData!");
		EqArticleShowData obj = new EqArticleShowData();

		// 设备类型

		List<DbEquipType> typelist = sqlSession
				.selectList("findAllEqTypeEqArticleShowData");

		// 厂商
		List<VOCoData> prList = null;
		RetCode rt = this.getData("findeEqProduction", null);
		if (null != rt.getObj()) {
			prList = new ArrayList<VOCoData>();
			VOCoData objVOCoData = null;

			List rlist = (List) rt.getObj();
			for (int i = 0; i < rlist.size(); i++) {
				objVOCoData = new VOCoData();
				objVOCoData.setEqtype(((HashMap) rlist.get(i)).get("eq_type")
						.toString());
				objVOCoData.setName(((HashMap) rlist.get(i)).get("production")
						.toString());

				prList.add(objVOCoData);
			}
		}

		obj.setTypelist(typelist);
		// obj.setUnitlist(unitlist);
		obj.setPrList(prList);

		return obj;
	}

	public Map getFindSelDataSql() {
		return PerformSQLUtil.get("findeEqProduction", this);
	}

	/**
	 * 同类各厂商设备能耗量 1.处理请求参数 2.查询基础数据 3.生成图形数据 4.生成表格数据
	 * 
	 * @param nowvo
	 *            请求参数
	 * @param savePath
	 *            图形保存路径
	 * @param showPath
	 *            图形展示路径
	 * @return
	 */
	public RetCode findEqCoAnData(VOEqAnalysis nowvo, String savePath,
			String showPath) {
		if (logg.isDebugEnabled())
			logg.debug("进入EqAnalysisBusiness.findEqCoAnData!对象VOEqAnalysis:"
					+ nowvo + " savePath:" + savePath + " showPath:" + showPath);

		RetCode rt = new RetCode();
		String nowTime = null;
		String saPath = null;
		String swPath = null;
		String imgTitle = null;
		String tableTitle = null;
		VOEqAnalysis vo = this.initVo(nowvo);
		CMyShowData obj = new CMyShowData();

		try {
			List<CoData> list = this.findCoData(vo.getHospid(), vo.getPower(),
					vo.getEqtype(), vo.getConame(), vo.getStartDate(),
					vo.getEndDate(), vo.getTimeStep());

			if (null != list) {
				List<ChartData> clist = EqAntoShowData.toChartData(list);
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				nowTime = Systime.DateToString(new Date(), "yyyyMMddHHmmss");
				saPath = savePath + "\\eqAn" + nowTime + ".png";
				swPath = showPath + "eqAn" + nowTime + ".png";
				swPath = SysUtil.replacePath(swPath);
				saPath = SysUtil.replacePath(saPath);
				imgTitle = "各厂商" + SysPower.getName(vo.getPower()) + "能源使用量";
				tableTitle = imgTitle;

				if (this.saveDraw(clist, info, 650, 400, saPath, imgTitle,
						SysPower.getUnitName(vo.getPower()))) {

					obj.setMyDrawF(swPath.replaceAll("\\\\", "/"));
					obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
				}

				TableData tb = EqAntoShowData.toTableData(clist,
						SysPower.getUnitName(vo.getPower()));

				tb.setTitle(tableTitle);
				obj.setMytable(tb);
				rt.setObj(obj);
			} else {
				rt.setCode(1002);
				rt.setDesc("暂无数据！");
				rt.setDetail("暂无数据！");
				rt.setObj(null);
			}
		} catch (Exception e) {
			logg.error("EqAnalysisBusiness-->findEqCoAnData", e);
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			rt.setDetail("操作失败！");
			rt.setObj(null);
		}

		return rt;
	}

	public boolean saveDraw(List<ChartData> list, ChartRenderingInfo info,
			int width, int height, String imagePath, String title, String unit) {

		JFreeChart chart = (new EqLineChart(list, title, unit)).getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();

			return true;
		} catch (Exception e) {
			logg.error("EqAnalysisMgr-->saveDraw", e);
		}

		return false;
	}

	public List<CoData> findCoData(String hospid, Short powerId,
			String dicType, String conames, String startDate, String endDate,
			ETimeStep step) {

		List<CoData> list = null;

		Map map = new HashMap();
		if (ETimeStep.year.equals(step)) {
			map.put("stattime", "");
		} else {
			map.put("stattime", null);
		}

		map.put("hospid", hospid);
		map.put("dicType", dicType);
		map.put("powerId", powerId);

		if (null != startDate) {
			map.put("startDate", startDate);
		} else {
			map.put("startDate", null);
		}
		if (null != endDate) {
			map.put("endDate", endDate);
		} else {
			map.put("endDate", null);
		}

		RetCode rt = this.getData("findCoData", map);
		if (null != rt.getObj()) {
			List tmp = (List) rt.getObj();
			Map m = null;
			CoData obj = null;
			list = new ArrayList<CoData>();
			for (int i = 0; i < tmp.size(); i++) {
				m = (Map) tmp.get(i);
				obj = new CoData();
				obj.setName(m.get("production").toString());
				obj.setStattime(m.get("stattime").toString());
				obj.setValue(SysUtil.toDouble(m.get("wastage").toString()));
				list.add(obj);
			}
		}

		return list;
	}

	public Map getFindCoDataSql() {
		return PerformSQLUtil.get("findCoData", this);
	}

	/**
	 * 同类各设备能耗量 1.处理请求参数 2.查询基础数据 3.生成图形数据 4.生成表格数据
	 * 
	 * @param nowvo
	 * @param savePath
	 * @param showPath
	 * @return
	 */
	public RetCode findEqAnData(VOEqAnalysis nowvo, String savePath,
			String showPath) {
		if (logg.isDebugEnabled())
			logg.debug("进入EqAnalysisBusiness.findEqAnData!对象VOEqAnalysis:"
					+ nowvo + " savePath:" + savePath + " showPath:" + showPath);

		RetCode rt = new RetCode();
		String nowTime = null;
		String saPath = null;
		String swPath = null;
		String imgTitle = null;
		String tableTitle = null;
		VOEqAnalysis vo = this.initVo(nowvo);
		CMyShowData obj = new CMyShowData();

		try {
			List<CoData> list = this.findEqData(vo.getHospid(), vo.getPower(),
					vo.getEqtype(), vo.getEqcodes(), vo.getStartDate(),
					vo.getEndDate(), vo.getTimeStep());

			if (null != list) {
				List<ChartData> clist = EqAntoShowData.toChartData(list);
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				nowTime = Systime.DateToString(new Date(), "yyyyMMddHHmmss");
				saPath = savePath + "\\eqAn" + nowTime + ".png";
				swPath = showPath + "eqAn" + nowTime + ".png";

				swPath = SysUtil.replacePath(swPath);
				saPath = SysUtil.replacePath(saPath);
				imgTitle = "各设备" + SysPower.getName(vo.getPower()) + "能源使用量";
				tableTitle = imgTitle;

				if (this.saveDraw(clist, info, 650, 400, saPath, imgTitle,
						SysPower.getUnitName(vo.getPower()))) {

					obj.setMyDrawF(swPath.replaceAll("\\\\", "/"));
					obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
				}

				TableData tb = EqAntoShowData.toTableData(clist,
						SysPower.getUnitName(vo.getPower()));

				tb.setTitle(tableTitle);
				obj.setMytable(tb);
				rt.setObj(obj);
			} else {
				rt.setCode(1002);
				rt.setDesc("暂无数据！");
				rt.setDetail("暂无数据！");
				rt.setObj(null);
			}
		} catch (Exception e) {
			logg.error("EqAnalysisBusiness-->findEqAnData", e);

			rt.setCode(1004);
			rt.setDesc("操作失败！");
			rt.setDetail("操作失败！");
			rt.setObj(null);
			e.printStackTrace();
		}

		return rt;
	}

	/**
	 * 查询数据(同类各设备能耗量)
	 * 
	 * @param hospid
	 *            医院Id
	 * @param powerId
	 *            能源Id
	 * @param dicType
	 *            设备类型
	 * @param eqcodes
	 *            设备编号集
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param step
	 *            时间隔
	 * @return
	 */
	public List<CoData> findEqData(String hospid, Short powerId,
			String dicType, String eqcodes, String startDate, String endDate,
			ETimeStep step) {

		List<CoData> list = null;

		Map map = new HashMap();

		if (ETimeStep.year.equals(step)) {
			map.put("stattime", "");
		} else {
			map.put("stattime", null);
		}

		map.put("hospid", hospid);
		map.put("dicType", dicType);
		map.put("powerId", powerId);

		if (null != startDate) {
			map.put("startDate", startDate);
		} else {
			map.put("startDate", null);
		}
		if (null != endDate) {
			map.put("endDate", endDate);
		} else {
			map.put("endDate", null);
		}

		map.put("conames", "'" + eqcodes.replace(",", "','") + "'");

		RetCode rt = this.getData("findEqData", map);
		if (null != rt.getObj()) {
			List tmp = (List) rt.getObj();
			HashMap m = null;
			CoData obj = null;
			list = new ArrayList<CoData>();
			for (int i = 0; i < tmp.size(); i++) {
				m = (HashMap) tmp.get(i);
				obj = new CoData();
				obj.setName(m.get("eqname").toString());
				obj.setStattime(m.get("stattime").toString());
				obj.setValue(SysUtil.toDouble(m.get("wastage").toString()));
				list.add(obj);
			}
		}

		return list;
	}

	public Map getFindEqDataSql() {
		return PerformSQLUtil.get("findEqData", this);
	}

}

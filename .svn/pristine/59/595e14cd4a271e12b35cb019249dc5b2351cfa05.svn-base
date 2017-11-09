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
import com.hanqian.common.EqArticleShowData;
import com.hanqian.common.EqRepairLineChart;
import com.hanqian.common.EqRepairShowData;
import com.hanqian.common.TableData;
import com.hanqian.form.EqRepairData;
import com.hanqian.form.VOCoData;
import com.hanqian.form.VOEqRepair;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.util.ETimeStep;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

/**
 * 设备维修比较业务类
 * 
 * @author clczp
 *
 */
@Service("EqRepairBusiness")
public class EqRepairBusiness extends BaseBusiness {
	/**
	 * log4g日志
	 */

	private static final Logger logg = Logger.getLogger(EqRepairBusiness.class);

	/**
	 * 请求信息处理
	 * 
	 * @param vosth
	 * @return
	 */
	private void setVOEqRepair(VOEqRepair nowvo) {
		if (nowvo == null) {
			nowvo = new VOEqRepair();
		}

		if (SysUtil.isNull(nowvo.getStartDate())
				&& SysUtil.isNull(nowvo.getEndDate())) {
			Date d = new Date();
			if (nowvo.getTimeStep() == null
					|| nowvo.getTimeStep() == ETimeStep.month) {
				nowvo.setTimeStep(ETimeStep.month);
				nowvo.setEndDate(Systime.DateToString(d, "yyyyMM"));
				d.setYear(d.getYear() - 1);
				nowvo.setStartDate(Systime.DateToString(d, "yyyyMM"));
			} else {
				nowvo.setTimeStep(ETimeStep.year);
				nowvo.setEndDate(Systime.DateToString(d, "yyyy"));
				d.setYear(d.getYear() - 5);
				nowvo.setStartDate(Systime.DateToString(d, "yyyy"));
			}

		}
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
		return PerformSQLUtil.get("findeEqNameEqArticleShowData", this);
	}

	/**
	 * 设备维修比较 1.处理请求参数 2.查询基础数据 3.生成图形数据 4.生成表格数据
	 * 
	 * @param nowvo
	 *            请求参数
	 * @param savePath
	 *            图形保存路径
	 * @param showPath
	 *            图形展示路径
	 * @param voobj
	 * @param savePath
	 * @param showPath
	 * @return
	 */
	public RetCode findEqRpairData(VOEqRepair vo, String savePath,
			String showPath) {
		if (logg.isDebugEnabled())
			logg.debug("进入EqAnalysisBusiness.findEqRpairData!对象VOEqRepair:"
					+ vo + " savePath:" + savePath + " showPath:" + showPath);
		RetCode rt = new RetCode();
		String nowTime = null;
		String saPath = null;
		String swPath = null;
		String imgTitle = null;
		String tableTitle = null;
		setVOEqRepair(vo);
		CMyShowData obj = new CMyShowData();
		try {
			List<EqRepairData> list = this.findEqRepairData(vo.getEqids(),
					vo.getStartDate(), vo.getEndDate(), vo.getTimeStep());

			if (null != list) {
				EqRepairShowData showData = new EqRepairShowData(list, "次");
				List<ChartData> clist = showData.getChartData();
				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				nowTime = Systime.DateToString(new Date(), "yyyyMMddHHmmss");
				saPath = savePath + "\\eqAn" + nowTime + ".png";
				swPath = showPath + "eqAn" + nowTime + ".png";
				// swPath = swPath.replace("\\", "/");

				saPath = SysUtil.replacePath(saPath);
				swPath = SysUtil.replacePath(swPath);
				imgTitle = "各设备维修比较";
				tableTitle = imgTitle;

				if (this.saveDraw(clist, info, 650, 400, saPath, imgTitle, "次")) {
					obj.setMyDrawF(swPath.replaceAll("\\\\", "/"));
					obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
				}

				TableData tb = showData.getTableData();
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
			logg.error("EqRepairBusiness-->findEqRpairData", e);
			// TODO: handle exception
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			rt.setDetail("操作失败！");
			rt.setObj(null);
			e.printStackTrace();
		}
		return rt;
	}

	public boolean saveDraw(List<ChartData> list, ChartRenderingInfo info,
			int width, int height, String imagePath, String title, String unit) {

		JFreeChart chart = (new EqRepairLineChart(list, title, unit))
				.getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();

			return true;
		} catch (Exception e) {
			logg.error("EqRepairMgr-->saveDraw", e);
		}

		return false;
	}

	/**
	 * 设备维修记录
	 * 
	 * @param eqids
	 *            设备ID集
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param step
	 *            时间隔
	 * @return
	 */
	public List<EqRepairData> findEqRepairData(String eqids, String startDate,
			String endDate, ETimeStep step) {

		List<EqRepairData> list = null;
		Map map = new HashMap();

		String str_time = "to_char(m.servicetime,'yyyyMM')";
		if (ETimeStep.year.equals(step)) {
			map.put("stattime", "");
		} else {
			map.put("stattime", null);
		}

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

		map.put("conames", "'" + eqids.replace(",", "','") + "'");

		// RetCode rt=this.findBySQL(eqDao, sql.toString());
		RetCode rt = this.getData("findEqRepairData", map);

		if (rt.getObj() != null) {
			List rlist = (List) rt.getObj();
			list = new ArrayList<EqRepairData>();
			EqRepairData obj = null;
			HashMap m = null;
			for (int i = 0; i < rlist.size(); i++) {
				m = (HashMap) rlist.get(i);
				obj = new EqRepairData();
				obj.setEqid(m.get("eqid") + "");
				obj.setEqname(m.get("name") + "");
				obj.setRcount(SysUtil.toInt(m.get("rcount") + ""));
				obj.setStattime(m.get("stattime") + "");

				list.add(obj);
			}
		}
		return list;
	}

	public Map getFindEqRepairDataSql() {
		return PerformSQLUtil.get("findEqRepairData", this);
	}

}

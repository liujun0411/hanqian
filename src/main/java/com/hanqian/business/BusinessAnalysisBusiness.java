package com.hanqian.business;

import java.io.FileOutputStream;
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

import com.hanqian.common.BusinessAtoShowDate;
import com.hanqian.common.BusinessLineChart;
import com.hanqian.common.BusinessRecChart;
import com.hanqian.common.CMyShowData;
import com.hanqian.common.ChartData;
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
 * 业务分析
 * 
 * @author clczp
 *
 */
@Service
public class BusinessAnalysisBusiness extends BaseBusiness {
	/**
	 * log4g日志
	 */
	public static final Logger logg = Logger
			.getLogger(BusinessAnalysisBusiness.class);

	/**
	 * 请求信息处理
	 * 
	 * @param vs
	 * @return
	 */
	private VOStatCondition getVOStHospDA(VOStatCondition vs, DbUsers dbusers) {
		if (logg.isDebugEnabled())
			logg.debug("进入BuildStoreyBusiness.getVOStHospDA!对象VOStatCondition:"
					+ vs + " DbUsers:" + dbusers);
		if (null == vs) {
			vs = new VOStatCondition();
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
				d.setMonth(d.getMonth() + 1);
				vs.setStartDate(Systime.DateToString(d, "yyyyMM"));
			} else {
				vs.setTimeStep(ETimeStep.year);
				// 最近5年
				Date d = new Date();
				vs.setEndDate(Systime.DateToString(d, "yyyy"));
				d.setYear(d.getYear() - 5);
				vs.setStartDate(Systime.DateToString(d, "yyyy"));
			}
		}

		// 初始化能源类型
		if (SysUtil.isNull(vs.getPower() + "")) {
			vs.setPower((short) 2);
		}
		// 初始化医院
		if (SysUtil.isNull(vs.getHospid())) {
			DbHospInfo dbhos = dbusers.getDbHospInfo();
			if (dbhos != null) {
				vs.setHospid(dbhos.getSeqHosp().toString());
			} else {
				List<DbHospInfo> lstanalysis = sqlSession
						.selectList("findAllHosAnalysis");
				vs.setHospid(lstanalysis.get(0).getSeqHosp().toString());
				// vs.setHospid(((List<DbHospInfo>)hosMgr.findAllHos()).get(0).getSeqHosp().toString());
			}
		}

		return vs;
	}

	public Map getGetVOStHospDASql() {
		return PerformSQLUtil.get("findAllHosAnalysis", this);
	}

	/**
	 * 业务类型名
	 * 
	 * @param busType
	 * @return
	 */
	private String getClmName(int busType) {
		String str = "门诊";
		if (2 == busType) {
			str = "急诊";
		} else if (3 == busType) {
			str = "住院";
		}

		return str;
	}

	/**
	 * 业务能耗/分析(含吨标煤)
	 * 
	 * @param voobj
	 * @param busType
	 * @param savePath
	 * @param showPath
	 * @param dbusers
	 * @param isWastage
	 *            能耗量 ，能耗价值
	 * @param isAnalysis
	 *            分析
	 * @return
	 */
	public RetCode findBuild(VOStatCondition voobj, int busType,
			String savePath, String showPath, DbUsers dbusers,
			boolean isWastage, boolean isAnalysis) {

		CMyShowData obj = null;

		RetCode rt = new RetCode();
		String name = this.getClmName(busType);
		Log log = LogFactory.getLog(HospDataAnalysisBusiness.class);

		try {

			voobj = this.getVOStHospDA(voobj, dbusers);

			// 查询数据
			List rlist = null;// 源数据
			List blist = null;// 分析标准
			if (isAnalysis) {

			} else {
				if (isWastage) {
					// 能耗量
					rlist = this.findStatBusiness(voobj.getHospid(), busType,
							voobj.getPower(), voobj.getStartDate(),
							voobj.getEndDate(), voobj.getTimeStep());
				} else {
					// 能耗价值
					rlist = this.findStatConvert(voobj.getHospid(), busType,
							voobj.getPower(), voobj.getStartDate(),
							voobj.getEndDate(), voobj.getTimeStep());
				}
			}

			if (rlist != null) {
				obj = new CMyShowData();
				// String hosName=hosMgr.findNameById(voobj.getHospid());
				int hospid = Integer.valueOf(voobj.getHospid());
				DbHospInfo hio = sqlSession.selectOne("findNameByIdHospInfo",
						hospid);
				String hosName = hio.getHospName();

				String title = hosName + SysPower.getName(voobj.getPower())
						+ "能源" + name + "能耗";

				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				BusinessAtoShowDate toDate = new BusinessAtoShowDate(
						voobj.getPower());

				String valKey = isWastage ? "wastage" : "convert";
				String valUntit = isWastage ? "" : "价值";
				String unit = isWastage ? SysPower
						.getUnitName(voobj.getPower()) : "元";
				String nowtime = Systime.DateToString(new Date(),
						"yyyyMMddHHmmss");
				String smPath = savePath + "\\buildanlysis" + nowtime + ".png"; // 生成图片名
				String shPath = showPath + "buildanlysis" + nowtime + ".png"; // 显示图片路径
				// shPath = shPath.replace("\\", "/");

				smPath = SysUtil.replacePath(smPath);
				shPath = SysUtil.replacePath(shPath);
				title += valUntit;
				if (isAnalysis) {
					title += "分析";
					unit = "占比";
				}

				List<ChartData> rclist = null;// 源
				List<ChartData> bclist = null;// 标准
				if (0 == voobj.getPower() && !isWastage) {
					unit = "综合";
				} else if (0 == voobj.getPower() && isWastage) {
					unit = "吨标煤";
				}

				rclist = toDate.getChartData(rlist, valKey, valUntit,
						voobj.getPower());

				// 加入标准
				boolean lastBlack = false;
				if (blist != null) {
					bclist = toDate.getChartData(blist, "value", "标准",
							voobj.getPower());
					rclist.addAll(bclist);
					lastBlack = true;
				}
				if (isWastage) {
					// 业务能耗量
					if (this.saveDrawAnalysis(rclist, info, 650, 400, smPath,
							title, unit)) {
						obj.setMyDrawF(shPath.replaceAll("\\\\", "/"));
						obj.setUseMapF(ChartUtilities.getImageMap("useMapF",
								info));
					}
				} else {
					// 业务能耗价值
					if (this.saveDraw(rclist, info, 650, 400, smPath, title,
							unit, lastBlack)) {

						obj.setMyDrawF(shPath.replaceAll("\\\\", "/"));
						obj.setUseMapF(ChartUtilities.getImageMap("useMapF",
								info));
					}
				}

				// 表格数据
				if (bclist != null) {
					// 移除标准
					rclist.removeAll(bclist);
				}

				obj.setMytable(toDate.getTableDate(rclist, unit, bclist,
						voobj.getPcha()));
				obj.getMytable().setTitle(title);

				rt.setObj(obj);
			} else {
				rt.setCode(1002);
				rt.setDesc("暂无数据！");
			}
			rt.setObj(obj);
		} catch (Exception e) {
			logg.error("BusinessAnalysisBusiness-->findBuild", e);
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			log.debug(e);
			e.printStackTrace();
		}

		return rt;
	}

	public Map getFindBuildSql() {
		return PerformSQLUtil.get("findNameByIdHospInfo", this);
	}

	public List findStatBusiness(String hospid, int busType, Short power,
			String startDate, String endDate, ETimeStep step) {
		if (endDate != null && endDate.length() == 4) {
			Date d = new Date();
			d.setYear(Systime.StringToDate(endDate, "yyyy").getYear() + 1);
			endDate = Systime.DateToString(d, "yyyy");
		}
		if (SysUtil.isNull(startDate)) {
			startDate = "0";
		}
		if (SysUtil.isNull(endDate)) {
			endDate = "999999";
		}

		boolean isYear = ETimeStep.month.equals(step) ? false : true;

		if (0 == power) {
			// 综合
			// return findStatZH(hospid,busType,startDate, endDate ,isYear);
			Map map = new HashMap();
			map.put("hospid", hospid);
			map.put("busType", busType);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			if (isYear) {
				map.put("isYear", "isYear");
			} else {
				map.put("isYear", null);
			}

			RetCode rt = this.getData("findStatZH", map);
			List list = null;
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}
			return list;
		} else {
			// return findStat(hospid,busType,power, startDate, endDate
			// ,isYear);
			Map map = new HashMap();
			map.put("hospid", hospid);
			map.put("busType", busType);
			map.put("power", power);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			if (isYear) {
				map.put("isYear", "isYear");
			} else {
				map.put("isYear", null);
			}

			RetCode rt = this.getData("findStat", map);
			List list = null;
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}
			return list;
		}
	}

	public List findStatConvert(String hospid, int busType, Short power,
			String startDate, String endDate, ETimeStep step) {
		if (endDate != null && endDate.length() == 4) {
			Date d = new Date();
			d.setYear(Systime.StringToDate(endDate, "yyyy").getYear() + 1);
			endDate = Systime.DateToString(d, "yyyy");
		}
		if (SysUtil.isNull(startDate)) {
			startDate = "0";
		}
		if (SysUtil.isNull(endDate)) {
			endDate = "999999";
		}

		boolean isYear = ETimeStep.month.equals(step) ? false : true;

		if (0 == power) {
			// 综合
			// return findStatZHConvert(hospid,busType,startDate, endDate
			// ,isYear);
			Map map = new HashMap();
			map.put("hospid", hospid);
			map.put("busType", busType);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			if (isYear) {
				map.put("isYear", "isYear");
			} else {
				map.put("isYear", null);
			}

			RetCode rt = this.getData("findStatZHConvert", map);
			List list = null;
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}
			return list;
		} else {
			// return findStatConvert(hospid,busType,power, startDate, endDate
			// ,isYear);
			Map map = new HashMap();
			map.put("hospid", hospid);
			map.put("busType", busType);
			map.put("power", power);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			if (isYear) {
				map.put("isYear", "isYear");
			} else {
				map.put("isYear", null);
			}

			RetCode rt = this.getData("findStatConvert", map);
			List list = null;
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}
			return list;
		}
	}

	public boolean saveDrawAnalysis(List<ChartData> list,
			ChartRenderingInfo info, int width, int height, String imagePath,
			String title, String unit) {
		JFreeChart chart = (new BusinessRecChart(list, title, unit)).getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();

			return true;
		} catch (Exception e) {
			logg.error("BusinessAnalysisBusiness->saveDrawAnalysis", e);
			// e.printStackTrace();
		}

		return false;
	}

	/**
	 * 业务数据折线图
	 * 
	 * @param list
	 * @param info
	 * @param width
	 * @param height
	 * @param imagePath
	 * @param title
	 * @param unit
	 * @param lastBlack
	 *            最后线为黑色
	 * @return
	 */
	public boolean saveDraw(List<ChartData> list, ChartRenderingInfo info,
			int width, int height, String imagePath, String title, String unit,
			boolean lastBlack) {
		JFreeChart chart = (new BusinessLineChart(list, title, unit, lastBlack))
				.getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();

			return true;
		} catch (Exception e) {
			logg.error("BusinessAnalysisMgr-->saveDraw", e);
			// e.printStackTrace();
		}

		return false;
	}

}

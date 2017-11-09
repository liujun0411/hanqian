package com.hanqian.business;

import java.io.File;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanqian.common.BuildAtoShowDate;
import com.hanqian.common.BuildLineChart;
import com.hanqian.common.CMyShowData;
import com.hanqian.common.ChartData;
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
 * 楼宇数据分析
 * 
 * @author clczp
 *
 */
@Service
public class BuildAnalysisBusiness extends BaseBusiness {
	/**
	 * log4g日志
	 */
	private static final Logger logg = Logger
			.getLogger(BuildAnalysisBusiness.class);

	@Autowired
	private WeatherBusiness weatherBusiness; // 天气情况

	/**
	 * 请求信息处理
	 * 
	 * @param vs
	 * @return
	 */
	private VOStatCondition getVOStHospDA(VOStatCondition vs, DbUsers dbusers) {
		if (logg.isDebugEnabled())
			logg.debug("进入BuildAnalysisBusiness.getVOStHospDA,对象VOStatCondition:"
					+ vs + " DbUsers" + dbusers);
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
				// vs.setHospid(((List<DbHospInfo>)hosMgr.findAllHos()).get(0).getSeqHosp().toString());
				List<DbHospInfo> listdbhinfo = sqlSession
						.selectList("findAllHos");
				vs.setHospid(listdbhinfo.get(0).getSeqHosp().toString());
			}
		}

		// 初始化楼宇(默认5条记录)
		if (SysUtil.isNull(vs.getSelUnits())) {
			// RetCode rc =
			// buildMgr.findBuildNameAndSequence(Integer.parseInt(vs.getHospid()));
			Map map = new HashMap();
			map.put("hospId", Integer.parseInt(vs.getHospid()));
			RetCode rc = this.getData("findBuildNameAndSequence", map);
			if (rc != null && rc.getObj() != null) {
				List blist = (List) rc.getObj();
				HashMap m = null;
				String buildids = "";
				for (int i = 0; i < blist.size() && i <= 5; i++) {
					if (i != 0) {
						buildids += "','";
					}
					m = (HashMap) blist.get(i);
					buildids += m.get("sequence");
				}
				vs.setSelUnits(buildids);
			}
		}

		return vs;
	}

	public Map getGetVOStHospDASql() {
		return PerformSQLUtil.get("findBuildNameAndSequence", this);
	}

	/**
	 * 楼宇能耗/分析(含吨标煤)
	 * 
	 * @param voobj
	 * @param savePath
	 * @param showPath
	 * @param dbusers
	 * @param isWastage
	 * @param isAnalysis
	 * @return
	 */
	public RetCode findBuild(VOStatCondition voobj, String savePath,
			String showPath, DbUsers dbusers, boolean isWastage,
			boolean isAnalysis) {
		if (logg.isDebugEnabled())
			logg.debug("进入BuildAnalysisBusiness.findBuild,对象VOStatCondition:"
					+ voobj + " savePath" + savePath + " DbUsers" + dbusers);

		CMyShowData obj = null;

		RetCode rt = new RetCode();

		Log log = LogFactory.getLog(HospDataAnalysisBusiness.class);

		try {

			voobj = this.getVOStHospDA(voobj, dbusers);

			// 查询数据
			List rlist = null;// 源数据
			List blist = null;// 分析标准
			if (isAnalysis) {
				rlist = this.findStatBulidAnalysis(voobj.getHospid(),
						voobj.getSelUnits(), voobj.getPower(),
						voobj.getStartDate(), voobj.getEndDate(),
						voobj.getTimeStep(), isWastage);
				if (!SysUtil.isNull(voobj.getBaseLine())) {
					blist = this.findStandard(voobj.getBaseLine(),
							voobj.getStartDate(), voobj.getEndDate(),
							voobj.getTimeStep());
				}
			} else {
				rlist = this.findStatBulid(voobj.getHospid(),
						voobj.getSelUnits(), voobj.getPower(),
						voobj.getStartDate(), voobj.getEndDate(),
						voobj.getTimeStep(), isWastage);
			}

			if (rlist != null) {
				obj = new CMyShowData();
				// String hosName=hosMgr.findNameById(voobj.getHospid());
				String sequence = voobj.getHospid();
				String hosName = ((Map) (sqlSession.selectList("findNameById",
						sequence).get(0))).get("hosp_name") + "";

				String title = hosName + SysPower.getName(voobj.getPower())
						+ "能源";

				StandardEntityCollection sec = new StandardEntityCollection();
				ChartRenderingInfo info = new ChartRenderingInfo(sec);
				BuildAtoShowDate toDate = new BuildAtoShowDate(voobj.getPower());

				String valKey = isWastage ? "wastage" : "convert";
				String valUntit = isWastage ? "使用量" : "折算金额";
				String unit = isWastage ? SysPower
						.getUnitName(voobj.getPower()) : "元";
				String name = Systime
						.DateToString(new Date(), "yyyyMMddHHmmss");
				String smPath = savePath + "\\buildanlysis" + name + ".png"; // 生成图片名
				String shPath = showPath + "buildanlysis" + name + ".png"; // 显示图片路径
				// shPath = shPath.replaceAll("\\", "/");
				smPath = this.replacePath(smPath);
				shPath = this.replacePath(shPath);
				title += valUntit;
				if (isAnalysis) {
					title += "分析";
					unit = "占比";
				}

				List<ChartData> rclist = null;// 源
				List<ChartData> bclist = null;// 标准
				if (0 == voobj.getPower()) {
					// 吨标煤
					valKey = "dbm";
				}

				rclist = toDate.getChartData(rlist, valKey, valUntit,
						voobj.getPower(), SysPower.getUnitEN(voobj.getPower()));

				// 加入标准
				boolean lastBlack = false;
				if (blist != null) {
					bclist = toDate.getChartData(blist, "value", "标准",
							voobj.getPower(), "");
					rclist.addAll(bclist);
					lastBlack = true;
				}

				// 添加倍率化后的气温
				boolean addRate = false;
				List<ChartData> wlist = null;
				if (!isAnalysis && !lastBlack
						&& voobj.getTimeStep().equals(ETimeStep.month)
						&& voobj.getPower() != 0) {
					float rate = WeatherRate.findReate(rclist);
					wlist = weatherBusiness.findAvgWeatherRate("上海",
							voobj.getStartDate(), voobj.getEndDate(), rate);
					addRate = rclist.addAll(wlist);
				}

				// 绘图
				if (this.saveDraw(rclist, info, 650, 400, smPath, title, unit,
						lastBlack)) {

					obj.setMyDrawF(shPath.replaceAll("\\\\", "/"));
					obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));
				}

				// 移除倍化后的气温
				if (addRate) {
					rclist.removeAll(wlist);
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
			rt.setCode(1004);
			rt.setDesc("操作失败！");
			log.debug(e);
			logg.error("BuildAnalysisBusiness-->findBuild", e);
		}

		return rt;
	}

	// Manager移动过来的方法
	public List findStatBulidAnalysis(String hospid, String builds,
			Short power, String startDate, String endDate, ETimeStep step,
			boolean isWastage) {
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
			return null;// findZHAnalysis(hospid,builds,startDate, endDate
						// ,isYear);
		} else {
			List list = null;
			Map map = new HashMap();
			if (isYear) {
				map.put("isYear", "isYear");
			} else {
				map.put("isYear", null);
			}
			if (isWastage) {
				map.put("isWastage", "isWastage");
			} else {
				map.put("isWastage", null);
			}
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("power", power);
			map.put("hospid", hospid);
			// 原始方法中有传递此参数，但是具体语句中无
			map.put("builds", builds);
			RetCode rt = this.getData("findStBAnalysis", map);
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}
			return list;
			// return findStBAnalysis(hospid,builds,power, startDate, endDate
			// ,isYear,isWastage);
		}
	}

	public Map getFindStatBulidAnalysisSql() {
		return PerformSQLUtil.get("findStBAnalysis", this);
	}

	// 新增manager方法
	public List findStandard(String name, String startDate, String endDate,
			ETimeStep step) {
		List list = null;

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

		Map map = new HashMap();
		if (isYear) {
			map.put("isYear", "isYear");
		} else {
			map.put("isYear", null);
		}
		map.put("name", name);
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		RetCode rt = this.getData("findBySQLPar", map);
		if (rt != null && rt.getObj() != null) {
			list = (List) rt.getObj();
		}

		return list;
	}

	public List findStatBulid(String hospid, String builds, Short power,
			String startDate, String endDate, ETimeStep step, boolean isWastage) {
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
			List list = null;
			Map map = new HashMap();
			if (isYear) {
				map.put("isYear", "isYear");
			} else {
				map.put("isYear", null);
			}
			map.put("hospid", hospid);
			map.put("builds", builds);
			map.put("startDate", startDate);
			map.put("endDate", endDate);

			RetCode rt = this.getData("findZH", map);
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}

			return list;
			// 综合
			// return findZH(hospid,builds,startDate, endDate ,isYear);
		} else {
			List list = null;
			Map map = new HashMap();
			if (isYear) {
				map.put("isYear", "isYear");
			} else {
				map.put("isYear", null);
			}
			if (isWastage) {
				map.put("isWastage", "isWastage");
			} else {
				map.put("isWastage", null);
			}
			map.put("hospid", hospid);
			map.put("builds", builds);
			map.put("power", power);
			map.put("startDate", startDate);
			map.put("endDate", endDate);

			RetCode rt = this.getData("findStB", map);
			if (rt != null && rt.getObj() != null) {
				list = (List) rt.getObj();
			}

			return list;
			// return findStB(hospid,builds,power, startDate, endDate
			// ,isYear,isWastage);
		}
	}

	public boolean saveDraw(List<ChartData> list, ChartRenderingInfo info,
			int width, int height, String imagePath, String title, String unit,
			boolean lastBlack) {
		JFreeChart chart = (new BuildLineChart(list, title, unit, lastBlack))
				.getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();

			return true;
		} catch (Exception e) {
			logg.error("BuildAnalysisBusiness-->saveDraw", e);
			// e.printStackTrace();
		}

		return false;
	}

	private static String replacePath(String rootPath) {
		if ("\\".equals(File.separator)) {
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = rootPath.replace("\\", "/");
		}
		return rootPath;
	}

	/**
	 * @return the weatherBusiness
	 */
	public WeatherBusiness getWeatherBusiness() {
		return weatherBusiness;
	}

	/**
	 * @param weatherBusiness
	 *            the weatherBusiness to set
	 */
	@Autowired
	public void setWeatherBusiness(WeatherBusiness weatherBusiness) {
		this.weatherBusiness = weatherBusiness;
	}

}

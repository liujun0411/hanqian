package com.hanqian.business;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.springframework.stereotype.Service;

import com.hanqian.common.AreaRatioBData;
import com.hanqian.common.AreaRatioData;
import com.hanqian.common.BiLiChart;
import com.hanqian.common.CMyListData;
import com.hanqian.common.CMyShowData;
import com.hanqian.common.ChartData;
import com.hanqian.pojo.DbBuildingrate;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.AreaChart;
import com.hanqian.util.OneUseBiLiChart;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;
import com.hanqian.util.Systime;

@Service
public class AreaRatioBusiness extends BaseBusiness {
	private static final Logger logg = Logger
			.getLogger(AreaRatioBusiness.class);
	private boolean isAlone = false;
	private String hospName;

	/**
	 * 各医院区域面积比例情况
	 * 
	 * @param dbusers
	 *            登陆用户
	 * @param selUnits
	 *            分析对象(医院Id集合)
	 * @param useId
	 *            区域Id
	 * @return
	 */
	public RetCode findUseArea(DbUsers dbusers, String selUnits, Integer useId) {
		RetCode rt = new RetCode();
		try {
			// 医院
			String[] hospid = this.getHospIds(dbusers, selUnits);
			List<AreaRatioData> oldlist = this.findOneQuyuAreaBiliData(hospid,
					useId + "");// 各医院useId区域面积比例
			List<AreaRatioBData> baselist = this.findQuyuAreaBaseData();// 区域面积基准

			if (null != oldlist && null != baselist) {
				CMyListData list = new CMyListData(oldlist, baselist);
				rt.setObj(list);
			} else {
				rt.setDetail("暂无数据！");
			}
		} catch (Exception e) {
			rt.setCode(1004);
			rt.setDetail("操作失败,请联系管理员！");
			e.printStackTrace();
			logg.error("AreaRatioBusiness-->findUseArea", e);
			rt.setObj(null);

		}

		return rt;
	}

	/**
	 * 字符解析
	 * 
	 * @param dbusers
	 *            用户
	 * @param selUnits
	 *            医院集合
	 * @return
	 */
	private String[] getHospIds(DbUsers dbusers, String selUnits) {
		String[] hospids = new String[1];
		if (SysUtil.isNull(selUnits)) {

			if (null == dbusers.getDbHospInfo()) {

				List lst = sqlSession.selectList("findHospInfoAll");
				DbHospInfo dbHospInfo = (DbHospInfo) lst.get(0);
				hospids[0] = dbHospInfo.getSeqHosp().toString();

			} else {
				DbHospInfo hosp = dbusers.getDbHospInfo();
				hospids[0] = hosp.getSeqHosp().toString();
			}
		} else {
			hospids = selUnits.split(",");
		}

		if (hospids.length == 1) {
			isAlone = true;
			hospName = this.getHospName(hospids[0]);
		} else {
			isAlone = false;
		}
		return hospids;
	}

	/**
	 * 单区域面积比例
	 * 
	 * @param hospids
	 *            医院Id集
	 * @param usetyeid
	 *            区域类型
	 * @return 
	 *         id(区域Id),name(区域名),unitname(医院名称),datavalue(比例),area(面积),sarea(总面积
	 *         )
	 */
	public List<AreaRatioData> findOneQuyuAreaBiliData(String[] hospids,
			String usetyeid) {
		String str = "''";
		for (int i = 0; null != hospids && i < hospids.length; i++) {
			str += ",'" + hospids[i] + "'";
		}
		Map param = new HashMap();
		param.put("str", str);
		param.put("usetyeid", usetyeid);
		RetCode rt = this.getData("findOneQuyuAreaBiliData", param);

		return this.getAreaRatioDataList(rt, hospids);
	}

	/**
	 * 区域面积比例 基准
	 * 
	 * @return
	 */
	private List<AreaRatioBData> findQuyuAreaBaseData() {
		List<AreaRatioBData> list = null;

		RetCode rt = this.getData("findQuyuAreaBaseData", null);

		if (null != rt.getObj()) {
			List temp = (List) rt.getObj();
			list = new ArrayList<AreaRatioBData>();
			for (int i = 0; i < temp.size(); i++) {
				HashMap map = (HashMap) temp.get(i);
				AreaRatioBData bData = new AreaRatioBData();
				bData.setId(map.get("id").toString());
				bData.setName(map.get("name").toString());
				if (null != map.get("comparechar")) {
					bData.setComparechar(map.get("comparechar").toString());
				}
				bData.setDatavlue(SysUtil.toDouble(map.get("datavalue")
						.toString()));

				list.add(bData);
			}

		}

		return list;
	}

	/**
	 * 医院名称
	 * 
	 * @param hospid
	 * @return
	 */
	private String getHospName(String hospid) {

		Map param = new HashMap();
		param.put("seqHosp", Integer.parseInt(hospid));

		DbHospInfo dbhosp = sqlSession.selectOne("findByIdAreaRatioBusiness",
				param);
		try {
			return dbhosp.getHospName();
		} catch (Exception e) {
			logg.error("AreaRatioBusiness-->getHospName", e);

		}
		return null;
	}

	/**
	 * 数据转换
	 * 
	 * @param rt
	 * @return
	 */
	private List<AreaRatioData> getAreaRatioDataList(RetCode rt) {
		List<AreaRatioData> list = null;
		if (null != rt.getObj()) {
			List temp = (List) rt.getObj();
			list = new ArrayList<AreaRatioData>();
			for (int i = 0; i < temp.size(); i++) {
				Map map = (Map) temp.get(i);
				AreaRatioData aData = new AreaRatioData();
				aData.setId(map.get("id") + "");
				aData.setName(map.get("name") + "");
				aData.setUnitname(map.get("unitname") + "");
				aData.setDatavlue(SysUtil.toDouble(map.get("datavalue") + ""));
				try {
					aData.setNote1(map.get("area").toString());
					aData.setNote2(map.get("sarea").toString());
				} catch (Exception e) {
					logg.error("AreaRatioMgr-->getAreaRatioDataList", e);
				}

				list.add(aData);
			}
		}

		return list;
	}

	/**
	 * 数据转换
	 * 
	 * @param rt
	 * @param hospids
	 * @return
	 */
	private List<AreaRatioData> getAreaRatioDataList(RetCode rt,
			String[] hospids) {
		HashMap<String, String> hosM = new HashMap<String, String>();
		for (String obj : hospids) {
			hosM.put(obj, obj);
		}
		String id = null;
		String name = null;

		List<AreaRatioData> list = null;
		if (null != rt.getObj()) {
			List temp = (List) rt.getObj();
			list = new ArrayList<AreaRatioData>();
			for (int i = 0; i < temp.size(); i++) {
				Map map = (Map) temp.get(i);
				AreaRatioData aData = new AreaRatioData();
				if (null == id) {
					id = map.get("id") + "";
					name = map.get("name") + "";
				}

				aData.setId(map.get("id") + "");
				aData.setName(map.get("name") + "");
				aData.setUnitname(map.get("unitname") + "");
				aData.setDatavlue(SysUtil.toDouble(map.get("datavalue") + ""));
				try {
					aData.setNote1(map.get("area").toString());
					aData.setNote2(map.get("sarea").toString());
				} catch (Exception e) {
					logg.error("AreaRatioBusiness-->getAreaRatioDataList", e);

				}
				hosM.remove(map.get("unitname") + "");

				list.add(aData);
			}

			Set<String> set = hosM.keySet();
			for (String unitname : set) {
				AreaRatioData aData = new AreaRatioData();
				aData.setId(id);
				aData.setName(name);
				aData.setUnitname(unitname);
				aData.setDatavlue(0D);
				list.add(aData);
			}

		}

		return list;
	}

	/**
	 * 生成图片、图片map、表格数据(各医院单区域)
	 * 
	 * @param mylist
	 * @param savePath
	 * @param imagePath
	 * @param dbusers
	 * @return
	 */
	public CMyShowData findManyAreaCMyShowData(CMyListData mylist,
			String savePath, String imagePath, DbUsers dbusers, Integer useId) {
		CMyShowData obj = new CMyShowData();

		StandardEntityCollection sec = new StandardEntityCollection();
		ChartRenderingInfo info = new ChartRenderingInfo(sec);

		String name = Systime.DateToString(new Date(), "yyyyMMddHHmmss");
		String smPath = savePath + "\\jfreechartNxiao" + dbusers.getUserid()
				+ name + ".png"; // 生成图片名
		String showPath = imagePath + "jfreechartNxiao" + dbusers.getUserid()
				+ name + ".png"; // 显示图片路径

		showPath = SysUtil.replacePath(showPath);
		smPath = SysUtil.replacePath(smPath);
		// 区域
		Map parameter = new HashMap();
		parameter.put("sequence", Integer.toString(useId.shortValue()));
		DbBuildingrate dbDic = sqlSession.selectOne("findByIdDbBuildingrate",
				parameter);
		// 各区院单区域比例图
		List<ChartData> listBiLi = mylist.getManyHospOneUseChartData(
				useId + "", dbDic.getYt());
		saveDrawOneUseBiLi(listBiLi, info, 650, 400, smPath,
				"各医院" + dbDic.getYt() + "区域总面积情况图");
		String myDrawS = showPath;
		obj.setMyDrawS(myDrawS.replaceAll("\\\\", "/"));
		obj.setUseMapS(ChartUtilities.getImageMap("useMapS", info));

		obj.setMytable(mylist.getManyHospOneUseTableData(listBiLi, useId + ""));
		obj.getMytable().setTitle("各医院" + dbDic.getYt() + "区域总面积情况");
		obj.setUseName(dbDic.getYt());
		return obj;
	}

	/**
	 * 绘图:各医院单区域面积比例
	 * 
	 * @param list
	 * @param info
	 * @param width
	 * @param height
	 * @param imagePath
	 * @param zChart
	 */
	private void saveDrawOneUseBiLi(List<ChartData> listBiLi,
			ChartRenderingInfo info, int width, int height, String imagePath,
			String title) {
		JFreeChart chart = (new OneUseBiLiChart(listBiLi, title)).getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();
		} catch (Exception e) {
			logg.error("AreaRatioMgr-->saveDrawOneUseBiLi", e);

		}
	}

	/**
	 * 区域面积比例,区域面积情况，医院总面积情况
	 * 
	 * @param dbusers
	 *            登陆用户
	 * @param selUnits
	 *            分析对象(医院Id集合)
	 * @return
	 */
	public RetCode findArea(DbUsers dbusers, String selUnits) {
		RetCode rt = new RetCode();
		try {

			String[] hospid = this.getHospIds(dbusers, selUnits);
			List<AreaRatioData> oldlist = null;
			if (isAlone) {
				oldlist = this.findQuyuAreaData(hospid[0]);// 区域面积比例
			} else {
				oldlist = this.findQuyuAreaData(hospid); // 区域面积比例
			}
			List<AreaRatioBData> baselist = this.findQuyuAreaBaseData();// 区域面积基准

			if (null != oldlist && null != baselist) {
				CMyListData list = new CMyListData(oldlist, baselist);
				rt.setObj(list);
			} else {
				rt.setDetail("暂无数据！");
			}
		} catch (Exception e) {
			rt.setDetail("操作失败,请联系管理员！");
			rt.setObj(null);
			logg.error("AreaRatioMgr-->findArea", e);
			e.printStackTrace();
		}

		return rt;
	}

	/**
	 * 区域面积比例
	 * 
	 * @param hospid
	 *            医院Id
	 * @param power
	 *            能源类型
	 */
	private List<AreaRatioData> findQuyuAreaData(String hospid) {

		Map parameter = new HashMap();
		parameter.put("hospid", hospid);
		RetCode rt = this.getData("findQuyuAreaData", parameter);
		return this.getAreaRatioDataList(rt);
	}

	/**
	 * 各医院区域总面积比例
	 * 
	 * @param hospid
	 *            医院Id
	 * @param power
	 *            能源类型
	 */
	private List<AreaRatioData> findQuyuAreaData(String[] hospids) {
		String str = "";
		for (int i = 0; null != hospids && i < hospids.length; i++) {
			if (i > 0) {
				str += ",";
			}
			str += "'" + hospids[i] + "'";
		}

		Map parameter = new HashMap();
		parameter.put("str", str);
		RetCode rt = this.getData("findQuyuAreaDataArray", parameter);

		return this.getAreaRatioDataList(rt, hospids);
	}

	/**
	 * 生成图片、图片map、表格数据
	 * 
	 * @param mylist
	 * @param savePath
	 * @param imagePath
	 * @param dbusers
	 * @return
	 */
	public CMyShowData findCMyShowData(CMyListData mylist, String savePath,
			String imagePath, DbUsers dbusers) {
		CMyShowData obj = new CMyShowData();

		StandardEntityCollection sec = new StandardEntityCollection();
		ChartRenderingInfo info = new ChartRenderingInfo(sec);

		String name = Systime.DateToString(new Date(), "yyyyMMddHHmmss");
		String smPath = savePath + "\\jfreechartNxiao" + dbusers.getUserid()
				+ name + ".png"; // 生成图片名
		String showPath = imagePath + "jfreechartNxiao" + dbusers.getUserid()
				+ name + ".png"; // 显示图片路径

		showPath = SysUtil.replacePath(showPath);
		smPath = SysUtil.replacePath(smPath);

		if (isAlone) {
			// 比例图
			List<ChartData> listBili = mylist.getBiliChartData();
			saveDrawBiLi(listBili, info, 650, 400, smPath, hospName + "面积比例图");
			String myDrawF = showPath;
			obj.setMyDrawF(myDrawF.replaceAll("\\\\", "/"));
			obj.setUseMapF(ChartUtilities.getImageMap("useMapF", info));

			name = Systime.DateToString(new Date(), "yyyyMMddHHmmss");
			smPath = savePath + "\\jfreechartNxiao" + dbusers.getUserid()
					+ name + "2.png"; // 生成图片名
			showPath = imagePath + "jfreechartNxiao" + dbusers.getUserid()
					+ name + "2.png"; // 显示图片路径

			showPath = SysUtil.replacePath(showPath);
			smPath = SysUtil.replacePath(smPath);
			// 面积图
			List<ChartData> listArea = mylist.getOneHospAreaChartData();
			saveDrawArea(listArea, info, 650, 400, smPath, hospName + "区域面积情况图");
			String myDrawS = showPath;
			obj.setMyDrawS(myDrawS.replaceAll("\\\\", "/"));
			obj.setUseMapS(ChartUtilities.getImageMap("useMapS", info));

			obj.setMytable(mylist.getOneHospitalTableData(listBili, listArea));
			obj.getMytable().setTitle(hospName + "面积比例及面积情况");

		} else {
			// 面积图
			List<ChartData> listArea = mylist.getManyHospAreaChartData();
			saveDrawArea(listArea, info, 650, 400, smPath, "各医院总面积情况图");
			String myDrawS = showPath;
			obj.setMyDrawS(myDrawS.replaceAll("\\\\", "/"));
			obj.setUseMapS(ChartUtilities.getImageMap("useMapS", info));

			obj.setMytable(mylist.getManyHospTableData(listArea));
			obj.getMytable().setTitle("各医院总面积情况");
		}

		return obj;
	}

	/**
	 * 绘图:面积比例
	 * 
	 * @param list
	 * @param info
	 * @param width
	 * @param height
	 * @param imagePath
	 * @param zChart
	 */
	private void saveDrawBiLi(List<ChartData> list, ChartRenderingInfo info,
			int width, int height, String imagePath, String title) {
		JFreeChart chart = (new BiLiChart(list, title)).getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();
		} catch (Exception e) {
			logg.error("AreaRatioMgr-->saveDrawBiLi", e);

		}
	}

	/**
	 * 绘图:单医院区域面积
	 * 
	 * @param list
	 * @param info
	 * @param width
	 * @param height
	 * @param imagePath
	 * @param zChart
	 */
	private void saveDrawArea(List<ChartData> list, ChartRenderingInfo info,
			int width, int height, String imagePath, String title) {
		JFreeChart chart = (new AreaChart(list, title)).getChart();
		try {
			FileOutputStream fileout = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsPNG(fileout, chart, width, height, info);
			fileout.close();
		} catch (Exception e) {
			logg.error("AreaRatioMgr-->saveDrawArea", e);

		}
	}

}

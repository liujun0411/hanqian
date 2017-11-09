package com.hanqian.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbBuildingrate;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

@Service
public class BuildRateBusiness extends BaseBusiness {
	private static final Logger logg = Logger
			.getLogger(BuildRateBusiness.class);

	/**
	 * 更新区域标准比例
	 * 
	 * @param obj
	 * @return
	 */
	public int insertBhildRate(JSONObject obj) {
		// 获得日期
		SimpleDateFormat sim = new SimpleDateFormat("yyyy");
		String currentDate = sim.format(new Date());

		// 更新数据
		try {
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("mz"), "1"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("jz"), "2"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("zy"), "3"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("yj"), "4"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("bz"), "5"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("xz"), "6"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("ky"), "7"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("jy"), "8"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("sh"), "9"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("ck"), "10"));
			this.insertBuildRate(new DbBuildingrate(currentDate, obj
					.getDouble("qt"), "11"));

			return 1;
		} catch (Exception e) {
			logg.error("BuildRateBusiness-->insertBhildRate", e);

		}

		return 0;
	}

	// 添加或更新
	private void insertBuildRate(DbBuildingrate rate) throws Exception {
		DbBuildingrate row = null;
		RetCode rt = this.findBuildRate(rate);
		try {
			row = ((List<DbBuildingrate>) rt.getObj()).get(0);
		} catch (Exception e) {
			logg.error("BuildRateBusiness-->insertBuildRate", e);
			// TODO: handle exception
		}
		if (null == row) {
			// rateDao.save(rate);
			sqlSession.insert("insertBuildRate", rate);
		} else {
			row.setFz(rate.getFz());
			// rateDao.merge(row);
		}
	}

	/**
	 * 区域标准比例(条件为空,则不查询)
	 * 
	 * @param rate
	 * @return
	 */
	private RetCode findBuildRate(DbBuildingrate rate) {
		RetCode rc = new RetCode(1001, "无查询数据", "未找到查询的相应数据");
		try {
			Map param = this.getDbBuildRateMap(rate);

			return this.getData("findBuildRate", param);
			// return this.findByCriteria(rateDao, getDbBuildRateCrit(rate));
		} catch (Exception e) {
			rc.setCode(1004);
			rc.setDesc("查询失败");
			rc.setDetail("查询失败");
			rc.setObj(null);
			logg.error("BuildRateBusiness-->findBuildRate", e);
			e.printStackTrace();
		}
		return rc;
	}

	private Map getDbBuildRateMap(DbBuildingrate rate) {
		Map map = new HashMap();

		if (rate != null) {
			if (!SysUtil.isNull(rate.getInfoyear())) {
				// dc.add(Restrictions.eq("infoyear", rate.getInfoyear()));
				map.put("infoyear", rate.getInfoyear());
			} else {
				map.put("infoyear", null);
			}
			if (!SysUtil.isNull(rate.getYt())) {
				// dc.add(Restrictions.eq("yt", rate.getYt()));
				map.put("yt", rate.getYt());
			} else {
				map.put("yt", null);
			}
			if (!SysUtil.isNull(rate.getSequence())) {
				// dc.add(Restrictions.eq("sequence", rate.getSequence()));
				map.put("sequence", rate.getSequence());
			} else {
				map.put("sequence", null);
			}
		} else {
			map.put("infoyear", null);
			map.put("yt", null);
			map.put("sequence", null);
		}

		return map;
	}

	/**
	 * 添加 医院楼宇用途比例设置
	 * */
	public void insertBhrate(List list) {
		// 获得日期
		SimpleDateFormat sim = new SimpleDateFormat("yyyy");
		String currentDate = sim.format(new Date());

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				String s = list.get(i).toString();
				if (s.length() > 32) {
					DbBuildingrate buildingrate = new DbBuildingrate();
					buildingrate.setSequence(s.substring(0, 32));
					this.findBuildingrate(buildingrate);
					buildingrate.setInfoyear(currentDate);
					buildingrate.setSequence(s.substring(0, 32));
					// buildingrate.setFh(s.substring(34, 36));
					buildingrate.setFz(Double.parseDouble(s.substring(36)));
					buildingrate.setYt(s.substring(32, 34));
					sqlSession.update("updateBuildRate", buildingrate);
					// rateDao.merge(buildingrate);//updateBuildRate
				} else {
					DbBuildingrate buildingrate = new DbBuildingrate();
					buildingrate.setInfoyear(currentDate);
					// buildingrate.setFh(s.substring(2, 4));
					buildingrate.setFz(Double.parseDouble(s.substring(4)));
					buildingrate.setYt(s.substring(0, 2));
					sqlSession.insert("insertBuildRate", buildingrate);
					// rateDao.save(buildingrate);
				}
			}
		}
	}

	/**
	 * 查询 楼宇用途比例设置
	 * 
	 * */
	public RetCode findBuildingrate(DbBuildingrate rate) {
		RetCode rc = new RetCode(1001, "无查询数据", "未找到查询的相应数据");
		try {
			Map param = this.getDbBuildRateMap(rate);
			return this.getData("findBuildRate", param);
			// return this.findByCriteria(rateDao, getDbBuildingrateCrit(rate));
		} catch (Exception e) {
			rc.setCode(1004);
			rc.setDesc("查询失败");
			rc.setDetail("查询失败");
			rc.setObj(null);
			logg.error("BuildRateBusiness-->findBuildingrate", e);
			e.printStackTrace();
		}
		return rc;
	}

	/**
	 * 数据上报解析入库
	 * */
	public void insertReportBuilding(List<DbBuildingrate> rate)
			throws Exception {
		if (rate != null) {
			DbBuildingrate dbBuildingrate2 = new DbBuildingrate();
			dbBuildingrate2.setInfoyear(rate.get(0).getInfoyear());
			RetCode rc = this.findBuildingrate(dbBuildingrate2);
			if (rc.getObj() != null) {

				Map map = new HashMap();
				map.put("infoyear", rate.get(0).getInfoyear());

				sqlSession.delete("deleteInsertReportBuilding", map);
				for (DbBuildingrate dbBuildingrate : rate) {
					// rateDao.save(dbBuildingrate);
					sqlSession.insert("insertBuildRate", dbBuildingrate);
				}
			} else {
				for (DbBuildingrate dbBuildingrate : rate) {
					// rateDao.save(dbBuildingrate);
					sqlSession.insert("insertBuildRate", dbBuildingrate);
				}
			}
		}
	}

}

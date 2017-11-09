package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

@Service
public class EqNavigationBusiness extends BaseBusiness {
	private static final Logger logg = Logger
			.getLogger(EqNavigationBusiness.class);

	public RetCode findEquipBuilding(String eqTypeId, String buildId,
			String unitType, String site, String storey, String serviceStorey,
			String serviceBuildId, String areas, String square,
			int currentPage, int pageSize) {

		if (logg.isDebugEnabled())
			logg.debug("进入EqNavigationBusiness.findEquipBuilding!参数eqTypeId:"
					+ eqTypeId);
		Map map = new HashMap();
		if (StringUtil.isNotEmpty(eqTypeId)) {
			map.put("eqTypeId", eqTypeId);
		} else {
			map.put("eqTypeId", null);
		}
		if (StringUtil.isNotEmpty(buildId) && !"0".equals(buildId)) {
			map.put("buildId", buildId);
		} else {
			map.put("buildId", null);
		}
		if (StringUtil.isNotEmpty(unitType)) {
			map.put("unitType", unitType);
		} else {
			map.put("unitType", null);
		}
		if (StringUtil.isNotEmpty(site)) {
			map.put("site", site);
		} else {
			map.put("site", null);
		}
		if (StringUtil.isNotEmpty(storey) && !"0".equals(storey)) {
			map.put("storey", storey);
		} else {
			map.put("storey", null);
		}

		if (StringUtil.isNotEmpty(serviceStorey)) {
			map.put("serviceStorey", serviceStorey);
		} else {
			map.put("serviceStorey", null);
		}
		if (StringUtil.isNotEmpty(serviceBuildId)
				&& !"0".equals(serviceBuildId)) {
			map.put("serviceBuildId", serviceBuildId);
		} else {
			map.put("serviceBuildId", null);
		}
		if (StringUtil.isNotEmpty(areas)) {
			map.put("areas", areas);
		} else {
			map.put("areas", null);
		}
		if (StringUtil.isNotEmpty(square)) {
			map.put("square", square);
		} else {
			map.put("square", null);
		}

		RetCode rt = this.getPageData("findEquipBuilding", map, currentPage,
				pageSize);
		return rt;
	}

	public Map getFindEquipBuildingSql() {
		return PerformSQLUtil.get("findEquipBuilding", this);
	}

}

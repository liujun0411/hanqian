package com.hanqian.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentBusiness extends BaseBusiness{

	public static final Logger log = Logger.getLogger(EnvironmentBusiness.class);
	
	public List showIllumination(){
		System.out.println("----------------------进Business");
		List list = new ArrayList<>();
		try {
			list = sqlSession.selectList("showIllumination");
		} catch (Exception e) {
			log.error("查询室外光照出错:" + e);
		}
		return list;
	}

	public List showHeatPumpSystem() {
		System.out.println("----------------------进Business");
		List list = new ArrayList<>();
		try {
			list = sqlSession.selectList("showHeatPumpSystem");
		} catch (Exception e) {
			log.error("查询热泵设备出错:" + e);
		}
		return list;
	}
}

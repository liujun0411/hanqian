package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hanqian.pojo.AlarmLog;
import com.hanqian.pojo.BusinessModule;
import com.hanqian.pojo.BusinessModuleLog;
import com.hanqian.pojo.UserPermissionLog;
//此类未注入xml
public class LogBusiness  extends BaseBusiness{
	private static final Logger logg = Logger.getLogger(LogBusiness.class);
	
	public BusinessModule findModuleByShort(String shortName) throws Exception{
		return this.findModuleByShortMethod(shortName);
	}
	
	public BusinessModule findModuleByShortMethod(String shortName) throws Exception {		
		Map parameter = new HashMap();
		parameter.put("moduleshort", shortName.trim());
		List <BusinessModule> moduleList =  sqlSession.selectList("findModuleByShort", parameter);
		if (moduleList!=null&&!moduleList.isEmpty()) {
			return moduleList.get(0);
		}
		return null;
	}
	
	public void insertModuleOperLog(BusinessModuleLog businessmodulelog) throws Exception{
		
		sqlSession.insert("insertModuleOperLog", businessmodulelog);
	}
	public void insertAlarmModuleOperLog(AlarmLog alarmLog) throws Exception{
		
		sqlSession.insert("insertAlarmModuleOperLog", alarmLog);
	}
	public void insertPermissionModuleOperLog(UserPermissionLog userPermissionLog) throws Exception{
		
		sqlSession.insert("insertPermissionModuleOperLog", userPermissionLog);
	}
}

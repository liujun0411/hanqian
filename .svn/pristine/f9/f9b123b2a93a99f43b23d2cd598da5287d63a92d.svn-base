package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbEqParam;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 设备参数 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service
public class DbEquipParamBusiness extends BaseBusiness {
	public static final Logger log = Logger
			.getLogger(DbEquipParamBusiness.class);

	/**
	 * 新增设备参数信息
	 * 
	 * @param dbEqParam
	 * @return
	 */
	public boolean insertDbEquipParam(DbEqParam dbEqParam) {
		if (log.isDebugEnabled())
			log.debug("进入DbEquipParamBusiness.insertDbEquipParam!对象DbEqParam:"
					+ dbEqParam);
		boolean bool = true;
		try {
			sqlSession.insert("insertDbEquipParam", dbEqParam);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getInsertDbEquipParamSql() {
		return PerformSQLUtil.get("insertDbEquipParam", this);
	}

	/**
	 * 更新设备参数信息
	 * 
	 * @param dbEqParam
	 * @return
	 */
	public boolean updateDbEquipParam(DbEqParam dbEqParam) {
		if (log.isDebugEnabled())
			log.debug("进入DbEquipParamBusiness.updateDbEquipParam!对象DbEqParam:"
					+ dbEqParam);
		boolean bool = true;
		
		
		Map map = new HashMap();
		
		if (!SysUtil.isNullObject(dbEqParam)) {
			if (dbEqParam.getSeq()!=null) {
				map.put("seq", dbEqParam.getSeq());				
			}else{
				map.put("seq", null);		
			}
			if (!SysUtil.isNull(dbEqParam.getParamValue())) {
				map.put("paramValue",dbEqParam.getParamValue());	
			}else{
				map.put("paramValue", null);			
			}
			
		}else{
			map.put("seq", null);		
			map.put("paramValue", null);			
		}
		
		
		try {
			sqlSession.update("updateDbEquipParam", map);
		} catch (Exception e) {
			e.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public Map getUpdateDbEquipParamSql() {
		return PerformSQLUtil.get("updateDbEquipParam", this);
	}

}

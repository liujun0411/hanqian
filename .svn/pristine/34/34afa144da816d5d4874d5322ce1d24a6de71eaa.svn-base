package com.hanqian.business;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 能源类型 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service("EnergyTypeBusiness")
public class EnergyTypeBusiness extends BaseBusiness {
	private static final Logger log = Logger
			.getLogger(EnergyTypeBusiness.class);

	/**
	 * 获得所有的能源类型
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findAllEnergyType() {
		// return energyTypeMgr.findAllEnergyType();
		if (log.isDebugEnabled())
			log.debug("进入EnergyTypeBusiness.findAllEnergyType!");

		return this.getData("findAllEnergyType", null);
	}

	public Map getFindAllEnergyTypeSql() {
		return PerformSQLUtil.get("findAllEnergyType", this);
	}

}

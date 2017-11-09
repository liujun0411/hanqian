package com.hanqian.business;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2013年11月6日
 * @see
 */
@Service
public class DbPointBussiness extends BaseBusiness {
	public static final Logger log = Logger.getLogger(DbPointBussiness.class);

	/**
	 * 从DB_POINT表内取得所有需要从sdcd取得的点位信息,如果reflash字段值小于0,则认为不需要从sdcd取得.
	 * 
	 * @param 2013年11月6日
	 * @param @return
	 * @return RetCode
	 */
	public RetCode getAllPoint() {
		if (log.isDebugEnabled())
			log.debug("进入DbinfoBusiness.getAllPoint!");
		log.debug("进入DbEquipParamBusiness.insertDbEquipParam!");
		return this.getData("findBySQLDbPoint", null);
	}

	public Map getGetAllPointSql() {
		return PerformSQLUtil.get("findBySQLDbPoint", this);
	}

}

package com.hanqian.business;

import org.apache.log4j.Logger;

import com.hanqian.util.RetCode;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author
 * @version 1.4 2012-9-10
 * @see
 */
// 此类在配置文档中没有被注入 2015-04-10号转换Mybatis调用时
public class BuildStaBusiness extends BaseBusiness {
	private static final Logger log = Logger.getLogger(BuildStaBusiness.class);

	/**
	 * 获取楼宇状态
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public RetCode findBuildingStatus() {
		// return buildStaMgr.findBuildStatus();
		if (log.isDebugEnabled())
			log.debug("进入BuildingPicBusiness.findBuildingStatus!");
		// Manager中方法不都被注释掉了
		RetCode rt = new RetCode();
		return rt;
	}

}

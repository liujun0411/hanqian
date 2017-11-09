package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.util.RetCode;
import com.hanqian.util.RetCodeMysql;
import com.hanqian.util.StringUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service("dbsqlcontroldatabusiness")
public class DbSQLControldataBusiness extends MysqlBaseBusiness{

	public static final Logger log = Logger
			.getLogger(DbSQLControldataBusiness.class);
	/**
	 * SDCD项目对应的Mysql数据库的列表List页面
	 * 
	 * @param 2015-11-30
	 * @param @param currentPage
	 * @param @param pageSize
	 * @param @param 
	 * @param @return
	 * @return RetCode
	 */
	public RetCodeMysql sDCDfindDiwanweiInfoList(int currentPage,int  pageSize,String projectpoint ,String datetime) {
		//RetCode rc=new RetCode();  
		if (log.isDebugEnabled())
			log.debug("进入DbControldataBusiness.sDCDfindDiwanweiInfoList");
	/*	int a = mysqlSqlSession.selectOne("mysqltest");*/
		Map map = new HashMap();
		if (StringUtil.isNotEmpty(projectpoint)) {
			map.put("projectpoint", projectpoint);
		}else{
			map.put("projectpoint", null);
		}
		if (StringUtil.isNotEmpty(datetime)) {
			map.put("datetime", datetime);
		}else{
			map.put("datetime", null);
		}
		/*map.put("currentPage", currentPage);
		map.put("pageSize", pageSize);
		 List list = mysqlSqlSession.selectList("sDCDfindDiwanweiInfoList",map);
		return list;*/
		return this.getPageData("sDCDfindDiwanweiInfoList", map, currentPage, pageSize);
	}
}
